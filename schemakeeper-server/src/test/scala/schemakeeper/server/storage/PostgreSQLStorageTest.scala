package schemakeeper.server.storage

import java.sql.DriverManager
import java.util

import cats.Id
import com.dimafeng.testcontainers.{ForAllTestContainer, PostgreSQLContainer}
import com.typesafe.config.{Config, ConfigFactory}
import org.apache.avro.{Schema, SchemaBuilder}
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, WordSpec}
import schemakeeper.api.SubjectMetadata
import schemakeeper.schema.{CompatibilityType, SchemaType}
import schemakeeper.server.Configuration
import schemakeeper.server.service.DBBackedService

import scala.util.Try

class PostgreSQLStorageTest extends WordSpec with ForAllTestContainer with BeforeAndAfterEach with BeforeAndAfterAll {
  override val container: PostgreSQLContainer = PostgreSQLContainer("postgres:latest")

  lazy val schemaStorage = {
    val map: util.Map[String, AnyRef] = new util.HashMap[String, AnyRef]
    map.put("schemakeeper.storage.username", container.username)
    map.put("schemakeeper.storage.password", container.password)
    map.put("schemakeeper.storage.schema", "schemakeeper")
    map.put("schemakeeper.storage.driver", container.driverClassName)
    map.put("schemakeeper.storage.url", container.jdbcUrl)

    val config: Config = ConfigFactory.parseMap(map)
    DBBackedService.apply[Id](Configuration.apply(config))
  }

  lazy val connection = {
    Class.forName(container.driverClassName)
    val connection = DriverManager.getConnection(container.jdbcUrl, container.username, container.password)
    connection.setAutoCommit(false)
    connection
  }

  override protected def afterEach(): Unit = {
    connection.createStatement().execute("update schemakeeper.config set config_value = 'backward' where config_name = 'default.compatibility'")
    connection.createStatement().execute("truncate schemakeeper.subject cascade")
    connection.commit()
  }

  override protected def afterAll(): Unit = {
    connection.close()
  }

  "Postgresql backend for schema storage" should {
    "return empty results" when {
      "database is empty" in {
        assert(schemaStorage.subjects().isEmpty)
        assert(schemaStorage.getLastSchema("empty").isEmpty)
        assert(schemaStorage.getLastSchemas("empty").isEmpty)
        assert(schemaStorage.subjectSchemaByVersion("empty", 1).isEmpty)
        assert(schemaStorage.subjectOnlySchemaByVersion("empty", 1).isEmpty)
        assert(schemaStorage.schemaById(1).isEmpty)
      }
    }

    "register new schema and subject" in {
      val schema: Schema = SchemaBuilder
        .builder("test")
        .record("test")
        .fields()
        .requiredString("f1")
        .endRecord()

      val id = schemaStorage.registerNewSubjectSchema("test", schema.toString, SchemaType.AVRO)

      assert(id > 0)
      assertResult(schemaStorage.getLastSchema("test").get)(schema.toString())
      assertResult(schemaStorage.schemaById(id).get)(schema.toString())
      assertResult(schemaStorage.subjectOnlySchemaByVersion("test", 1).get)(schema.toString())
    }

    "do not register new schema version due to compatibility issues" in {
      val schema: Schema = SchemaBuilder
        .builder("test_namespace")
        .record("test")
        .fields()
        .requiredString("f1")
        .endRecord()

      val updatedSchema: Schema = SchemaBuilder
        .builder("test_namespace")
        .record("test")
        .fields()
        .requiredString("some_name") // replace required field by another field
        .endRecord()

      schemaStorage.registerNewSubjectSchema("test", schema.toString, SchemaType.AVRO)
      schemaStorage.updateSubjectCompatibility("test", CompatibilityType.FULL)
      Try {
        schemaStorage.registerNewSubjectSchema("test", updatedSchema.toString, SchemaType.AVRO)
      }

      assert(schemaStorage.getLastSchemas("test").length == 1)
    }

    "register multiple schema versions" in {
      val schema: Schema = SchemaBuilder
        .builder("test_namespace")
        .record("test")
        .fields()
        .requiredString("f1")
        .endRecord()

      val updatedSchema: Schema = SchemaBuilder
        .builder("test_namespace")
        .record("test")
        .fields()
        .requiredString("f1")
        .optionalString("f2")
        .endRecord()

      schemaStorage.registerNewSubjectSchema("test", schema.toString, SchemaType.AVRO)
      schemaStorage.updateSubjectCompatibility("test", CompatibilityType.BACKWARD)
      schemaStorage.registerNewSubjectSchema("test", updatedSchema.toString, SchemaType.AVRO)

      assert(schemaStorage.getLastSchemas("test").length == 2)
      assertResult(List(1, 2))(schemaStorage.subjectVersions("test"))
    }

    "delete specific subject schema version" in {
      schemaStorage.registerNewSubjectSchema("test", Schema.create(Schema.Type.STRING).toString, SchemaType.AVRO)
      schemaStorage.updateSubjectCompatibility("test", CompatibilityType.NONE)
      schemaStorage.registerNewSubjectSchema("test", Schema.create(Schema.Type.INT).toString, SchemaType.AVRO)

      assert(schemaStorage.getLastSchemas("test").length == 2)
      assert(schemaStorage.deleteSubjectVersion("test", 2))
      assert(schemaStorage.getLastSchemas("test").length == 1)
    }

    "delete subject" in {
      schemaStorage.registerNewSubjectSchema("test", Schema.create(Schema.Type.STRING).toString, SchemaType.AVRO)
      schemaStorage.updateSubjectCompatibility("test", CompatibilityType.NONE)
      schemaStorage.registerNewSubjectSchema("test", Schema.create(Schema.Type.INT).toString, SchemaType.AVRO)

      assert(schemaStorage.getLastSchemas("test").length == 2)
      schemaStorage.deleteSubject("test")
      assert(schemaStorage.getLastSchemas("test").isEmpty)
    }

    "return default compatibility type" in {
      assertResult(CompatibilityType.BACKWARD)(schemaStorage.getGlobalCompatibility().get)
    }

    "update compatibility type" in {
      assertResult(CompatibilityType.FORWARD)(schemaStorage.updateGlobalCompatibility(CompatibilityType.FORWARD).get)
    }

    "return None" when {
      "get subject meta for not existing subject" in {
        val meta = schemaStorage.getSubjectMetadata("A1")

        assert(meta.isEmpty)
      }
    }

    "return subject metadata" in {
      schemaStorage.registerNewSubjectSchema("test", Schema.create(Schema.Type.STRING).toString, SchemaType.AVRO)
      val meta = schemaStorage.getSubjectMetadata("test")
      val expected = SubjectMetadata.instance("test", CompatibilityType.BACKWARD, SchemaType.AVRO, Array(1))

      assertResult(expected)(meta.get)
    }
  }
}

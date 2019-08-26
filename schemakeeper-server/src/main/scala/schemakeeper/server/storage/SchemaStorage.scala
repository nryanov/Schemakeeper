package schemakeeper.server.storage

import schemakeeper.avro.compatibility.CompatibilityType
import schemakeeper.server.metadata.AvroSchemaMetadata


trait SchemaStorage[F[_]] {
  def subjects(): F[List[String]]

  def subjectVersions(subject: String): F[List[Int]]

  def subjectSchemaByVersion(subject: String, version: Int): F[Option[AvroSchemaMetadata]]

  def subjectOnlySchemaByVersion(subject: String, version: Int): F[Option[String]]

  def schemaById(id: Int): F[Option[String]]

  def deleteSubject(subject: String): F[Boolean]

  def deleteSubjectVersion(subject: String, version: Int): F[Boolean]

  def updateSubjectCompatibility(subject: String, compatibilityType: CompatibilityType): F[Boolean]

  def getSubjectCompatibility(subject: String): F[Option[CompatibilityType]]

  def getLastSchema(subject: String): F[Option[String]]

  def getLastSchemas(subject: String): F[List[String]]

  def registerNewSubjectSchema(subject: String, schema: String, version: Int, schemaHash: String): F[Int]

  def checkSubjectExistence(subject: String): F[Boolean]

  def registerNewSubject(subject: String): F[Int]

  def getNextVersionNumber(subject: String): F[Int]
}
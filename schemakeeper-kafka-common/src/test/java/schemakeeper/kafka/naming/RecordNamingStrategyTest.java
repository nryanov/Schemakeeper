package schemakeeper.kafka.naming;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.kafka.common.errors.SerializationException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RecordNamingStrategyTest {
    private NamingStrategy strategy = new RecordNamingStrategy();

    @Test
    public void returnNullWhenSchemaIsNull() {
        assertNull(strategy.resolveSubjectName("topic", false, null));
    }

    @Test
    public void throwErrorForNotRecordSchema() {
        assertThrows(SerializationException.class, () -> strategy.resolveSubjectName("topic", false, Schema.create(Schema.Type.INT)));
    }

    @Test
    public void returnSubjectName() {
        Schema schema = SchemaBuilder.record("test")
                .fields()
                .requiredString("f1")
                .endRecord();
        assertEquals("test", strategy.resolveSubjectName("topic", false, schema));
    }
}

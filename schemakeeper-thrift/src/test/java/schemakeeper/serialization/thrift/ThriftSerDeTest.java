package schemakeeper.serialization.thrift;


import org.junit.jupiter.api.Test;
import schemakeeper.client.InMemorySchemaKeeperClient;
import schemakeeper.exception.ThriftDeserializationException;
import schemakeeper.exception.ThriftSerializationException;
import schemakeeper.serialization.thrift.test.ThriftMsgV1;
import schemakeeper.serialization.thrift.test.ThriftMsgV2;
import schemakeeper.serialization.thrift.test.ThriftMsgV5;

import static org.junit.jupiter.api.Assertions.*;

public class ThriftSerDeTest {
    @Test
    public void simpleSerializationTest() throws ThriftSerializationException, ThriftDeserializationException {
        InMemorySchemaKeeperClient client = new InMemorySchemaKeeperClient("none");
        ThriftSerializer serializer = new ThriftSerializer(client);
        ThriftDeserializer deserializer = new ThriftDeserializer(client);

        ThriftMsgV1 msgV1 = new ThriftMsgV1("f1");
        byte[] result = serializer.serialize("test", msgV1);
        Object d = deserializer.deserialize(result);

        assertEquals(msgV1, d);
    }

    @Test
    public void throwErrorDueToSchemaIncompatibility() throws ThriftSerializationException {
        InMemorySchemaKeeperClient client = new InMemorySchemaKeeperClient("backward");
        ThriftSerializer s1 = new ThriftSerializer(client);
        ThriftSerializer s2 = new ThriftSerializer(client);

        ThriftMsgV1 msgV1 = new ThriftMsgV1("f1");
        ThriftMsgV2 msgV2 = new ThriftMsgV2(1);

        s1.serialize("test", msgV1);

        assertThrows(RuntimeException.class, () -> s2.serialize("test", msgV2));
    }

    @Test
    public void readDataUsingOldSchema() throws ThriftSerializationException, ThriftDeserializationException {
        InMemorySchemaKeeperClient client = new InMemorySchemaKeeperClient("full");
        ThriftSerializer s1 = new ThriftSerializer(client);
        ThriftSerializer s2 = new ThriftSerializer(client);
        ThriftDeserializer deserializer = new ThriftDeserializer(client);

        ThriftMsgV1 msgV1 = new ThriftMsgV1("f1");
        s1.serialize("test", msgV1);

        ThriftMsgV5 msgV5 = new ThriftMsgV5("f1");
        byte[] result = s2.serialize("test", msgV5);

        Object d = deserializer.deserialize(result, ThriftMsgV1.class);

        assertEquals(msgV1, d);
    }

    @Test
    public void readDataWithoutSpecifiedSchema() throws ThriftSerializationException, ThriftDeserializationException {
        InMemorySchemaKeeperClient client = new InMemorySchemaKeeperClient("full");
        ThriftSerializer s1 = new ThriftSerializer(client);
        ThriftSerializer s2 = new ThriftSerializer(client);
        ThriftDeserializer deserializer = new ThriftDeserializer(client);

        ThriftMsgV1 msgV1 = new ThriftMsgV1("f1");
        s1.serialize("test", msgV1);

        ThriftMsgV5 msgV5 = new ThriftMsgV5("f1");
        byte[] result = s2.serialize("test", msgV5);

        Object d = deserializer.deserialize(result);

        assertEquals(d.getClass(), ThriftMsgV5.class);
        assertEquals(msgV5, d);
    }
}

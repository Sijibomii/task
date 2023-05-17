package com.task.server.serializers;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UUIDSerializer implements RedisSerializer<UUID>{
    @Override
    public byte[] serialize(UUID uuid) throws SerializationException {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.putLong(uuid.getMostSignificantBits());
        buffer.putLong(uuid.getLeastSignificantBits());
        return buffer.array();
    }

    @Override
    public UUID deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null) {
            return null;
        }
        if (bytes.length != 16) {
            throw new SerializationException("Invalid UUID byte array length");
        }
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        long mostSigBits = buffer.getLong();
        long leastSigBits = buffer.getLong();
        return new UUID(mostSigBits, leastSigBits);
    }
}

package com.fsd.core.services.libraryservice.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsd.core.services.libraryservice.models.AuditEntity;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class AuditSerializer implements Serializer<AuditEntity> {

    @Override
    public void configure(Map<String, ?> map, boolean b) {
    }

    @Override
    public void close() {
    }

    @Override
    public byte[] serialize(String arg0, AuditEntity arg1) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(arg1).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }
}

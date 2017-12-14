package com.fsd.core.services.libraryservice.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsd.core.services.libraryservice.models.AuditEntity;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class AuditDeserializer implements Deserializer<AuditEntity> {

    @Override public void close() {
    }
    @Override public void configure(Map<String, ?> arg0, boolean arg1) {
    }

    @Override
    public AuditEntity deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        AuditEntity auditEntity = null;
        try {
            auditEntity = mapper.readValue(arg1, AuditEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return auditEntity;
    }

}
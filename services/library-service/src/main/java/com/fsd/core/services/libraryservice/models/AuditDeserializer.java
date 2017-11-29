package com.fsd.core.services.libraryservice.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class AuditDeserializer implements Deserializer<Audit>{

    @Override public void close() {
    }
    @Override public void configure(Map<String, ?> arg0, boolean arg1) {
    }

    @Override
    public Audit deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        Audit audit = null;
        try {
            audit = mapper.readValue(arg1, Audit.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return audit;
    }

}

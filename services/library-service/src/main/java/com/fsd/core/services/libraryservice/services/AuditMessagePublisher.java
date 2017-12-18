package com.fsd.core.services.libraryservice.services;


import com.fsd.core.services.libraryservice.models.AuditEntity;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

@EnableBinding(Source.class)
public class AuditMessagePublisher {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private Source source;

    public boolean sendAuditInfo(AuditEntity auditEntity) {
        //send message to channel
        source.output().send(MessageBuilder.withPayload(auditEntity).build());
        return true;
    }
}
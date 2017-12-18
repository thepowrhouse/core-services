package com.fsd.core.services.libraryservice.services;

import com.fsd.core.services.libraryservice.models.AuditEntity;
import com.fsd.core.services.libraryservice.models.dto.AuditDTO;
import com.fsd.core.services.libraryservice.repository.AuditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import javax.annotation.Resource;

@EnableBinding(Sink.class)
public class AuditMessageConsumer {

    @Resource
    private AuditRepository auditRepository;

    public static void main(String[] args) {
        SpringApplication.run(AuditMessageConsumer.class, args);
    }

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @StreamListener(Sink.INPUT)
    public void process(AuditDTO obj) {
        logger.info("Done:.." + obj);
        AuditEntity entity = new AuditEntity();
        entity.setUpdatedAt(obj.getUpdatedAt());
        entity.setCreatedAt(obj.getCreatedAt());
        entity.setEvent(obj.getEvent());
        entity.setUserEntity(obj.getUserEntity());
        auditRepository.save(entity);
    }
}
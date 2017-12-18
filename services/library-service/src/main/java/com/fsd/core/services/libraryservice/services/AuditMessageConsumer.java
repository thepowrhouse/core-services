package com.fsd.core.services.libraryservice.services;

import com.fsd.core.services.libraryservice.models.AuditEntity;
import com.fsd.core.services.libraryservice.repository.AuditRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import javax.annotation.Resource;

@EnableBinding(Sink.class)
public class AuditMessageConsumer {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private AuditRepository repository;

    @StreamListener(target = Sink.INPUT)
    @HystrixCommand
    public void save(AuditEntity auditEntity) {
        System.out.print(auditEntity);
        repository.save(auditEntity);
    }
}

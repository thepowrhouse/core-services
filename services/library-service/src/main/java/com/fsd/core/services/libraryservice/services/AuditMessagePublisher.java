package com.fsd.core.services.libraryservice.services;


import com.fsd.core.services.libraryservice.models.dto.AuditDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

@EnableBinding(Source.class)
public class AuditMessagePublisher {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private Source source;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean sendAuditInfo(AuditDTO dto) {
        try {
            //send message to channel
            logger.info("Publisher:..." + dto);
            source.output().send(MessageBuilder.withPayload(dto).build());
            return true;
        } catch (Exception e) {
            logger.error("Publisher:...", e);
        }
        return false;
    }
}
package com.fsd.core.services.libraryservice.services;

import com.fsd.core.services.libraryservice.models.AuditEntity;
import com.fsd.core.services.libraryservice.repository.AuditRepository;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;

@Component
public class AuditServiceImpl implements AuditService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    KafkaTemplate<Integer,AuditEntity> auditKafkaTemplate;

    int numberOfDaysToExpire = 15;

    @Override
    public boolean sendAuditInfo(AuditEntity auditEntity) {
        try{
            SendResult<Integer, AuditEntity> sendResult = auditKafkaTemplate.sendDefault(auditEntity.getId(), auditEntity).get();
            RecordMetadata recordMetadata = sendResult.getRecordMetadata();
            logger.info("Producer - topic = {}, partition = {}, offset = {}, workUnit = {}" +
                    recordMetadata.topic() + recordMetadata.partition() + recordMetadata.offset() + auditEntity);
            return true;
        }catch(Exception e){
            logger.info("Failure");
            e.printStackTrace();
            return false;
        }

    }

    @Autowired
    private AuditRepository auditMongoRepository;


    @KafkaListener(topics = "bookStoreTopic")
    public void receiveAuditInfo(AuditEntity audit, @Header(KafkaHeaders.OFFSET) Integer offset,
                                 @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                                 @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        logger.info("Consumer - Processing topic = {}, partition = {}, offset = {}, workUnit = {}"+
                topic + partition + offset + audit);

        logger.info("Event ?"+ audit.getEvent());

       AuditEntity auditEntity = auditMongoRepository.save(audit);

        logger.info("Audit Entity Successfully pushed to Mongo ");

        for(AuditEntity auditVal : auditMongoRepository.findAll()){
            logger.info("Event Messages ?"+ audit.getEvent());
        }


    }


}

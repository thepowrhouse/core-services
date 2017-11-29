package com.fsd.core.services.libraryservice.services;

import com.fsd.core.services.libraryservice.models.Audit;
import org.apache.catalina.User;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.util.concurrent.CountDownLatch;

public class AuditKafkaConsumerDispatcher {

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch(){
        return latch;
    }

    public void receiveAuditInfo(Audit audit, @Header(KafkaHeaders.OFFSET) Integer offset,
                                 @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                                 @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        System.out.println("Processing topic = {}, partition = {}, offset = {}, workUnit = {}"+
                topic + partition + offset + audit);
        latch.countDown();
    }
}

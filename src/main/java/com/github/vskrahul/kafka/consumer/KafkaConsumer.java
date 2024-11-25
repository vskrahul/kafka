package com.github.vskrahul.kafka.consumer;


import com.github.vskrahul.kafka.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.github.vskrahul.kafka.constants.KafkaConstants.*;

@Service
@Slf4j
public class KafkaConsumer {

    //@KafkaListener(topics = TOPIC_1, groupId = CONSUMER_GROUP_2)
    public void consume_Consumer_1_Group1(
            @Payload String message,
            @Header(KafkaHeaders.OFFSET) Long offset,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            Acknowledgment acknowledgment
    ) {
        try {
            log.info("[offset={}] [partition={}] [message={}] [state=start]", offset, partition, message);
            try {
                Thread.sleep(1);
                acknowledgment.acknowledge();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            //executorService.submit(() -> processor.process(message, acknowledgment));
        } catch (Exception e) {
            //do nothing
        }
    }

    private boolean failOnce = true;

    @KafkaListener(topics = TOPIC_2, groupId = CONSUMER_GROUP_3, containerGroup = "container-group-3", concurrency = "3")
    @Transactional
    public void consume_Consumer_2_Group2(String message) {
        try {
            log.info("[consumer-group={}] [consumer=consumer-3] [message={}]", CONSUMER_GROUP_3, message);
            Thread.sleep(1000);
            if(failOnce) {
                failOnce = false;
                throw new RuntimeException("failed intentionally");
            }
        } catch (Exception e) {
            log.error("[value={}] [message={}]", message, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //@KafkaListener(topics = TOPIC_A, groupId = CONSUMER_GROUP_B)
    public void consumeUserModel(UserModel message) {
        try {
            log.info("[consumer-group={}] [consumer=consumeUserModel] [message={}]", CONSUMER_GROUP_1, message);
        } catch (Exception e) {
            // do nothing
        }
    }
}

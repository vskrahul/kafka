package com.github.vskrahul.kafka.producer;

import com.github.vskrahul.kafka.constants.KafkaConstants;
import com.github.vskrahul.kafka.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class KafkaProducer {
    private KafkaTemplate<String, String> kafkaTemplateTopic1;
    private KafkaTemplate<String, String> kafkaTemplateTopic2;
    //private KafkaTemplate<String, UserModel> userModelKafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplateTopic1
            ,KafkaTemplate<String, String> kafkaTemplateTopic2
            //,KafkaTemplate<String, UserModel> userModelKafkaTemplate
    ) {
        this.kafkaTemplateTopic1 = kafkaTemplateTopic1;
        this.kafkaTemplateTopic2 = kafkaTemplateTopic2;
        //this.userModelKafkaTemplate = userModelKafkaTemplate;
    }

    @Transactional
    public void publishTextMessage(String message) {
        try {
            log.info("[method=publishTextMessage] [message={}]", message);
            //this.kafkaTemplateTopic1.send(KafkaConstants.TOPIC_1, message);
            CompletableFuture<SendResult<String, String>> result = this.kafkaTemplateTopic2.send(KafkaConstants.TOPIC_2, message);
            result.whenComplete((a, b) -> {
                log.error("[callback=whenComplete] [value={}] [message={}]", a.getProducerRecord().value(), b.getMessage());
            });
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void publishJsonMessage(UserModel userModel) {
        try {
            log.info("[method=publishJsonMessage] [userModel={}]", userModel.toString());
            //this.userModelKafkaTemplate.send(KafkaConstants.TOPIC_A, userModel);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

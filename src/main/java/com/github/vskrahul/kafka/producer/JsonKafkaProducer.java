package com.github.vskrahul.kafka.producer;

import com.github.vskrahul.kafka.constants.KafkaConstants;
import com.github.vskrahul.kafka.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

//@Service
//@Slf4j
public class JsonKafkaProducer {
//    private KafkaTemplate<String, UserModel> kafkaTemplate;
//
//    @Autowired
//    public JsonKafkaProducer(KafkaTemplate<String, UserModel> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void sendMessage(UserModel userModel) {
//        Message<UserModel> message = MessageBuilder
//                .withPayload(userModel)
//                .setHeader(KafkaHeaders.TOPIC, KafkaConstants.TOPIC_1)
//                .build();
//        log.info("[method=sendJsonMessage] [message={}]", userModel);
//        this.kafkaTemplate.send(message);
//    }
}

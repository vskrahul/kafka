package com.github.vskrahul.kafka.controller;

import com.github.vskrahul.kafka.model.MessageModel;
import com.github.vskrahul.kafka.model.UserModel;
import com.github.vskrahul.kafka.producer.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("kafka")
@Slf4j
public class KafkaController {
    private KafkaProducer kafkaProducer;
    //private JsonKafkaProducer jsonKafkaProducer;

    @Autowired
    public KafkaController(
            KafkaProducer kafkaProducer
            //,JsonKafkaProducer jsonKafkaProducer
    ) {
        this.kafkaProducer = kafkaProducer;
        //this.jsonKafkaProducer = jsonKafkaProducer;
    }

    @PostMapping(value = {"publish-text", "publish/{something}"})
    public void publicTextMessage(
            @RequestBody MessageModel message,
            @RequestParam(required = false) String name,
            @PathVariable(required = false) String something
            ) {
        log.info("[name={}] [something={}]", name, something);

        Stream.iterate(1, v -> v + 1)
                .limit(5)
                .forEach(v -> {
                    this.kafkaProducer.publishTextMessage(message.getMessage() + "-" + v);
                });
    }

    @PostMapping(value = "publish-json", consumes = "application/json", produces = "application/text")
    public ResponseEntity<String> publishJsonMessage(@RequestBody UserModel userModel) {
        //this.jsonKafkaProducer.sendMessage(userModel);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("ping")
    public String ping() {
        return "success";
    }
}

package com.github.vskrahul.kafka.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@Data
@NoArgsConstructor
public class MessageModel implements Serializable {
    String message;
    public MessageModel(String message) {
        this.message = message;
    }
}

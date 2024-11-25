package com.github.vskrahul.kafka.interceptor;

import com.github.vskrahul.kafka.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

@Slf4j
public class KafkaProducerInterceptor implements ProducerInterceptor<String, String> {

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        record.headers().forEach(header -> {
            log.info("[method=parseProducerHeader] [key={}] [value={}]", header.key(), header.value());
        });
        log.info("[method=onSend] [key={}] [value={}] [topic={}] [partition={}]", record.key(), record.value(), record.topic(), record.partition());
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        log.info("[method=onAcknowledgement] [metadata={}]", JsonUtil.toJsonString(metadata));
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}

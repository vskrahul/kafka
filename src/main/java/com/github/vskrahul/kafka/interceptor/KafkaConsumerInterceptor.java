package com.github.vskrahul.kafka.interceptor;

import com.github.vskrahul.kafka.constants.KafkaConstants;
import com.github.vskrahul.kafka.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.Map;

@Slf4j
public class KafkaConsumerInterceptor implements ConsumerInterceptor<String, String> {

    @Override
    public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> records) {
        records.forEach(v -> {
            v.headers().forEach(header -> {
                log.info("[method=parseConsumerHeader] [key={}] [value={}]", header.key(), header.value());
            });
            log.info("[method=onConsume] [key={}] [partition={}] [offset={}] [topic={}] [value={}]", v.key(), v.partition(), v.offset(), v.topic(), v.value());
        });
        return records;
    }

    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {
        log.info("[method=onCommit] [offsets={}]", JsonUtil.toJsonString(offsets));
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}

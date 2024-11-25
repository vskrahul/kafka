package com.github.vskrahul.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

public class MyRecordFilterStrategy implements RecordFilterStrategy<String, String> {

    @Override
    public boolean filter(ConsumerRecord<String, String> consumerRecord) {
        return false;
    }

    @Override
    public List<ConsumerRecord<String, String>> filterBatch(List<ConsumerRecord<String, String>> consumerRecords) {
        return RecordFilterStrategy.super.filterBatch(consumerRecords);
    }

    @Override
    public boolean ignoreEmptyBatch() {
        return RecordFilterStrategy.super.ignoreEmptyBatch();
    }
}

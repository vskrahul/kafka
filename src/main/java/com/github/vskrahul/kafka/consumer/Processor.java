package com.github.vskrahul.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Processor {

    private boolean failOnce = true;

    public void process(String message, Acknowledgment acknowledgment) {
        log.info("[method=process] [state=start] [message={}]", message);
        try {
            Thread.sleep(5000);
            if(failOnce) {
                failOnce = false;
                throw new Exception("Failed to process the data, I hope consumer will re-process it.");
            }
        } catch (Exception e) {
            log.error("[method=process] [state=error] [message={}]", message);
            //acknowledgment.acknowledge();
            throw new RuntimeException(e);
        }
        acknowledgment.acknowledge();
        log.info("[method=process] [state=end] [message={}]", message);
    }
}

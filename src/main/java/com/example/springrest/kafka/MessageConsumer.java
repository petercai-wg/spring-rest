package com.example.springrest.kafka;

import com.example.springrest.config.AsyncTaskConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.example.springrest.dto.CustomMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MessageConsumer {

    @KafkaListener(topics = "my-new-topic", groupId = "my-debug-group", concurrency = "3")
    public void listen(CustomMessage message) {
        log.info("Received message: {} - {}", message.title(), message.content());
        if (message.content().contains("key")) {
            // throw new RuntimeException("Simulated listen failure for message: " +
            // message.content());
            throw new IllegalArgumentException("Invalid message content for: " + message.content());
        }

    }

    // @KafkaListener(topics = "my-new-topic", groupId = "my-debug-group")
    // public void onMessage(ConsumerRecord<String, CustomMessage> crecord,
    // Acknowledgment acknowledgment) {
    // String uniqueId = crecord.topic() + "-" + crecord.partition() + "-" +
    // crecord.offset();

    // log.info("Processing message: {}, {} - {}", uniqueId, crecord.key(),
    // crecord.value());

    // if (crecord.key() == null) {
    // throw new RuntimeException("Simulated processing failure for message: " +
    // uniqueId);
    // }

    // acknowledgment.acknowledge();

    // }
}
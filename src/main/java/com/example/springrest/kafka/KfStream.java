package com.example.springrest.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

// https://github.com/Java-Techie-jt/fraud-detection-system/tree/main
// https://github.com/Java-Techie-jt/kafka-ordering/blob/main/src/main/java/com/javatechie/publisher/OrderProducer.java

record Transaction(
        String transactionId,
        String userId,
        double amount,
        String timestamp) {
}

// @Configuration
// @EnableKafkaStreams
@Slf4j
public class KfStream {
    @Bean
    public KStream<String, String> fraudDetectStream(StreamsBuilder builder) {

        // Step 1: Read messages from the input topic.
        KStream<String, String> transactionsStream = builder.stream("transactions");

        // Step 2: Process the stream
        KStream<String, String> filteredTransactionStream = transactionsStream
                .filter((key, value) -> isPredicated(value))
                .peek((key, value) -> {
                    log.info("Peek - transactionId={} , value={}", key, value);
                });

        // Step 3: write to an output topic.
        filteredTransactionStream.to("Txn-alerts");

        return transactionsStream;

    }

    private boolean isPredicated(String value) {
        try {
            Transaction transaction = new ObjectMapper()
                    .readValue(value, Transaction.class); // validate JSON
            return transaction.amount() > 10000; // simple fraud rule
        } catch (Exception e) {
            return false;
        }
    }
}

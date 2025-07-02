package com.example.demo.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerKafka {
	
    public static void main(String[] args) {
        // Configurazione consumer
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // broker Kafka
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "prova-consumer-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // leggi dall'inizio

        // Creazione consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // Iscrizione al topic
        consumer.subscribe(Collections.singletonList("prova-topic"));

        // Lettura messaggi
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("Ricevuto messaggio: key = %s, value = %s, offset = %d%n",
                        record.key(), record.value(), record.offset());
            }
        }
    }
}

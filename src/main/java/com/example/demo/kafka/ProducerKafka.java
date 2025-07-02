package com.example.demo.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;

public class ProducerKafka {
	
    public static void main(String[] args) {
        // Configurazione producer
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // broker Kafka
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Creazione producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        // Creazione del messaggio
        String topic = "prova-topic";
        String key = "id-utente-1";
        String value = "Messaggio di prova da Java";

        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);

        // Inviare il messaggio
        producer.send(record);

        // Chiusura producer
        producer.close();

        System.out.println("Messaggio inviato con successo!");
    }
}

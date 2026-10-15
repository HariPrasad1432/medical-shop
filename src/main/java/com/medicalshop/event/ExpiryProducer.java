package com.medicalshop.event;
import lombok.RequiredArgsConstructor; import org.springframework.kafka.core.KafkaTemplate; import org.springframework.stereotype.Service;
@Service @RequiredArgsConstructor public class ExpiryProducer{private final KafkaTemplate<String,ExpiryEvent> kafka;public void publish(ExpiryEvent e){kafka.send("medicine-expiry-alerts",String.valueOf(e.batchId()),e);}}

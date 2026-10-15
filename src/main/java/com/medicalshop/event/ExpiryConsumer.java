package com.medicalshop.event;
import org.springframework.kafka.annotation.KafkaListener; import org.springframework.stereotype.Service;
@Service public class ExpiryConsumer{@KafkaListener(topics="medicine-expiry-alerts",groupId="medical-shop-notifications")public void consume(ExpiryEvent e){System.out.println("EXPIRY ALERT: "+e.medicine()+" / "+e.batchNumber()+" / "+e.expiryDate());}}

package com.medicalshop.service;
import com.medicalshop.event.*;
import com.medicalshop.repository.BatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ExpiryScheduler {

    private final BatchRepository r;
    private final ExpiryProducer p;

    @Scheduled(cron = "0 0 8 * * *")
    public void check() {
        LocalDate expiryLimit = LocalDate.now().plusDays(30);

        r.expiring(expiryLimit).forEach(b ->
            p.publish(new ExpiryEvent(
                b.getId(),
                b.getMedicine().getName(),
                b.getBatchNumber(),
                b.getExpiryDate().toString()
            ))
        );
    }
}
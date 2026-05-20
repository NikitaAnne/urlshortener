package com.nikita.urlshortener.scheduler;

import com.nikita.urlshortener.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class UrlCleanupScheduler {

    private final UrlRepository urlRepository;

    @Transactional
    @Scheduled(cron = "0 0 * * * *")
    public void deleteExpiredUrls() {

        log.info("Running expired URL cleanup job");

        urlRepository.deleteByExpiresAtBefore(LocalDateTime.now());
    }

}

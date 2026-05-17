package com.nikita.urlshortener.impl;

import com.nikita.urlshortener.dto.UrlRequestDto;
import com.nikita.urlshortener.dto.UrlResponseDto;
import com.nikita.urlshortener.entity.UrlMapping;
import com.nikita.urlshortener.exception.ExpiredUrlException;
import com.nikita.urlshortener.exception.ResourceNotFoundException;
import com.nikita.urlshortener.repository.UrlRepository;
import com.nikita.urlshortener.service.UrlService;
import com.nikita.urlshortener.util.ShortUrlGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;

    private final StringRedisTemplate redisTemplate;

    @Override
    public UrlResponseDto shortenUrl(UrlRequestDto requestDto) {

        String shortCode = ShortUrlGenerator.shortCodeGenerator();

        UrlMapping urlMapping = UrlMapping.builder()
                .originalUrl(requestDto.getOriginalUrl())
                .shortCode(shortCode)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusDays(7))
                .clickCount(0L)
                .build();

        urlRepository.save(urlMapping);

        String shortUrl = "http://localhost:8080/api/v1/url/" + shortCode;

        return new UrlResponseDto(shortUrl);

    }

    @Override
    public String getOriginalUrl(String shortCode) {

        String cachedUrl = redisTemplate.opsForValue().get(shortCode);

        if (cachedUrl != null) {

            UrlMapping cachedMapping = urlRepository
                    .findByShortCode(shortCode)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Short URL not found"));

            cachedMapping.setClickCount(
                    cachedMapping.getClickCount() + 1);

            urlRepository.save(cachedMapping);

            return cachedUrl;
        }

        UrlMapping urlMapping = urlRepository
                .findByShortCode(shortCode)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Short URL not found"));
        if (urlMapping.getExpiresAt() != null &&
                urlMapping.getExpiresAt().isBefore(LocalDateTime.now())) {

            throw new ExpiredUrlException("Short URL has expired");
        }

        redisTemplate.opsForValue()
                .set(shortCode, urlMapping.getOriginalUrl());

        urlMapping.setClickCount(
                urlMapping.getClickCount() + 1);

        urlRepository.save(urlMapping);

        return urlMapping.getOriginalUrl();
    }

}

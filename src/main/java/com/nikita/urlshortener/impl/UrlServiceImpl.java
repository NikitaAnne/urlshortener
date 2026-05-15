package com.nikita.urlshortener.impl;

import com.nikita.urlshortener.dto.UrlRequestDto;
import com.nikita.urlshortener.dto.UrlResponseDto;
import com.nikita.urlshortener.entity.UrlMapping;
import com.nikita.urlshortener.repository.UrlRepository;
import com.nikita.urlshortener.service.UrlService;
import com.nikita.urlshortener.util.ShortUrlGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;

    @Override
    public UrlResponseDto shortenUrl(UrlRequestDto requestDto){

        String shortCode = ShortUrlGenerator.shortCodeGenerator();

        UrlMapping urlMapping = UrlMapping.builder()
                .originalUrl(requestDto.getOriginalUrl())
                .shortCode(shortCode)
                .createdAt(LocalDateTime.now())
                .clickCount(0L)
                .build();

        urlRepository.save(urlMapping);

        String shortUrl = "http://localhost:8080/" + shortCode;

        return new UrlResponseDto(shortUrl);

    }
}

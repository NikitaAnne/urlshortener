package com.nikita.urlshortener.controller;

import com.nikita.urlshortener.dto.UrlRequestDto;
import com.nikita.urlshortener.dto.UrlResponseDto;
import com.nikita.urlshortener.service.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/url")
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @PostMapping("/shorten")
    public UrlResponseDto shortenUrl(
            @Valid @RequestBody UrlRequestDto requestDto) {

        return urlService.shortenUrl(requestDto);
    }
}

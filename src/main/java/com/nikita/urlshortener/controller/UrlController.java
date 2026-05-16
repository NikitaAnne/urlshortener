package com.nikita.urlshortener.controller;

import com.nikita.urlshortener.dto.UrlRequestDto;
import com.nikita.urlshortener.dto.UrlResponseDto;
import com.nikita.urlshortener.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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

    @GetMapping("/{shortCode}")
    public void redirectToOriginalUrl(
            @PathVariable String shortCode,
            HttpServletResponse response) throws IOException {

        String originalUrl = urlService.getOriginalUrl(shortCode);

        response.sendRedirect(originalUrl);
    }
}

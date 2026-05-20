package com.nikita.urlshortener.controller;

import com.nikita.urlshortener.dto.UrlRequestDto;
import com.nikita.urlshortener.dto.UrlResponseDto;
import com.nikita.urlshortener.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "URL Shortener API",
        description = "APIs for shortening and redirecting URLs")
@RestController
@RequestMapping("/api/v1/url")
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @Operation(summary = "Create short URL")
    @PostMapping("/shorten")
    public UrlResponseDto shortenUrl(
            @Valid @RequestBody UrlRequestDto requestDto) {

        return urlService.shortenUrl(requestDto);
    }

    @Operation(summary = "Redirect to original URL")
    @GetMapping("/{shortCode}")
    public void redirectToOriginalUrl(
            @PathVariable String shortCode,
            HttpServletResponse response) throws IOException {

        String originalUrl = urlService.getOriginalUrl(shortCode);

        response.sendRedirect(originalUrl);
    }
}

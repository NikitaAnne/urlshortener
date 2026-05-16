package com.nikita.urlshortener.service;

import com.nikita.urlshortener.dto.UrlRequestDto;
import com.nikita.urlshortener.dto.UrlResponseDto;

public interface UrlService {
    UrlResponseDto shortenUrl(UrlRequestDto urlRequestDto);
    String getOriginalUrl(String shortCode);
}

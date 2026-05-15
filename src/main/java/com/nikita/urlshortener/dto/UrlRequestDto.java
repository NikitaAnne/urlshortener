package com.nikita.urlshortener.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlRequestDto {
    @NotBlank(message = "URL cannot be empty")
    private String originalUrl;
}

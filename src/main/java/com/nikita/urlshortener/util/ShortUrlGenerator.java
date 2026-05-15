package com.nikita.urlshortener.util;

import java.util.UUID;

public class ShortUrlGenerator {
    public static String shortCodeGenerator() {
        return UUID.randomUUID()
                .toString()
                .substring(0, 6);
    }
}

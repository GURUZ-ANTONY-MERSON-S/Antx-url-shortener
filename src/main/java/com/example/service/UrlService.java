package com.example.service;

import com.example.entity.UrlMapping;
import com.example.exception.ShortUrlNotFoundException;
import com.example.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    private static final String CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private final Random random = new Random();

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public UrlMapping shortenUrl(String originalUrl) {

        if (originalUrl == null || originalUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("Original URL cannot be empty");
        }

        String shortCode;

        do {
            shortCode = generateShortCode();
        } while (urlRepository.existsByShortCode(shortCode));

        UrlMapping urlMapping = new UrlMapping();

        urlMapping.setOriginalUrl(originalUrl.trim());
        urlMapping.setShortCode(shortCode);

        return urlRepository.save(urlMapping);
    }

    public String getOriginalUrl(String shortCode) {

        UrlMapping urlMapping = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() ->
                        new ShortUrlNotFoundException("Short URL not found"));

        urlMapping.setClickCount(urlMapping.getClickCount() + 1);

        urlRepository.save(urlMapping);

        return urlMapping.getOriginalUrl();
    }

    private String generateShortCode() {

        StringBuilder code = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }

        return code.toString();
    }
}
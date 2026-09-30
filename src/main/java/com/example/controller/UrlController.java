package com.example.controller;

import com.example.dto.UrlRequest;
import com.example.entity.UrlMapping;
import com.example.service.UrlService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<UrlMapping> shortenUrl(@RequestBody UrlRequest request) {

        if (request == null ||
            request.getOriginalUrl() == null ||
            request.getOriginalUrl().trim().isEmpty()) {

            return ResponseEntity.badRequest().build();
        }

        UrlMapping result =
                urlService.shortenUrl(request.getOriginalUrl().trim());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {

        String originalUrl = urlService.getOriginalUrl(shortCode);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(java.net.URI.create(originalUrl));

        return ResponseEntity
                .status(302)
                .headers(headers)
                .build();
    }
}
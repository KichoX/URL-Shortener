package com.URLshortener.URL.api;

import com.URLshortener.URL.service.UrlShortenerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class UrlController {
    private final UrlShortenerService service;

    public UrlController(UrlShortenerService service) {
        this.service = service;
    }

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody String longUrl) throws IOException {
        String shortUrl = service.shortenUrl(longUrl);
        return ResponseEntity.ok(shortUrl);
    }

    @GetMapping("redirect/{shortCode}")
    public void redirectToUrl(@PathVariable String shortCode, HttpServletResponse response) throws IOException {
        service.getLongUrl(shortCode, response);
    }
}
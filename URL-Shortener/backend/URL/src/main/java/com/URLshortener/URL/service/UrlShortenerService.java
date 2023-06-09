package com.URLshortener.URL.service;


import com.URLshortener.URL.domain.Url;
import com.URLshortener.URL.repository.UrlRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.List;

@Service
public class UrlShortenerService {
    private final UrlRepository repository;

    public UrlShortenerService(UrlRepository repository) {
        this.repository = repository;
    }

    public String shortenUrl(String longUrl) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Url url = objectMapper.readValue(longUrl, Url.class);
        Url existingUrl = repository.searchByLongUrl(url.getLongUrl());

        return existingUrl != null ? existingUrl.toJsonString() : saveAndHash(url.getLongUrl());
    }

    private String saveAndHash(String longUrl) {
        String hashed = hash(longUrl);
        Url url = repository.searchByShortUrl(hashed);
        if (url == null) {
            url = new Url(longUrl, hashed);
            repository.save(url);
        }
        repository.save(url);
        return url.toJsonString();
    }

    private String hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.substring(0, 6);
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    public void redirectToLongUrl(String shortUrl, HttpServletResponse response) throws IOException {
        Url url = repository.searchByShortUrl(shortUrl);

        if (validateUrl(shortUrl)) {
            response.sendRedirect(url.getLongUrl());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public String getLongUrl(String shortUrl) {
        Url url = repository.searchByShortUrl(shortUrl);

        if (validateUrl(shortUrl)) {
            return url.toJsonString();
        } else {
            return "Error: URL is null";
        }
    }

    private boolean validateUrl(String shortUrl) {
        Url url = repository.searchByShortUrl(shortUrl);

        if (url != null) {
            url.setClicks(url.getClicks() + 1);
            repository.save(url);
            return true;
        }

        return false;
    }

    public List<String> getAll() {
        return repository.findAll().stream()
                .sorted(Comparator.reverseOrder())
                .map(Url::toJsonString)
                .toList();
    }

    public ResponseEntity<String> delete(String shortCode) {
        Url url = repository.searchByShortUrl(shortCode);
        if (url != null) {
            repository.delete(url);
            return ResponseEntity.ok("OK");
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<String> update(String oldCode, String newCode) {
        Url url = repository.searchByShortUrl(oldCode);
        if (url != null) {
            repository.delete(url);
            url.setShortUrl(newCode);
            repository.save(url);
            return ResponseEntity.ok("OK");
        }

        return ResponseEntity.notFound().build();
    }
}
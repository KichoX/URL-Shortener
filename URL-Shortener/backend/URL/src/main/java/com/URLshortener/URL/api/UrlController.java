package com.URLshortener.URL.api;

import com.URLshortener.URL.service.UrlShortenerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*")
public class UrlController {
    private final UrlShortenerService service;

    public UrlController(UrlShortenerService service) {
        this.service = service;
    }

    @PostMapping("/shorten")
    public String shortenUrl(@RequestBody String longUrl) throws IOException {
        return service.shortenUrl(longUrl);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("redirect/{shortCode}")
    public void redirectToUrl(@PathVariable String shortCode, HttpServletResponse response) throws IOException {
        service.redirectToLongUrl(shortCode, response);
    }

    @GetMapping("get/{shortCode}")
    public String getLongUrl(@PathVariable String shortCode) {
        return service.getLongUrl(shortCode);
    }
}
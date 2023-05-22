package com.URLshortener.URL.domain;


import jakarta.persistence.*;

@Entity
@Table(name = "urls")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "long_url")
    private String longUrl;

    @Column(name = "short_url")
    private String shortUrl;

    @Column(name = "clicks")
    private int clicks;

    public Url() {
    }


    public Url(Long id, String longUrl, String shortUrl) {
        this.id = id;
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
    }

    public Url(String longUrl, String shortUrl) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.clicks = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) { this.shortUrl = shortUrl; }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }
}
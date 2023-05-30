package com.URLshortener.URL.repository;

import com.URLshortener.URL.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Url searchByShortUrl(String code);
    Url searchByLongUrl(String url);
}


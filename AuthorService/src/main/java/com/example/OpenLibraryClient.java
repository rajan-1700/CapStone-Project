package com.example;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
@FeignClient(
        name = "openlibrary-client",
        url = "${openlibrary.api.base-url}",
        configuration = FeignSSLConfig.class
)
public interface OpenLibraryClient {

    @GetMapping("/search/authors.json")
    Map<String, Object> searchAuthors(@RequestParam("q") String query);
}
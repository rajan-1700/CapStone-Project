package com.example;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthorService {

    private final OpenLibraryClient openLibraryClient;

    public AuthorService(OpenLibraryClient openLibraryClient) {
        this.openLibraryClient = openLibraryClient;
    }

    public Map<String, Object> searchAuthors(String name) {
        return openLibraryClient.searchAuthors(name);
    }
}

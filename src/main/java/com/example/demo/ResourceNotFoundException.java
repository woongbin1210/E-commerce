package com.example.demo;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String messages) {
        super(messages);
    }
}

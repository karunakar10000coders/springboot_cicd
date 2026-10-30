package com.example.demo.service;

import com.example.demo.model.Greeting;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class GreetingService {

    private final AtomicLong counter = new AtomicLong();

    public Greeting getGreeting(String name) {
        String message = String.format("Hello, %s! Welcome to the CI/CD Demo.", name);
        return new Greeting(counter.incrementAndGet(), message);
    }

    public Greeting getDefaultGreeting() {
        return new Greeting(counter.incrementAndGet(), "Hello, World! CI/CD Pipeline is working!");
    }
}

package com.example.demo;

import com.example.demo.model.Greeting;
import com.example.demo.service.GreetingService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreetingServiceTest {

    private final GreetingService greetingService = new GreetingService();

    @Test
    void shouldReturnDefaultGreeting() {
        Greeting greeting = greetingService.getDefaultGreeting();
        assertNotNull(greeting);
        assertEquals("Hello, World! CI/CD Pipeline is working!", greeting.getMessage());
        assertTrue(greeting.getId() > 0);
    }

    @Test
    void shouldReturnPersonalisedGreeting() {
        Greeting greeting = greetingService.getGreeting("Alice");
        assertNotNull(greeting);
        assertEquals("Hello, Alice! Welcome to the CI/CD Demo.", greeting.getMessage());
    }

    @Test
    void shouldIncrementId() {
        Greeting g1 = greetingService.getDefaultGreeting();
        Greeting g2 = greetingService.getDefaultGreeting();
        assertEquals(g1.getId() + 1, g2.getId());
    }
}

package com.example.demo.controller;

import com.example.demo.model.Greeting;
import com.example.demo.service.GreetingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    /**
     * GET /api/greeting
     * Returns a default greeting.
     */
    @GetMapping("/greeting")
    public Greeting getDefaultGreeting() {
        return greetingService.getDefaultGreeting();
    }

    /**
     * GET /api/greeting/{name}
     * Returns a personalised greeting.
     */
    @GetMapping("/greeting/{name}")
    public Greeting getGreeting(@PathVariable String name) {
        return greetingService.getGreeting(name);
    }

    /**
     * GET /api/status
     * Simple status endpoint for Jenkins post-deploy smoke test.
     */
    @GetMapping("/status")
    public String status() {
        return "UP";
    }
}

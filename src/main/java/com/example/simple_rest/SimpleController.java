package com.example.simple_rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class SimpleController {

    // declare a record named User
    record User(String username, String password) {}

    @GetMapping("/greeting")
    public String getGreeting() {
        return "¡Hello, World!";
    }

    @GetMapping("/delay")
    public String getDelayedResponse() {
        // Simulate delay of 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "¡Delayed hello, World!";
    }

    // Post handler, receives a String and returns it
    @PostMapping("/echo")
    public String echo(String message) {
        return message;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login() {
        User user = new User("admin", "admin");

        return ResponseEntity.ok(user);
    }
}

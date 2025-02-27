package com.example.simple_rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class SimpleController {

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(SimpleController.class);

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
    public String echo(@RequestBody String message) {
        return message;
    }

    // Post handler, receives an alert from Zabbix and logs it
    @PostMapping("/alert")
    public String alert(@RequestBody String message) {
        logger.info(message);
        return message;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login() {
        User user = new User("admin", "admin");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping("/greeting2")
    public String getGreeting2() {
        System.out.println("getGreeting2");
        // we call an external api
        String url = "http://localhost:8080/api/greeting";

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("username", "password"); // Replace with actual credentials

        // Create entity
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Make the request
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();

    }
}

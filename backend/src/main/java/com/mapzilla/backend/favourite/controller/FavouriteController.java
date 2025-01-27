package com.mapzilla.backend.favourite.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/favourite")
public class FavouriteController {

    @GetMapping
    public String hello() {
        return "Hello from Spring Boot & Keycloak";
    }

    @GetMapping("/hello-2")
    public String hello2() {
        return "Hello from Spring Boot & Keycloak - ADMIN";
    }
}

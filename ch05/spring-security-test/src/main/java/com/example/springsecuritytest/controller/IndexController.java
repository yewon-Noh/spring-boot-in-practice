package com.example.springsecuritytest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "index page";
    }

    @GetMapping("/admin")
    public String admin() { return "admin page"; }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }
}

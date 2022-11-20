package ru.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibraryController {

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/info")
    public String info() {
        return "info";
    }
}
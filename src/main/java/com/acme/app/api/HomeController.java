package com.acme.app.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "API Backend Practice corriendo correctamente 🚀. Visita /swagger-ui.html";
    }
}

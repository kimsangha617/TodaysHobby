package org.example.haas.todayshobby.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping("heartbeat")
    public String index() {
        return "heart beat...";
    }
}

package org.example.haas.todayshobby.controller;

import lombok.RequiredArgsConstructor;
import org.example.haas.todayshobby.domain.Item;
import org.example.haas.todayshobby.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("get")
    public String getItem() {
        System.out.println("xxxx");
        Item item = itemService.getOne();
        return "get success : " + item.getItemName();
    }

    @GetMapping("add")
    public String addItem() {
        System.out.println("vvvv");
        Item item = itemService.addItem();
        return "add success : " + item.getItemName();
    }
}

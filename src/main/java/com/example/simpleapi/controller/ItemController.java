package com.example.simpleapi.controller;

import com.example.simpleapi.dto.ItemDto;
import com.example.simpleapi.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
public class ItemController {

    private final ItemService itemService;

    @Inject
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public List<ItemDto> getItems(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return itemService.getItems();
    }
}

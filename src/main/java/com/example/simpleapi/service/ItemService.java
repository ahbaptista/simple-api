package com.example.simpleapi.service;

import com.example.simpleapi.dto.ItemDto;
import com.example.simpleapi.repository.ItemRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Inject
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemDto> getItems() {
        return StreamSupport.stream(itemRepository.findAll().spliterator(), false)
                .map(item -> new ItemDto.ItemDtoBuilder()
                        .setId(item.getId())
                        .setName(item.getName())
                        .setDescription(item.getDescription())
                        .setPrice(item.getPrice())
                        .build())
                .collect(Collectors.toList());
    }
}

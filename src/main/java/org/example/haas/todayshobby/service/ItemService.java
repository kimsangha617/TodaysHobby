package org.example.haas.todayshobby.service;

import lombok.RequiredArgsConstructor;
import org.example.haas.todayshobby.domain.Item;
import org.example.haas.todayshobby.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;


    @Transactional
    public Item addItem() {
        Item item = Item.builder()
                .itemName("ggobookchip")
                .build();
        itemRepository.save(item);
        return item;
    }

    public Item getOne() {
        return itemRepository.findById(1L).get();
    }
}

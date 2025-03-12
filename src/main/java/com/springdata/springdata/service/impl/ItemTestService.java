package com.springdata.springdata.service.impl;

import com.springdata.springdata.entity.Item;
import com.springdata.springdata.repository.ItemRepository;
import com.springdata.springdata.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("test")
public class ItemTestService implements ItemService {

    @Autowired
    private ItemRepository itemRepository;


    @Override
    public List<Item> findAllItems() {
        return itemRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public Item getItemById(int id) {
        return null;
    }

    @Override
    public void addItem(Item item) {

    }

    @Override
    public void updateItem(Item item) {

    }

    @Override
    public void deleteItem(int id) {

    }

    @Override
    public List<Item> getItemsFromWord(String str) {
        return List.of();
    }

    @Override
    public Item findItemByCostAndAndDescription(int cost, String description) {
        return null;
    }
}

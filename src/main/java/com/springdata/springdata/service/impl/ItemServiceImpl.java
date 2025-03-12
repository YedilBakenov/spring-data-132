package com.springdata.springdata.service.impl;

import com.springdata.springdata.entity.Item;
import com.springdata.springdata.repository.ItemRepository;
import com.springdata.springdata.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("primary")
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;


    @Override
    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItemById(int id) {
        return itemRepository.findById(id).get();
    }

    @Override
    public void addItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void updateItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void deleteItem(int id) {
        itemRepository.deleteById(id);
    }

    @Override
    public List<Item> getItemsFromWord(String str) {
        return itemRepository.getItemsFromWord(str);
    }

    @Override
    public Item findItemByCostAndAndDescription(int cost, String description) {
        return itemRepository.findItemByCostAndAndDescription(cost,description);
    }
}

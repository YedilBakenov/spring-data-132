package com.springdata.springdata.service;

import com.springdata.springdata.entity.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {

    List<Item> findAllItems();

    Item getItemById(int id);

    void addItem(Item item);

    void updateItem(Item item);

    void deleteItem(int id);

    List<Item> getItemsFromWord(String str);

    Item findItemByCostAndAndDescription(int cost, String description);

}

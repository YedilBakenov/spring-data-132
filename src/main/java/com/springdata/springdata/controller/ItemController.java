package com.springdata.springdata.controller;

import com.springdata.springdata.entity.Item;
import com.springdata.springdata.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping()
    public String getAllItems(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        return "index";
    }

    @PostMapping(value = "/add-item")
    public String addItem(Item item) {
        itemRepository.save(item);
        return "redirect:/api/items";
    }

    @GetMapping(value = "/add-item")
    public String addItem() {
        return "add-item";
    }

    @GetMapping(value = "/item-details/{id}")
    public String getItemById(@PathVariable int id,
                              Model model) {
        model.addAttribute("item", itemRepository.findById(id).get());
        return "item-details";
    }

    @PostMapping(value = "/item-update")
    public String updateItem(Item item) {
        itemRepository.save(item);
        return "redirect:/api/items";
    }

    @PostMapping(value = "/item-delete")
    public String deleteItem(@RequestParam int id) {
        itemRepository.deleteById(id);
        return "redirect:/api/items";
    }

    @GetMapping(value = "/item-cost-desc")
    public String getItem(@RequestParam int cost,
                          @RequestParam String description,
                          Model model) {
        model.addAttribute("item", itemRepository.findItemByCostAndAndDescription(cost, description));

        return "params";
    }

}

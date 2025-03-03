package com.springdata.springdata.controller;

import com.springdata.springdata.entity.City;
import com.springdata.springdata.entity.Item;
import com.springdata.springdata.repository.CityRepository;
import com.springdata.springdata.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final CityRepository cityRepository;

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
        List<City> cities = cityRepository.findAll();
        cities.removeAll(itemRepository.findById(id).get().getCities());
        model.addAttribute("cities", cities);
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

    @PostMapping(value = "/add-city")
    public String addCity(@RequestParam int item_id,
                          @RequestParam int city_id) {

        Item item = itemRepository.findById(item_id).get();
        City city = cityRepository.findById(city_id).get();

        item.getCities().add(city);
        itemRepository.save(item);

        return "redirect:/api/items/item-details/" + item_id;
    }

    @PostMapping(value = "/remove-city")
    public String removeCity(@RequestParam int item_id,
                            @RequestParam int city_id) {

        Item item = itemRepository.findById(item_id).get();
        City city = cityRepository.findById(city_id).get();

        item.getCities().remove(city);
        itemRepository.save(item);

        return "redirect:/api/items/item-details/" + item_id;
    }


}

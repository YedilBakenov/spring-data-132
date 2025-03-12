package com.springdata.springdata.controller;

import com.springdata.springdata.entity.City;
import com.springdata.springdata.entity.Item;
import com.springdata.springdata.entity2.User;
import com.springdata.springdata.repository.CityRepository;
import com.springdata.springdata.repository2.UserRepository;
import com.springdata.springdata.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    @Autowired
    @Qualifier("primary")
    private ItemService itemService;

    private final CityRepository cityRepository;

    private final UserRepository userRepository;

    @GetMapping()
    public String getAllItems(Model model) {
        model.addAttribute("items", itemService.findAllItems());
        return "index";
    }

    @GetMapping(value = "/search")
    public String getAllItemsFromStr(@RequestParam String str,
                                     Model model) {
        model.addAttribute("items", itemService.getItemsFromWord(str));
        return "index";
    }

    @GetMapping(value = "/add-user")
    public String getAddPage() {
        return "add-user";
    }

    @PostMapping(value = "/add-user")
    public String getAddCar(User user) {
        userRepository.save(user);
        return "redirect:/api/items";
    }

    @PostMapping(value = "/add-item")
    public String addItem(Item item) {
        itemService.addItem(item);
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
        cities.removeAll(itemService.getItemById(id).getCities());
        model.addAttribute("cities", cities);
        model.addAttribute("item", itemService.getItemById(id));
        return "item-details";
    }

    @PostMapping(value = "/item-update")
    public String updateItem(Item item) {
        itemService.addItem(item);
        return "redirect:/api/items";
    }

    @PostMapping(value = "/item-delete")
    public String deleteItem(@RequestParam int id) {
        itemService.deleteItem(id);
        return "redirect:/api/items";
    }

    @GetMapping(value = "/item-cost-desc")
    public String getItem(@RequestParam int cost,
                          @RequestParam String description,
                          Model model) {
        model.addAttribute("item", itemService.findItemByCostAndAndDescription(cost, description));

        return "params";
    }

    @PostMapping(value = "/add-city")
    public String addCity(@RequestParam int item_id,
                          @RequestParam int city_id) {

        Item item = itemService.getItemById(item_id);
        City city = cityRepository.findById(city_id).get();

        item.getCities().add(city);
        itemService.addItem(item);

        return "redirect:/api/items/item-details/" + item_id;
    }

    @PostMapping(value = "/remove-city")
    public String removeCity(@RequestParam int item_id,
                            @RequestParam int city_id) {

        Item item = itemService.getItemById(item_id);
        City city = cityRepository.findById(city_id).get();

        item.getCities().remove(city);
        itemService.addItem(item);

        return "redirect:/api/items/item-details/" + item_id;
    }


}

package com.hcl.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.entity.MenuItem;
import com.hcl.entity.Restaurant;

@RestController
public class MenuRestController {
     @GetMapping("/menu")
     public List<MenuItem> getAllMenuItems(){
    	 Restaurant restaurant = new Restaurant();
    	 return restaurant.getMenuList();
     }
}

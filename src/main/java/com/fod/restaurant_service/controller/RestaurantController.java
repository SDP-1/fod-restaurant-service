package com.fod.restaurant_service.controller;

import com.fod.restaurant_service.entity.Restaurant;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
@Validated
public class RestaurantController {

    @GetMapping
    public ResponseEntity<String> testRestaurant() {
        return ResponseEntity.ok("Restaurant service work...");
    }

}

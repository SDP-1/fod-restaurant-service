package com.fod.restaurant_service.controller;

import com.fod.restaurant_service.dto.OrderResponseDTO;
import com.fod.restaurant_service.dto.RestaurantRequestDTO;
import com.fod.restaurant_service.dto.RestaurantResponseDTO;
import com.fod.restaurant_service.entity.Enum.CuisineType;
import com.fod.restaurant_service.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantResponseDTO> createRestaurant(@Valid @RequestBody RestaurantRequestDTO requestDTO) {
        RestaurantResponseDTO responseDTO = restaurantService.createRestaurant(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> getRestaurantById(@PathVariable String id) {
        Optional<RestaurantResponseDTO> restaurant = restaurantService.getRestaurantById(id);
        return restaurant.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping()
    public ResponseEntity<List<RestaurantResponseDTO>> getAllRestaurants() {
        List<RestaurantResponseDTO> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/cuisine/{cuisineType}")
    public ResponseEntity<List<RestaurantResponseDTO>> getRestaurantsByCuisine(@PathVariable CuisineType cuisineType) {
        List<RestaurantResponseDTO> restaurants = restaurantService.getRestaurantsByCuisine(cuisineType);
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/search")
    public ResponseEntity<List<RestaurantResponseDTO>> searchRestaurantsByName(@RequestParam String name) {
        List<RestaurantResponseDTO> restaurants = restaurantService.searchRestaurantsByName(name);
        return ResponseEntity.ok(restaurants);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> updateRestaurant(@PathVariable String id, @Valid @RequestBody RestaurantRequestDTO requestDTO) {
        RestaurantResponseDTO updatedRestaurant = restaurantService.updateRestaurant(id, requestDTO);
        if (updatedRestaurant != null) {
            return ResponseEntity.ok(updatedRestaurant);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable String id) {
        restaurantService.deleteRestaurant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/orders/restaurant/{restaurantId}")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByRestaurantId(@PathVariable String restaurantId) {
        List<OrderResponseDTO> orders = restaurantService.getOrdersByRestaurantId(restaurantId);
        return ResponseEntity.ok(orders);
    }
    @PatchMapping("/orders/{orderId}/status/{newStatus}")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(
            @PathVariable String orderId,
            @PathVariable String newStatus
    ) {
        OrderResponseDTO updated = restaurantService.updateOrderStatus(orderId, newStatus);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> toggleRestaurantStatus(@PathVariable String id, @RequestParam boolean active) {
        boolean updated = restaurantService.updateRestaurantStatus(id, active);
        return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }


}
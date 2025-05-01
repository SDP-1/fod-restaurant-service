package com.fod.restaurant_service.service;

import com.fod.restaurant_service.client.OrderClient;
import com.fod.restaurant_service.dto.OrderResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fod.restaurant_service.dto.RestaurantRequestDTO;
import com.fod.restaurant_service.dto.RestaurantResponseDTO;
import com.fod.restaurant_service.entity.Restaurant;
import com.fod.restaurant_service.entity.Enum.CuisineType;
import com.fod.restaurant_service.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private  ModelMapper modelMapper;

    @Autowired
    private OrderClient orderClient;

    public RestaurantResponseDTO createRestaurant(RestaurantRequestDTO requestDTO) {
        Restaurant restaurant = modelMapper.map(requestDTO, Restaurant.class);
        restaurant.setActive(true);
        restaurant.setAverageRating(0.0);
        restaurant.setReviewCount(0);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return modelMapper.map(savedRestaurant, RestaurantResponseDTO.class);
    }

    public Optional<RestaurantResponseDTO> getRestaurantById(String id) {
        return restaurantRepository.findById(id)
                .map(restaurant -> modelMapper.map(restaurant, RestaurantResponseDTO.class));
    }

    public List<RestaurantResponseDTO> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantResponseDTO.class))
                .collect(Collectors.toList());
    }

    public List<RestaurantResponseDTO> getRestaurantsByCuisine(CuisineType cuisineType) {
        return restaurantRepository.findByCuisineType(cuisineType)
                .stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantResponseDTO.class))
                .collect(Collectors.toList());
    }

    public List<RestaurantResponseDTO> searchRestaurantsByName(String name) {
        return restaurantRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantResponseDTO.class))
                .collect(Collectors.toList());
    }

    public RestaurantResponseDTO updateRestaurant(String id, RestaurantRequestDTO requestDTO) {
        Optional<Restaurant> existingRestaurant = restaurantRepository.findById(id);
        if (existingRestaurant.isPresent()) {
            Restaurant restaurant = existingRestaurant.get();
            modelMapper.map(requestDTO, restaurant);
            Restaurant updatedRestaurant = restaurantRepository.save(restaurant);
            return modelMapper.map(updatedRestaurant, RestaurantResponseDTO.class);
        }
        return null;
    }

    public void deleteRestaurant(String id) {
        restaurantRepository.deleteById(id);
    }
    public List<OrderResponseDTO> getOrdersByRestaurantId(String restaurantId){
        return orderClient.getOrdersByRestaurantId(restaurantId);
    }
    public OrderResponseDTO updateOrderStatus(String orderId, String newStatus) {
        return orderClient.updateOrderStatus(orderId, newStatus);
    }
    public boolean updateRestaurantStatus(String id, boolean active) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            restaurant.setActive(active);
            restaurantRepository.save(restaurant);
            return true;
        }
        return false;
    }

}


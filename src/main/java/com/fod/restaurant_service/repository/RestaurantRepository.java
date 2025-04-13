package com.fod.restaurant_service.repository;

import org.springframework.stereotype.Repository;

import com.fod.restaurant_service.entity.Restaurant;
import com.fod.restaurant_service.entity.Enum.CuisineType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
    List<Restaurant> findByCuisineType(CuisineType cuisineType);
    List<Restaurant> findByNameContainingIgnoreCase(String name);
}
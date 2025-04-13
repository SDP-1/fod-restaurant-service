package com.fod.restaurant_service.repository;

import com.fod.restaurant_service.entity.MenuItem;
import com.fod.restaurant_service.entity.Enum.ItemCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends MongoRepository<MenuItem, String> {
    List<MenuItem> findByRestaurantId(String restaurantId);
    List<MenuItem> findByRestaurantIdAndCategory(String restaurantId, ItemCategory category);
}

package com.fod.restaurant_service.entity;

import com.fod.restaurant_service.entity.Enum.ItemCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "menu_items")
public class MenuItem extends GenaralModel {
    @Id
    private String id; // Unique identifier (e.g., "item_456")
    private String restaurantId; // Links to Restaurant.id (e.g., "rest_123")
    private String name; // Item name (e.g., "Butter Chicken")
    private String description; // Details (e.g., "Creamy tomato-based curry with tender chicken")
    private Double price; // Price (e.g., 12.99)
    private ItemCategory category; // E.g., MAIN_COURSE
    private boolean available; // Stock status (e.g., true)
    private List<String> imageUrls = new ArrayList<>(); // Multiple images (e.g., ["https://cdn.example.com/butterchicken1.jpg", "https://cdn.example.com/butterchicken2.jpg"])
    private List<String> dietaryTags; // E.g., ["SPICY", "GLUTEN_FREE"]
    private Integer calories; // Nutritional info (e.g., 600)
    private List<String> customizationOptions; // E.g., ["Mild", "Medium", "Spicy"]
    private Double discountPrice; // Promotional price (e.g., 10.99)
    private Integer preparationTime; // in minutes
}

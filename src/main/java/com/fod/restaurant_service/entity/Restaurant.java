package com.fod.restaurant_service.entity;

import com.fod.restaurant_service.entity.Enum.CuisineType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "restaurants")
public class Restaurant extends GenaralModel {
    @Id
    private String id; // Unique identifier (e.g., "rest_123")
    private String name; // Restaurant name (e.g., "Spicy Haven")
    private String password; // Hashed password
    private Address address; // Physical location
    private CuisineType cuisineType; // E.g., INDIAN
    private String phoneNumber; // Contact (e.g., "+1-555-123-4567")
    private String email; // Contact email (e.g., "contact@spicyhaven.com")
    private Double averageRating; // Star rating (e.g., 4.3 out of 5)
    private Integer reviewCount; // Number of reviews (e.g., 250)
    private Integer estimatedDeliveryTime; // Minutes (e.g., 30)
    private Double deliveryFee; // Fee (e.g., 3.99)
    private List<String> operatingHours; // E.g., ["Mon-Sun: 11AM-10PM"]
    private List<String> imageUrls = new ArrayList<>();
    private String logoUrl; // RestaurantLogo
    private Boolean isPromoted; // Promotional status (e.g., true for sponsored listings)
    private List<String> dietaryPreferences; // E.g., ["VEGAN", "GLUTEN_FREE"]
}

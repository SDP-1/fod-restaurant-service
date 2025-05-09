package com.fod.restaurant_service.dto;

import com.fod.restaurant_service.entity.Enum.CuisineType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantRequestDTO {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Password is required")
    @Size(min = 4, message = "Password must be at least 4 characters long")
    private String password;

    @NotNull(message = "Address is required")
    @Valid
    private AddressDTO address;

    @NotNull(message = "Cuisine type is required")
    private CuisineType cuisineType;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number")
    private String phoneNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Operating hours are required")
    private List<String> operatingHours;

    @NotNull(message = "Delivery fee is required")
    @PositiveOrZero(message = "Delivery fee must be non-negative")
    private Double deliveryFee;

    @NotNull(message = "Estimated delivery time is required")
    @Positive(message = "Estimated delivery time must be positive")
    private Integer estimatedDeliveryTime;

    private List<String> imageUrls;

    private String logoUrl;

    private Boolean isPromoted;

    private List<String> dietaryPreferences;
    private Boolean active;
}

package com.fod.restaurant_service.dto;

import com.fod.restaurant_service.entity.Enum.ItemCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItemRequestDTO {
    @NotBlank(message = "Restaurant ID is required")
    private String restaurantId;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Double price;

    @NotNull(message = "Category is required")
    private ItemCategory category;

    @NotNull(message = "Availability is required")
    private Boolean isAvailable;

    private String imageUrl;

    private List<String> dietaryTags;

    @PositiveOrZero(message = "Calories must be non-negative")
    private Integer calories;

    private List<String> customizationOptions;

    @PositiveOrZero(message = "Discount price must be non-negative")
    private Double discountPrice;
}

package com.fod.restaurant_service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ReviewRequestDTO {

    @NotBlank(message = "User ID is required")
    private String userId;

    @NotBlank(message = "User name is required")
    private String userName;

    @NotBlank(message = "Target ID (restaurant or menu item ID) is required")
    private String targetId;

    @NotBlank(message = "Comment cannot be empty")
    @Size(max = 500, message = "Comment can be up to 500 characters")
    private String comment;

    @NotNull(message = "Rating is required")
    @DecimalMin(value = "0.5", message = "Rating must be at least 0.5")
    @DecimalMax(value = "5.0", message = "Rating must not exceed 5.0")
    private Double rating;
}


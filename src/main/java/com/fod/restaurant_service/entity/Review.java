package com.fod.restaurant_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review extends GenaralModel{
    @Id
    private String id; // Unique identifier
    private String userId;           // ID of the user who gave the review
    private String userName;         // Optional: name of the user
    private String targetId; // menuitem id or restaurant id
    private String comment;          // Review text
    private Double rating;           // Rating given (e.g., 4.5)
}


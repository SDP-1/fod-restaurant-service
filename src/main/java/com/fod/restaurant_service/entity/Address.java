package com.fod.restaurant_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    private String street; // E.g., "789 Oak Ave"
    private String city; // E.g., "Springfield"
    private String state; // E.g., "IL"
    private String zipCode; // E.g., "62703"
    private String landmark; // E.g., "Near downtown plaza"
    private Double latitude; // E.g., 39.7990
    private Double longitude; // E.g., -89.6452
}

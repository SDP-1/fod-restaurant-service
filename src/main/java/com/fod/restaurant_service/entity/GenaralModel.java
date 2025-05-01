package com.fod.restaurant_service.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
public class GenaralModel {
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;
    private boolean isActive = false;
}
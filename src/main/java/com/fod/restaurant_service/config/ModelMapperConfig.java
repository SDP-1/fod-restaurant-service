package com.fod.restaurant_service.config;

import com.fod.restaurant_service.dto.MenuItemRequestDTO;
import com.fod.restaurant_service.dto.RestaurantRequestDTO;
import com.fod.restaurant_service.entity.MenuItem;
import com.fod.restaurant_service.entity.Restaurant;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        // Custom mapping: isAvailable → available
        modelMapper.addMappings(new PropertyMap<MenuItemRequestDTO, MenuItem>() {
            @Override
            protected void configure() {
                map().setAvailable(source.getIsAvailable());
            }
        });

        // Custom mapping: active → isActive
        modelMapper.addMappings(new PropertyMap<RestaurantRequestDTO, Restaurant>() {
            @Override
            protected void configure() {
                map().setActive(source.getActive());
            }
        });


        return modelMapper;
    }
}

package com.fod.restaurant_service.config;

import com.fod.restaurant_service.dto.MenuItemRequestDTO;
import com.fod.restaurant_service.entity.MenuItem;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        // Custom mapping for isAvailable → available
        modelMapper.addMappings(new PropertyMap<MenuItemRequestDTO, MenuItem>() {
            @Override
            protected void configure() {
                map().setAvailable(source.getIsAvailable());
            }
        });

        return modelMapper;
    }
}

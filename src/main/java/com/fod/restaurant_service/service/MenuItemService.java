package com.fod.restaurant_service.service;

import com.fod.restaurant_service.dto.MenuItemRequestDTO;
import com.fod.restaurant_service.dto.MenuItemResponseDTO;
import com.fod.restaurant_service.dto.ReviewResponseDTO;
import com.fod.restaurant_service.entity.MenuItem;
import com.fod.restaurant_service.entity.Enum.ItemCategory;
import com.fod.restaurant_service.repository.MenuItemRepository;
import com.fod.restaurant_service.repository.RestaurantRepository;
import com.fod.restaurant_service.repository.ReviewRepository;
import com.netflix.discovery.converters.Auto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ReviewRepository reviewRepository;

    public MenuItemResponseDTO createMenuItem(MenuItemRequestDTO requestDTO) {
        if (!restaurantRepository.existsById(requestDTO.getRestaurantId())) {
            throw new IllegalArgumentException("Restaurant not found. restaurant id: " + requestDTO.getRestaurantId());
        }
        MenuItem menuItem = modelMapper.map(requestDTO, MenuItem.class);
        menuItem.setActive(true);
        MenuItem savedMenuItem = menuItemRepository.save(menuItem);
        return modelMapper.map(savedMenuItem, MenuItemResponseDTO.class);
    }

    public Optional<MenuItemResponseDTO> getMenuItemById(String id) {
        return menuItemRepository.findById(id)
                .map(this::convertToMenuItemResponseDTO);
    }

    public List<MenuItemResponseDTO> getAllMenuItems() {
        return menuItemRepository.findAll()
                .stream()
                .map(this::convertToMenuItemResponseDTO)
                .collect(Collectors.toList());
    }

    public List<String> getMenuItemsCategorysByRestaurantId( String restaurantId) {
        if (!restaurantRepository.existsById(restaurantId)) {
            throw new IllegalArgumentException("Restaurant not found: " + restaurantId);
        }

        return menuItemRepository.findByRestaurantId(restaurantId)
                .stream()
                .map(MenuItem::getCategory)       // extract ItemCategory
                .distinct()                       // remove duplicates
                .map(Enum::name)                  // convert to String
                .collect(Collectors.toList());
    }

    public List<MenuItemResponseDTO> getMenuItemsByRestaurant(String restaurantId) {
        if (!restaurantRepository.existsById(restaurantId)) {
            throw new IllegalArgumentException("Restaurant not found: " + restaurantId);
        }
        return menuItemRepository.findByRestaurantId(restaurantId)
                .stream()
                .map(this::convertToMenuItemResponseDTO)
                .collect(Collectors.toList());
    }

    public List<MenuItemResponseDTO> getMenuItemsByRestaurantAndCategory(String restaurantId, ItemCategory category) {
        if (!restaurantRepository.existsById(restaurantId)) {
            throw new IllegalArgumentException("Restaurant not found: " + restaurantId);
        }
        return menuItemRepository.findByRestaurantIdAndCategory(restaurantId, category)
                .stream()
                .map(this::convertToMenuItemResponseDTO)
                .collect(Collectors.toList());
    }

    public MenuItemResponseDTO updateMenuItem(String id, MenuItemRequestDTO requestDTO) {
        Optional<MenuItem> existingMenuItem = menuItemRepository.findById(id);
        if (existingMenuItem.isPresent()) {
            if (!restaurantRepository.existsById(requestDTO.getRestaurantId())) {
                throw new IllegalArgumentException("Restaurant not found: " + requestDTO.getRestaurantId());
            }
            MenuItem menuItem = existingMenuItem.get();
            modelMapper.map(requestDTO, menuItem);
            MenuItem updatedMenuItem = menuItemRepository.save(menuItem);
            return modelMapper.map(updatedMenuItem, MenuItemResponseDTO.class);
        }
        return null;
    }

    private MenuItemResponseDTO convertToMenuItemResponseDTO(MenuItem menuItem) {
        MenuItemResponseDTO dto = modelMapper.map(menuItem, MenuItemResponseDTO.class);
        dto.setReviews(
                reviewRepository.findByTargetId(menuItem.getId()).stream()
                        .map(review -> modelMapper.map(review, ReviewResponseDTO.class))
                        .collect(Collectors.toList())
        );
        return dto;
    }


    public void deleteMenuItem(String id) {
        menuItemRepository.deleteById(id);
    }
}

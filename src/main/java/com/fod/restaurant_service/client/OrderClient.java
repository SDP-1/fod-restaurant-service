
package com.fod.restaurant_service.client;

import com.fod.restaurant_service.dto.OrderResponseDTO;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "order-service")
public interface OrderClient {

    //example
    //remove this
    @GetMapping("/api/orders/restaurant/{restaurantId}")
    @LoadBalanced
    List<OrderResponseDTO> getOrdersByRestaurantId(@PathVariable("restaurantId") String restaurantId);

    @PutMapping("/api/orders/{orderId}/status/{newStatus}")
    OrderResponseDTO updateOrderStatus(
            @PathVariable("orderId") String orderId,
            @PathVariable("newStatus") String newStatus
    );

}
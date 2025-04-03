package com.fod.restaurant_service.client;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "restaurant-service")
public interface OrderClient {

    //example
    //remove this
    @GetMapping("/order/{order-id}")
    @LoadBalanced
    List<String> findAllOdersById(@PathVariable("order-id") int orderId);

}

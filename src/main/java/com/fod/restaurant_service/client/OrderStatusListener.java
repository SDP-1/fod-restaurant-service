//package com.fod.restaurant_service.client;
//
//import com.fod.restaurant_service.dto.OrderStatusUpdateEvent;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class OrderStatusListener {
//
//    private static final Logger logger = LoggerFactory.getLogger(OrderStatusListener.class);
//
//    @KafkaListener(
//            topics = "order-status-updates",
//            groupId = "restaurant-group",
//            containerFactory = "kafkaListenerContainerFactory" // make sure you configure this in config
//    )
//    public void handleOrderStatusEvent(OrderStatusUpdateEvent event) {
//        logger.info("Received OrderStatusUpdateEvent via Kafka: {}", event);
//
//        if ("COMPLETED".equalsIgnoreCase(event.getStatus())) {
//            logger.info(" Order {} marked as COMPLETED by Delivery Service", event.getOrderRefId());
//
//            //  TODO: Persist the completed status to your database if needed
//            // e.g., update an order record in your local DB
//        } else {
//            logger.info(" Order {} has status: {}", event.getOrderRefId(), event.getStatus());
//        }
//    }
//}

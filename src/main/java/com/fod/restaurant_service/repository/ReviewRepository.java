package com.fod.restaurant_service.repository;

import com.fod.restaurant_service.entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {

    List<Review> findByTargetId(String targetId);

    List<Review> findByUserId(String userId);

    List<Review> findByTargetIdAndIsActiveTrue(String targetId);
}


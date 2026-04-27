package com.webinarhub.platform.repository;

import com.webinarhub.platform.entity.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {
    List<Rating> findByWebinarIdOrderByCreatedAtDesc(String webinarId);
    boolean existsByUserIdAndWebinarId(String userId, String webinarId);
}

package com.webinarhub.platform.repository;

import com.webinarhub.platform.entity.Registration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends MongoRepository<Registration, String> {

    // Use simple string field queries — no @DBRef ObjectId issues
    List<Registration> findByUserId(String userId);

    List<Registration> findByWebinarId(String webinarId);

    Optional<Registration> findByUserIdAndWebinarId(String userId, String webinarId);

    Long countByWebinarId(String webinarId);

    void deleteByUserIdAndWebinarId(String userId, String webinarId);

    Long countByWebinarIdAndAttendedTrue(String webinarId);
}

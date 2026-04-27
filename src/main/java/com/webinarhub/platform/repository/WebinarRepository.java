package com.webinarhub.platform.repository;

import com.webinarhub.platform.entity.Webinar;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WebinarRepository extends MongoRepository<Webinar, String> {

    List<Webinar> findByTitleContainingIgnoreCase(String title);

    List<Webinar> findByStatus(Webinar.WebinarStatus status);

    List<Webinar> findByCategory(String category);

    Long countByStatus(Webinar.WebinarStatus status);

    @Query(value = "{ 'dateTime': { $gt: ?0 } }", sort = "{ 'dateTime' : 1 }")
    List<Webinar> findUpcomingWebinars(LocalDateTime now);

    @Query("{ 'instructor': ?0 }")
    List<Webinar> findByInstructorName(String instructor);

    // Registration count logic gets complicated in Mongo without aggregation, 
    // but typically handled in service layer or embedded docs.
    // For now we'll comment this JPQL mapping to prevent crashes, since Registration has the ref.
    // Long countRegistrationsByWebinarId(String webinarId);

    // Update status is better handled in the Service by saving or partial update.
    // void updateStatusById(String id, Webinar.WebinarStatus status);

    List<Webinar> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);

    List<Webinar> findAllByOrderByDateTimeDesc();

    List<Webinar> findByDateTimeBetweenAndReminderSentFalse(LocalDateTime start, LocalDateTime end);
}

package com.webinarhub.platform.repository;

import com.webinarhub.platform.entity.Resource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends MongoRepository<Resource, String> {

    @Query("{ 'webinar.$id': ?0 }")
    List<Resource> findByWebinarId(String webinarId);

    List<Resource> findByFileType(Resource.ResourceType fileType);

    @Query(value = "{ 'webinar.$id': ?0 }", count = true)
    Long countByWebinarId(String webinarId);

    @Query(value = "{ 'webinar.$id': ?0 }", delete = true)
    void deleteByWebinarId(String webinarId);

    @Query("{ 'webinar.$id': ?0 }")
    List<Resource> findResourcesWithWebinar(String webinarId);
}

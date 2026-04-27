package com.webinarhub.platform.repository;

import com.webinarhub.platform.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    Long countByRole(User.Role role);

    @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
    List<User> searchByName(String name);

    boolean existsByEmail(String email);

    List<User> findByRole(User.Role role);
}

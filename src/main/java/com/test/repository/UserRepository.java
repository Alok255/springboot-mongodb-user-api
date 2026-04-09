package com.test.repository;

import com.test.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByUserName(String userName);
    String deleteByUserName(String userName);
}

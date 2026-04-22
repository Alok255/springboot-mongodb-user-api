package com.test.service;

import com.mongodb.MongoTimeoutException;
import com.test.dto.UserDTO;
import com.test.entity.UserEntity;
import com.test.exception.UserNotFound;
import com.test.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final MongoTemplate mongoTemplate;

    public List<UserDTO> getUserByAge(int userAge) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userAge").gt(userAge));
        System.out.println("native query:" + query);
        List<UserEntity> userEntity = mongoTemplate.find(query, UserEntity.class);
        return userEntity.stream().map(
                entity -> {
                    UserDTO userdto = new UserDTO();
                    userdto.setUserEmail(entity.getUserEmail());
                    userdto.setUserName(entity.getUserName());
                    userdto.setUserAge(entity.getUserAge());
                    return userdto;
                }
        ).collect(Collectors.toList());

    }

    //@Override
    public UserEntity createUser(UserDTO userDTO) {

        // if (userDTO.getUserName().equalsIgnoreCase("")) {
        //  throw new RuntimeException("Request Payload cannot be empty");
        //log.error("user request payload cannot be null");
        // return null;

        UserEntity userEntity = new UserEntity();

        //Mapping between DTO and Entity
        userEntity.setUserName(userDTO.getUserName());
        userEntity.setUserEmail(userDTO.getUserEmail());
        userEntity.setUserAge(userDTO.getUserAge());
        return userRepository.save(userEntity);

    }


    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserByName(String name) {
        try {
            Optional<UserEntity> optionalUser = userRepository.findByUserName(name);
            if (optionalUser.isEmpty()) {
                throw new UserNotFound("User not found with the name");
            }
            return optionalUser;
        } catch (MongoTimeoutException e) {
            throw new MongoTimeoutException("Error fetching the user By name " + e.getMessage());
        }
    }

    public String deleteUserByName(String name) {
        userRepository.deleteByUserName(name);
        return "user deleted";
    }

    public Optional<UserEntity> getUserById(String id) {
        try {
            Optional<UserEntity> optionalUser = Optional.ofNullable(userRepository.findById(id).orElse(null));
            if (optionalUser.isEmpty()) {
                throw new RuntimeException("User not found" + id);
            }
            return optionalUser;
        } catch (Exception e) {
            throw new RuntimeException("Error occured while getting user by id:" + e.getMessage());
        }

    }

    public UserDTO updateUserById(String id, UserDTO userDTO) {
        Optional<UserEntity> optionalUser = Optional.ofNullable(userRepository.findById(id).orElse(null));
        UserDTO userResponse = new UserDTO();
        if (!optionalUser.isEmpty()) {
            UserEntity userentity = new UserEntity();
            userentity.setUserAge(userDTO.getUserAge());
            userentity.setUserName(userDTO.getUserName());
            userentity.setUserEmail(userDTO.getUserEmail());
            userentity = userRepository.save(userentity);

            userResponse.setUserAge(userentity.getUserAge());
            userResponse.setUserName(userentity.getUserName());
            userResponse.setUserEmail(userentity.getUserEmail());
        }

        return userResponse;
    }

}

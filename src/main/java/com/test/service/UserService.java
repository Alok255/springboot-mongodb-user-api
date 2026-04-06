package com.test.service;

import com.test.dto.UserDTO;
import com.test.entity.UserEntity;
import com.test.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    //@Override
    public UserEntity createUser(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();

        //Mapping between DTO and Entity
        userEntity.setUserName(userDTO.getUserName());
        userEntity.setUserEmail(userDTO.getUserEmail());
        userEntity.setUserAge(userDTO.getUserAge());
        return repository.save(userEntity);
    }

}

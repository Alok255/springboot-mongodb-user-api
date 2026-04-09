package com.test.service;

import com.test.dto.UserDTO;
import com.test.entity.UserEntity;
import com.test.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final MongoTemplate mongotemplate;
    public List<UserDTO>  getUserByAge(int userAge ){
        Query query=new Query();
        query.addCriteria(Criteria.where("userAge" ).gt(userAge));
        System.out.println("native query:"+query);
       List <UserEntity> userentity=mongotemplate.find(query, UserEntity.class);
       return userentity.stream().map(
               entity ->{
                   UserDTO userdto = new UserDTO();
                   userdto.setUserEmail(entity.getUserEmail());
                   userdto.setUserName(entity.getUserName());
                   userdto.setUserAge(entity.getUserAge());
                   return userdto;
               }
               ).collect(Collectors.toList());

    }

    //@Override
    public UserEntity createUser(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();

        //Mapping between DTO and Entity
        userEntity.setUserName(userDTO.getUserName());
        userEntity.setUserEmail(userDTO.getUserEmail());
        userEntity.setUserAge(userDTO.getUserAge());
        return repository.save(userEntity);
    }
    public List<UserEntity> getAllUsers(){
        return repository.findAll();
    }
    public Optional<UserEntity> getUserByName(String name){
        Optional<UserEntity> optionalUser = Optional.ofNullable(repository.findByUserName(name).orElse(null));
        return optionalUser;
    }
    public String deleteUserByName(String name){
        repository.deleteByUserName(name);
        return "user deleted";
    }
    public Optional<UserEntity> getUserById(String id){
        Optional<UserEntity> optionalUser= Optional.ofNullable(repository.findById(id).orElse(null));
        return optionalUser;
    }
    public UserDTO updateUserById(String id, UserDTO userDTO){
        Optional<UserEntity> optionalUser= Optional.ofNullable(repository.findById(id).orElse(null));
        UserDTO userResponse=new UserDTO();
        if (!optionalUser.isEmpty()) {
            UserEntity userentity = new UserEntity();
            userentity.setUserAge(userDTO.getUserAge());
            userentity.setUserName(userDTO.getUserName());
            userentity.setUserEmail(userDTO.getUserEmail());
            userentity = repository.save(userentity);

            userResponse.setUserAge(userentity.getUserAge());
            userResponse.setUserName(userentity.getUserName());
            userResponse.setUserEmail(userentity.getUserEmail());
        }

        return userResponse;
    }

}

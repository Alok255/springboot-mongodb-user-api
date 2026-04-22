package com.test.service;

import com.test.dto.UserDTO;
import com.test.entity.UserEntity;
import com.test.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private MongoTemplate mongoTemplate;
    @InjectMocks
    private UserService userService;


    @Test
    void testGetAllUsers(){
        List<UserEntity> userList = List.of(new UserEntity("166763", 1, "Pranav", "pranava@gmail.com",24),new UserEntity("242", 2, "fdswf", "pranava@gmail.com",24));
        when(userRepository.findAll()).thenReturn(userList);
        List<UserEntity> userResult = userService.getAllUsers();
        Assertions.assertEquals(2,userList.size()); //validation
        verify(userRepository).findAll();
    }
    @Test
    void testGetUserByName() {
        UserEntity userEntity = new UserEntity("166763", 1, "Pranav", "pranava@gmail.com", 24);
        when(userRepository.findByUserName("Pranav")).thenReturn(Optional.of(userEntity));
        Optional<UserEntity> optionalUser = userService.getUserByName("Pranav");
        Assertions.assertEquals("Pranav", optionalUser.get().getUserName());
        Assertions.assertTrue(optionalUser.isPresent());
    }
        @Test
        void testGetUserById(){
                UserEntity userEntity1 = new UserEntity("166763", 1, "Pranav", "pranava@gmail.com",24);
                when(userRepository.findById("166763")).thenReturn(Optional.of(userEntity1));
                Optional<UserEntity> optionalUser1 = userService.getUserById("166763");
                Assertions.assertEquals("166763", optionalUser1.get().getId());
                Assertions.assertTrue(optionalUser1.isPresent());
    }
    @Test
    void testDeleteUserByName(){
    //    doNothing().when(userRepository).deleteByUserName("Pranav");
        when(userRepository.deleteByUserName("Pranav")).thenReturn("deleted");
        String deletedResult = userService.deleteUserByName("Pranav");
        Assertions.assertEquals("user deleted", deletedResult);
        verify(userRepository).deleteByUserName("Pranav");
    }
    @Test
    void testGetUserByAge(){
        List<UserEntity> userList = List.of(new UserEntity("166763", 1, "Pranav", "pranava@gmail.com",24),new UserEntity("242", 2, "fdswf", "pranava@gmail.com",24));
        when(mongoTemplate.find(any(Query.class),eq(UserEntity.class))).thenReturn(userList);
        List<UserDTO> userDto= userService.getUserByAge(14);
        Assertions.assertEquals(2, userDto.size());
        Assertions.assertEquals("Pranav", userDto.get(0).getUserName());
    }

}

package com.example.testing;

import com.example.testing.demo.User;
import com.example.testing.demo.UserRepository;
import com.example.testing.demo.UserService;
import com.example.testing.dto.ListUserResponse;
import com.example.testing.dto.UserDto;
import com.example.testing.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class ServiceTests {
    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    private UserDto user;
    private List<User> users;
    private UserResponse userResponse;
    private User user1;
    @BeforeEach
    void setUp() {
        users = List.of(new User(1,"Amit", "amit@gmail.com"),
                new User(2,"Amit", "amit@gmail.com"),
                new User(3,"Amit", "amit@gmail.com"));
        user = new UserDto(2,"Rahul", "Swagger");
        user1 = new User(2,"Rahul", "Swagger");
    }
    @Test
    void testFindAllUsers(){
        when(repository.findAll()).thenReturn(users);
        ListUserResponse userList= service.getAll();
        log.info("userList: "+userList);
        Assertions.assertEquals(3,userList.users().size());
        verify(repository,times(1)).findAll();
        log.info("repository findAll method is verified");
    }
    @Test
    void createUser(){
        user = new UserDto(2,"Rahul", "Swagger");
        user1 = new User("Rahul", "Swagger");
        when(repository.save(user1)).thenReturn(user1);
        UserResponse userResponse= service.insert(user);
        log.info("user: "+userResponse);
        Assertions.assertEquals(user1.getName(),userResponse.user().getName());
        verify(repository,times(1)).save(user1);
        log.info("repository save method is verified");

    }

    @Test
    void getById(){
        when(repository.findById(user.id())).thenReturn(Optional.ofNullable(user1));
        UserResponse userResponse=service.getById(user.id());
        log.info("user: "+userResponse);
        Assertions.assertEquals(2,userResponse.user().getId());
        verify(repository,times(1)).findById(user.id());
        log.info("repository findById method is verified");

    }
    @Test
    void update(){
        when(repository.findById(user.id())).thenReturn(Optional.ofNullable(user1));
        when(repository.saveAndFlush(user1)).thenReturn(user1);
        UserResponse userResponse=service.update(user);
        log.info("user: "+userResponse);
        Assertions.assertEquals(user1.getName(),userResponse.user().getName());
        verify(repository,times(1)).saveAndFlush(user1);
        log.info("repository update method is verified");
    }

    @Test
    void patch(){
        Map<String,String> updates = new HashMap<>();
        updates.put("name","karthik");
        User patchedUser = new User(2,"karthik", "amit@gmail.com");
        when(repository.findById(patchedUser.getId())).thenReturn(Optional.of(patchedUser));
        when(repository.save(patchedUser)).thenReturn(patchedUser);
        UserResponse userResponse=service.patch(patchedUser.getId(),updates);
        log.info("user: "+userResponse);
        Assertions.assertEquals(patchedUser.getName(),userResponse.user().getName());
        verify(repository,times(1)).save(patchedUser);
        log.info("repository patch method is verified");
    }


}

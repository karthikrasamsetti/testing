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
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class ServiceTests {
    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    private UserDto user;
    private List<User> users;

    private User user1;
    @BeforeEach
    void setUp() {
        users = List.of(new User(1,"Amit", "amit@gmail.com"),
                new User(2,"Amit", "amit@gmail.com"),
                new User(3,"Amit", "amit@gmail.com"));
        user = new UserDto(2,"Rahul", "Swagger");
        user1 = new User("Rahul", "Swagger");
    }
    @Test
    public void testFindAllUsers(){
        when(repository.findAll()).thenReturn(users);
        ListUserResponse userList= service.getAll();
        log.info("userList: "+userList);
        Assertions.assertEquals(3,userList.users().size());
        verify(repository,times(1)).findAll();
        log.info("repository findAll method is verified");
    }
    @Test
    public  void createUser(){
        UserResponse userResponse= service.insert(user);
        log.info("user: "+userResponse);
        verify(repository,times(1)).save(user1);
        log.info("repository save method is verified");

    }


}

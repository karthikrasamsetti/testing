package com.example.testing.demo;

import com.example.testing.dto.ListUserResponse;
import com.example.testing.dto.UserDto;
import com.example.testing.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    public UserResponse insert(UserDto userDto){
        var user= User.builder()
                .name(userDto.name())
                .email(userDto.email())
                .build();
        System.out.println(user);
        userRepository.save(user);
        return UserResponse.builder()
                .user(user)
                .build();
    }

    public ListUserResponse getAll(){
        List<User>users= userRepository.findAll();
         return ListUserResponse.builder()
                 .users(users)
                 .build();
    }
    public UserResponse getById(int id){
        User user= userRepository.findById(id).orElseThrow();
        return UserResponse.builder()
                .user(user)
                .build();
    }
    public  UserResponse update(UserDto userDto){
        User user=userRepository.findById(userDto.id()).orElseThrow();
        user.setName(userDto.name());
        user.setEmail(userDto.email());
        User newUser= userRepository.saveAndFlush(user);
        return UserResponse.builder()
                .user(newUser)
                .build();
    }
    public UserResponse patch(int id, Map<String,String> updates){
        log.info("Enter Patch method");
        User user=userRepository.findById(id).orElseThrow();
        UserResponse newUser;
        if(updates.containsKey("name")){
          user.setName(updates.get("name"));}
        else {
            user.setEmail(updates.get("email"));
        }
        User patchUser=userRepository.save(user);
        return UserResponse.builder()
                .user(patchUser)
                .build();
    }
}

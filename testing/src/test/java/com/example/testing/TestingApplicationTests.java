package com.example.testing;


import com.example.testing.controller.SampleController;
import com.example.testing.demo.User;
import com.example.testing.demo.UserService;
import com.example.testing.dto.ListUserResponse;
import com.example.testing.dto.PatchRequest;
import com.example.testing.dto.UserDto;
import com.example.testing.dto.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@AutoConfigureMockMvc
class TestingApplicationTests {

    @Autowired
    private SampleController sampleController;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private MockMvc mockMvc;
    private UserDto user;
    private UserResponse usersResponse;
    private List<User> users;
    private ListUserResponse listUserResponse;
    @MockBean
    private UserService service;

    @BeforeEach
    void setUp() {
        users = List.of(new User(1,"Amit", "amit@gmail.com"),
                new User(2,"Amit", "amit@gmail.com"),
                new User(3,"Amit", "amit@gmail.com"));
        listUserResponse = new ListUserResponse(users);
        user = new UserDto(2,"Rahul", "Swagger");
        usersResponse = new UserResponse(new User(2,"Rahul", "Swagger"));
    }

    @Test
    void contextLoads() {
        Assertions.assertThat(sampleController).isNotNull();
    }

    @Test
    void testSayHello() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("Hello, World!"));
    }

    @Test
    void getAll() throws Exception {
        Mockito.when(service.getAll()).thenReturn(listUserResponse);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
        Assertions.assertThat(result).isNotNull();
        String userJson = result.getResponse().getContentAsString();
        Assertions.assertThat(userJson).isEqualToIgnoringCase(mapper.writeValueAsString(listUserResponse));

    }

    @Test
    void createUserTest() throws Exception {

        Mockito.when(service.insert(Mockito.any(UserDto.class))).thenReturn(usersResponse);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user).getBytes(StandardCharsets.UTF_8))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

        Assertions.assertThat(result).isNotNull();
        String userJson = result.getResponse().getContentAsString();
        Assertions.assertThat(userJson).isNotEmpty();
        Assertions.assertThat(userJson).isEqualToIgnoringCase(mapper.writeValueAsString(usersResponse));
    }

    @Test
    void getByIdTest() throws Exception {
        Mockito.when(service.getById(user.id())).thenReturn(usersResponse);
        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}",2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
        Assertions.assertThat(result).isNotNull();
        String userJson = result.getResponse().getContentAsString();
        Assertions.assertThat(userJson).isNotEmpty();
        Assertions.assertThat(userJson).isEqualToIgnoringCase(mapper.writeValueAsString(usersResponse));
    }

    @Test
    void updateUserTest() throws Exception {
        Mockito.when(service.update(Mockito.any(UserDto.class))).thenReturn(usersResponse);
        MvcResult result=mockMvc.perform(MockMvcRequestBuilders.put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user).getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
        Assertions.assertThat(result).isNotNull();
        String userJson=result.getResponse().getContentAsString();
        Assertions.assertThat(userJson).isNotEmpty();
        Assertions.assertThat(userJson).isEqualToIgnoringCase(mapper.writeValueAsString(usersResponse));

    }

    @Test
    void patchUserTest() throws Exception {
        Map<String,String> updates = new HashMap<>();
        updates.put("name","karthik");
        PatchRequest patchRequest=new PatchRequest(updates);

        User patchedUser = new User(2,"karthik", "amit@gmail.com");
        UserResponse userResponse = new UserResponse(patchedUser);

        Mockito.when(service.patch(patchedUser.getId(),updates)).thenReturn(userResponse);

        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.patch("/user/{id}",2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(patchRequest).getBytes(StandardCharsets.UTF_8))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
        Assertions.assertThat(result).isNotNull();
        String userJson = result.getResponse().getContentAsString();
        Assertions.assertThat(userJson).isNotEmpty();
        Assertions.assertThat(userJson).isEqualToIgnoringCase(mapper.writeValueAsString(userResponse));
    }

}

package com.example.testing.dto;

import com.example.testing.demo.User;
import lombok.Builder;

@Builder
public record UserResponse(User user) {

}

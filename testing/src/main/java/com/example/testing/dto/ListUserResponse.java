package com.example.testing.dto;

import com.example.testing.demo.User;
import lombok.Builder;

import java.util.List;
@Builder
public record ListUserResponse(List<User> users) {
}

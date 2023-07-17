package com.example.testing.dto;

import lombok.Builder;

@Builder
public record UserDto(int id,String name,String email) {
}

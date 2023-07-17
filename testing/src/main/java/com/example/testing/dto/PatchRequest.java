package com.example.testing.dto;

import lombok.Builder;
import org.springframework.validation.annotation.Validated;

import java.util.Map;
@Builder
public record PatchRequest(@Validated Map<String,String> map) {
}

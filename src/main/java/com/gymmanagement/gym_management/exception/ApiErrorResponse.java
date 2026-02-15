package com.gymmanagement.gym_management.exception;

import java.time.Instant;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorResponse {
    private String code;
    private String message;
    private Map<String, Object> details;
    private Instant timestamp;
}

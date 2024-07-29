package com.rest.api.dto;

import java.time.LocalDateTime;

public record ApiResponse(String path, String status, int statusCode, boolean success, String message,
                          String errorMessage, LocalDateTime timestamp) {
}
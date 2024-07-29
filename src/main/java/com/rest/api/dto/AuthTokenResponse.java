package com.rest.api.dto;

import com.rest.api.enumeration.TokenType;

public record AuthTokenResponse(String authToken, TokenType type) {
}

package com.rest.api.dto;

import java.util.List;

public record AuthTokenDetails(String id,
                               String username,
                               String email,
                               String contactNo,
                               String city,
                               String state,
                               String country,
                               boolean isAccountNonExpired,
                               boolean isAccountNonLocked,
                               boolean isCredentialsNonExpired,
                               boolean isEnabled,
                               boolean verified,
                               List<String> roles,
                               List<String> authorities) {
}

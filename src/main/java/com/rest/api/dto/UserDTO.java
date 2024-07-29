package com.rest.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String id;
    private String username;
    private String password;
    private String email;
    private String contactNo;
    private String city;
    private String state;
    private String country;
    public boolean isAccountNonExpired;
    public boolean isAccountNonLocked;
    public boolean isCredentialsNonExpired;
    public boolean isEnabled;
    public boolean verified;
    private Set<RoleDTO> roles;
}

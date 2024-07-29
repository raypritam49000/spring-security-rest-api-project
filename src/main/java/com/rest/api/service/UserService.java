package com.rest.api.service;

import com.rest.api.dto.AuthRequestDTO;
import com.rest.api.dto.AuthTokenResponse;
import com.rest.api.dto.UserDTO;

public interface UserService {
    public UserDTO loadUserByUsername(String username);

    public UserDTO createUser(UserDTO userDTO);

    public AuthTokenResponse loginUser(AuthRequestDTO authRequestDTO);
}

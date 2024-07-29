package com.rest.api.service.impl;

import com.rest.api.dto.AuthRequestDTO;
import com.rest.api.dto.AuthTokenDetails;
import com.rest.api.dto.AuthTokenResponse;
import com.rest.api.dto.UserDTO;
import com.rest.api.enumeration.TokenType;
import com.rest.api.exception.BadCredentialsException;
import com.rest.api.exception.ResourceNotFoundException;
import com.rest.api.mappers.UserMapper;
import com.rest.api.repository.UserRepository;
import com.rest.api.security.JwtUtil;
import com.rest.api.security.PasswordEncoder;
import com.rest.api.service.RoleService;
import com.rest.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RoleService roleService;


    @Override
    public UserDTO loadUserByUsername(String username) {
        return UserMapper.INSTANCE.toDto(userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found with given username : " + username)));
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setAccountNonExpired(true);
        userDTO.setAccountNonLocked(true);
        userDTO.setCredentialsNonExpired(true);
        userDTO.setVerified(true);
        userDTO.setEnabled(true);
        return UserMapper.INSTANCE.toDto(userRepository.save(UserMapper.INSTANCE.toEntity(userDTO)));
    }

    @Override
    public AuthTokenResponse loginUser(AuthRequestDTO authRequestDTO) {
        UserDTO userDTO = loadUserByUsername(authRequestDTO.getUsername());

        if (!passwordEncoder.matches(authRequestDTO.getPassword(), userDTO.getPassword()))
            throw new BadCredentialsException("Invalid Credentials");

        List<String> userRoles = roleService.getRolesByUserId(userDTO.getId());
        List<String> userPermissions = roleService.getPermissionsByUserId(userDTO.getId());

        String token = jwtUtil.createToken(
                new AuthTokenDetails(
                        userDTO.getId(),
                        userDTO.getUsername(),
                        userDTO.getEmail(),
                        userDTO.getContactNo(),
                        userDTO.getCity(),
                        userDTO.getState(),
                        userDTO.getState(),
                        userDTO.isAccountNonExpired(),
                        userDTO.isAccountNonLocked(),
                        userDTO.isCredentialsNonExpired(),
                        userDTO.isEnabled,
                        userDTO.isVerified(),
                        userRoles,
                        userPermissions));

        return new AuthTokenResponse(token, TokenType.Bearer);
    }

}

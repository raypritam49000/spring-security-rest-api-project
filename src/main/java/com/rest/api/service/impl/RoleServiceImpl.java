package com.rest.api.service.impl;

import com.rest.api.exception.ResourceNotFoundException;
import com.rest.api.model.Permission;
import com.rest.api.model.Role;
import com.rest.api.repository.UserRepository;
import com.rest.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<String> getRolesByUserId(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with given userid : " + userId))
                .getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getPermissionsByUserId(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with given userid : " + userId))
                .getRoles()
                .stream()
                .flatMap(role -> role.getPermissions().stream())
                .map(Permission::getName)
                .collect(Collectors.toList());
    }
}

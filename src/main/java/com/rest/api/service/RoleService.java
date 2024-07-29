package com.rest.api.service;

import java.util.List;

public interface RoleService {
    List<String> getRolesByUserId(String userId);

    List<String> getPermissionsByUserId(String userId);
}

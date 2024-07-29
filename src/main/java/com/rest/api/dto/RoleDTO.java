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
public class RoleDTO {
    private String id;
    private String name;
    private Set<PermissionDTO> permissions;
}
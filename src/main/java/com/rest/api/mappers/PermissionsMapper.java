package com.rest.api.mappers;

import com.rest.api.dto.PermissionDTO;
import com.rest.api.mappers.base.BaseMapper;
import com.rest.api.model.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PermissionsMapper extends BaseMapper<PermissionDTO, Permission> {
    PermissionsMapper INSTANCE = Mappers.getMapper(PermissionsMapper.class);
}
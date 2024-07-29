package com.rest.api.mappers;

import com.rest.api.dto.RoleDTO;
import com.rest.api.mappers.base.BaseMapper;
import com.rest.api.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RolesMapper extends BaseMapper<RoleDTO, Role> {
    RolesMapper INSTANCE = Mappers.getMapper(RolesMapper.class);

    @Override
    RoleDTO toDto(Role entity);

    @Override
    Role toEntity(RoleDTO dto);

    @Override
    List<RoleDTO> toDtoList(List<Role> entities);

    @Override
    List<Role> toEntityList(List<RoleDTO> entities);
}
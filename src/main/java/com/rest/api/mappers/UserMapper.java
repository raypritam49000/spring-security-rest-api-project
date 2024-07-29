package com.rest.api.mappers;

import com.rest.api.dto.UserDTO;
import com.rest.api.mappers.base.BaseMapper;
import com.rest.api.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper extends BaseMapper<UserDTO, User> {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}
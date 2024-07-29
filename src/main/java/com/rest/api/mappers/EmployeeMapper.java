package com.rest.api.mappers;

import com.rest.api.dto.EmployeeDTO;
import com.rest.api.mappers.base.BaseMapper;
import com.rest.api.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper extends BaseMapper<EmployeeDTO, Employee> {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
}

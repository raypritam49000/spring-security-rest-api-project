package com.rest.api.mappers;

import com.rest.api.dto.StudentDTO;
import com.rest.api.mappers.base.BaseMapper;
import com.rest.api.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper extends BaseMapper<StudentDTO, Student> {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
}

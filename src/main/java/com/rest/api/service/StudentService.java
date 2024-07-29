package com.rest.api.service;

import com.rest.api.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    public StudentDTO createStudent(StudentDTO student);
    public List<StudentDTO> getAllStudents();
    public StudentDTO getStudentById(String id);
    public StudentDTO updateStudent(String id, StudentDTO studentDetails);
    public boolean deleteStudent(String id);
}

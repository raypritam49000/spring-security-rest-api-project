package com.rest.api.service.impl;

import com.rest.api.dto.StudentDTO;
import com.rest.api.exception.ResourceNotFoundException;
import com.rest.api.mappers.StudentMapper;
import com.rest.api.model.Student;
import com.rest.api.repository.StudentRepository;
import com.rest.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentDTO createStudent(StudentDTO student) {
        return StudentMapper.INSTANCE.toDto(studentRepository.save(StudentMapper.INSTANCE.toEntity(student)));
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return StudentMapper.INSTANCE.toDtoList(studentRepository.findAll());
    }

    @Override
    public StudentDTO getStudentById(String id) {
        return StudentMapper.INSTANCE.toDto(studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with given id : " + id)));
    }

    @Override
    public StudentDTO updateStudent(String id, StudentDTO studentDetails) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with given id : " + id));

        existingStudent.setFirstName(studentDetails.getFirstName());
        existingStudent.setLastName(studentDetails.getLastName());
        existingStudent.setEmail(studentDetails.getEmail());
        existingStudent.setContactNo(studentDetails.getContactNo());
        existingStudent.setSalary(studentDetails.getSalary());
        existingStudent.setGender(studentDetails.getGender());
        existingStudent.setFatherName(studentDetails.getFatherName());
        existingStudent.setMotherName(studentDetails.getMotherName());

        return StudentMapper.INSTANCE.toDto(studentRepository.save(existingStudent));
    }

    @Override
    public boolean deleteStudent(String id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

package com.rest.api.controller;

import com.rest.api.dto.AuthTokenDetails;
import com.rest.api.dto.StudentDTO;
import com.rest.api.exception.UnauthorizedAccessException;
import com.rest.api.service.StudentService;
import com.rest.api.utility.PermissionUtility;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO student, HttpServletRequest request) {
        AuthTokenDetails authTokenDetails = (AuthTokenDetails) request.getAttribute("authTokenDetails");
        if (!PermissionUtility.hasPermission(authTokenDetails, "STUDENT_CREATE")) {
            throw new UnauthorizedAccessException("Access denied");
        }
        StudentDTO createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents(HttpServletRequest request) {
        AuthTokenDetails authTokenDetails = (AuthTokenDetails) request.getAttribute("authTokenDetails");
        if (!PermissionUtility.hasPermission(authTokenDetails, "STUDENT_READ")) {
            throw new UnauthorizedAccessException("Access denied");
        }
        List<StudentDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable String id, HttpServletRequest request) {
        AuthTokenDetails authTokenDetails = (AuthTokenDetails) request.getAttribute("authTokenDetails");
        if (!PermissionUtility.hasPermission(authTokenDetails, "STUDENT_READ")) {
            throw new UnauthorizedAccessException("Access denied");
        }
        StudentDTO student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable String id, @RequestBody StudentDTO studentDetails, HttpServletRequest request) {
        AuthTokenDetails authTokenDetails = (AuthTokenDetails) request.getAttribute("authTokenDetails");
        if (!PermissionUtility.hasPermission(authTokenDetails, "STUDENT_UPDATE")) {
            throw new UnauthorizedAccessException("Access denied");
        }
        StudentDTO updatedStudent = studentService.updateStudent(id, studentDetails);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id, HttpServletRequest request) {
        AuthTokenDetails authTokenDetails = (AuthTokenDetails) request.getAttribute("authTokenDetails");
        if (!PermissionUtility.hasPermission(authTokenDetails, "STUDENT_DELETE")) {
            throw new UnauthorizedAccessException("Access denied");
        }
        boolean isDeleted = studentService.deleteStudent(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

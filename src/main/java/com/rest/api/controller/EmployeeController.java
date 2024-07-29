package com.rest.api.controller;

import com.rest.api.dto.AuthTokenDetails;
import com.rest.api.dto.EmployeeDTO;
import com.rest.api.exception.UnauthorizedAccessException;
import com.rest.api.service.EmployeeService;
import com.rest.api.utility.PermissionUtility;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO, HttpServletRequest request) {
        AuthTokenDetails authTokenDetails = (AuthTokenDetails) request.getAttribute("authTokenDetails");
        if (!PermissionUtility.hasPermission(authTokenDetails, "EMPLOYEE_CREATE")) {
            throw new UnauthorizedAccessException("Access denied");
        }
        EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
        return ResponseEntity.ok(createdEmployee);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(HttpServletRequest request) {
        AuthTokenDetails authTokenDetails = (AuthTokenDetails) request.getAttribute("authTokenDetails");
        if (!PermissionUtility.hasPermission(authTokenDetails, "EMPLOYEE_READ")) {
            throw new UnauthorizedAccessException("Access denied");
        }
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable String id, HttpServletRequest request) {
        AuthTokenDetails authTokenDetails = (AuthTokenDetails) request.getAttribute("authTokenDetails");
        if (!PermissionUtility.hasPermission(authTokenDetails, "EMPLOYEE_READ")) {
            throw new UnauthorizedAccessException("Access denied");
        }
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable String id, @RequestBody EmployeeDTO employeeDetails, HttpServletRequest request) {
        AuthTokenDetails authTokenDetails = (AuthTokenDetails) request.getAttribute("authTokenDetails");
        if (!PermissionUtility.hasPermission(authTokenDetails, "EMPLOYEE_UPDATE")) {
            throw new UnauthorizedAccessException("Access denied");
        }
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String id, HttpServletRequest request) {
        AuthTokenDetails authTokenDetails = (AuthTokenDetails) request.getAttribute("authTokenDetails");
        if (!PermissionUtility.hasPermission(authTokenDetails, "EMPLOYEE_DELETE")) {
            throw new UnauthorizedAccessException("Access denied");
        }
        boolean isDeleted = employeeService.deleteEmployee(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

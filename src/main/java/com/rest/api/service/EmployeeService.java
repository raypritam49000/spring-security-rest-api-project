package com.rest.api.service;

import com.rest.api.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    public List<EmployeeDTO> getAllEmployees();

    public EmployeeDTO getEmployeeById(String id);

    public EmployeeDTO updateEmployee(String id, EmployeeDTO employeeDetails);

    public boolean deleteEmployee(String id);
}

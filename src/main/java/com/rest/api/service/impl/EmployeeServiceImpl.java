package com.rest.api.service.impl;

import com.rest.api.dto.EmployeeDTO;
import com.rest.api.exception.ResourceNotFoundException;
import com.rest.api.mappers.EmployeeMapper;
import com.rest.api.model.Employee;
import com.rest.api.repository.EmployeeRepository;
import com.rest.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        return EmployeeMapper.INSTANCE.toDto(employeeRepository.save(EmployeeMapper.INSTANCE.toEntity(employeeDTO)));
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return EmployeeMapper.INSTANCE.toDtoList(employeeRepository.findAll());
    }

    @Override
    public EmployeeDTO getEmployeeById(String id) {
        return EmployeeMapper.INSTANCE.toDto(employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with given id : " + id)));
    }

    @Override
    public EmployeeDTO updateEmployee(String id, EmployeeDTO employeeDetails) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with given id : " + id));

        existingEmployee.setFirstName(employeeDetails.getFirstName());
        existingEmployee.setLastName(employeeDetails.getLastName());
        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setContactNo(employeeDetails.getContactNo());
        existingEmployee.setSalary(employeeDetails.getSalary());
        existingEmployee.setGender(employeeDetails.getGender());
        existingEmployee.setFatherName(employeeDetails.getFatherName());
        existingEmployee.setMotherName(employeeDetails.getMotherName());

        return EmployeeMapper.INSTANCE.toDto(employeeRepository.save(existingEmployee));
    }

    @Override
    public boolean deleteEmployee(String id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

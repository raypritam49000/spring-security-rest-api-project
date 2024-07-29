package com.rest.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNo;
    private double salary;
    private String gender;
    private String fatherName;
    private String motherName;
}

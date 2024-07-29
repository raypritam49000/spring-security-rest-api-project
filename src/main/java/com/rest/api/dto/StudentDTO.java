package com.rest.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNo;
    private double salary;
    private String gender;
    private String fatherName;
    private String motherName;
    private LocalDateTime createdAt;
}

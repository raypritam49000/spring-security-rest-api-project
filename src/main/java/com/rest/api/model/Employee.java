package com.rest.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "employee",
        uniqueConstraints = @UniqueConstraint(columnNames = "email"),
        indexes = {
                @Index(columnList = "firstName"),
                @Index(name = "fn_index", columnList = "firstName"),
                @Index(name = "mulitIndex1", columnList = "firstName, lastName"),
                @Index(name = "mulitIndex2", columnList = "lastName, firstName"),
                @Index(name = "mulitSortIndex", columnList = "firstName, lastName DESC"),
                @Index(name = "uniqueIndex", columnList = "firstName", unique = true),
                @Index(name = "uniqueMulitIndex", columnList = "firstName, lastName", unique = true)
        })
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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

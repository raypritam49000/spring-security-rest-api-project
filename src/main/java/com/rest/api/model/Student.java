package com.rest.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
@CompoundIndexes({
        @CompoundIndex(name = "name_idx", def = "{'firstName': 1, 'lastName': 1}")
})
public class Student {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    @TextIndexed
    private String contactNo;
    private double salary;
    private String gender;
    @GeoSpatialIndexed
    private String fatherName;
    private String motherName;
    @Indexed(expireAfterSeconds = 3600)
    private LocalDateTime createdAt;
}

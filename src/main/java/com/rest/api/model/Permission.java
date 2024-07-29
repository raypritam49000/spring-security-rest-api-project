package com.rest.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
}

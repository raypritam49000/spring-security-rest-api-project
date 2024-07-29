package com.rest.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    private String password;
    private String email;
    private String contactNo;
    private String city;
    private String state;
    private String country;
    public boolean isAccountNonExpired;
    public boolean isAccountNonLocked;
    public boolean isCredentialsNonExpired;
    public boolean isEnabled;
    public boolean verified;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role_tb",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

}

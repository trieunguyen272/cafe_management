package com.project.cafe_management_system.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="users_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "userRole")
    private List<User> users;

}

package com.project.cafe_management_system.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="identifies")
public class Identify {

    @Id
    private String username;

    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "identify")
    private User user;
}

package com.project.cafe_management_system.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "identify_id", nullable = false)
    private Identify identify;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "date_of_birth")
    private Data dateOfBirth;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_role_id", nullable = false)
    private UserRole userRole;

    @Column(name = "status")
    private Enum status;




}

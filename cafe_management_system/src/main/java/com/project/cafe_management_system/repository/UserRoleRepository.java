package com.project.cafe_management_system.repository;

import com.project.cafe_management_system.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}

package com.project.cafe_management_system.repository;

import com.project.cafe_management_system.model.Identify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public interface IdentifyRepository extends JpaRepository<Identify, String> {
    @Query("SELECT s FROM Identify s WHERE s.username = ?1")
    Identify findIdentifyByUsername(String username);

    @Query("SELECT s FROM Identify s WHERE s.username = ?1")
    Optional<Identify> findIdentifyByIdentifyName(String username);
}

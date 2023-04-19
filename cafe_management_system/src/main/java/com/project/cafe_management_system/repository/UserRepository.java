package com.project.cafe_management_system.repository;

import com.project.cafe_management_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("SELECT s FROM User s WHERE s.identifyId = ?1")
//    User findUserByUsername(String username);

//    @Query("SELECT s FROM User s WHERE s.identifyId = :username")
//    User findUserByUsername(@Param("username") String username);

//    @Query("SELECT s FROM User s WHERE s.identifyId = ?1")
//    Optional<User> findUserByUsername(String username);
}

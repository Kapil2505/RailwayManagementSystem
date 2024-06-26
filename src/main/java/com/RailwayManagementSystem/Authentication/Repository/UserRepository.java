package com.RailwayManagementSystem.Authentication.Repository;

import com.RailwayManagementSystem.Authentication.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public  interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User>findByUsernameOrEmail(String username,String email);
    Optional<User>findByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);

    Boolean existsByUsernameOrEmail(String username,String email);
}

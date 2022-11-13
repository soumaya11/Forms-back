package com.example.pfe.repositories;

import com.example.pfe.Models.Role;
import com.example.pfe.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
 
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
	User getById(Integer id);
	Optional <List<User>> findByUsernameContaining(String username);

    List<User> findByRoles (Role role_admin);
}
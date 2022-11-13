package com.example.pfe.repositories;


import com.example.pfe.Models.Role;
import com.example.pfe.Models.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
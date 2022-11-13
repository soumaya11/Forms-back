package com.example.pfe.repositories;

import com.example.pfe.Models.Enquette;
import com.example.pfe.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnquetteRepository extends JpaRepository<Enquette, Long> {
    List<Enquette> findByUser(User user);
}

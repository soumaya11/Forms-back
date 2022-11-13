package com.example.pfe.repositories;

import com.example.pfe.Models.Enquette;
import com.example.pfe.Models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question , Long> {
    List<Question> findByEnquette(Enquette e);
}

package com.example.pfe.repositories;

import com.example.pfe.Models.Question;
import com.example.pfe.Models.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseRepository  extends JpaRepository<Response , Long> {
    List<Response> findByQuestion(Question q);
}

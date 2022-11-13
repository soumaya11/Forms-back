package com.example.pfe.services;

import com.example.pfe.Models.Enquette;

import com.example.pfe.Models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EnquetteService {

      Enquette create(Enquette e);
      List<Enquette> getAll();
      ResponseEntity<?> getById(Long id);
      List<Enquette> getByUser(User user);


    ResponseEntity<?> delete(Long id);

    ResponseEntity<?> getQuestions(Long id);

    ResponseEntity<?> deleteQuestion(Long id, Long idq);
}

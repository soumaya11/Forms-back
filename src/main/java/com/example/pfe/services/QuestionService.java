package com.example.pfe.services;

import com.example.pfe.Models.Enquette;
import com.example.pfe.Models.Question;
import com.example.pfe.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {

    public ResponseEntity<?> create(Long id, Question q);

    public List<Question> getByEnquette(Enquette e);

    public ResponseEntity<?> delete(Long id) throws NotFoundException;

    ResponseEntity<?> changeStatus(Long id) throws NotFoundException;
}

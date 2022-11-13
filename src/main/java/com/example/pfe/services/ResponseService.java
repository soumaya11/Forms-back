package com.example.pfe.services;


import com.example.pfe.Models.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResponseService {
    public ResponseEntity<?> create(Long id ,Response r);
    public List<Response>  getAll();
    public ResponseEntity<?> getById(Long id);
    public ResponseEntity<?>getByQuestion(Long q);
}

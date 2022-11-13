package com.example.pfe.controller;


import com.example.pfe.exceptions.NotFoundException;
import com.example.pfe.services.QuestionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Transactional
@RestController
@RequestMapping("api/question")
@CrossOrigin(origins = "*")
public class QuestionController {

    @Autowired
    QuestionServiceImp service ;


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws NotFoundException {
        return this.service.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable("id") Long id) throws NotFoundException {
        return this.service.changeStatus(id);
    }
}

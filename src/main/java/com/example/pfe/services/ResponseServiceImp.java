package com.example.pfe.services;

import com.example.pfe.Models.Question;
import com.example.pfe.Models.Response;
import com.example.pfe.message.NotFoundResponse;
import com.example.pfe.repositories.QuestionRepository;
import com.example.pfe.repositories.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponseServiceImp implements ResponseService{

    @Autowired
    ResponseRepository repository;

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public ResponseEntity<?> create(Long id,Response r) {

        Optional<Question> q=  this.questionRepository.findById(id) ;
        if(q.isPresent()){
            r.setQuestion(q.get());
            Response res = this.repository.save(r);
            return new ResponseEntity<>(res,HttpStatus.OK) ;
        }else{

            return new ResponseEntity<>("question  not found", HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public List<Response> getAll() {
        return this.repository.findAll();
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
    Optional<Response> r =  this.repository.findById(id);

    if(!r.isPresent()){
           return new ResponseEntity<>(new NotFoundResponse("no response found with id : "+id),
                HttpStatus.NOT_FOUND);
    }else {
        return new ResponseEntity<>(  r , HttpStatus.OK);
    }
    }

    @Override
    public ResponseEntity<?> getByQuestion(Long id) {
        Optional<Question> q = questionRepository.findById(id);
        if(q.isPresent()){

            List<Response> l = this.repository.findByQuestion(q.get());
            return new ResponseEntity<>(l,HttpStatus.OK );
        } else {
            return  new ResponseEntity<>("no question found " , HttpStatus.NOT_FOUND);
        }

    }
}

package com.example.pfe.services;

import com.example.pfe.Models.Enquette;
import com.example.pfe.Models.Question;
import com.example.pfe.exceptions.NotFoundException;
import com.example.pfe.repositories.EnquetteRepository;
import com.example.pfe.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImp  implements QuestionService{

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    EnquetteRepository enquetteRepository;
    @Override
    public ResponseEntity<?> create(Long id, Question q) {
      Optional<Enquette> e =  this.enquetteRepository.findById(id) ;
      if(e.isPresent()){
          q.setEnquette(e.get());
          Question qes = this.questionRepository.save(q);
          return new ResponseEntity<>(qes,HttpStatus.OK) ;
      }
      return new ResponseEntity<>("enquette not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public List<Question> getByEnquette(Enquette e) {
        return this.questionRepository.findByEnquette(e);
    }

    @Override
    public ResponseEntity<?> delete(Long id) throws NotFoundException {


         Question q = this.questionRepository.findById(id).orElseThrow (()->new NotFoundException("question not found"));
            this.questionRepository.delete(q);
         return  new ResponseEntity<>("deleted " , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> changeStatus(Long id) throws NotFoundException {
        Question q = this.questionRepository.findById(id).orElseThrow(()->new NotFoundException("question not found"));
        q.setStatus(!q.getStatus());
        return  new ResponseEntity<>(q,HttpStatus.OK);
    }
}

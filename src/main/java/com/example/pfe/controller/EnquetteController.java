package com.example.pfe.controller;

import com.example.pfe.Models.Enquette;
import com.example.pfe.Models.Question;
import com.example.pfe.Models.Response;
import com.example.pfe.Models.User;
import com.example.pfe.repositories.UserRepo;
import com.example.pfe.exceptions.NotFoundException;
import com.example.pfe.services.EnquetteServiceImp;
import com.example.pfe.services.QuestionServiceImp;
import com.example.pfe.services.ResponseService;
import com.example.pfe.services.ResponseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/enquette")

@CrossOrigin(origins = "http://localhost:4200")
public class EnquetteController {

    @Autowired
    EnquetteServiceImp service ;

    @Autowired
    QuestionServiceImp questionService;

    @Autowired
    ResponseServiceImp responseService;

    @Autowired
    UserRepo userRepo;


    @PostMapping()
    public Enquette save(@RequestBody() Enquette e){
        System.out.println(e);
    return  this.service.create(e);
    }

    @GetMapping()
    public List<Enquette> getAll(){
    return this.service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id")Long id)  {
        return  this.service.getById(id) ;
    }

    @GetMapping("/user/{id}")
    public List<Enquette> getByUser(@PathVariable("id") Long uid) throws NotFoundException {
        User u = userRepo.findById(uid).orElseThrow(()->new NotFoundException("user not found"));

    return this.service.getByUser(u);
    }

    @PostMapping("/addQuestion/{id}")
    public ResponseEntity<?> addQuestion(@PathVariable("id") Long id, @RequestBody()Question q){

        return  this.questionService.create(id, q );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return  this.service.delete(id);
    }


    @GetMapping("/getQuestions/{id}")
    public ResponseEntity<?> getQuestions(@PathVariable("id") Long id){
       return this.service.getQuestions(id);
    }

    @DeleteMapping("/{id}/question/{idq}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") Long id , @PathVariable("idq") Long idq){
        return  this.service.deleteQuestion(id , idq);
    }
}

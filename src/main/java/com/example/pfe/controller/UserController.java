package com.example.pfe.controller;


import com.example.pfe.Models.Role;
import com.example.pfe.Models.RoleName;
import com.example.pfe.Models.User;
import com.example.pfe.repositories.RoleRepository;
import com.example.pfe.repositories.UserRepo;
import com.example.pfe.exceptions.NotFoundException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.*;



@Transactional
@RestController
@RequestMapping(value = "api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    UserRepo userRepository;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;
    @ApiOperation("get user with specific id")
    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable Long id ) throws Exception{
        return userRepository.findById(id).orElseThrow(()->new Exception("!!!!!"));
    }


    @ApiOperation("get all users")
    @GetMapping(value = "/all")
    public List<User> getAll(){
        return userRepository.findAll();
    }

    
    @ApiOperation("get all users number")
    @GetMapping(value = "/count")
    public ResponseEntity<?> count(){
        Long x = Long.valueOf(userRepository.findAll().size());
        HashMap<String , Long>  res= new HashMap<>();
        res.put("users" ,x );
        return   ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @ApiOperation("get admins")
    @GetMapping("/admins")
    public List<User> getAdmins(){
        Role r = roleRepository.findByName(RoleName.ROLE_ADMIN).get();
        return userRepository.findByRoles(r);
    }

    @ApiOperation("get users")
    @GetMapping("/users")
    public List<User> getUsers(){
        Role r = roleRepository.findByName(RoleName.ROLE_USER).get();
        return userRepository.findByRoles(r);
    }
    
    @ApiOperation("delete user   ")
    @DeleteMapping(value="/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable Long id) throws NotFoundException {
        User user = userRepository.findById(id).orElseThrow(()->new NotFoundException("User not found"));
        userRepository.delete(user);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted",Boolean.TRUE);
        return response;
    }

 
    @ApiOperation("update password")
    @PatchMapping("/pwdUpdate/{UID}")
    public Map<String,Boolean> updatePwd(@PathVariable Long UID, @RequestBody String pwd) throws NotFoundException {
    	User u = userRepository.findById(UID).orElseThrow(()->new NotFoundException("User not found"));

       u.setPassword(encoder.encode(pwd));
    	 userRepository.save(u);
    	   Map<String,Boolean> response = new HashMap<>();
           response.put("password updated "+u.getPassword(),Boolean.TRUE);
           return response;
    }

    @ApiOperation("find user with username like ")
    @GetMapping(value = "find/{username}")
    public List<User> getUser(@PathVariable String username ) throws Exception{
        return userRepository.findByUsernameContaining(username).orElseThrow(()->new Exception("!!!!!"));
    }


    @ApiOperation("update user status")
    @PutMapping("/{id}")
    public User updateStatus(@PathVariable("id") Long id){
         User u=  userRepository.findById(id).get();
         u.setStatus(!u.getStatus());
         return  userRepository.save(u);
    }


}

package com.example.pfe.controller;



import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.example.pfe.Models.Role;
import com.example.pfe.Models.RoleName;
import com.example.pfe.Models.User;
import com.example.pfe.configuration.JwtProvider;
import com.example.pfe.repositories.RoleRepository;
import com.example.pfe.repositories.UserRepo;
import com.example.pfe.message.JwtResponse;
import com.example.pfe.message.LoginForm;
import com.example.pfe.message.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 
@Transactional
@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "*")
public class AuthRestAPIs {

	
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepo userRepository;

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    RoleRepository roleRepository;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()

                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);

        Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
        User userdetails = user.get();

            return ResponseEntity.ok(new JwtResponse(jwt , userdetails));



    }
 
    @PostMapping(value = "/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
       if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>("Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>("Email is already in use!",
                    HttpStatus.BAD_REQUEST);
        }
        // Creating user's account
        User user = new User(
                signUpRequest.getName(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()) ,
                signUpRequest.getEtablissement(),
                signUpRequest.getTel(),
                signUpRequest.getStatus()
                );
        System.out.println("*******************************");

        System.out.println(user);

        System.out.println("*******************************");
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch(role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Role not found."));
                    roles.add(adminRole);
                    break;

                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Role not found."));
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(user);
    }
}

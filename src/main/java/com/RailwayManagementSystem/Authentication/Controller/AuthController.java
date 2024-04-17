package com.RailwayManagementSystem.Authentication.Controller;

import com.RailwayManagementSystem.Authentication.Entity.Role;
import com.RailwayManagementSystem.Authentication.Entity.User;
import com.RailwayManagementSystem.Authentication.PayLoad.LoginDto;
import com.RailwayManagementSystem.Authentication.PayLoad.SignupDto;
import com.RailwayManagementSystem.Authentication.Repository.RoleRepository;
import com.RailwayManagementSystem.Authentication.Repository.UserRepository;
import com.RailwayManagementSystem.Security.JWTAuthResponse;
import com.RailwayManagementSystem.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/authApi")
public class AuthController {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;


    // http://localhost:8080/authApi/SignUp/Admin
    @PostMapping("/SignUp/Admin")
    public ResponseEntity<?> signUpAdmin(@RequestBody SignupDto signupDto)
    {
        if(userRepo.existsByEmail(signupDto.getEmail()))
        {
            return new ResponseEntity<>("email exist already !"+signupDto.getEmail(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(userRepo.existsByEmail(signupDto.getUsername()))
        {
            return new ResponseEntity<>("UserName exist already !"+signupDto.getUsername(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        User user = new User();
        user.setName(signupDto.getName());
        user.setUsername(signupDto.getUsername());
        user.setEmail(signupDto.getEmail());
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(role));
        userRepo.save(user);
        return new ResponseEntity<>("Admin Registered !",HttpStatus.CREATED);
    }


    // http://localhost:8080/authApi/SignIn
    @PostMapping("/SignIn")
    public ResponseEntity<JWTAuthResponse>authenticateUser(@RequestBody LoginDto loginDto)
    {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = tokenProvider.generateToken(authenticate);
        return ResponseEntity.ok(new JWTAuthResponse(token));
    }


    // http://localhost:8080/authApi/SignUp/User
    @PostMapping("/SignUp/User")
    public  ResponseEntity<?>signUpUser(@RequestBody SignupDto signupDto)
    {
        if(userRepo.existsByEmail(signupDto.getEmail()))
        {
            return new ResponseEntity<>("email exist already !"+signupDto.getEmail(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(userRepo.existsByEmail(signupDto.getUsername()))
        {
            return new ResponseEntity<>("UserName exist already !"+signupDto.getUsername(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        User user = new User();
        user.setName(signupDto.getName());
        user.setUsername(signupDto.getUsername());
        user.setEmail(signupDto.getEmail());
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singleton(role));
        userRepo.save(user);
        return new ResponseEntity<>("user Registered !",HttpStatus.CREATED);
    }
}

package com.himusharier.springSecurityByGenuineCoder.spring_security_by_genuine_coder.controller;

import com.himusharier.springSecurityByGenuineCoder.spring_security_by_genuine_coder.model.MyUser;
import com.himusharier.springSecurityByGenuineCoder.spring_security_by_genuine_coder.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private MyUserRepository myUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register/user")
    public MyUser createUser(@RequestBody MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return myUserRepository.save(user);
    }

}

package com.himusharier.springSecurityClass.controller;

 import com.himusharier.springSecurityClass.annotation.CurrentUser;
 import com.himusharier.springSecurityClass.model.CustomUser;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.RestController;
 
 @RestController
 public class UserController {
 
     @GetMapping("/user")
     public CustomUser user(@CurrentUser CustomUser currentUser) {
         return currentUser;
     }
 }
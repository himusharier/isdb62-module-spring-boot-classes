package com.himusharier.springSecurityClass.dto;
 
 import jakarta.validation.constraints.Email;
 import jakarta.validation.constraints.NotBlank;
 import jakarta.validation.constraints.Size;
 import lombok.Getter;
 import lombok.Setter;
 
 @Getter
 @Setter
 public class RegisterRequest {
 
     @NotBlank(message = "Email cannot be blank")
     @Email(message = "Email should be valid")
     private String email;
 
     @NotBlank(message = "Password cannot be blank")
     @Size(min = 5, message = "Password must be at least 5 characters")
     private String password;
 
     private String firstName;
     private String lastName;
     private String phoneNumber;
 }
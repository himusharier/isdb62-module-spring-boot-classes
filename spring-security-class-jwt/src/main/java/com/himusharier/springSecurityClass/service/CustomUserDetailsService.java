package com.himusharier.springSecurityClass.service;

 import com.himusharier.springSecurityClass.model.CustomUser;
 import com.himusharier.springSecurityClass.model.CustomUserDetails;
 import com.himusharier.springSecurityClass.repository.CustomUserRepository;
 import org.springframework.security.core.userdetails.UserDetails;
 import org.springframework.security.core.userdetails.UserDetailsService;
 import org.springframework.security.core.userdetails.UsernameNotFoundException;
 import org.springframework.stereotype.Service;
 
 @Service
 public class CustomUserDetailsService implements UserDetailsService {
 
     private final CustomUserRepository userRepository;
 
     public CustomUserDetailsService(CustomUserRepository userRepository) {
         this.userRepository = userRepository;
     }
 
     @Override
     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         CustomUser customUser = this.userRepository.findCustomUserByEmail(username);
         if (customUser == null) {
             throw new UsernameNotFoundException("username " + username + " is not found");
         }
         return new CustomUserDetails(customUser);
     }
 
 }
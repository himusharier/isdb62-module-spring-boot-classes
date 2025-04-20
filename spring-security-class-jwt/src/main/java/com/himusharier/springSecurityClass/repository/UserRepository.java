package com.himusharier.springSecurityClass.repository;

 import com.himusharier.springSecurityClass.constants.Role;
 import com.himusharier.springSecurityClass.model.User;
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.stereotype.Repository;
 
 import java.util.List;
 import java.util.Optional;
 
 @Repository
 public interface UserRepository extends JpaRepository<User, Long> {
 
     Optional<User> findByEmail(String email);
 
     boolean existsByEmail(String email);
 
     List<User> findByRole(Role role);
 }
package com.himusharier.springSecurityByGenuineCoder.spring_security_by_genuine_coder.repository;

import com.himusharier.springSecurityByGenuineCoder.spring_security_by_genuine_coder.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {

    Optional<MyUser> findByUsername(String username);

}

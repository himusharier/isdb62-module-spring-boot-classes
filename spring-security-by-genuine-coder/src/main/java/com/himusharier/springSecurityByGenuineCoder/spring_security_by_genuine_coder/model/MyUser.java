package com.himusharier.springSecurityByGenuineCoder.spring_security_by_genuine_coder.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "my_user_by_genuine_coder")
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String role;
}

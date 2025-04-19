package com.himusharier.springSecurityClass.repository;

import com.himusharier.springSecurityClass.model.CustomUser;

public interface CustomUserRepository {
     CustomUser findCustomUserByEmail(String email);
 }
package org.isdb.StudentCRUD.dto;

import lombok.Getter;
import lombok.Setter;
import org.isdb.StudentCRUD.constants.Role;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String email;
    private Role role;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

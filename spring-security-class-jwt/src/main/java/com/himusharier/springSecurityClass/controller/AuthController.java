package com.himusharier.springSecurityClass.controller;

import com.himusharier.springSecurityClass.config.JwtTokenProvider;
import com.himusharier.springSecurityClass.constants.Role;
import com.himusharier.springSecurityClass.dto.LoginRequest;
import com.himusharier.springSecurityClass.dto.RegisterRequest;
import com.himusharier.springSecurityClass.dto.RegisterRequestV2;
import com.himusharier.springSecurityClass.dto.UserDTO;
import com.himusharier.springSecurityClass.model.CustomUserDetails;
import com.himusharier.springSecurityClass.model.User;
import com.himusharier.springSecurityClass.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody RegisterRequestV2 registerRequest //RegisterRequest
    ) {
        try {
            User user = new User(
//                    registerRequest.getEmail(),
//                    registerRequest.getPassword(),
//                    Role.STUDENT, // Default role for registration
//                    registerRequest.getFirstName(),
//                    registerRequest.getLastName(),
//                    registerRequest.getPhoneNumber()
                    registerRequest.email(),
                    registerRequest.password(),
                    Role.STUDENT, // Default role for registration
                    registerRequest.firstName(),
                    registerRequest.lastName(),
                    registerRequest.phoneNumber()
            );

            User savedUser = userService.createUser(user);

            // Create DTO to return (exclude sensitive info)
            UserDTO userDTO = new UserDTO();
            userDTO.setId(savedUser.getId());
            userDTO.setEmail(savedUser.getEmail());
            userDTO.setRole(savedUser.getRole());
            userDTO.setFirstName(savedUser.getFirstName());
            userDTO.setLastName(savedUser.getLastName());
            userDTO.setPhoneNumber(savedUser.getPhoneNumber());

            return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(HttpServletRequest request,
                                              HttpServletResponse response,
                                              @Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.createToken(authentication);

            // Get user details
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = userDetails.user();

            // Create response with token and user info
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("token", jwt);
            responseData.put("tokenType", "Bearer");

            // Add user information
            Map<String, Object> userData = new HashMap<>();
            userData.put("id", user.getId());
            userData.put("email", user.getEmail());
            userData.put("role", user.getRole());
            userData.put("firstName", user.getFirstName());
            userData.put("lastName", user.getLastName());

            responseData.put("user", userData);

            return ResponseEntity.ok(responseData);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }
    }

    // This endpoint can be called from Angular to check if a token is valid
    @GetMapping("/validate-token")
    public ResponseEntity<?> validateToken(HttpServletRequest request) {
        String jwt = getJwtFromRequest(request);

        if (jwt != null && jwtTokenProvider.validateToken(jwt)) {
            String username = jwtTokenProvider.getUsernameFromToken(jwt);
            UserDetails userDetails = userService.loadUserByUsername(username);

            // Return user information
            CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
            User user = customUserDetails.user();

            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setRole(user.getRole());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());

            return ResponseEntity.ok(userDTO);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
    }

    // Helper method to extract JWT from request
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
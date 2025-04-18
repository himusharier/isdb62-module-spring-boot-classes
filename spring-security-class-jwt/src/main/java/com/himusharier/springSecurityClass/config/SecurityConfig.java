package com.himusharier.springSecurityClass.config;

import com.himusharier.springSecurityClass.model.CustomUser;
import com.himusharier.springSecurityClass.repository.MapCustomUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http
            , JwtTokenProvider jwtTokenProvider) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // Disable CSRF for REST APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login").permitAll()
                        .requestMatchers("/api/public/**").permitAll()
                        .requestMatchers("/api/user/**").hasAnyRole("REGULAR_USER", "MANAGER", "ADMIN")
                        .requestMatchers("/api/manager/**").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return http.build();
    }
/*
    @Bean
    public UserDetailsService userDetailsService() {
        // Regular user with basic access
        UserDetails regularUser = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user123"))
//                .roles("REGULAR_USER")
                .authorities("ROLE_REGULAR_USER", "SENIOR")
                .build();

        System.out.println(regularUser.getAuthorities());

        UserDetails hemel = User.builder()
                .username("hemel")
                .password(passwordEncoder().encode("hemel123"))
//                .roles("REGULAR_USER")
                .authorities("ROLE_REGULAR_USER", "JUNIOR")
                .build();

        System.out.println(hemel.getAuthorities());

        // Manager with intermediate access
        String manager123 = passwordEncoder().encode("manager123");
        System.out.println(manager123);
        UserDetails manager = User.builder()
                .username("manager")
                .password(manager123)
                .roles("MANAGER")
                .build();

        System.out.println(manager.getAuthorities());

        // Admin with full access
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(regularUser, hemel, manager, admin);
    }
*/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    MapCustomUserRepository userRepository() {
        String rawPassword = "admin";
        String encoded = passwordEncoder().encode(rawPassword);

        CustomUser customUser1 = new CustomUser(1L, "user@example.com", encoded);
        CustomUser customUser2 = new CustomUser(1L, "user1@example.com", encoded);
        CustomUser customUser3 = new CustomUser(1L, "user2@example.com", encoded);
        CustomUser customUser4 = new CustomUser(1L, "user3@example.com", encoded);
        CustomUser customUser5 = new CustomUser(1L, "user4@example.com", encoded);

        Map<String, CustomUser> emailToCustomUser = new HashMap<>();
        emailToCustomUser.put(customUser1.getEmail(), customUser1);
        emailToCustomUser.put(customUser2.getEmail(), customUser2);
        emailToCustomUser.put(customUser3.getEmail(), customUser3);
        emailToCustomUser.put(customUser4.getEmail(), customUser4);
        emailToCustomUser.put(customUser5.getEmail(), customUser5);

        return new MapCustomUserRepository(emailToCustomUser);
    }

}
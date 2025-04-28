package com.example.onlinecoursemanagementsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.UUID;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public AuthenticationProvider authenticationProvider() {
        String password = UUID.randomUUID().toString();
        System.out.println("User Password : " + password);

        UserDetails user = User.builder()
                .username("user")
                .password("{noop}" + password)
                .roles("USER")
                .build();

        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(new InMemoryUserDetailsManager(user));
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
            authorizationManagerRequestMatcherRegistry
                    // Course
                    .requestMatchers(HttpMethod.GET, "/course", "/course/*").permitAll()
                    .requestMatchers(HttpMethod.POST, "/course").hasAnyRole("SUPER_ADMIN", "ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/course/*").hasAnyRole("SUPER_ADMIN", "ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/course/*").hasAnyRole("SUPER_ADMIN", "ADMIN")
                    // Enrollment
                    .requestMatchers(HttpMethod.GET, "/enrollment", "/enrollment/*").permitAll()
                    .requestMatchers(HttpMethod.POST, "/enrollment").hasAnyRole("SUPER_ADMIN", "ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/enrollment/*").hasAnyRole("SUPER_ADMIN", "ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/enrollment/*").hasAnyRole("SUPER_ADMIN", "ADMIN")
                    // Instructor
                    .requestMatchers(HttpMethod.GET, "/instructor", "/instructor/*").permitAll()
                    .requestMatchers(HttpMethod.POST, "/instructor").hasAnyRole("SUPER_ADMIN", "ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/instructor/*").hasAnyRole("SUPER_ADMIN", "ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/instructor/*").hasAnyRole("SUPER_ADMIN", "ADMIN")
                    // Student
                    .requestMatchers(HttpMethod.GET, "/student", "/student/*").permitAll()
                    .requestMatchers(HttpMethod.POST, "/student").hasAnyRole("SUPER_ADMIN", "ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/student/*").hasAnyRole("SUPER_ADMIN", "ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/student/*").hasAnyRole("SUPER_ADMIN", "ADMIN")

                    .anyRequest()
                    .authenticated();
        }).formLogin(Customizer.withDefaults());

        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));
        http.cors(cors -> cors.configurationSource(request -> {
            var config = new org.springframework.web.cors.CorsConfiguration();
            config.setAllowedOrigins(List.of("https://your-frontend.com"));
            config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
            config.setAllowedHeaders(List.of("*"));
            return config;
        }));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                String md5 = MD5Util.getMD5(rawPassword.toString());
                return md5.equals(encodedPassword);
            }
        };

    }

}
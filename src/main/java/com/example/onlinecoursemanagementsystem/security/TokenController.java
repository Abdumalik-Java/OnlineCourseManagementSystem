package com.example.onlinecoursemanagementsystem.security;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {

    @GetMapping({"/generate"})
    public ResponseEntity<String> generateToken() {
        JwtUtil.encode("ADMIN","ADMIN");
        return ResponseEntity.status(HttpStatus.OK).body("Token successfully generated");
    }

    @GetMapping({"/parse/{token}"})
    public HttpEntity<JwtDto> parseToken(@PathVariable("token") String token) {
        JwtDto jwtDto = JwtUtil.decode(token);
        return ResponseEntity.status(HttpStatus.OK).body(jwtDto);
    }

}
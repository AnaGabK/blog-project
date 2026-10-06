package com.anakataoka.blog.controller;

import com.anakataoka.blog.dto.request.LoginRequestDTO;
import com.anakataoka.blog.dto.response.LoginResponseDTO;
import com.anakataoka.blog.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service){
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto){
        String token = service.login(dto);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}

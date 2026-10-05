package com.anakataoka.blog.controller;


import com.anakataoka.blog.dto.request.PublicacaoRequestDTO;
import com.anakataoka.blog.dto.response.PublicacaoResponseDTO;
import com.anakataoka.blog.service.PublicacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publicacao")
public class PublicacaoController {
    private final PublicacaoService publicacaoService;

    public PublicacaoController(PublicacaoService publicacaoService){
        this.publicacaoService = publicacaoService;
    }

    @PostMapping
    public ResponseEntity<PublicacaoResponseDTO> criar(@Valid @RequestBody PublicacaoRequestDTO dto){
        PublicacaoResponseDTO response = publicacaoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<PublicacaoResponseDTO>> getAll(){
        List<PublicacaoResponseDTO> responseDTOS = publicacaoService.listar();
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacaoResponseDTO> getOne(@PathVariable(value = "id") Long id){
        PublicacaoResponseDTO response = publicacaoService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacaoResponseDTO> atualizar(@PathVariable(value = "id") Long id,
                                                           @Valid @RequestBody PublicacaoRequestDTO dto){
        PublicacaoResponseDTO response = publicacaoService.atualizar(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable(value = "id") Long id){
        publicacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

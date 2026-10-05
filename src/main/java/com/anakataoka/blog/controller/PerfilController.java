package com.anakataoka.blog.controller;

import com.anakataoka.blog.dto.request.PerfilRequestDTO;
import com.anakataoka.blog.dto.response.PerfilResponseDTO;
import com.anakataoka.blog.service.PerfilService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfil")
public class PerfilController {
    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService){
        this.perfilService = perfilService;
    }

    @PostMapping
    public ResponseEntity<PerfilResponseDTO> criar(@Valid @RequestBody PerfilRequestDTO dto){
        PerfilResponseDTO response = perfilService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<PerfilResponseDTO>> getAll(){
        return ResponseEntity.ok(perfilService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfilResponseDTO> getOne(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(perfilService.buscarPorID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerfilResponseDTO> atualizar(@PathVariable(value = "id") Long id,
                                                       @Valid @RequestBody PerfilRequestDTO dto){
        PerfilResponseDTO response = perfilService.atualizar(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable(value = "id") Long id){
        perfilService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

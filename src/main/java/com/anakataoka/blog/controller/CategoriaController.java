package com.anakataoka.blog.controller;

import com.anakataoka.blog.dto.request.CategoriaRequestDTO;
import com.anakataoka.blog.dto.response.CategoriaResponseDTO;
import com.anakataoka.blog.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    private final CategoriaService service;

    public CategoriaController(CategoriaService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> criar(@Valid @RequestBody CategoriaRequestDTO dto){
        CategoriaResponseDTO response = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> getAll(){
        List<CategoriaResponseDTO> response = service.listar();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> getOne(@PathVariable(value = "id") Long id){
        CategoriaResponseDTO response = service.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizar(@PathVariable(value = "id") Long id,
                                                          @Valid @RequestBody CategoriaRequestDTO dto){
        CategoriaResponseDTO response = service.atualizar(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

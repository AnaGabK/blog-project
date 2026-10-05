package com.anakataoka.blog.controller;

import com.anakataoka.blog.dto.request.ComentarioRequestDTO;
import com.anakataoka.blog.dto.response.ComentarioResponseDTO;
import com.anakataoka.blog.service.ComentarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {
    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService){
        this.comentarioService = comentarioService;
    }

    @PostMapping
    public ResponseEntity<ComentarioResponseDTO> criar(@Valid @RequestBody ComentarioRequestDTO dto){
        ComentarioResponseDTO responseDTO = comentarioService.criar(dto);
        return ResponseEntity.status((HttpStatus.CREATED))
                .body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentarioResponseDTO> getOne(@PathVariable(value="id") Long id){
        ComentarioResponseDTO responseDTO = comentarioService.buscarPorId(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComentarioResponseDTO> atualizar(@PathVariable(value="id") Long id,
                                                           @Valid @RequestBody ComentarioRequestDTO dto){
        ComentarioResponseDTO responseDTO = comentarioService.atualizar(id, dto);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable(value="id") Long id){
        comentarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

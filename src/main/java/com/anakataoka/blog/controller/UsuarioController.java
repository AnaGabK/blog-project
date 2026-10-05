package com.anakataoka.blog.controller;

import com.anakataoka.blog.dto.request.UsuarioRequestDTO;
import com.anakataoka.blog.dto.response.UsuarioResponseDTO;
import com.anakataoka.blog.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(@Valid @RequestBody UsuarioRequestDTO dto){
        UsuarioResponseDTO response = usuarioService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAll(){
        return ResponseEntity.ok(usuarioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getOne(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(usuarioService.buscarPorID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> update(@PathVariable(value = "id") Long id,
                                                     @Valid @RequestBody UsuarioRequestDTO dto){
        return ResponseEntity.ok(usuarioService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id){
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}

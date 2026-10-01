package com.anakataoka.blog.service;

import com.anakataoka.blog.dto.request.UsuarioRequestDTO;
import com.anakataoka.blog.dto.response.UsuarioResponseDTO;
import com.anakataoka.blog.entity.Perfil;
import com.anakataoka.blog.entity.Usuario;
import com.anakataoka.blog.mapper.UsuarioMapper;
import com.anakataoka.blog.repository.PerfilRepository;
import com.anakataoka.blog.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    private final PerfilRepository perfilRepository;

    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository repository,
            UsuarioMapper mapper,
            PerfilRepository perfilRepository,
            PasswordEncoder passwordEncoder
    ){
        this.repository = repository;
        this.mapper = mapper;
        this.perfilRepository = perfilRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //CREATE
    public UsuarioResponseDTO criar(UsuarioRequestDTO dto){
        Usuario usuario = mapper.toEntity(dto);

        if (dto.perfilIds() != null && !dto.perfilIds().isEmpty()) {
            Set<Perfil> perfis = new HashSet<>(
                    perfilRepository.findAllById(dto.perfilIds())
            );
            usuario.setPerfis(perfis);
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        usuario = repository.save(usuario);
        return mapper.toResponseDTO(usuario);
    }

    //READ ALL
    public List<UsuarioResponseDTO> listar(){
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    //READ BY ID
    public UsuarioResponseDTO buscarPorID(Long id){
        Usuario usuario = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Usuario não encontrado"));

        return mapper.toResponseDTO(usuario);
    }

    //UPDATE
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto){
        Usuario usuario = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Usuario não encontrado"));

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setImagem(dto.imagem());

        if (dto.senha() != null && !dto.senha().isBlank()) {
            usuario.setSenha(passwordEncoder.encode(dto.senha()));
        }

        Set<Perfil> perfis = new HashSet<>(
                perfilRepository.findAllById(dto.perfilIds())
        );
        usuario.setPerfis(perfis);

        repository.save(usuario);
        return mapper.toResponseDTO(usuario);
    }

    //DELETE
    public void deletar(Long id){
        Usuario usuario = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Usuário nao encontrado"));

        repository.delete(usuario);
    }

}

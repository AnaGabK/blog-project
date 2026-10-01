package com.anakataoka.blog.service;

import com.anakataoka.blog.dto.request.PerfilRequestDTO;
import com.anakataoka.blog.dto.response.PerfilResponseDTO;
import com.anakataoka.blog.entity.Perfil;
import com.anakataoka.blog.entity.Usuario;
import com.anakataoka.blog.mapper.PerfilMapper;
import com.anakataoka.blog.repository.PerfilRepository;
import com.anakataoka.blog.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PerfilService {
    private final PerfilRepository repository;
    private final PerfilMapper mapper;

    private final UsuarioRepository usuarioRepository;

    public PerfilService(
            PerfilRepository repository,
            PerfilMapper mapper,
            UsuarioRepository usuarioRepository
    ){
        this.repository = repository;
        this.mapper = mapper;
        this.usuarioRepository = usuarioRepository;
    }

    public PerfilResponseDTO criar(PerfilRequestDTO dto){
        Perfil perfil = mapper.toEntity(dto);

        if (dto.usuariosIds() != null && !dto.usuariosIds().isEmpty()) {
            Set<Usuario> usuarios = new HashSet<>(
                    usuarioRepository.findAllById(dto.usuariosIds())
            );
            perfil.setUsuarios(usuarios);
        }

        perfil.setNome(dto.nome());

        perfil = repository.save(perfil);

        return mapper.toResponseDTO(perfil);
    }

    public List<PerfilResponseDTO> listar(){
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public PerfilResponseDTO buscarPorID(Long id){
        Perfil perfil = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Perfil não encontrado"));

        return mapper.toResponseDTO(perfil);
    }

    public PerfilResponseDTO atualizar(Long id, PerfilRequestDTO dto){
        Perfil perfil = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Perfil não encontrado"));

        perfil.setNome(dto.nome());

        if (dto.usuariosIds() != null && !dto.usuariosIds().isEmpty()) {
            Set<Usuario> usuarios = new HashSet<>(
                    usuarioRepository.findAllById(dto.usuariosIds())
            );
            perfil.setUsuarios(usuarios);
        }

        repository.save(perfil);

        return mapper.toResponseDTO(perfil);
    }

    public void deletar(Long id){
        Perfil perfil = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Perfil não encontrado"));

        repository.delete(perfil);
    }
}

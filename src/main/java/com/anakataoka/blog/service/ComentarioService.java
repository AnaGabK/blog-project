package com.anakataoka.blog.service;

import com.anakataoka.blog.dto.request.ComentarioRequestDTO;
import com.anakataoka.blog.dto.response.ComentarioResponseDTO;
import com.anakataoka.blog.dto.response.PerfilResponseDTO;
import com.anakataoka.blog.entity.Comentario;
import com.anakataoka.blog.entity.Publicacao;
import com.anakataoka.blog.entity.Usuario;
import com.anakataoka.blog.mapper.ComentarioMapper;
import com.anakataoka.blog.repository.ComentarioRepository;
import com.anakataoka.blog.repository.PublicacaoRepository;
import com.anakataoka.blog.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {
    private final ComentarioRepository repository;
    private final ComentarioMapper mapper;

    private final UsuarioRepository usuarioRepository;
    private final PublicacaoRepository publicacaoRepository;

    public ComentarioService(ComentarioRepository repository,
                             ComentarioMapper mapper,
                             UsuarioRepository usuarioRepository,
                             PublicacaoRepository publicacaoRepository){
        this.repository = repository;
        this.mapper = mapper;
        this.usuarioRepository = usuarioRepository;
        this.publicacaoRepository = publicacaoRepository;
    }

    public ComentarioResponseDTO criar(ComentarioRequestDTO dto){
        Comentario comentario = mapper.toEntity(dto);

        if (dto.usuarioId() != null){
            Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado!"));
            comentario.setUsuario(usuario);
        }
        if(dto.publicacaoId() != null){
            Publicacao publicacao = publicacaoRepository.findById(dto.publicacaoId())
                    .orElseThrow(() -> new EntityNotFoundException("Publicacao nao encontrada"));
            comentario.setPublicacao(publicacao);
        }
        comentario.setTexto(dto.texto());

        repository.save(comentario);

        return mapper.toResponseDTO(comentario);
    }

    public List<ComentarioResponseDTO> listarPorUsuario(Long id){
        return repository.findByUsuarioId(id)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public List<ComentarioResponseDTO> listarPorPublicacao(Long id){
        return repository.findByPublicacaoId(id)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public ComentarioResponseDTO buscarPorId(Long id){
        Comentario comentario = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Comentario nao encontrado"));

        return mapper.toResponseDTO(comentario);
    }

    public ComentarioResponseDTO atualizar(Long id, ComentarioRequestDTO dto){
        Comentario comentario = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comentario nao encontrado"));

        comentario.setTexto(dto.texto());

        repository.save(comentario);

        return mapper.toResponseDTO(comentario);
    }

    public void deletar(Long id){
        Comentario comentario = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comentario nao encontrado"));

        repository.delete(comentario);
    }

}

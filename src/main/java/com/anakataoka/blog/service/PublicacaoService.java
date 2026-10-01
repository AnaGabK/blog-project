package com.anakataoka.blog.service;

import com.anakataoka.blog.dto.request.PublicacaoRequestDTO;
import com.anakataoka.blog.dto.response.PublicacaoResponseDTO;
import com.anakataoka.blog.entity.Categoria;
import com.anakataoka.blog.entity.Publicacao;
import com.anakataoka.blog.entity.Usuario;
import com.anakataoka.blog.mapper.PublicacaoMapper;
import com.anakataoka.blog.repository.CategoriaRepository;
import com.anakataoka.blog.repository.PublicacaoRepository;
import com.anakataoka.blog.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PublicacaoService {
    private final PublicacaoRepository repository;
    private final PublicacaoMapper mapper;

    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;

    public PublicacaoService(
            PublicacaoRepository repository,
            PublicacaoMapper mapper,
            CategoriaRepository categoriaRepository,
            UsuarioRepository usuarioRepository
    ){
        this.repository = repository;
        this.mapper = mapper;
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public PublicacaoResponseDTO criar(PublicacaoRequestDTO dto){
        Publicacao publicacao = mapper.toEntity(dto);

        if(dto.categoriasId() != null && !dto.categoriasId().isEmpty()){
            Set<Categoria> categoriaSet = new HashSet<>(
                categoriaRepository.findAllById(dto.categoriasId())
            );
            publicacao.setCategorias(categoriaSet);
        }
        if(dto.usuarioId() != null){
            Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
            publicacao.setUsuario(usuario);
        }

        repository.save(publicacao);
        return mapper.toResposeDTO(publicacao);
    }

    public List<PublicacaoResponseDTO> listar(){
        return repository.findAll()
                .stream()
                .map(mapper::toResposeDTO)
                .toList();
    }

    public List<PublicacaoResponseDTO> listarPorUsuario(Long id){
        return repository.findByUsuarioId(id)
                .stream()
                .map(mapper::toResposeDTO)
                .toList();
    }

    public PublicacaoResponseDTO buscarPorId(Long id){
        Publicacao publicacao = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Publicacao nao encontrada"));

        return mapper.toResposeDTO(publicacao);
    }

    public PublicacaoResponseDTO atualizar(Long id, PublicacaoRequestDTO dto){
        Publicacao publicacao = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Publicacao nao encontrada"));

        publicacao.setTitulo(dto.titulo());
        publicacao.setDescricao(dto.descricao());
        publicacao.setImagem(dto.imagem());

        if (dto.categoriasId() != null && !dto.categoriasId().isEmpty()) {
            Set<Categoria> categorias = new HashSet<>(
                    categoriaRepository.findAllById(dto.categoriasId())
            );
            publicacao.setCategorias(categorias);
        }

        repository.save(publicacao);
        return mapper.toResposeDTO(publicacao);
    }

    public void deletar(Long id){
        Publicacao publicacao = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Publicacao nao encontrada"));

        repository.delete(publicacao);
    }
}

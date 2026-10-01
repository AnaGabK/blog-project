package com.anakataoka.blog.service;

import com.anakataoka.blog.dto.request.CategoriaRequestDTO;
import com.anakataoka.blog.dto.response.CategoriaResponseDTO;
import com.anakataoka.blog.entity.Categoria;
import com.anakataoka.blog.entity.Publicacao;
import com.anakataoka.blog.mapper.CategoriaMapper;
import com.anakataoka.blog.repository.CategoriaRepository;
import com.anakataoka.blog.repository.PublicacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoriaService {
    private final CategoriaRepository repository;
    private final CategoriaMapper mapper;

    private final PublicacaoRepository publicacaoRepository;

    public CategoriaService(CategoriaRepository repository,
                            CategoriaMapper mapper,
                            PublicacaoRepository publicacaoRepository){
        this.repository = repository;
        this.mapper = mapper;
        this.publicacaoRepository = publicacaoRepository;
    }

    public CategoriaResponseDTO criar(CategoriaRequestDTO dto){
        Categoria categoria = mapper.toEntity(dto);

        if(dto.publicacoesId() != null && !dto.publicacoesId().isEmpty()){
            Set<Publicacao> publicacoes = new HashSet<>(
                    publicacaoRepository.findAllById(dto.publicacoesId())
            );
            categoria.setPublicacoes(publicacoes);
        }
        repository.save(categoria);

        return mapper.toResponseDTO(categoria);
    }

    public List<CategoriaResponseDTO> listar(){
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public CategoriaResponseDTO buscarPorId(Long id){
        Categoria categoria = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Categoria não encontrada"));
        return mapper.toResponseDTO(categoria);
    }

    public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO dto){
        Categoria categoria = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Categoria não encontrada"));

        categoria.setTag(dto.tag());

        if(dto.publicacoesId() != null && !dto.publicacoesId().isEmpty()){
            Set<Publicacao> publicacoes = new HashSet<>(
                    publicacaoRepository.findAllById(dto.publicacoesId())
            );
            categoria.setPublicacoes(publicacoes);
        }

        repository.save(categoria);
        return mapper.toResponseDTO(categoria);
    }


    public void deletar(Long id){
        Categoria categoria = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Categoria não encontrada"));

        repository.delete(categoria);
    }
}

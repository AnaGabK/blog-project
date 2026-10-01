package com.anakataoka.blog.mapper;

import com.anakataoka.blog.dto.request.PublicacaoRequestDTO;
import com.anakataoka.blog.dto.response.PublicacaoResponseDTO;
import com.anakataoka.blog.entity.Categoria;
import com.anakataoka.blog.entity.Publicacao;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PublicacaoMapper {
    public Publicacao toEntity(PublicacaoRequestDTO dto){
        if (dto == null){
            return null;
        }
        Publicacao publicacao = new Publicacao();
        publicacao.setTitulo(dto.titulo());
        publicacao.setDescricao(dto.descricao());
        publicacao.setImagem(dto.imagem());

        return publicacao;
    }

    public PublicacaoResponseDTO toResposeDTO(Publicacao publicacao){
        return new PublicacaoResponseDTO(
                publicacao.getId(),
                publicacao.getTitulo(),
                publicacao.getDescricao(),
                publicacao.getImagem(),
                publicacao.getDataCriacao(),
                publicacao.getUsuario().getId(),
                toCategoriaIds(publicacao.getCategorias())
        );
    }

    private Set<Long> toCategoriaIds(Set<Categoria> categorias){
        if(categorias == null){
            return Collections.emptySet();
        }
        return categorias.stream()
                .map(Categoria::getId)
                .collect(Collectors.toSet());
    }
}

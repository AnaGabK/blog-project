package com.anakataoka.blog.mapper;

import com.anakataoka.blog.dto.request.CategoriaRequestDTO;
import com.anakataoka.blog.dto.response.CategoriaResponseDTO;
import com.anakataoka.blog.entity.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {
    public Categoria toEntity(CategoriaRequestDTO dto){
        if(dto == null){
            return null;
        }

        Categoria categoria = new Categoria();
        categoria.setTag(dto.tag());

        return categoria;
    }

    public CategoriaResponseDTO toResponseDTO(Categoria categoria){
        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getTag()
        );
    }
}

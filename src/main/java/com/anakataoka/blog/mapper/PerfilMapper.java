package com.anakataoka.blog.mapper;

import com.anakataoka.blog.dto.request.PerfilRequestDTO;
import com.anakataoka.blog.dto.response.PerfilResponseDTO;
import com.anakataoka.blog.entity.Perfil;
import org.springframework.stereotype.Component;

@Component
public class PerfilMapper {

    public Perfil toEntity(PerfilRequestDTO dto){
        if(dto == null){
            return null;
        }

        Perfil perfil = new Perfil();

        perfil.setNome(dto.nome());

        return perfil;
    }

    public PerfilResponseDTO toResponseDTO(Perfil perfil){
        return new PerfilResponseDTO(
                perfil.getId(),
                perfil.getNome()
        );
    }
}

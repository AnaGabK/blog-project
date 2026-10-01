package com.anakataoka.blog.repository;

import com.anakataoka.blog.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByUsuarioId(Long id);

    List<Comentario> findByPublicacaoId(Long id);
}

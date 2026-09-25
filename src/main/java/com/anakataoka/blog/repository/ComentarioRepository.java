package com.anakataoka.blog.repository;

import com.anakataoka.blog.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}

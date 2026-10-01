package com.anakataoka.blog.repository;

import com.anakataoka.blog.entity.Publicacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {
    List<Publicacao> findByUsuarioId(Long id);
}

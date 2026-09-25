package com.anakataoka.blog.repository;

import com.anakataoka.blog.entity.Publicacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {
}

package com.anakataoka.blog.auth;

import com.anakataoka.blog.entity.Perfil;
import com.anakataoka.blog.entity.Usuario;
import com.anakataoka.blog.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailService implements UserDetailsService {
    private final UsuarioRepository repository;

    public UsuarioDetailService(UsuarioRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        return User.withUsername(usuario.getEmail())
                .password(usuario.getSenha())
                .authorities(
                        usuario.getPerfis()
                                .stream()
                                .map(Perfil::getNome)
                                .toArray(String[]::new)
                ).build();
    }
}

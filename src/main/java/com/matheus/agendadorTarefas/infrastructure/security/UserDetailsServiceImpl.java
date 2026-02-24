package com.matheus.agendadorTarefas.infrastructure.security;

import com.matheus.agendadorTarefas.business.dto.UsuarioDTO;
import com.matheus.agendadorTarefas.infrastructure.client.UsuarioClient;
import com.matheus.agendadorTarefas.infrastructure.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
//O Spring Security sabe validar senhas, mas ele não sabe onde você guardou seus usuários. Esta classe faz essa ponte.
public class UserDetailsServiceImpl {

    // Repositório para acessar dados de usuário no banco de dados
    @Autowired
    private UsuarioClient client;


    public UserDetails carregaDadosUsuario(String email, String token) {

        UsuarioDTO usuarioDTO = client.buscaUsuario(email, token);
        return User
                .withUsername(usuarioDTO.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuarioDTO.getSenha()) // Define a senha do usuário
                .build();
    }

}

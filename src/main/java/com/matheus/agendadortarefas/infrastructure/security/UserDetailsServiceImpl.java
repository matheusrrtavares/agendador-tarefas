package com.matheus.agendadortarefas.infrastructure.security;



import com.matheus.agendadortarefas.business.dto.UsuarioDTO;
import com.matheus.agendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl {

    private final UsuarioClient client;

    public UserDetails carregaDadosUsuario(String email, String token) {

        UsuarioDTO usuarioDTO = client.buscaUsuarioPorEmail(email, token);
        return User
                .withUsername(usuarioDTO.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuarioDTO.getSenha()) // Define a senha do usuário
                .build();
    }

}

package com.matheus.agendadortarefas.business;

import com.matheus.agendadortarefas.business.dto.TarefaDTO;
import com.matheus.agendadortarefas.business.mapper.TarefaConverter;
import com.matheus.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.matheus.agendadortarefas.infrastructure.enums.StatusNotificacao;
import com.matheus.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.matheus.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefasRepository repository;
    private final TarefaConverter converter;
    private final JwtUtil jwtUtil;

    public TarefaDTO salvarTarefa(TarefaDTO dto, String token) {

        String email = jwtUtil.extractUsername(token.substring(7));

        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacao(StatusNotificacao.PENDENTE);
        dto.setEmailUsuario(email);

        TarefasEntity entity = converter.paraTarefaEntity(dto);

        return converter.paraTarefaDTO(
                repository.save(entity));
    }

}

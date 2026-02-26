package com.matheus.agendadorTarefas.business.service;

import com.matheus.agendadorTarefas.business.converter.TarefasConverter;
import com.matheus.agendadorTarefas.business.dto.TarefasDTO;
import com.matheus.agendadorTarefas.infrastructure.entity.TarefasEntity;
import com.matheus.agendadorTarefas.infrastructure.enums.StatusNotificacao;
import com.matheus.agendadorTarefas.infrastructure.repository.TarefasRepository;
import com.matheus.agendadorTarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefasConverter;
    private final JwtUtil jwtUtil;


    public TarefasDTO agendaTarefa (String token, TarefasDTO tarefasDTO) {
        String email = jwtUtil.extractUsername(token.substring(7));

        tarefasDTO.setDataCriacao(LocalDateTime.now());
        tarefasDTO.setStatusNotificacao(StatusNotificacao.PENDENTE);
        tarefasDTO.setEmailUsuario(email);
        TarefasEntity entity = tarefasConverter.paraTarefasEntity(tarefasDTO);

        return tarefasConverter.paraTarefasDTO(
                tarefasRepository.save(entity));
    }
}

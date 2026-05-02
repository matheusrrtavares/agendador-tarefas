package com.matheus.agendadortarefas.business;

import com.matheus.agendadortarefas.business.dto.TarefaDTO;
import com.matheus.agendadortarefas.business.mapper.TarefaConverter;
import com.matheus.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.matheus.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.matheus.agendadortarefas.infrastructure.enums.StatusNotificacao;
import com.matheus.agendadortarefas.infrastructure.exception.ResourceNotFoundException;
import com.matheus.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.matheus.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefasRepository repository;
    private final TarefaConverter converter;
    private final TarefaUpdateConverter updateConverter;
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

    public List<TarefaDTO> buscaTarefasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {

        return converter.paraListaTarefasDTO(
                repository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefaDTO> buscaTarefasPorEmail(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));

        return converter.paraListaTarefasDTO(repository.findByEmailUsuario(email));
    }

    public void deletaTarefaPorId(String id) {
        try {
            repository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id inexistente: " +
                    e.getCause());
        }
    }

    public TarefaDTO alteraStatusTarefa(StatusNotificacao statusNotificacao, String id) {

        try {
            TarefasEntity entity = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada: " + id));
            entity.setStatusNotificacao(statusNotificacao);
            return converter.paraTarefaDTO(repository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Tarefa não encontrada: " + e.getCause());
        }

    }

    public TarefaDTO updateTarefas(TarefaDTO dto, String id) {

        try {
            TarefasEntity entity = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada"));

            updateConverter.updateTarefas(dto, entity);
            return converter.paraTarefaDTO(repository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Tarefa não encontrada: " + e.getCause());
        }

    }

}

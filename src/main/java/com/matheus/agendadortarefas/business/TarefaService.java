package com.matheus.agendadortarefas.business;

import com.matheus.agendadortarefas.business.dto.TarefasDTORecord;
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

    public TarefasDTORecord salvarTarefa(TarefasDTORecord dto, String token) {

        String email = jwtUtil.extractUsername(token.substring(7));
        TarefasDTORecord dtoFinal = new TarefasDTORecord(
                null, dto.nomeTarefa(),
                dto.descricao(), LocalDateTime.now(),
                dto.dataEvento(), dto.emailUsuario(),
                null, StatusNotificacao.PENDENTE);

        TarefasEntity entity = converter.paraTarefaEntity(dtoFinal  );

        return converter.paraTarefasDTORecord(
                repository.save(entity));
    }

    public List<TarefasDTORecord> buscaTarefasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {

        return converter.paraListaTarefasDTO(
                repository.findByDataEventoBetweenAndStatusNotificacao(dataInicial, dataFinal, StatusNotificacao.PENDENTE));
    }

    public List<TarefasDTORecord> buscaTarefasPorEmail(String token) {
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

    public TarefasDTORecord alteraStatusTarefa(StatusNotificacao statusNotificacao, String id) {

        try {
            TarefasEntity entity = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada: " + id));
            entity.setStatusNotificacao(statusNotificacao);
            return converter.paraTarefasDTORecord(repository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Tarefa não encontrada: " + e.getCause());
        }

    }

    public TarefasDTORecord updateTarefas(TarefasDTORecord dto, String id) {

        try {
            TarefasEntity entity = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada"));

            updateConverter.updateTarefas(dto, entity);
            return converter.paraTarefasDTORecord(repository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Tarefa não encontrada: " + e.getCause());
        }

    }

}

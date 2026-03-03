package com.matheus.agendadorTarefas.business.converter;

import com.matheus.agendadorTarefas.business.dto.TarefasDTO;
import com.matheus.agendadorTarefas.infrastructure.entity.TarefasEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TarefasConverter {

    public TarefasEntity paraTarefasEntity(TarefasDTO tarefasDTO) {
        return TarefasEntity.builder()
                .id(tarefasDTO.getId())
                .nomeTarefa(tarefasDTO.getNomeTarefa())
                .dataCriacao(tarefasDTO.getDataCriacao())
                .dataEvento(tarefasDTO.getDataEvento())
                .emailUsuario(tarefasDTO.getEmailUsuario())
                .dataAlteracao(tarefasDTO.getDataAlteracao())
                .statusNotificacao(tarefasDTO.getStatusNotificacao())
                .descricao(tarefasDTO.getDescricao())
                .build();
    }

    public TarefasDTO paraTarefasDTO(TarefasEntity tarefasEntity) {
        return TarefasDTO.builder()
                .id(tarefasEntity.getId())
                .nomeTarefa(tarefasEntity.getNomeTarefa())
                .dataCriacao(tarefasEntity.getDataCriacao())
                .dataEvento(tarefasEntity.getDataEvento())
                .emailUsuario(tarefasEntity.getEmailUsuario())
                .dataAlteracao(tarefasEntity.getDataAlteracao())
                .statusNotificacao(tarefasEntity.getStatusNotificacao())
                .descricao(tarefasEntity.getDescricao())
                .build();
    }

    public List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTO> tarefasDTOS){
        return tarefasDTOS.stream().map(this :: paraTarefasEntity).toList();
    }

    public List<TarefasDTO> paraListaTarefasDTO(List<TarefasEntity> tarefasEntities){
        return tarefasEntities.stream().map(this :: paraTarefasDTO).toList();
    }
}

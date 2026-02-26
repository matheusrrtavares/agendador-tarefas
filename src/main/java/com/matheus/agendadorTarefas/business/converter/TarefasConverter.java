package com.matheus.agendadorTarefas.business.converter;

import com.matheus.agendadorTarefas.business.dto.TarefasDTO;
import com.matheus.agendadorTarefas.infrastructure.entity.TarefasEntity;
import org.springframework.stereotype.Component;

@Component
public class TarefasConverter {

    public TarefasEntity paraTarefasEntity(TarefasDTO tarefasDTO) {
        return TarefasEntity.builder()
                .id(tarefasDTO.getId())
                .nomeTarefa(tarefasDTO.getNomeTarefa())
                .dataCriacao(tarefasDTO.getDataCriacao())
                .dataEvento(tarefasDTO.getDataEvento())
                .emailUsuario(tarefasDTO.getEmailUsuario())
                .horaAlteracao(tarefasDTO.getHoraAltereacao())
                .statusNotificacao(tarefasDTO.getStatusNotificacao())
                .descricao(tarefasDTO.getDescrição())
                .build();
    }

    public TarefasDTO paraTarefasDTO(TarefasEntity tarefasEntity) {
        return TarefasDTO.builder()
                .id(tarefasEntity.getId())
                .nomeTarefa(tarefasEntity.getNomeTarefa())
                .dataCriacao(tarefasEntity.getDataCriacao())
                .dataEvento(tarefasEntity.getDataEvento())
                .emailUsuario(tarefasEntity.getEmailUsuario())
                .horaAltereacao(tarefasEntity.getHoraAlteracao())
                .statusNotificacao(tarefasEntity.getStatusNotificacao())
                .descrição(tarefasEntity.getDescricao())
                .build();
    }
}

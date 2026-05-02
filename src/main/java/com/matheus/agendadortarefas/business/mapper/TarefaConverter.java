package com.matheus.agendadortarefas.business.mapper;

import com.matheus.agendadortarefas.business.dto.TarefaDTO;
import com.matheus.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    TarefasEntity paraTarefaEntity(TarefaDTO dto);

    TarefaDTO paraTarefaDTO(TarefasEntity entity);

}

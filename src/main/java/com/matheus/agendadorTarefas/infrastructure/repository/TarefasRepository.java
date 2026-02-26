package com.matheus.agendadorTarefas.infrastructure.repository;

import com.matheus.agendadorTarefas.infrastructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {
}

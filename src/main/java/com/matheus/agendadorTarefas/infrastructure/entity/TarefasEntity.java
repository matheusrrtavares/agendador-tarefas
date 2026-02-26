package com.matheus.agendadorTarefas.infrastructure.entity;

import com.matheus.agendadorTarefas.infrastructure.enums.StatusNotificacao;
import lombok.*;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("tarefas")
public class TarefasEntity {

    //Mongo gera automaticamente os IDs
    @Id
    private String id;
    private String nomeTarefa;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEvento;
    private Local horaAlteracao;
    private String emailUsuario;
    private StatusNotificacao statusNotificacao;



}

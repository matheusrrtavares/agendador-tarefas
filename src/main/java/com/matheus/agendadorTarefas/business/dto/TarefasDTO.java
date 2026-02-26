package com.matheus.agendadorTarefas.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.matheus.agendadorTarefas.infrastructure.enums.StatusNotificacao;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefasDTO {


    private String id;
    private String nomeTarefa;
    private String descrição;
    private LocalDateTime dataCriacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;
    private Local horaAltereacao;
    private String emailUsuario;
    private StatusNotificacao statusNotificacao;
}

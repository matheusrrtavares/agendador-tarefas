package com.matheus.agendadortarefas.controller;

import com.matheus.agendadortarefas.business.TarefaService;
import com.matheus.agendadortarefas.business.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefaService service;

    @PostMapping
    public ResponseEntity<TarefaDTO> salvaTarefa(@RequestBody TarefaDTO dto,
                                                 @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(service.salvarTarefa(dto, token));
    }

}

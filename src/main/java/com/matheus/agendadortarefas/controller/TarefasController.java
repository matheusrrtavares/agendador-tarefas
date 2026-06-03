package com.matheus.agendadortarefas.controller;

import com.matheus.agendadortarefas.business.TarefaService;
import com.matheus.agendadortarefas.business.dto.TarefasDTORecord;
import com.matheus.agendadortarefas.infrastructure.enums.StatusNotificacao;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefaService service;

    @PostMapping
    public ResponseEntity<TarefasDTORecord> salvaTarefa(@RequestBody TarefasDTORecord dto,
                                                 @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(service.salvarTarefa(dto, token));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTORecord>> buscaTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(service.buscaTarefasPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTORecord>> buscaTarefasPorEmail(@RequestHeader("Authorization") String token){

        return ResponseEntity.ok(service.buscaTarefasPorEmail(token));

    }

    @DeleteMapping
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id){
        service.deletaTarefaPorId(id);

        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefasDTORecord> atualizaStatusTarefa(@RequestParam("status") StatusNotificacao status,
                                                          @RequestParam("id") String id){
        return ResponseEntity.ok(service.alteraStatusTarefa(status, id));
    }

    @PutMapping
    public ResponseEntity<TarefasDTORecord> updateTarefas(@RequestBody TarefasDTORecord dto,
                                                   @RequestParam("id") String id) {
        return ResponseEntity.ok(service.updateTarefas(dto, id));
    }

}

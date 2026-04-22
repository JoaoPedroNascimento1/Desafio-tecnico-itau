package br.com.desafioItau.controller;

import br.com.desafioItau.configuration.EstatisticaProperties;
import br.com.desafioItau.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@Slf4j
@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
public class EstatisticaController {

  private final EstatisticaProperties estatisticaProperties;
  private final TransacaoRepository transacaoRepository;

  @GetMapping
  public ResponseEntity Estatistica() {
    try{

      estatisticaProperties.validar();
      int tempo = estatisticaProperties.segundos();
      log.info("Calculando as transacoes nos ultimos {} segundos", tempo);

      var horaInicial = OffsetDateTime.now().minusSeconds(estatisticaProperties.segundos());
      return ResponseEntity.ok(transacaoRepository.estatistica(horaInicial));

    } catch (IllegalArgumentException e) {
      log.error(e.getMessage());
      return ResponseEntity.badRequest().body(e.getMessage());
    }

  }
}

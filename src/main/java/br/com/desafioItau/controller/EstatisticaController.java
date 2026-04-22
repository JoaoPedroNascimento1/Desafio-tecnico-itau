package br.com.desafioItau.controller;

import br.com.desafioItau.configuration.EstatisticaProperties;
import br.com.desafioItau.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
public class EstatisticaController {

  private final EstatisticaProperties estatisticaProperties;
  private final TransacaoRepository transacaoRepository;

  @GetMapping
  public ResponseEntity Estatistica() {
    estatisticaProperties.validar();
    var horaInicial = OffsetDateTime.now().minusSeconds(estatisticaProperties.segundos());
    return ResponseEntity.ok(transacaoRepository.estatistica(horaInicial));
  }
}

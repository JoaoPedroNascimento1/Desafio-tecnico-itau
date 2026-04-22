package br.com.desafioItau.controller;

import br.com.desafioItau.dto.request.TransacaoRequest;
import br.com.desafioItau.dto.response.TransacaoResponse;
import br.com.desafioItau.repository.TransacaoRepository;
import br.com.desafioItau.service.TransacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/transacao")
@RequiredArgsConstructor
public class TransacaoController {

  private final TransacaoService transacaoService;
  private final TransacaoRepository transacaoRepository;

  @PostMapping
  public ResponseEntity<TransacaoResponse> transacao(@Valid @RequestBody TransacaoRequest transacaoRequest){
      transacaoService.validar(transacaoRequest);
      transacaoRepository.salvarDados(transacaoRequest);
      log.info("Transacao concluida com sucesso");
      return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @DeleteMapping
  public ResponseEntity<Void> deletarDados(){
    transacaoRepository.deletarDados();
    log.info("Dados deletados com sucesso!");
    return ResponseEntity.ok().build();
  }

}

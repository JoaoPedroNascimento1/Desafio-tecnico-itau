package br.com.desafioItau.controller;

import br.com.desafioItau.dto.request.TransacaoRequest;
import br.com.desafioItau.dto.response.TransacaoResponse;
import br.com.desafioItau.repository.TransacaoRepository;
import br.com.desafioItau.service.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacao")
@RequiredArgsConstructor
public class TransacaoController {

  private final TransacaoService transacaoService;
  private final TransacaoRepository transacaoRepository;

  @PostMapping
  public ResponseEntity<TransacaoResponse> transacao(@RequestBody TransacaoRequest transacaoRequest){
    try{
      transacaoService.validar(transacaoRequest);
      transacaoRepository.salvarDados(transacaoRequest);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    } catch (IllegalArgumentException e){
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    } catch (RuntimeException e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }
}

package br.com.desafioItau.service;

import br.com.desafioItau.dto.request.TransacaoRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class TransacaoService {

  public void validar(TransacaoRequest transacaoRequest){
   if(transacaoRequest.valor().compareTo(BigDecimal.ZERO) < 0){
    throw new IllegalArgumentException("O valor deve ser positivo");
   }
   if(transacaoRequest.dataHora().isAfter(OffsetDateTime.now())){
     throw new IllegalArgumentException("O data não pode ocorrer no futuro");
   }
   if(transacaoRequest.dataHora() == null || transacaoRequest.valor() == null){
     throw new IllegalArgumentException("O campo não pode ser null");
   }
  }
}

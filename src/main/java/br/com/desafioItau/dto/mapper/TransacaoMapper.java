package br.com.desafioItau.dto.mapper;

import br.com.desafioItau.dto.request.TransacaoRequest;
import br.com.desafioItau.dto.response.TransacaoResponse;
import br.com.desafioItau.entity.Transacao;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TransacaoMapper {

  public Transacao toTransacao(TransacaoRequest transacaoRequest){
    return Transacao.builder()
      .valor(transacaoRequest.valor())
      .dataHora(transacaoRequest.dataHora())
      .build();
  }

  public TransacaoResponse toTransacaoResponse(Transacao transacao){
    return TransacaoResponse.builder()
      .valor(transacao.getValor())
      .dataHora(transacao.getDataHora())
      .build();
  }
}

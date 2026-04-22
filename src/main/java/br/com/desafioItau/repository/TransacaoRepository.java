package br.com.desafioItau.repository;

import br.com.desafioItau.dto.mapper.TransacaoMapper;
import br.com.desafioItau.dto.request.TransacaoRequest;
import br.com.desafioItau.dto.response.TransacaoResponse;
import br.com.desafioItau.entity.Estatistica;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransacaoRepository {

  List<TransacaoResponse> transacoes = new ArrayList<>();

  public void salvarDados(TransacaoRequest transacaoRequest){
    transacoes.add(TransacaoMapper.toTransacaoResponse(TransacaoMapper.toTransacao(transacaoRequest)));
  }

  public void deletarDados(){
    transacoes.clear();
  }

  public Estatistica estatistica(OffsetDateTime horaInicial){
    if(transacoes.isEmpty()){
      return new Estatistica(0,0.0,0.0,0.0,0.0);
    }

    var resumo = transacoes.stream().filter(t -> t.dataHora().isAfter(horaInicial) || t.dataHora().isEqual(horaInicial))
      .mapToDouble(t -> t.valor().doubleValue())
      .summaryStatistics();

    return new Estatistica(resumo.getCount(), resumo.getSum(), resumo.getAverage(), resumo.getMax(),  resumo.getMin());
  }

}

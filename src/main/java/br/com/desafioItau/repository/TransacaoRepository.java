package br.com.desafioItau.repository;

import br.com.desafioItau.dto.mapper.TransacaoMapper;
import br.com.desafioItau.dto.request.TransacaoRequest;
import br.com.desafioItau.dto.response.TransacaoResponse;
import org.springframework.stereotype.Repository;

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

}

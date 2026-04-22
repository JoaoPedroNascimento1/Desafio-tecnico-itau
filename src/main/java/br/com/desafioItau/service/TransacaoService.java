package br.com.desafioItau.service;

import br.com.desafioItau.dto.request.TransacaoRequest;
import br.com.desafioItau.exception.BadRequestException;
import br.com.desafioItau.exception.UnprocessableEntityException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class TransacaoService {

  public void validar(TransacaoRequest transacaoRequest) {

    if (transacaoRequest.dataHora() == null || transacaoRequest.valor() == null) {
      throw new UnprocessableEntityException("O campo não pode ser null");
    }

    if (transacaoRequest.valor().compareTo(BigDecimal.ZERO) < 0) {
      throw new UnprocessableEntityException("O valor deve ser positivo");
    }

    if (transacaoRequest.dataHora().isAfter(OffsetDateTime.now())) {
      throw new UnprocessableEntityException("O data não pode ocorrer no futuro");
    }

  }
}

package br.com.desafioItau.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
public record TransacaoResponse(BigDecimal valor, OffsetDateTime dataHora) {
}

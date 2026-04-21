package br.com.desafioItau.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
public record TransacaoRequest (@NotNull @Positive(message = "O valor deve ser positivo") BigDecimal valor,
                                @NotNull OffsetDateTime dataHora){
}

package br.com.desafioItau.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {

  private BigDecimal valor;
  private OffsetDateTime dataHora;
}

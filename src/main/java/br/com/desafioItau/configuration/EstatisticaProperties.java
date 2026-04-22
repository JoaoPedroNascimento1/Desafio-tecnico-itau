package br.com.desafioItau.configuration;

import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "estatistica")
@Validated
public record EstatisticaProperties(@Positive(message = "O valor deve ser positivo") Integer segundos) {

  public int validar(){
    if (segundos <=0) {
      throw new IllegalArgumentException("O valor segundos deve ser positivo");
    }
    return segundos;
  }

}

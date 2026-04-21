package br.com.desafioItau.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Estatistica {

  private long count;
  private double sum;
  private double avg;
  private double max;
  private double min;

}

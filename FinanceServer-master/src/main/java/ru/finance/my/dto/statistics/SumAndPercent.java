package ru.finance.my.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SumAndPercent {
  private Long sum;

  private Long percent;
}
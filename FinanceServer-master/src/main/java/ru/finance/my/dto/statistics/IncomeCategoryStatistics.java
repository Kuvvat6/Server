package ru.finance.my.dto.statistics;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.finance.my.entity.IncomeCategory;

@Getter
@Setter
@Builder
public class IncomeCategoryStatistics {

  private IncomeCategory incomeCategory;

  private SumAndPercent sumAndPercent;
}

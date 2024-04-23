package ru.finance.my.dto.statistics;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.finance.my.entity.ExpenseCategory;

@Getter
@Setter
@Builder
public class ExpenseCategoryStatistics {

  private ExpenseCategory expenseCategory;

  private SumAndPercent sumAndPercent;
}

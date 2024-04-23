package ru.finance.my.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.finance.my.entity.ExpenseCategory;
import ru.finance.my.entity.IncomeCategory;

@Configuration
@ConfigurationProperties(prefix = "default-value")
public class DefaultValueConfiguration {

  private ExpenseCategory expenseCategory;

  private IncomeCategory incomeCategory;

  public ExpenseCategory getExpenseCategory() {
    return expenseCategory;
  }

  public void setExpenseCategory(ExpenseCategory expenseCategory) {
    this.expenseCategory = expenseCategory;
  }

  public IncomeCategory getIncomeCategory() {
    return incomeCategory;
  }

  public void setIncomeCategory(IncomeCategory incomeCategory) {
    this.incomeCategory = incomeCategory;
  }
}

package ru.finance.my.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.finance.my.dto.statistics.ExpenseCategoryStatistics;
import ru.finance.my.dto.statistics.IncomeCategoryStatistics;
import ru.finance.my.dto.statistics.StatisticsDTO;
import ru.finance.my.dto.statistics.SumAndPercent;
import ru.finance.my.entity.Expense;
import ru.finance.my.entity.Income;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {

  @Autowired
  private ExpenseService expenseService;

  @Autowired
  private IncomeService incomeService;

  public StatisticsDTO getStatistics(Long userId, LocalDate beforeDate, LocalDate afterDate) {
    Map<Long, ExpenseCategoryStatistics> percentOfSumOfExpenseCategory = new HashMap<>();
    Map<Long, IncomeCategoryStatistics> percentOfSumOfIncomeCategory = new HashMap<>();
    List<Expense> expenses = expenseService.getAll(beforeDate, afterDate, userId);
    List<Income> incomes = incomeService.getAll(beforeDate, afterDate, userId);
    Long expenseSum = 0L;
    for (Expense expense : expenses) {
      expenseSum += expense.getSum();
      if (percentOfSumOfExpenseCategory.containsKey(expense.getExpenseCategory().getId())) {
        percentOfSumOfExpenseCategory.get(expense.getExpenseCategory().getId()).getSumAndPercent().setSum(
            percentOfSumOfExpenseCategory.get(expense.getExpenseCategory().getId()).getSumAndPercent().getSum() + expense.getSum()
        );
      } else {
        ExpenseCategoryStatistics expenseCategoryStatistics = ExpenseCategoryStatistics.builder()
            .expenseCategory(expense.getExpenseCategory())
            .sumAndPercent(new SumAndPercent(expense.getSum(), 0L))
            .build();
        percentOfSumOfExpenseCategory.put(expense.getExpenseCategory().getId(), expenseCategoryStatistics);
      }
    }
    for (Map.Entry<Long, ExpenseCategoryStatistics> entry : percentOfSumOfExpenseCategory.entrySet()) {
      entry.getValue().getSumAndPercent().setPercent(
          entry.getValue().getSumAndPercent().getSum() * 100 / expenseSum
      );
    }
    Long incomeSum = 0L;
    for (Income income : incomes) {
      incomeSum += income.getSum();
      if (percentOfSumOfIncomeCategory.containsKey(income.getIncomeCategory().getId())) {
        percentOfSumOfIncomeCategory.get(income.getIncomeCategory().getId()).getSumAndPercent().setSum(
            percentOfSumOfIncomeCategory.get(income.getIncomeCategory().getId()).getSumAndPercent().getSum() + income.getSum()
        );
      } else {
        IncomeCategoryStatistics incomeCategoryStatistics = IncomeCategoryStatistics.builder()
            .incomeCategory(income.getIncomeCategory())
            .sumAndPercent(new SumAndPercent(income.getSum(), 0L))
            .build();
        percentOfSumOfIncomeCategory.put(income.getIncomeCategory().getId(), incomeCategoryStatistics);
      }
    }
    for (Map.Entry<Long, IncomeCategoryStatistics> entry : percentOfSumOfIncomeCategory.entrySet()) {
      entry.getValue().getSumAndPercent().setPercent(
          entry.getValue().getSumAndPercent().getSum() * 100 / incomeSum
      );
    }
    return StatisticsDTO.builder()
        .expenseSum(expenseSum)
        .incomeSum(incomeSum)
        .percentOfSumOfExpenseCategory(percentOfSumOfExpenseCategory)
        .percentOfSumOfIncomeCategory(percentOfSumOfIncomeCategory)
        .build();
  }
}

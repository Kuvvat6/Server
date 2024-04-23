package ru.finance.my.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import ru.finance.my.entity.Expense;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ExpenseMapper {

  List<Expense> getAll(LocalDate beforeDate, LocalDate afterDate, Long userId);

  void deleteById(Long expenseId);

  Expense getById(Long expenseId);

  void update(Expense expense);

  void createNew(Expense expense);

  void updateAllExpensesByExpenseCategory(Long oldExpenseCategoryId, Long newExpenseCategoryId);

  void deleteAccount(Long accountId);

  void deleteByUserId(Long userId);
}

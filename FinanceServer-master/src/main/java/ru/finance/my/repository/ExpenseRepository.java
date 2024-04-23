package ru.finance.my.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.finance.my.entity.Expense;
import ru.finance.my.repository.mapper.ExpenseMapper;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ExpenseRepository {

  @Autowired
  private ExpenseMapper expenseMapper;

  public List<Expense> getAll(LocalDate beforeDate, LocalDate afterDate, Long userId) {
    return expenseMapper.getAll(beforeDate, afterDate, userId);
  }

  public void createNew(Expense expense) {
    expenseMapper.createNew(expense);
  }

  public void deleteById(Long expenseId) {
    expenseMapper.deleteById(expenseId);
  }

  public Expense getById(Long expenseId) {
    return expenseMapper.getById(expenseId);
  }

  public void update(Expense expense) {
    expenseMapper.update(expense);
  }

  public void updateAllExpensesByExpenseCategory(Long oldExpenseCategoryId, Long newExpenseCategoryId) {
    expenseMapper.updateAllExpensesByExpenseCategory(oldExpenseCategoryId, newExpenseCategoryId);
  }

  public void deleteAccount(Long accountId) {
    expenseMapper.deleteAccount(accountId);
  }

  public void deleteByUserId(Long userId) {
    expenseMapper.deleteByUserId(userId);
  }
}

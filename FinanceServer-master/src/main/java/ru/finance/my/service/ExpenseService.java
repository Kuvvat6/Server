package ru.finance.my.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.finance.my.entity.Expense;
import ru.finance.my.repository.ExpenseRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

  @Autowired
  private ExpenseRepository expenseRepository;

  @Autowired
  private AccountService accountService;

  public List<Expense> getAll(LocalDate beforeDate, LocalDate afterDate, Long userId) {
    return expenseRepository.getAll(beforeDate, afterDate, userId);
  }

  public Expense getById(Long expenseId) {
    return expenseRepository.getById(expenseId);
  }

  @Transactional(rollbackFor = Exception.class)
  public void createNew(Expense expense) {
    makeExpense(expense);
    expenseRepository.createNew(expense);
  }

  @Transactional(rollbackFor = Exception.class)
  public void deleteById(Long expenseId) {
    Expense expense = expenseRepository.getById(expenseId);
    rollbackExpense(expense);
    expenseRepository.deleteById(expenseId);
  }

  @Transactional(rollbackFor = Exception.class)
  public void update(Expense expense) {
    Expense oldExpense = expenseRepository.getById(expense.getId());
    rollbackExpense(oldExpense);
    oldExpenseToNewExpenseMapping(oldExpense, expense);
    expenseRepository.update(expense);
    makeExpense(expense);
  }

  public void updateAllExpensesByExpenseCategory(Long oldExpenseCategoryId, Long newExpenseCategoryId) {
    expenseRepository.updateAllExpensesByExpenseCategory(oldExpenseCategoryId, newExpenseCategoryId);
  }

  public void deleteAccount(Long accountId) {
    expenseRepository.deleteAccount(accountId);
  }

  private void makeExpense(Expense expense) {
    accountService.minusSum(expense.getAccount().getId(), expense.getSum());
  }

  private void rollbackExpense(Expense expense) {
    accountService.plusSum(expense.getAccount().getId(), expense.getSum());
  }

  private void oldExpenseToNewExpenseMapping(Expense oldExpense, Expense newExpense) {
    if (newExpense.getSum() == null) {
      newExpense.setSum(oldExpense.getSum());
    }
    if (newExpense.getAccount() == null) {
      newExpense.setAccount(oldExpense.getAccount());
    }
    if (newExpense.getExpenseCategory() == null) {
      newExpense.setExpenseCategory(oldExpense.getExpenseCategory());
    }
    if (newExpense.getDate() == null) {
      newExpense.setDate(oldExpense.getDate());
    }
  }

  public void deleteByUserId(Long userId) {
    expenseRepository.deleteByUserId(userId);
  }
}

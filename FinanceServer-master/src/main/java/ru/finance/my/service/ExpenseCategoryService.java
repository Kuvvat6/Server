package ru.finance.my.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.finance.my.entity.ExpenseCategory;
import ru.finance.my.repository.ExpenseCategoryRepository;

import java.util.List;

@Service
public class ExpenseCategoryService {

  @Autowired
  private ExpenseCategoryRepository expenseCategoryRepository;

  @Autowired
  private ExpenseService expenseService;

  public List<ExpenseCategory> getAll(Long userId) {
    return expenseCategoryRepository.getAll(userId);
  }

  public ExpenseCategory getById(Long expenseCategoryId) {
    return expenseCategoryRepository.getById(expenseCategoryId);
  }

  public void createNew(ExpenseCategory expenseCategory) {
    expenseCategoryRepository.createNew(expenseCategory);
  }

  public void update(ExpenseCategory expenseCategory) {
    expenseCategoryRepository.update(expenseCategory);
  }

  @Transactional(rollbackFor = Exception.class)
  public void deleteById(Long expenseCategoryId, Long userId) {
    expenseService.updateAllExpensesByExpenseCategory(expenseCategoryId,expenseCategoryRepository.getDefaultIdByUserId(userId));
    expenseCategoryRepository.deleteById(expenseCategoryId);
  }

  public void createDefaultForNewUser(ExpenseCategory expenseCategory, Long userId) {
    expenseCategory.setUserId(userId);
    expenseCategoryRepository.createDefaultForNewUser(expenseCategory);
  }

  public void deleteByUserId(Long userId) {
    expenseCategoryRepository.deleteByUserId(userId);
  }
}

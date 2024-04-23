package ru.finance.my.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.finance.my.entity.ExpenseCategory;
import ru.finance.my.repository.mapper.ExpenseCategoryMapper;

import java.util.List;

@Repository
public class ExpenseCategoryRepository {

  @Autowired
  private ExpenseCategoryMapper expenseCategoryMapper;

  public List<ExpenseCategory> getAll(Long userId) {
    return expenseCategoryMapper.getAll(userId);
  }

  public void createNew(ExpenseCategory expenseCategory) {
    expenseCategoryMapper.createNew(expenseCategory);
  }

  public void deleteById(Long expenseCategoryId) {
    expenseCategoryMapper.deleteById(expenseCategoryId);
  }

  public ExpenseCategory getById(Long expenseCategoryId) {
    return expenseCategoryMapper.getById(expenseCategoryId);
  }

  public void update(ExpenseCategory expenseCategory) {
    expenseCategoryMapper.update(expenseCategory);
  }

  public Long getDefaultIdByUserId(Long userId) {
    return expenseCategoryMapper.getDefaultIdByUserId(userId);
  }

  public void createDefaultForNewUser(ExpenseCategory expenseCategory) {
    expenseCategoryMapper.createDefaultForNewUser(expenseCategory);
  }

  public void deleteByUserId(Long userId) {
    expenseCategoryMapper.deleteByUserId(userId);
  }
}

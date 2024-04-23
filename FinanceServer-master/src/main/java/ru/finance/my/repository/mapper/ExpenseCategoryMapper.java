package ru.finance.my.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import ru.finance.my.entity.ExpenseCategory;

import java.util.List;

@Mapper
public interface ExpenseCategoryMapper {

  List<ExpenseCategory> getAll(Long userId);

  void deleteById(Long expenseCategoryId);

  ExpenseCategory getById(Long expenseCategoryId);

  void update(ExpenseCategory expenseCategory);

  void createNew(ExpenseCategory expenseCategory);

  Long getDefaultIdByUserId(Long userId);

  void createDefaultForNewUser(ExpenseCategory expenseCategory);

  void deleteByUserId(Long userId);
}

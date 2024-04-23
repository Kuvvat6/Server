package ru.finance.my.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import ru.finance.my.entity.IncomeCategory;

import java.util.List;

@Mapper
public interface IncomeCategoryMapper {

  List<IncomeCategory> getAll(Long userId);

  void deleteById(Long expenseCategoryId);

  IncomeCategory getById(Long incomeCategoryId);

  void update(IncomeCategory incomeCategory);

  void createNew(IncomeCategory incomeCategory);

  Long getDefaultIdByUserId(Long userId);

  void createDefaultForNewUser(IncomeCategory incomeCategory);

  void deleteByUserId(Long userId);
}

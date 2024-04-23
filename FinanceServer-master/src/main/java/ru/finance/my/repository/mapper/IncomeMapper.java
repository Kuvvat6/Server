package ru.finance.my.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import ru.finance.my.entity.Income;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface IncomeMapper {

  List<Income> getAll(LocalDate beforeDate, LocalDate afterDate, Long userId);

  void deleteById(Long incomeId);

  Income getById(Long incomeId);

  void update(Income income);

  void createNew(Income income);

  void updateAllIncomesByIncomeCategory(Long oldIncomeCategoryId, Long newIncomeCategoryId);

  void deleteAccount(Long accountId);

  void deleteByUserId(Long userId);
}

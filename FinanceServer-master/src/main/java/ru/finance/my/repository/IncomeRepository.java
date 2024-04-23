package ru.finance.my.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.finance.my.entity.Income;
import ru.finance.my.repository.mapper.IncomeMapper;

import java.time.LocalDate;
import java.util.List;

@Repository
public class IncomeRepository {

  @Autowired
  private IncomeMapper incomeMapper;

  public List<Income> getAll(LocalDate beforeDate, LocalDate afterDate, Long userId) {
    return incomeMapper.getAll(beforeDate, afterDate, userId);
  }

  public void createNew(Income income) {
    incomeMapper.createNew(income);
  }

  public void deleteById(Long incomeId) {
    incomeMapper.deleteById(incomeId);
  }

  public Income getById(Long incomeId) {
    return incomeMapper.getById(incomeId);
  }

  public void update(Income income) {
    incomeMapper.update(income);
  }

  public void updateAllIncomesByIncomeCategory(Long oldIncomeCategoryId, Long newIncomeCategoryId) {
    incomeMapper.updateAllIncomesByIncomeCategory(oldIncomeCategoryId, newIncomeCategoryId);
  }

  public void deleteAccount(Long accountId) {
    incomeMapper.deleteAccount(accountId);
  }

  public void deleteByUserId(Long userId) {
    incomeMapper.deleteByUserId(userId);
  }
}

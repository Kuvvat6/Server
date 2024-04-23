package ru.finance.my.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.finance.my.entity.Income;
import ru.finance.my.repository.IncomeRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class IncomeService {

  @Autowired
  private IncomeRepository incomeRepository;

  @Autowired
  private AccountService accountService;

  public List<Income> getAll(LocalDate beforeDate, LocalDate afterDate, Long userId) {
    return incomeRepository.getAll(beforeDate, afterDate, userId);
  }

  public Income getById(Long incomeId) {
    return incomeRepository.getById(incomeId);
  }

  @Transactional(rollbackFor = Exception.class)
  public void createNew(Income income) {
    makeIncome(income);
    incomeRepository.createNew(income);
  }

  @Transactional(rollbackFor = Exception.class)
  public void deleteById(Long incomeId) {
    Income income = incomeRepository.getById(incomeId);
    rollbackIncome(income);
    incomeRepository.deleteById(incomeId);
  }

  @Transactional(rollbackFor = Exception.class)
  public void update(Income income) {
    Income oldIncome = incomeRepository.getById(income.getId());
    rollbackIncome(oldIncome);
    oldIncomeToNewIncomeMapping(oldIncome, income);
    incomeRepository.update(income);
    makeIncome(income);
  }

  public void updateAllIncomesByIncomeCategory(Long oldIncomeCategoryId, Long newIncomeCategoryId) {
    incomeRepository.updateAllIncomesByIncomeCategory(oldIncomeCategoryId, newIncomeCategoryId);
  }

  public void deleteAccount(Long accountId) {
    incomeRepository.deleteAccount(accountId);
  }

  private void makeIncome(Income income) {
    accountService.plusSum(income.getAccount().getId(), income.getSum());
  }

  private void rollbackIncome(Income income) {
    accountService.minusSum(income.getAccount().getId(), income.getSum());
  }

  private void oldIncomeToNewIncomeMapping(Income oldIncome, Income newIncome) {
    if (newIncome.getSum() == null) {
      newIncome.setSum(oldIncome.getSum());
    }
    if (newIncome.getAccount() == null) {
      newIncome.setAccount(oldIncome.getAccount());
    }
    if (newIncome.getIncomeCategory() == null) {
      newIncome.setIncomeCategory(oldIncome.getIncomeCategory());
    }
    if (newIncome.getDate() == null) {
      newIncome.setDate(oldIncome.getDate());
    }
  }

  public void deleteByUserId(Long userId) {
    incomeRepository.deleteByUserId(userId);
  }
}

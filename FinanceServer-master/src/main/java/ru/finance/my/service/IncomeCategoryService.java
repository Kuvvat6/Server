package ru.finance.my.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.finance.my.entity.IncomeCategory;
import ru.finance.my.repository.IncomeCategoryRepository;

import java.util.List;

@Service
public class IncomeCategoryService {

  @Autowired
  private IncomeCategoryRepository incomeCategoryRepository;

  @Autowired
  private IncomeService incomeService;

  public List<IncomeCategory> getAll(Long userId) {
    return incomeCategoryRepository.getAll(userId);
  }

  public IncomeCategory getById(Long incomeCategoryId) {
    return incomeCategoryRepository.getById(incomeCategoryId);
  }

  public void createNew(IncomeCategory incomeCategory) {
    incomeCategoryRepository.createNew(incomeCategory);
  }

  public void update(IncomeCategory incomeCategory) {
    incomeCategoryRepository.update(incomeCategory);
  }

  @Transactional(rollbackFor = Exception.class)
  public void deleteById(Long incomeCategoryId, Long userId) {
    incomeService.updateAllIncomesByIncomeCategory(incomeCategoryId, incomeCategoryRepository.getDefaultIdByUserId(userId));
    incomeCategoryRepository.deleteById(incomeCategoryId);
  }

  public void createDefaultForNewUser(IncomeCategory incomeCategory, Long userId) {
    incomeCategory.setUserId(userId);
    incomeCategoryRepository.createDefaultForNewUser(incomeCategory);
  }

  public void deleteByUserId(Long userId) {
    incomeCategoryRepository.deleteByUserId(userId);
  }
}

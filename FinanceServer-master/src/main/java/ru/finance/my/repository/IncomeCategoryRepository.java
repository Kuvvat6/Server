package ru.finance.my.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.finance.my.entity.IncomeCategory;
import ru.finance.my.repository.mapper.IncomeCategoryMapper;

import java.util.List;

@Repository
public class IncomeCategoryRepository {

  @Autowired
  private IncomeCategoryMapper incomeCategoryMapper;

  public List<IncomeCategory> getAll(Long userId) {
    return incomeCategoryMapper.getAll(userId);
  }

  public void createNew(IncomeCategory incomeCategory) {
    incomeCategoryMapper.createNew(incomeCategory);
  }

  public void deleteById(Long incomeCategoryId) {
    incomeCategoryMapper.deleteById(incomeCategoryId);
  }

  public IncomeCategory getById(Long incomeCategoryId) {
    return incomeCategoryMapper.getById(incomeCategoryId);
  }

  public void update(IncomeCategory incomeCategory) {
    incomeCategoryMapper.update(incomeCategory);
  }

  public Long getDefaultIdByUserId(Long userId) {
    return incomeCategoryMapper.getDefaultIdByUserId(userId);
  }

  public void createDefaultForNewUser(IncomeCategory incomeCategory) {
    incomeCategoryMapper.createDefaultForNewUser(incomeCategory);
  }

  public void deleteByUserId(Long userId) {
    incomeCategoryMapper.deleteByUserId(userId);
  }
}

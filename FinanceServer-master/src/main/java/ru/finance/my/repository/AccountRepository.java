package ru.finance.my.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.finance.my.entity.Account;
import ru.finance.my.repository.mapper.AccountMapper;

import java.util.List;

@Repository
public class AccountRepository {

  @Autowired
  private AccountMapper accountMapper;

  public List<Account> getAllByUserId(Long userId) {
    return accountMapper.getAllByUserId(userId);
  }

  public Account getById(Long accountId) {
    return accountMapper.getById(accountId);
  }

  public void plusSum(Long accountId, Long sum) {
    accountMapper.plusSum(accountId, sum);
  }

  public void minusSum(Long accountId, Long sum) {
    accountMapper.minusSum(accountId, sum);
  }

  public void update(Account account) {
    accountMapper.update(account);
  }

  public void deleteById(Long accountId) {
    accountMapper.deleteById(accountId);
  }

  public void createNew(Account account) {
    accountMapper.createNew(account);
  }

  public void deleteByUserId(Long userId) {
    accountMapper.deleteByUserId(userId);
  }
}

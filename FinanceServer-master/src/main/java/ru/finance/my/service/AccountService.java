package ru.finance.my.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.finance.my.entity.Account;
import ru.finance.my.repository.AccountRepository;

import java.util.List;

@Service
public class AccountService {

  @Autowired
  private TransferService transferService;

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private ExpenseService expenseService;

  @Autowired
  private IncomeService incomeService;

  public List<Account> getAllByUserId(Long userId) {
    return accountRepository.getAllByUserId(userId);
  }

  public Account getById(Long accountId) {
    return accountRepository.getById(accountId);
  }

  public void plusSum(Long accountId, Long sum) {
    accountRepository.plusSum(accountId, sum);
  }

  public void minusSum(Long accountId, Long sum) {
    accountRepository.minusSum(accountId, sum);
  }

  public void update(Account account) {
    accountRepository.update(account);
  }

  @Transactional(rollbackFor = Exception.class)
  public void deleteById(Long accountId) {
    transferService.deleteAccount(accountId);
    expenseService.deleteAccount(accountId);
    incomeService.deleteAccount(accountId);
    accountRepository.deleteById(accountId);
  }

  public void createNew(Account account) {
    accountRepository.createNew(account);
  }

  public void deleteByUserId(Long userId) {
    accountRepository.deleteByUserId(userId);
  }
}

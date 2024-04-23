package ru.finance.my.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.finance.my.entity.Transfer;
import ru.finance.my.repository.mapper.TransferMapper;

import java.time.LocalDate;
import java.util.List;

@Repository
public class TransferRepository {

  @Autowired
  private TransferMapper transferMapper;

  public List<Transfer> getAll(LocalDate beforeDate, LocalDate afterDate, Long userId) {
    return transferMapper.getAll(beforeDate, afterDate, userId);
  }

  public void createNew(Transfer transfer) {
    transferMapper.createNew(transfer);
  }

  public void deleteById(Long transferId) {
    transferMapper.deleteById(transferId);
  }

  public Transfer getById(Long transferId) {
    return transferMapper.getById(transferId);
  }

  public void update(Transfer transfer) {
    transferMapper.update(transfer);
  }

  private void clearFromAccountId(Long accountId) {
    transferMapper.clearFromAccountId(accountId);
  }

  private void clearToAccountId(Long accountId) {
    transferMapper.clearToAccountId(accountId);
  }

  public void deleteAccount(Long accountId) {
    clearFromAccountId(accountId);
    clearToAccountId(accountId);
  }

  public void deleteEmptyTransfers() {
    transferMapper.deleteEmptyTransfers();
  }

  public void deleteByUserId(Long userId) {
    transferMapper.deleteByUserId(userId);
  }
}

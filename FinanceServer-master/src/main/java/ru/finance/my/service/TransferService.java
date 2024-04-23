package ru.finance.my.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.finance.my.entity.Transfer;
import ru.finance.my.repository.TransferRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransferService {

  @Autowired
  private TransferRepository transferRepository;

  @Autowired
  private AccountService accountService;

  public void deleteAccount(Long accountId) {
    transferRepository.deleteAccount(accountId);
    transferRepository.deleteEmptyTransfers();
  }

  public Transfer getById(Long transferId) {
    return transferRepository.getById(transferId);
  }

  public List<Transfer> getAll(LocalDate beforeDate, LocalDate afterDate, Long userId) {
    return transferRepository.getAll(beforeDate, afterDate, userId);
  }

  @Transactional(rollbackFor = Exception.class)
  public void createNew(Transfer transfer) {
    makeTransfer(transfer);
    transferRepository.createNew(transfer);
  }

  @Transactional(rollbackFor = Exception.class)
  public void deleteById(Long transferId) {
    Transfer transfer = transferRepository.getById(transferId);
    rollbackTransfer(transfer);
    transferRepository.deleteById(transferId);
  }

  @Transactional(rollbackFor = Exception.class)
  public void update(Transfer transfer) {
    Transfer oldTransfer = transferRepository.getById(transfer.getId());
    rollbackTransfer(oldTransfer);
    oldTransferToNewTransferMapping(oldTransfer, transfer);
    transferRepository.update(transfer);
    makeTransfer(transfer);
  }

  private void makeTransfer(Transfer transfer) {
    accountService.plusSum(transfer.getToAccount().getId(), transfer.getSum());
    accountService.minusSum(transfer.getFromAccount().getId(), transfer.getSum());
  }

  private void rollbackTransfer(Transfer transfer) {
    accountService.plusSum(transfer.getFromAccount().getId(), transfer.getSum());
    accountService.minusSum(transfer.getToAccount().getId(), transfer.getSum());
  }

  private void oldTransferToNewTransferMapping(Transfer oldTransfer, Transfer newTransfer) {
    if (newTransfer.getSum() == null) {
      newTransfer.setSum(oldTransfer.getSum());
    }
    if (newTransfer.getFromAccount() == null) {
      newTransfer.setFromAccount(oldTransfer.getFromAccount());
    }
    if (newTransfer.getToAccount() == null) {
      newTransfer.setToAccount(oldTransfer.getToAccount());
    }
    if (newTransfer.getDate() == null) {
      newTransfer.setDate(oldTransfer.getDate());
    }
  }

  public void deleteByUserId(Long userId) {
    transferRepository.deleteByUserId(userId);
  }
}

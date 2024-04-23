package ru.finance.my.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import ru.finance.my.entity.Transfer;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface TransferMapper {

  List<Transfer> getAll(LocalDate beforeDate, LocalDate afterDate, Long userId);

  void createNew(Transfer transfer);

  void deleteById(Long transferId);

  Transfer getById(Long transferId);

  void update(Transfer transfer);

  void clearFromAccountId(Long accountId);

  void clearToAccountId(Long accountId);

  void deleteEmptyTransfers();

  void deleteByUserId(Long userId);
}

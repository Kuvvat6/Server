package ru.finance.my.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import ru.finance.my.entity.Account;

import java.util.List;

@Mapper
public interface AccountMapper {

  List<Account> getAllByUserId(Long userId);

  Account getById(Long accountId);

  void plusSum(Long accountId, Long sum);

  void minusSum(Long accountId, Long sum);

  void update(Account account);

  void deleteById(Long accountId);

  void createNew(Account account);

  void deleteByUserId(Long userId);
}

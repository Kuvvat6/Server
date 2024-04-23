package ru.finance.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ru.finance.my.controller.path.Path;
import ru.finance.my.entity.Account;
import ru.finance.my.entity.User;
import ru.finance.my.service.AccountService;

import java.util.List;

@RestController
@RequestMapping(Path.ACCOUNT_PATH)
public class AccountController {

  @Autowired
  private AccountService accountService;

  @GetMapping
  public List<Account> getAllByUserToken(UsernamePasswordAuthenticationToken token) {
    User user = (User)token.getPrincipal();
    return accountService.getAllByUserId(user.getId());
  }

  @GetMapping("/{accountId}")
  public Account getById(@PathVariable Long accountId) {
    return accountService.getById(accountId);
  }

  @PutMapping("/{accountId}")
  public void update(@PathVariable Long accountId, @RequestBody Account account) {
    account.setId(accountId);
    accountService.update(account);
  }

  @DeleteMapping("/{accountId}")
  public void deleteById(@PathVariable Long accountId) {
    accountService.deleteById(accountId);
  }

  @PostMapping
  public ResponseEntity<Account> createNew(@RequestBody Account account, UsernamePasswordAuthenticationToken token) {
    User user = (User) token.getPrincipal();
    account.setUserId(user.getId());
    accountService.createNew(account);
    return ResponseEntity.status(HttpStatus.CREATED).body(account);
  }
}

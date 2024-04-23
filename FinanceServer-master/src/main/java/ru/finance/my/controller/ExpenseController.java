package ru.finance.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ru.finance.my.controller.path.Path;
import ru.finance.my.entity.Expense;
import ru.finance.my.entity.User;
import ru.finance.my.service.ExpenseService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(Path.EXPENSE_PATH)
public class ExpenseController {

  @Autowired
  private ExpenseService expenseService;

  @GetMapping
  public List<Expense> getAll(
      @RequestParam("before-date") String beforeDate,
      @RequestParam("after-date") String afterDate,
      UsernamePasswordAuthenticationToken token
  ) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate before = LocalDate.parse(beforeDate, formatter);
    LocalDate after = LocalDate.parse(afterDate, formatter);
    User user = (User)token.getPrincipal();
    return expenseService.getAll(before, after, user.getId());
  }

  @PostMapping
  public ResponseEntity<Expense> createNew(@RequestBody Expense expense) {
    expenseService.createNew(expense);
    return ResponseEntity.status(HttpStatus.CREATED).body(expense);
  }

  @DeleteMapping("/{expenseId}")
  public void deleteById(@PathVariable Long expenseId) {
    expenseService.deleteById(expenseId);
  }

  @PutMapping("/{expenseId}")
  public Expense updateById(@PathVariable Long expenseId, @RequestBody Expense expense) {
    expense.setId(expenseId);
    expenseService.update(expense);
    return expense;
  }

  @GetMapping("/{expenseId}")
  public Expense getById(@PathVariable Long expenseId) {
    return expenseService.getById(expenseId);
  }
}

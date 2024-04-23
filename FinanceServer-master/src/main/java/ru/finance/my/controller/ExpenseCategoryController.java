package ru.finance.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ru.finance.my.controller.path.Path;
import ru.finance.my.entity.ExpenseCategory;
import ru.finance.my.entity.User;
import ru.finance.my.service.ExpenseCategoryService;

import java.util.List;

@RestController
@RequestMapping(Path.EXPENSE_CATEGORY_PATH)
public class ExpenseCategoryController {

  @Autowired
  private ExpenseCategoryService expenseCategoryService;

  @GetMapping
  public List<ExpenseCategory> getAll(UsernamePasswordAuthenticationToken token) {
    User user = (User)token.getPrincipal();
    return expenseCategoryService.getAll(user.getId());
  }

  @PostMapping
  public ResponseEntity<ExpenseCategory> createNew(@RequestBody ExpenseCategory expenseCategory, UsernamePasswordAuthenticationToken token) {
    User user = (User)token.getPrincipal();
    expenseCategory.setUserId(user.getId());
    expenseCategoryService.createNew(expenseCategory);
    return ResponseEntity.status(HttpStatus.CREATED).body(expenseCategory);
  }

  @DeleteMapping("/{expenseCategoryId}")
  public void deleteById(@PathVariable Long expenseCategoryId, UsernamePasswordAuthenticationToken token) {
    User user = (User)token.getPrincipal();
    expenseCategoryService.deleteById(expenseCategoryId, user.getId());
  }

  @PutMapping("/{expenseCategoryId}")
  public ExpenseCategory updateById(@PathVariable Long expenseCategoryId, @RequestBody ExpenseCategory expenseCategory) {
    expenseCategory.setId(expenseCategoryId);
    expenseCategoryService.update(expenseCategory);
    return expenseCategory;
  }

  @GetMapping("/{expenseCategoryId}")
  public ExpenseCategory getById(@PathVariable Long expenseCategoryId) {
    return expenseCategoryService.getById(expenseCategoryId);
  }
}

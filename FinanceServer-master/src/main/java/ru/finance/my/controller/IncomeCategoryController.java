package ru.finance.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ru.finance.my.controller.path.Path;
import ru.finance.my.entity.IncomeCategory;
import ru.finance.my.entity.User;
import ru.finance.my.service.IncomeCategoryService;

import java.util.List;

@RestController
@RequestMapping(Path.INCOME_CATEGORY_PATH)
public class IncomeCategoryController {

  @Autowired
  private IncomeCategoryService incomeCategoryService;

  @GetMapping
  public List<IncomeCategory> getAll(UsernamePasswordAuthenticationToken token) {
    User user = (User)token.getPrincipal();
    return incomeCategoryService.getAll(user.getId());
  }

  @PostMapping
  public ResponseEntity<IncomeCategory> createNew(@RequestBody IncomeCategory incomeCategory, UsernamePasswordAuthenticationToken token) {
    User user = (User)token.getPrincipal();
    incomeCategory.setUserId(user.getId());
    incomeCategoryService.createNew(incomeCategory);
    return ResponseEntity.status(HttpStatus.CREATED).body(incomeCategory);
  }

  @DeleteMapping("/{incomeCategoryId}")
  public void deleteById(@PathVariable Long incomeCategoryId, UsernamePasswordAuthenticationToken token) {
    User user = (User)token.getPrincipal();
    incomeCategoryService.deleteById(incomeCategoryId, user.getId());
  }

  @PutMapping("/{incomeCategoryId}")
  public IncomeCategory updateById(@PathVariable Long incomeCategoryId, @RequestBody IncomeCategory incomeCategory) {
    incomeCategory.setId(incomeCategoryId);
    incomeCategoryService.update(incomeCategory);
    return incomeCategory;
  }

  @GetMapping("/{incomeCategoryId}")
  public IncomeCategory getById(@PathVariable Long incomeCategoryId) {
    return incomeCategoryService.getById(incomeCategoryId);
  }
}

package ru.finance.my.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Expense {

  private Long id;

  private Long sum;

  private Account account;

  private LocalDate date;

  private ExpenseCategory expenseCategory;
}

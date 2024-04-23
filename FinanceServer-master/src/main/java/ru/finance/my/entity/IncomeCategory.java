package ru.finance.my.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncomeCategory {

  private Long id;

  private String name;

  private Long userId;

  private Boolean accessToDelete;
}

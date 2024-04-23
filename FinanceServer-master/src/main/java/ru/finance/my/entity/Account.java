package ru.finance.my.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {

  private Long id;

  private String name;

  private Long sum;

  private Long userId;
}

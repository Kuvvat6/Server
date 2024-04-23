package ru.finance.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.finance.my.controller.path.Path;
import ru.finance.my.dto.statistics.StatisticsDTO;
import ru.finance.my.entity.User;
import ru.finance.my.service.StatisticsService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(Path.STATISTICS_PATH)
public class StatisticsController {

  @Autowired
  private StatisticsService statisticsService;

  @GetMapping
  public StatisticsDTO getStatistics(
      UsernamePasswordAuthenticationToken token,
      @RequestParam("before-date") String beforeDate,
      @RequestParam("after-date") String afterDate
  ) {
    User user = (User)token.getPrincipal();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate before = LocalDate.parse(beforeDate, formatter);
    LocalDate after = LocalDate.parse(afterDate, formatter);
    return statisticsService.getStatistics(user.getId(), before, after);
  }
}

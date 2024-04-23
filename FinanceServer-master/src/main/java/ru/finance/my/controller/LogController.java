package ru.finance.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.finance.my.controller.path.Path;
import ru.finance.my.dto.LoginRequestDTO;
import ru.finance.my.entity.User;
import ru.finance.my.service.LogService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class LogController {

  @Autowired
  private LogService logService;

  @PostMapping(Path.LOGIN_PATH)
  public ResponseEntity<User> login(@RequestBody LoginRequestDTO loginRequestDTO) {
    User user = logService.authenticate(loginRequestDTO);
    return ResponseEntity.ok().body(user);
  }

  @PostMapping(Path.LOGOUT_PATH)
  public ResponseEntity<HttpStatus> logout(HttpServletRequest httpServletRequest) {
    HttpSession session = httpServletRequest.getSession(false);
    session.invalidate();
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}

package ru.finance.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ru.finance.my.controller.path.Path;
import ru.finance.my.entity.User;
import ru.finance.my.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(Path.USER_PATH)
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<User> getUserByToken(UsernamePasswordAuthenticationToken token) {
    return ResponseEntity.ok().body((User) token.getPrincipal());
  }

  @PutMapping
  public ResponseEntity<User> updateUserByToken(UsernamePasswordAuthenticationToken token, @RequestBody User userDataForUpdate, HttpServletRequest httpServletRequest) {
    User currentUser = (User) token.getPrincipal();
    if (
        !userService.emailIsNotUnique(userDataForUpdate.getEmail(), currentUser.getId()) &&
            !userService.loginIsNotUnique(userDataForUpdate.getLogin(), currentUser.getId())
    ) {
      userDataForUpdate.setId(currentUser.getId());

      userService.updateUser(userDataForUpdate, currentUser);

      HttpSession session = httpServletRequest.getSession(false);
      session.invalidate();

      return ResponseEntity.ok().body(userDataForUpdate);
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @DeleteMapping
  public ResponseEntity<HttpStatus> deleteByToken(UsernamePasswordAuthenticationToken token, HttpServletRequest httpServletRequest) {
    HttpSession session = httpServletRequest.getSession(false);
    session.invalidate();

    userService.deleteUserById(((User) token.getPrincipal()).getId());

    return ResponseEntity.ok().build();
  }
}

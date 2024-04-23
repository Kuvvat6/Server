package ru.finance.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.finance.my.controller.path.Path;
import ru.finance.my.dto.RegistrationRequestDTO;
import ru.finance.my.service.UserService;

@RestController
@RequestMapping(Path.REGISTRATION_PATH)
public class RegistrationController {

  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<HttpStatus> registration(@RequestBody RegistrationRequestDTO registrationRequestDTO) {
    if (
        registrationRequestDTO.getPassword().equals(registrationRequestDTO.getConfirmOfPassword()) &&
            !userService.existByEmail(registrationRequestDTO.getEmail()) &&
            !userService.existByLogin(registrationRequestDTO.getLogin())
    ) {
      userService.createNew(registrationRequestDTO);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }
}

package ru.finance.my.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.finance.my.dto.LoginRequestDTO;
import ru.finance.my.entity.User;

@Service
public class LogService {

  @Autowired
  private UserService userService;

  @Autowired
  private AuthenticationManager authenticationManager;

  public User authenticate(LoginRequestDTO loginRequestDTO) {
    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername() , loginRequestDTO.getPassword()));
    securityContext.setAuthentication(authentication);
    SecurityContextHolder.setContext(securityContext);
    return (User) userService.loadUserByUsername(loginRequestDTO.getUsername());
  }
}

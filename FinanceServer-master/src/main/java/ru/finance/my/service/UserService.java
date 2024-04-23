package ru.finance.my.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.finance.my.configuration.DefaultValueConfiguration;
import ru.finance.my.dto.RegistrationRequestDTO;
import ru.finance.my.entity.User;
import ru.finance.my.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  private ExpenseService expenseService;

  @Autowired
  private IncomeService incomeService;

  @Autowired
  private ExpenseCategoryService expenseCategoryService;

  @Autowired
  private IncomeCategoryService incomeCategoryService;

  @Autowired
  private TransferService transferService;

  @Autowired
  private AccountService accountService;

  @Autowired
  private DefaultValueConfiguration defaultValueConfiguration;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDetails userDetails = userRepository.getByLogin(username);
    if (userDetails == null) throw new UsernameNotFoundException("User not found");
    return userDetails;
  }

  @Transactional(rollbackFor = Exception.class)
  public void createNew(RegistrationRequestDTO registrationRequestDTO) {
    User user = getUser(registrationRequestDTO);
    userRepository.createNew(user);
    expenseCategoryService.createDefaultForNewUser(defaultValueConfiguration.getExpenseCategory(), user.getId());
    incomeCategoryService.createDefaultForNewUser(defaultValueConfiguration.getIncomeCategory(), user.getId());
  }

  public boolean existByEmail(String email) {
    return userRepository.existByEmail(email);
  }

  private User getUser(RegistrationRequestDTO registrationRequestDTO) {
    return User.builder()
        .email(registrationRequestDTO.getEmail())
        .name(registrationRequestDTO.getName())
        .login(registrationRequestDTO.getLogin())
        .password(bCryptPasswordEncoder.encode(registrationRequestDTO.getPassword()))
        .build();
  }

  public void updateUser(User userDataForUpdate, User oldUser) {
    if (userDataForUpdate.getName() == null) {
      userDataForUpdate.setName(oldUser.getName());
    }
    if (userDataForUpdate.getLogin() == null) {
      userDataForUpdate.setLogin(oldUser.getLogin());
    }
    if (userDataForUpdate.getEmail() == null) {
      userDataForUpdate.setEmail(oldUser.getEmail());
    }
    if (userDataForUpdate.getPassword() == null) {
      userDataForUpdate.setPassword(oldUser.getPassword());
    } else {
      userDataForUpdate.setPassword(bCryptPasswordEncoder.encode(userDataForUpdate.getPassword()));
    }
    userRepository.updateUser(userDataForUpdate);
  }

  @Transactional(rollbackFor = Exception.class)
  public void deleteUserById(Long userId) {
    transferService.deleteByUserId(userId);
    expenseService.deleteByUserId(userId);
    incomeService.deleteByUserId(userId);
    expenseCategoryService.deleteByUserId(userId);
    incomeCategoryService.deleteByUserId(userId);
    accountService.deleteByUserId(userId);
    userRepository.deleteUserById(userId);
  }

  public boolean emailIsNotUnique(String email, Long userId) {
    return userRepository.emailIsNotUnique(email, userId);
  }

  public boolean existByLogin(String login) {
    return userRepository.existByLogin(login);
  }

  public boolean loginIsNotUnique(String login, Long userId) {
    return userRepository.loginIsNotUnique(login, userId);
  }
}

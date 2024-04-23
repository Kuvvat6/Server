package ru.finance.my.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.finance.my.entity.User;
import ru.finance.my.repository.mapper.UserMapper;

@Repository
public class UserRepository {

  @Autowired
  private UserMapper userMapper;

  public User getByLogin(String login) {
    return userMapper.getByLogin(login);
  }

  public void createNew(User user) {
    userMapper.createNew(user);
  }

  public boolean existByEmail(String email) {
    return userMapper.existByEmail(email);
  }

  public void updateUser(User user) {
    userMapper.updateUser(user);
  }

  public void deleteUserById(Long userId) {
    userMapper.deleteUserById(userId);
  }

  public boolean emailIsNotUnique(String email, Long userId) {
    return userMapper.emailIsNotUnique(email, userId);
  }

  public boolean existByLogin(String login) {
    return userMapper.existByLogin(login);
  }

  public boolean loginIsNotUnique(String login, Long userId) {
    return userMapper.loginIsNotUnique(login, userId);
  }
}

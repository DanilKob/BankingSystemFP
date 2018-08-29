package model.service;

import controller.dto.LoginDto;
import controller.dto.RegistrationFormDto;
import model.dao.UserDao;
import model.dao.config.DataBaseConfiguration;
import model.entity.User;
import model.exception.NotUniqueException;

import java.util.Optional;

public class UserService {

    public static void registerUser(User user) throws NotUniqueException{
        try(UserDao userDao = DataBaseConfiguration.factory.createUserDao()){
            userDao.create(user);
        }
    }

    public static void registerUser(RegistrationFormDto registrationForm) throws NotUniqueException {
        try(UserDao userDao = DataBaseConfiguration.factory.createUserDao()){
            User user = new User.Builder().
                    setFirstName(registrationForm.getFirstName())
                    .setLastName(registrationForm.getLastName())
                    .setMiddleName(registrationForm.getMiddleName())
                    .setLogin(registrationForm.getLogin())
                    .setPassword(registrationForm.getPassword())
                    .setRole(User.ROLE.USER)
                    .build();
            userDao.create(user);
        }
    }

    public static Optional<User> login(LoginDto loginDto){
        try(UserDao userDao = DataBaseConfiguration.factory.createUserDao()){
            return userDao.findByLoginPassword(loginDto.getLogin(),loginDto.getPassword());
        }
    }

    public static User findByBankAccountId(int bankAccountId){
        try(UserDao userDao = DataBaseConfiguration.factory.createUserDao()){
            return userDao.findByBankAccount(bankAccountId);
        }
    }
}

package model;

import model.entity.RegistrationForm;
import model.exception.LoginIsAlreadyExistException;

public class UserService {
    // todo add DAO
    public void registerUser(RegistrationForm registrationForm) throws LoginIsAlreadyExistException {
        System.out.println("User was registered");
    }
}

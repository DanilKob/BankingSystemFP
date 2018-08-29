package model.mock;

import model.mock.entity.RegistrationForm;
import model.exception.NotUniqueException;

public class UserService {
    // todo add DAO
    public void registerUser(RegistrationForm registrationForm) throws NotUniqueException {
        System.out.println("User was registered");
    }
}

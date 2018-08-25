package model.service;

import model.entity.User;
import model.exception.LoginException;
public class GuestService {
    public User.ROLE login(User user) throws LoginException {
        // todo add DAO
        // todo change return role
        //throw new LoginException(new String(""));
        System.out.println("Log in as Admin");
        return User.ROLE.ADMIN;
    }
}

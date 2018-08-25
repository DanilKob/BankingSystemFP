package model.exception;

public class LoginIsAlreadyExistException extends Exception{
    private String login;

    public LoginIsAlreadyExistException(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

package model.entity;

import controller.utility.Languages;

public class RegistrationForm {

    private String firstName;
    private String lastName;
    private String middleName;
    private String login;
    private String password;

    private Languages language;



    public RegistrationForm(String firstName, String lastName, String middleName,
                            String login, String password, Languages language) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.language = language;
        this.login = login;
        this.password = password;
    }
}

package model.entity;

import model.dao.extracter.ExtractParam;
import model.dao.mapper.MappingKey;
import model.dao.statement.TableConstants;
import model.dao.statement.tables.RoleTable;
import model.dao.statement.tables.UserTable;

import java.util.List;

public class User implements Entity{
    @MappingKey
    @ExtractParam(columnName = UserTable.USER_ID)
    private int id;

    @ExtractParam(columnName = UserTable.USER_FIRST_NAME)
    private String firstName;

    @ExtractParam(columnName = UserTable.USER_LAST_NAME)
    private String lastName;

    @ExtractParam(columnName = UserTable.USER_MIDDLE_NAME)
    private String middleName;

    @ExtractParam(columnName = UserTable.USER_LOGIN)
    private String login;

    @ExtractParam(columnName = UserTable.USER_PASSWORD)
    private String password;

    @ExtractParam(columnName = RoleTable.ROLE_NAME)
    private ROLE role;

    private List<BankAccount> bankAccountList;

    //todo make constructor private
    public User(){
        
    }

    public enum ROLE {
        USER(TableConstants.ROLE_USER_ID),
        ADMIN(TableConstants.ROLE_ADMIN_ID),
        GUEST(TableConstants.ROLE_GUEST_ID);

        public final int roleId;

        ROLE(int roleId){
            this.roleId = roleId;
        }
    }

    public static class Builder{
        private User user = new User();

        public Builder setId(int id) {
            user.id = id;
            return this;
        }
        public Builder setFirstName(String firstName) {
            user.firstName = firstName;
            return this;
        }
        public Builder setLastName(String lastName) {
            user.lastName = lastName;
            return this;
        }

        public Builder setMiddleName(String middleName) {
            user.middleName = middleName;
            return this;
        }

        public Builder setLogin(String login) {
            user.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            user.password = password;
            return this;
        }

        public Builder setRole(ROLE role){
            user.role = role;
            return this;
        }

        public Builder setRole(String role){
            // todo add exception handler
            user.role = ROLE.valueOf(role);
            return this;
        }

        public User build(){
            return user;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public List<BankAccount> getBankAccountList() {
        return bankAccountList;
    }

    public void setBankAccountList(List<BankAccount> bankAccountList) {
        this.bankAccountList = bankAccountList;
    }


}

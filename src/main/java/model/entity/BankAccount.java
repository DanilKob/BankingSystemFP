package model.entity;

import model.dao.extracter.ExtractParam;
import model.dao.statement.tables.BankAccountTable;
import model.entity.enums.Account;

import java.time.LocalDateTime;

public class BankAccount implements Entity{
    @ExtractParam(columnName = BankAccountTable.BANK_ACCOUNT_ID)
    private int id;
    @ExtractParam(columnName = BankAccountTable.BANK_ACCOUNT_USER_ID)
    private int userId;
    @ExtractParam(columnName = BankAccountTable.BANK_ACCOUNT_BALANCE)
    private int balance;


    private Account accountType;

    @ExtractParam(columnName = BankAccountTable.BANK_ACCOUNT_DATE)
    private LocalDateTime date;

    public static class Builder{
        BankAccount bankAccount = new BankAccount();

        public Builder setId(int id) {
            bankAccount.id = id;
            return this;
        }

        public Builder setUserId(int userId) {
            bankAccount.userId = userId;
            return this;
        }

        public Builder setBalance(int balance) {
            bankAccount.balance = balance;
            return this;
        }


        public Builder setAccountType(Account accountType) {
            bankAccount.accountType = accountType;
            return this;
        }


        public BankAccount build(){
            return bankAccount;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Account getAccountType() {
        return accountType;
    }

    public void setAccountType(Account accountType) {
        this.accountType = accountType;
    }

}

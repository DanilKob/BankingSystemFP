package controller.dto;



public class BankAccountDto {
    protected int id;
    protected int userId;
    protected int balance;

    protected int accountPropertiesId;

    public static class Builder{
        BankAccountDto bankAccount = new BankAccountDto();

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

        public Builder setAccountPropertiesId(int accountPropertiesId) {
            bankAccount.accountPropertiesId = accountPropertiesId;
            return this;
        }


        public BankAccountDto build(){
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

    public int getAccountPropertiesId() {
        return accountPropertiesId;
    }

    public void setAccountPropertiesId(int accountPropertiesId) {
        this.accountPropertiesId = accountPropertiesId;
    }
}

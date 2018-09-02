package model.entity;

public class DepositAccount extends BankAccount {

    private DepositTariff depositTariff;

    //protected int accountPropertiesId;

    private int depositRate;
    private int depositAmount;

    public DepositTariff getDepositTariff() {
        return depositTariff;
    }

    public void setDepositTariff(DepositTariff depositTariff) {
        this.depositTariff = depositTariff;
    }

    public int getDepositRate() {
        return depositRate;
    }

    public void setDepositRate(int depositRate) {
        this.depositRate = depositRate;
    }

    public int getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(int depositAmount) {
        this.depositAmount = depositAmount;
    }

}

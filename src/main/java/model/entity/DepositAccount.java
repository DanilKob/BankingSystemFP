package model.entity;

public class DepositAccount extends BankAccount {

    private DepositTariff depositTariff;

    private int depositAmount;

    public DepositTariff getDepositTariff() {
        return depositTariff;
    }

    public void setDepositTariff(DepositTariff depositTariff) {
        this.depositTariff = depositTariff;
    }

    public int getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(int depositAmount) {
        this.depositAmount = depositAmount;
    }

}

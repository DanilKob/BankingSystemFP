package controller.dto;

public class DepositAccountDto extends BankAccountDto {
    private int deposit;

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }
}

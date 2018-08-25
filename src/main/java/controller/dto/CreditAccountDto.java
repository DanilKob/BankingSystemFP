package controller.dto;

public class CreditAccountDto extends BankAccountDto{
    private int creditId;

    public int getCreditId() {
        return creditId;
    }

    public void setCreditId(int creditId) {
        this.creditId = creditId;
    }
}

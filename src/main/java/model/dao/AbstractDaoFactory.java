package model.dao;

public abstract class AbstractDaoFactory {
    public abstract UserDao createUserDao();
    public abstract BankAccountDao createBankAccountDao();
    public abstract CreditDao createCreditDao();
    public abstract DepositDao createDepositDao();
    public abstract HistoryDao createHistoryDao();
    public abstract CreditTariffDao createCreditTariffDao();
    public abstract DepositTariffDao createDepositTariffDao();
}

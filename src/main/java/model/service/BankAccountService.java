package model.service;

import controller.dto.CreditAccountDto;
import controller.dto.DepositAccountDto;
import controller.dto.RequisitionDto;
import model.dao.CreditDao;
import model.dao.DepositDao;
import model.dao.config.DataBaseConfiguration;
import model.entity.CreditAccount;
import model.entity.DepositAccount;
import model.entity.enums.Account;
import model.exception.NotUniqueException;

import java.util.List;

public class BankAccountService {
    public static void registerCreditAccount(CreditAccountDto creditAccountDto) throws NotUniqueException {
        try(CreditDao creditDao = DataBaseConfiguration.factory.createCreditDao()){
            CreditAccount creditAccount= convertCreditDtoToEntity(creditAccountDto);
            creditDao.create(creditAccount);
        }
    }

    public static void registerDepositAccount(DepositAccountDto depositAccountDto) throws NotUniqueException {
        try(DepositDao depositDao = DataBaseConfiguration.factory.createDepositDao()){
            DepositAccount depositAccount = convertDepositDtoToEntity(depositAccountDto);
            depositDao.create(depositAccount);
        }
    }

    public static CreditAccount getCreditAccount(int id){
        try(CreditDao creditDao = DataBaseConfiguration.factory.createCreditDao()){
            CreditAccount creditAccount = creditDao.findById(id);
            return creditAccount;
        }
    }

    public static DepositAccount getDepositAccount(int id){
        try(DepositDao depositDao = DataBaseConfiguration.factory.createDepositDao()){
            DepositAccount depositAccount = depositDao.findById(id);
            return depositAccount;
        }
    }

    public static boolean payFromCredit(RequisitionDto requisitionDto){
        int fromAccountId = requisitionDto.getFromAccountId();
        int fromUserId = requisitionDto.getFromUserId();
        int toAccountId = requisitionDto.getToAccountId();
        int toUserId = requisitionDto.getToUserId();
        int balance = requisitionDto.getBalance();

        try(CreditDao creditDao = DataBaseConfiguration.factory.createCreditDao()){
            return creditDao.pay(fromAccountId,fromUserId,toAccountId,toUserId,balance);
        }
    }

    public static List<CreditAccount> getAllCreditAccountsByUserId(int id){
        try(CreditDao creditDao = DataBaseConfiguration.factory.createCreditDao()){
            return creditDao.findAllByUserId(id);
        }
    }

    public static List<DepositAccount> getAllDepositAccountsByUserId(int id){
        try(DepositDao depositDao = DataBaseConfiguration.factory.createDepositDao()){
            return depositDao.findAllByUserId(id);
        }
    }


    //public static void payFromDepositAccount()
    // todo Refactor convert method
    public static CreditAccount convertCreditDtoToEntity(CreditAccountDto creditAccountDto){
        CreditAccount creditAccount = new CreditAccount();
        creditAccount.setUserId(creditAccountDto.getUserId());
        creditAccount.setCreditId(creditAccountDto.getAccountPropertiesId());
        //creditAccount.setCreditId(creditAccountDto.getCreditId());
        creditAccount.setAccountType(Account.UNCONFIRMED_CREDIT);
        // todo add date
        return creditAccount;
    }

    public static DepositAccount convertDepositDtoToEntity(DepositAccountDto depositAccountDto){
        DepositAccount depositAccount = new DepositAccount();
        depositAccount.setUserId(depositAccountDto.getUserId());
        depositAccount.setDepositId(depositAccountDto.getAccountPropertiesId());
        //depositAccount.setDepositId(depositAccountDto.getDeposit());
        depositAccount.setAccountType(Account.DEPOSIT);
        // todo add date
        return depositAccount;
    }
}

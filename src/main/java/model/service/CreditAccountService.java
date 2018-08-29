package model.service;

import controller.dto.CreditAccountDto;
import controller.dto.RequisitionDto;
import model.dao.CreditDao;
import model.dao.config.DataBaseConfiguration;
import model.entity.CreditAccount;
import model.entity.enums.Account;
import model.exception.NotUniqueException;

import java.util.List;

public class CreditAccountService {
    public static void registerCreditAccount(CreditAccountDto creditAccountDto) throws NotUniqueException {
        try(CreditDao creditDao = DataBaseConfiguration.factory.createCreditDao()){
            CreditAccount creditAccount= convertCreditDtoToEntity(creditAccountDto);
            creditDao.create(creditAccount);
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

    public static CreditAccount getConfirmedCreditAccount(int id){
        try(CreditDao creditDao = DataBaseConfiguration.factory.createCreditDao()){
            CreditAccount creditAccount = creditDao.findById(id);
            creditAccount.setAccountType(Account.CREDIT);
            return creditAccount;
        }
    }
}

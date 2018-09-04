package model.service;

import controller.dto.CreditAccountDto;
import model.dao.CreditDao;
import model.dao.CreditTariffDao;
import model.dao.config.DataBaseConfiguration;
import model.entity.CreditAccount;
import model.entity.CreditTariff;
import model.entity.Requsition;
import model.entity.enums.Account;
import model.exception.NotUniqueException;
import model.exception.TariffNotExistException;

import java.util.List;

public class CreditAccountService {
    /*
    public static void createCreditAccount(CreditAccount creditAccount) throws NotUniqueException {
        creditAccount.setAccountType(Account.UNCONFIRMED_CREDIT);
        try(CreditDao creditDao = DataBaseConfiguration.factory.createCreditDao()){
            creditDao.create(creditAccount);
        }
    }
    */

    public static void registerCreditAccount(CreditAccount creditAccount) throws TariffNotExistException {
        creditAccount.setAccountType(Account.UNCONFIRMED_CREDIT);
        try(CreditDao creditDao = DataBaseConfiguration.factory.createCreditDao()){
            creditDao.registerCredit(creditAccount);
        }
    }

    /*
    public static boolean payFromCredit(Requsition requsition){
        int fromAccountId = requsition.getFromAccountId();
        int fromUserId = requsition.getFromUserId();
        int toAccountId = requsition.getToAccountId();
        int toUserId = requsition.getToUserId();
        int balance = requsition.getBalance();

        try(CreditDao creditDao = DataBaseConfiguration.factory.createCreditDao()){
            return creditDao.pay(fromAccountId,fromUserId,toAccountId,toUserId,balance);
        }
    }
    */
    public static List<CreditAccount> getAllCreditAccountsByUserId(int id){
        try(CreditDao creditDao = DataBaseConfiguration.factory.createCreditDao()){
            return creditDao.findAllConfirmedByUserId(id);
        }
    }

    public static List<CreditAccount> getAllUnconfirmed(int userId){
        try(CreditDao creditDao = DataBaseConfiguration.factory.createCreditDao()){
            return creditDao.findAllUnconfirmedCreditsByUserId(userId);
        }
    }

    public static CreditAccount getConfirmedCreditAccount(int id){
        try(CreditDao creditDao = DataBaseConfiguration.factory.createCreditDao()){
            CreditAccount creditAccount = creditDao.findById(id);
            creditAccount.setAccountType(Account.CREDIT);
            return creditAccount;
        }
    }

    public static List<CreditTariff> getAllCreditTariffs(){
        try(CreditTariffDao creditTariffDao = DataBaseConfiguration.factory.createCreditTariffDao()){
            return creditTariffDao.findAll();
        }
    }
    /*
    //public static void payFromDepositAccount()
    // todo Refactor convert method
    public static CreditAccount convertCreditDtoToEntity(CreditAccountDto creditAccountDto){
        CreditAccount creditAccount = new CreditAccount();
        creditAccount.setUserId(creditAccountDto.getUserId());
        creditAccount.setCreditId(creditAccountDto.getAccountPropertiesId());
        //creditAccount.setId(creditAccountDto.getId());
        creditAccount.setAccountType(Account.UNCONFIRMED_CREDIT);
        // todo add date
        return creditAccount;
    }
    */

}

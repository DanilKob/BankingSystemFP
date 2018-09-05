package model.service;

import controller.dto.CreditAccountDto;
import controller.dto.DepositAccountDto;
import controller.dto.RequisitionDto;
import model.dao.BankAccountDao;
import model.dao.CreditDao;
import model.dao.DepositDao;
import model.dao.HistoryDao;
import model.dao.config.DataBaseConfiguration;
import model.entity.*;
import model.entity.enums.Account;
import model.exception.BankAccountNotExistException;
import model.exception.NotUniqueException;

import java.util.LinkedList;
import java.util.List;

public class BankAccountService {
    public static List<BankAccount> getAllConfirmedBankAccount(int userId){
        try(BankAccountDao bankAccountDao = DataBaseConfiguration.factory.createBankAccountDao()){
            return bankAccountDao.findAllBankAccountByUserId(userId);
        }
    }
    public static List<History> getAllHistoryByAccountId(int accountId, int userId){
        try(HistoryDao historyDao = DataBaseConfiguration.factory.createHistoryDao()){
            return historyDao.findAllbyBankAccountId(accountId,userId);
        }
    }

    public static boolean pay(Requsition requsition) throws BankAccountNotExistException {
        int fromAccountId = requsition.getFromAccountId();
        int fromUserId = requsition.getFromUserId();
        int toAccountId = requsition.getToAccountId();
        int balance = requsition.getBalance();

        try(BankAccountDao bankAccountDao = DataBaseConfiguration.factory.createBankAccountDao()){
            return bankAccountDao.pay(fromAccountId,fromUserId,toAccountId,balance);
        }
    }
}

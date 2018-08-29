package model.service;

import controller.dto.CreditAccountDto;
import controller.dto.DepositAccountDto;
import controller.dto.RequisitionDto;
import model.dao.BankAccountDao;
import model.dao.CreditDao;
import model.dao.DepositDao;
import model.dao.HistoryDao;
import model.dao.config.DataBaseConfiguration;
import model.entity.BankAccount;
import model.entity.CreditAccount;
import model.entity.DepositAccount;
import model.entity.History;
import model.entity.enums.Account;
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
}

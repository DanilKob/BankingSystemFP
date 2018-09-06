package model.service;

import controller.dto.DepositAccountDto;
import model.dao.DepositDao;
import model.dao.DepositTariffDao;
import model.dao.config.DataBaseConfiguration;
import model.entity.DepositAccount;
import model.entity.DepositTariff;
import model.entity.enums.Account;
import model.exception.BankAccountNotExistException;
import model.exception.NotUniqueException;
import model.exception.TariffNotExistException;

import java.util.List;

public class DepositAccountService {
    public static void registerDepositAccount(DepositAccount depositAccount) throws TariffNotExistException {
        try(DepositDao depositDao = DataBaseConfiguration.factory.createDepositDao()){
            depositAccount.setAccountType(Account.DEPOSIT);
            depositDao.registerDeposit(depositAccount);
        }
    }

    public static DepositAccount getDepositAccount(int id) throws BankAccountNotExistException {
        try(DepositDao depositDao = DataBaseConfiguration.factory.createDepositDao()){
            DepositAccount depositAccount = depositDao.findById(id);
            depositAccount.setAccountType(Account.DEPOSIT);
            return depositAccount;
        }
    }

    public static List<DepositAccount> getAllDepositAccountsByUserId(int id){
        try(DepositDao depositDao = DataBaseConfiguration.factory.createDepositDao()){
            return depositDao.findAllByUserId(id);
        }
    }

    public static DepositAccount convertDepositDtoToEntity(DepositAccountDto depositAccountDto){
        DepositAccount depositAccount = new DepositAccount();
        depositAccount.setUserId(depositAccountDto.getUserId());
        depositAccount.getDepositTariff().setId(depositAccountDto.getAccountPropertiesId());
        //depositAccount.setDepositId(depositAccountDto.getDeposit());
        depositAccount.setAccountType(Account.DEPOSIT);
        // todo add date
        return depositAccount;
    }

    public static List<DepositTariff> getAllDepositTariffs(){
        try(DepositTariffDao depositTariffDao = DataBaseConfiguration.factory.createDepositTariffDao()){
            return depositTariffDao.findAll();
        }
    }

    public static List<DepositAccount> getAllDepositsByUserBankAcountId(int bankAccountId){
        try(DepositDao depositDao = DataBaseConfiguration.factory.createDepositDao()){
            return depositDao.findAllByUserBankAccountId(bankAccountId);
        }
    }
}

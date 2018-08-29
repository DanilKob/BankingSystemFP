package model.service;

import controller.dto.DepositAccountDto;
import model.dao.DepositDao;
import model.dao.config.DataBaseConfiguration;
import model.entity.DepositAccount;
import model.entity.enums.Account;
import model.exception.NotUniqueException;

import java.util.List;

public class DepositAccountService {
    public static void registerDepositAccount(DepositAccountDto depositAccountDto) throws NotUniqueException {
        try(DepositDao depositDao = DataBaseConfiguration.factory.createDepositDao()){
            DepositAccount depositAccount = convertDepositDtoToEntity(depositAccountDto);
            depositDao.create(depositAccount);
        }
    }

    public static DepositAccount getDepositAccount(int id){
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
        depositAccount.setDepositId(depositAccountDto.getAccountPropertiesId());
        //depositAccount.setDepositId(depositAccountDto.getDeposit());
        depositAccount.setAccountType(Account.DEPOSIT);
        // todo add date
        return depositAccount;
    }
}

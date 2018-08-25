package model.dao.mapper;

import model.dao.statement.tables.BankAccountTable;
import model.dao.statement.tables.DepositTable;
import model.entity.DepositAccount;
import model.entity.enums.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepositMapper extends GenericMapper<Integer,DepositAccount> {
    @Override
    public DepositAccount extractFromResultSet(ResultSet rs) throws SQLException {
        DepositAccount depositAccount = new DepositAccount();
        ///
        depositAccount.setId(rs.getInt(BankAccountTable.BANK_ACCOUNT_ID));
        depositAccount.setBalance(rs.getInt(BankAccountTable.BANK_ACCOUNT_BALANCE));
        depositAccount.setName(rs.getString(DepositTable.DEPOSIT_NAME));
        depositAccount.setAccountType(Account.DEPOSIT);
        depositAccount.setDepositId(rs.getInt(BankAccountTable.BANK_ACCOUNT_DEPOSIT_ID));
        //
        // todo add deposit properties sets
        //depositAccount.setDepositAmount(rs.getInt());
        //depositAccount.setLimit(rs.getInt());

        return depositAccount;
    }

    @Override
    public Integer getKey(DepositAccount entity) {
        return entity.getId();
    }
}

package model.dao.mapper;

import model.dao.statement.tables.BankAccountTable;
import model.dao.statement.tables.CreditTable;
import model.entity.CreditAccount;
import model.entity.enums.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditMapper extends GenericMapper<Integer,CreditAccount> {
    @Override
    public CreditAccount extractFromResultSet(ResultSet rs) throws SQLException {
        CreditAccount creditAccount = new CreditAccount();
        creditAccount.setId(rs.getInt(BankAccountTable.BANK_ACCOUNT_ID));
        creditAccount.setBalance(rs.getInt(BankAccountTable.BANK_ACCOUNT_BALANCE));
        //
        creditAccount.setAccountType(Account.CREDIT);
        creditAccount.setCreditId(rs.getInt(BankAccountTable.BANK_ACCOUNT_CREDIT_ID));
        creditAccount.setLimit(rs.getInt(CreditTable.CREDIT_LIMIT));
        creditAccount.setName(rs.getString(CreditTable.CREDIT_NAME));
        return creditAccount;
    }

    @Override
    public Integer getKey(CreditAccount entity) {
        return entity.getId();
    }
}

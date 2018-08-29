package controller.command;

import controller.PagesName;
import controller.Parameters;
import model.dao.statement.Statements;
import model.entity.BankAccount;
import model.entity.User;
import model.service.BankAccountService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BankAccountsCommand extends AbstractBankAccountInfo{
    @Override
    public String execute(HttpServletRequest request) {
        int userId = ((User)request.getSession().getAttribute(Parameters.USER)).getId();
        System.out.println(Statements.SELECT_ALL_BANK_ACCOUNT_BY_USER_ID);
        List<BankAccount> bankAccountList = BankAccountService.getAllConfirmedBankAccount(userId);
        
        for (BankAccount bankAccount : bankAccountList) {
            System.out.println(bankAccount.getAccountType());
        }
        super.createComplianceTableInSession(request,bankAccountList);

        request.setAttribute(Parameters.BANK_ACCOUNTS,bankAccountList);
        return PagesName.USER_HOME_PAGE;
    }
}

package controller.command;

import controller.PagesName;
import controller.Parameters;
import controller.crypter.Crypter;
import model.entity.CreditAccount;
import model.entity.User;
import model.service.BankAccountService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class CreditsCommand implements Command{

    @Override
    public String execute(HttpServletRequest request) {
        if(((User)request.getSession().getAttribute(Parameters.USER))==null) System.out.println("USER IS NULL");

        int userId = ((User)request.getSession().getAttribute(Parameters.USER)).getId();
        List<CreditAccount> creditAccounts = BankAccountService.getAllCreditAccountsByUserId(userId);
        /*
        Crypter<CreditAccount> creditAccountCrypter = new Crypter<>();
        creditAccounts = creditAccountCrypter.cryptEntityId(creditAccounts);
        Map<Integer,Integer> complianceTable = creditAccountCrypter.getComplianceTable();
        */

        Crypter<CreditAccount> creditAccountCrypter = new Crypter<>();
        Map<Integer,Integer> complianceTable = creditAccountCrypter.getComplianceTable(creditAccounts);
        request.getSession().setAttribute(Parameters.COMPLIANCE_TABLE,complianceTable);

        request.setAttribute(Parameters.CREDITS,creditAccounts);
        System.out.println("IN CREDIT COMMAND !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        //return CommandConstants.REDIRECT+PagesName.CREDITS;
        return PagesName.USER_HOME_PAGE;
    }
}

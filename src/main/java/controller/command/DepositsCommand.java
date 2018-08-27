package controller.command;

import controller.PagesName;
import controller.Parameters;
import controller.crypter.Crypter;
import model.entity.CreditAccount;
import model.entity.DepositAccount;
import model.entity.User;
import model.service.BankAccountService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class DepositsCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        // todo remove this if
        if((request.getSession().getAttribute(Parameters.USER))==null) System.out.println("USER IS NULL");
        int userId = ((User)request.getSession().getAttribute(Parameters.USER)).getId();
        List<DepositAccount> depositAccountList = BankAccountService.getAllDepositAccountsByUserId(userId);

        Crypter<DepositAccount> depositAccountCrypter = new Crypter<>();
        depositAccountList = depositAccountCrypter.cryptEntityId(depositAccountList);
        Map<Integer,Integer> complianceTable = depositAccountCrypter.getComplianceTable();
        request.getSession().setAttribute(Parameters.DEPOSIT_COMPLIANCE_TABLE,complianceTable);

        request.setAttribute(Parameters.DEPOSITS,depositAccountList);
        System.out.println("IN DEPOSIT COMMAND !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        //return CommandConstants.REDIRECT+PagesName.CREDITS;
        return PagesName.USER_HOME_PAGE;
    }
}

package controller.command;

import controller.PagesName;
import controller.Parameters;
import model.entity.DepositAccount;
import model.entity.User;
import model.service.DepositAccountService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DepositsCommand extends AbstractBankAccountInfo{
    @Override
    public String execute(HttpServletRequest request) {
        // todo remove this if
        if((request.getSession().getAttribute(Parameters.USER))==null) System.out.println("USER IS NULL");
        int userId = ((User)request.getSession().getAttribute(Parameters.USER)).getId();
        List<DepositAccount> depositAccountList = DepositAccountService.getAllDepositAccountsByUserId(userId);

        /*
        Crypter<DepositAccount> depositAccountCrypter = new Crypter<>();
        depositAccountList = depositAccountCrypter.cryptEntityId(depositAccountList);
        Map<Integer,Integer> complianceTable = depositAccountCrypter.getComplianceTable();
        */
        /*
        Crypter<DepositAccount> depositAccountCrypter = new Crypter<>();
        Map<Integer,Integer> complianceTable = depositAccountCrypter.getComplianceTable(depositAccountList);
        request.getSession().setAttribute(Parameters.COMPLIANCE_TABLE,complianceTable);
        */
        super.createComplianceTableInSession(request,depositAccountList);

        request.setAttribute(Parameters.DEPOSITS,depositAccountList);
        System.out.println("IN DEPOSIT COMMAND !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        //return CommandConstants.REDIRECT+PagesName.CREDITS;
        return PagesName.USER_HOME_PAGE;
    }
}

package controller.command;

import controller.PagesName;
import controller.Parameters;
import model.entity.CreditAccount;
import model.entity.User;
import model.service.CreditAccountService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CreditsCommand extends AbstractBankAccountInfo{

    @Override
    public String execute(HttpServletRequest request) {
        if(((User)request.getSession().getAttribute(Parameters.USER))==null) System.out.println("USER IS NULL");

        int userId = ((User)request.getSession().getAttribute(Parameters.USER)).getId();
        List<CreditAccount> creditAccounts = CreditAccountService.getAllCreditAccountsByUserId(userId);

        super.createComplianceTableInSession(request,creditAccounts);

        request.setAttribute(Parameters.CREDITS,creditAccounts);
        System.out.println("IN CREDIT COMMAND !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        //return CommandConstants.REDIRECT+PagesName.CREDITS;
        return PagesName.USER_HOME_PAGE;
    }
}

package controller.command;

import controller.PagesName;
import controller.Parameters;
import model.entity.CreditAccount;
import model.entity.User;
import model.service.CreditAccountService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Deprecated
public class CreditsCommand extends AbstractBankAccountInfo{

    @Override
    public String execute(HttpServletRequest request) {

        int userId = ((User)request.getSession().getAttribute(Parameters.USER)).getId();
        List<CreditAccount> creditAccounts = CreditAccountService.getAllCreditAccountsByUserId(userId);

        super.createComplianceTableInSession(request,creditAccounts);

        request.setAttribute(Parameters.CREDITS,creditAccounts);
        //return CommandConstants.REDIRECT+PagesName.CREDITS;
        return PagesName.USER_HOME_PAGE;
    }
}

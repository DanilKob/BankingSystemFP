package controller.command;

import controller.PagesName;
import controller.Parameters;
import model.entity.DepositAccount;
import model.entity.User;
import model.service.DepositAccountService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Deprecated
public class DepositsCommand extends AbstractBankAccountInfo{
    @Override
    public String execute(HttpServletRequest request) {
        // todo remove this if
        int userId = ((User)request.getSession().getAttribute(Parameters.USER)).getId();
        List<DepositAccount> depositAccountList = DepositAccountService.getAllDepositAccountsByUserId(userId);
        super.createComplianceTableInSession(request,depositAccountList);
        request.setAttribute(Parameters.DEPOSITS,depositAccountList);
        //return CommandConstants.REDIRECT+PagesName.CREDITS;
        return PagesName.USER_HOME_PAGE;
    }
}

package controller.command;

import controller.PagesName;
import controller.Parameters;
import controller.crypter.SecurityEntity;
import model.entity.CreditAccount;
import model.entity.User;
import model.service.CreditAccountService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserUnconfirmedCreditsCommand extends AbstractBankAccountInfo{
    @Override
    public String execute(HttpServletRequest request) {
        if((request.getSession().getAttribute(Parameters.USER))==null) System.out.println("USER IS NULL");
        int userId = ((User)request.getSession().getAttribute(Parameters.USER)).getId();
        List<CreditAccount> unconfirmedCreditAccountList = CreditAccountService.getAllUnconfirmedBuUserId(userId);
        //super.createComplianceTableInSession(request,unconfirmedCreditAccountList);
        List<SecurityEntity<CreditAccount>> securityUnconfirmedCreditsList = super
                .cryptEntityList(request,unconfirmedCreditAccountList);

        request.setAttribute(Parameters.UNCONFIRMED_CREDITS,securityUnconfirmedCreditsList);
        System.out.println("IN UNCONFIRMED CREDIT COMMAND !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        //return CommandConstants.REDIRECT+PagesName.CREDITS;
        return PagesName.USER_HOME_PAGE;
    }
}

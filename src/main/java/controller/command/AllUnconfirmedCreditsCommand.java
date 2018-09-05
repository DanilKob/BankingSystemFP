package controller.command;

import controller.PagesName;
import controller.Parameters;
import controller.crypter.SecurityEntity;
import model.entity.CreditAccount;
import model.service.CreditAccountService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllUnconfirmedCreditsCommand extends AbstractBankAccountInfo {
    @Override
    public String execute(HttpServletRequest request) {
        List<CreditAccount> unconfirmedCreditsList = CreditAccountService.getAllUnconfirmedCredits();
        List<SecurityEntity<CreditAccount>> securityEntityList = super.cryptEntityList(request,unconfirmedCreditsList);
        request.setAttribute(Parameters.ALL_UNCONFIRMED_CREDITS,securityEntityList);
        return PagesName.ADMIN_HOME_PAGE;
    }
}

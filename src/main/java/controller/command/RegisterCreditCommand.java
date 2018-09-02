package controller.command;

import controller.PagesName;
import controller.Parameters;
import model.entity.CreditAccount;
import model.exception.TariffNotExistException;
import model.service.CreditAccountService;

import javax.servlet.http.HttpServletRequest;

public class RegisterCreditCommand extends AbstractBankAccountInfo{
    @Override
    public String execute(HttpServletRequest request) {
        int userId = super.getUserIdInSession(request);
        int creditTariffId = (int)request.getAttribute(Parameters.CREDIT_TARIFF_ID);

        CreditAccount creditAccount = new CreditAccount();
        // todo
        creditAccount.getCreditTariff().setId(creditTariffId);
        creditAccount.setUserId(userId);
        try {
            CreditAccountService.registerCreditAccount(creditAccount);
        } catch (TariffNotExistException e) {
            // todo forward to registrayion credit page
            return CommandConstants.REDIRECT + PagesName.USER_HOME_PAGE;
        }
        return CommandConstants.REDIRECT + PagesName.USER_HOME_PAGE;
    }
}

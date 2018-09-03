package controller.command;

import controller.Parameters;
import model.entity.CreditAccount;
import model.entity.CreditTariff;
import model.exception.TariffNotExistException;
import model.service.CreditAccountService;

import javax.servlet.http.HttpServletRequest;

public class RegisterCreditCommand extends AbstractBankAccountInfo{
    @Override
    public String execute(HttpServletRequest request) {
        int userId = super.getUserIdInSession(request);
        int creditTariffId = Integer.parseInt(request.getParameter(Parameters.CREDIT_TARIFF_ID));

        CreditAccount creditAccount = new CreditAccount();
        // todo
        CreditTariff creditTariff = new CreditTariff();
        creditTariff.setId(creditTariffId);
        creditAccount.setCreditTariff(creditTariff);
        creditAccount.setUserId(userId);
        try {
            CreditAccountService.registerCreditAccount(creditAccount);
        } catch (TariffNotExistException e) {
            // todo forward to registrayion credit page
            e.printStackTrace();
            return CommandConstants.REDIRECT + CommandConstants.SET_COMMAND + CommandConstants.USER_HOME_PAGE_COMMAND;
        }
        return CommandConstants.REDIRECT + CommandConstants.SET_COMMAND + CommandConstants.USER_HOME_PAGE_COMMAND;
    }
}

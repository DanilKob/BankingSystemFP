package controller.command;

import controller.Parameters;
import controller.utility.IOHandler;
import controller.utility.Languages;
import model.entity.CreditAccount;
import model.entity.CreditTariff;
import model.exception.TariffNotExistException;
import model.service.CreditAccountService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RegisterCreditCommand extends AbstractBankAccountInfo{
    @Override
    public String execute(HttpServletRequest request) {
        int userId = super.getUserIdInSession(request);
        int creditTariffId = Integer.parseInt(request.getParameter(Parameters.CREDIT_TARIFF_ID));

        CreditAccount creditAccount = new CreditAccount();
        CreditTariff creditTariff = new CreditTariff();
        creditTariff.setId(creditTariffId);
        creditAccount.setCreditTariff(creditTariff);
        creditAccount.setUserId(userId);
        try {
            CreditAccountService.registerCreditAccount(creditAccount);
        } catch (TariffNotExistException e) {
            e.printStackTrace();
            Logger.getLogger(RegisterCreditCommand.class.getName()).info(TariffNotExistException.class.getName(),e);
            Languages language = IOHandler.getLanguageFromRequest(request);

            return CommandConstants.REDIRECT + CommandConstants.SET_COMMAND
                    + CommandConstants.USER_HOME_PAGE_COMMAND + IOHandler.getTarrifNotExistParam(language);
        }
        return CommandConstants.REDIRECT + CommandConstants.SET_COMMAND + CommandConstants.USER_HOME_PAGE_COMMAND;
    }
}

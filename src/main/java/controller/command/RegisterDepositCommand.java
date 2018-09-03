package controller.command;

import controller.Parameters;
import model.entity.DepositAccount;
import model.entity.DepositTariff;
import model.exception.NotUniqueException;
import model.exception.TariffNotExistException;
import model.service.DepositAccountService;

import javax.servlet.http.HttpServletRequest;

public class RegisterDepositCommand extends AbstractBankAccountInfo{
    @Override
    public String execute(HttpServletRequest request) {
        int userId = super.getUserIdInSession(request);
        int depositTariffId = Integer.parseInt(request.getParameter(Parameters.DEPOSIT_TARIFF_ID));
        int balance = Integer.parseInt(request.getParameter(Parameters.BALANCE));

        DepositAccount depositAccount = new DepositAccount();
        DepositTariff depositTariff = new DepositTariff();
        depositTariff.setId(depositTariffId);
        depositAccount.setDepositTariff(depositTariff);
        depositAccount.setUserId(userId);
        depositAccount.setBalance(balance);

        try {
            DepositAccountService.registerDepositAccount(depositAccount);
        } catch (TariffNotExistException e) {
            e.printStackTrace();
            // todo redirect to error
            //return CommandConstants.REDIRECT + CommandConstants.USER_HOME_COMMAND;
            return CommandConstants.REDIRECT + CommandConstants.SET_COMMAND + CommandConstants.USER_HOME_COMMAND;
        }
        // todo set message
        return CommandConstants.REDIRECT + CommandConstants.SET_COMMAND + CommandConstants.USER_HOME_COMMAND;
    }
}

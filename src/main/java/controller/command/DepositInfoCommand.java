package controller.command;

import controller.PagesName;
import controller.Parameters;
import model.entity.DepositAccount;
import model.entity.User;
import model.service.BankAccountService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class DepositInfoCommand extends AbstractBankAccountInfo {
    @Override
    public String execute(HttpServletRequest request) {
        // todo ClassCastException

        int depositIdFromPage = Integer.parseInt(request.getParameter(Parameters.SERIAL_ID_FROM_PAGE));
        int realCreditId = super.unCryptBankAccountId(request,depositIdFromPage);

        System.out.println("Deposit id " + depositIdFromPage);
        System.out.println("Real deposit id " + realCreditId);

        DepositAccount depositAccount = BankAccountService.getDepositAccount(realCreditId);

        System.out.println("Deposit is null " + depositAccount==null);


        request.setAttribute(Parameters.DEPOSIT_ACCOUNT,depositAccount);

        return /*CommandConstants.REDIRECT +*/ PagesName.DEPOSIT_PAGE;
    }
}

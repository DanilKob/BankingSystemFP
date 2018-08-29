package controller.command;

import controller.PagesName;
import controller.Parameters;
import model.entity.DepositAccount;
import model.service.DepositAccountService;

import javax.servlet.http.HttpServletRequest;

public class DepositInfoCommand extends AbstractBankAccountInfo {
    @Override
    public String execute(HttpServletRequest request) {
        // todo ClassCastException

        //int depositIdFromPage = Integer.parseInt(request.getParameter(Parameters.FAKE_ID_FROM_PAGE));
        int realCreditId = super.decryptBankAccountIdFromRequest(request);


        DepositAccount depositAccount = DepositAccountService.getDepositAccount(realCreditId);

        System.out.println("Deposit is null " + depositAccount==null);


        request.setAttribute(Parameters.DEPOSIT_ACCOUNT,depositAccount);

        //return /*CommandConstants.REDIRECT +*/ PagesName.DEPOSIT_PAGE;
        return PagesName.BANK_ACCOUNT_PAGE;
    }
}

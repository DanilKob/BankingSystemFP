package controller.command;

import controller.PagesName;
import controller.Parameters;
import model.entity.CreditAccount;
import model.service.CreditAccountService;

import javax.servlet.http.HttpServletRequest;

public class CreditInfoCommand extends AbstractBankAccountInfo{
    @Override
    public String execute(HttpServletRequest request) {
        // todo ClassCastException

       // int creditIdFromPage = Integer.parseInt(request.getParameter(Parameters.FAKE_ID_FROM_PAGE));
        int realCreditId = super.decryptBankAccountIdFromRequest(request);

        System.out.println("Real credit id " + realCreditId);

        CreditAccount creditAccount = CreditAccountService.getConfirmedCreditAccount(realCreditId);

        System.out.println(creditAccount.getIndebtedness());

        request.setAttribute(Parameters.CREDIT_ACCOUNT,creditAccount);
        //return /*CommandConstants.REDIRECT +*/ PagesName.CREDIT_PAGE;
        return PagesName.BANK_ACCOUNT_PAGE;
    }
}

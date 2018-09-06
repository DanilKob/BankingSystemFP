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

        int realCreditId = super.decryptBankAccountIdFromRequest(request);

        CreditAccount creditAccount = CreditAccountService.getConfirmedCreditAccount(realCreditId);

        System.out.println(creditAccount.getIndebtedness());

        request.setAttribute(Parameters.CREDIT_ACCOUNT,creditAccount);
        //return /*CommandConstants.REDIRECT +*/ PagesName.CREDIT_PAGE;
        return PagesName.BANK_ACCOUNT_PAGE;
    }
}

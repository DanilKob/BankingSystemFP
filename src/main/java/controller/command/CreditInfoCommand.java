package controller.command;

import controller.PagesName;
import controller.Parameters;
import model.entity.CreditAccount;
import model.exception.BankAccountNotExistException;
import model.service.CreditAccountService;

import javax.servlet.http.HttpServletRequest;

public class CreditInfoCommand extends AbstractBankAccountInfo{
    @Override
    public String execute(HttpServletRequest request) {
        int realCreditId = super.decryptBankAccountIdFromRequest(request);
        CreditAccount creditAccount;
        try {
            creditAccount = CreditAccountService.getConfirmedCreditAccount(realCreditId);
        } catch (BankAccountNotExistException e) {
            return PagesName.ERROR;
        }

        request.setAttribute(Parameters.CREDIT_ACCOUNT,creditAccount);
        return PagesName.BANK_ACCOUNT_PAGE;
    }
}

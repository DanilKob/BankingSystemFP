package controller.command;

import controller.PagesName;
import controller.Parameters;
import model.entity.DepositAccount;
import model.exception.BankAccountNotExistException;
import model.service.DepositAccountService;

import javax.servlet.http.HttpServletRequest;

public class DepositInfoCommand extends AbstractBankAccountInfo {
    @Override
    public String execute(HttpServletRequest request) {
        // todo ClassCastException
        int realCreditId = super.decryptBankAccountIdFromRequest(request);
        DepositAccount depositAccount ;
        try {
            depositAccount = DepositAccountService.getDepositAccount(realCreditId);
        } catch (BankAccountNotExistException e) {
            return PagesName.ERROR;
        }
        request.setAttribute(Parameters.DEPOSIT_ACCOUNT,depositAccount);

        //return /*CommandConstants.REDIRECT +*/ PagesName.DEPOSIT_PAGE;
        return PagesName.BANK_ACCOUNT_PAGE;
    }
}

package controller.command;

import controller.PagesName;
import model.service.CreditAccountService;

import javax.servlet.http.HttpServletRequest;

public class CancelCreditCommand extends AbstractBankAccountInfo {
    @Override
    public String execute(HttpServletRequest request) {
        int realCreditId = super.decryptBankAccountIdFromRequest(request);
        CreditAccountService.cancelCreditAccount(realCreditId);
        return PagesName.ADMIN_HOME_PAGE;
    }
}

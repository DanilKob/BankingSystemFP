package controller.command;

import controller.PagesName;
import controller.Parameters;
import model.service.CreditAccountService;

import javax.servlet.http.HttpServletRequest;

public class ConfirmCreditCommand extends AbstractBankAccountInfo {
    @Override
    public String execute(HttpServletRequest request) {
        int realCreditid = super.decryptBankAccountIdFromRequest(request);
        int indebtedness = Integer.parseInt(request.getParameter(Parameters.CREDIT_INDEBTEDNESS));
        CreditAccountService.confirmCreditAccount(realCreditid,indebtedness);
        return PagesName.ADMIN_HOME_PAGE;
    }
}

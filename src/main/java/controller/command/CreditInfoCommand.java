package controller.command;

import controller.PagesName;
import controller.Parameters;
import model.entity.CreditAccount;
import model.entity.User;
import model.service.BankAccountService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class CreditInfoCommand extends AbstractBankAccountInfo{
    @Override
    public String execute(HttpServletRequest request) {
        // todo ClassCastException

        int creditIdFromPage = Integer.parseInt(request.getParameter(Parameters.SERIAL_ID_FROM_PAGE));
        int realCreditId = super.unCryptBankAccountId(request,creditIdFromPage);

        System.out.println("Credit id" + creditIdFromPage);
        System.out.println("Real credit id " + realCreditId);

        CreditAccount creditAccount = BankAccountService.getCreditAccount(realCreditId);

        request.setAttribute(Parameters.CREDIT_ACCOUNT,creditAccount);
        return /*CommandConstants.REDIRECT +*/ PagesName.CREDIT_PAGE;
    }
}

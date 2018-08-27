package controller.command;

import controller.PagesName;
import controller.Parameters;
import model.entity.CreditAccount;
import model.entity.User;
import model.service.BankAccountService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class CreditPageCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        // todo ClassCastException


        Map<Integer,Integer> complianceTable = (Map<Integer, Integer>) request.getSession()
                .getAttribute(Parameters.CREDIT_COMPLIANCE_TABLE);
        if(complianceTable==null) {
            return CommandConstants.REDIRECT + PagesName.ERROR;
        }

        int creditIdFromPage = Integer.parseInt(request.getParameter(Parameters.CREDIT_ID));
        int realCreditId = complianceTable.get(creditIdFromPage);

        System.out.println("Credit id" + creditIdFromPage);
        System.out.println("Real credit id " + realCreditId);

        CreditAccount creditAccount = BankAccountService.getCreditAccount(realCreditId);


        request.setAttribute(Parameters.CREDIT_ACCOUNT,creditAccount);

        return /*CommandConstants.REDIRECT +*/ PagesName.CREDIT_PAGE;
    }
}

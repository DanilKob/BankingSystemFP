package controller.command;

import controller.PagesName;
import controller.Parameters;
import model.entity.DepositAccount;
import model.entity.User;
import model.service.BankAccountService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class DepositPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        // todo ClassCastException


        Map<Integer,Integer> complianceTable = (Map<Integer, Integer>) request.getSession()
                .getAttribute(Parameters.DEPOSIT_COMPLIANCE_TABLE);
        if(complianceTable==null) {
            return CommandConstants.REDIRECT + PagesName.ERROR;
        }

        int depositIdFromPage = Integer.parseInt(request.getParameter(Parameters.DEPOSIT_ID));
        int realCreditId = complianceTable.get(depositIdFromPage);

        System.out.println("Deposit id " + depositIdFromPage);
        System.out.println("Real deposit id " + realCreditId);

        DepositAccount depositAccount = BankAccountService.getDepositAccount(realCreditId);

        System.out.println("Deposit is null " + depositAccount==null);


        request.setAttribute(Parameters.DEPOSIT_ACCOUNT,depositAccount);

        return /*CommandConstants.REDIRECT +*/ PagesName.DEPOSIT_PAGE;
    }
}

package controller.command;

import controller.PagesName;
import controller.Parameters;
import model.entity.History;
import model.entity.User;
import model.service.BankAccountService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class HistoryInfoCommand extends AbstractBankAccountInfo {
    @Override
    public String execute(HttpServletRequest request) {
        int realBankAccountID = super.decryptBankAccountIdFromRequest(request);
        int userId = super.getUserIdInSession(request);
        List<History> historyList = BankAccountService.getAllHistoryByAccountId(realBankAccountID,userId);

        for (History history : historyList) {
            System.out.println("from " + history.getFromAccountId());
            System.out.println("to " + history.getToAccountId());
            System.out.println("user " + history.getUser());
            System.out.println("user name " + history.getUser().getLastName()+" "+history.getUser().getFirstName());
        }
        request.setAttribute(Parameters.BANK_ACCOUNT_HISTORY,historyList);
        return PagesName.BANK_ACCOUNT_PAGE;
    }
}

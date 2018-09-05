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

        request.setAttribute(Parameters.BANK_ACCOUNT_HISTORY,historyList);
        return PagesName.BANK_ACCOUNT_PAGE;
    }
}

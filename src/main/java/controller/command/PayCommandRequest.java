package controller.command;

import javax.servlet.http.HttpServletRequest;

public class PayCommandRequest extends AbstractBankAccountInfo{
    @Override
    public String execute(HttpServletRequest request) {
        int userIdInSession = super.getUserIdInSession(request);
        int realAccountId = super.decryptBankAccountIdFromRequest(request);

        return null;
    }
}

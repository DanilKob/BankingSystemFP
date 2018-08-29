package controller.command;

import controller.PagesName;
import controller.Parameters;
import model.entity.User;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class PayCommandRequest extends AbstractBankAccountInfo{
    @Override
    public String execute(HttpServletRequest request) {
        int userIdInSession = super.getUserIdInSession(request);
        int realAccountId = super.decryptBankAccountIdFromRequest(request);
        int payToId = Integer.parseInt(request.getParameter(Parameters.PAY_TO_ID));
        // todo check balance
        User user = UserService.findByBankAccountId(payToId);
        request.setAttribute(Parameters.USER,user);
        return PagesName.PAYMENT_PAGE;
    }
}

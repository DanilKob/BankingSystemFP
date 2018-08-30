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
        int payToId = Integer.parseInt(request.getParameter(Parameters.PAY_TO_ACCOUNT_ID));
        int price = Integer.parseInt(request.getParameter(Parameters.PAY_PRICE));
        // todo check balance
        User user = UserService.findByBankAccountId(payToId);
        request.setAttribute(Parameters.USER,user);
        request.setAttribute(Parameters.PAY_TO_ACCOUNT_ID,payToId);
        request.setAttribute(Parameters.PAY_PRICE,price);
        return PagesName.PAYMENT_PAGE;
    }
}

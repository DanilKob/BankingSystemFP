package controller.command;

import controller.PagesName;
import controller.Parameters;
import model.entity.Requsition;
import model.service.CreditAccountService;

import javax.servlet.http.HttpServletRequest;

public class PaymentConfirmationCommand extends AbstractBankAccountInfo{
    @Override
    public String execute(HttpServletRequest request) {
        System.out.println("In confirmation command!!!");
        System.out.println("User Id in session ::");
        int userIdInSession = super.getUserIdInSession(request);
        System.out.println(userIdInSession);
        System.out.println("Real Id in session ::");
        int realAccountId = super.decryptBankAccountIdFromRequest(request);
        System.out.println(realAccountId);

        System.out.println(Integer.parseInt(request.getParameter(Parameters.PAY_TO_ACCOUNT_ID)));
        int payToAccountId = Integer.parseInt(request.getParameter(Parameters.PAY_TO_ACCOUNT_ID));
        System.out.println(request.getParameter(Parameters.PAY_TO_USER_ID));
        int userToId =  Integer.parseInt(request.getParameter(Parameters.PAY_TO_USER_ID));

        int price = Integer.parseInt(request.getParameter(Parameters.PAY_PRICE));

        Requsition requsition = new Requsition();

        requsition.setFromAccountId(realAccountId);
        requsition.setFromUserId(userIdInSession);

        requsition.setToAccountId(payToAccountId);
        requsition.setToUserId(userToId);

        requsition.setBalance(price);

        boolean isPaymentSuccessful = CreditAccountService.payFromCredit(requsition);

        System.out.println("In confirmation command!!!");
        System.out.println("Success ? "+isPaymentSuccessful);

        request.setAttribute(Parameters.PAYMENT_SUCCESS,isPaymentSuccessful);

        // todo redirect to status pages
        //return CommandConstants.REDIRECT + CommandConstants.SET_COMMAND + CommandConstants.USER_HOME_PAGE_COMMAND;
        return PagesName.PAYMENT_PAGE;
    }
}

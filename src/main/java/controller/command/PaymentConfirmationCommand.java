package controller.command;

import controller.PagesName;
import controller.Parameters;
import model.entity.Requsition;
import model.exception.BankAccountNotExistException;
import model.service.BankAccountService;
import model.service.CreditAccountService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class PaymentConfirmationCommand extends AbstractBankAccountInfo{
    @Override
    public String execute(HttpServletRequest request) {
        int userIdInSession = super.getUserIdInSession(request);
        int realAccountId = super.decryptBankAccountIdFromRequest(request);
        int payToAccountId = Integer.parseInt(request.getParameter(Parameters.PAY_TO_ACCOUNT_ID));
        int price = Integer.parseInt(request.getParameter(Parameters.PAY_PRICE));

        Requsition requsition = new Requsition();

        requsition.setFromAccountId(realAccountId);
        requsition.setFromUserId(userIdInSession);
        requsition.setToAccountId(payToAccountId);
        requsition.setBalance(price);

        boolean isPaymentSuccessful;
        try {
            isPaymentSuccessful = BankAccountService.pay(requsition);
        } catch (BankAccountNotExistException e) {
            e.printStackTrace();
            // todo add logger
            // todo add message
            Logger.getLogger(LoggerConstants.DANGER_USER).warn("Account is not exist");
            return PagesName.ERROR;
        }

        Logger.getLogger(PaymentConfirmationCommand.class.getName()).info("Payment from user"
                +userIdInSession+", accountId "+realAccountId+ " to accountId " + payToAccountId);
        Logger.getLogger(PaymentConfirmationCommand.class.getName()).info("Was success ? "+isPaymentSuccessful);

        request.setAttribute(Parameters.PAYMENT_SUCCESS,isPaymentSuccessful);

        return PagesName.PAYMENT_PAGE;
    }
}

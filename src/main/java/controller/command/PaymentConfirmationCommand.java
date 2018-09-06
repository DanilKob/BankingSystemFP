package controller.command;

import controller.PagesName;
import controller.Parameters;
import controller.utility.IOHandler;
import controller.utility.Languages;
import model.entity.Requsition;
import model.exception.BankAccountNotExistException;
import model.service.BankAccountService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class PaymentConfirmationCommand extends AbstractBankAccountInfo{
    @Override
    public String execute(HttpServletRequest request) {
        Languages language = IOHandler.getLanguageFromRequest(request);
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
            Logger.getLogger(PaymentConfirmationCommand.class.getName()).info("Account is not exist");
            return CommandConstants.REDIRECT + CommandConstants.SET_COMMAND
                    + CommandConstants.USER_HOME_PAGE_COMMAND + IOHandler.getAccountisNotExistParam(language);
        }

        Logger.getLogger(PaymentConfirmationCommand.class.getName()).info("Payment from user"
                +userIdInSession+", accountId "+realAccountId+ " to accountId " + payToAccountId);
        Logger.getLogger(PaymentConfirmationCommand.class.getName()).info("Was success ? "+isPaymentSuccessful);

        request.setAttribute(Parameters.PAYMENT_SUCCESS,isPaymentSuccessful);

        return PagesName.PAYMENT_PAGE;
    }
}

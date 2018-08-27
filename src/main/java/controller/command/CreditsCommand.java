package controller.command;

import controller.PagesName;
import controller.Parameters;
import model.entity.CreditAccount;
import model.entity.User;
import model.service.BankAccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CreditsCommand implements Command{

    @Override
    public String execute(HttpServletRequest request) {
        if(((User)request.getSession().getAttribute(Parameters.USER))==null) System.out.println("USER IS NULL");
        int userId = ((User)request.getSession().getAttribute(Parameters.USER)).getId();
        List<CreditAccount> creditAccounts = BankAccountService.getAllCreditAccountsByUserId(userId);
        request.setAttribute(Parameters.CREDITS,creditAccounts);
        for (CreditAccount creditAccount : creditAccounts) {
            System.out.println(creditAccount.getBalance());
        }
        System.out.println("IN CREDIT COMMAND !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        //return CommandConstants.REDIRECT+PagesName.CREDITS;
        return PagesName.USER_HOME_PAGE;
    }
}

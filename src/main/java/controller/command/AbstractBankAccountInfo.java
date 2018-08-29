package controller.command;

import controller.PagesName;
import controller.Parameters;
import controller.crypter.Crypter;
import model.entity.DepositAccount;
import model.entity.Entity;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public abstract class AbstractBankAccountInfo implements Command{

    public int decryptBankAccountIdFromRequest(HttpServletRequest request) throws ClassCastException{
        Map<Integer,Integer> complianceTable = (Map<Integer, Integer>) request.getSession()
                .getAttribute(Parameters.COMPLIANCE_TABLE);

        // todo check compliance map for null
        /*
        if(complianceTable==null) {
            return CommandConstants.REDIRECT + PagesName.ERROR;
        }
        */
        // todo check exception
        int accountIdFromPage;
        if(request.getParameter(Parameters.SERIAL_ID_FROM_PAGE)==null
                || request.getParameter(Parameters.SERIAL_ID_FROM_PAGE).isEmpty()){

            accountIdFromPage = Integer.parseInt(String.valueOf(request.getSession()
                    .getAttribute(Parameters.SERIAL_ID_FROM_PAGE)));

        }else{
            accountIdFromPage = Integer.parseInt(request.getParameter(Parameters.SERIAL_ID_FROM_PAGE));
            request.getSession().setAttribute(Parameters.SERIAL_ID_FROM_PAGE,accountIdFromPage);
        }

        return complianceTable.get(accountIdFromPage);
    }

    public<T extends Entity> void createComplianceTableInSession(HttpServletRequest request, List<T> accountList){
        Crypter<T> accountCrypter = new Crypter<>();
        Map<Integer,Integer> complianceTable = accountCrypter.getComplianceTable(accountList);
        request.getSession().setAttribute(Parameters.COMPLIANCE_TABLE,complianceTable);
    }

    public int getUserIdInSession(HttpServletRequest request){
        return ((User)request.getSession().getAttribute(Parameters.USER)).getId();
    }
}

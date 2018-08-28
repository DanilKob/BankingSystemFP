package controller.command;

import controller.PagesName;
import controller.Parameters;
import controller.crypter.Crypter;
import model.entity.DepositAccount;
import model.entity.Entity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public abstract class AbstractBankAccountInfo implements Command{
    public int unCryptBankAccountId(HttpServletRequest request,int accountId) throws ClassCastException{
        Map<Integer,Integer> complianceTable = (Map<Integer, Integer>) request.getSession()
                .getAttribute(Parameters.COMPLIANCE_TABLE);

        // todo check compliance map for null
        /*
        if(complianceTable==null) {
            return CommandConstants.REDIRECT + PagesName.ERROR;
        }
        */
        int creditIdFromPage = Integer.parseInt(request.getParameter(Parameters.SERIAL_ID_FROM_PAGE));
        return complianceTable.get(creditIdFromPage);
    }

    public<T extends Entity> void createComplianceTableInSession(HttpServletRequest request, List<T> accountList){
        Crypter<T> accountCrypter = new Crypter<>();
        Map<Integer,Integer> complianceTable = accountCrypter.getComplianceTable(accountList);
        request.getSession().setAttribute(Parameters.COMPLIANCE_TABLE,complianceTable);
    }
}

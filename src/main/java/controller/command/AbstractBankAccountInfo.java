package controller.command;

import controller.Parameters;
import controller.crypter.Crypter;
import controller.crypter.SecurityEntity;
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
        if(request.getParameter(Parameters.FAKE_ID_FROM_PAGE)==null
                || request.getParameter(Parameters.FAKE_ID_FROM_PAGE).isEmpty()){

            accountIdFromPage = Integer.parseInt(String.valueOf(request.getSession()
                    .getAttribute(Parameters.FAKE_ID_FROM_PAGE)));

        }else{
            accountIdFromPage = Integer.parseInt(request.getParameter(Parameters.FAKE_ID_FROM_PAGE));
            request.getSession().setAttribute(Parameters.FAKE_ID_FROM_PAGE,accountIdFromPage);
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

    public <T extends Entity> List<SecurityEntity<T>> cryptEntityList(HttpServletRequest request, List<T> entityLsit){
        Crypter<T> entityCrypter = new Crypter<>();
        List<SecurityEntity<T>> securityEntityList = entityCrypter.cryptEntityId(entityLsit);
        Map<Integer,Integer> complianceTable = entityCrypter.getComplianceTable();
        request.getSession().setAttribute(Parameters.COMPLIANCE_TABLE,complianceTable);
        return securityEntityList;
    }
}

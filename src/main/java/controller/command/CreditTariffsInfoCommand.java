package controller.command;

import controller.PagesName;
import controller.Parameters;
import model.entity.CreditTariff;
import model.service.CreditAccountService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CreditTariffsInfoCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        List<CreditTariff> creditTariffList = CreditAccountService.getAllCreditTariffs();
        request.setAttribute(Parameters.CREDIT_TARIFFS,creditTariffList);
        return PagesName.TARIFF_INFO_PAGE;
    }
}

package controller.command;

import controller.PagesName;
import controller.Parameters;
import model.entity.DepositTariff;
import model.service.DepositAccountService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DepositTariffInfoCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        List<DepositTariff> depositTariffList = DepositAccountService.getAllDepositTariffs();
        request.setAttribute(Parameters.DEPOSIT_TARIFFS, depositTariffList);
        return PagesName.TARIFF_INFO_PAGE;
    }
}

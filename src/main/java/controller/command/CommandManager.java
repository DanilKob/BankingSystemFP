package controller.command;

import controller.Parameters;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class CommandManager {
    private static CommandManager instance = new CommandManager();
    private Map<String,Command> commandMap = new HashMap<>();

    private Set<String> guestCommandNamesSet = new HashSet<>();
    private Set<String> userCommandNamesSet = new HashSet<>();
    private Set<String> adminCommandNamesSet = new HashSet<>();

    {
        init();
    }

    private CommandManager(){

    }

    public static CommandManager getInstance(){
        return instance;
    }


    public Command getCommand(HttpServletRequest request){
        String commandName = request.getParameter(Parameters.ACTION_PARAM);
        System.out.println(commandName);
        if(commandName == null) return getDefaultCommand();
        return commandMap.getOrDefault(commandName,getDefaultCommand());
    }

    private Command getDefaultCommand(){
        return commandMap.get(CommandConstants.DEFAULT_COMMAND);
    }

    private void init(){
        registerCommand(User.ROLE.GUEST,CommandConstants.LOGIN_COMMAND, new LogInCommand());
        registerCommand(User.ROLE.GUEST,CommandConstants.REGISTRATION_COMMAND,new RegistrationCommand());

        registerCommand(User.ROLE.USER,CommandConstants.CREDIT_PAGE_COMMAND, new CreditInfoCommand());
        registerCommand(User.ROLE.USER,CommandConstants.DEPOSIT_PAGE_COMMAND,new DepositInfoCommand());
        registerCommand(User.ROLE.USER,CommandConstants.BANK_ACCOUNTS_COMMAND,new BankAccountsCommand());
        registerCommand(User.ROLE.USER,CommandConstants.HISTORY_COMMAND, new HistoryInfoCommand());
        registerCommand(User.ROLE.USER,CommandConstants.PAY_COMMAND,new PayRequestCommand());
        registerCommand(User.ROLE.USER,CommandConstants.PAYMENT_CONFIRMATION,new PaymentConfirmationCommand());
        registerCommand(User.ROLE.USER,CommandConstants.CREDIT_TARIFF_INFO, new CreditTariffsInfoCommand());
        registerCommand(User.ROLE.USER,CommandConstants.DEPOSIT_TARIFF_INFO, new DepositTariffInfoCommand());
        registerCommand(User.ROLE.USER,CommandConstants.USER_UNCONFIRMED_CREDITS_COMMAND, new UserUnconfirmedCreditsCommand());
        registerCommand(User.ROLE.USER,CommandConstants.USER_HOME_PAGE_COMMAND, new UserHomePageCommand());
        registerCommand(User.ROLE.USER,CommandConstants.REGISTER_CREDIT_ACCOUNT,new RegisterCreditCommand());
        registerCommand(User.ROLE.USER,CommandConstants.REGISTER_DEPOSIT_ACCOUNT, new RegisterDepositCommand());


        registerCommand(User.ROLE.ADMIN,CommandConstants.ADMIN_HOME_PAGE_COMMAND, new AdminHomePageCommand());
        registerCommand(User.ROLE.ADMIN,CommandConstants.ALL_UNCONFIRMED_CREDITS, new AllUnconfirmedCreditsCommand());
        registerCommand(User.ROLE.ADMIN,CommandConstants.UNCONFIRMED_CREDIT_INFO, new UnconfirmedCreditInfoCommand());
        registerCommand(User.ROLE.ADMIN,CommandConstants.CONFIRM_CREDIT, new ConfirmCreditCommand());
        registerCommand(User.ROLE.ADMIN,CommandConstants.CANCEL_CREDIT, new CancelCreditCommand());

        Command logoutCommand = new LogOutCommand();
        registerCommand(User.ROLE.USER,CommandConstants.LOGOUT_COMMAND,logoutCommand);
        registerCommand(User.ROLE.ADMIN,CommandConstants.LOGOUT_COMMAND,logoutCommand);

        Command homePageCommand = new HomePageCommand();
        registerCommand(User.ROLE.GUEST,CommandConstants.HOME_PAGE_COMMAND,homePageCommand);
        registerCommand(User.ROLE.USER,CommandConstants.HOME_PAGE_COMMAND,homePageCommand);
        registerCommand(User.ROLE.ADMIN,CommandConstants.HOME_PAGE_COMMAND,homePageCommand);

        Command serverExceptionCommand = new ServerExceptionCommand();
        registerCommand(User.ROLE.GUEST,CommandConstants.SERVER_EXCEPTION_COMMAND,serverExceptionCommand);
        registerCommand(User.ROLE.USER,CommandConstants.SERVER_EXCEPTION_COMMAND,serverExceptionCommand);
        registerCommand(User.ROLE.ADMIN,CommandConstants.SERVER_EXCEPTION_COMMAND,serverExceptionCommand);
    }

    private void registerCommand(User.ROLE role, String commandName, Command command){
        getCommandNameSet(role).add(commandName);
        commandMap.putIfAbsent(commandName,command);
    }

    public  Set<String> getCommandNameSet(User.ROLE role){
        switch (role){
            case USER: return userCommandNamesSet;
            case ADMIN: return adminCommandNamesSet;
        }
        return guestCommandNamesSet;
    }
}

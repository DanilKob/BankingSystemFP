package controller.command;

import controller.Parameters;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class CommandManager {
    private final int initialCapacity = 5;
    private static CommandManager instance = new CommandManager();
    private Map<String,Command> commandMap = new HashMap<>();
    /*
    private Map<String,Map<String,Command>> roleMap = new HashMap<>(initialCapacity);

    private Map<String,Command> guestCommandMap = new HashMap<>();
    private Map<String,Command> userCommandMap = new HashMap<>();
    private Map<String,Command> adminCommandMap = new HashMap<>();
    */
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

    public Command getCommand(String commandName){

        return commandMap.get(commandName);
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
        registerCommand(User.ROLE.USER,CommandConstants.PAY_COMMAND,new PayCommandRequest());
        registerCommand(User.ROLE.USER,CommandConstants.PAYMENT_CONFIRMATION,new PaymentConfirmationCommand());
        registerCommand(User.ROLE.USER,CommandConstants.CREDIT_TARIFF_INFO, new CreditTariffsInfoCommand());
        registerCommand(User.ROLE.USER,CommandConstants.DEPOSIT_TARIFF_INFO, new DepositTariffInfoCommand());
        registerCommand(User.ROLE.USER,CommandConstants.UNCONFIRMED_CREDITS_COMMAND, new UnConfirmedCreditsCommand());

        Command logoutCommand = new LogOutCommand();
        registerCommand(User.ROLE.USER,CommandConstants.LOGOUT_COMMAND,logoutCommand);
        registerCommand(User.ROLE.ADMIN,CommandConstants.LOGOUT_COMMAND,logoutCommand);

        /*
        commandMap.put(CommandConstants.LOGIN_COMMAND, new LogInCommand());
        commandMap.put(CommandConstants.LOGOUT_COMMAND,new LogOutCommand());
        commandMap.put(CommandConstants.REGISTRATION_COMMAND,new RegistrationCommand());
        commandMap.put(CommandConstants.DEFAULT_COMMAND,new DefaultCommand());
        commandMap.put(CommandConstants.CREDITS_COMMAND, new CreditsCommand());
        commandMap.put(CommandConstants.CREDIT_PAGE_COMMAND, new CreditInfoCommand());
        commandMap.put(CommandConstants.DEPOSITS_COMMAND,new DepositsCommand());
        commandMap.put(CommandConstants.DEPOSIT_PAGE_COMMAND,new DepositInfoCommand());
        */
        /*
        guestCommandMap.put(CommandConstants.LOGIN_COMMAND, new LogInCommand());
        guestCommandMap.put(CommandConstants.REGISTRATION_COMMAND,new RegistrationCommand());
        */

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

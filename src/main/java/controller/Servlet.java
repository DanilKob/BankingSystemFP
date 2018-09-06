package controller;

import controller.command.Command;
import controller.command.CommandManager;
import controller.command.CommandConstants;
import controller.command.LoggerConstants;
import controller.utility.RolesUtility;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

public class Servlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        config.getServletContext().setAttribute(Parameters.LOGGED_USERS,new HashSet<String>());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    private void processRequest(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        Command command = CommandManager.getInstance().getCommand(req);
        String page;

        try{
            page = command.execute(req);
        }catch (NullPointerException | IllegalArgumentException | ClassCastException dangerUserException){
            Logger.getLogger(LoggerConstants.DANGER_USER).info(dangerUserException);
            RolesUtility.logoutUser(req);
            page = CommandConstants.REDIRECT + PagesName.ERROR;
        }catch (RuntimeException e){
            page = CommandConstants.REDIRECT + CommandConstants.SET_COMMAND + CommandConstants.SERVER_EXCEPTION_COMMAND;
        }

        Logger.getLogger(Servlet.class.getName()).info(command.getClass().getName());

        if(page.contains(CommandConstants.REDIRECT)){
            page = page.replace(CommandConstants.REDIRECT,"");
            resp.sendRedirect(page);
        }else{
            req.getRequestDispatcher(page).forward(req,resp);
        }

    }
}

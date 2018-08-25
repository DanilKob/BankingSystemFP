package controller;

import controller.command.Command;
import controller.command.CommandManager;
import controller.command.CommandConstants;

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
        String page = command.execute(req);

        System.out.println("Command = "+command.getClass().getName());
        System.out.println("page = "+page);

        // todo Ask about method "isRedirected"

        if(page.contains(CommandConstants.REDIRECT)){
            page = page.replace(CommandConstants.REDIRECT,"");
            System.out.println("page after replace = "+page);
            resp.sendRedirect(page);
        }else{
            req.getRequestDispatcher(page).forward(req,resp);
        }
        /*
        if(commandName!=null){
            String pageResponse = CommandManager.getInstance().getCommand(commandName).execute(req);
            req.getRequestDispatcher(pageResponse).forward(req,resp);
        }else{
            resp.sendRedirect(req.getContextPath());
        }*/
    }
}

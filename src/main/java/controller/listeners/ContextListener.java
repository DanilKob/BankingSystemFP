package controller.listeners;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    public static final String loggerPathKey = "loggerPropertiesPath";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String homeDir = servletContextEvent.getServletContext().getRealPath("/");
        String loggerPropertyPath = servletContextEvent.getServletContext().getInitParameter(loggerPathKey);
        String fullPath = homeDir+loggerPropertyPath;
        PropertyConfigurator.configure(fullPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

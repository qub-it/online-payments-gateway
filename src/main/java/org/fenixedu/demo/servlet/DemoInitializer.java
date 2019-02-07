package org.fenixedu.demo.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DemoInitializer implements ServletContextListener {

        @Override
        public void contextInitialized(ServletContextEvent event) {
        }

        @Override
        public void contextDestroyed(ServletContextEvent event){
        }
}
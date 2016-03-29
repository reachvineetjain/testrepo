package com.ccighgo.utils;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class ContextFinalizer implements ServletContextListener {

   private static final Logger LOGGER = Logger.getLogger(ContextFinalizer.class);

   public void contextDestroyed(ServletContextEvent sce) {
      Enumeration<Driver> drivers = DriverManager.getDrivers();
      Driver d = null;
      while (drivers.hasMoreElements()) {
         try {
            d = drivers.nextElement();
            DriverManager.deregisterDriver(d);
         } catch (SQLException ex) {
            LOGGER.warn(String.format("Error deregistering driver %s", d), ex);
         }
      }
      Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
      Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
      for (Thread t : threadArray) {
         if (t.getName().contains("Abandoned connection cleanup thread")) {
            synchronized (t) {
               t.stop(); // don't complain, it works
            }
         }
      }
   }

   @Override
   public void contextInitialized(ServletContextEvent arg0) {

   }

}

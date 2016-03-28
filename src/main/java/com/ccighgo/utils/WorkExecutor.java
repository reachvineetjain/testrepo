package com.ccighgo.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Work executor class for setting task in concurrent manner
 * 
 * @author ravimishra
 *
 */
public class WorkExecutor {
   private ExecutorService executor;
   private int maxThreads = 100;

   public int getMaxThreads() {
      return maxThreads;
   }

   public void setMaxThreads(int maxThreads) {
      this.maxThreads = maxThreads;
   }

   public void init() {
      LOGGER.debug("Initializing executor with max {} threads", maxThreads);
      executor = Executors.newFixedThreadPool(maxThreads);
   }

   public void submit(Runnable task) {
      executor.execute(task);
   }

   public void shutdown() {
      executor.shutdown();
   }

   private static final Logger LOGGER = LoggerFactory.getLogger(WorkExecutor.class);
}

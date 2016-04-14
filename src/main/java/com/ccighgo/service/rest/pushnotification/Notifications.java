package com.ccighgo.service.rest.pushnotification;

import java.util.Collection;

import javax.websocket.Session;

public final class Notifications {

   public static void broadcastMessage(String message, Collection<Session> sessions) {
      for (Session s : sessions) {
         if (s.isOpen()) {
            s.getAsyncRemote().sendText(message);
         }
      }
   }

   public static void sendMessage(String message, Session session) {
      if (session != null && session.isOpen()) {
         session.getAsyncRemote().sendText(message);
      }
   }
}

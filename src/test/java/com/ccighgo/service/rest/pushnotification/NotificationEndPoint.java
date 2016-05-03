//package com.ccighgo.service.rest.pushnotification;
//
//import java.io.IOException;
//
//import javax.websocket.ClientEndpoint;
//import javax.websocket.CloseReason;
//import javax.websocket.OnClose;
//import javax.websocket.OnError;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//
//import org.apache.log4j.Logger;
//
//@ClientEndpoint
//public class NotificationEndPoint {
//   private Session session;
//
//   private static final Logger LOGGER = Logger.getLogger(NotificationEndPoint.class);
//
//   @OnOpen
//   public void onOpen(Session session) {
//      this.session = session;
//      // System.out.println("NotificationEndPoint :: onOpen :: Connected to Push
//      // Notification Server");
//
//   }
//
//   @OnClose
//   public void onClose(Session session, CloseReason closeReason) {
//      // System.out.println("NotificationEndPoint :: onClose :: " +
//      // closeReason.getReasonPhrase());
//
//   }
//
//   @OnMessage
//   public void onMessage(String message, Session session) {
//      // System.out.println("NotificationEndPoint :: onMessage ::");
//      // System.out.println("Message received from server = " + message);
//   }
//
//   @OnError
//   public void onError(Session session, Throwable exp) {
//      // System.out.println("::NotificationEndPoint :: onError :: ");
//      // System.out.println(exp.getMessage());
//
//   }
//
//   public void sendMessage(String msg) {
//      try {
//         this.session.getBasicRemote().sendText("Your msg :: " + msg);
//      } catch (IOException e) {
//         LOGGER.error(e.getMessage(), e);
//      }
//
//   }
//}

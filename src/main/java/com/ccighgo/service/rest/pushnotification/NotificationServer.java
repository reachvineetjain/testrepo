package com.ccighgo.service.rest.pushnotification;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.scheduling.annotation.Scheduled;

@ServerEndpoint("/notify/{uid}")
public class NotificationServer {

	/**
	 * @OnOpen allows us to intercept the creation of a new session. The session
	 *         class allows us to send data to the user. In the method onOpen,
	 *         we'll let the user know that the handshake was successful.
	 */
	@OnOpen
	public void onOpen(@PathParam("uid") String uid, Session session) {
		System.out.println("EchoServer :: " + session.getId() + " has opened a connection");
		System.out.println("EchoServer :: id = " + uid);
		
		SessionRegistry.INSTANCE.addSession(uid, session);
		
		try {
			session.getBasicRemote().sendText("Connection Established");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * When a user sends a message to the server, this method will intercept the
	 * message and allow us to react to it. For now the message is read as a
	 * String.
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("EchoServer :: Message from " + session.getId() + ": " + message);
		try {
			session.getBasicRemote().sendText(message);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * The user closes the connection.
	 * 
	 * Note: you can't send messages to the client from this method
	 */
	@OnClose
	public void onClose(Session session) {
		System.out.println("EchoServer :: Session " + session.getId() + " has ended");
		SessionRegistry.INSTANCE.removeSession(session);
	}
	
	@Scheduled(fixedDelay = 10000)
	public void execute() {
		System.out.println("........ scheduled notification task ..........");
		
		System.out.println("Sessions count = " + SessionRegistry.INSTANCE.getSessions().size());
		
		for(Session s : SessionRegistry.INSTANCE.getSessions().values()) {
			try {
				s.getBasicRemote().sendText("$$ Notification from server $$");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

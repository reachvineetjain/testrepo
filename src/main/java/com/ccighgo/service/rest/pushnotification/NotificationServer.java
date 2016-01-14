package com.ccighgo.service.rest.pushnotification;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

@ServerEndpoint("/notify/{uid}")
public class NotificationServer {
	
	private static final Logger LOGGER = Logger.getLogger(NotificationServer.class);

	/**
	 * @OnOpen allows us to intercept the creation of a new session. The session
	 *         class allows us to send data to the user. In the method onOpen,
	 *         we'll let the user know that the handshake was successful.
	 */
	@OnOpen
	public void onOpen(@PathParam("uid") String uid, Session session) {
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
		System.out.println("NotificationServer :: Message from " + session.getId() + ": " + message);
		LOGGER.info("NotificationServer :: Message from " + session.getId() + ": " + message);
		
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
		SessionRegistry.INSTANCE.removeSession(session);
	}
	
	@Scheduled(fixedDelay = 10000)
	public void execute() {
		System.out.println("........ scheduled notification task ..........");
		System.out.println("Sessions count = " + SessionRegistry.INSTANCE.getSessions().size());
		
		LOGGER.info("........ Scheduled Notification Task ..........");
		LOGGER.debug("Sessions count = " + SessionRegistry.INSTANCE.getSessions().size());
		
		Notifications.broadcastMessage("$$ Notification from server $$", SessionRegistry.INSTANCE.getSessions().values());
	}
}

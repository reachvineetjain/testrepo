package com.ccighgo.service.rest.pushnotification;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;

import org.glassfish.tyrus.client.ClientManager;
import org.glassfish.tyrus.client.ClientProperties;

public class NotificationEndPointTest {

	public static void main(String[] args) throws DeploymentException, IOException, URISyntaxException, InterruptedException {
		
		Random random = new Random();
		int num = random.nextInt(100 - 1 + 1) + 1;

		String dest = "ws://localhost:8086/cci_gh_go/notify/" + num;
//		String dest = "ws://52.2.191.63:8086/cci_gh_go/notify/" + num;
		
//		String dest = "ws://localhost:8080/cci_gh_go/push?1000";
		// ws://52.2.191.63:8086/cci_gh_go/services/notifications/push?1000
		
		NotificationEndPoint socket = new NotificationEndPoint();
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		container.connectToServer(socket, URI.create(dest));
		
		while(true) {
//			socket.sendMessage("Hello WebSocket @" + System.currentTimeMillis());
			Thread.sleep(6000);
		}
	}
}

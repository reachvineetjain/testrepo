package com.ccighgo.service.rest.pushnotification;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccighgo.exception.PushNotificationException;
import com.ccighgo.service.rest.authorization.Authorization;
@Path("/notification/")
// annotation to create socket
@ServerEndpoint("/push")
public class PushNotification {
	 private static final Logger LOGGER = LoggerFactory.getLogger(Authorization.class); 
	   private Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
	   private Session wsSession;
	   private static HashMap<String,Session> unique_user= new HashMap<String,Session>();
	   JSONObject json=null;
	//Socket connection close
	   @OnOpen
	   public void onOpen(Session peer) throws IOException{
		   LOGGER.info("Connection opened ...");
	       this.wsSession=peer;
	       unique_user.put(peer.getQueryString(), wsSession);
	       peers.add(peer);
	   }
	   
	 //Socket connection close
	   @OnClose
	   public void onClose(Session peer) throws PushNotificationException{
		   LOGGER.info("Connection closed ...");
	      
	   		try {
				peer.close();
			} catch (IOException e) {
				throw new PushNotificationException(e.getMessage());
			}
	   	       peers.remove(peer);
	       unique_user.remove(wsSession);
	   }
	   /**
	    * RESTFul service for push notification
	    * 
	    * @param no ,uid,type
	    * 
	    */
	  @GET 
	  @Path("pushData/{no}/{uid}/{type}")
	   public void pushData(@PathParam("no")String no,@PathParam("uid")String uid,@PathParam("type")String type) throws PushNotificationException 
	   {
		Session pushDataSession=unique_user.get(uid);
		try {
				pushDataSession.getBasicRemote().sendText("[{\"no\": "+no+", \"uid\": "+uid+", \"type\": "+type+"}]");
			} catch (IOException e) {	
				throw new PushNotificationException(e.getMessage());
			}
		}

}

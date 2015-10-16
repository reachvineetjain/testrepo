/**
 * 
 */
package com.ccighgo.service.rest.authorization;



import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;







import com.ccighgo.service.components.authorization.AuthorizationManagerInterface;
import com.ccighgo.service.transport.usermanagement.beans.user.User;

/**
 * @author ravi
 *
 */
@Path("/authorize/")
@Produces("application/json")
@Consumes("application/json")
//annotation for creating websocket
@ServerEndpoint("/pushn")
public class Authorization {
   private static final Logger LOGGER = LoggerFactory.getLogger(Authorization.class); 
   private Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
   private Session wsSession;
   private static HashMap<String,Session> unique_user= new HashMap<String,Session>();
   JSONObject json=null;
   
  //Socket connection open
   @OnOpen
   public void onOpen(Session peer) throws IOException{
	   LOGGER.info("Connection opened ...");
       this.wsSession=peer;
       unique_user.put(peer.getQueryString(), wsSession);
       peers.add(peer);
   }
   
 //Socket connection close
   @OnClose
   public void onClose(Session peer) {
	   LOGGER.info("Connection closed ...");
       try {
   		peer.close();
   	} catch (IOException e) {
   		e.printStackTrace();
   	}
       peers.remove(peer);
       unique_user.remove(wsSession);
   }
  
   @Autowired AuthorizationManagerInterface authorizationManager;
   
   /**
    * RESTFul service gets user by id for edit
    * 
    * @param id
    * @return User by id
    */
   @GET
   @Path("cciusr/{userId}")
   @Produces("application/json")
   public User getCCIUserDetails(@PathParam("userId") String userId) {
	   return authorizationManager.getCCIUserDetails(userId);
   }
   
   /**
    * RESTFul service for push notification
    * 
    * @param no , uid, type
    * 
    */
   @GET 
   @Path("pushData/{no}/{uid}/{type}")
   public void pushData(@PathParam("no")String no,@PathParam("uid")String uid,@PathParam("type")String type)
   {
	Session pushDataSession=unique_user.get(uid);
	try {
			pushDataSession.getBasicRemote().sendText("[{\"no\": "+no+", \"uid\": "+uid+", \"type\": "+type+"}]");
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}
}

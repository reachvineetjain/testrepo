/**
 * 
 */
package com.ccighgo.service.rest.authorization;



import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;


import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;


import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
/*import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;*/
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.shiro.SecurityUtils;
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
@ServerEndpoint("/push")
public class Authorization {
   
   private static final Logger LOGGER = LoggerFactory.getLogger(Authorization.class); 
   
   @OnMessage
   public String onMessage(String message) {
   	System.out.println(message);
       return "";
   }
   @OnOpen
   public void onOpen(Session peer) {
	   LOGGER.info("Connection opened ...");
       System.out.println("Connection opened");
       Timer timer = new Timer();
       timer.scheduleAtFixedRate(new TaskExampleRepeating(), 10000, 5000);
       peers.add(peer);
   }
   @OnClose
   public void onClose(Session peer) {
	   LOGGER.info("Connection closed ...");
       System.out.println("Connection closed ...");
       try {
   		peer.close();
   	} catch (IOException e) {
   		// TODO Auto-generated catch block
   		e.printStackTrace();
   	}
       peers.remove(peer);
    
  
   }
   class TaskExampleRepeating extends TimerTask{
       public void run(){
	        	try {
	        		for(Session s : peers)
	        		{
	        			s.getBasicRemote().sendText(""+counter);
	        			
	        		}
				} catch (IOException e) {
					e.printStackTrace();
				}
	        	counter++;
       }
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
	   System.out.println("Authorization getCCIUserDetails"+userId);
	   
       return authorizationManager.getCCIUserDetails(userId);
   }
   
 //  private static final Logger LOG = Logger.getLogger(Authorization.class.getName());
  private Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
   private int counter = 1;
  

  
   

}

/**
 * 
 */
package com.ccighgo.services.rest.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.apache.cxf.jaxrs.ext.MessageContext;

/**
 * @author ravimishra
 *
 */
@Path("/login/")
@Produces("application/json")
@Consumes("application/json")
public class Login {
    
    @Context
    MessageContext msgContext;
    
    @GET
    @Path("ping/{input}")
    @Produces("text/plain")
    public String ping(@PathParam("input") String input) {
       return input;
    }
    
    public String login(){
        //TODO user shiro filter to authenticate user.. waiting for DB design for user
        HttpSession session = msgContext.getHttpServletRequest().getSession();
        //this is if user logs out and hits back button
        if(session == null || session.getAttribute("username") == null){
            //send user to login page
        }
        else{
            
        }
        return null;
        
    }
    
    public String logout(){
        HttpServletRequest request = msgContext.getHttpServletRequest();
        HttpSession session = request.getSession();
        session.setAttribute("username", null);
        session.invalidate();
        //TODO send user back to login page
        return null;
        
    }

}

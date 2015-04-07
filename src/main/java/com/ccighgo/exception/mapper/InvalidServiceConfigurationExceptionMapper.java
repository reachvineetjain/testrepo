package com.ccighgo.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ExceptionMapper;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.ccighgo.exception.InvalidServiceConfigurationException;


@Provider 
public class InvalidServiceConfigurationExceptionMapper implements ExceptionMapper<InvalidServiceConfigurationException> {
//	private static final Logger LOGGER = LoggerFactory.getLogger(InvalidServiceConfigurationExceptionMapper.class);

    public Response toResponse(InvalidServiceConfigurationException exception) {
     //   LOGGER.error("Internal fault", exception);
        return Response.status(Status.SERVICE_UNAVAILABLE).build();
    }
}

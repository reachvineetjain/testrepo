package com.ccighgo.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ExceptionMapper;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.ccighgo.exception.NotFoundException;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
   // private static final Logger LOGGER = LoggerFactory.getLogger(NotFoundExceptionMapper.class);

    public Response toResponse(NotFoundException exception) {
        return Response.status(Status.NOT_FOUND).build();
    }
}

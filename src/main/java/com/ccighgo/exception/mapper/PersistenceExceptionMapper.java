package com.ccighgo.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class PersistenceExceptionMapper implements ExceptionMapper<Exception> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceExceptionMapper.class);

    public Response toResponse(Exception exception) {
        LOGGER.error("An error occured with database access", exception);
        return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
}

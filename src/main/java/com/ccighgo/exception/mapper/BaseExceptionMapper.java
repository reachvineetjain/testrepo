package com.ccighgo.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class BaseExceptionMapper implements ExceptionMapper<Exception> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseExceptionMapper.class);

    public Response toResponse(Exception exception) {
        LOGGER.error("Unknown error occored. Please check for more possible error conditions", exception);
        return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
}

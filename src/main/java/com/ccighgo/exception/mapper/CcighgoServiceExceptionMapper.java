package com.ccighgo.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.ccighgo.exception.CcighgoServiceException;

public class CcighgoServiceExceptionMapper implements ExceptionMapper<CcighgoServiceException> {

   // private static final Logger LOGGER = LoggerFactory.getLogger(CcighgoServiceExceptionMapper.class);

    public Response toResponse(CcighgoServiceException exception) {
     //   LOGGER.warn("Validation Error", exception);
        ResponseBuilder responseBuilder = Response.status(Status.BAD_REQUEST);
        responseBuilder.header("X-Application-Error-Code", Integer.toString(exception.getErrorCode().getValue()));
        responseBuilder.header("X-Application-Error-Info", exception.getMessage());
        exception.printStackTrace();
        return responseBuilder.build();
    }

}

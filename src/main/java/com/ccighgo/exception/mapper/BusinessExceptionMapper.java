package com.ccighgo.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccighgo.exception.BusinessException;


@Provider 
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {
	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessExceptionMapper.class);

    public Response toResponse(BusinessException exception) {
        LOGGER.warn("Business info Error", exception);
        ResponseBuilder responseBuilder = Response.status(Status.BAD_REQUEST);
        responseBuilder.header("X-Application-Error-Code", Integer.toString(exception.getErrorCode().getValue()));
        responseBuilder.header("X-Application-Error-Info", exception.getMessage());
        return responseBuilder.build();
    }

	

}

/**
 * 
 */
package com.ccighgo.service.rest.generic.documents;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.generic.document.GenericDocumentsInterface;
import com.ccighgo.service.components.partnerinquiry.PartnerInquiryService;
import com.ccighgo.service.transport.generic.beans.documents.partner.PartnerGenericDocuments;
import com.ccighgo.utils.WSDefaultResponse;

/**
 * @author Ahmed
 *
 */
@Path("/genericDocuments/")
@Produces("application/json")
@Consumes("application/json")
public class GenericDocuments {
	@Autowired
	GenericDocumentsInterface genericDocumentsInterface;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(GenericDocuments.class);

	@GET
	@Path("ping/{input}")
	@Produces("text/plain")
	public String ping(@PathParam("input") String input) {
		return input;
	}

	@GET
	@Path("viewPartnerDocument/{partnerId}")
	@Produces("application/json")
	public List<PartnerGenericDocuments> viewPartnerDocument(
			@PathParam("partnerId") String partnerId) {
		return genericDocumentsInterface.viewPartnerDocument(partnerId);
	}

	@POST
	@Path("addPartnerDocument")
	@Produces("application/json")
	@Consumes("application/json")
	public WSDefaultResponse addPartnerDocument(
			PartnerGenericDocuments partnerGenericDocuments) {
		return null;
	}
}

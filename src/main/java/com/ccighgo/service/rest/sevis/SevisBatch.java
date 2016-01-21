package com.ccighgo.service.rest.sevis;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.sevis.SevisService;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.sevis.CreateSEVISBatch;

@Path("/sevis/")
@Produces("application/json")
@Consumes("application/json")
public class SevisBatch {

	@Autowired
	SevisService sevisBatchService;
	@Context
	ServletContext servletContext;

//	@POST
//	@Path("create/batch")
//	@Produces("application/json")
//	public Response createStudentBatch(CreateSEVISBatch batchData) {
//		return sevisBatchService.createBatch(batchData, servletContext);
//	}

	@GET
	@Path("process/log")
	@Produces("application/json")
	public Response processLog() {
		return sevisBatchService.processSevisLog(null); // test only, pass xml
														// file URI or ....
	}

	@POST
	@Path("create/batch/student")
	@Produces("application/json")
	public Response createStudent(CreateSEVISBatch batchParam) {
		System.out.println("Service ...");
		return sevisBatchService.createStudentBatch(batchParam, servletContext);
	}

	@POST
	@Path("create/batch/bio")
	@Produces("application/json")
	public Response updateEVBio(CreateSEVISBatch batchParam) {
		return sevisBatchService.updateEVBiographical(batchParam, servletContext);
	}

	@POST
	@Path("create/batch/update/status/invalid")
	@Produces("application/json")
	public Response updateStatusInvalid(CreateSEVISBatch batchParam) {
		// Status Invalid
		// Update.EV.Status.Invalid
		return sevisBatchService.updateEVStatusInvalid(batchParam, servletContext);
	}

	@POST
	@Path("create/batch/update/status/terminate")
	@Produces("application/json")
	public Response updateStatusTerminate(CreateSEVISBatch batchParam) {
		// Status Terminate
		// Update.EV.Status.Terminate
		// Update.Student.Status.Terminate
		return sevisBatchService.updateEVStatusTerminate(batchParam, servletContext);
	}

	@POST
	@Path("create/batch/update/editSubject")
	@Produces("application/json")
	public Response updateEditSubject(CreateSEVISBatch batchParam) {
		// Update - EditSubject
		// Event - Update.EV.Program.EditSubject
		return sevisBatchService.updateEVProgramEditSubject(batchParam, servletContext);
	}

	@POST
	@Path("create/batch/update/financialInfo")
	@Produces("application/json")
	public Response updateFinancialInfo(CreateSEVISBatch batchParam) {
		// Financial Info
		// Update.EV.FinancialInfo
		// Update.Student.FinancialInfo
		return sevisBatchService.updateEVFinancialInfo(batchParam, servletContext);
	}

	@POST
	@Path("create/batch/update/tipp")
	@Produces("application/json")
	public Response updateTIPP(CreateSEVISBatch batchParam) {
		// T/IPP
		// Update.EV.TIPP
		return sevisBatchService.updateEVTIPP(batchParam, servletContext);
	}

	@POST
	@Path("create/batch/update/reprint")
	@Produces("application/json")
	public Response updateReprint(CreateSEVISBatch batchParam) {
		// Reprint
		// Update.EV.Reprint
		// Update.EV.Dependent.Reprint
		// Update.Student.Reprint
		// Update.Student.Dependent.Reprint

		// return sevisBatchService.updateEVReprint(batchParam, servletContext);
		// return sevisBatchService.updateEVDependentReprint(batchParam,
		// servletContext);
		// return sevisBatchService.updateStudentReprint(batchParam,
		// servletContext);
		return sevisBatchService.updateStudentDependentReprint(batchParam, servletContext);
	}

	@POST
	@Path("create/batch/update/program/extension")
	@Produces("application/json")
	public Response updateProgramExtension(CreateSEVISBatch batchParam) {
		// Program Extension
		// Update.EV.Program.Extension
		// Update.Student.Program.Extension

		// return sevisBatchService.updateStudentProgramExtension(batchParam,
		// servletContext);

		return sevisBatchService.updateEVProgramExtension(batchParam, servletContext);
	}

	@POST
	@Path("create/batch/update/program/shorten")
	@Produces("application/json")
	public Response updateProgramShorten(CreateSEVISBatch batchParam) {
		// Program Shorten

		// Update.EV.Program.Shorten
		// Update.Student.Program.Shorten

		// return sevisBatchService.updateEVProgramShorten(batchParam,
		// servletContext);
		return sevisBatchService.updateStudentProgramShorten(batchParam, servletContext);

	}

	@POST
	@Path("create/batch/update/soa")
	@Produces("application/json")
	public Response updateSOA(CreateSEVISBatch batchParam) {
		// Update - Site of Activity
		// Update.EV.SiteOfActivity.Edit
		return sevisBatchService.updateEVSOAEdit(batchParam, servletContext);

	}

	@GET
	@Path("ping")
	@Produces("application/json")
	public Response ping() {
		return new Response();
	}
	
	public static final String BATCH_DOWNLOAD_LINK = "/sevis/download/batch/";
	
	@GET
	@Path("download/batch/{file}")
	@Produces("application/xml")
	public javax.ws.rs.core.Response download(@PathParam("file") String file) {
//		file = "AAAAAAAAAAAAFL.xml"; // test
		return sevisBatchService.downloadBatchFile(file, servletContext);
	}

}

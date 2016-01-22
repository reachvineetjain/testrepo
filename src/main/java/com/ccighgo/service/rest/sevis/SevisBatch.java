package com.ccighgo.service.rest.sevis;

import java.io.File;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.component.serviceutils.ApplicationContextProvider;
import com.ccighgo.service.components.sevis.EVBatchGenerator;
import com.ccighgo.service.components.sevis.SevisUtils;
import com.ccighgo.service.components.sevis.StuBatchGenerator;
import com.ccighgo.service.components.sevis.data.CreateStudentBatchDataService;
import com.ccighgo.service.components.sevis.data.UpdateEVBioBatchDataService;
import com.ccighgo.service.components.sevis.data.UpdateEVFinancialInfoBatchDataService;
import com.ccighgo.service.components.sevis.data.UpdateEVProgramEditSubjectBatchDataService;
import com.ccighgo.service.components.sevis.data.UpdateEVProgramExtentionBatchDataService;
import com.ccighgo.service.components.sevis.data.UpdateEVSOAEditBatchDataService;
import com.ccighgo.service.components.sevis.data.UpdateEVStatusInvalidBatchDataService;
import com.ccighgo.service.components.sevis.data.UpdateEVStatusTerminateBatchDataService;
import com.ccighgo.service.components.sevis.data.UpdateEVTIPPBatchDataService;
import com.ccighgo.service.components.sevis.data.UpdateStudentDependentReprintBatchDataService;
import com.ccighgo.service.components.sevis.data.UpdateStudentProgramShortenBatchDataService;
import com.ccighgo.service.components.sevis.log.SevisLogProcessor;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.sevis.BatchParam;

@Path("/sevis/")
@Produces("application/json")
@Consumes("application/json")
public class SevisBatch {

//	@Autowired
//	SevisService sevisBatchService;
	@Context
	ServletContext servletContext;
	
	@Autowired StuBatchGenerator stuBatchGenerator;
	@Autowired EVBatchGenerator evBatchGenerator;
	@Autowired SevisLogProcessor logProcessor;

//	@POST
//	@Path("create/batch")
//	@Produces("application/json")
//	public Response createStudentBatch(BatchParam batchData) {
//		return sevisBatchService.createBatch(batchData, servletContext);
//	}

	@GET
	@Path("process/log")
	@Produces("application/json")
	public Response processLog() {
		return logProcessor.processLog(null); // test only, pass xml
														// file URI or ....
	}

	@POST
	@Path("create/batch/student")
	@Produces("application/json")
	public Response createStudent(BatchParam batchParam) {
		System.out.println("Service ...");
//		return sevisBatchService.createStudentBatch(batchParam, servletContext);
		
		CreateStudentBatchDataService dataService = getServiceBean(CreateStudentBatchDataService.class);
		return stuBatchGenerator.createBatch(batchParam, dataService, servletContext);
	}

	@POST
	@Path("create/batch/bio")
	@Produces("application/json")
	public Response updateEVBio(BatchParam batchParam) {
//		return sevisBatchService.updateEVBiographical(batchParam, servletContext);
		UpdateEVBioBatchDataService dataService = getServiceBean(UpdateEVBioBatchDataService.class);
		return evBatchGenerator.createBatch(batchParam, dataService, servletContext);
	}

	@POST
	@Path("create/batch/update/status/invalid")
	@Produces("application/json")
	public Response updateStatusInvalid(BatchParam batchParam) {
		// Status Invalid
		// Update.EV.Status.Invalid
//		return sevisBatchService.updateEVStatusInvalid(batchParam, servletContext);
		
		UpdateEVStatusInvalidBatchDataService dataService = getServiceBean(UpdateEVStatusInvalidBatchDataService.class);
		return evBatchGenerator.createBatch(batchParam, dataService, servletContext);
	}

	@POST
	@Path("create/batch/update/status/terminate")
	@Produces("application/json")
	public Response updateStatusTerminate(BatchParam batchParam) {
		// Status Terminate
		// Update.EV.Status.Terminate
		// Update.Student.Status.Terminate
//		return sevisBatchService.updateEVStatusTerminate(batchParam, servletContext);
		
		UpdateEVStatusTerminateBatchDataService dataService = getServiceBean(UpdateEVStatusTerminateBatchDataService.class);
		return evBatchGenerator.createBatch(batchParam, dataService, servletContext);
	}

	@POST
	@Path("create/batch/update/editSubject")
	@Produces("application/json")
	public Response updateEditSubject(BatchParam batchParam) {
		// Update - EditSubject
		// Event - Update.EV.Program.EditSubject
//		return sevisBatchService.updateEVProgramEditSubject(batchParam, servletContext);
		UpdateEVProgramEditSubjectBatchDataService dataService = getServiceBean(UpdateEVProgramEditSubjectBatchDataService.class);
		return evBatchGenerator.createBatch(batchParam, dataService, servletContext);
	}

	@POST
	@Path("create/batch/update/financialInfo")
	@Produces("application/json")
	public Response updateFinancialInfo(BatchParam batchParam) {
		// Financial Info
		// Update.EV.FinancialInfo
		// Update.Student.FinancialInfo
//		return sevisBatchService.updateEVFinancialInfo(batchParam, servletContext);
		UpdateEVFinancialInfoBatchDataService dataService = getServiceBean(UpdateEVFinancialInfoBatchDataService.class);
		return evBatchGenerator.createBatch(batchParam, dataService, servletContext);
	}

	@POST
	@Path("create/batch/update/tipp")
	@Produces("application/json")
	public Response updateTIPP(BatchParam batchParam) {
		// T/IPP
		// Update.EV.TIPP
//		return sevisBatchService.updateEVTIPP(batchParam, servletContext);
		UpdateEVTIPPBatchDataService dataService = getServiceBean(UpdateEVTIPPBatchDataService.class);
		return evBatchGenerator.createBatch(batchParam, dataService, servletContext);
	}

	@POST
	@Path("create/batch/update/reprint")
	@Produces("application/json")
	public Response updateReprint(BatchParam batchParam) {
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
//		return sevisBatchService.updateStudentDependentReprint(batchParam, servletContext);
		UpdateStudentDependentReprintBatchDataService dataService = getServiceBean(
				UpdateStudentDependentReprintBatchDataService.class);
		return stuBatchGenerator.createBatch(batchParam, dataService, servletContext);
	}

	@POST
	@Path("create/batch/update/program/extension")
	@Produces("application/json")
	public Response updateProgramExtension(BatchParam batchParam) {
		// Program Extension
		// Update.EV.Program.Extension
		// Update.Student.Program.Extension

		// return sevisBatchService.updateStudentProgramExtension(batchParam,
		// servletContext);

//		return sevisBatchService.updateEVProgramExtension(batchParam, servletContext);
		UpdateEVProgramExtentionBatchDataService dataService = getServiceBean(
				UpdateEVProgramExtentionBatchDataService.class);
		return evBatchGenerator.createBatch(batchParam, dataService, servletContext);
	}

	@POST
	@Path("create/batch/update/program/shorten")
	@Produces("application/json")
	public Response updateProgramShorten(BatchParam batchParam) {
		// Program Shorten

		// Update.EV.Program.Shorten
		// Update.Student.Program.Shorten

		// return sevisBatchService.updateEVProgramShorten(batchParam,
		// servletContext);
//		return sevisBatchService.updateStudentProgramShorten(batchParam, servletContext);
		UpdateStudentProgramShortenBatchDataService dataService = getServiceBean(
				UpdateStudentProgramShortenBatchDataService.class);
		return stuBatchGenerator.createBatch(batchParam, dataService, servletContext);

	}

	@POST
	@Path("create/batch/update/soa")
	@Produces("application/json")
	public Response updateSOA(BatchParam batchParam) {
		// Update - Site of Activity
		// Update.EV.SiteOfActivity.Edit
//		return sevisBatchService.updateEVSOAEdit(batchParam, servletContext);
		
		UpdateEVSOAEditBatchDataService dataService = getServiceBean(UpdateEVSOAEditBatchDataService.class);
		return evBatchGenerator.createBatch(batchParam, dataService, servletContext);

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
		File f = new File(SevisUtils.batchFileRealPath(file, servletContext));
		if (f.exists()) {
			ResponseBuilder response = javax.ws.rs.core.Response.ok((Object) f);
			response.header("Content-Disposition", "attachment; filename=\"" + file + "\"");
			return response.build();
		} else {
			String msg = "<msg>" + file + " not found." + "</msg>";
			return javax.ws.rs.core.Response.status(javax.ws.rs.core.Response.Status.NOT_FOUND).entity(msg).build();
		}
	}
	
	private static <T> T getServiceBean(Class<T> klass) {
		return ApplicationContextProvider.getContext().getBean(klass);
	}

}

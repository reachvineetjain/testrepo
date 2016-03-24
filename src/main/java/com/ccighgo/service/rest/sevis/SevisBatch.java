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
import com.ccighgo.service.components.sevis.batch.EVBatchGenerator;
import com.ccighgo.service.components.sevis.batch.StuBatchGenerator;
import com.ccighgo.service.components.sevis.common.SevisUtils;
import com.ccighgo.service.components.sevis.data.CreateNewEVBatchDataService;
import com.ccighgo.service.components.sevis.data.CreateStudentBatchDataService;
import com.ccighgo.service.components.sevis.data.UpdateEVBioBatchDataService;
import com.ccighgo.service.components.sevis.data.UpdateEVDependentEndStatusBatchDataService;
import com.ccighgo.service.components.sevis.data.UpdateEVFinancialInfoBatchDataService;
import com.ccighgo.service.components.sevis.data.UpdateEVProgramAmendDataService;
import com.ccighgo.service.components.sevis.data.UpdateEVProgramEditSubjectBatchDataService;
import com.ccighgo.service.components.sevis.data.UpdateEVProgramShortenBatchDataService;
import com.ccighgo.service.components.sevis.data.UpdateEVSOAEditBatchDataService;
import com.ccighgo.service.components.sevis.data.UpdateEVSoaAddBatchDataService;
import com.ccighgo.service.components.sevis.data.UpdateEVStatusInvalidBatchDataService;
import com.ccighgo.service.components.sevis.data.UpdateEVStatusTerminateBatchDataService;
import com.ccighgo.service.components.sevis.data.UpdateEVTIPPBatchDataService;
import com.ccighgo.service.components.sevis.data.UpdateEVUpdateHousingBatchDataService;
import com.ccighgo.service.components.sevis.data.UpdateEVValidateHousingBatchDataService;
import com.ccighgo.service.components.sevis.data.UpdateStudentDependentReprintBatchDataService;
import com.ccighgo.service.components.sevis.data.UpdateStudentProgramExtentionBatchDataService;
import com.ccighgo.service.components.sevis.log.SevisLogProcessor;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.sevis.BatchParam;

@Path("/sevis/")
@Produces("application/json")
@Consumes("application/json")
public class SevisBatch {

	@Context
	ServletContext servletContext;
	// student
	@Autowired StuBatchGenerator stuBatchGenerator;
	//exchange visitor
	@Autowired EVBatchGenerator evBatchGenerator;
	// when we submit batch file they will return log file --> this is for processing !
	@Autowired SevisLogProcessor logProcessor;

	@GET
	@Path("process/log")
	@Produces("application/json")
	public Response processLog() {
		// we are using static code for the file location
		return logProcessor.processLog(null); // test only, pass xml
														// file URI or ....
	}

	@POST
	@Path("create/batch/new/student")
	@Produces("application/json")
	public Response createNewStudent(BatchParam batchParam) {
		// CreateStudent.Student
		
		CreateStudentBatchDataService dataService = getServiceBean(CreateStudentBatchDataService.class);
		return stuBatchGenerator.createBatch(batchParam, dataService, servletContext);
	}
	
	@POST
	@Path("create/batch/new/ev")
	@Produces("application/json")
	public Response createNewExchangeVisitor(BatchParam batchParam) {
		// CreateEV.ExchangeVisitor
		
		CreateNewEVBatchDataService dataService = getServiceBean(CreateNewEVBatchDataService.class);
		return evBatchGenerator.createBatch(batchParam, dataService, servletContext);
	}

	@POST
	@Path("create/batch/update/bio")
	@Produces("application/json")
	public Response updateEVBio(BatchParam batchParam) {
		// UpdateEV.ExchangeVisitor.Biographical
		
		UpdateEVBioBatchDataService dataService = getServiceBean(UpdateEVBioBatchDataService.class);
		return evBatchGenerator.createBatch(batchParam, dataService, servletContext);
	}

	@POST
	@Path("create/batch/update/status/invalid")
	@Produces("application/json")
	public Response updateStatusInvalid(BatchParam batchParam) {
		// Status Invalid
		// Update.EV.Status.Invalid
		
		UpdateEVStatusInvalidBatchDataService dataService = getServiceBean(UpdateEVStatusInvalidBatchDataService.class);
		return evBatchGenerator.createBatch(batchParam, dataService, servletContext);
	}

	@POST
	@Path("create/batch/update/status/terminate")
	@Produces("application/json")
	public Response updateStatusTerminate(BatchParam batchParam) {
		// Status Terminate
		// Update.EV.Status.Terminate
		// Update.Student.Status.Terminate ??
		
		UpdateEVStatusTerminateBatchDataService dataService = getServiceBean(UpdateEVStatusTerminateBatchDataService.class);
		return evBatchGenerator.createBatch(batchParam, dataService, servletContext);
	}
	
	@POST
	@Path("create/batch/update/status/end")
	@Produces("application/json")
	public Response updateStatusEnd(BatchParam batchParam) {
		// Status End
		// UpdateEV.ExchangeVisitor.Dependent.EndStatus
		
		UpdateEVDependentEndStatusBatchDataService dataService = getServiceBean(UpdateEVDependentEndStatusBatchDataService.class);
		return evBatchGenerator.createBatch(batchParam, dataService, servletContext);
	}

	@POST
	@Path("create/batch/update/editSubject")
	@Produces("application/json")
	public Response updateEditSubject(BatchParam batchParam) {
		// Update - EditSubject
		// Event - UpdateEV.ExchangeVisitor.Program.EditSubject
		UpdateEVProgramEditSubjectBatchDataService dataService = getServiceBean(UpdateEVProgramEditSubjectBatchDataService.class);
		return evBatchGenerator.createBatch(batchParam, dataService, servletContext);
	}

	@POST
	@Path("create/batch/update/financialInfo")
	@Produces("application/json")
	public Response updateFinancialInfo(BatchParam batchParam) {
		// Financial Info
		// UpdateEV.ExchangeVisitor.FinancialInfo
		// Update.Student.FinancialInfo ??
		UpdateEVFinancialInfoBatchDataService dataService = getServiceBean(UpdateEVFinancialInfoBatchDataService.class);
		return evBatchGenerator.createBatch(batchParam, dataService, servletContext);
	}

	@POST
	@Path("create/batch/update/tipp")
	@Produces("application/json")
	public Response updateTIPP(BatchParam batchParam) {
		// T/IPP
		// Update.EV.TIPP
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

		// EV
//		UpdateEVProgramExtentionBatchDataService dataService = getServiceBean(
//				UpdateEVProgramExtentionBatchDataService.class);
//		return evBatchGenerator.createBatch(batchParam, dataService, servletContext);
		
		// Student
		UpdateStudentProgramExtentionBatchDataService dataService = getServiceBean(
				UpdateStudentProgramExtentionBatchDataService.class);
		return stuBatchGenerator.createBatch(batchParam, dataService, servletContext);
	}

	@POST
	@Path("create/batch/update/program/shorten")
	@Produces("application/json")
	public Response updateProgramShorten(BatchParam batchParam) {
		// Program Shorten

		// Update.EV.Program.Shorten
		// Update.Student.Program.Shorten
		
		// EV
		UpdateEVProgramShortenBatchDataService dataService = getServiceBean(UpdateEVProgramShortenBatchDataService.class);
		return evBatchGenerator.createBatch(batchParam, dataService, servletContext);

		// Student
//		UpdateStudentProgramShortenBatchDataService dataService = getServiceBean(
//				UpdateStudentProgramShortenBatchDataService.class);		
//		return stuBatchGenerator.createBatch(batchParam, dataService, servletContext);

	}
	
	@POST
	@Path("create/batch/update/programdate")
	@Produces("application/json")
	public Response updateProgramDate(BatchParam batchParam) {
		// Program Date
		// UpdateEV.ExchangeVisitor.Program.Amend
		UpdateEVProgramAmendDataService dataService = getServiceBean(UpdateEVProgramAmendDataService.class);
		return evBatchGenerator.createBatch(batchParam, dataService, servletContext);

	}

	@POST
	@Path("create/batch/update/soa")
	@Produces("application/json")
	public Response updateSOA(BatchParam batchParam) {
		// Update - Site of Activity
		// Update.EV.SiteOfActivity.Edit
		
		UpdateEVSOAEditBatchDataService dataService = getServiceBean(UpdateEVSOAEditBatchDataService.class);
		return evBatchGenerator.createBatch(batchParam, dataService, servletContext);

	}
	
	@POST
	@Path("create/batch/validate/housing")
	@Produces("application/json")
	public Response valiateHousing(BatchParam batchParam) {
		// Validation - Housing
		// Update.EV.Validate.USAddress
		
		UpdateEVValidateHousingBatchDataService dataService = getServiceBean(UpdateEVValidateHousingBatchDataService.class);
		return evBatchGenerator.createBatch(batchParam, dataService, servletContext);
	}
	
	@POST
	@Path("create/batch/update/housing")
	@Produces("application/json")
	public Response updateHousing(BatchParam batchParam) {
		// Update - Housing
		// Update.EV.Biographical.USAddress
		
		UpdateEVUpdateHousingBatchDataService dataService = getServiceBean(UpdateEVUpdateHousingBatchDataService.class);
		return evBatchGenerator.createBatch(batchParam, dataService, servletContext);
	}
	
	@POST
	@Path("create/batch/validate/soa")
	@Produces("application/json")
	public Response valiateSOA(BatchParam batchParam) {
		// Validation - SOA
		// UpdateEV.ExchangeVisitor.SiteOfActivity.Add
		
		UpdateEVSoaAddBatchDataService dataService = getServiceBean(UpdateEVSoaAddBatchDataService.class);
		return evBatchGenerator.createBatch(batchParam, dataService, servletContext);
	}


	@GET
	@Path("ping")
	@Produces("application/json")
	public Response ping() {
		return new Response();
	}
	
	public static final String BATCH_DOWNLOAD_LINK = "/sevis/download/batch/";
	
	/**
	 * Download service.
	 * 
	 * @param file
	 * @return
	 */
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

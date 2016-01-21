package com.ccighgo.service.components.sevis;

import java.io.File;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.rest.sevis.SevisBatch;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.common.response.beans.Status;
import com.ccighgo.service.transport.sevis.BatchDetails;
import com.ccighgo.service.transport.sevis.BatchParam;
import com.ccighgo.utils.CCIConstants;

import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISBatchCreateUpdateEV;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.CreateEV;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV;
import gov.ice.xmlschema.sevisbatch.log.TransactionLogType;
import gov.ice.xmlschema.sevisbatch.log.TransactionLogType.BatchDetail.Process.Record;
import gov.ice.xmlschema.sevisbatch.student.SEVISBatchCreateUpdateStudent;
import gov.ice.xmlschema.sevisbatch.student.SEVISStudentBatchType.CreateStudent;
import gov.ice.xmlschema.sevisbatch.student.SEVISStudentBatchType.UpdateStudent;

@Component
public class SevisServiceImpl implements SevisService {

	@Autowired
	CommonComponentUtils componentUtils;
	@Autowired
	MessageUtils messageUtil;

	private Status createFailureStatus() {
		Status status = componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR,
				SevisBatchErrorCode.BATCH_CREATE_FAILED.getValue(), "Batch Creation Failed.");
		return status;
	}

	@Override
	public Response processSevisLog(File xmlFile) {

		// test log file
		TransactionLogType log = SevisUtils
				.unmarshalSevisLog(new File("C:\\git\\CCIGHGO\\xsd\\sevisbatch\\gov\\SevisTransLog.xml"));

		SevisLogUtils logUtils = new SevisLogUtils(log);
		System.out.println("Total participants processed = " + logUtils.getTotalRecords().size());
		System.out.println("Processing failed participants = " + logUtils.getFailedRecords().size());

		for (Record r : logUtils.getFailedRecords()) {
			System.out.println("failed participant sevis = " + r.getRequestID());
			System.out.println("Error = " + r.getResult().getErrorMessage());
		}

		for (Record r : logUtils.getSuccessRecords()) {
			System.out.println("New participant sevis id = " + r.getSevisID());
			System.out.println("Any Dependents? = " + r.getDependent().size());
			System.out.println("Dependent sevis id = " + r.getDependent().get(0).getDependentSevisID());
		}

		// TODO convert log JAXB into appropriate format required for front.
		// or save to DB

		return new Response();
	}
	
	@Override
	public Response createStudentBatch(BatchParam batchParam, ServletContext servletContext) {
		CreateStudentBatchDataService dataService = getServiceBean(CreateStudentBatchDataService.class);
		return createStudentBatch(batchParam, dataService, servletContext);
	}

	@Override
	public Response updateEVBiographical(BatchParam batchParam, ServletContext servletContext) {
		UpdateEVBioBatchDataService dataService = getServiceBean(UpdateEVBioBatchDataService.class);
		return createEVBatch(batchParam, dataService, servletContext);
	}

	@Override
	public Response updateEVStatusInvalid(BatchParam batchParam, ServletContext servletContext) {
		UpdateEVStatusInvalidBatchDataService dataService = getServiceBean(UpdateEVStatusInvalidBatchDataService.class);
		return createEVBatch(batchParam, dataService, servletContext);
	}

	@Override
	public Response updateEVDependentEndStatus(BatchParam batchParam, ServletContext servletContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateEVStatusTerminate(BatchParam batchParam, ServletContext servletContext) {
		UpdateEVStatusTerminateBatchDataService dataService = getServiceBean(
				UpdateEVStatusTerminateBatchDataService.class);
		return createEVBatch(batchParam, dataService, servletContext);
	}

	@Override
	public Response updateEVSOAEdit(BatchParam batchParam, ServletContext servletContext) {
		UpdateEVSOAEditBatchDataService dataService = getServiceBean(
				UpdateEVSOAEditBatchDataService.class);
		return createEVBatch(batchParam, dataService, servletContext);
	}

	@Override
	public Response updateStudentStatusTerminate(BatchParam batchParam, ServletContext servletContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateEVProgramEditSubject(BatchParam batchParam, ServletContext servletContext) {
		UpdateEVProgramEditSubjectBatchDataService dataService = getServiceBean(
				UpdateEVProgramEditSubjectBatchDataService.class);
		return createEVBatch(batchParam, dataService, servletContext);
	}

	@Override
	public Response updateEVFinancialInfo(BatchParam batchParam, ServletContext servletContext) {
		UpdateEVFinancialInfoBatchDataService dataService = getServiceBean(UpdateEVFinancialInfoBatchDataService.class);
		return createEVBatch(batchParam, dataService, servletContext);
	}

	@Override
	public Response updateEVTIPP(BatchParam batchParam, ServletContext servletContext) {
		UpdateEVTIPPBatchDataService dataService = getServiceBean(UpdateEVTIPPBatchDataService.class);
		return createEVBatch(batchParam, dataService, servletContext);
	}

	@Override
	public Response updateEVReprint(BatchParam batchParam, ServletContext servletContext) {
		UpdateEVReprintBatchDataService dataService = getServiceBean(UpdateEVReprintBatchDataService.class);
		return createEVBatch(batchParam, dataService, servletContext);
	}

	@Override
	public Response updateEVDependentReprint(BatchParam batchParam, ServletContext servletContext) {
		UpdateEVDependentReprintBatchDataService dataService = getServiceBean(
				UpdateEVDependentReprintBatchDataService.class);
		return createEVBatch(batchParam, dataService, servletContext);
	}

	@Override
	public Response updateStudentReprint(BatchParam batchParam, ServletContext servletContext) {
		UpdateStudentReprintBatchDataService dataService = getServiceBean(UpdateStudentReprintBatchDataService.class);
		return createStudentBatch(batchParam, dataService, servletContext);
	}

	@Override
	public Response updateStudentDependentReprint(BatchParam batchParam, ServletContext servletContext) {
		UpdateStudentDependentReprintBatchDataService dataService = getServiceBean(
				UpdateStudentDependentReprintBatchDataService.class);
		return createStudentBatch(batchParam, dataService, servletContext);
	}

	@Override
	public Response updateEVProgramExtension(BatchParam batchParam, ServletContext servletContext) {
		UpdateEVProgramExtentionBatchDataService dataService = getServiceBean(
				UpdateEVProgramExtentionBatchDataService.class);
		return createEVBatch(batchParam, dataService, servletContext);
	}

	@Override
	public Response updateEVProgramShorten(BatchParam batchParam, ServletContext servletContext) {
		UpdateEVProgramShortenBatchDataService dataService = getServiceBean(
				UpdateEVProgramShortenBatchDataService.class);
		return createEVBatch(batchParam, dataService, servletContext);
	}

	@Override
	public Response updateStudentProgramExtension(BatchParam batchParam, ServletContext servletContext) {
		UpdateStudentProgramExtentionBatchDataService dataService = getServiceBean(
				UpdateStudentProgramExtentionBatchDataService.class);
		return createStudentBatch(batchParam, dataService, servletContext);
	}

	@Override
	public Response updateStudentProgramShorten(BatchParam batchParam, ServletContext servletContext) {
		UpdateStudentProgramShortenBatchDataService dataService = getServiceBean(
				UpdateStudentProgramShortenBatchDataService.class);
		return createStudentBatch(batchParam, dataService, servletContext);
	}

	@Override
	public File getBatchFile(String file, ServletContext servletContext) {
		String path = batchFileRealPath(file, servletContext);
		return new File(path);
	}
	
	private Response createEVBatch(BatchParam batchParam, IEVBatchDataService dataService,
			ServletContext servletContext) {

		boolean invalidArgs = batchParam == null || dataService == null || servletContext == null;
		if (invalidArgs) {
			return createInvalidArgsResponse();
		}

		/*
		 * get batch data
		 */
		SEVISBatchCreateUpdateEV jaxb = dataService.fetchBatchData(batchParam);

		String file = jaxb.getBatchHeader().getBatchID() + ".xml";
		String outputFile = batchFileRealPath(file, servletContext);
		String schemaFile = servletContext.getRealPath(dataService.getSchemaFile());

		/*
		 * generate batch file
		 */
		boolean success = SevisUtils.generateBatchFile(jaxb, outputFile, schemaFile);
		
		BatchDetails batchDetails = createEVBatchDetails(jaxb);
		
		if (success) {
			batchDetails.setFileUrl(SevisBatch.BATCH_DOWNLOAD_LINK + file);
			batchDetails.setStatus(createSuccessStatus());
		} else {
			batchDetails.setStatus(createFailureStatus());
		}
		
		return batchDetails;
	}

	private BatchDetails createEVBatchDetails(SEVISBatchCreateUpdateEV jaxb) {
		BatchDetails batchDetails = new BatchDetails();
		batchDetails.setBatchId(jaxb.getBatchHeader().getBatchID());
		CreateEV createEV = jaxb.getCreateEV();
		UpdateEV updateEV = jaxb.getUpdateEV();
		int createCount = createEV != null && createEV.getExchangeVisitor() != null ? createEV.getExchangeVisitor().size() : 0;
		int updateCount = updateEV != null && updateEV.getExchangeVisitor() != null ? updateEV.getExchangeVisitor().size() : 0;
		batchDetails.setCreateCount(createCount);
		batchDetails.setUpdateCount(updateCount);
		return batchDetails;
	}

	private Response createStudentBatch(BatchParam batchParam, IStudentBatchDataService dataService,
			ServletContext servletContext) {
		
		boolean invalidArgs = batchParam == null || dataService == null || servletContext == null;
		if (invalidArgs) {
			return createInvalidArgsResponse();
		}
		
		StopWatch sw = new StopWatch();
		sw.start();
		SEVISBatchCreateUpdateStudent jaxb = dataService.fetchBatchData(batchParam);
		sw.stop();
		System.out.println("Data fetch time = " + sw.getTotalTimeSeconds());

		String file = jaxb.getBatchHeader().getBatchID() + ".xml";
		String outputFile = batchFileRealPath(file, servletContext);
		String schemaFile = servletContext.getRealPath(dataService.getSchemaFile());

		StopWatch sw1 = new StopWatch();
		sw1.start();
		boolean success = SevisUtils.generateBatchFile(jaxb, outputFile, schemaFile);
		sw1.stop();
		System.out.println("Batch file time = " + sw1.getTotalTimeSeconds());

		BatchDetails batchDetails = createStudentBatchDetails(jaxb);
		if (success) {
			batchDetails.setFileUrl(SevisBatch.BATCH_DOWNLOAD_LINK + file);
			batchDetails.setStatus(createSuccessStatus());
		} else {
			batchDetails.setStatus(createFailureStatus());
		}

		return batchDetails;
	}

	private BatchDetails createStudentBatchDetails(SEVISBatchCreateUpdateStudent jaxb) {
		BatchDetails batchDetails = new BatchDetails();
		batchDetails.setBatchId(jaxb.getBatchHeader().getBatchID());
		CreateStudent createStudent = jaxb.getCreateStudent();
		int createCount = createStudent != null && createStudent.getStudent() != null ? createStudent.getStudent().size() : 0;
		UpdateStudent updateStudent = jaxb.getUpdateStudent();
		int updateCount = updateStudent != null && updateStudent.getStudent() != null ? updateStudent.getStudent().size() : 0;
		batchDetails.setCreateCount(createCount);
		batchDetails.setUpdateCount(updateCount);
		return batchDetails;
	}
	
	private String batchFileRealPath(String file, ServletContext servletContext) {
		String xmlDir = SevisUtils.getProperty(SevisConstants.XML_DIR);
		String xmlFile = xmlDir + File.separator + file;
		return servletContext.getRealPath(xmlFile);
	}
	
	private Status createSuccessStatus() {
		Status status = componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO,
				SevisBatchErrorCode.POST_PARAM_NA.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
		return status;
	}

	private BatchDetails createInvalidArgsResponse() {
		BatchDetails batchDetails = new BatchDetails();
		Status status = componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR,
				SevisBatchErrorCode.POST_PARAM_NA.getValue(), "Invalid Arguments.");
		batchDetails.setStatus(status);
		return batchDetails;
	}
}

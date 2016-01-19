package com.ccighgo.service.components.sevis;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.ccighgo.exception.CcighgoException;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.common.response.beans.Status;
import com.ccighgo.service.transport.sevis.CreateSEVISBatch;
import com.ccighgo.service.transport.sevis.SEVISBatchDetails;
import com.ccighgo.utils.CCIConstants;

import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISBatchCreateUpdateEV;
import gov.ice.xmlschema.sevisbatch.log.TransactionLogType;
import gov.ice.xmlschema.sevisbatch.log.TransactionLogType.BatchDetail.Process.Record;
import gov.ice.xmlschema.sevisbatch.student.SEVISBatchCreateUpdateStudent;

@Component
public class SevisServiceImpl implements SevisService {

	@Autowired
	CommonComponentUtils componentUtils;
	@Autowired
	MessageUtils messageUtil;

	@Override
	public Response createBatch(CreateSEVISBatch batchParam, ServletContext servletContext) {
		SEVISBatchDetails batchDetails = new SEVISBatchDetails();

		boolean invalidArgs = batchParam == null || servletContext == null;
		if (invalidArgs) {
			return createInvalidArgsResponse();
		}

		SevisBatchType batchType = SevisBatchType.fromValue(batchParam.getBatchType());
		if (batchType == null) {
			String msg = "Sevis batch type is not defined for " + batchParam.getBatchType()
					+ " in SevisBatchType enum.";
			Status status = componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR,
					SevisBatchErrorCode.POST_PARAM_NA.getValue(), msg);
			batchDetails.setStatus(status);

			return batchDetails;
		}

		try {
			SEVISBatchCreateUpdateStudent jaxbObject = batchType.fetchBatchData(batchParam);

			String fileName = jaxbObject.getBatchHeader().getBatchID() + ".xml";
			String xmlFile = SevisConstants.BATCH_FILES_PATH + fileName; // test

			String schemaFile = servletContext.getRealPath(batchType.getSchemaFile());
			SevisUtils.generateXMLBatchFile(jaxbObject, xmlFile, schemaFile);

			batchDetails.setBatchId(jaxbObject.getBatchHeader().getBatchID());
			batchDetails.setParticipantCount(getParticipantsCount(jaxbObject, batchType));

			Status status = createSuccessStatus();
			batchDetails.setStatus(status);

		} catch (CcighgoException e) {
			Status status = createFailureStatus();
			batchDetails.setStatus(status);
		}

		return batchDetails;
	}

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

	private static int getParticipantsCount(Object obj, SevisBatchType batchType) {
		if (obj instanceof SEVISBatchCreateUpdateStudent) {
			SEVISBatchCreateUpdateStudent stu = (SEVISBatchCreateUpdateStudent) obj;
			if (batchType == SevisBatchType.CREATE_STUDENT) {
				return stu.getCreateStudent().getStudent().size();
			} else {
				return stu.getUpdateStudent().getStudent().size();
			}
		} else if (obj instanceof SEVISBatchCreateUpdateEV) {
			SEVISBatchCreateUpdateEV ev = (SEVISBatchCreateUpdateEV) obj;
			// if (batchType == SevisBatchType.CREATE_EV) {
			// return ev.getCreateEV().getExchangeVisitor().size();
			// } else {
			return ev.getUpdateEV().getExchangeVisitor().size();
			// }
		} else {
			throw new IllegalArgumentException(obj.getClass().getName());
		}
	}

	@Override
	public Response createStudent(CreateSEVISBatch batchParam, ServletContext servletContext) {
//		System.out.println("SevisServiceImpl ...");
		CreateStudentBatchDataService dataService = getServiceBean(CreateStudentBatchDataService.class);

//		System.out.println("data service = " + dataService);

		StopWatch sw = new StopWatch();
		sw.start();
		SEVISBatchCreateUpdateStudent jaxb = dataService.fetchBatchData(batchParam);
		sw.stop();
		System.out.println("Data fetch time = " + sw.getTotalTimeSeconds());

		String fileName = jaxb.getBatchHeader().getBatchID() + ".xml";
		String xmlFile = SevisConstants.BATCH_FILES_PATH + fileName; // test

		System.out.println("schema file = " + dataService.getSchemaFile());
		String schemaFile = servletContext.getRealPath(dataService.getSchemaFile());

		System.out.println("schemaFile = " + schemaFile);

		StopWatch sw1 = new StopWatch();
		sw1.start();
		SevisUtils.generateXMLBatchFile(jaxb, xmlFile, schemaFile);
		sw1.stop();
		System.out.println("Batch file time = " + sw1.getTotalTimeSeconds());

		SEVISBatchDetails batchDetails = createBatchDetailsFrom(jaxb, "create");

		Status status = createSuccessStatus();
		batchDetails.setStatus(status);

		return batchDetails;
	}

	@Override
	public Response updateEVBiographical(CreateSEVISBatch batchParam, ServletContext servletContext) {
		UpdateEVBioBatchDataService dataService = getServiceBean(UpdateEVBioBatchDataService.class);
		SEVISBatchDetails batchDetails = updateEVBatch(batchParam, dataService, servletContext);

		return batchDetails;
	}

	@Override
	public Response updateEVStatusInvalid(CreateSEVISBatch batchParam, ServletContext servletContext) {
		UpdateEVStatusInvalidBatchDataService dataService = getServiceBean(UpdateEVStatusInvalidBatchDataService.class);
		SEVISBatchDetails batchDetails = updateEVBatch(batchParam, dataService, servletContext);

		return batchDetails;
	}

	@Override
	public Response updateEVDependentEndStatus(CreateSEVISBatch batchParam, ServletContext servletContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateEVStatusTerminate(CreateSEVISBatch batchParam, ServletContext servletContext) {
		UpdateEVStatusTerminateBatchDataService dataService = getServiceBean(
				UpdateEVStatusTerminateBatchDataService.class);
		return updateEVBatch(batchParam, dataService, servletContext);
	}

	@Override
	public Response updateEVSOAEdit(CreateSEVISBatch batchParam, ServletContext servletContext) {
		UpdateEVSOAEditBatchDataService dataService = getServiceBean(
				UpdateEVSOAEditBatchDataService.class);
		return updateEVBatch(batchParam, dataService, servletContext);
	}

	@Override
	public Response updateStudentStatusTerminate(CreateSEVISBatch batchParam, ServletContext servletContext) {
		// TODO Auto-generated method stub
		return null;
	}

	private SEVISBatchDetails updateEVBatch(CreateSEVISBatch batchParam, IEVBatchDataService dataService,
			ServletContext servletContext) {

		boolean invalidArgs = batchParam == null || dataService == null || servletContext == null;
		if (invalidArgs) {
			return createInvalidArgsResponse();
		}

		SEVISBatchCreateUpdateEV jaxb = dataService.fetchBatchData(batchParam);

		// SEVISBatchDetails batchDetails = new SEVISBatchDetails();
		String fileName = jaxb.getBatchHeader().getBatchID() + ".xml";
		String xmlFile = SevisConstants.BATCH_FILES_PATH + fileName; // test

		String schemaFile = servletContext.getRealPath(dataService.getSchemaFile());
		SevisUtils.generateXMLBatchFile(jaxb, xmlFile, schemaFile);

		SEVISBatchDetails batchDetails = createBatchDetailsFrom(jaxb, "update");
		batchDetails.setStatus(createSuccessStatus());

		return batchDetails;
	}

	private SEVISBatchDetails createBatchDetailsFrom(SEVISBatchCreateUpdateEV batch, String type) {
		SEVISBatchDetails batchDetails = new SEVISBatchDetails();
		batchDetails.setBatchId(batch.getBatchHeader().getBatchID());

		if (type.equals("update")) {
			batchDetails.setParticipantCount(batch.getUpdateEV().getExchangeVisitor().size());
		} else {
			batchDetails.setParticipantCount(batch.getCreateEV().getExchangeVisitor().size());
		}

		return batchDetails;
	}

	private SEVISBatchDetails createBatchDetailsFrom(SEVISBatchCreateUpdateStudent batch, String type) {
		SEVISBatchDetails batchDetails = new SEVISBatchDetails();
		batchDetails.setBatchId(batch.getBatchHeader().getBatchID());

		if (type.equals("update")) {
			batchDetails.setParticipantCount(batch.getUpdateStudent().getStudent().size());
		} else {
			batchDetails.setParticipantCount(batch.getCreateStudent().getStudent().size());
		}

		return batchDetails;
	}

	private Status createSuccessStatus() {
		Status status = componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO,
				SevisBatchErrorCode.POST_PARAM_NA.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
		return status;
	}

	private SEVISBatchDetails createInvalidArgsResponse() {
		SEVISBatchDetails batchDetails = new SEVISBatchDetails();
		Status status = componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR,
				SevisBatchErrorCode.POST_PARAM_NA.getValue(), "Invalid Arguments.");
		batchDetails.setStatus(status);
		return batchDetails;
	}

	private SEVISBatchDetails updateStudentBatch(CreateSEVISBatch batchParam,
			IStudentBatchDataService dataService, ServletContext servletContext) {

		boolean invalidArgs = batchParam == null || dataService == null || servletContext == null;
		if (invalidArgs) {
			return createInvalidArgsResponse();
		}

		SEVISBatchCreateUpdateStudent jaxb = dataService.fetchBatchData(batchParam);

		// SEVISBatchDetails batchDetails = new SEVISBatchDetails();
		String fileName = jaxb.getBatchHeader().getBatchID() + ".xml";
		String xmlFile = SevisConstants.BATCH_FILES_PATH + fileName; // test

		String schemaFile = servletContext.getRealPath(dataService.getSchemaFile());
		SevisUtils.generateXMLBatchFile(jaxb, xmlFile, schemaFile);

		SEVISBatchDetails batchDetails = createBatchDetailsFrom(jaxb, "update");

		batchDetails.setStatus(createSuccessStatus());
		return batchDetails;
	}

	@Override
	public Response updateEVProgramEditSubject(CreateSEVISBatch batchParam, ServletContext servletContext) {
		UpdateEVProgramEditSubjectBatchDataService dataService = getServiceBean(
				UpdateEVProgramEditSubjectBatchDataService.class);
		SEVISBatchDetails batchDetails = updateEVBatch(batchParam, dataService, servletContext);

		return batchDetails;
	}

	@Override
	public Response updateEVFinancialInfo(CreateSEVISBatch batchParam, ServletContext servletContext) {
		UpdateEVFinancialInfoBatchDataService dataService = getServiceBean(UpdateEVFinancialInfoBatchDataService.class);
		SEVISBatchDetails batchDetails = updateEVBatch(batchParam, dataService, servletContext);

		return batchDetails;
	}

	@Override
	public Response updateEVTIPP(CreateSEVISBatch batchParam, ServletContext servletContext) {
		UpdateEVTIPPBatchDataService dataService = getServiceBean(UpdateEVTIPPBatchDataService.class);
		SEVISBatchDetails batchDetails = updateEVBatch(batchParam, dataService, servletContext);

		return batchDetails;
	}

	@Override
	public Response updateEVReprint(CreateSEVISBatch batchParam, ServletContext servletContext) {
		UpdateEVReprintBatchDataService dataService = getServiceBean(UpdateEVReprintBatchDataService.class);
		SEVISBatchDetails batchDetails = updateEVBatch(batchParam, dataService, servletContext);

		return batchDetails;
	}

	@Override
	public Response updateEVDependentReprint(CreateSEVISBatch batchParam, ServletContext servletContext) {
		UpdateEVDependentReprintBatchDataService dataService = getServiceBean(
				UpdateEVDependentReprintBatchDataService.class);
		SEVISBatchDetails batchDetails = updateEVBatch(batchParam, dataService, servletContext);

		return batchDetails;
	}

	@Override
	public Response updateStudentReprint(CreateSEVISBatch batchParam, ServletContext servletContext) {
		UpdateStudentReprintBatchDataService dataService = getServiceBean(UpdateStudentReprintBatchDataService.class);
		SEVISBatchDetails batchDetails = updateStudentBatch(batchParam, dataService, servletContext);

		return batchDetails;
	}

	@Override
	public Response updateStudentDependentReprint(CreateSEVISBatch batchParam, ServletContext servletContext) {
		UpdateStudentDependentReprintBatchDataService dataService = getServiceBean(
				UpdateStudentDependentReprintBatchDataService.class);
		SEVISBatchDetails batchDetails = updateStudentBatch(batchParam, dataService, servletContext);

		return batchDetails;
	}

	@Override
	public Response updateEVProgramExtension(CreateSEVISBatch batchParam, ServletContext servletContext) {
		UpdateEVProgramExtentionBatchDataService dataService = getServiceBean(
				UpdateEVProgramExtentionBatchDataService.class);
		SEVISBatchDetails batchDetails = updateEVBatch(batchParam, dataService, servletContext);
		return batchDetails;
	}

	@Override
	public Response updateEVProgramShorten(CreateSEVISBatch batchParam, ServletContext servletContext) {
		UpdateEVProgramShortenBatchDataService dataService = getServiceBean(
				UpdateEVProgramShortenBatchDataService.class);
		SEVISBatchDetails batchDetails = updateEVBatch(batchParam, dataService, servletContext);
		return batchDetails;
	}

	@Override
	public Response updateStudentProgramExtension(CreateSEVISBatch batchParam, ServletContext servletContext) {
		UpdateStudentProgramExtentionBatchDataService dataService = getServiceBean(
				UpdateStudentProgramExtentionBatchDataService.class);
		SEVISBatchDetails batchDetails = updateStudentBatch(batchParam, dataService, servletContext);
		return batchDetails;
	}

	@Override
	public Response updateStudentProgramShorten(CreateSEVISBatch batchParam, ServletContext servletContext) {
		UpdateStudentProgramShortenBatchDataService dataService = getServiceBean(
				UpdateStudentProgramShortenBatchDataService.class);
		SEVISBatchDetails batchDetails = updateStudentBatch(batchParam, dataService, servletContext);
		return batchDetails;
	}

}

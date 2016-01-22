package com.ccighgo.service.components.sevis;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.ccighgo.service.components.sevis.data.IBatchDataService;
import com.ccighgo.service.rest.sevis.SevisBatch;
import com.ccighgo.service.transport.sevis.BatchDetails;
import com.ccighgo.service.transport.sevis.BatchParam;

import gov.ice.xmlschema.sevisbatch.student.SEVISBatchCreateUpdateStudent;
import gov.ice.xmlschema.sevisbatch.student.SEVISStudentBatchType.CreateStudent;
import gov.ice.xmlschema.sevisbatch.student.SEVISStudentBatchType.UpdateStudent;

@Component
public class StuBatchGenerator extends AbstractBatchGenerator<SEVISBatchCreateUpdateStudent> {

	@Override
	public BatchDetails createBatch(BatchParam batchParam, IBatchDataService<SEVISBatchCreateUpdateStudent> dataService,
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
		String outputFile = SevisUtils.batchFileRealPath(file, servletContext);
		String schemaFile = servletContext.getRealPath(getBatchSchemaFile());

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

	@Override
	public String getBatchSchemaFile() {
		String schemaDir = SevisUtils.getProperty(SevisConstants.SCHEMA_DIR);
		String schemaFile = SevisUtils.getProperty(SevisConstants.STU_SCHEMA);
		return schemaDir + File.separator + schemaFile;
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
}

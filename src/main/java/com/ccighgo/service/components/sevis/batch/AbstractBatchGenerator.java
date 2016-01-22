package com.ccighgo.service.components.sevis.batch;

import java.io.File;

import javax.servlet.ServletContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.sevis.common.SevisBatchErrorCode;
import com.ccighgo.service.components.sevis.common.SevisUtils;
import com.ccighgo.service.components.sevis.data.IBatchDataService;
import com.ccighgo.service.rest.sevis.SevisBatch;
import com.ccighgo.service.transport.common.response.beans.Status;
import com.ccighgo.service.transport.sevis.BatchDetails;
import com.ccighgo.service.transport.sevis.BatchParam;
import com.ccighgo.utils.CCIConstants;

@Component
public abstract class AbstractBatchGenerator<T> implements IBatchGenerator<T> {

	@Autowired
	CommonComponentUtils componentUtils;

	@Autowired
	MessageUtils messageUtil;
	
	protected abstract T fetchBatchData(BatchParam param, IBatchDataService<T> dataService);
	protected abstract String getBatchId();
	protected abstract BatchDetails asBatchDetails(T obj);
	protected abstract String getBatchSchemaFile();
	
	@Override
	public BatchDetails createBatch(BatchParam batchParam, IBatchDataService<T> dataService,
			ServletContext servletContext) {
		/*
		 * Template Method
		 */
		
		boolean invalidArgs = batchParam == null || dataService == null || servletContext == null;
		if (invalidArgs) {
			return createInvalidArgsResponse();
		}
		
		T jaxb = fetchBatchData(batchParam, dataService); // abstract
		BatchDetails batchDetails = asBatchDetails(jaxb); // abstract
		String file = getBatchId(); // abstract
		String schema = getBatchSchemaFile(); // abstract
		
		String outputFile = SevisUtils.batchFileRealPath(file, servletContext);
		String schemaFile = servletContext.getRealPath(schema);

		try {
			JAXBContext ctx = JAXBContext.newInstance(jaxb.getClass());
			
			/*
			 * Validate
			 */
			boolean valid = SevisUtils.validateJaxb(ctx, jaxb, new File(schemaFile));
			
			if(!valid) {
				batchDetails.setStatus(createFailureStatus("Batch file validation failed."));
				return batchDetails;
			}
			
			/*
			 * Save to a file.
			 */
			boolean saved = SevisUtils.marshalJaxbToFile(ctx, jaxb, new File(outputFile));
			if(!saved) {
				batchDetails.setStatus(createFailureStatus("Batch file could not be saved."));
			} else {
				batchDetails.setFileUrl(SevisBatch.BATCH_DOWNLOAD_LINK + file);
				batchDetails.setStatus(createSuccessStatus());
			}			
		} catch (JAXBException e) {
			batchDetails.setStatus(createFailureStatus());
			e.printStackTrace();
		}

		return batchDetails;
	}	

	protected Status createFailureStatus() {
		Status status = componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR,
				SevisBatchErrorCode.BATCH_CREATE_FAILED.getValue(), "Batch Creation Failed.");
		return status;
	}
	protected Status createFailureStatus(String msg) {
		Status status = componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR,
				SevisBatchErrorCode.BATCH_CREATE_FAILED.getValue(), msg);
		return status;
	}

	protected Status createSuccessStatus() {
		Status status = componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO,
				SevisBatchErrorCode.POST_PARAM_NA.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
		return status;
	}

	protected BatchDetails createInvalidArgsResponse() {
		BatchDetails batchDetails = new BatchDetails();
		Status status = componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR,
				SevisBatchErrorCode.POST_PARAM_NA.getValue(), "Invalid Arguments.");
		batchDetails.setStatus(status);
		return batchDetails;
	}

}

package com.ccighgo.service.components.sevis;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;

import com.ccighgo.service.components.sevis.data.IBatchDataService;
import com.ccighgo.service.rest.sevis.SevisBatch;
import com.ccighgo.service.transport.sevis.BatchDetails;
import com.ccighgo.service.transport.sevis.BatchParam;

import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISBatchCreateUpdateEV;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.CreateEV;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV;

@Component
public class EVBatchGenerator extends AbstractBatchGenerator<SEVISBatchCreateUpdateEV> {

	@Override
	public String getBatchSchemaFile() {
		String schemaDir = SevisUtils.getProperty(SevisConstants.SCHEMA_DIR);
		String schemaFile = SevisUtils.getProperty(SevisConstants.EV_SCHEMA);
		return schemaDir + File.separator + schemaFile;
	}
	

	@Override
	public BatchDetails createBatch(BatchParam batchParam, IBatchDataService<SEVISBatchCreateUpdateEV> dataService,
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
		String outputFile = SevisUtils.batchFileRealPath(file, servletContext);
		String schemaFile = servletContext.getRealPath(getBatchSchemaFile());

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
}

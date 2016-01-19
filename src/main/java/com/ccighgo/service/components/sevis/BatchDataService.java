package com.ccighgo.service.components.sevis;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.sevis.CreateSEVISBatch;

import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISBatchCreateUpdateEV;
import gov.ice.xmlschema.sevisbatch.student.SEVISBatchCreateUpdateStudent;

/**
 * 
 * Get data from DB and map into SEVIS JAXB classes.
 *
 */
@Service
public interface BatchDataService {

	public SEVISBatchCreateUpdateStudent getCreateStudentsBatchData(CreateSEVISBatch batchParam);
	public SEVISBatchCreateUpdateEV getUpdateEVBiographicalBatchData(CreateSEVISBatch batchParam);

}

package com.ccighgo.service.components.sevis;

import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISBatchCreateUpdateEV;
import gov.ice.xmlschema.sevisbatch.student.SEVISBatchCreateUpdateStudent;

public interface SevisBatchDataProvider {
	/**
	 * Fetch batch type specific data from DB and convert it into respective
	 * JAXB classes ({@link SEVISBatchCreateUpdateStudent} or
	 * {@link SEVISBatchCreateUpdateEV}).
	 * 
	 * @param params
	 * @return
	 */
	public <T> T fetchBatchData(Object params);

}

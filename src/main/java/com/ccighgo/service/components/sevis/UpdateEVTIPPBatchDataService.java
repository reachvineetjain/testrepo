package com.ccighgo.service.components.sevis;

import org.springframework.stereotype.Component;

import com.ccighgo.service.transport.sevis.BatchParam;

import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISBatchCreateUpdateEV;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor.TIPP;

@Component
public class UpdateEVTIPPBatchDataService implements IEVBatchDataService {

	@Override
	public SEVISBatchCreateUpdateEV fetchBatchData(BatchParam batchParam) {
		// TODO Auto-generated method stub
		
		// choices
		// which one?
		return null;
	}

	private TIPP createTIPP() {
		TIPP tipp = new TIPP();
		return tipp;
	}

}

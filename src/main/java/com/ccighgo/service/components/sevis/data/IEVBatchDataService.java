package com.ccighgo.service.components.sevis.data;

import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import gov.ice.xmlschema.sevisbatch.common.BatchHeaderType;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISBatchCreateUpdateEV;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.CreateEV;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor;

@Service
public interface IEVBatchDataService
		extends IBatchDataService<SEVISBatchCreateUpdateEV> {

	/**
	 * Creates EV with minimal attributes for updates.
	 * 
	 * @param userId
	 * @param sevisId
	 * @param requestId
	 * @return {@link ExchangeVisitor}
	 */
	default ExchangeVisitor createExchangeVisitor(String userId, String sevisId, String requestId) {
		Preconditions.checkArgument(userId != null && !userId.isEmpty());
		Preconditions.checkArgument(sevisId != null && !sevisId.isEmpty());
		Preconditions.checkArgument(requestId != null && !requestId.isEmpty());

		ExchangeVisitor ev = new ExchangeVisitor();
		ev.setSevisID(sevisId);
		ev.setRequestID(requestId);
		ev.setUserID(userId);
		return ev;
	}

	/**
	 * Creates Update.EV batch with minimal required attributes.
	 * 
	 * @param userId
	 * @param orgId
	 * @param batchId
	 * @return {@link SEVISBatchCreateUpdateEV}
	 */
	default SEVISBatchCreateUpdateEV createUpdateEVBatch(String userId, String orgId, String batchId) {

		SEVISBatchCreateUpdateEV batch = createEVBatch(userId, orgId, batchId);
		UpdateEV updateEv = new UpdateEV();
		batch.setUpdateEV(updateEv);

		return batch;
	}

	/**
	 * Creates Create.EV batch with minimal required attributes.
	 * 
	 * @param userId
	 * @param orgId
	 * @param batchId
	 * @return
	 */
	default SEVISBatchCreateUpdateEV createCreateEVBatch(String userId, String orgId, String batchId) {

		SEVISBatchCreateUpdateEV batch = createEVBatch(userId, orgId, batchId);
		CreateEV createEV = new CreateEV();
		batch.setCreateEV(createEV);

		return batch;
	}

	default SEVISBatchCreateUpdateEV createEVBatch(String userId, String orgId, String batchId) {

		Preconditions.checkArgument(userId != null && !userId.isEmpty());
		Preconditions.checkArgument(orgId != null && !orgId.isEmpty());
		Preconditions.checkArgument(batchId != null && !batchId.isEmpty());

		SEVISBatchCreateUpdateEV batch = new SEVISBatchCreateUpdateEV();
		batch.setUserID(userId);

		BatchHeaderType headerType = new BatchHeaderType();

		headerType.setBatchID(batchId);

		// school code
		headerType.setOrgID(orgId);

		batch.setBatchHeader(headerType);

		return batch;
	}

}
package com.ccighgo.service.components.sevis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.transport.common.response.beans.Status;
import com.ccighgo.service.transport.sevis.BatchDetails;
import com.ccighgo.utils.CCIConstants;

@Component
public abstract class AbstractBatchGenerator<T> implements IBatchGenerator<T> {

	@Autowired
	CommonComponentUtils componentUtils;

	@Autowired
	MessageUtils messageUtil;

	protected Status createFailureStatus() {
		Status status = componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR,
				SevisBatchErrorCode.BATCH_CREATE_FAILED.getValue(), "Batch Creation Failed.");
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

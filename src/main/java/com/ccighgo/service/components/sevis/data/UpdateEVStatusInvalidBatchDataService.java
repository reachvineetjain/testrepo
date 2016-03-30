package com.ccighgo.service.components.sevis.data;

import org.springframework.stereotype.Component;

import com.ccighgo.service.components.sevis.common.SevisUtils;
import com.ccighgo.service.transport.sevis.BatchParam;

import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISBatchCreateUpdateEV;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor.Status;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor.Status.Invalid;

@Component
public class UpdateEVStatusInvalidBatchDataService implements IEVBatchDataService {

   @Override
   public SEVISBatchCreateUpdateEV fetchBatchData(BatchParam batchParam) {
      // String batchId = generateBatchId("fName", "lName");
      String batchId = SevisUtils.createBatchId();
      SEVISBatchCreateUpdateEV batch = createUpdateEVBatch(batchParam.getUserId(), "P-1-12345", batchId);

      ExchangeVisitor ev = createExchangeVisitor(batchParam.getUserId(), "N0000000000", "1");
      ev.setStatus(createEndStatus("Remarks"));

      batch.getUpdateEV().getExchangeVisitor().add(ev);

      return batch;
   }

   private Status createEndStatus(String remarks) {
      Status status = new Status();
      Invalid invalid = new Invalid();
      invalid.setRemarks(remarks);
      status.setInvalid(invalid);
      return status;
   }

}

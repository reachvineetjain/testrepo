package com.ccighgo.service.components.sevis.batch;

import java.io.File;

import org.springframework.stereotype.Component;

import com.ccighgo.service.components.sevis.common.SevisConstants;
import com.ccighgo.service.components.sevis.common.SevisUtils;
import com.ccighgo.service.components.sevis.data.IBatchDataService;
import com.ccighgo.service.transport.sevis.BatchDetails;
import com.ccighgo.service.transport.sevis.BatchParam;

import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISBatchCreateUpdateEV;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.CreateEV;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV;

@Component
public class EVBatchGenerator extends AbstractBatchGenerator<SEVISBatchCreateUpdateEV> {

   private SEVISBatchCreateUpdateEV jaxb;

   @Override
   protected SEVISBatchCreateUpdateEV fetchBatchData(BatchParam param, IBatchDataService<SEVISBatchCreateUpdateEV> dataService) {
      this.jaxb = dataService.fetchBatchData(param);
      return this.jaxb;
   }

   @Override
   protected String getBatchId() {
      return jaxb.getBatchHeader().getBatchID();
   }

   @Override
   protected BatchDetails asBatchDetails(SEVISBatchCreateUpdateEV jaxb) {
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

   @Override
   protected String getBatchSchemaFile() {
      String schemaDir = SevisUtils.getProperty(SevisConstants.SCHEMA_DIR);
      String schemaFile = SevisUtils.getProperty(SevisConstants.EV_SCHEMA);
      return schemaDir + File.separator + schemaFile;
   }
}

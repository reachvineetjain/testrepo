package com.ccighgo.service.components.sevis.batch;

import java.io.File;

import org.springframework.stereotype.Component;

import com.ccighgo.service.components.sevis.common.SevisConstants;
import com.ccighgo.service.components.sevis.common.SevisUtils;
import com.ccighgo.service.components.sevis.data.IBatchDataService;
import com.ccighgo.service.transport.sevis.BatchDetails;
import com.ccighgo.service.transport.sevis.BatchParam;

import gov.ice.xmlschema.sevisbatch.student.SEVISBatchCreateUpdateStudent;
import gov.ice.xmlschema.sevisbatch.student.SEVISStudentBatchType.CreateStudent;
import gov.ice.xmlschema.sevisbatch.student.SEVISStudentBatchType.UpdateStudent;

@Component
public class StuBatchGenerator extends AbstractBatchGenerator<SEVISBatchCreateUpdateStudent> {

   private SEVISBatchCreateUpdateStudent jaxb;

   @Override
   protected SEVISBatchCreateUpdateStudent fetchBatchData(BatchParam param, IBatchDataService<SEVISBatchCreateUpdateStudent> dataService) {
      jaxb = dataService.fetchBatchData(param);
      return jaxb;
   }

   @Override
   protected String getBatchId() {
      return jaxb.getBatchHeader().getBatchID();
   }

   @Override
   protected BatchDetails asBatchDetails(SEVISBatchCreateUpdateStudent jaxb) {
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

   @Override
   protected String getBatchSchemaFile() {
      String schemaDir = SevisUtils.getProperty(SevisConstants.SCHEMA_DIR);
      String schemaFile = SevisUtils.getProperty(SevisConstants.STU_SCHEMA);
      return schemaDir + File.separator + schemaFile;
   }
}

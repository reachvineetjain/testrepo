package com.ccighgo.service.components.sevis.data;

import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import gov.ice.xmlschema.sevisbatch.alpha.common.BatchHeaderType;
import gov.ice.xmlschema.sevisbatch.student.SEVISBatchCreateUpdateStudent;
import gov.ice.xmlschema.sevisbatch.student.SEVISStudentBatchType.CreateStudent;
import gov.ice.xmlschema.sevisbatch.student.SEVISStudentBatchType.UpdateStudent;
import gov.ice.xmlschema.sevisbatch.student.SEVISStudentBatchType.UpdateStudent.Student;

@Service
public interface IStudentBatchDataService
		extends IBatchDataService<SEVISBatchCreateUpdateStudent> {

	/**
	 * Creates Update.Student batch with required attributes.
	 * 
	 * @param userId
	 * @param orgId
	 * @param batchId
	 * @return
	 */
	default SEVISBatchCreateUpdateStudent createUpdateStudentBatch(String userId, String orgId, String batchId) {

		SEVISBatchCreateUpdateStudent batch = createStudentBatch(userId, orgId, batchId);
		UpdateStudent updateStu = new UpdateStudent();
		batch.setUpdateStudent(updateStu);

		return batch;
	}

	/**
	 * Creates Create.Student batch with required attributes.
	 * 
	 * @param userId
	 * @param orgId
	 * @param batchId
	 * @return
	 */
	default SEVISBatchCreateUpdateStudent createCreateStudentBatch(String userId, String orgId, String batchId) {
		SEVISBatchCreateUpdateStudent batch = createStudentBatch(userId, orgId, batchId);

		CreateStudent createStu = new CreateStudent();
		batch.setCreateStudent(createStu);

		return batch;
	}

	/**
	 * Creates {@link Student} with minimal attributes.
	 * 
	 * @param sevisId
	 * @param requestId
	 * @param userId
	 * @return
	 */
	default Student createStudentForUpdate(String sevisId, String requestId, String userId) {
		Student student = new Student();
		student.setSevisID(sevisId);
		student.setRequestID(requestId);
		student.setUserID(userId);
		return student;
	}

	default SEVISBatchCreateUpdateStudent createStudentBatch(String userId, String orgId, String batchId) {
		Preconditions.checkArgument(userId != null && !userId.isEmpty());
		Preconditions.checkArgument(orgId != null && !orgId.isEmpty());
		Preconditions.checkArgument(batchId != null && !batchId.isEmpty());

		SEVISBatchCreateUpdateStudent batch = new SEVISBatchCreateUpdateStudent();
		batch.setUserID(userId);

		BatchHeaderType headerType = new BatchHeaderType();

		headerType.setBatchID(batchId);

		// school code
		headerType.setOrgID(orgId);

		batch.setBatchHeader(headerType);

		return batch;
	}

}
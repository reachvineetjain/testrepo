package com.ccighgo.service.components.sevis;

import static com.ccighgo.service.components.sevis.SevisUtils.generateBatchId;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Participant;
import com.ccighgo.jpa.repositories.ParticipantRepository;
import com.ccighgo.service.transport.sevis.CreateSEVISBatch;

import gov.ice.xmlschema.sevisbatch.student.ReprintType;
import gov.ice.xmlschema.sevisbatch.student.SEVISBatchCreateUpdateStudent;
import gov.ice.xmlschema.sevisbatch.student.SEVISStudentBatchType.UpdateStudent.Student;

@Component
public class UpdateStudentReprintBatchDataService implements IStudentBatchDataService {
	@Autowired
	ParticipantRepository participantRepository;

	@Override
	public SEVISBatchCreateUpdateStudent fetchBatchData(CreateSEVISBatch batchParam) {

		// get EVs from DB
		// @formatter:off
		List<Integer> participantIds = batchParam.getParticipant()
				.stream()
				.map(p -> p.getParticipantGoId())
				.collect(Collectors.toList());
		// @formatter:on

		List<Participant> participants = participantRepository.findByParticipantGoIdIn(participantIds);

		// @formatter:off
		// map each participants into Student
		List<Student> students = participants.stream()
				.map(p -> createStudentForUpdate("N0000000000", "1", batchParam.getUserId()))
				.collect(Collectors.toList());
		// @formatter:on

		students.forEach(s -> s.setReprint(createReprintType(true, StudentReprintReason.ONE)));

		String batchId = generateBatchId("fName", "lName");
		SEVISBatchCreateUpdateStudent updateBatch = createUpdateStudentBatch(batchParam.getUserId(), "P-1-12345",
				batchId);
		updateBatch.getUpdateStudent().getStudent().addAll(students);

		return updateBatch;
	}

	// /**
	// * Creates {@link Student} with minimal attributes.
	// *
	// * @param sevisId
	// * @param requestId
	// * @param userId
	// * @return
	// */
	// private Student createStudent(String sevisId, String requestId, String
	// userId) {
	// Student student = new Student();
	// student.setSevisID(sevisId);
	// student.setRequestID(requestId);
	// student.setUserID(userId);
	// return student;
	// }

	private ReprintType createReprintType(Boolean printForm, StudentReprintReason reason) {
		ReprintType reprintType = new ReprintType();
		reprintType.setPrintForm(printForm);
		reprintType.setReason(reason.getCode());
		return reprintType;
	}

}

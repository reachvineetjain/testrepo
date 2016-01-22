package com.ccighgo.service.components.sevis.data;

import static com.ccighgo.service.components.sevis.SevisUtils.generateBatchId;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Participant;
import com.ccighgo.jpa.repositories.ParticipantRepository;
import com.ccighgo.service.transport.sevis.BatchParam;

import gov.ice.xmlschema.sevisbatch.student.SEVISBatchCreateUpdateStudent;
import gov.ice.xmlschema.sevisbatch.student.SEVISStudentBatchType.UpdateStudent.Student;
import gov.ice.xmlschema.sevisbatch.student.SEVISStudentBatchType.UpdateStudent.Student.Dependent;
import gov.ice.xmlschema.sevisbatch.student.SEVISStudentBatchType.UpdateStudent.Student.Dependent.Reprint;

@Component
public class UpdateStudentDependentReprintBatchDataService implements IStudentBatchDataService {
	@Autowired
	ParticipantRepository participantRepository;

	@Override
	public SEVISBatchCreateUpdateStudent fetchBatchData(BatchParam batchParam) {

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

		students.forEach(s -> s.setDependent(createDependentReprint("N0000000000")));
		String batchId = generateBatchId("fName", "lName");
		SEVISBatchCreateUpdateStudent updateBatch = createUpdateStudentBatch(batchParam.getUserId(), "P-1-12345",
				batchId);
		updateBatch.getUpdateStudent().getStudent().addAll(students);

		return updateBatch;
	}

	private Dependent createDependentReprint(String depSevisId) {
		Dependent dependent = new Dependent();
		dependent.setReprint(createReprint(depSevisId));
		return dependent;

	}

	private Reprint createReprint(String depSevisId) {
		Reprint reprint = new Reprint();
		reprint.setDependentSevisID(depSevisId);
		return reprint;
	}

}

package com.ccighgo.service.components.sevis;

import static com.ccighgo.service.components.sevis.SevisUtils.generateBatchId;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Participant;
import com.ccighgo.jpa.repositories.ParticipantRepository;
import com.ccighgo.service.transport.sevis.BatchParam;

import gov.ice.xmlschema.sevisbatch.student.SEVISBatchCreateUpdateStudent;
import gov.ice.xmlschema.sevisbatch.student.SEVISStudentBatchType.UpdateStudent.Student;
import gov.ice.xmlschema.sevisbatch.student.SEVISStudentBatchType.UpdateStudent.Student.Program;
import gov.ice.xmlschema.sevisbatch.student.SEVISStudentBatchType.UpdateStudent.Student.Program.Shorten;

@Component
public class UpdateStudentProgramShortenBatchDataService implements IStudentBatchDataService {

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

		students.forEach(s -> s.setProgram(createProgramShorten(true, SevisUtils.convert(LocalDate.now()))));

		String batchId = generateBatchId("fName", "lName");
		SEVISBatchCreateUpdateStudent updateBatch = createUpdateStudentBatch(batchParam.getUserId(), "P-1-12345",
				batchId);
		updateBatch.getUpdateStudent().getStudent().addAll(students);

		return updateBatch;
	}

	private Program createProgramShorten(boolean printForm, XMLGregorianCalendar newPrgEndDate) {
		Program program = new Program();
		program.setShorten(createShorten(printForm, newPrgEndDate));
		return program;
	}

	private Shorten createShorten(boolean printForm, XMLGregorianCalendar newPrgEndDate) {
		Shorten shorten = new Shorten();
		shorten.setPrintForm(printForm);
		shorten.setNewPrgEndDate(newPrgEndDate);
		return shorten;
	}

}

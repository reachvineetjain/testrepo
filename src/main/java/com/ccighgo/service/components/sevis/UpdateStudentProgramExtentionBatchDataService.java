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
import com.ccighgo.service.transport.sevis.CreateSEVISBatch;

import gov.ice.xmlschema.sevisbatch.student.SEVISBatchCreateUpdateStudent;
import gov.ice.xmlschema.sevisbatch.student.SEVISStudentBatchType.UpdateStudent.Student;
import gov.ice.xmlschema.sevisbatch.student.SEVISStudentBatchType.UpdateStudent.Student.Program;
import gov.ice.xmlschema.sevisbatch.student.SEVISStudentBatchType.UpdateStudent.Student.Program.Extension;

@Component
public class UpdateStudentProgramExtentionBatchDataService implements IStudentBatchDataService {
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

		students.forEach(
				s -> s.setProgram(createProgramExtension(true, SevisUtils.convert(LocalDate.now()), "Explanation")));

		String batchId = generateBatchId("fName", "lName");
		SEVISBatchCreateUpdateStudent updateBatch = createUpdateStudentBatch(batchParam.getUserId(), "P-1-12345",
				batchId);
		updateBatch.getUpdateStudent().getStudent().addAll(students);

		return updateBatch;
	}

	private Program createProgramExtension(boolean printForm, XMLGregorianCalendar newPrgEndDate, String explanation) {
		Program program = new Program();
		program.setExtension(createExtension(printForm, newPrgEndDate, explanation));
		return program;
	}

	/**
	 * 
	 * @param printForm
	 *            Print request indicator (Value: 1 or true; 0 or false)
	 *            <p>
	 *            Indicator used to request that I-20 in PDF document is
	 *            returned with SEVIS Batch download.
	 * @param newPrgEndDate
	 * @param explanation
	 *            Explanation for extension of program.
	 * @return
	 */
	private Extension createExtension(boolean printForm, XMLGregorianCalendar newPrgEndDate, String explanation) {
		Extension ext = new Extension();
		ext.setPrintForm(printForm);
		ext.setNewPrgEndDate(newPrgEndDate);
		ext.setExplanation(explanation);
		return ext;
	}

}

package com.ccighgo.service.components.sevis;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Participant;
import com.ccighgo.jpa.repositories.ParticipantRepository;
import com.ccighgo.service.transport.sevis.BatchParam;

import gov.ice.xmlschema.sevisbatch.alpha.common.BatchHeaderType;
import gov.ice.xmlschema.sevisbatch.alpha.common.ForeignAddrType;
import gov.ice.xmlschema.sevisbatch.alpha.common.NameType;
import gov.ice.xmlschema.sevisbatch.alpha.common.USAddrDoctorType;
import gov.ice.xmlschema.sevisbatch.alpha.table.BirthCntryCodeType;
import gov.ice.xmlschema.sevisbatch.alpha.table.CntryCodeWithoutType;
import gov.ice.xmlschema.sevisbatch.alpha.table.GenderCodeType;
import gov.ice.xmlschema.sevisbatch.alpha.table.StudentCreationReason;
import gov.ice.xmlschema.sevisbatch.student.EducationalInfoType;
import gov.ice.xmlschema.sevisbatch.student.EducationalInfoType.EduLevel;
import gov.ice.xmlschema.sevisbatch.student.EducationalInfoType.EngProficiency;
import gov.ice.xmlschema.sevisbatch.student.FinancialType;
import gov.ice.xmlschema.sevisbatch.student.FinancialType.Expense;
import gov.ice.xmlschema.sevisbatch.student.FinancialType.Funding;
import gov.ice.xmlschema.sevisbatch.student.SEVISBatchCreateUpdateStudent;
import gov.ice.xmlschema.sevisbatch.student.SEVISStudentBatchType.CreateStudent;
import gov.ice.xmlschema.sevisbatch.student.SEVISStudentBatchType.CreateStudent.Student;
import gov.ice.xmlschema.sevisbatch.student.StudentPersonType.PersonalInfo;

@Component("createStudentBatchDataService")
public class CreateStudentBatchDataService implements IStudentBatchDataService {

	@Autowired
	ParticipantRepository participantRepository;

	@Override
	public SEVISBatchCreateUpdateStudent fetchBatchData(BatchParam batchParam) {
		SEVISBatchCreateUpdateStudent batch = new SEVISBatchCreateUpdateStudent();
		batch.setUserID(batchParam.getUserId());

		BatchHeaderType headerType = new BatchHeaderType();

		// generate new batch ID
		// TODO may be a DB sequence to generate 12 digit number
		// data length = 14
//		headerType.setBatchID(SevisUtils.generateBatchId("fName", "lName"));
		headerType.setBatchID(SevisUtils.createBatchId());

		// school code
		headerType.setOrgID("P-1-12345");

		batch.setBatchHeader(headerType);

		// get student participants from DB
		List<Integer> participantIds = batchParam.getParticipant().stream().map(p -> p.getParticipantGoId())
				.collect(Collectors.toList());
		List<Participant> participants = participantRepository.findByParticipantGoIdIn(participantIds);

		// if (participants.size() > 250) {
		// // partition into chunks of 250 participants.
		// List<List<Integer>> lists = Lists.partition(participantIds, 250);
		// }

		CreateStudent createStu = new CreateStudent();

		// map each participants into Student
		participants.stream().map(p -> intoStudent(p, batchParam.getUserId()))
				.forEach(s -> createStu.getStudent().add(s));

		batch.setCreateStudent(createStu);

		return batch;
	}

	private Student intoStudent(Participant participant, String userId) {

		Student student = new Student();
		student.setRequestID("requestId");
		student.setUserID(userId);
		student.setPrintForm(true);

		PersonalInfo personalInfo = new PersonalInfo();
		NameType fullName = new NameType();
		fullName.setFirstName(participant.getFirstName());
		fullName.setLastName(participant.getLastName());
		personalInfo.setFullName(fullName);

		personalInfo.setBirthDate(SevisUtils.convert(LocalDate.now()));
		personalInfo.setBirthCountryCode(BirthCntryCodeType.US);
		personalInfo.setGender(GenderCodeType.F);
		personalInfo.setCitizenshipCountryCode(CntryCodeWithoutType.AA);

		/*
		 * Visa class of admission type (Value: 01 = F-1; 02 = M-1)
		 */
		personalInfo.setVisaType("01");

		student.setPersonalInfo(personalInfo);

		/*
		 * Initial student (Value: I = Initial Attendance S = Change of Status)
		 */
		student.setIssueReason(StudentCreationReason.I);

		USAddrDoctorType usaAddress = new USAddrDoctorType();
		usaAddress.setAddress1("Address 1");
		usaAddress.setPostalCode("60169");
		student.setUSAddress(usaAddress);

		ForeignAddrType fAddress = new ForeignAddrType();
		fAddress.setAddress1("Address 1");
		fAddress.setCountryCode(CntryCodeWithoutType.AA);
		student.setForeignAddress(fAddress);

		EducationalInfoType eduInfo = new EducationalInfoType();
		EduLevel eduLevel = new EduLevel();
		eduLevel.setLevel("01");
		eduInfo.setEduLevel(eduLevel);

		/*
		 * Primary major code (Format: 12.1234) (NOTE: The code sent to SEVIS
		 * must include the decimal point.), Data Length = 7
		 */
		eduInfo.setPrimaryMajor("01.0000");

		eduInfo.setLengthOfStudy("10");
		eduInfo.setPrgStartDate(SevisUtils.convert(LocalDate.now()));
		eduInfo.setPrgEndDate(SevisUtils.convert(LocalDate.now()));

		EngProficiency engProf = new EngProficiency();
		engProf.setEngRequired(true);
		eduInfo.setEngProficiency(engProf);

		student.setEducationalInfo(eduInfo);

		FinancialType finInfo = new FinancialType();
		/*
		 * Number of months in the academic term (Format: Integer only), Data
		 * Length = 2
		 */
		finInfo.setAcademicTerm("10");
		Expense exp = new Expense();
		exp.setTuition(10000);
		exp.setLivingExpense(500);
		finInfo.setExpense(exp);

		Funding fund = new Funding();
		fund.setPersonal(1000);
		finInfo.setFunding(fund);

		student.setFinancialInfo(finInfo);

		return student;
	}

}
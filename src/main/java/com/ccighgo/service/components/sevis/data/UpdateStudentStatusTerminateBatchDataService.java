package com.ccighgo.service.components.sevis.data;

import gov.ice.xmlschema.sevisbatch.student.SEVISBatchCreateUpdateStudent;
import gov.ice.xmlschema.sevisbatch.student.SEVISStudentBatchType.UpdateStudent.Student;
import gov.ice.xmlschema.sevisbatch.student.SEVISStudentBatchType.UpdateStudent.Student.Status;
import gov.ice.xmlschema.sevisbatch.student.SEVISStudentBatchType.UpdateStudent.Student.Status.Terminate;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Participant;
import com.ccighgo.jpa.repositories.ParticipantRepository;
import com.ccighgo.service.components.sevis.common.SevisUtils;
import com.ccighgo.service.transport.sevis.BatchParam;

@Component
public class UpdateStudentStatusTerminateBatchDataService implements IStudentBatchDataService {
   @Autowired ParticipantRepository participantRepository;

   @Override
   public SEVISBatchCreateUpdateStudent fetchBatchData(BatchParam batchParam) {

      // get EVs from DB
      // @formatter:off
      List<Integer> participantIds = batchParam.getParticipant().stream().map(p -> p.getParticipantGoId()).collect(Collectors.toList());
      // @formatter:on

      List<Participant> participants = participantRepository.findByParticipantGoIdIn(participantIds);

      // @formatter:off
      // map each participants into Student
      List<Student> students = participants.stream().map(p -> createStudentForUpdate("N0000000000", "1", batchParam.getUserId())).collect(Collectors.toList());
      // @formatter:on

      students.forEach(s -> s.setStatus(createStatus(createTerminate("01"))));

      String batchId = SevisUtils.createBatchId();
      SEVISBatchCreateUpdateStudent updateBatch = createUpdateStudentBatch(batchParam.getUserId(), "P-1-12345", batchId);
      updateBatch.getUpdateStudent().getStudent().addAll(students);

      return updateBatch;
   }

   private Status createStatus(Terminate terminate) {
      Status status = new Status();
      status.setTerminate(terminate);
      return status;
   }

   private Terminate createTerminate(String reason) {
      Terminate terminate = new Terminate();
      terminate.setReason(reason);
      return terminate;
   }

   // private Program createProgramExtension(boolean printForm,
   // XMLGregorianCalendar newPrgEndDate, String explanation) {
   // Program program = new Program();
   // program.setExtension(createExtension(printForm, newPrgEndDate,
   // explanation));
   // return program;
   // }
   //
   // /**
   // *
   // * @param printForm
   // * Print request indicator (Value: 1 or true; 0 or false)
   // * <p>
   // * Indicator used to request that I-20 in PDF document is
   // * returned with SEVIS Batch download.
   // * @param newPrgEndDate
   // * @param explanation
   // * Explanation for extension of program.
   // * @return
   // */
   // private Extension createExtension(boolean printForm, XMLGregorianCalendar
   // newPrgEndDate, String explanation) {
   // Extension ext = new Extension();
   // ext.setPrintForm(printForm);
   // ext.setNewPrgEndDate(newPrgEndDate);
   // ext.setExplanation(explanation);
   // return ext;
   // }

}

package com.ccighgo.service.components.sevis;

import static com.ccighgo.service.components.sevis.SevisUtils.generateBatchId;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Participant;
import com.ccighgo.jpa.repositories.ParticipantRepository;
import com.ccighgo.service.transport.sevis.BatchParam;

import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISBatchCreateUpdateEV;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor.Program;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor.Program.EditSubject;

@Component
public class UpdateEVProgramEditSubjectBatchDataService implements IEVBatchDataService {

	@Autowired
	ParticipantRepository participantRepository;

	@Override
	public SEVISBatchCreateUpdateEV fetchBatchData(BatchParam batchParam) {

		// get EVs from DB
		// @formatter:off
		List<Integer> participantIds = batchParam.getParticipant()
				.stream()
				.map(p -> p.getParticipantGoId())
				.collect(toList());
		// @formatter:on

		List<Participant> participants = participantRepository.findByParticipantGoIdIn(participantIds);

		// @formatter:off
		List<ExchangeVisitor> evs = participants.stream()
				.map(p -> intoEV(p, batchParam.getUserId(), "N0000000000", "1"))
				.collect(toList());
		// @formatter:on

		evs.forEach(ev -> ev.setProgram(createEditSubjectProgram()));

		String batchId = generateBatchId("fName", "lName");
		SEVISBatchCreateUpdateEV batch = createUpdateEVBatch(batchParam.getUserId(), "P-1-12345", batchId);
		batch.getUpdateEV().getExchangeVisitor().addAll(evs);

		return batch;
	}

	private ExchangeVisitor intoEV(Participant p, String userId, String sevisId, String requestId) {
		// p -> EV
		return createExchangeVisitor(userId, sevisId, requestId);
	}

	private Program createEditSubjectProgram() {
		Program program = new Program();
		program.setEditSubject(createEditSubject());
		return program;
	}

	private EditSubject createEditSubject() {
		EditSubject editSub = new EditSubject();
		editSub.setPrintForm(true);

		/*
		 * Business Rule: Code from CIP 2010 list of codes that represents
		 * exchange visitor’s subject or field of study. (See table entitled
		 * Primary Major Codes for Students and Subject/Field Codes for Exchange
		 * Visitors)
		 * 
		 */

		/*
		 * Data Definition: Code for subject or field of study (Format: 12.1234)
		 * (NOTE: The code sent to SEVIS must include the decimal point.)
		 */
		editSub.setSubjectFieldCode("01.0106");

		editSub.setSubjectFieldRemarks("Subject Field Remarks");
		editSub.setRemarks("Remarks");

		return editSub;
	}

}

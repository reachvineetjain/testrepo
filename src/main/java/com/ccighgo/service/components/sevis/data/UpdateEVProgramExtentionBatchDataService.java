package com.ccighgo.service.components.sevis.data;

import static com.ccighgo.service.components.sevis.SevisUtils.generateBatchId;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Participant;
import com.ccighgo.jpa.repositories.ParticipantRepository;
import com.ccighgo.service.components.sevis.SevisUtils;
import com.ccighgo.service.transport.sevis.BatchParam;

import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISBatchCreateUpdateEV;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor.Program;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor.Program.Extension;

@Component
public class UpdateEVProgramExtentionBatchDataService implements IEVBatchDataService {
	@Autowired
	ParticipantRepository participantRepository;

	@Override
	public SEVISBatchCreateUpdateEV fetchBatchData(BatchParam batchParam) {
		// get EVs from DB
		// @formatter:off
		List<Integer> participantIds = batchParam.getParticipant()
				.stream()
				.map(p -> p.getParticipantGoId())
				.collect(Collectors.toList());
		// @formatter:on

		List<Participant> participants = participantRepository.findByParticipantGoIdIn(participantIds);

		// @formatter:off
		List<ExchangeVisitor> evs = participants.stream()
				.map(p -> intoEV(p, batchParam.getUserId(), "N0000000000", "1"))
				.collect(Collectors.toList());
		// @formatter:on

		evs.forEach(ev -> ev.setProgram(createProgramExtension(true, SevisUtils.convert(LocalDate.now()))));

		String batchId = generateBatchId("fName", "lName");
		SEVISBatchCreateUpdateEV batch = createUpdateEVBatch(batchParam.getUserId(), "P-1-12345", batchId);
		batch.getUpdateEV().getExchangeVisitor().addAll(evs);

		return batch;
	}

	private ExchangeVisitor intoEV(Participant p, String userId, String sevisId, String requestId) {
		// p -> EV
		return createExchangeVisitor(userId, sevisId, requestId);
	}

	private Program createProgramExtension(boolean printForm, XMLGregorianCalendar newPrgEndDate) {
		Program program = new Program();
		program.setExtension(createExtension(printForm, newPrgEndDate));
		return program;
	}

	private Extension createExtension(boolean printForm, XMLGregorianCalendar newPrgEndDate) {
		Extension ext = new Extension();
		ext.setPrintForm(printForm);
		ext.setNewPrgEndDate(newPrgEndDate);
		return ext;
	}

}

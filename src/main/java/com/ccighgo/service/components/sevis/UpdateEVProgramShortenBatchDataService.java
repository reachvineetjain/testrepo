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

import gov.ice.xmlschema.sevisbatch.alpha.table.EVCompletionCodeType;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISBatchCreateUpdateEV;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor.Program;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor.Program.Shorten;

@Component
public class UpdateEVProgramShortenBatchDataService implements IEVBatchDataService {
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

		evs.forEach(ev -> ev.setProgram(
				createProgramShorten(true, SevisUtils.convert(LocalDate.now()), "Remarks", EVCompletionCodeType.APED)));

		String batchId = generateBatchId("fName", "lName");
		SEVISBatchCreateUpdateEV batch = createUpdateEVBatch(batchParam.getUserId(), "P-1-12345", batchId);
		batch.getUpdateEV().getExchangeVisitor().addAll(evs);

		return batch;
	}

	private ExchangeVisitor intoEV(Participant p, String userId, String sevisId, String requestId) {
		// p -> EV
		return createExchangeVisitor(userId, sevisId, requestId);
	}

	private Program createProgramShorten(boolean printForm, XMLGregorianCalendar newPrgEndDate, String remarks,
			EVCompletionCodeType shortenReason) {
		Program program = new Program();
		program.setShorten(createShorten(printForm, newPrgEndDate, remarks, shortenReason));
		return program;
	}

	private Shorten createShorten(boolean printForm, XMLGregorianCalendar newPrgEndDate, String remarks,
			EVCompletionCodeType shortenReason) {
		Shorten shorten = new Shorten();
		shorten.setPrintForm(printForm);
		shorten.setNewPrgEndDate(newPrgEndDate);
		shorten.setRemarks(remarks);
		shorten.setShortenReason(shortenReason);
		return shorten;
	}

}

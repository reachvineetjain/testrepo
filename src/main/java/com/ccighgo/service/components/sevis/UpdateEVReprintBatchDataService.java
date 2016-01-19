package com.ccighgo.service.components.sevis;

import static com.ccighgo.service.components.sevis.SevisUtils.generateBatchId;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Participant;
import com.ccighgo.jpa.repositories.ParticipantRepository;
import com.ccighgo.service.transport.sevis.CreateSEVISBatch;

import gov.ice.xmlschema.sevisbatch.exchangevisitor.ReprintType;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISBatchCreateUpdateEV;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor;

@Component
public class UpdateEVReprintBatchDataService implements IEVBatchDataService {

	@Autowired
	ParticipantRepository participantRepository;

	@Override
	public SEVISBatchCreateUpdateEV fetchBatchData(CreateSEVISBatch batchParam) {
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

		evs.forEach(ev -> ev.setReprint(createReprint(true, EVReprintReason.DAMAGED)));

		String batchId = generateBatchId("fName", "lName");
		SEVISBatchCreateUpdateEV batch = createUpdateEVBatch(batchParam.getUserId(), "P-1-12345", batchId);
		batch.getUpdateEV().getExchangeVisitor().addAll(evs);

		return batch;
	}

	private ExchangeVisitor intoEV(Participant p, String userId, String sevisId, String requestId) {
		// p -> EV
		return createExchangeVisitor(userId, sevisId, requestId);
	}

	private ReprintType createReprint(boolean printForm, EVReprintReason reason) {
		ReprintType reprint = new ReprintType();
		reprint.setPrintForm(printForm);
		reprint.setReason(reason.getCode());

		return reprint;
	}

}

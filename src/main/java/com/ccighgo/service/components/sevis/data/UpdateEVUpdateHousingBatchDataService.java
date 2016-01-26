package com.ccighgo.service.components.sevis.data;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Participant;
import com.ccighgo.jpa.repositories.ParticipantRepository;
import com.ccighgo.service.components.sevis.common.SevisUtils;
import com.ccighgo.service.transport.sevis.BatchParam;

import gov.ice.xmlschema.sevisbatch.common.USAddrDoctorType;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISBatchCreateUpdateEV;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor.Biographical;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor.Validate;

@Component
public class UpdateEVUpdateHousingBatchDataService implements IEVBatchDataService {

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

		evs.forEach(ev -> ev.setBiographical(createBiographical(true, createUSAddress("Address 1", "60169"))));
		
		String batchId = SevisUtils.createBatchId();
		SEVISBatchCreateUpdateEV batch = createUpdateEVBatch(batchParam.getUserId(), "P-1-12345", batchId);
		batch.getUpdateEV().getExchangeVisitor().addAll(evs);

		return batch;
	}

	private ExchangeVisitor intoEV(Participant p, String userId, String sevisId, String requestId) {
		// p -> EV
		return createExchangeVisitor(userId, sevisId, requestId);
	}
	
	private Biographical createBiographical(boolean printForm, USAddrDoctorType usAddress) {
		Biographical bio = new Biographical();
		bio.setPrintForm(printForm);
		
		bio.setUSAddress(usAddress);
		return bio;
	}
	
	/**
	 * 
	 * @param address1
	 * @param postalCode
	 * @return
	 */
	private USAddrDoctorType createUSAddress(String address1, String postalCode) {
		USAddrDoctorType usAddress = new USAddrDoctorType();
		usAddress.setAddress1(address1);
		usAddress.setPostalCode(postalCode);
		return usAddress;
	}

}

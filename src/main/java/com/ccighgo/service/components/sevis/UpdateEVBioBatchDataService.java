package com.ccighgo.service.components.sevis;

import static com.ccighgo.service.components.sevis.SevisUtils.generateBatchId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ccighgo.service.transport.sevis.CreateSEVISBatch;

import gov.ice.xmlschema.sevisbatch.alpha.common.NameNullableType;
import gov.ice.xmlschema.sevisbatch.alpha.table.BirthCntryCodeType;
import gov.ice.xmlschema.sevisbatch.alpha.table.CntryCodeWithoutType;
import gov.ice.xmlschema.sevisbatch.alpha.table.GenderCodeType;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISBatchCreateUpdateEV;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor.Biographical;

@Component
public class UpdateEVBioBatchDataService implements IEVBatchDataService {

	@Override
	public SEVISBatchCreateUpdateEV fetchBatchData(CreateSEVISBatch batchParam) {

		String batchId = generateBatchId("fName", "lName");
		SEVISBatchCreateUpdateEV batch = createUpdateEVBatch(batchParam.getUserId(), "P-1-12345", batchId);

		ExchangeVisitor ev = createExchangeVisitor(batchParam.getUserId(), "N0000000000", "1");
		ev.setBiographical(createBiographical());

		List<ExchangeVisitor> evs = new ArrayList<>();
		evs.add(ev);

		batch.getUpdateEV().getExchangeVisitor().addAll(evs);

		return batch;
	}

	private Biographical createBiographical() {
		Biographical bio = new Biographical();
		NameNullableType fullName = new NameNullableType();
		fullName.setFirstName("First Name");
		fullName.setLastName("Last Name");
		bio.setFullName(fullName);

		bio.setBirthDate(SevisUtils.convert(LocalDate.now()));
		bio.setGender(GenderCodeType.F);
		bio.setBirthCity("Birth City");
		bio.setBirthCountryCode(BirthCntryCodeType.AA);
		bio.setCitizenshipCountryCode(CntryCodeWithoutType.AA);
		bio.setPermanentResidenceCountryCode(CntryCodeWithoutType.AA);
		return bio;
	}

}

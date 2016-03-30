package com.ccighgo.service.components.sevis.data;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Participant;
import com.ccighgo.jpa.repositories.ParticipantRepository;
import com.ccighgo.service.components.sevis.common.SevisUtils;
import com.ccighgo.service.transport.sevis.BatchParam;

import gov.ice.xmlschema.sevisbatch.exchangevisitor.AddSOA;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISBatchCreateUpdateEV;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor.SiteOfActivity;

@Component
public class UpdateEVSoaAddBatchDataService implements IEVBatchDataService {

   @Autowired ParticipantRepository participantRepository;

   @Override
   public SEVISBatchCreateUpdateEV fetchBatchData(BatchParam batchParam) {
      // get EVs from DB
      // @formatter:off
      List<Integer> participantIds = batchParam.getParticipant().stream().map(p -> p.getParticipantGoId()).collect(Collectors.toList());
      // @formatter:on

      List<Participant> participants = participantRepository.findByParticipantGoIdIn(participantIds);

      // @formatter:off
      List<ExchangeVisitor> evs = participants.stream().map(p -> intoEV(p, batchParam.getUserId(), "N0000000000", "1")).collect(Collectors.toList());
      // @formatter:on

      evs.forEach(ev -> ev.setSiteOfActivity(createSoa(createAddSoa(false, "Address 1", "60169"))));

      String batchId = SevisUtils.createBatchId();
      SEVISBatchCreateUpdateEV batch = createUpdateEVBatch(batchParam.getUserId(), "P-1-12345", batchId);
      batch.getUpdateEV().getExchangeVisitor().addAll(evs);

      return batch;
   }

   private ExchangeVisitor intoEV(Participant p, String userId, String sevisId, String requestId) {
      // p -> EV
      return createExchangeVisitor(userId, sevisId, requestId);
   }

   private SiteOfActivity createSoa(AddSOA addSoa) {
      SiteOfActivity soa = new SiteOfActivity();

      soa.setAdd(addSoa);

      return soa;
   }

   private AddSOA createAddSoa(boolean printForm, String address1, String postalCode) {
      AddSOA addSoa = new AddSOA();
      addSoa.setPrintForm(printForm);
      addSoa.setAddress1(address1);
      addSoa.setPostalCode(postalCode);
      addSoa.setExplanation("Explanation");
      addSoa.setSiteName("Site Name");
      return addSoa;
   }
}

package com.ccighgo.service.components.sevis.data;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Participant;
import com.ccighgo.jpa.repositories.ParticipantRepository;
import com.ccighgo.service.components.sevis.common.SevisUtils;
import com.ccighgo.service.transport.sevis.BatchParam;

import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISBatchCreateUpdateEV;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor.Dependent;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor.Dependent.EndStatus;

@Component
public class UpdateEVDependentEndStatusBatchDataService implements IEVBatchDataService {

   @Autowired ParticipantRepository participantRepository;

   @Override
   public SEVISBatchCreateUpdateEV fetchBatchData(BatchParam batchParam) {

      // get EVs from DB
      // @formatter:off
      List<Integer> participantIds = batchParam.getParticipant().stream().map(p -> p.getParticipantGoId()).collect(Collectors.toList());
      // @formatter:on

      List<Participant> participants = participantRepository.findByParticipantGoIdIn(participantIds);

      List<ExchangeVisitor> evs = participants.stream().map(p -> intoEV(p, batchParam.getUserId(), "N0000000000", "1")).collect(Collectors.toList());

      // EVDependent End Status Reason. 02: Death, 04: Divorce, 09: Other.

      evs.forEach(ev -> ev.setDependent(createDependent(createEndStatus("N0123456789", "09"))));

      String batchId = SevisUtils.createBatchId();
      SEVISBatchCreateUpdateEV batch = createUpdateEVBatch(batchParam.getUserId(), "P-1-12345", batchId);
      batch.getUpdateEV().getExchangeVisitor().addAll(evs);

      return batch;
   }

   private ExchangeVisitor intoEV(Participant p, String userId, String sevisId, String requestId) {
      // p -> EV
      return createExchangeVisitor(userId, sevisId, requestId);
   }

   private Dependent createDependent(EndStatus endStatus) {
      Dependent dependent = new Dependent();
      dependent.setEndStatus(endStatus);
      return dependent;
   }

   private EndStatus createEndStatus(String dependentSevisId, String reason) {
      EndStatus end = new EndStatus();
      end.setDependentSevisID(dependentSevisId);
      end.setReason(reason);
      return end;
   }

}

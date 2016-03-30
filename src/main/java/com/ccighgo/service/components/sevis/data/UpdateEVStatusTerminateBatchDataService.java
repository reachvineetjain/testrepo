package com.ccighgo.service.components.sevis.data;

import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISBatchCreateUpdateEV;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor.Status;
import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor.Status.Terminate;
import gov.ice.xmlschema.sevisbatch.table.EVTerminationReasonType;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Participant;
import com.ccighgo.jpa.repositories.ParticipantRepository;
import com.ccighgo.service.components.sevis.common.SevisUtils;
import com.ccighgo.service.transport.sevis.BatchParam;

@Component
public class UpdateEVStatusTerminateBatchDataService implements IEVBatchDataService {

   @Autowired ParticipantRepository participantRepository;

   @Override
   public SEVISBatchCreateUpdateEV fetchBatchData(BatchParam batchParam) {

      // get EVs from DB
      // @formatter:off
      List<Integer> participantIds = batchParam.getParticipant().stream().map(p -> p.getParticipantGoId()).collect(Collectors.toList());
      // @formatter:on

      List<Participant> participants = participantRepository.findByParticipantGoIdIn(participantIds);

      List<ExchangeVisitor> evs = participants.stream().map(p -> intoEV(p, batchParam.getUserId(), "N0000000000", "1")).collect(Collectors.toList());

      evs.forEach(ev -> ev.setStatus(createTerminateStatus(EVTerminationReasonType.CONVIC, SevisUtils.convert(LocalDate.now()))));

      String batchId = SevisUtils.createBatchId();
      SEVISBatchCreateUpdateEV batch = createUpdateEVBatch(batchParam.getUserId(), "P-1-12345", batchId);
      batch.getUpdateEV().getExchangeVisitor().addAll(evs);

      return batch;
   }

   private ExchangeVisitor intoEV(Participant p, String userId, String sevisId, String requestId) {
      // p -> EV
      return createExchangeVisitor(userId, sevisId, requestId);
   }

   private Status createTerminateStatus(EVTerminationReasonType reason, XMLGregorianCalendar effectiveDate) {
      Status status = new Status();
      Terminate terminate = new Terminate();
      status.setTerminate(terminate);

      terminate.setReason(reason);
      terminate.setEffectiveDate(effectiveDate);

      return status;
   }

}

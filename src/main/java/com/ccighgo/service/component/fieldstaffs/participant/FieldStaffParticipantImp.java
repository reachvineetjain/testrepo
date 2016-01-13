package com.ccighgo.service.component.fieldstaffs.participant;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.exception.ErrorCode;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.FieldStaffMessageConstants;
import com.ccighgo.service.transport.fieldstaff.beans.fieldstaffparticipant.FieldStaffParticipant;
import com.ccighgo.service.transport.fieldstaff.beans.fieldstaffparticipant.FieldStaffParticipants;
import com.ccighgo.utils.CCIConstants;

@Component
public class FieldStaffParticipantImp implements FieldStaffParticipantInterface {

   private static final Logger LOGGER = Logger.getLogger(FieldStaffParticipantImp.class);

   @Autowired
   EntityManager em;

   @Autowired
   CommonComponentUtils componentUtils;

   @Autowired
   MessageUtils messageUtil;

   private static final String SP_FS_PARTICIPANT = "CALL SPFieldStaffMonitoringParticipantListing(?,?)";

   @Override
   public FieldStaffParticipants getAll(String goId) {
      FieldStaffParticipants fieldStaffParticipants = new FieldStaffParticipants();
      if (goId != null)
         try {
            Query query = em.createNativeQuery(SP_FS_PARTICIPANT);
            query.setParameter(1, Integer.valueOf(goId));
            query.setParameter(2, 1);

            @SuppressWarnings("unchecked")
            List<Object[]> result = query.getResultList();
            if (result != null) {
               for (Object[] obj : result) {
                  /*
                   * 1 CCIID 2 firstName 3 IastName 4 Partner 5 countryName 6
                   * Programs 7 Gender 8 ApprovedDate 9 LocalCordinator 10
                   * RegionalDirector 11 HostFamily
                   */
                  FieldStaffParticipant fsp = new FieldStaffParticipant();
                  fsp.setGoId(obj[0].toString());
                  fsp.setFirstName(obj[1].toString());
                  fsp.setLastName(obj[2].toString());
                  fsp.setPartner(obj[3].toString());
                  fsp.setCountry(obj[4].toString());
                  fsp.setProgram(obj[5].toString());
                  fsp.setGender(obj[6].toString());
                  fsp.setApprovedDate(obj[7].toString());
                  fsp.setLC(obj[8].toString());
                  fsp.setRD(obj[9].toString());
                  fsp.setHS(obj[10].toString());
                  fieldStaffParticipants.getParticipants().add(fsp);
               }
            }
            fieldStaffParticipants.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FIELDSTAFF_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } catch (Exception e) {
            e.printStackTrace();
            fieldStaffParticipants.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_PARTICIPANT.getValue(),
                  messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_PARTICIPANT)));
            LOGGER.error(messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_PARTICIPANT));
         }
      return fieldStaffParticipants;
   }

   @Override
   public FieldStaffParticipants getMyTeam(String goId) {
      FieldStaffParticipants fieldStaffParticipants = new FieldStaffParticipants();
      if (goId != null)
         try {
            Query query = em.createNativeQuery(SP_FS_PARTICIPANT);
            query.setParameter(1, Integer.valueOf(goId));
            query.setParameter(2, 0);

            @SuppressWarnings("unchecked")
            List<Object[]> result = query.getResultList();
            if (result != null) {
               for (Object[] obj : result) {
                  /*
                   * 1 CCIID 2 firstName 3 IastName 4 Partner 5 countryName 6
                   * Programs 7 Gender 8 ApprovedDate 9 LocalCordinator 10
                   * RegionalDirector 11 HostFamily
                   */
                  FieldStaffParticipant fsp = new FieldStaffParticipant();
                  fsp.setGoId(obj[0].toString());
                  fsp.setFirstName(obj[1].toString());
                  fsp.setLastName(obj[2].toString());
                  fsp.setPartner(obj[3].toString());
                  fsp.setCountry(obj[4].toString());
                  fsp.setProgram(obj[5].toString());
                  fsp.setGender(obj[6].toString());
                  fsp.setApprovedDate(obj[7].toString());
                  fsp.setLC(obj[8].toString());
                  fsp.setRD(obj[9].toString());
                  fsp.setHS(obj[10].toString());
                  fieldStaffParticipants.getParticipants().add(fsp);
               }
            }
            fieldStaffParticipants.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FIELDSTAFF_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } catch (Exception e) {
            e.printStackTrace();
            fieldStaffParticipants.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_PARTICIPANT.getValue(),
                  messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_PARTICIPANT)));
            LOGGER.error(messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_PARTICIPANT));
         }
      return fieldStaffParticipants;
   }

}

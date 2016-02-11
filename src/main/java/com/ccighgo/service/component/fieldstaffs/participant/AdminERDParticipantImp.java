package com.ccighgo.service.component.fieldstaffs.participant;

import java.util.Date;
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
import com.ccighgo.service.transport.fieldstaff.beans.adminerdparticipant.AdminERDParticipant;
import com.ccighgo.service.transport.fieldstaff.beans.adminerdparticipant.AdminERDParticipants;
import com.ccighgo.service.transport.fieldstaff.beans.placement.adminerdplacement.AdminERDPlacement;
import com.ccighgo.service.transport.fieldstaff.beans.placement.adminerdplacement.AdminERDPlacements;
import com.ccighgo.service.transport.partner.beans.fieldstaff.placement.adminerdmyplacement.AdminMyPlacement;
import com.ccighgo.service.transport.partner.beans.fieldstaff.placement.adminerdmyplacement.AdminMyPlacements;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;

@Component
public class AdminERDParticipantImp implements AdminERDParticipantInterface {

   private static final Logger LOGGER = Logger.getLogger(AdminERDParticipantImp.class);

   @Autowired
   EntityManager em;

   @Autowired
   CommonComponentUtils componentUtils;

   @Autowired
   MessageUtils messageUtil;

   private static final String SP_FS_PARTICIPANT = "CALL SPFieldStaffMonitoringParticipantListing(?,?,?)";
   private static final String SP_FS_PLACEMENT_LIST = "CALL SPFieldStaffParticipantListing(?,?,?)";
   private static final int ALL_PARTICIPANT_FLAG = 1;
   private static final int MY_TEAM_PARTICIPANT_FLAG = 0;
   private static final int MY_PLACEMENT_FLAG = 0;
   private static final int MY_TEAM_PLACEMENT_FLAG = 1;

   @Override
   public AdminERDParticipants getAll(String goId, String catagories) {
      LOGGER.info("goid: " + goId);
      AdminERDParticipants erdParticipants = new AdminERDParticipants();
      if (goId != null)
         try {
            Query query = em.createNativeQuery(SP_FS_PARTICIPANT);
            query.setParameter(1, Integer.valueOf(goId));
            query.setParameter(2, ALL_PARTICIPANT_FLAG);
            query.setParameter(3, catagories);
            @SuppressWarnings("unchecked")
            List<Object[]> result = query.getResultList();
            if (result != null) {
               for (Object[] obj : result) {
                  /*
                   * 1 CCIID 2 firstName 3 IastName 4 Partner 5 countryName 6
                   * Programs 7 Gender 8 ApprovedDate 9 LocalCordinator 10
                   * RegionalDirector 11 HostFamily
                   */
                  AdminERDParticipant esp = new AdminERDParticipant();
                  esp.setGoId(String.valueOf(obj[0]));
                  esp.setFirstName(String.valueOf(obj[1]));
                  esp.setLastName(String.valueOf(obj[2]));
                  esp.setPartner(String.valueOf(obj[3]));
                  esp.setCountry(String.valueOf(obj[4]));
                  esp.setProgram(String.valueOf(obj[5]));
                  esp.setGender(String.valueOf(obj[6]));
                  Date dateformat=(Date)obj[7];                 
                  esp.setApprovedDate(DateUtils.getMMddYyyyString(dateformat));
                  esp.setLC(String.valueOf(obj[8]));
                  esp.setRD(String.valueOf(obj[9]));
                  esp.setHS(String.valueOf(obj[10]));
                  esp.setPicUrl(String.valueOf(obj[11]));
                  erdParticipants.getParticipants().add(esp);
               }
            } else {
               erdParticipants.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                     messageUtil.getMessage(CCIConstants.NO_RECORD)));
            }
            erdParticipants.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FIELDSTAFF_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } catch (Exception e) {
            e.printStackTrace();
            erdParticipants.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_PARTICIPANT.getValue(),
                  messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_PARTICIPANT)));
            LOGGER.error(messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_PARTICIPANT));
         }
      return erdParticipants;
   }

   @Override
   public AdminERDParticipants getMyTeam(String goId, String catagories) {
      LOGGER.info("goid: " + goId);
      AdminERDParticipants erdParticipants = new AdminERDParticipants();
      if (goId != null)
         try {
            Query query = em.createNativeQuery(SP_FS_PARTICIPANT);
            query.setParameter(1, Integer.valueOf(goId));
            query.setParameter(2, MY_TEAM_PARTICIPANT_FLAG);
            query.setParameter(3, catagories);
            @SuppressWarnings("unchecked")
            List<Object[]> result = query.getResultList();
            if (result == null) {
               erdParticipants.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                     messageUtil.getMessage(CCIConstants.NO_RECORD)));
            } else {
               for (Object[] obj : result) {
                  /*
                   * 1 CCIID 2 firstName 3 IastName 4 Partner 5 countryName 6
                   * Programs 7 Gender 8 ApprovedDate 9 LocalCordinator 10
                   * RegionalDirector 11 HostFamily
                   */
                  AdminERDParticipant fsp = new AdminERDParticipant();
                  fsp.setGoId(obj[0].toString());
                  fsp.setGoId(String.valueOf(obj[0]));
                  fsp.setFirstName(String.valueOf(obj[1]));
                  fsp.setLastName(String.valueOf(obj[2]));
                  fsp.setPartner(String.valueOf(obj[3]));
                  fsp.setCountry(String.valueOf(obj[4]));
                  fsp.setProgram(String.valueOf(obj[5]));
                  fsp.setGender(String.valueOf(obj[6]));
                  Date dateformat=(Date)obj[7];                 
                  fsp.setApprovedDate(DateUtils.getMMddYyyyString(dateformat));                 
                  fsp.setLC(String.valueOf(obj[8]));
                  fsp.setRD(String.valueOf(obj[9]));
                  fsp.setHS(String.valueOf(obj[10]));
                  fsp.setPicUrl(String.valueOf(obj[11]));
                  erdParticipants.getParticipants().add(fsp);
               }
            }
            erdParticipants.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FIELDSTAFF_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } catch (Exception e) {
            e.printStackTrace();
            erdParticipants.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_PARTICIPANT.getValue(),
                  messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_PARTICIPANT)));
            LOGGER.error(messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_PARTICIPANT));
         }
      return erdParticipants;
   }

   /*
    * Placement Tab
    */
   @Override
   public AdminMyPlacements getERDMyPlacement(String goId, String catagories) {
      AdminMyPlacements myPlacements = new AdminMyPlacements();
      try {
         Query query = em.createNativeQuery(SP_FS_PLACEMENT_LIST);
         query.setParameter(1, goId);
         query.setParameter(2, MY_PLACEMENT_FLAG);
         query.setParameter(3, catagories);
         @SuppressWarnings("unchecked")
         List<Object[]> result = query.getResultList();
         if (result == null) {
            myPlacements.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
            return myPlacements;
         }
         for (Object[] obj : result) {
            /*
             * 1 participantGold 2 firstName 3 IastName 4 Partner 5 countryName
             * 6 SeasonName 7 Programs 8 participantStatusName 9 holdRequested
             * 10 photo 11 countryFlag
             */
            AdminMyPlacement placement = new AdminMyPlacement();
            placement.setGoId(String.valueOf(obj[0]));
            placement.setFirstName(String.valueOf(obj[1]));
            placement.setLastName(String.valueOf(obj[2]));
            placement.setPartner(String.valueOf(obj[3]));
            placement.setCountry(String.valueOf(obj[4]));
            placement.setSeason(String.valueOf(obj[5]));
            placement.setProgram(String.valueOf(obj[6]));
            placement.setStatus(String.valueOf(obj[7]));
            placement.setHolds(String.valueOf(obj[8]));
            placement.setPicUrl(String.valueOf(obj[9]));
            placement.setFlagUrl(String.valueOf(obj[9]));
            myPlacements.getTypes().add(placement);
         }
         myPlacements.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FIELDSTAFF_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         e.printStackTrace();
         myPlacements.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_PLACEMENT.getValue(),
               messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_PLACEMENT)));
         LOGGER.error(messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_PLACEMENT));
      }
      return myPlacements;
   }

   @Override
   public AdminERDPlacements getERDPlacementMyTeamPlacement(String goId, String catagories) {

      AdminERDPlacements eRDPlacements = new AdminERDPlacements();
      try {
         Query query = em.createNativeQuery(SP_FS_PLACEMENT_LIST);
         query.setParameter(1, goId);
         query.setParameter(2, MY_TEAM_PLACEMENT_FLAG);
         query.setParameter(3, catagories);
         @SuppressWarnings("unchecked")
         List<Object[]> result = query.getResultList();
         if (result == null) {
            eRDPlacements.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
            return eRDPlacements;
         }
         for (Object[] obj : result) {
            /*
             * 1 CCIID 2 fieldStaffFirstName 3 fieldStaffLastName 4 paxFirstName
             * 5 paxLastName 6 Partner 7 countryName 8 SeasonName 9 Programs 10
             * Status 11 Holds 12 paxPhoto
             */
            AdminERDPlacement placement = new AdminERDPlacement();
            placement.setGoId(String.valueOf(obj[0]));
            placement.setFieldstaffFirstName(String.valueOf(obj[1]));
            placement.setFieldstaffLastName(String.valueOf(obj[2]));
            placement.setFirstName(String.valueOf(obj[3]));
            placement.setLastName(String.valueOf(obj[4]));
            placement.setPartner(String.valueOf(obj[5]));
            placement.setCountry(String.valueOf(obj[6]));
            placement.setSeason(String.valueOf(obj[7]));
            placement.setProgram(String.valueOf(obj[8]));
            placement.setStatus(String.valueOf(obj[9]));
            placement.setHolds(String.valueOf(obj[10]));
            placement.setPaxPhoto(String.valueOf(obj[11]));
            eRDPlacements.getPlacements().add(placement);
         }
         eRDPlacements.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FIELDSTAFF_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         e.printStackTrace();
         eRDPlacements.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_PLACEMENT.getValue(),
               messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_PLACEMENT)));
         LOGGER.error(messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_PLACEMENT));

      }
      return eRDPlacements;
   }

}

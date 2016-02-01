package com.ccighgo.service.components.hf.participant.application.season;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.HostFamilyMessageConstants;
import com.ccighgo.service.transport.hostfamily.beans.seasons.list.HFParticipantSeason;
import com.ccighgo.service.transport.hostfamily.beans.seasons.list.HFParticipantSeasonList;
import com.ccighgo.utils.CCIConstants;

/**
 * The Implementation class.It implements the methods used to fetch the list of
 * seasons for given host family.
 * 
 * @author vijay
 *
 */
@Component
public class HfParticipantApplicationSeasonsImpl implements HfParticipantApplicationSeasonsInterface {

   private static final Logger LOGGER = Logger.getLogger(HfParticipantApplicationSeasonsImpl.class);

   private static final String SP_HF_SEASON_LIST = "CALL SPHostFamilySeasonList(?)";
   private static final String SPACE = " ";

   @Autowired MessageUtils messageUtil;

   @Autowired CommonComponentUtils componentUtils;

   @Autowired EntityManager entityManager;

   @Override
   public HFParticipantSeasonList getHFParticipantSeasonList(String goId) {

      LOGGER.info("HfParticipantApplicationSeasonsImpl#getHFParticipantSeasonList- goId " + goId);

      HFParticipantSeasonList hfParticipantSeasonList = new HFParticipantSeasonList();

      try {
         if (goId == null || Integer.valueOf(goId) == 0) {
            throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_HF_SEASONID));
         }
         int count = 0;
         // call the stored procedure
         Query query = entityManager.createNativeQuery(SP_HF_SEASON_LIST);
         query.setParameter(1, Integer.valueOf(goId));

         @SuppressWarnings("unchecked")
         List<Object[]> results = query.getResultList();

         /*
          * The list of the fields in the result set fetched by call to SP.
          * 0.seasonName ,1.programSeasonStatus 2.RDFirstName 3.RDLastName
          * 4.RMFirstName,5.RMLastName,6.ACFirstName,
          * 7.ACLastName,8.LCFirstName,9.LCLastName,10.HFSeasonStatus
          */
         if (results != null && results.size() > 0) {
            for (Object[] obj : results) {
               count += 1;
               HFParticipantSeason season = new HFParticipantSeason();
               season.setGoId(Integer.valueOf(goId));
               season.setSeasonName(obj[0] != null ? obj[0].toString() : SPACE);
               season.setProgramSeasonStatus(obj[1] != null ? obj[1].toString() : SPACE);
               season.setRdFirstName(obj[2] != null ? obj[2].toString() : SPACE);
               season.setRdLastName(obj[3] != null ? obj[3].toString() : SPACE);
               season.setRmFirstName(obj[4] != null ? obj[4].toString() : SPACE);
               season.setRmLastName(obj[5] != null ? obj[5].toString() : SPACE);
               season.setAcFirstName(obj[6] != null ? obj[6].toString() : SPACE);
               season.setAcLastName(obj[7] != null ? obj[7].toString() : SPACE);
               season.setLcFirstName(obj[8] != null ? obj[8].toString() : SPACE);
               season.setLcLastName(obj[9] != null ? obj[9].toString() : SPACE);
               season.setHfSeasonStatus(obj[10] != null ? obj[10].toString() : SPACE);
               hfParticipantSeasonList.getHfParticipantSeasons().add(season);

            }
         }
         hfParticipantSeasonList.setCount(count);
         // set the status
         hfParticipantSeasonList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.HF_SEASONS_SERVICE_SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (Exception e) {
         // set the status
         hfParticipantSeasonList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_HF_SEASON.getValue(),
               messageUtil.getMessage(HostFamilyMessageConstants.ERROR_IN_GET_HF_SEASON)));
         // log the error message
         LOGGER.error(e.getMessage());

      }
      return hfParticipantSeasonList;
   }
}

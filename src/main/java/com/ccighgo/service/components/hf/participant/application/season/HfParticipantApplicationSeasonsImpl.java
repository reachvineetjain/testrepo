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

   private static final String SP_HF_SEASON_LIST = "CALL SPAdminHostFamilyAddedSeason(?)";
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
          * The list of the fields in the result set. 0.seasonName ,
          * 1.programSeasonStatus, 2.RD Name 3.RM Name, 4.AC Name, 5.LC Name,
          * 6.HF season status,
          */
         if (results != null && results.size() > 0) {
            for (Object[] obj : results) {
               count += 1;
               HFParticipantSeason season = new HFParticipantSeason();
               season.setGoId(Integer.valueOf(goId));
               season.setSeasonName(obj[0] != null ? obj[0].toString() : SPACE);
               season.setProgramSeasonStatus(obj[1] != null ? obj[1].toString() : SPACE);
               season.setLcName(obj[2] != null ? obj[2].toString() : SPACE);
               season.setRdName(obj[3] != null ? obj[3].toString() : SPACE);
               season.setHfSeasonStatus(obj[4] != null ? obj[4].toString() : SPACE);
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

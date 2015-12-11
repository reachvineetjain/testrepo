/**
 * 
 */
package com.ccighgo.service.components.fieldstaff.season;

import java.util.ArrayList;
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
import com.ccighgo.service.components.errormessages.constants.PartnerSeasonMessageConstants;
import com.ccighgo.service.transport.fieldstaff.beans.seasons.FieldStaffSeason;
import com.ccighgo.service.transport.fieldstaff.beans.seasons.FieldStaffSeasons;
import com.ccighgo.service.transport.partner.beans.partner.season.application.PartnerSeasonApplication;
import com.ccighgo.utils.CCIConstants;

/**
 * @author ravi
 *
 */
@Component
public class FieldStaffSeasonServiceImpl implements FieldStaffSeasonService {

   private static final Logger LOGGER = Logger.getLogger(FieldStaffSeasonServiceImpl.class);

   @Autowired EntityManager entityManager;

   @Autowired MessageUtils messageUtil;
   @Autowired CommonComponentUtils componentUtils;

   private static final String SP_FS_SEASON_LIST = "CALL SPFieldStaffSeasonsList(?)";
   private static final String SP_PARTNER_SEASON_APPLICATION_LIST = "call SPPartnerSeasonAplication(?)";

   @Override
   public FieldStaffSeasons getFieldStaffSeasons(Integer fsGoId) {
      FieldStaffSeasons fsSeasonList = new FieldStaffSeasons();
      try {
         if (fsGoId == null || fsGoId == 0) {
            throw new CcighgoException("invalid field staff id");
         }
         int count = 0;
         Query query = entityManager.createNativeQuery(SP_FS_SEASON_LIST);
         query.setParameter(1, fsGoId);
         List<Object[]> results = query.getResultList();
         if (results != null && results.size() > 0) {
            for (Object[] obj : results) {
               count += 1;
               FieldStaffSeason season = new FieldStaffSeason();
               fsSeasonList.getFieldStaffSeasons().add(season);
            }
         }
         fsSeasonList.setCount(count);
         fsSeasonList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         fsSeasonList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(), e.getMessage()));
      }
      return fsSeasonList;
   }

}

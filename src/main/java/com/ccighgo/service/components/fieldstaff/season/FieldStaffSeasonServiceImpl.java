/**
 * 
 */
package com.ccighgo.service.components.fieldstaff.season;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.FieldStaffLeadershipSeason;
import com.ccighgo.db.entities.FieldStaffLeadershipSeasonDetail;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.FieldStaffLeadershipSeasonDetailRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.fieldstaff.beans.seasons.FieldStaffSeason;
import com.ccighgo.service.transport.fieldstaff.beans.seasons.FieldStaffSeasons;
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
   @Autowired FieldStaffLeadershipSeasonDetailRepository fieldStaffLeadershipSeasonDetailRepository;

   private static final String SP_FS_SEASON_LIST = "CALL SPFieldStaffSeasonsList(?)";
   private static final String SP_PARTNER_SEASON_APPLICATION_LIST = "call SPPartnerSeasonAplication(?)";

   @Override
   @Transactional(readOnly = true)
   public FieldStaffSeasons getFieldStaffSeasons(String fsGoId) {
      FieldStaffSeasons fsSeasonList = new FieldStaffSeasons();
      try {
         if (fsGoId == null || Integer.valueOf(fsGoId) == 0) {
            throw new CcighgoException("invalid field staff id");
         }
         int count = 0;
         Query query = entityManager.createNativeQuery(SP_FS_SEASON_LIST);
         query.setParameter(1, Integer.valueOf(fsGoId));
         List<Object[]> results = query.getResultList();
         if (results != null && results.size() > 0) {
            for (Object[] obj : results) {
               count += 1;
               // 0.fs season id, 1.seasonid,2 department program id, 3.
               // season/program name,4. start date, 5.end date, 6.status, 7. is
               // signed, 8.departmentid
               FieldStaffSeason season = new FieldStaffSeason();
               season.setFsGoId(Integer.valueOf(fsGoId));
               season.setFsSeasonId(Integer.valueOf(obj[0].toString()));
               season.setSeasonId(Integer.valueOf(obj[1].toString()));
               season.setDepartmentProgramId(Integer.valueOf(obj[2].toString()));
               season.setProgramName(obj[3].toString());
               season.setStartDate(obj[4].toString());
               season.setEndDate(obj[5].toString());
               season.setStatus(obj[6].toString());
               season.setSignedContract(obj[7].toString().equals("true") ? 1 : 0);
               season.setDepartmentId(Integer.valueOf(obj[8].toString()));
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

   @Override
   @Transactional
   public Response updateSignedContract(String fslSeasonId, String seasonId, String deparmentProgramId, String statusVal) {
      Response resp = new Response();
      try {
         if (fslSeasonId == null || Integer.valueOf(fslSeasonId) == 0 || Integer.valueOf(fslSeasonId) < 0) {
            throw new CcighgoException("invalid field staff season id");
         }
         if (seasonId == null || Integer.valueOf(seasonId) == 0 || Integer.valueOf(seasonId) < 0) {
            throw new CcighgoException("invalid season id");
         }
         if (deparmentProgramId == null || Integer.valueOf(deparmentProgramId) == 0 || Integer.valueOf(deparmentProgramId) < 0) {
            throw new CcighgoException("invalid department program id");
         }
         if (statusVal == null) {
            throw new CcighgoException("invalid value");
         }
         FieldStaffLeadershipSeasonDetail fieldStaffLeadershipSeasonDetail = fieldStaffLeadershipSeasonDetailRepository.getFslSeasonDetailByIdSeasonIdAndDeptPrgId(
               Integer.valueOf(fslSeasonId), Integer.valueOf(seasonId), Integer.valueOf(deparmentProgramId));
         if (fieldStaffLeadershipSeasonDetail == null) {
            throw new CcighgoException("no record found");
         }
         fieldStaffLeadershipSeasonDetail.setAgreeToTerms(Integer.valueOf(seasonId) == 1 ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         fieldStaffLeadershipSeasonDetailRepository.saveAndFlush(fieldStaffLeadershipSeasonDetail);
         resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(), e.getMessage()));
      }
      return resp;
   }
}

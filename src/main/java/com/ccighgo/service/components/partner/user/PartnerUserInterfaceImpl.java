/**
 * 
 */
package com.ccighgo.service.components.partner.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.PartnerSeason;
import com.ccighgo.db.entities.PartnerUser;
import com.ccighgo.db.entities.Season;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.GenderRepository;
import com.ccighgo.jpa.repositories.GoIdSequenceRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.LoginUserTypeRepository;
import com.ccighgo.jpa.repositories.PartnerPermissionRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerUserRepository;
import com.ccighgo.jpa.repositories.SalutationRepository;
import com.ccighgo.jpa.repositories.UserTypeRepository;
import com.ccighgo.service.component.emailing.EmailServiceImpl;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.PartnerAdminSeasonConstants;
import com.ccighgo.service.components.errormessages.constants.RegionManagementMessageConstants;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.partner.user.details.PartnerUserDetails;
import com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUsers;
import com.ccighgo.utils.CCIConstants;

/**
 * @author ravi
 *
 */
@Component
public class PartnerUserInterfaceImpl implements PartnerUserInterface {

   private static final Logger LOGGER = Logger.getLogger(PartnerUserInterfaceImpl.class);

   @Autowired PartnerUserRepository partnerUserRepository;
   
   @Autowired CommonComponentUtils componentUtils;
   
   @Autowired MessageUtils messageUtil;
   
   @Autowired GoIdSequenceRepository goIdSequenceRepository;
   
   @Autowired LoginRepository loginRepository;
   
   @Autowired LoginUserTypeRepository loginUserTypeRepository;
   
   @Autowired PartnerRepository partnerRepository;
   
   @Autowired UserTypeRepository userTypeRepository;
   
   @Autowired PartnerPermissionRepository partnerPermissionRepository;
   
   @Autowired GenderRepository genderRepository;
   
   @Autowired SalutationRepository salutationRepositotry;
   
   @Autowired EmailServiceImpl email;

   @Override
   @Transactional(readOnly = true)
   public PartnerUsers getAllPartnerUsers(String partnerId) {
      PartnerUsers partnerUsers = new PartnerUsers();
      if (partnerId == null || Integer.valueOf(partnerId) < 0 || Integer.valueOf(partnerId) == 0) {
         partnerUsers.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_SEASON_ID.getValue(),
               messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID)));
      } else {
         try {
            List<PartnerUser> partnerUsersDBList = partnerUserRepository.findByPartnerGoId(Integer.valueOf(partnerId));
            if (partnerUsersDBList != null) {
               partnerUsers.setPartnerUserCount(partnerUsersDBList.size());
               List<com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUser> partnerUsersUIList = new ArrayList<com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUser>();
               for (PartnerUser user : partnerUsersDBList) {
                  com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUser puser = new com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUser();
                  puser.setPartnerUserId(user.getPartnerUserId());
                  puser.setPartnerUserFirstName(user.getFirstName());
                  puser.setPartnerUserLastName(user.getLastName());
                  puser.setPartnerUserLoginName(user.getLogin().getLoginName());
                  puser.setPartnerUserEmail(user.getEmail());
                  puser.setPartnerUserStatus(user.getActive() == CCIConstants.ACTIVE ? 1 : 0);
                  partnerUsersUIList.add(puser);
               }
               partnerUsers.getPartnerUsers().addAll(partnerUsersUIList);
               partnerUsers.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            } else {
               partnerUsers.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                     messageUtil.getMessage(CCIConstants.NO_RECORD)));
               LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
            }
         } catch (CcighgoException e) {
            partnerUsers.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_SUP_REG_LIST.getValue(),
                  messageUtil.getMessage(RegionManagementMessageConstants.ERROR_GET_SUP_REG_LIST)));
            LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.ERROR_GET_SUP_REG_LIST));
         }
      }
      return partnerUsers;
   }

   @Override
   @Transactional
   public Response updatePartnerUserStatus(String statusVal, String partnerUserId) {
      Response response = new Response();
      if (partnerUserId == null) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               messageUtil.getMessage(PartnerAdminSeasonConstants.INVALID_PARTNER_ADMIN_SEASON_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.INVALID_PARTNER_ADMIN_SEASON_ID));
         return response;
      }
      if (statusVal == null || !(Integer.valueOf(statusVal)==0 ||Integer.valueOf(statusVal)==1)) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               messageUtil.getMessage(PartnerAdminSeasonConstants.PARTNER_ADMIN_SEASON_INVALID_STATUS_VALUE)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.PARTNER_ADMIN_SEASON_INVALID_STATUS_VALUE));
         return response;
      }else{
         try{
            PartnerUser partnerUser = partnerUserRepository.findOne(Integer.valueOf(partnerUserId));
            if(Integer.valueOf(statusVal)==1){
               partnerUser.setActive(CCIConstants.ACTIVE);
            }
            if(Integer.valueOf(statusVal)==0){
               partnerUser.setActive(CCIConstants.INACTIVE);
            }
            partnerUserRepository.saveAndFlush(partnerUser);
            response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }catch (CcighgoException e) {
            response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
                  messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_STATUS)));
            LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_STATUS));
         }
      }
      return response;
   }

   @Override
   public PartnerUserDetails getPartnerUserDetails(String partnerUserId) {
      PartnerUserDetails partnerUserDetails = new PartnerUserDetails();
      
      
      return partnerUserDetails;
   }
  
}

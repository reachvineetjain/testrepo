package com.ccighgo.utils.reuse.function;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.CCIStaffUser;
import com.ccighgo.db.entities.CCIStaffUsersCCIStaffRole;
import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LoginUserType;
import com.ccighgo.db.entities.PartnerUser;
import com.ccighgo.jpa.repositories.CCIStaffUsersRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.PartnerUserRepository;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.ExceptionUtil;
import com.ccighgo.utils.reuse.function.pojo.UserInformationOfCreatedBy;

@Component
public class ReusedFunctionImpl implements ReusedFunctions {

   @Autowired LoginRepository loginRepository;
   @Autowired CCIStaffUsersRepository cciStaffUsersRepository;
   @Autowired PartnerUserRepository partnerUserRepository;
   private static final Logger LOGGER = Logger.getLogger(ReusedFunctionImpl.class);

   @Override
   public UserInformationOfCreatedBy getPartnerCreatedByInformation(Integer createdBy) {
      UserInformationOfCreatedBy userInformationOfCreatedBy = null;
      try {
         Login login = loginRepository.findOne(createdBy);
         if (login != null) {
            userInformationOfCreatedBy = new UserInformationOfCreatedBy();
            // userInformationOfCreatedBy.setUserName(login.getLoginName());
            Integer goId = login.getGoIdSequence().getGoId();
            for (LoginUserType loginUsrType : login.getLoginUserTypes()) {
               if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.CCI_USR)) {
                  CCIStaffUser cciStaffUsers = cciStaffUsersRepository.findOne(goId);
                  userInformationOfCreatedBy.setUserName(cciStaffUsers.getFirstName() + " " + cciStaffUsers.getLastName());
                  userInformationOfCreatedBy.setUserId(goId);
                  userInformationOfCreatedBy.setPhotoUrl(cciStaffUsers.getPhoto());
                  if (cciStaffUsers.getCcistaffUsersCcistaffRoles() != null && !cciStaffUsers.getCcistaffUsersCcistaffRoles().isEmpty()) {
                     CCIStaffUsersCCIStaffRole cciStaffUsersCCIStaffRole = cciStaffUsers.getCcistaffUsersCcistaffRoles().get(0);
                     if (cciStaffUsersCCIStaffRole.getCcistaffRole() != null)
                        userInformationOfCreatedBy.setRole(cciStaffUsersCCIStaffRole.getCcistaffRole().getCciStaffRoleName());
                  }
               } else if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.PARTNER_USER)) {
                  PartnerUser partnerUser = partnerUserRepository.findByPartnerGoIdAndLoginId(createdBy, goId);
                  userInformationOfCreatedBy.setUserName(partnerUser.getFirstName() + " " + partnerUser.getLastName());
                  userInformationOfCreatedBy.setUserId(goId);
                  userInformationOfCreatedBy.setPhotoUrl(partnerUser.getPhoto());
                  userInformationOfCreatedBy.setRole(partnerUser.getTitle());
               } else if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.PARTNER_AGENT)) {
               } else if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.FIELD_STAFF_USER)) {
               } else if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.HOST_FAMILY_USER)) {
               } else if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.EMPLOYEE_USER)) {
               } else if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.PARTICPANT_USER)) {
               }
            }
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);

      }
      return userInformationOfCreatedBy;
   }

}

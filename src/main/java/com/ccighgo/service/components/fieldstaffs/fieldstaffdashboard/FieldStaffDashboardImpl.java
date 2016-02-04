package com.ccighgo.service.components.fieldstaffs.fieldstaffdashboard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.FieldStaff;
import com.ccighgo.db.entities.FieldStaffWorkQueueCategory;
import com.ccighgo.db.entities.FieldStaffWorkQueueCategoryAggregate;
import com.ccighgo.db.entities.FieldStaffWorkQueueType;
import com.ccighgo.db.entities.FieldStaffWorkQueueTypeAggregate;
import com.ccighgo.db.entities.Login;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.FieldStaffRepository;
import com.ccighgo.jpa.repositories.FieldStaffWorkQueueCategoriesRepository;
import com.ccighgo.jpa.repositories.FieldStaffWorkQueueCategoryAggregateRepository;
import com.ccighgo.jpa.repositories.FieldStaffWorkQueueTypeAggregateRepository;
import com.ccighgo.jpa.repositories.FieldStaffWorkQueueTypeRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.FieldStaffMessageConstants;
import com.ccighgo.service.transport.beans.fieldstaffdashboard.erdaccount.ERDPersonalInfo;
import com.ccighgo.service.transport.beans.fieldstaffdashboard.erdaccount.ErdMyAccount;
import com.ccighgo.service.transport.beans.fieldstaffdashboard.erddashboard.ErdDashboard;
import com.ccighgo.service.transport.beans.fieldstaffdashboard.erddashboard.ErdDashboardAccount;
import com.ccighgo.service.transport.beans.fieldstaffdashboard.erddashboard.ErdDashboardCategorieDetails;
import com.ccighgo.service.transport.beans.fieldstaffdashboard.erddashboard.ErdDashboardType;
import com.ccighgo.service.transport.beans.fieldstaffdashboard.erddashboard.ErdDashboardTypes;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.utils.CCIConstants;

@Component
public class FieldStaffDashboardImpl implements FieldStaffDashboardInterface {

   private static final Logger LOGGER = Logger.getLogger(FieldStaffDashboardInterface.class);
   @Autowired
   FieldStaffWorkQueueTypeRepository fieldStaffWorkQueueTypeRepository;
   @Autowired
   FieldStaffWorkQueueTypeAggregateRepository fieldStaffWorkQueueTypeAggregateRepository;
   @Autowired
   FieldStaffWorkQueueCategoryAggregateRepository fieldStaffWorkQueueCategoryAggregateRepository;
   @Autowired
   FieldStaffWorkQueueCategoriesRepository fieldStaffWorkQueueCategoriesRepository;
   @Autowired
   FieldStaffRepository fieldStaffRepository;
   @Autowired
   LoginRepository loginRepository;
   @Autowired
   CommonComponentUtils componentUtils;
   @Autowired
   MessageUtils messageUtil;

   @Override
   public ErdDashboard getErdDashboardWorkQueues(String fieldStaffGoId) {

      ErdDashboard erdDashboard = new ErdDashboard();
      ErdDashboardTypes erdDashboardTypes = new ErdDashboardTypes();
      try {
         List<FieldStaffWorkQueueType> fieldStaffWorkQueueTypes = fieldStaffWorkQueueTypeRepository.findAll();
         if (fieldStaffWorkQueueTypes != null)
            for (FieldStaffWorkQueueType type : fieldStaffWorkQueueTypes) {
               ErdDashboardType t = new ErdDashboardType();
               t.setTypeId(type.getFieldStaffWQTypeId());
               t.setType(type.getFieldStaffWQTypeName());
               FieldStaffWorkQueueTypeAggregate aggregate = fieldStaffWorkQueueTypeAggregateRepository.getTypeAggregate(Integer.valueOf(fieldStaffGoId), type
                     .getLookupDepartmentProgram().getLookupDepartmentProgramId(), type.getFieldStaffWQTypeId());
               t.setNumber(aggregate.getFieldStaffWQTypeAggregate().toString());
               List<FieldStaffWorkQueueCategory> FieldStaffWorkQueueCategories = fieldStaffWorkQueueCategoriesRepository.findAllCategoriesByTypeId(type.getFieldStaffWQTypeId());
               for (FieldStaffWorkQueueCategory catagory : FieldStaffWorkQueueCategories) {
                  ErdDashboardCategorieDetails details = new ErdDashboardCategorieDetails();
                  details.setCategorieId(catagory.getFieldStaffWQCategoryId());
                  details.setCategorie(catagory.getFieldStaffWQCategoryName());
                  FieldStaffWorkQueueCategoryAggregate catAggregate = fieldStaffWorkQueueCategoryAggregateRepository.getCategoryAggregate(Integer.valueOf(fieldStaffGoId), catagory
                        .getFieldStaffWorkQueueType().getLookupDepartmentProgram().getLookupDepartmentProgramId(), catagory.getFieldStaffWQCategoryId());
                  details.setNumber(catAggregate.getFieldStaffWQCategoryAggregate().toString());
                  t.getCategories().add(details);
               }
               erdDashboardTypes.getTypes().add(t);
            }
         erdDashboard.setErdDashboardTypes(erdDashboardTypes);
         erdDashboard.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FIELDSTAFF_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         e.printStackTrace();
         erdDashboard.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_DASHBOARD.getValue(),
               messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_DASHBOARD)));
         LOGGER.error(messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_DASHBOARD));
      }
      return erdDashboard;
   }

   @Override
   public ErdMyAccount getMyAccountDetail(String fsGoId) {
      ErdMyAccount erdMyAccount = new ErdMyAccount();
      try {
         ERDPersonalInfo pi = new ERDPersonalInfo();
         FieldStaff fs = fieldStaffRepository.findOne(Integer.valueOf(fsGoId));
         Login login = loginRepository.findByGoId(fs.getGoIdSequence());
         pi.setFirstName(fs.getFirstName());
         pi.setLastName(fs.getLastName());
         pi.setHomePhone(fs.getPhone());
         pi.setWorkPhone(fs.getWorkPhone());
         pi.setCellPhone(fs.getCellPhone());
         pi.setEmail(login.getEmail());
         pi.setUserName(login.getLoginName());
         pi.setReseiveEmail(fs.getReceiveEmails() == CCIConstants.ACTIVE);
         pi.setLinkDOSCertificate("");// TODO ask phani!!
         pi.setPhysicalAddress1(fs.getCurrentAddress1());
         pi.setPhysicalAddress2(fs.getCurrentAddress2());
         pi.setPhysicalCity(fs.getCurrentCity());
         pi.setPhysicalSate(fs.getLookupUsstate2().getStateName());
         pi.setPhysicalZip(fs.getCurrentZipCode());
         pi.setMallingAddress1(fs.getMailingAddress1());
         pi.setMallingAddress2(fs.getMailingAddress2());
         pi.setMallingCity(fs.getMailingCity());
         pi.setMallingZip(fs.getMailingZipCode());
         pi.setMallingState(fs.getLookupUsstate1().getStateName()); 
         erdMyAccount.setPersonalInfo(pi);
         erdMyAccount.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FIELDSTAFF_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         erdMyAccount.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_INFO, ErrorCode.FIELDSTAFF_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         LOGGER.error(CCIConstants.SERVICE_FAILURE);
      }
      return erdMyAccount;
   }
}

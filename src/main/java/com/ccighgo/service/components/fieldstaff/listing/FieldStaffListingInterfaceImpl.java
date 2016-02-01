/**
 * 
 */
package com.ccighgo.service.components.fieldstaff.listing;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.FieldStaff;
import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.SeasonGeographyConfiguration;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.FieldStaffLeadershipSeasonRepository;
import com.ccighgo.jpa.repositories.FieldStaffRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.SeasonGeographyConfigurationRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.FieldStaffMessageConstants;
import com.ccighgo.service.transport.fieldstaff.beans.lclist.Contact;
import com.ccighgo.service.transport.fieldstaff.beans.lclist.FieldStaffLC;
import com.ccighgo.service.transport.fieldstaff.beans.lclist.FieldStaffLCList;
import com.ccighgo.service.transport.fieldstaff.beans.lclist.LCSeasonContact;
import com.ccighgo.service.transport.fieldstaff.beans.rmlist.FieldStaffRM;
import com.ccighgo.service.transport.fieldstaff.beans.rmlist.FieldStaffRMList;
import com.ccighgo.service.transport.fieldstaff.beans.rmlist.RMSeasonContact;
import com.ccighgo.utils.CCIConstants;

/**
 * @author ravi
 *
 */
@Component
public class FieldStaffListingInterfaceImpl implements FieldStaffListingInterface {

   private static final Logger LOGGER = Logger.getLogger(FieldStaffListingInterface.class);
   @Autowired
   EntityManager em;
   @Autowired
   MessageUtils messageUtil;
   @Autowired
   CommonComponentUtils componentUtils;
   @Autowired FieldStaffRepository fieldStaffRepository;
   @Autowired LoginRepository loginRepository;
   @Autowired SeasonGeographyConfigurationRepository seasonGeographyConfigurationRepository ;
   @Autowired  FieldStaffLeadershipSeasonRepository  fieldStaffLeadershipSeasonRepository;
   private static final String SP_FS_SEARCH_LIST = "CALL SPFieldStaffSearch(?)";

   @Override
   public FieldStaffLCList getFieldStaffLCList() {
      int count = 0;
      FieldStaffLCList fieldStaffLCList = new FieldStaffLCList();
      try {
         Query query = em.createNativeQuery(SP_FS_SEARCH_LIST);
         query.setParameter(1, 1);
         @SuppressWarnings("unchecked")
         List<Object[]> result = query.getResultList();
         if (result != null) {
            int tempGoID = 0;
            for (Object[] obj : result) {
               /*
                * 1 fieldStaffGold 2 fsGoldphoto 3 fsFirstName 4 fsLastName 5
                * phone 6 city 7 zipCode 8 stateName 9 active 10 email II
                * seasonName 12 seasonStatus 13 fsHierarchyGoId 14
                * fsHierarchyGoldphoto 15 fieldStaffType 16 fsHierarchyFirstName
                * 17 fsHierarchyLastName
                */

               int goId = Integer.valueOf(String.valueOf(obj[0]));

               if (tempGoID != goId) {
                  FieldStaffLC fslc = new FieldStaffLC();
                  if (String.valueOf(obj[0]) != null)
                     fslc.setFsLcGoId(Integer.valueOf(String.valueOf(obj[0])));
                  fslc.setFsPic(String.valueOf(obj[1]));
                  fslc.setFirstName(String.valueOf(obj[2]));
                  fslc.setLastName(String.valueOf(obj[3]));
                  fslc.setPhone(String.valueOf(obj[4]));
                  fslc.setCity(String.valueOf(obj[5]));
                  fslc.setZip(String.valueOf(obj[6]));
                  fslc.setState(String.valueOf(obj[7]));
                  if (String.valueOf(obj[7]) != null)
                     fslc.setActive(Boolean.valueOf(String.valueOf(obj[8])));
                  fslc.setEmail(String.valueOf(obj[9]));
                  count++;
                  fieldStaffLCList.getFieldStaffLcs().add(fslc);
               }
               LCSeasonContact lcSeasonContact = new LCSeasonContact();
               lcSeasonContact.setSeasonName(String.valueOf(obj[10]));
               lcSeasonContact.setFieldStaffType(String.valueOf(obj[14]));
               lcSeasonContact.setSeasonStatus(String.valueOf(obj[11]));

               Contact cont = new Contact();
               cont.setFirstName(String.valueOf(obj[15]));
               cont.setLastName(String.valueOf(obj[16]));
               cont.setGoId(String.valueOf(obj[12]));
               cont.setPicture(String.valueOf(obj[13]));
               lcSeasonContact.setFieldStaffDetail(cont);
               if (fieldStaffLCList.getFieldStaffLcs() != null && !fieldStaffLCList.getFieldStaffLcs().isEmpty())
                  fieldStaffLCList.getFieldStaffLcs().get(fieldStaffLCList.getFieldStaffLcs().size() - 1).getLcSeasonContacts().add(lcSeasonContact);
               else if (fieldStaffLCList.getFieldStaffLcs().isEmpty()) {
                  fieldStaffLCList.getFieldStaffLcs().get(0).getLcSeasonContacts().add(lcSeasonContact);
               }
               tempGoID = goId;
            }
         } else {
            fieldStaffLCList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
         fieldStaffLCList.setCount(count);
         fieldStaffLCList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FS_SERVICE_SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         fieldStaffLCList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_LIST.getValue(),
               messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_LIST)));
         LOGGER.error(messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_LIST));
         e.printStackTrace();
      }
      return fieldStaffLCList;
   }

   @Override
   public FieldStaffRMList getFieldStaffRMList() {

      FieldStaffRMList fieldStaffRMList = new FieldStaffRMList();
      int count = 0;
      try {
         Query query = em.createNativeQuery(SP_FS_SEARCH_LIST);
         query.setParameter(1, 2);
         @SuppressWarnings("unchecked")
         List<Object[]> result = query.getResultList();
         if (result != null) {
            int tempGoID = 0;
            for (Object[] obj : result) {
               /*
               1 fieldStaffGold 2 fsGoldphoto 3 fsFirstName 4 fsLastName 5 phone 6 city 
               7 zipCode 8 stateName 9 active 10 email 11 seasonName 12 seasonStatus 
               13 fsHierarchyGold 14 fsHierarchyGoldphoto 15 fieldStaffType 
               16 fsHierarchyFirstName 17 fsHierarchyLastName 
                */

               int goId = Integer.valueOf(String.valueOf(obj[0]));
               if (tempGoID != goId) {
                  FieldStaffRM fsrm = new FieldStaffRM();
                  fsrm.setFsGoId(Integer.valueOf(String.valueOf(obj[0])));
                  fsrm.setFsPic(String.valueOf(obj[1]));
                  fsrm.setFirstName(String.valueOf(obj[2]));
                  fsrm.setLastName(String.valueOf(obj[3]));
                  fsrm.setPhone(String.valueOf(obj[4]));
                  fsrm.setCity(String.valueOf(obj[5]));
                  fsrm.setZip(String.valueOf(obj[6]));
                  fsrm.setState(String.valueOf(obj[7]));
                  if (obj[8] != null)
                     fsrm.setActive(Boolean.valueOf(String.valueOf(obj[7])));
                  fsrm.setEmail(String.valueOf(obj[8]));
                  count++;
                  fieldStaffRMList.getFieldStaffRms().add(fsrm);
               }

               RMSeasonContact rmSc = new RMSeasonContact();
               rmSc.setSeasonName(String.valueOf(obj[10]));
               rmSc.setFieldStaffType(String.valueOf(obj[14]));
               rmSc.setSeasonStatus(String.valueOf(obj[11]));

               com.ccighgo.service.transport.fieldstaff.beans.rmlist.Contact cont = new com.ccighgo.service.transport.fieldstaff.beans.rmlist.Contact();
               cont.setFirstName(String.valueOf(obj[15]));
               cont.setLastName(String.valueOf(obj[16]));
               cont.setGoId(String.valueOf(obj[12]));
               cont.setPicture(String.valueOf(obj[13]));
               rmSc.setFieldStaffDetail(cont);
               if (fieldStaffRMList.getFieldStaffRms() != null && !fieldStaffRMList.getFieldStaffRms().isEmpty())
                  fieldStaffRMList.getFieldStaffRms().get(fieldStaffRMList.getFieldStaffRms().size() - 1).getRmSeasonContacts().add(rmSc);
               else if (fieldStaffRMList.getFieldStaffRms().isEmpty()) {
                  fieldStaffRMList.getFieldStaffRms().get(0).getRmSeasonContacts().add(rmSc);
               }
               tempGoID = goId;
            }

         } else {
            fieldStaffRMList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
         fieldStaffRMList.setCount(count);
         fieldStaffRMList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FS_SERVICE_SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         fieldStaffRMList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_LIST.getValue(),
               messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_LIST)));
         LOGGER.error(messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_LIST));
         e.printStackTrace();
      }
      return fieldStaffRMList;
   }

   
   
   public FieldStaffLCList getFieldStaffLCList(Integer roleId) {
      int count = 0;
      FieldStaffLCList fieldStaffLCList = new FieldStaffLCList();
      try {
    	  List<FieldStaff> fieldStaffList = fieldStaffRepository.findAllRDStaff(1);
//    	  SeasonGeographyConfigurationRepository s = seasonGeographyConfigurationRepository.findDistinctRegions()
    	  
    	  
    	  for (FieldStaff fieldStaff : fieldStaffList) {
    		  FieldStaffLC fsl = new FieldStaffLC();
    		  fsl.setFirstName(fieldStaff.getFirstName());
    		  fsl.setPhone(fieldStaff.getPhoto());
    		  fsl.setLastName(fieldStaff.getLastName());
    		  fsl.setPhone(fieldStaff.getPhone());
    		  fsl.setCity(fieldStaff.getCurrentCity());
    		  fsl.setState(fieldStaff.getLookupUsstate2().getStateName());
    		  fsl.setZip(fieldStaff.getCurrentZipCode());
    		  Login l = loginRepository.findByCCIGoId(fieldStaff.getFieldStaffGoId());
    		  fsl.setActive(l.getActive()!=null && l.getActive()==1);
    		  fsl.setEmail(l.getEmail());
		}
    	  List<SeasonGeographyConfiguration>  seasonGeographyConfigurationId= fieldStaffLeadershipSeasonRepository.findByFieldStaffGoId(50006);

    	  for (SeasonGeographyConfiguration seasonGeographyConfiguration : seasonGeographyConfigurationId) {
			
		}
    	  
      } catch (Exception e) {
         fieldStaffLCList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_LIST.getValue(),
               messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_LIST)));
         LOGGER.error(messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_LIST));
         e.printStackTrace();
      }
      return fieldStaffLCList;
   }
   
}

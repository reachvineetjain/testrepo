/**
 * 
 */
package com.ccighgo.service.components.fieldstaff.listing;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.FieldStaffType;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.FieldStaffLeadershipSeasonRepository;
import com.ccighgo.jpa.repositories.FieldStaffRepository;
import com.ccighgo.jpa.repositories.FieldStaffTypeRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.SeasonGeographyConfigurationRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.transport.fieldstaff.beans.ac.season.contacts.FSACSeasonContact;
import com.ccighgo.service.transport.fieldstaff.beans.ac.season.contacts.FSACSeasonContacts;
import com.ccighgo.service.transport.fieldstaff.beans.fslist.FieldStaff;
import com.ccighgo.service.transport.fieldstaff.beans.fslist.FieldStaffList;
import com.ccighgo.service.transport.fieldstaff.beans.fstypes.FieldStaffTypes;
import com.ccighgo.service.transport.fieldstaff.beans.lc.season.contacts.FSLCSeasonContact;
import com.ccighgo.service.transport.fieldstaff.beans.lc.season.contacts.FSLCSeasonContacts;
import com.ccighgo.service.transport.fieldstaff.beans.rd.season.contacts.FSRDSeasonContact;
import com.ccighgo.service.transport.fieldstaff.beans.rd.season.contacts.FSRDSeasonContacts;
import com.ccighgo.service.transport.fieldstaff.beans.rm.season.contacts.FSRMSeasonContact;
import com.ccighgo.service.transport.fieldstaff.beans.rm.season.contacts.FSRMSeasonContacts;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.CCIUtils;

/**
 * @author ravi
 *
 */
@Component
public class FieldStaffListingInterfaceImpl implements FieldStaffListingInterface {

   private static final Logger LOGGER = Logger.getLogger(FieldStaffListingInterface.class);

   @Autowired EntityManager entityManager;
   @Autowired MessageUtils messageUtil;
   @Autowired CommonComponentUtils componentUtils;

   @Autowired FieldStaffRepository fieldStaffRepository;
   @Autowired FieldStaffTypeRepository fieldStaffTypeRepository;
   @Autowired LoginRepository loginRepository;
   @Autowired SeasonGeographyConfigurationRepository seasonGeographyConfigurationRepository;
   @Autowired FieldStaffLeadershipSeasonRepository fieldStaffLeadershipSeasonRepository;

   // parameter:role id
   private static final String SP_FS_SEARCH_LIST = "CALL SPFieldStaffSearch(?)";
   // parameter: GoId
   private static final String SP_FS_SEASON_HIERARCHY = "CALL SPFieldStaffSeasonHierarchy (?)";

   @Override
   @Transactional(readOnly = true)
   @SuppressWarnings("unchecked")
   public FieldStaffList getFieldStaffList(String typeId) {
      int count = 0;
      FieldStaffList fieldStaffList = new FieldStaffList();
      try {
         Query searchFSQuery = entityManager.createNativeQuery(SP_FS_SEARCH_LIST);
         searchFSQuery.setParameter(1, Integer.valueOf(typeId));
         List<Object[]> fsList = searchFSQuery.getResultList();
         if (fsList == null || fsList.isEmpty()) {
            fieldStaffList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
            return fieldStaffList;
         }
         List<FieldStaff> fieldStaffs = new ArrayList<FieldStaff>();
         for (Object[] fs : fsList) {
            FieldStaff fieldStaff = new FieldStaff();
            // create record only if go id is not null
            if (String.valueOf(fs[0]) != null) {
               int fsGoId = Integer.valueOf(fs[0].toString());
               // SP position 0: GoId
               fieldStaff.setGoId(fsGoId);
               // SP position 1: field staff picture
               fieldStaff.setPicture(fs[1] != null ? fs[1].toString() : CCIConstants.EMPTY);
               // SP position 2: first name of field staff
               fieldStaff.setFirstName(fs[2] != null ? fs[2].toString() : CCIConstants.EMPTY);
               // SP position 3: last name of field staff
               fieldStaff.setLastName(fs[3] != null ? fs[3].toString() : CCIConstants.EMPTY);
               // SP position 4: contact number
               fieldStaff.setPhone(fs[4] != null ? fs[4].toString() : CCIConstants.EMPTY);
               // SP position 5: city of field staff
               fieldStaff.setCity(fs[5] != null ? fs[5].toString() : CCIConstants.EMPTY);
               // SP position 6: zip/postal code of field staff
               fieldStaff.setZip(fs[6] != null ? fs[6].toString() : CCIConstants.EMPTY);
               // SP position 7: state of field staff
               fieldStaff.setState(fs[7] != null ? fs[7].toString() : CCIConstants.EMPTY);
               // SP position 8: is field staff active?
               fieldStaff.setActive(Boolean.valueOf(fs[8].toString()).equals(Boolean.TRUE) ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
               // SP position 9: status of the field staff
               fieldStaff.setFsStatus(fs[9] != null ? fs[9].toString() : CCIConstants.EMPTY);
               fieldStaffs.add(fieldStaff);
               count++;
            }
         }
         fieldStaffList.setCount(count);
         fieldStaffList.getFieldStaff().addAll(fieldStaffs);
         fieldStaffList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FS_SERVICE_SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         fieldStaffList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_LIST.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return fieldStaffList;
   }

   @Override
   @Transactional(readOnly = true)
   public FieldStaffTypes getFieldStaffTypes() {
      FieldStaffTypes fsTypes = new FieldStaffTypes();
      try {
         List<FieldStaffType> fsTypeDBList = fieldStaffTypeRepository.findAll();
         if (fsTypeDBList != null) {
            List<com.ccighgo.service.transport.fieldstaff.beans.fstypes.FieldStaffType> fieldStaffType = new ArrayList<com.ccighgo.service.transport.fieldstaff.beans.fstypes.FieldStaffType>();
            for (FieldStaffType ft : fsTypeDBList) {
               if (ft.getFieldStaffTypeId() != 6) {
                  com.ccighgo.service.transport.fieldstaff.beans.fstypes.FieldStaffType fType = new com.ccighgo.service.transport.fieldstaff.beans.fstypes.FieldStaffType();
                  fType.setFieldStaffTypeId(ft.getFieldStaffTypeId());
                  fType.setFieldStaffType(ft.getFieldStaffTypeName());
                  fType.setFieldStaffTypeCode(ft.getFieldStaffTypeCode());
                  fieldStaffType.add(fType);
               }
            }
            fsTypes.getFieldStaffType().addAll(fieldStaffType);
            fsTypes.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FS_SERVICE_SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }
      } catch (Exception e) {
         fsTypes.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_LIST.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return fsTypes;
   }

   @Override
   @Transactional(readOnly = true)
   @SuppressWarnings("unchecked")
   public FSLCSeasonContacts getFSLCSeasonContacts(String goId) {
      FSLCSeasonContacts fslcSeasonContacts = new FSLCSeasonContacts();
      try {
         Query searchFSQuery = entityManager.createNativeQuery(SP_FS_SEASON_HIERARCHY);
         searchFSQuery.setParameter(1, Integer.valueOf(goId));
         List<Object[]> seasonContactList = searchFSQuery.getResultList();
         if (seasonContactList != null) {
            List<FSLCSeasonContact> fslcSeasonContactsList = new ArrayList<FSLCSeasonContact>();
            for (Object[] obj : seasonContactList) {
               FSLCSeasonContact contact = new FSLCSeasonContact();
               contact.setSeasonName(obj[2] != null ? obj[2].toString() : CCIConstants.EMPTY);
               contact.setAcFirstName(obj[3] != null ? CCIUtils.getNameString(obj[3].toString()) : CCIConstants.EMPTY);
               contact.setAcLastName(obj[4] != null ? CCIUtils.getNameString(obj[4].toString()) : CCIConstants.EMPTY);
               contact.setErdFirstName(obj[5] != null ? CCIUtils.getNameString(obj[5].toString()) : CCIConstants.EMPTY);
               contact.setErdLastName(obj[6] != null ? CCIUtils.getNameString(obj[6].toString()) : CCIConstants.EMPTY);
               contact.setRdFirstName(obj[7] != null ? CCIUtils.getNameString(obj[7].toString()) : CCIConstants.EMPTY);
               contact.setRdLastName(obj[8] != null ? CCIUtils.getNameString(obj[8].toString()) : CCIConstants.EMPTY);
               contact.setRmFirstName(obj[9] != null ? CCIUtils.getNameString(obj[9].toString()) : CCIConstants.EMPTY);
               contact.setRmLastName(obj[10] != null ? CCIUtils.getNameString(obj[10].toString()) : CCIConstants.EMPTY);
               contact.setAcPicture(obj[11] != null ? obj[11].toString() : CCIConstants.EMPTY);
               contact.setErdPicture(obj[12] != null ? obj[12].toString() : CCIConstants.EMPTY);
               contact.setRdPicture(obj[13] != null ? obj[13].toString() : CCIConstants.EMPTY);
               contact.setRmPicture(obj[14] != null ? obj[14].toString() : CCIConstants.EMPTY);
               fslcSeasonContactsList.add(contact);
            }
            fslcSeasonContacts.getFSLCSeasonContacts().addAll(fslcSeasonContactsList);
            fslcSeasonContacts.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FS_SERVICE_SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            fslcSeasonContacts.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (Exception e) {
         fslcSeasonContacts.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_LIST.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return fslcSeasonContacts;
   }

   @Override
   @Transactional(readOnly = true)
   @SuppressWarnings("unchecked")
   public FSACSeasonContacts getFSACSeasonContacts(String goId) {
      FSACSeasonContacts fsacSeasonContacts = new FSACSeasonContacts();
      try {
         Query searchFSQuery = entityManager.createNativeQuery(SP_FS_SEASON_HIERARCHY);
         searchFSQuery.setParameter(1, Integer.valueOf(goId));
         List<Object[]> seasonContactList = searchFSQuery.getResultList();
         if (seasonContactList != null) {
            List<FSACSeasonContact> fsacSeasonContactsList = new ArrayList<FSACSeasonContact>();
            for (Object[] obj : seasonContactList) {
               FSACSeasonContact contact = new FSACSeasonContact();
               contact.setSeasonName(obj[2] != null ? obj[2].toString() : CCIConstants.EMPTY);
               contact.setErdFirstName(obj[3] != null ? CCIUtils.getNameString(obj[3].toString()) : CCIConstants.EMPTY);
               contact.setErdLastName(obj[4] != null ? CCIUtils.getNameString(obj[4].toString()) : CCIConstants.EMPTY);
               contact.setRdFirstName(obj[5] != null ? CCIUtils.getNameString(obj[5].toString()) : CCIConstants.EMPTY);
               contact.setRdLastName(obj[6] != null ? CCIUtils.getNameString(obj[6].toString()) : CCIConstants.EMPTY);
               contact.setRmFirstName(obj[7] != null ? CCIUtils.getNameString(obj[7].toString()) : CCIConstants.EMPTY);
               contact.setRmLastName(obj[8] != null ? CCIUtils.getNameString(obj[8].toString()) : CCIConstants.EMPTY);
               contact.setErdPicture(obj[9] != null ? obj[9].toString() : CCIConstants.EMPTY);
               contact.setRdPicture(obj[10] != null ? obj[10].toString() : CCIConstants.EMPTY);
               contact.setRmPicture(obj[11] != null ? obj[11].toString() : CCIConstants.EMPTY);
               fsacSeasonContactsList.add(contact);
            }
            fsacSeasonContacts.getFSACSeasonContacts().addAll(fsacSeasonContactsList);
            fsacSeasonContacts.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FS_SERVICE_SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            fsacSeasonContacts.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (Exception e) {
         fsacSeasonContacts.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_LIST.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return fsacSeasonContacts;
   }

   @Override
   @Transactional(readOnly = true)
   @SuppressWarnings("unchecked")
   public FSRDSeasonContacts getFSRDSeasonContacts(String goId) {
      FSRDSeasonContacts fsrdSeasonContacts = new FSRDSeasonContacts();
      try {
         Query searchFSQuery = entityManager.createNativeQuery(SP_FS_SEASON_HIERARCHY);
         searchFSQuery.setParameter(1, Integer.valueOf(goId));
         List<Object[]> seasonContactList = searchFSQuery.getResultList();
         if (seasonContactList != null) {
            List<FSRDSeasonContact> fsrdSeasonContactsList = new ArrayList<FSRDSeasonContact>();
            for (Object[] obj : seasonContactList) {
               FSRDSeasonContact contact = new FSRDSeasonContact();
               contact.setSeasonName(obj[2] != null ? obj[2].toString() : CCIConstants.EMPTY);
               contact.setErdFirstName(obj[3] != null ? CCIUtils.getNameString(obj[3].toString()) : CCIConstants.EMPTY);
               contact.setErdLastName(obj[4] != null ? CCIUtils.getNameString(obj[4].toString()) : CCIConstants.EMPTY);
               contact.setErdPicture(obj[5] != null ? obj[5].toString() : CCIConstants.EMPTY);
               fsrdSeasonContactsList.add(contact);
            }
            fsrdSeasonContacts.getFSRDSeasonContacts().addAll(fsrdSeasonContactsList);
            fsrdSeasonContacts.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FS_SERVICE_SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            fsrdSeasonContacts.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (Exception e) {
         fsrdSeasonContacts.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_LIST.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return fsrdSeasonContacts;
   }

   @Override
   @Transactional(readOnly = true)
   @SuppressWarnings("unchecked")
   public FSRMSeasonContacts getFSRMSeasonContacts(String goId) {
      FSRMSeasonContacts fsrmSeasonContacts = new FSRMSeasonContacts();
      try {
         Query searchFSQuery = entityManager.createNativeQuery(SP_FS_SEASON_HIERARCHY);
         searchFSQuery.setParameter(1, Integer.valueOf(goId));
         List<Object[]> seasonContactList = searchFSQuery.getResultList();
         if (seasonContactList != null) {
            List<FSRMSeasonContact> fsrmSeasonContactsList = new ArrayList<FSRMSeasonContact>();
            for (Object[] obj : seasonContactList) {
               FSRMSeasonContact contact = new FSRMSeasonContact();
               contact.setSeasonName(obj[2] != null ? obj[2].toString() : CCIConstants.EMPTY);
               contact.setErdFirstName(obj[3] != null ? CCIUtils.getNameString(obj[3].toString()) : CCIConstants.EMPTY);
               contact.setErdLastName(obj[4] != null ? CCIUtils.getNameString(obj[4].toString()) : CCIConstants.EMPTY);
               contact.setRdFirstName(obj[5] != null ? CCIUtils.getNameString(obj[5].toString()) : CCIConstants.EMPTY);
               contact.setRdLastName(obj[6] != null ? CCIUtils.getNameString(obj[6].toString()) : CCIConstants.EMPTY);
               contact.setErdPicture(obj[7] != null ? obj[7].toString() : CCIConstants.EMPTY);
               contact.setRdPicture(obj[8] != null ? obj[8].toString() : CCIConstants.EMPTY);
               fsrmSeasonContactsList.add(contact);
            }
            fsrmSeasonContacts.getFSRMSeasonContacts().addAll(fsrmSeasonContactsList);
            fsrmSeasonContacts.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FS_SERVICE_SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            fsrmSeasonContacts.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (Exception e) {
         fsrmSeasonContacts.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_LIST.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return fsrmSeasonContacts;
   }
}

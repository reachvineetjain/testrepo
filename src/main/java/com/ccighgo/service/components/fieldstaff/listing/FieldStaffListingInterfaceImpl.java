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

import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.FieldStaffLeadershipSeasonRepository;
import com.ccighgo.jpa.repositories.FieldStaffRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.SeasonGeographyConfigurationRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.FieldStaffMessageConstants;
import com.ccighgo.service.transport.fieldstaff.beans.aclist.ACSeasonContact;
import com.ccighgo.service.transport.fieldstaff.beans.aclist.FieldStaffAC;
import com.ccighgo.service.transport.fieldstaff.beans.aclist.FieldStaffACList;
import com.ccighgo.service.transport.fieldstaff.beans.lclist.Contact;
import com.ccighgo.service.transport.fieldstaff.beans.lclist.FieldStaffLC;
import com.ccighgo.service.transport.fieldstaff.beans.lclist.FieldStaffLCList;
import com.ccighgo.service.transport.fieldstaff.beans.lclist.LCSeasonContact;
import com.ccighgo.service.transport.fieldstaff.beans.rdlist.FieldStaffRD;
import com.ccighgo.service.transport.fieldstaff.beans.rdlist.FieldStaffRDList;
import com.ccighgo.service.transport.fieldstaff.beans.rdlist.RDSeasonContact;
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

   @Autowired EntityManager entityManager;
   @Autowired MessageUtils messageUtil;
   @Autowired CommonComponentUtils componentUtils;

   @Autowired FieldStaffRepository fieldStaffRepository;
   @Autowired LoginRepository loginRepository;
   @Autowired SeasonGeographyConfigurationRepository seasonGeographyConfigurationRepository;
   @Autowired FieldStaffLeadershipSeasonRepository fieldStaffLeadershipSeasonRepository;

   // parameter:role id
   private static final String SP_FS_SEARCH_LIST = "CALL SPFieldStaffSearch(?)";
   // parameter:go id
   private static final String SP_FS_SEASONS = "CALL SPFieldStaffSeasonsList (?)";
   // parameter:parameter:go id, season id and department program id
   private static final String SP_FS_SEASON_CONTACTS = "CALL SPFieldStaffHeirarchy (?,?,?)";

   @Override
   @Transactional(readOnly = true)
   @SuppressWarnings("unchecked")
   public FieldStaffLCList getFieldStaffLCList() {
      int count = 0;
      FieldStaffLCList fieldStaffLCList = new FieldStaffLCList();
      try {
         Query searchFSQuery = entityManager.createNativeQuery(SP_FS_SEARCH_LIST);
         searchFSQuery.setParameter(1, CCIConstants.LOCAL_COORDINATOR);
         List<Object[]> fsList = searchFSQuery.getResultList();
         if (fsList == null || fsList.isEmpty()) {
            throw new CcighgoException("no local coordinators found");
         }
         List<FieldStaffLC> fieldStaffLcsList = new ArrayList<FieldStaffLC>();
         List<LCSeasonContact> lcSeasonContactsList = new ArrayList<LCSeasonContact>();
         for (Object[] fs : fsList) {
            FieldStaffLC fieldStaffLc = new FieldStaffLC();
            // create record only if go id is not null
            if (String.valueOf(fs[0]) != null) {
               int fsGoId = Integer.valueOf(fs[0].toString());
               // SP position 0: GoId
               fieldStaffLc.setGoId(fsGoId);
               // SP position 1: field staff picture
               fieldStaffLc.setPicture(fs[1] != null ? fs[1].toString() : CCIConstants.EMPTY);
               // SP position 2: first name of field staff
               fieldStaffLc.setFirstName(fs[2] != null ? fs[2].toString() : CCIConstants.EMPTY);
               // SP position 3: last name of field staff
               fieldStaffLc.setLastName(fs[3] != null ? fs[3].toString() : CCIConstants.EMPTY);
               // SP position 4: contact number
               fieldStaffLc.setPhone(fs[4] != null ? fs[4].toString() : CCIConstants.EMPTY);
               // SP position 5: city of field staff
               fieldStaffLc.setCity(fs[5] != null ? fs[5].toString() : CCIConstants.EMPTY);
               // SP position 6: zip/postal code of field staff
               fieldStaffLc.setZip(fs[6] != null ? fs[6].toString() : CCIConstants.EMPTY);
               // SP position 7: state of field staff
               fieldStaffLc.setState(fs[7] != null ? fs[7].toString() : CCIConstants.EMPTY);
               // SP position 8: is field staff active?
               fieldStaffLc.setActive(Boolean.valueOf(fs[8].toString()).equals(Boolean.TRUE) ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
               // SP position 9: status of the field staff
               fieldStaffLc.setFsStatus(fs[9] != null ? fs[9].toString() : CCIConstants.EMPTY);
               count++;
               /**
                * Fetch the seasons for field staff based on go id
                */
               Query searchFSSeasonsQuery = entityManager.createNativeQuery(SP_FS_SEASONS);
               searchFSSeasonsQuery.setParameter(1, fsGoId);
               List<Object[]> fsSeasonsList = searchFSSeasonsQuery.getResultList();
               if (fsSeasonsList != null && !(fsList.isEmpty())) {
                  for (Object[] fsSeason : fsSeasonsList) {
                     if (fsSeason[1] != null && fsSeason[2] != null) {
                        LCSeasonContact seasonContact = new LCSeasonContact();
                        int seasonId = Integer.valueOf(fsSeason[1].toString());
                        int departmentProgrammId = Integer.valueOf(fsSeason[2].toString());
                        Contact areaCoordinator = new Contact();
                        Contact regionalManager = new Contact();
                        Contact regionalDirector = new Contact();
                        Contact executiveRegionalDirector = new Contact();
                        seasonContact.setSeasonId(seasonId);
                        seasonContact.setSeasonProgramId(departmentProgrammId);
                        seasonContact.setSeasonName(fsSeason[3].toString());
                        seasonContact.setSeasonStatus(fsSeason[6].toString());
                        /**
                         * Fetch season contacts based on go id , season id and
                         * department program id
                         */
                        Query searchFSSeasonContactsQuery = entityManager.createNativeQuery(SP_FS_SEASON_CONTACTS);
                        searchFSSeasonContactsQuery.setParameter(1, fsGoId);
                        searchFSSeasonContactsQuery.setParameter(2, seasonId);
                        searchFSSeasonContactsQuery.setParameter(3, departmentProgrammId);
                        List<Object[]> fsSeasonContactList = searchFSSeasonContactsQuery.getResultList();
                        if (fsSeasonContactList != null && !(fsSeasonContactList.isEmpty())) {
                           for (Object[] contact : fsSeasonContactList) {
                              if (contact[4] != null) {
                                 if (String.valueOf(contact[4]).equals(CCIConstants.AC)) {
                                    areaCoordinator.setPicture(contact[1] != null ? contact[1].toString() : CCIConstants.EMPTY);
                                    areaCoordinator.setFirstName(contact[2] != null ? contact[2].toString() : CCIConstants.EMPTY);
                                    areaCoordinator.setLastName(contact[3] != null ? contact[3].toString() : CCIConstants.EMPTY);
                                 }
                                 if (String.valueOf(contact[4]).equals(CCIConstants.RM)) {
                                    regionalManager.setPicture(contact[1] != null ? contact[1].toString() : CCIConstants.EMPTY);
                                    regionalManager.setFirstName(contact[2] != null ? contact[2].toString() : CCIConstants.EMPTY);
                                    regionalManager.setLastName(contact[3] != null ? contact[3].toString() : CCIConstants.EMPTY);
                                 }
                                 if (String.valueOf(contact[4]).equals(CCIConstants.RD)) {
                                    regionalDirector.setPicture(contact[1] != null ? contact[1].toString() : CCIConstants.EMPTY);
                                    regionalDirector.setFirstName(contact[2] != null ? contact[2].toString() : CCIConstants.EMPTY);
                                    regionalDirector.setLastName(contact[3] != null ? contact[3].toString() : CCIConstants.EMPTY);
                                 }
                                 if (String.valueOf(contact[4]).equals(CCIConstants.ERD)) {
                                    executiveRegionalDirector.setPicture(contact[1] != null ? contact[1].toString() : CCIConstants.EMPTY);
                                    executiveRegionalDirector.setFirstName(contact[2] != null ? contact[2].toString() : CCIConstants.EMPTY);
                                    executiveRegionalDirector.setLastName(contact[3] != null ? contact[3].toString() : CCIConstants.EMPTY);
                                 }
                              }
                           }
                        }
                        seasonContact.setAreaCoordinator(areaCoordinator);
                        seasonContact.setRegionalManager(regionalManager);
                        seasonContact.setRegionalDirector(regionalDirector);
                        seasonContact.setExecutiveRegionalDirector(executiveRegionalDirector);
                        lcSeasonContactsList.add(seasonContact);
                        fieldStaffLc.getLcSeasonContacts().addAll(lcSeasonContactsList);
                     }
                  }
               }
            }
            fieldStaffLcsList.add(fieldStaffLc);
         }
         fieldStaffLCList.setCount(count);
         fieldStaffLCList.getFieldStaffLcs().addAll(fieldStaffLcsList);
         fieldStaffLCList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FS_SERVICE_SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         fieldStaffLCList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_LIST.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return fieldStaffLCList;
   }

   @Override
   @Transactional(readOnly = true)
   @SuppressWarnings("unchecked")
   public FieldStaffRMList getFieldStaffRMList() {
      int count = 0;
      FieldStaffRMList fieldStaffRMList = new FieldStaffRMList();
      try {
         Query searchFSQuery = entityManager.createNativeQuery(SP_FS_SEARCH_LIST);
         searchFSQuery.setParameter(1, CCIConstants.REGIONAL_MANAGER);
         List<Object[]> fsList = searchFSQuery.getResultList();
         if (fsList == null || fsList.isEmpty()) {
            throw new CcighgoException("no regional managers found");
         }
         List<FieldStaffRM> fieldStaffRmsList = new ArrayList<FieldStaffRM>();
         List<RMSeasonContact> rmSeasonContactsList = new ArrayList<RMSeasonContact>();
         for (Object[] fs : fsList) {
            FieldStaffRM fieldStaffRm = new FieldStaffRM();
            // create record only if go id is not null
            if (String.valueOf(fs[0]) != null) {
               int fsGoId = Integer.valueOf(fs[0].toString());
               // SP position 0: GoId
               fieldStaffRm.setGoId(fsGoId);
               // SP position 1: field staff picture
               fieldStaffRm.setPicture(fs[1] != null ? fs[1].toString() : CCIConstants.EMPTY);
               // SP position 2: first name of field staff
               fieldStaffRm.setFirstName(fs[2] != null ? fs[2].toString() : CCIConstants.EMPTY);
               // SP position 3: last name of field staff
               fieldStaffRm.setLastName(fs[3] != null ? fs[3].toString() : CCIConstants.EMPTY);
               // SP position 4: contact number
               fieldStaffRm.setPhone(fs[4] != null ? fs[4].toString() : CCIConstants.EMPTY);
               // SP position 5: city of field staff
               fieldStaffRm.setCity(fs[5] != null ? fs[5].toString() : CCIConstants.EMPTY);
               // SP position 6: zip/postal code of field staff
               fieldStaffRm.setZip(fs[6] != null ? fs[6].toString() : CCIConstants.EMPTY);
               // SP position 7: state of field staff
               fieldStaffRm.setState(fs[7] != null ? fs[7].toString() : CCIConstants.EMPTY);
               // SP position 8: is field staff active?
               fieldStaffRm.setActive(Boolean.valueOf(fs[8].toString()).equals(Boolean.TRUE) ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
               // SP position 9: status of the field staff
               fieldStaffRm.setFsStatus(fs[9] != null ? fs[9].toString() : CCIConstants.EMPTY);
               count++;
               /**
                * Fetch the seasons for field staff based on go id
                */
               Query searchFSSeasonsQuery = entityManager.createNativeQuery(SP_FS_SEASONS);
               searchFSSeasonsQuery.setParameter(1, fsGoId);
               List<Object[]> fsSeasonsList = searchFSSeasonsQuery.getResultList();
               if (fsSeasonsList != null && !(fsList.isEmpty())) {
                  for (Object[] fsSeason : fsSeasonsList) {
                     if (fsSeason[1] != null && fsSeason[2] != null) {
                        RMSeasonContact seasonContact = new RMSeasonContact();
                        int seasonId = Integer.valueOf(fsSeason[1].toString());
                        int departmentProgrammId = Integer.valueOf(fsSeason[2].toString());
                        com.ccighgo.service.transport.fieldstaff.beans.rmlist.Contact regionalDirector = new com.ccighgo.service.transport.fieldstaff.beans.rmlist.Contact();
                        com.ccighgo.service.transport.fieldstaff.beans.rmlist.Contact executiveRegionalDirector = new com.ccighgo.service.transport.fieldstaff.beans.rmlist.Contact();
                        seasonContact.setSeasonId(seasonId);
                        seasonContact.setSeasonProgramId(departmentProgrammId);
                        seasonContact.setSeasonName(fsSeason[3].toString());
                        seasonContact.setSeasonStatus(fsSeason[6].toString());
                        /**
                         * Fetch season contacts based on go id , season id and
                         * department program id
                         */
                        Query searchFSSeasonContactsQuery = entityManager.createNativeQuery(SP_FS_SEASON_CONTACTS);
                        searchFSSeasonContactsQuery.setParameter(1, fsGoId);
                        searchFSSeasonContactsQuery.setParameter(2, seasonId);
                        searchFSSeasonContactsQuery.setParameter(3, departmentProgrammId);
                        List<Object[]> fsSeasonContactList = searchFSSeasonContactsQuery.getResultList();
                        if (fsSeasonContactList != null && !(fsSeasonContactList.isEmpty())) {
                           for (Object[] contact : fsSeasonContactList) {
                              if (contact[4] != null) {
                                 if (String.valueOf(contact[4]).equals(CCIConstants.RD)) {
                                    regionalDirector.setPicture(contact[1] != null ? contact[1].toString() : CCIConstants.EMPTY);
                                    regionalDirector.setFirstName(contact[2] != null ? contact[2].toString() : CCIConstants.EMPTY);
                                    regionalDirector.setLastName(contact[3] != null ? contact[3].toString() : CCIConstants.EMPTY);
                                 }
                                 if (String.valueOf(contact[4]).equals(CCIConstants.ERD)) {
                                    executiveRegionalDirector.setPicture(contact[1] != null ? contact[1].toString() : CCIConstants.EMPTY);
                                    executiveRegionalDirector.setFirstName(contact[2] != null ? contact[2].toString() : CCIConstants.EMPTY);
                                    executiveRegionalDirector.setLastName(contact[3] != null ? contact[3].toString() : CCIConstants.EMPTY);
                                 }
                              }
                           }
                        }
                        seasonContact.setRegionalDirector(regionalDirector);
                        seasonContact.setExecutiveRegionalDirector(executiveRegionalDirector);
                        rmSeasonContactsList.add(seasonContact);
                        fieldStaffRm.getRmSeasonContacts().addAll(rmSeasonContactsList);
                     }
                  }
               }
            }
            fieldStaffRmsList.add(fieldStaffRm);
         }
         fieldStaffRMList.setCount(count);
         fieldStaffRMList.getFieldStaffRms().addAll(fieldStaffRmsList);
         fieldStaffRMList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FS_SERVICE_SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         fieldStaffRMList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_LIST.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return fieldStaffRMList;
   }

   @Override
   @Transactional(readOnly = true)
   @SuppressWarnings("unchecked")
   public FieldStaffACList getFieldStaffACList() {
      int count = 0;
      FieldStaffACList acList = new FieldStaffACList();
      try {
         Query searchFSQuery = entityManager.createNativeQuery(SP_FS_SEARCH_LIST);
         searchFSQuery.setParameter(1, CCIConstants.AREA_COORDINATOR);
         List<Object[]> fsList = searchFSQuery.getResultList();
         if (fsList == null || fsList.isEmpty()) {
            throw new CcighgoException("no area coordinators found");
         }
         List<FieldStaffAC> fieldStaffAcsList = new ArrayList<FieldStaffAC>();
         List<ACSeasonContact> acSeasonContactsList = new ArrayList<ACSeasonContact>();
         for (Object[] fs : fsList) {
            FieldStaffAC fieldStaffAc = new FieldStaffAC();
            // create record only if go id is not null
            if (String.valueOf(fs[0]) != null) {
               int fsGoId = Integer.valueOf(fs[0].toString());
               // SP position 0: GoId
               fieldStaffAc.setGoId(fsGoId);
               // SP position 1: field staff picture
               fieldStaffAc.setPicture(fs[1] != null ? fs[1].toString() : CCIConstants.EMPTY);
               // SP position 2: first name of field staff
               fieldStaffAc.setFirstName(fs[2] != null ? fs[2].toString() : CCIConstants.EMPTY);
               // SP position 3: last name of field staff
               fieldStaffAc.setLastName(fs[3] != null ? fs[3].toString() : CCIConstants.EMPTY);
               // SP position 4: contact number
               fieldStaffAc.setPhone(fs[4] != null ? fs[4].toString() : CCIConstants.EMPTY);
               // SP position 5: city of field staff
               fieldStaffAc.setCity(fs[5] != null ? fs[5].toString() : CCIConstants.EMPTY);
               // SP position 6: zip/postal code of field staff
               fieldStaffAc.setZip(fs[6] != null ? fs[6].toString() : CCIConstants.EMPTY);
               // SP position 7: state of field staff
               fieldStaffAc.setState(fs[7] != null ? fs[7].toString() : CCIConstants.EMPTY);
               // SP position 8: is field staff active?
               fieldStaffAc.setActive(Boolean.valueOf(fs[8].toString()).equals(Boolean.TRUE) ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
               // SP position 9: status of the field staff
               fieldStaffAc.setFsStatus(fs[9] != null ? fs[9].toString() : CCIConstants.EMPTY);
               count++;
               /**
                * Fetch the seasons for field staff based on go id
                */
               Query searchFSSeasonsQuery = entityManager.createNativeQuery(SP_FS_SEASONS);
               searchFSSeasonsQuery.setParameter(1, fsGoId);
               List<Object[]> fsSeasonsList = searchFSSeasonsQuery.getResultList();
               if (fsSeasonsList != null && !(fsList.isEmpty())) {
                  for (Object[] fsSeason : fsSeasonsList) {
                     if (fsSeason[1] != null && fsSeason[2] != null) {
                        ACSeasonContact seasonContact = new ACSeasonContact();
                        int seasonId = Integer.valueOf(fsSeason[1].toString());
                        int departmentProgrammId = Integer.valueOf(fsSeason[2].toString());
                        com.ccighgo.service.transport.fieldstaff.beans.aclist.Contact regionalManager = new com.ccighgo.service.transport.fieldstaff.beans.aclist.Contact();
                        com.ccighgo.service.transport.fieldstaff.beans.aclist.Contact regionalDirector = new com.ccighgo.service.transport.fieldstaff.beans.aclist.Contact();
                        com.ccighgo.service.transport.fieldstaff.beans.aclist.Contact executiveRegionalDirector = new com.ccighgo.service.transport.fieldstaff.beans.aclist.Contact();
                        seasonContact.setSeasonId(seasonId);
                        seasonContact.setSeasonProgramId(departmentProgrammId);
                        seasonContact.setSeasonName(fsSeason[3].toString());
                        seasonContact.setSeasonStatus(fsSeason[6].toString());
                        /**
                         * Fetch season contacts based on go id , season id and
                         * department program id
                         */
                        Query searchFSSeasonContactsQuery = entityManager.createNativeQuery(SP_FS_SEASON_CONTACTS);
                        searchFSSeasonContactsQuery.setParameter(1, fsGoId);
                        searchFSSeasonContactsQuery.setParameter(2, seasonId);
                        searchFSSeasonContactsQuery.setParameter(3, departmentProgrammId);
                        List<Object[]> fsSeasonContactList = searchFSSeasonContactsQuery.getResultList();
                        if (fsSeasonContactList != null && !(fsSeasonContactList.isEmpty())) {
                           for (Object[] contact : fsSeasonContactList) {
                              if (contact[4] != null) {
                                 if (String.valueOf(contact[4]).equals(CCIConstants.RM)) {
                                    regionalManager.setPicture(contact[1] != null ? contact[1].toString() : CCIConstants.EMPTY);
                                    regionalManager.setFirstName(contact[2] != null ? contact[2].toString() : CCIConstants.EMPTY);
                                    regionalManager.setLastName(contact[3] != null ? contact[3].toString() : CCIConstants.EMPTY);
                                 }
                                 if (String.valueOf(contact[4]).equals(CCIConstants.RD)) {
                                    regionalDirector.setPicture(contact[1] != null ? contact[1].toString() : CCIConstants.EMPTY);
                                    regionalDirector.setFirstName(contact[2] != null ? contact[2].toString() : CCIConstants.EMPTY);
                                    regionalDirector.setLastName(contact[3] != null ? contact[3].toString() : CCIConstants.EMPTY);
                                 }
                                 if (String.valueOf(contact[4]).equals(CCIConstants.ERD)) {
                                    executiveRegionalDirector.setPicture(contact[1] != null ? contact[1].toString() : CCIConstants.EMPTY);
                                    executiveRegionalDirector.setFirstName(contact[2] != null ? contact[2].toString() : CCIConstants.EMPTY);
                                    executiveRegionalDirector.setLastName(contact[3] != null ? contact[3].toString() : CCIConstants.EMPTY);
                                 }
                              }
                           }
                        }
                        seasonContact.setExecutiveRegionalDirector(executiveRegionalDirector);
                        acSeasonContactsList.add(seasonContact);
                        fieldStaffAc.getAcSeasonContacts().addAll(acSeasonContactsList);
                     }
                  }
               }
            }
            fieldStaffAcsList.add(fieldStaffAc);
         }
         acList.setCount(count);
         acList.getFieldStaffAcs().addAll(fieldStaffAcsList);
         acList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FS_SERVICE_SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         acList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_LIST.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return acList;
   }

   @Override
   @Transactional(readOnly = true)
   @SuppressWarnings("unchecked")
   public FieldStaffRDList getFieldStaffRDList() {
      int count = 0;
      FieldStaffRDList rdList = new FieldStaffRDList();
      try {
         Query searchFSQuery = entityManager.createNativeQuery(SP_FS_SEARCH_LIST);
         searchFSQuery.setParameter(1, CCIConstants.REGIONAL_DIRECTOR);
         List<Object[]> fsList = searchFSQuery.getResultList();
         if (fsList == null || fsList.isEmpty()) {
            throw new CcighgoException("no regional directors found");
         }
         List<FieldStaffRD> fieldStaffRdsList = new ArrayList<FieldStaffRD>();
         List<RDSeasonContact> rdSeasonContactsList = new ArrayList<RDSeasonContact>();
         for (Object[] fs : fsList) {
            FieldStaffRD fieldStaffRd = new FieldStaffRD();
            // create record only if go id is not null
            if (String.valueOf(fs[0]) != null) {
               int fsGoId = Integer.valueOf(fs[0].toString());
               // SP position 0: GoId
               fieldStaffRd.setGoId(fsGoId);
               // SP position 1: field staff picture
               fieldStaffRd.setPicture(fs[1] != null ? fs[1].toString() : CCIConstants.EMPTY);
               // SP position 2: first name of field staff
               fieldStaffRd.setFirstName(fs[2] != null ? fs[2].toString() : CCIConstants.EMPTY);
               // SP position 3: last name of field staff
               fieldStaffRd.setLastName(fs[3] != null ? fs[3].toString() : CCIConstants.EMPTY);
               // SP position 4: contact number
               fieldStaffRd.setPhone(fs[4] != null ? fs[4].toString() : CCIConstants.EMPTY);
               // SP position 5: city of field staff
               fieldStaffRd.setCity(fs[5] != null ? fs[5].toString() : CCIConstants.EMPTY);
               // SP position 6: zip/postal code of field staff
               fieldStaffRd.setZip(fs[6] != null ? fs[6].toString() : CCIConstants.EMPTY);
               // SP position 7: state of field staff
               fieldStaffRd.setState(fs[7] != null ? fs[7].toString() : CCIConstants.EMPTY);
               // SP position 8: is field staff active?
               fieldStaffRd.setActive(Boolean.valueOf(fs[8].toString()).equals(Boolean.TRUE) ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
               // SP position 9: status of the field staff
               fieldStaffRd.setFsStatus(fs[9] != null ? fs[9].toString() : CCIConstants.EMPTY);
               count++;
               /**
                * Fetch the seasons for field staff based on go id
                */
               Query searchFSSeasonsQuery = entityManager.createNativeQuery(SP_FS_SEASONS);
               searchFSSeasonsQuery.setParameter(1, fsGoId);
               List<Object[]> fsSeasonsList = searchFSSeasonsQuery.getResultList();
               if (fsSeasonsList != null && !(fsList.isEmpty())) {
                  for (Object[] fsSeason : fsSeasonsList) {
                     if (fsSeason[1] != null && fsSeason[2] != null) {
                        RDSeasonContact seasonContact = new RDSeasonContact();
                        int seasonId = Integer.valueOf(fsSeason[1].toString());
                        int departmentProgrammId = Integer.valueOf(fsSeason[2].toString());
                        com.ccighgo.service.transport.fieldstaff.beans.rdlist.Contact executiveRegionalDirector = new com.ccighgo.service.transport.fieldstaff.beans.rdlist.Contact();
                        seasonContact.setSeasonId(seasonId);
                        seasonContact.setSeasonProgramId(departmentProgrammId);
                        seasonContact.setSeasonName(fsSeason[3].toString());
                        seasonContact.setSeasonStatus(fsSeason[6].toString());
                        /**
                         * Fetch season contacts based on go id , season id and
                         * department program id
                         */
                        Query searchFSSeasonContactsQuery = entityManager.createNativeQuery(SP_FS_SEASON_CONTACTS);
                        searchFSSeasonContactsQuery.setParameter(1, fsGoId);
                        searchFSSeasonContactsQuery.setParameter(2, seasonId);
                        searchFSSeasonContactsQuery.setParameter(3, departmentProgrammId);
                        List<Object[]> fsSeasonContactList = searchFSSeasonContactsQuery.getResultList();
                        if (fsSeasonContactList != null && !(fsSeasonContactList.isEmpty())) {
                           for (Object[] contact : fsSeasonContactList) {
                              if (contact[4] != null) {
                                 if (String.valueOf(contact[4]).equals(CCIConstants.ERD)) {
                                    executiveRegionalDirector.setPicture(contact[1] != null ? contact[1].toString() : CCIConstants.EMPTY);
                                    executiveRegionalDirector.setFirstName(contact[2] != null ? contact[2].toString() : CCIConstants.EMPTY);
                                    executiveRegionalDirector.setLastName(contact[3] != null ? contact[3].toString() : CCIConstants.EMPTY);
                                 }
                              }
                           }
                        }
                        seasonContact.setExecutiveRegionalDirector(executiveRegionalDirector);
                        rdSeasonContactsList.add(seasonContact);
                        fieldStaffRd.getRdSeasonContacts().addAll(rdSeasonContactsList);
                     }
                  }
               }
            }
            fieldStaffRdsList.add(fieldStaffRd);
         }
         rdList.setCount(count);
         rdList.getFieldStaffRds().addAll(fieldStaffRdsList);
         rdList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FS_SERVICE_SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         rdList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_LIST.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return rdList;
   }

}

/**
 * 
 */
package com.ccighgo.service.components.fieldstaff.listing;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.service.transport.fieldstaff.beans.lclist.Contact;
import com.ccighgo.service.transport.fieldstaff.beans.lclist.FieldStaffLC;
import com.ccighgo.service.transport.fieldstaff.beans.lclist.FieldStaffLCList;
import com.ccighgo.service.transport.fieldstaff.beans.lclist.LCSeasonContact;
import com.ccighgo.service.transport.fieldstaff.beans.rmlist.FieldStaffRM;
import com.ccighgo.service.transport.fieldstaff.beans.rmlist.FieldStaffRMList;
import com.ccighgo.service.transport.fieldstaff.beans.rmlist.RMSeasonContact;

/**
 * @author ravi
 *
 */
@Component
public class FieldStaffListingInterfaceImpl implements FieldStaffListingInterface {

   @Autowired
   EntityManager em;

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
                * 1 fieldStaffGold 2 firstName 3 IastName 4 phone 5 currentCity
                * 6 currentZipCode 7 stateName 8 active 9 email 10
                * fieldStaffStatusName 11 seasonName 12 AC 13 RM 14 RD 15 ERD 16
                * seasonStatus 17 photoLC 18 photoAC 19 photoRM 20 photoRD 21
                * photoERD
                */

               int goId = Integer.valueOf(String.valueOf(obj[0]));

               if (tempGoID != goId) {
                  FieldStaffLC fslc = new FieldStaffLC();
                  fslc.setFsLcGoId(Integer.valueOf(String.valueOf(obj[0])));
                  fslc.setFirstName(String.valueOf(obj[1]));
                  fslc.setLastName(String.valueOf(obj[2]));
                  fslc.setPhone(String.valueOf(obj[3]));
                  fslc.setCity(String.valueOf(obj[4]));
                  fslc.setZip(String.valueOf(obj[5]));
                  if (String.valueOf(obj[7]) != null)
                     fslc.setActive(Boolean.valueOf(String.valueOf(obj[7])));
                  fslc.setEmail(String.valueOf(obj[8]));
                  count++;
                  fieldStaffLCList.getFieldStaffLcs().add(fslc);
               }
               LCSeasonContact lcSeasonContact = new LCSeasonContact();
               lcSeasonContact.setSeasonName(String.valueOf(obj[10]));
               Contact acCon = new Contact();
               acCon.setFullName(String.valueOf(obj[11]));
               acCon.setPicture(String.valueOf(obj[17]));
               lcSeasonContact.setAreaCoordinator(acCon);
               Contact rmCon = new Contact();
               rmCon.setFullName(String.valueOf(obj[12]));
               rmCon.setPicture(String.valueOf(obj[18]));
               lcSeasonContact.setRegionalManager(rmCon);
               Contact rdCon = new Contact();
               rdCon.setFullName(String.valueOf(obj[13]));
               rdCon.setPicture(String.valueOf(obj[19]));
               lcSeasonContact.setRegionalDirector(rdCon);
               Contact erdCon = new Contact();
               erdCon.setFullName(String.valueOf(obj[14]));
               erdCon.setPicture(String.valueOf(obj[20]));
               lcSeasonContact.setExecutiveRegionalDirector(erdCon);
               if (fieldStaffLCList.getFieldStaffLcs() != null && !fieldStaffLCList.getFieldStaffLcs().isEmpty())
                  fieldStaffLCList.getFieldStaffLcs().get(fieldStaffLCList.getFieldStaffLcs().size() - 1).getLcSeasonContacts().add(lcSeasonContact);
               else if (fieldStaffLCList.getFieldStaffLcs().isEmpty()) {
                  fieldStaffLCList.getFieldStaffLcs().get(0).getLcSeasonContacts().add(lcSeasonContact);
               }
               tempGoID = goId;
            }
         }
         fieldStaffLCList.setCount(count);
      } catch (Exception e) {
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
                * 1 fieldStaffGold 2 firstName 3 lastName 4 phone 5 currentCity
                * 6 currentZipCode 7 stateName 8 active 9 email 10
                * fieldStaffStatusName 11 seasonName 12 RD 13 ERD 14
                * seasonStatus 15 photoRM 16 photoRD 17 photoERD
                */

               int goId = Integer.valueOf(String.valueOf(obj[0]));
               if (tempGoID != goId) {
                  FieldStaffRM fsrm = new FieldStaffRM();
                  fsrm.setFsLcGoId(Integer.valueOf(String.valueOf(obj[0])));
                  fsrm.setFirstName(String.valueOf(obj[1]));
                  fsrm.setLastName(String.valueOf(obj[2]));
                  fsrm.setPhone(String.valueOf(obj[3]));
                  fsrm.setCity(String.valueOf(obj[4]));
                  fsrm.setZip(String.valueOf(obj[5]));
                  if (String.valueOf(obj[7]) != null)
                     fsrm.setActive(Boolean.valueOf(String.valueOf(obj[7])));
                  fsrm.setEmail(String.valueOf(obj[8]));
                  count++;
                  fieldStaffRMList.getFieldStaffRms().add(fsrm);
               }

               RMSeasonContact rmSc = new RMSeasonContact();
               rmSc.setSeasonName(String.valueOf(obj[10]));
               com.ccighgo.service.transport.fieldstaff.beans.rmlist.Contact rdContact = new com.ccighgo.service.transport.fieldstaff.beans.rmlist.Contact();
               rdContact.setFullName(String.valueOf(obj[11]));
               rdContact.setPicture(String.valueOf(obj[15]));
               rmSc.setRegionalDirector(rdContact);
               com.ccighgo.service.transport.fieldstaff.beans.rmlist.Contact erdContact = new com.ccighgo.service.transport.fieldstaff.beans.rmlist.Contact();
               erdContact.setFullName(String.valueOf(obj[12]));
               erdContact.setPicture(String.valueOf(obj[16]));
               if (fieldStaffRMList.getFieldStaffRms() != null && !fieldStaffRMList.getFieldStaffRms().isEmpty())
                  fieldStaffRMList.getFieldStaffRms().get(fieldStaffRMList.getFieldStaffRms().size() - 1).getRmSeasonContacts().add(rmSc);
               else if (fieldStaffRMList.getFieldStaffRms().isEmpty()) {
                  fieldStaffRMList.getFieldStaffRms().get(0).getRmSeasonContacts().add(rmSc);
               }
               tempGoID = goId;
            }

         }
         fieldStaffRMList.setCount(count);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return fieldStaffRMList;
   }

}

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
import com.ccighgo.service.transport.fieldstaff.beans.lclist.FieldStaffLCList;
import com.ccighgo.service.transport.fieldstaff.beans.rmlist.FieldStaffRM;
import com.ccighgo.service.transport.fieldstaff.beans.rmlist.FieldStaffRMList;
import com.ccighgo.service.transport.fieldstaff.beans.rmlist.RMSeasonContact;

/**
 * @author ravi
 *
 */
@Component
public class FieldStaffListingInterfaceImpl implements FieldStaffListingInterface {

   @Autowired EntityManager em;
   
   private static final String SP_FS_SEARCH_LIST = "CALL SPFieldStaffSearch(?)";
   @Override
   public FieldStaffLCList getFieldStaffLCList() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public FieldStaffRMList getFieldStaffRMList() {
      
      FieldStaffRMList fieldStaffRMList= new FieldStaffRMList();
      int count=0;
      Query query = em.createNativeQuery(SP_FS_SEARCH_LIST);
      query.setParameter(1, 3);
      @SuppressWarnings("unchecked")
      List<Object[]> result = query.getResultList();
      if(result!=null)
      {
         for(Object[] obj:result)
         {
           /* 1 fieldStaffGold 2 firstName 3 IastName 4 phone 5 currentCity 6 currentZipCode 
            7 stateName 8 active 9 email 10 fieldStaffStatusName 11 seasonName 12 AC 
            13 RM 14 RD 15 ERD 16 seasonStatus 17 photoLC 18 photoAC 19 photo RM 20 photoRD 
            21 photoERD */
            FieldStaffRM fsrm=new FieldStaffRM();
            fsrm.setFsLcGoId(Integer.valueOf(String.valueOf(obj[0])));
            fsrm.setFirstName(String.valueOf(obj[1]));
            fsrm.setLastName(String.valueOf(obj[2]));
            fsrm.setPhone(String.valueOf(obj[3]));
            fsrm.setCity(String.valueOf(obj[4]));
            fsrm.setZip(String.valueOf(obj[5]));
            fsrm.setActive(Integer.valueOf(String.valueOf(obj[6])));
            fsrm.setEmail(String.valueOf(obj[7]));
            String seasonName=String.valueOf(obj[10]);
            String[] seasonNames= seasonName.split(",");
            String [] seasonStatus= String.valueOf(obj[11]).split(",");
            String[] rdName=String.valueOf(obj[12]).split(",");
            int c=0;
            for(String sn:seasonNames)
            {
               RMSeasonContact rmSc=new RMSeasonContact();
               rmSc.setSeasonName(sn);
               Contact contact=new Contact();
               //contact.setFirstName(value);
               //rmSc.setRegionalDirector(value);
               //RMsc.setStatus\
              // RMsc.setRegionalDirector(value);
            }
            
           count++;
           
         }
      }
      return null;
   }

}

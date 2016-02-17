/**
 * 
 */
package com.ccighgo.service.components.fieldstaff.network.details;

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
import com.ccighgo.service.components.fieldstaff.details.listing.FieldStaffLeadershipDetailsInterfaceImpl;
import com.ccighgo.service.transport.fieldstaff.beans.fieldstaff.leadership.details.MyFieldStaff;
import com.ccighgo.service.transport.fieldstaff.beans.fieldstaff.leadership.details.MyFieldStaffLeadership;
import com.ccighgo.service.transport.fieldstaff.beans.fieldstaff.network.FSLNetwork;
import com.ccighgo.service.transport.fieldstaff.beans.fieldstaff.network.FieldStaffNetwork;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;

/**
 * @author ravi
 *
 */
@Component
public class FSDetailsInterfaceImpl implements FSDetailsInterface{
   
   @Autowired MessageUtils messageUtil;
   @Autowired CommonComponentUtils componentUtils;
   @Autowired EntityManager entityManager;

   private static final Logger LOGGER = Logger.getLogger(FieldStaffLeadershipDetailsInterfaceImpl.class);

   private static final String SP_FSL_DETAILS_LIST = "call SPFieldStaffFSLAndNetworkListing(?,?)";
   private static final String EMPTY = "";
   private static final Integer FIELD_STAFF_LIST = 0;
   private static final Integer FIELD_STAFF_NETWORK_LIST = 1;

   @Override
   public MyFieldStaffLeadership getMyFieldStaffLeadershipList(String fsGoId) {
      LOGGER.info("fsGoId: " + fsGoId);
      MyFieldStaffLeadership myList = new MyFieldStaffLeadership();
      try {
         if (fsGoId == null || Integer.valueOf(fsGoId) == 0 || Integer.valueOf(fsGoId) < 0) {
            throw new CcighgoException("invalid field staff id");
         }
         Query query = entityManager.createNativeQuery(SP_FSL_DETAILS_LIST);
         query.setParameter(1, Integer.valueOf(fsGoId));
         query.setParameter(2, FIELD_STAFF_LIST);// 0 for listing
         @SuppressWarnings("unchecked")
         List<Object[]> results = query.getResultList();
         if (results != null && results.size() > 0) {
            List<MyFieldStaff> myFieldStaffs = new ArrayList<MyFieldStaff>();
            int count = 0;
            // 0:goID, 1:firstName, 2:lastname, 3:email,4, current city,
            // 5:state, 6: seasons, 7:role/type, 8: programs
            for (Object[] obj : results) {
               MyFieldStaff staff = new MyFieldStaff();
               staff.setGoId(obj[0] != null ? Integer.valueOf(obj[0].toString()) : 0);
               staff.setFirstName(obj[1] != null ? obj[1].toString() : EMPTY);
               staff.setLastName(obj[2] != null ? obj[2].toString() : EMPTY);
               staff.setEmail(obj[3] != null ? obj[3].toString() : EMPTY);
               staff.setCity(obj[4] != null ? obj[4].toString() : EMPTY);
               staff.setState(obj[5] != null ? obj[5].toString() : EMPTY);
               staff.setSeason(obj[6] != null ? obj[6].toString() : EMPTY);
               staff.setType(obj[7] != null ? obj[7].toString() : EMPTY);
               staff.setProgram(obj[8] != null ? obj[8].toString() : EMPTY);
               staff.setPhoto(obj[9] != null ? obj[9].toString() : EMPTY);
               myFieldStaffs.add(staff);
               count++;
            }
            myList.setCount(count);
            myList.getMyFieldStaffs().addAll(myFieldStaffs);
            myList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            myList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(), messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         myList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return myList;
   }

   @Override
   public FieldStaffNetwork getFieldStaffNetworkList(String fsGoId) {
      LOGGER.info("fsGoId: "+fsGoId);
      FieldStaffNetwork networkList = new FieldStaffNetwork();
      try {
         if (fsGoId == null || Integer.valueOf(fsGoId) == 0 || Integer.valueOf(fsGoId) < 0) {
            throw new CcighgoException("invalid fieldstaff id");
         }
         Query query = entityManager.createNativeQuery(SP_FSL_DETAILS_LIST);
         query.setParameter(1, Integer.valueOf(fsGoId));
         query.setParameter(2, FIELD_STAFF_NETWORK_LIST);
         @SuppressWarnings("unchecked")
         List<Object[]> results = query.getResultList();
         // 0:goId, 1:first name, 2:last name, 3:city, 4:state, 5:FS type, 6:
         // home phone, 7:email, 8:date of inquiry, 9:status, 10:season status,
         // 11:submitted to cci, 12: season name
         if (results != null && results.size() > 0) {
            List<FSLNetwork> fslNetworks = new ArrayList<FSLNetwork>();
            int count = 0;
            for (Object[] obj : results) {
               FSLNetwork fslNetwork = new FSLNetwork();
               fslNetwork.setGoId(obj[0] != null ? Integer.valueOf(obj[0].toString()) : 0);
               fslNetwork.setFirstName(obj[1] != null ? obj[1].toString() : EMPTY);
               fslNetwork.setLastName(obj[2] != null ? obj[2].toString() : EMPTY);
               fslNetwork.setCity(obj[3] != null ? obj[3].toString() : EMPTY);
               fslNetwork.setState(obj[4] != null ? obj[4].toString() : EMPTY);
               fslNetwork.setType(obj[5] != null ? obj[5].toString() : EMPTY);
               fslNetwork.setHomePhone(obj[6] != null ? obj[6].toString() : EMPTY);
               fslNetwork.setEmail(obj[7] != null ? obj[7].toString() : EMPTY);
               fslNetwork.setDateOfInquiry(obj[8] != null ? DateUtils.getFormattedStringDate(obj[8].toString()) : EMPTY);
               fslNetwork.setStatus(obj[9] != null ? obj[9].toString() : EMPTY);
               fslNetwork.setSeasonStatus(obj[10] != null ? obj[10].toString() : EMPTY);
               fslNetwork.setDateSubmittedToCCI(obj[11] != null ? DateUtils.getFormattedStringDate(obj[11].toString()) : EMPTY);
               fslNetwork.setSeasons(obj[10] != null ? obj[10].toString() : EMPTY);
               fslNetwork.setPhoto(obj[13] != null ? obj[13].toString() : EMPTY);
               fslNetworks.add(fslNetwork);
               count++;
            }
            networkList.setCount(count);
            networkList.getFslNetworks().addAll(fslNetworks);
            networkList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            networkList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         networkList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return networkList;
   }


}

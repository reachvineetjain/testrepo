/**
 * 
 */
package com.ccighgo.service.components.fieldstaff.details.listing;

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
import com.ccighgo.service.transport.fieldstaff.beans.fieldstaff.network.details.FSLNetwork;
import com.ccighgo.service.transport.fieldstaff.beans.fieldstaff.network.details.FieldStaffNetworkList;
import com.ccighgo.service.transport.fieldstaff.beans.myfieldstaff.leadership.details.MyFieldStaff;
import com.ccighgo.service.transport.fieldstaff.beans.myfieldstaff.leadership.details.MyFieldStaffLeadershipList;
import com.ccighgo.utils.CCIConstants;

/**
 * @author ravi
 *
 */
@Component
public class FieldStaffLeadershipDetailsInterfaceImpl implements FieldStaffLeadershipDetailsInterface {

   @Autowired MessageUtils messageUtil;
   @Autowired CommonComponentUtils componentUtils;
   @Autowired EntityManager entityManager;

   private static final Logger LOGGER = Logger.getLogger(FieldStaffLeadershipDetailsInterfaceImpl.class);

   private static final String SP_FSL_DETAILS_LIST = "call SPFieldStaffFSLAndNetworkListing(?,?)";

   @Override
   public MyFieldStaffLeadershipList getMyFieldStaffLeadershipList(String fsGoId) {
      MyFieldStaffLeadershipList myList = new MyFieldStaffLeadershipList();
      try {
         if (fsGoId == null || Integer.valueOf(fsGoId) == 0 || Integer.valueOf(fsGoId) < 0) {
            throw new CcighgoException("invalid fieldstaff id");
         }
         Query query = entityManager.createNativeQuery(SP_FSL_DETAILS_LIST);
         query.setParameter(1, Integer.valueOf(fsGoId));
         query.setParameter(2, 0);// 0 for listing
         @SuppressWarnings("unchecked")
         List<Object[]> results = query.getResultList();
         if (results != null && results.size() > 0) {
            List<MyFieldStaff> myFieldStaffs = new ArrayList<MyFieldStaff>();
            int count = 0;
            //0:goID, 1:firstName, 2:lastname, 3:email,4, current city, 5:state, 6: seasons, 7:role/type
            for (Object[] obj : results) {
               MyFieldStaff staff = new MyFieldStaff();
               staff.setFsLcGoId(Integer.valueOf(obj[0].toString()));
               staff.setFirstName(obj[1].toString());
               staff.setLastName(obj[2].toString());
               staff.setEmail(obj[3].toString());
               staff.setCity(obj[4].toString());
               staff.setState(obj[5].toString());
               staff.setSeason(obj[6].toString());
               staff.setRoleType(obj[7].toString());
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
   public FieldStaffNetworkList getFieldStaffNetworkList(String fsGoId) {
      FieldStaffNetworkList networkList = new FieldStaffNetworkList();
      try {
         if (fsGoId == null || Integer.valueOf(fsGoId) == 0 || Integer.valueOf(fsGoId) < 0) {
            throw new CcighgoException("invalid fieldstaff id");
         }
         Query query = entityManager.createNativeQuery(SP_FSL_DETAILS_LIST);
         query.setParameter(1, Integer.valueOf(fsGoId));
         query.setParameter(2, 1);// 1 for network
         @SuppressWarnings("unchecked")
         List<Object[]> results = query.getResultList();
         //0:goId, 1:first name, 2:last name, 3:city, 4:state, 5:FS type, 6: home phone, 7:email, 8:date of inquiry, 9:status, 10:season status, 11:submitted to cci, 12: season name
         if (results != null && results.size() > 0) {
            List<FSLNetwork> fslNetworks = new ArrayList<FSLNetwork>();
            int count = 0;
            for (Object[] obj : results) {
               FSLNetwork fslNetwork = new FSLNetwork();
               fslNetwork.setFsGoId(Integer.valueOf(obj[0].toString()));
               fslNetwork.setFirstName(obj[1].toString());
               fslNetwork.setLastName(obj[2].toString());
               fslNetwork.setCity(obj[3].toString());
               fslNetwork.setState(obj[4].toString());
               fslNetwork.setType(obj[5].toString());
               fslNetwork.setHomePhone(obj[6].toString());
               fslNetwork.setEmail(obj[7].toString());
               fslNetwork.setDateOfInquiry(obj[8].toString());
               fslNetwork.setStatus(obj[9].toString());
               fslNetwork.setSeasonStatus(obj[10].toString());
               fslNetwork.setDateSubmittedToCCI(obj[11].toString());
               fslNetwork.setSeasons(obj[12].toString());
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

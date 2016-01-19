package com.ccighgo.service.components.fieldstaffs.placements;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.fieldstaff.listing.FieldStaffListingInterface;
import com.ccighgo.service.transport.fieldstaff.beans.placement.adminerdplacement.AdminERDPlacements;
import com.ccighgo.service.transport.fieldstaff.beans.placement.erdplacement.ERDPlacements;
import com.ccighgo.service.transport.partner.beans.fieldstaff.placement.myplacement.MyPlacements;

@Component
public class FieldStaffMyPlacementsImpl implements FieldStaffMyPlacementsInterface {

   private static final Logger LOGGER = Logger.getLogger(FieldStaffMyPlacementsInterface.class);
   @Autowired
   EntityManager em;
   @Autowired
   MessageUtils messageUtil;
   @Autowired
   CommonComponentUtils componentUtils;
   
   
   private static final String SP_FS_PLACEMENT_LIST = "CALL SPFieldStaffParticipantListing(?,?,?)";
   private static final int MY_PARTICIPANT_FLAG=0;
   private static final int MY_TEAM_PARTICIPANT_FLAG=1;
   @Override
   public MyPlacements getAll() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public AdminERDPlacements getAdminERDPlacementMyParticipant(String goId, String catagories) {
      Query query = em.createNativeQuery(SP_FS_PLACEMENT_LIST);
      query.setParameter(1, goId);
      query.setParameter(2, MY_PARTICIPANT_FLAG);
      query.setParameter(3, catagories);
      return null;
   }

   @Override
   public AdminERDPlacements getAdminERDPlacementMyTeamParticipant(String goId, String catagories) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public ERDPlacements getERDPlacementMyParticipant(String goId, String catagories) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public ERDPlacements getERDPlacementMyTeamParticipant(String goId, String catagories) {
      // TODO Auto-generated method stub
      return null;
   }

}

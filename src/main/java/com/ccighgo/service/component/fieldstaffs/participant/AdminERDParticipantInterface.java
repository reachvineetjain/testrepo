package com.ccighgo.service.component.fieldstaffs.participant;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.fieldstaff.beans.adminerdparticipant.AdminERDParticipants;
import com.ccighgo.service.transport.fieldstaff.beans.placement.adminerdplacement.AdminERDPlacements;
import com.ccighgo.service.transport.partner.beans.fieldstaff.placement.adminerdmyplacement.AdminMyPlacements;
/**
 * @author sinshaw.demisse
 *
 */
@Service
public interface AdminERDParticipantInterface {
   /**
    * @return FieldStaffParticipants
    */
   public AdminERDParticipants getAll(String goId,String catagories);
   /**
    * @param goId
    * @return FieldStaffParticipants
    */
   public AdminERDParticipants getMyTeam(String goId, String catagories);
   /**
    * @param goId
    * @param catagories
    * @return
    */
   public AdminMyPlacements getERDMyPlacement(String goId, String catagories);
   /**
    * @param goId
    * @param catagories
    * @return
    */
   public AdminERDPlacements getERDPlacementMyTeamPlacement(String goId, String catagories);
   
  
}

package com.ccighgo.service.components.fieldstaffs.placements;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.fieldstaff.beans.placement.adminerdplacement.AdminERDPlacement;
import com.ccighgo.service.transport.fieldstaff.beans.placement.adminerdplacement.AdminERDPlacements;
import com.ccighgo.service.transport.fieldstaff.beans.placement.erdplacement.ERDPlacements;
import com.ccighgo.service.transport.partner.beans.fieldstaff.placement.myplacement.MyPlacements;

/**
 * @author sinshaw.demisse
 *
 */
@Service
public interface FieldStaffMyPlacementsInterface {
   /**
    * @return list of MyPlacement
    */
   MyPlacements getAll();
   
   AdminERDPlacements getAdminERDPlacementMyParticipant(String goId, String catagories);
   
   AdminERDPlacements getAdminERDPlacementMyTeamParticipant(String goId, String catagories);
   
   ERDPlacements getERDPlacementMyParticipant(String goId, String catagories);
   
   ERDPlacements getERDPlacementMyTeamParticipant(String goId, String catagories);
}

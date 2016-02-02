package com.ccighgo.service.components.fieldstaffs.placements;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.fieldstaff.beans.erdparticipant.ERDParticipants;
import com.ccighgo.service.transport.fieldstaff.beans.placement.erdplacement.ERDPlacements;
import com.ccighgo.service.transport.fieldstaff.beans.placement.participants.ERDPlacementParticipants;
import com.ccighgo.service.transport.partner.beans.fieldstaff.placement.myplacement.MyPlacements;

/**
 * @author sinshaw.demisse
 *
 */
@Service
public interface FieldStaffMyPlacementsInterface {
   /**
    * @param goId
    * @param catagories
    * @return
    */
   MyPlacements getERDMyPlacement(String goId, String catagories);
   
   /**
    * @param goId
    * @param catagories
    * @return
    */
   ERDPlacementParticipants getERDPlacementParticipant(String goId, String catagories);
   
   /**
    * @param goId
    * @param catagories
    * @return
    */
   ERDPlacements getERDPlacementMyTeamPlacement(String goId, String catagories); 
   /**
    * 
    * @return FieldStaffParticipants
    */
   public ERDParticipants getAllParticipant(String goId, String catagorie);
   /**
    * 
    * @param goId
    * @return FieldStaffParticipants
    */
   public ERDParticipants getMyTeamParticipant(String goId,String catagorie);
}

package com.ccighgo.service.components.fieldstaffs.myplacements;

import org.springframework.stereotype.Service;

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
}

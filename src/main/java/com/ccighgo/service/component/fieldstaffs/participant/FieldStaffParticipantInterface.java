package com.ccighgo.service.component.fieldstaffs.participant;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.fieldstaff.beans.fieldstaffparticipant.FieldStaffParticipants;

/**
 * @author sinshaw.demisse
 *
 */
@Service
public interface FieldStaffParticipantInterface {
   /**
    * @return FieldStaffParticipants
    */
   public FieldStaffParticipants getAll();
   /**
    * @param goId
    * @return FieldStaffParticipants
    */
   public FieldStaffParticipants getMyTeam(String goId);
}

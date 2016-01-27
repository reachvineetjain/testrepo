/**
 * 
 */
package com.ccighgo.service.components.fieldstaff.season;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.fieldstaff.beans.season.details.FieldStaffAdminSeasonDetails;
import com.ccighgo.service.transport.fieldstaff.beans.seasons.FieldStaffSeasons;
import com.ccighgo.service.transport.utility.beans.fs.status.list.FieldStaffStatusList;
import com.ccighgo.service.transport.utility.beans.payment.schedule.PaymentScheduleList;

/**
 * @author ravi
 *
 */
@Service
public interface FieldStaffSeasonService {

   /**
    * @param fsGoId
    * @return
    */
   public FieldStaffSeasons getFieldStaffSeasons(String fsGoId);

   /**
    * @param fslSeasonId
    * @param seasonId
    * @param deparmentProgramId
    * @param statusVal
    * @return
    */
   public Response updateSignedContract(String fslSeasonId, String seasonId, String deparmentProgramId, String statusVal);

   /**
    * @param fieldStaffGoId
    * @param fsSeasonId
    * @return
    */
   public FieldStaffAdminSeasonDetails getFSSeasonDetails(String fieldStaffGoId, String fsSeasonId);

   /**
    * @return
    */
   public FieldStaffStatusList getFieldStaffStatusList();

   /**
    * @return
    */
   public PaymentScheduleList getPaymentScheduleList();

   /**
    * @param details
    * @return
    */
   public FieldStaffAdminSeasonDetails updateFieldStaffAdminSeasonDetails(FieldStaffAdminSeasonDetails details);

   /**
    * @param fsSeasonId
    * @return
    */
   public Response deleteFSAdminSeason(String fsSeasonId);

   /**
    * @param fsSeasonId
    * @return
    */
   public Response deactivateFSAdminSeason(String fsSeasonId);

}

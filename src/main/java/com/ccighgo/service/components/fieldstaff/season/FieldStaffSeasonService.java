/**
 * 
 */
package com.ccighgo.service.components.fieldstaff.season;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.fieldstaff.beans.seasons.FieldStaffSeasons;

/**
 * @author ravi
 *
 */
@Service
public interface FieldStaffSeasonService {

   FieldStaffSeasons getFieldStaffSeasons(String fsGoId);

   Response updateSignedContract(String fslSeasonId, String seasonId, String deparmentProgramId, String statusVal);

}

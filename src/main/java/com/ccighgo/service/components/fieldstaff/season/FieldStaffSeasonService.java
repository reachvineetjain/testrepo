/**
 * 
 */
package com.ccighgo.service.components.fieldstaff.season;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.fieldstaff.beans.seasons.FieldStaffSeasons;

/**
 * @author ravi
 *
 */
@Service
public interface FieldStaffSeasonService {

   FieldStaffSeasons getFieldStaffSeasons(Integer fsGoId);

}

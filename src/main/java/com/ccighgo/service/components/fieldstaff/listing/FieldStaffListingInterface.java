/**
 * 
 */
package com.ccighgo.service.components.fieldstaff.listing;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.fieldstaff.beans.ac.season.contacts.FSACSeasonContacts;
import com.ccighgo.service.transport.fieldstaff.beans.erd.seasons.FSERDSeasons;
import com.ccighgo.service.transport.fieldstaff.beans.fslist.FieldStaffList;
import com.ccighgo.service.transport.fieldstaff.beans.fstypes.FieldStaffTypes;
import com.ccighgo.service.transport.fieldstaff.beans.lc.season.contacts.FSLCSeasonContacts;
import com.ccighgo.service.transport.fieldstaff.beans.rd.season.contacts.FSRDSeasonContacts;
import com.ccighgo.service.transport.fieldstaff.beans.rm.season.contacts.FSRMSeasonContacts;

/**
 * @author ravi
 *
 */
@Service
public interface FieldStaffListingInterface {

	/**
	 * @param typeId
	 * @return
	 */
	public FieldStaffList getFieldStaffList(String typeId);

	/**
	 * @return
	 */
	public FieldStaffTypes getFieldStaffTypes();

	/**
	 * @param goId
	 * @return
	 */
	public FSLCSeasonContacts getFSLCSeasonContacts(String goId);

	/**
	 * @param goId
	 * @return
	 */
	public FSACSeasonContacts getFSACSeasonContacts(String goId);

	/**
	 * @param goId
	 * @return
	 */
	public FSRDSeasonContacts getFSRDSeasonContacts(String goId);

	/**
	 * @param goId
	 * @return
	 */
	public FSRMSeasonContacts getFSRMSeasonContacts(String goId);

	/**
	 * @param goId
	 * @return
	 */
	public FSERDSeasons getERDSeasons(String goId);

}

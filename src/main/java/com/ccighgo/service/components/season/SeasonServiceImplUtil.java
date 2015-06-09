/**
 * 
 */
package com.ccighgo.service.components.season;

import com.ccighgo.db.entities.Season;
import com.ccighgo.service.transport.seasons.beans.season.SeasonBean;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonListObject;
import com.ccighgo.utils.CCIConstants;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
public class SeasonServiceImplUtil {

	/**
	 * @param seasonBean
	 * @param seasonEntity
	 */
	public static void convertEntitySeasonToBeanSeason(SeasonBean seasonBean,Season seasonEntity) {
			seasonBean.setDepartmentId(seasonEntity.getDepartment() != null ?seasonEntity.getDepartment().getDepartmentId()  : -1);
			seasonBean.setSeasonName(seasonEntity.getSeasonName() != null?seasonEntity.getSeasonName():CCIConstants.EMPTY_DATA);
			seasonBean.setStatus(seasonEntity.getSeasonStatus() != null
					&& seasonEntity.getSeasonStatus().getStatus() != null ?seasonEntity.getSeasonStatus().getStatus():CCIConstants.EMPTY_DATA);
	}

	/**
	 * @param seasonBean
	 * @param seasonEntity
	 */
	public static void convertEntitySeasonToSeasonListObject(SeasonListObject seasonBean, Season seasonEntity) {
		seasonBean.setSeasonId(seasonEntity.getSeasonId());
		seasonBean.setStatus(seasonEntity.getSeasonStatus() != null
				&& seasonEntity.getSeasonStatus().getStatus() != null ?seasonEntity.getSeasonStatus().getStatus():CCIConstants.EMPTY_DATA);
		
	}
}

/**
 * 
 */
package com.ccighgo.service.components.season;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Department;
import com.ccighgo.db.entities.Season;
import com.ccighgo.db.entities.SeasonHSPConfiguration;
import com.ccighgo.db.entities.SeasonStatus;
import com.ccighgo.jpa.repositories.SeasonStatusRepository;
import com.ccighgo.service.transport.seasons.beans.season.SeasonBean;
import com.ccighgo.service.transport.seasons.beans.seasonslist.DepartmentObject;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonListObject;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.ValidationUtils;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
@Component
public class SeasonServiceImplUtil {

	@Autowired
	SeasonStatusRepository seasonStatusRepository;
	/**
	 * @param seasonBean
	 * @param seasonEntity
	 */
	public void convertEntitySeasonToBeanSeason(SeasonBean seasonBean,Season seasonEntity) {
			seasonBean.setDepartmentId(seasonEntity.getDepartment() != null ? seasonEntity.getDepartment().getDepartmentId()  : -1);
			seasonBean.setSeasonName(seasonEntity.getSeasonName() != null? seasonEntity.getSeasonName():CCIConstants.EMPTY_DATA);
			seasonBean.setStatus(seasonEntity.getSeasonStatus() != null
					&& seasonEntity.getSeasonStatus().getStatus() != null ? seasonEntity.getSeasonStatus().getStatus():CCIConstants.EMPTY_DATA);
			if(seasonEntity.getSeasonHspconfigurations()!=null)
			for (SeasonHSPConfiguration seasonconf : seasonEntity.getSeasonHspconfigurations()) {
				if(seasonconf.getSeason()!=null)
				if(seasonconf.getSeason().getSeasonId() == seasonEntity.getSeasonId())
					seasonBean.setStartDate(seasonconf.getSeasonStartDate()!=null ? seasonconf.getSeasonStartDate().toString():CCIConstants.EMPTY_DATA);
					seasonBean.setEndDate(seasonconf.getSeasonStartDate()!=null ? seasonconf.getSeasonEndDate().toString():CCIConstants.EMPTY_DATA);
			}
	}

	/**
	 * @param seasonBean
	 * @param seasonEntity
	 */
	public void convertEntitySeasonToSeasonListObject(SeasonListObject seasonBean, Season seasonEntity) {
		seasonBean.setSeasonId(seasonEntity.getSeasonId());
		seasonBean.setStatus(seasonEntity.getSeasonStatus() != null
				&& seasonEntity.getSeasonStatus().getStatus() != null ?seasonEntity.getSeasonStatus().getStatus():CCIConstants.EMPTY_DATA);
		seasonBean.setDepartment(getDepartmentBean(seasonEntity.getDepartment()));
		if(seasonEntity.getSeasonHspconfigurations()!=null)
			for (SeasonHSPConfiguration seasonconf : seasonEntity.getSeasonHspconfigurations()) {
				if(seasonconf.getSeason()!=null)
				if(seasonconf.getSeason().getSeasonId() == seasonEntity.getSeasonId())
					seasonBean.setStartDate(seasonconf.getSeasonStartDate()!=null ? seasonconf.getSeasonStartDate().toString():CCIConstants.EMPTY_DATA);
					seasonBean.setEndDate(seasonconf.getSeasonStartDate()!=null ? seasonconf.getSeasonEndDate().toString():CCIConstants.EMPTY_DATA);
			}
	}

	/**
	 * @param department
	 * @return
	 */
	private DepartmentObject getDepartmentBean(Department department) {
		if(department!=null){
			DepartmentObject departmentObject =new DepartmentObject();
			departmentObject.setDepartmentCode(department.getAcronym());
			departmentObject.setDepartmentId(department.getDepartmentId());
			departmentObject.setDepartmentName(department.getDepartmentName());
			return departmentObject;
		}
		return null;
	}

	/**
	 * @param seasonBean
	 * @param seasonEntity
	 */
	public void convertBeanSeasonToEntitySeason(SeasonBean seasonBean,
			Season seasonEntity) {
		ValidationUtils.validateRequired(seasonBean.getDepartmentId()+"");
		Department department = new Department();
		department.setDepartmentId(seasonBean.getDepartmentId());
		seasonEntity.setDepartment(department);
		ValidationUtils.validateRequired(seasonBean.getSeasonName());
		seasonEntity.setSeasonName(seasonBean.getSeasonName());
		
//		SeasonStatus seasonStatus=new SeasonStatus();
//		seasonStatus.setSeasonStatusId(seasonBean.getStatus());
//		seasonEntity.setSeasonStatus(seasonStatus);
	}
}

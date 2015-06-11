/**
 * 
 */
package com.ccighgo.service.components.season;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Department;
import com.ccighgo.db.entities.Season;
import com.ccighgo.db.entities.SeasonHSPConfiguration;
import com.ccighgo.db.entities.SeasonStatus;
import com.ccighgo.jpa.repositories.DepartmentRepository;
import com.ccighgo.jpa.repositories.SeasonHSPConfigurationRepsitory;
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
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	SeasonHSPConfigurationRepsitory seasonHSPConfigurationRepsitory;
	
	
	/**
	 * @param seasonBean
	 * @param seasonEntity
	 */
	public void convertEntitySeasonToBeanSeason(SeasonBean seasonBean,Season seasonEntity) {
			seasonBean.setDepartmentId(seasonEntity.getDepartment() != null ? seasonEntity.getDepartment().getDepartmentId()  : -1);
			seasonBean.setSeasonName(seasonEntity.getSeasonName() != null? seasonEntity.getSeasonName():CCIConstants.EMPTY_DATA);
			if(seasonEntity.getSeasonStatus() != null){
				seasonBean.setStatus(seasonEntity.getSeasonStatus().getStatus());
				seasonBean.setStatusId(seasonEntity.getSeasonStatus().getSeasonStatusId());
			}
			if(seasonEntity.getSeasonHspconfigurations()!=null)
			for (SeasonHSPConfiguration seasonconf : seasonEntity.getSeasonHspconfigurations()) {
				if(seasonconf.getSeason()!=null)
				if(seasonconf.getSeason().getSeasonId() == seasonEntity.getSeasonId()){
					seasonBean.setStartDate(seasonconf.getSeasonStartDate()!=null ? seasonconf.getSeasonStartDate().toString():CCIConstants.EMPTY_DATA);
					seasonBean.setEndDate(seasonconf.getSeasonStartDate()!=null ? seasonconf.getSeasonEndDate().toString():CCIConstants.EMPTY_DATA);
					seasonBean.setSeasonHSPConfigurationId(seasonconf.getSeasonHSPConfigurationId());
				}
			}
	}

	/**
	 * @param seasonBean
	 * @param seasonEntity
	 */
	public void convertEntitySeasonToSeasonListObject(SeasonListObject seasonBean, Season seasonEntity) {
		seasonBean.setSeasonId(seasonEntity.getSeasonId());
		seasonBean.setSeasonName(seasonEntity.getSeasonName());
		if(seasonEntity.getSeasonStatus() != null){
			seasonBean.setStatus(seasonEntity.getSeasonStatus().getStatus());
			seasonBean.setStatusId(seasonEntity.getSeasonStatus().getSeasonStatusId());
		}
		seasonBean.setDepartment(getDepartmentBean(seasonEntity.getDepartment()));
		if(seasonEntity.getSeasonHspconfigurations()!=null)
			for (SeasonHSPConfiguration seasonconf : seasonEntity.getSeasonHspconfigurations()) {
				if(seasonconf.getSeason()!=null)
				if(seasonconf.getSeason().getSeasonId() == seasonEntity.getSeasonId())
					seasonBean.setStartDate(seasonconf.getSeasonStartDate()!=null ? seasonconf.getSeasonStartDate().toString():CCIConstants.EMPTY_DATA);
					seasonBean.setEndDate(seasonconf.getSeasonStartDate()!=null ? seasonconf.getSeasonEndDate().toString():CCIConstants.EMPTY_DATA);
					seasonBean.setSeasonHSPConfigurationId(seasonconf.getSeasonHSPConfigurationId());
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
	public void convertSeasonBeanToSeasonEntity(SeasonBean seasonBean,
			Season seasonEntity) {
		ValidationUtils.validateRequired(seasonBean.getDepartmentId()+"");
		
		Department department = departmentRepository.findOne(seasonBean.getDepartmentId());
		seasonEntity.setDepartment(department);
		
		ValidationUtils.validateRequired(seasonBean.getSeasonName());
		seasonEntity.setSeasonName(seasonBean.getSeasonName());
		
		ValidationUtils.validateRequired(seasonBean.getStatusId()+"");
		SeasonStatus seasonStatus = seasonStatusRepository.findOne(seasonBean.getStatusId());
		seasonEntity.setSeasonStatus(seasonStatus);
		
		/* TODO start date && End Date from HSP
		 * Inject HSP and retrieve values from database then assign it to the season entity
		 */
	}
}

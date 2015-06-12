/**
 * 
 */
package com.ccighgo.service.components.season;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.ccighgo.utils.DateAdapter;
import com.ccighgo.utils.DateUtils;
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
					seasonBean.setStartDate(DateUtils.getMMddYyyyString(seasonconf.getSeasonStartDate()));
					seasonBean.setEndDate(DateUtils.getMMddYyyyString(seasonconf.getSeasonEndDate()));
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
					seasonBean.setStartDate(DateUtils.getMMddYyyyString(seasonconf.getSeasonStartDate()));
					seasonBean.setEndDate(DateUtils.getMMddYyyyString(seasonconf.getSeasonEndDate()));
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
		seasonEntity.setCreatedBy(1);
		seasonEntity.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
		seasonEntity.setModifiedBy(1);
		seasonEntity.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
		seasonEntity.setSeasonFullName(seasonBean.getSeasonName());
	}

	public void createSeasonHspConfiguration(SeasonBean seasonBean,
			Season seasonEntity) {
		SeasonHSPConfiguration seasonHSPConfiguration  = new SeasonHSPConfiguration();
		seasonHSPConfiguration.setSeason(seasonEntity);
		seasonHSPConfiguration.setSeasonEndDate(DateUtils.getDateFromString(seasonBean.getEndDate()));
		seasonHSPConfiguration.setSeasonStartDate(DateUtils.getDateFromString(seasonBean.getStartDate()));
//		seasonHSPConfigurationRepsitory.saveAndFlush(seasonHSPConfiguration);
	}
}

/**
 * 
 */
package com.ccighgo.service.components.season;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.DepartmentProgram;
import com.ccighgo.db.entities.DepartmentProgramOption;
import com.ccighgo.db.entities.LookupDepartment;
import com.ccighgo.db.entities.Season;
import com.ccighgo.db.entities.SeasonHSPConfiguration;
import com.ccighgo.db.entities.SeasonStatus;
import com.ccighgo.jpa.repositories.DepartmentRepository;
import com.ccighgo.jpa.repositories.SeasonHSPConfigurationRepsitory;
import com.ccighgo.jpa.repositories.SeasonStatusRepository;
import com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatuses;
import com.ccighgo.service.transport.seasons.beans.season.SeasonBean;
import com.ccighgo.service.transport.seasons.beans.seasonslist.DepartmentObject;
import com.ccighgo.service.transport.seasons.beans.seasonslist.ProgramOptions;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonListObject;
import com.ccighgo.utils.CCIConstants;
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
			seasonBean.setSeasonId(seasonEntity.getSeasonId());
			seasonBean.setDepartmentId(seasonEntity.getLookupDepartment() != null ? seasonEntity.getLookupDepartment().getDepartmentId()  : -1);
			seasonBean.setSeasonName(seasonEntity.getSeasonName() != null? seasonEntity.getSeasonName():CCIConstants.EMPTY_DATA);
			seasonBean.setStatus(seasonEntity.getSeasonStatus() != null?seasonEntity.getSeasonStatus().getStatus():CCIConstants.EMPTY_DATA);
			seasonBean.setStatusId(seasonEntity.getSeasonStatus() != null?seasonEntity.getSeasonStatus().getSeasonStatusId():CCIConstants.EMPTY_INTEGER_FIELD);
			
			String startDate =CCIConstants.EMPTY_DATA,endDate =CCIConstants.EMPTY_DATA;
			Integer seasonHspConfId =CCIConstants.EMPTY_INTEGER_FIELD;
			
			if(seasonEntity.getSeasonHspconfigurations()!=null)
				for (SeasonHSPConfiguration seasonconf : seasonEntity.getSeasonHspconfigurations()) {
					if(seasonconf.getSeason()!=null)
						if(seasonconf.getSeason().getSeasonId() == seasonEntity.getSeasonId()){
							startDate = DateUtils.getMMddYyyyString(seasonconf.getSeasonStartDate());
							endDate = DateUtils.getMMddYyyyString(seasonconf.getSeasonEndDate());
							seasonHspConfId = seasonconf.getSeasonHSPConfigurationId();
						}
					}
			seasonBean.setStartDate(startDate);
			seasonBean.setEndDate(endDate);
			seasonBean.setSeasonHSPConfigurationId(seasonHspConfId);
			
				if(seasonEntity.getLookupDepartment()!=null){
					if(seasonEntity.getLookupDepartment().getDepartmentPrograms()!=null){
						for (DepartmentProgram departmentProgram : seasonEntity.getLookupDepartment().getDepartmentPrograms()) {
							if(departmentProgram.getLookupDepartment().getDepartmentId()==seasonEntity.getLookupDepartment().getDepartmentId()&&departmentProgram.getDepartmentProgramOptions()!=null){
								for (DepartmentProgramOption departmentProgramOption : departmentProgram.getDepartmentProgramOptions()) {
									if(departmentProgramOption.getDepartmentProgram().getDepartmentProgramId()==departmentProgram.getDepartmentProgramId())
										seasonBean.getProgramOptions().add(mapProgramOptionEntityToBean2(departmentProgramOption));
								}
							}
						}
					}
				}
	}

	private com.ccighgo.service.transport.seasons.beans.season.ProgramOptions mapProgramOptionEntityToBean2(
			DepartmentProgramOption departmentProgramOption) {
		com.ccighgo.service.transport.seasons.beans.season.ProgramOptions programOptions =new com.ccighgo.service.transport.seasons.beans.season.ProgramOptions();
		programOptions.setProgramOptionsID(departmentProgramOption.getDepartmentProgramOptionId());
		programOptions.setProgramOptionsCode(departmentProgramOption.getProgramOptionCode()!=null?departmentProgramOption.getProgramOptionCode():CCIConstants.EMPTY_DATA);
		programOptions.setProgramOptionsName(departmentProgramOption.getProgramOptionName()!=null?departmentProgramOption.getProgramOptionName():CCIConstants.EMPTY_DATA);
		return programOptions;
	}

	/**
	 * @param seasonBean
	 * @param seasonEntity
	 */
	public void convertEntitySeasonToSeasonListObject(SeasonListObject seasonBean, Season seasonEntity) {
		seasonBean.setSeasonId(seasonEntity.getSeasonId());
		seasonBean.setSeasonName(seasonEntity.getSeasonName());
		seasonBean.setStatus(seasonEntity.getSeasonStatus() != null?seasonEntity.getSeasonStatus().getStatus():CCIConstants.EMPTY_DATA);
		seasonBean.setStatusId(seasonEntity.getSeasonStatus() != null?seasonEntity.getSeasonStatus().getSeasonStatusId():CCIConstants.EMPTY_INTEGER_FIELD);
		seasonBean.setDepartment(getDepartmentBean(seasonEntity.getLookupDepartment()));
		String startDate =CCIConstants.EMPTY_DATA,endDate =CCIConstants.EMPTY_DATA;
		Integer seasonHspConfId =CCIConstants.EMPTY_INTEGER_FIELD;
		
		if(seasonEntity.getSeasonHspconfigurations()!=null)
			for (SeasonHSPConfiguration seasonconf : seasonEntity.getSeasonHspconfigurations()) {
				if(seasonconf.getSeason()!=null)
					if(seasonconf.getSeason().getSeasonId() == seasonEntity.getSeasonId()){
						startDate = DateUtils.getMMddYyyyString(seasonconf.getSeasonStartDate());
						endDate = DateUtils.getMMddYyyyString(seasonconf.getSeasonEndDate());
						seasonHspConfId = seasonconf.getSeasonHSPConfigurationId();
					}
				}
		seasonBean.setStartDate(startDate);
		seasonBean.setEndDate(endDate);
		seasonBean.setSeasonHSPConfigurationId(seasonHspConfId);
		
		if(seasonEntity.getLookupDepartment()!=null){
			if(seasonEntity.getLookupDepartment().getDepartmentPrograms()!=null){
				for (DepartmentProgram departmentProgram : seasonEntity.getLookupDepartment().getDepartmentPrograms()) {
					if(departmentProgram.getLookupDepartment().getDepartmentId()==seasonEntity.getLookupDepartment().getDepartmentId()&&departmentProgram.getDepartmentProgramOptions()!=null){
						for (DepartmentProgramOption departmentProgramOption : departmentProgram.getDepartmentProgramOptions()) {
							if(departmentProgramOption.getDepartmentProgram().getDepartmentProgramId()==departmentProgram.getDepartmentProgramId())
								seasonBean.getProgramOptions().add(mapProgramOptionEntityToBean(departmentProgramOption));
						}
					}
				}
			}
		}
		
	}
	
	private ProgramOptions mapProgramOptionEntityToBean(
			DepartmentProgramOption departmentProgramOption) {
		ProgramOptions programOptions =new ProgramOptions();
		programOptions.setProgramOptionsID(departmentProgramOption.getDepartmentProgramOptionId());
		programOptions.setProgramOptionsCode(departmentProgramOption.getProgramOptionCode()!=null?departmentProgramOption.getProgramOptionCode():CCIConstants.EMPTY_DATA);
		programOptions.setProgramOptionsName(departmentProgramOption.getProgramOptionName()!=null?departmentProgramOption.getProgramOptionName():CCIConstants.EMPTY_DATA);
		return programOptions;
	}

	/**
	 * @param department
	 * @return
	 */
	private DepartmentObject getDepartmentBean(LookupDepartment department) {
		if(department!=null){
			DepartmentObject departmentObject =new DepartmentObject();
			departmentObject.setDepartmentId(department.getDepartmentId());
			departmentObject.setDepartmentCode(department.getAcronym()!=null?department.getAcronym():CCIConstants.EMPTY_DATA);
			departmentObject.setDepartmentName(department.getDepartmentName()!=null?department.getAcronym():CCIConstants.EMPTY_DATA);
			return departmentObject;
		}
		return null;
	}

	/**
	 * @param seasonBean
	 * @param seasonEntity
	 * @param update 
	 */
	public void convertSeasonBeanToSeasonEntity(SeasonBean seasonBean,
			Season seasonEntity, boolean update) {
		if(update){
			ValidationUtils.validateRequired(seasonBean.getSeasonId()+"");
			seasonEntity.setSeasonId(seasonBean.getSeasonId());
		}
		ValidationUtils.validateRequired(seasonBean.getDepartmentId()+"");
		LookupDepartment department = departmentRepository.findOne(seasonBean.getDepartmentId());
		seasonEntity.setLookupDepartment(department);
		
		ValidationUtils.validateRequired(seasonBean.getSeasonName());
		seasonEntity.setSeasonName(seasonBean.getSeasonName());
		
		SeasonStatus seasonStatus = seasonStatusRepository.findOne(CCIConstants.DRAFT_STATUS_NO);
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
		seasonHSPConfigurationRepsitory.saveAndFlush(seasonHSPConfiguration);
	}

	public void updateSeasonHspConfiguration(SeasonBean seasonBean,
			Season seasonEntity) {
		SeasonHSPConfiguration seasonHSPConfiguration  = new SeasonHSPConfiguration();
		seasonHSPConfiguration.setSeason(seasonEntity);
		seasonHSPConfiguration.setSeasonEndDate(DateUtils.getDateFromString(seasonBean.getEndDate()));
		seasonHSPConfiguration.setSeasonStartDate(DateUtils.getDateFromString(seasonBean.getStartDate()));
		seasonHSPConfiguration.setSeasonHSPConfigurationId(seasonBean.getSeasonHSPConfigurationId());
		seasonHSPConfigurationRepsitory.saveAndFlush(seasonHSPConfiguration);
	}
	
	public SeasonStatuses getSeasonStatus(){
	   SeasonStatuses seasonStatuses = null;
	   Sort sort = new Sort(Sort.Direction.ASC, "status");
	   List<SeasonStatus> seasonStatusDBList = seasonStatusRepository.findAll(sort);
	   if(seasonStatusDBList!=null){
	      seasonStatuses = new SeasonStatuses();
	      List<com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatus> seasonStatusList = new ArrayList<com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatus>();
	      for(SeasonStatus ss:seasonStatusDBList){
	         com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatus status = new com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatus(); 
	         status.setSeasonStatusId(ss.getSeasonStatusId());
	         status.setSeasonStatus(ss.getStatus());
	         status.setActive(ss.getActive() == CCIConstants.ACTIVE ? true : false);
	         seasonStatusList.add(status);
	      }
	      seasonStatuses.getSeasonStatuses().addAll(seasonStatusList);
	   }
      return seasonStatuses;
	}
}

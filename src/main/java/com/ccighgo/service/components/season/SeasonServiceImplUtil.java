/**
 * 
 */
package com.ccighgo.service.components.season;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.DepartmentProgram;
import com.ccighgo.db.entities.DepartmentProgramOption;
import com.ccighgo.db.entities.LookupDepartment;
import com.ccighgo.db.entities.Season;
import com.ccighgo.db.entities.SeasonF1Detail;
import com.ccighgo.db.entities.SeasonHSADetail;
import com.ccighgo.db.entities.SeasonHSPConfiguration;
import com.ccighgo.db.entities.SeasonJ1Detail;
import com.ccighgo.db.entities.SeasonStatus;
import com.ccighgo.jpa.repositories.DepartmentRepository;
import com.ccighgo.jpa.repositories.SeasonF1DetailsRepository;
import com.ccighgo.jpa.repositories.SeasonHSPConfigurationRepsitory;
import com.ccighgo.jpa.repositories.SeasonJ1DetailsRepository;
import com.ccighgo.jpa.repositories.SeasonRepository;
import com.ccighgo.jpa.repositories.SeasonStatusRepository;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.GHTSection1Base;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.GHTSection2Dates;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.SeasonGHTDetails;
import com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatuses;
import com.ccighgo.service.transport.seasons.beans.season.SeasonBean;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1Accounting;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1AugustStart1StSemesterDetails;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1AugustStartFullYearDetails;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1BasicDetails;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1FieldSettings;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1JanuaryStart2NdSemesterDetails;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1JanuaryStartFullYearDetail;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1ProgramAllocationDetails;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1ProgramAllocations;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.SeasonHSPF1Details;
import com.ccighgo.service.transport.seasons.beans.seasonslist.DepartmentObject;
import com.ccighgo.service.transport.seasons.beans.seasonslist.ProgramOptions;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonListObject;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.ExceptionUtil;
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
	@Autowired
	SeasonF1DetailsRepository seasonF1DetailsRepository;
	@Autowired
	SeasonJ1DetailsRepository seasonJ1DetailsRepository;
	 @Autowired
	 SeasonRepository seasonRepository;
	private Logger logger = LoggerFactory.getLogger(SeasonServiceImplUtil.class);
	
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
		seasonHSPConfiguration.setCreatedBy(1);
		seasonHSPConfiguration.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
		seasonHSPConfiguration.setModifiedBy(1);
		seasonHSPConfiguration.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
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

	public HSPF1BasicDetails getHSPF1NameAndStatus(
			SeasonF1Detail allF1Details , String seasonId) {
		HSPF1BasicDetails hspf1BasicDetails=null ;
		if(allF1Details!=null){
			hspf1BasicDetails=new HSPF1BasicDetails();
			hspf1BasicDetails.setSeasonId(Integer.parseInt(seasonId));
			hspf1BasicDetails.setProgramName(allF1Details.getProgramName());
			hspf1BasicDetails.setProgramStatus(allF1Details.getSeasonStatus().getStatus());
		}
		
		return hspf1BasicDetails;
	}

	public HSPF1Accounting getHSPF1Accounting(SeasonF1Detail allF1Details,String seasonId) {
		HSPF1Accounting hspf1Accounting = null;
		if(allF1Details!=null ){
			hspf1Accounting=new HSPF1Accounting();
			hspf1Accounting.setSeasonId(Integer.parseInt(seasonId));
			hspf1Accounting.setGreenHeartMargin(allF1Details.getGreenHeartMargin());
		}
		return hspf1Accounting;
	}

	public HSPF1JanuaryStart2NdSemesterDetails getHSPF1JanuaryStart2NdSemesterDetails(
			SeasonF1Detail allF1Details, String seasonId) {
		HSPF1JanuaryStart2NdSemesterDetails hspf1JanuaryStart2NdSemesterDetails =null;
		if(allF1Details!=null )
		{
			hspf1JanuaryStart2NdSemesterDetails = new HSPF1JanuaryStart2NdSemesterDetails();
			hspf1JanuaryStart2NdSemesterDetails.setSeasonId(Integer.parseInt(seasonId));
			hspf1JanuaryStart2NdSemesterDetails.setActivateFullYearProgram(allF1Details.getActiveFullYearJanProgram()!=0);
			hspf1JanuaryStart2NdSemesterDetails.setApplicationDeadlineDate(DateUtils.getMMddYyyyString(allF1Details.getSecondSemAppDeadlineDate()));
			hspf1JanuaryStart2NdSemesterDetails.setStartDate(DateUtils.getMMddYyyyString(allF1Details.getSecondSemStartDate()));
			hspf1JanuaryStart2NdSemesterDetails.setEndDate(DateUtils.getMMddYyyyString(allF1Details.getSecondSemEndDate()));
			hspf1JanuaryStart2NdSemesterDetails.setShow2NdSemestertoNewHF(allF1Details.getShowSecSemToNewHF()!=0);
			hspf1JanuaryStart2NdSemesterDetails.setEarliestBirthDate(DateUtils.getMMddYyyyString(allF1Details.getSecondSemEarliestBirthDate()));
			hspf1JanuaryStart2NdSemesterDetails.setLatestBirthDate(DateUtils.getMMddYyyyString(allF1Details.getSecondSemLatestBirthDate()));
		}
		return hspf1JanuaryStart2NdSemesterDetails;
	}

	public HSPF1AugustStartFullYearDetails getHSPF1AugustStartFullYearDetails(
			SeasonF1Detail allF1Details, String seasonId) {
		HSPF1AugustStartFullYearDetails hspAugustStartFullYearDetails =null;
		if(allF1Details!=null ){
			hspAugustStartFullYearDetails=new HSPF1AugustStartFullYearDetails();
			hspAugustStartFullYearDetails.setSeasonId(Integer.parseInt(seasonId));
			hspAugustStartFullYearDetails.setApplicationDeadlineDate(DateUtils.getMMddYyyyString(allF1Details.getAugFullYearAppDeadlineDate()));
			hspAugustStartFullYearDetails.setEndDate(DateUtils.getMMddYyyyString(allF1Details.getAugFullYearEndDate()));
			hspAugustStartFullYearDetails.setShowFullYearToNewHF(allF1Details.getShowAugFullYearToNewHF()!=0);
			hspAugustStartFullYearDetails.setStartDate(DateUtils.getMMddYyyyString(allF1Details.getAugFullYearStartDate()));
		}
		return hspAugustStartFullYearDetails;
	}

	public HSPF1AugustStart1StSemesterDetails getHSPF1AugustStart1StSemesterDetails(
			SeasonF1Detail allF1Details, String seasonId) {
		HSPF1AugustStart1StSemesterDetails hspf1AugustStart1StSemesterDetails=null;
		if(allF1Details!=null ){
			hspf1AugustStart1StSemesterDetails=new HSPF1AugustStart1StSemesterDetails();
			hspf1AugustStart1StSemesterDetails.setSeasonId(Integer.parseInt(seasonId));
			hspf1AugustStart1StSemesterDetails.setApplicationDeadlineDate(DateUtils.getMMddYyyyString(allF1Details.getFirstSemAppDeadlineDate()));
			hspf1AugustStart1StSemesterDetails.setEarliestBirthDate(DateUtils.getMMddYyyyString(allF1Details.getFirstSemEarliestBirthDate()));
			hspf1AugustStart1StSemesterDetails.setLatestBirthDate(DateUtils.getMMddYyyyString(allF1Details.getFirstSemLatestBirthDate()));
			hspf1AugustStart1StSemesterDetails.setStartDate(DateUtils.getMMddYyyyString(allF1Details.getFirstSemStartDate()));
			hspf1AugustStart1StSemesterDetails.setEndDate(DateUtils.getMMddYyyyString(allF1Details.getFirstSemEndDate()));
		}
		return hspf1AugustStart1StSemesterDetails;
	}

	public HSPF1FieldSettings getHSPF1FieldSettings(
			SeasonF1Detail allF1Details, String seasonId) {
		HSPF1FieldSettings hspf1FieldSettings =null;
		if(allF1Details!=null ){
			hspf1FieldSettings=new HSPF1FieldSettings();
			hspf1FieldSettings.setSeasonId(Integer.parseInt(seasonId));		
			hspf1FieldSettings.setAddOrStartHFInquiriesDate(DateUtils.getMMddYyyyString(allF1Details.getHfInquiryDate()));
			hspf1FieldSettings.setAllowFSToStartRenewalProcess(allF1Details.getAllowFieldStaffToStartRenewalProcess()!=0);
			hspf1FieldSettings.setDefaultLcPaymentSchedule(allF1Details.getLcPaymentScheduleId());
			hspf1FieldSettings.setFsAgreement(allF1Details.getFsAgreementId());
			hspf1FieldSettings.setHfReferencesNo(allF1Details.getHfReferences());
			hspf1FieldSettings.setShowSeasonProgramToCurrentHF(allF1Details.getShowSeasonToCurrentHF()!=0);
			hspf1FieldSettings.setShowSpecialRequestStudentToRD(allF1Details.getShowSpecialRequestStudent()!=0);
			hspf1FieldSettings.setShowWelcomeFamilyModle(allF1Details.getShowWelcomeFamily()!=0);
		}
		return hspf1FieldSettings;
	}

	public HSPF1ProgramAllocations getHSPF1ProgramAllocations(
			SeasonF1Detail allF1Details, String seasonId) {
		HSPF1ProgramAllocations hspf1ProgramAllocations =new HSPF1ProgramAllocations();
		hspf1ProgramAllocations.setSeasonId(Integer.parseInt(seasonId));
		HSPF1ProgramAllocationDetails hspf1ProgramAllocationDetails =null;
		if(allF1Details!=null ){
			hspf1ProgramAllocationDetails =new HSPF1ProgramAllocationDetails();
		}
		// TODO not complete
		hspf1ProgramAllocations.getProgramAllocationDetails().add(hspf1ProgramAllocationDetails);
		return hspf1ProgramAllocations;
	}

	public HSPF1JanuaryStartFullYearDetail getHSPF1JanuaryStartFullYearDetails(
			SeasonF1Detail allF1Details, String seasonId) {
		HSPF1JanuaryStartFullYearDetail hspf1JanuaryStartFullYearDetail =null;
		if(allF1Details!=null ){
			hspf1JanuaryStartFullYearDetail =new HSPF1JanuaryStartFullYearDetail();
			hspf1JanuaryStartFullYearDetail.setSeasonId(Integer.parseInt(seasonId));
			hspf1JanuaryStartFullYearDetail.setApplicationDeadlineDate(DateUtils.getMMddYyyyString(allF1Details.getJanFullYearAppDeadlineDate()));
			hspf1JanuaryStartFullYearDetail.setEndDate(DateUtils.getMMddYyyyString(allF1Details.getJanFullYearEndDate()));
			hspf1JanuaryStartFullYearDetail.setStartDate(DateUtils.getMMddYyyyString(allF1Details.getJanFullYearStartDate()));
			hspf1JanuaryStartFullYearDetail.setShowFullYearToHF(allF1Details.getShowJanFullYearToNewHF()!=0);
		}
		return hspf1JanuaryStartFullYearDetail;
	}

	public SeasonHSPF1Details updateF1Details(SeasonF1Detail allF1Details,
			SeasonHSPF1Details seasonHSPF1Details) {
		try{
			allF1Details.setSeasonStatus(seasonStatusRepository.getSeasonStatusByName(seasonHSPF1Details.getDetails().getProgramStatus()));
			allF1Details.setProgramName(seasonHSPF1Details.getDetails().getProgramName());
			allF1Details.setGreenHeartMargin(seasonHSPF1Details.getAccounting().getGreenHeartMargin());
			allF1Details.setActiveFullYearJanProgram((byte) (seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().isActivateFullYearProgram()?1:0));
			 allF1Details.setShowSecSemToNewHF((byte) (seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().isShow2NdSemestertoNewHF()?1:0));
			 allF1Details.setSecondSemAppDeadlineDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().getApplicationDeadlineDate()));
			 allF1Details.setSecondSemEarliestBirthDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().getEarliestBirthDate()));
			 allF1Details.setSecondSemEndDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().getEndDate()));
			 allF1Details.setSecondSemLatestBirthDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().getLatestBirthDate()));
			 allF1Details.setSecondSemStartDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().getStartDate()));
			 allF1Details.setJanFullYearAppDeadlineDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStartFullYearDetail().getApplicationDeadlineDate()));
			 allF1Details.setJanFullYearEndDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStartFullYearDetail().getEndDate()));
			 allF1Details.setJanFullYearStartDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStartFullYearDetail().getStartDate()));
			 allF1Details.setShowJanFullYearToNewHF((byte) (seasonHSPF1Details.getJanuaryStartFullYearDetail().isShowFullYearToHF()?1:0));
			 allF1Details.setFirstSemAppDeadlineDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStart1StSemesterDetails().getApplicationDeadlineDate()));
			 allF1Details.setFirstSemEarliestBirthDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStart1StSemesterDetails().getEarliestBirthDate()));
			 allF1Details.setFirstSemLatestBirthDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStart1StSemesterDetails().getLatestBirthDate()));
			 allF1Details.setFirstSemStartDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStart1StSemesterDetails().getStartDate()));
			 allF1Details.setFirstSemEndDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStart1StSemesterDetails().getEndDate()));
			 allF1Details.setAugFullYearAppDeadlineDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStartFullYearDetails().getApplicationDeadlineDate()));
			 allF1Details.setAugFullYearEndDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStartFullYearDetails().getEndDate()));
			 allF1Details.setAugFullYearStartDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStartFullYearDetails().getStartDate()));
			 allF1Details.setShowAugFullYearToNewHF((byte) (seasonHSPF1Details.getAugustStartFullYearDetails().isShowFullYearToNewHF()?1:0));
			 allF1Details.setHfInquiryDate(DateUtils.getDateFromString(seasonHSPF1Details.getFieldSettings().getAddOrStartHFInquiriesDate()));
			 allF1Details.setAllowFieldStaffToStartRenewalProcess((byte) (seasonHSPF1Details.getFieldSettings().isAllowFSToStartRenewalProcess()?1:0));
			 allF1Details.setLcPaymentScheduleId(seasonHSPF1Details.getFieldSettings().getDefaultLcPaymentSchedule());
			 allF1Details.setFsAgreementId(seasonHSPF1Details.getFieldSettings().getFsAgreement());
			 allF1Details.setHfReferences(seasonHSPF1Details.getFieldSettings().getHfReferencesNo());
			 allF1Details.setShowSeasonToCurrentHF((byte) (seasonHSPF1Details.getFieldSettings().isShowSeasonProgramToCurrentHF()?1:0));
			 allF1Details.setShowSpecialRequestStudent((byte) (seasonHSPF1Details.getFieldSettings().isShowSpecialRequestStudentToRD()?1:0));
			 allF1Details.setShowWelcomeFamily((byte) (seasonHSPF1Details.getFieldSettings().isShowWelcomeFamilyModle()?1:0));
			 
 			seasonF1DetailsRepository.saveAndFlush(allF1Details);
		}catch(Exception ex){
			ExceptionUtil.logException(ex, logger);
			return null;
		}
 		return seasonHSPF1Details;
	}

	public HSPF1BasicDetails updateHSPF1NameAndStatus(
			SeasonF1Detail currentF1Detail, HSPF1BasicDetails hspf1BasicDetails) {
		try{
			currentF1Detail.setSeasonStatus(seasonStatusRepository.getSeasonStatusByName(hspf1BasicDetails.getProgramStatus()));
			currentF1Detail.setProgramName(hspf1BasicDetails.getProgramName());
			seasonF1DetailsRepository.saveAndFlush(currentF1Detail);
		}catch(Exception ex){
			ExceptionUtil.logException(ex, logger);
			return null;
		}
			return hspf1BasicDetails;
	}

	public HSPF1Accounting updateF1Accounting(SeasonF1Detail currentF1Detail,
			HSPF1Accounting hspf1Accounting) {
		try{
			currentF1Detail.setGreenHeartMargin(hspf1Accounting.getGreenHeartMargin());
			seasonF1DetailsRepository.saveAndFlush(currentF1Detail);
		}catch(Exception ex){
			ExceptionUtil.logException(ex, logger);
			return null;
		}
		return hspf1Accounting;
	}

	public HSPF1JanuaryStart2NdSemesterDetails updateF1JanStart2NdSemesterDetails(
			SeasonF1Detail allF1Details,
			HSPF1JanuaryStart2NdSemesterDetails hspf1JanuaryStart2NdSemesterDetails) {
		try{
			 allF1Details.setActiveFullYearJanProgram((byte) (hspf1JanuaryStart2NdSemesterDetails.isActivateFullYearProgram()?1:0));
			 allF1Details.setShowSecSemToNewHF((byte) (hspf1JanuaryStart2NdSemesterDetails.isShow2NdSemestertoNewHF()?1:0));
			 allF1Details.setSecondSemAppDeadlineDate(DateUtils.getDateFromString(hspf1JanuaryStart2NdSemesterDetails.getApplicationDeadlineDate()));
			 allF1Details.setSecondSemEarliestBirthDate(DateUtils.getDateFromString(hspf1JanuaryStart2NdSemesterDetails.getEarliestBirthDate()));
			 allF1Details.setSecondSemEndDate(DateUtils.getDateFromString(hspf1JanuaryStart2NdSemesterDetails.getEndDate()));
			 allF1Details.setSecondSemLatestBirthDate(DateUtils.getDateFromString(hspf1JanuaryStart2NdSemesterDetails.getLatestBirthDate()));
			 allF1Details.setSecondSemStartDate(DateUtils.getDateFromString(hspf1JanuaryStart2NdSemesterDetails.getStartDate()));
			seasonF1DetailsRepository.saveAndFlush(allF1Details);
		}catch(Exception ex){
			ExceptionUtil.logException(ex, logger);
			return null;
		}
		return hspf1JanuaryStart2NdSemesterDetails;
	}

	public HSPF1JanuaryStartFullYearDetail updateF1JanStartFullYearDetails(
			SeasonF1Detail allF1Details,
			HSPF1JanuaryStartFullYearDetail hspf1JanuaryStartFullYearDetail) {
		try{
			 allF1Details.setJanFullYearAppDeadlineDate(DateUtils.getDateFromString(hspf1JanuaryStartFullYearDetail.getApplicationDeadlineDate()));
			 allF1Details.setJanFullYearEndDate(DateUtils.getDateFromString(hspf1JanuaryStartFullYearDetail.getEndDate()));
			 allF1Details.setJanFullYearStartDate(DateUtils.getDateFromString(hspf1JanuaryStartFullYearDetail.getStartDate()));
			 allF1Details.setShowJanFullYearToNewHF((byte) (hspf1JanuaryStartFullYearDetail.isShowFullYearToHF()?1:0));
			seasonF1DetailsRepository.saveAndFlush(allF1Details);
		}catch(Exception ex){
			ExceptionUtil.logException(ex, logger);
			return null;
		}
		return hspf1JanuaryStartFullYearDetail;
	}

	public HSPF1AugustStart1StSemesterDetails updateF1AugStart1StSemesterDetails(
			SeasonF1Detail allF1Details,
			HSPF1AugustStart1StSemesterDetails hspf1AugustStart1StSemesterDetails) {
		try{
			 allF1Details.setFirstSemAppDeadlineDate(DateUtils.getDateFromString(hspf1AugustStart1StSemesterDetails.getApplicationDeadlineDate()));
			 allF1Details.setFirstSemEarliestBirthDate(DateUtils.getDateFromString(hspf1AugustStart1StSemesterDetails.getEarliestBirthDate()));
			 allF1Details.setFirstSemLatestBirthDate(DateUtils.getDateFromString(hspf1AugustStart1StSemesterDetails.getLatestBirthDate()));
			 allF1Details.setFirstSemStartDate(DateUtils.getDateFromString(hspf1AugustStart1StSemesterDetails.getStartDate()));
			 allF1Details.setFirstSemEndDate(DateUtils.getDateFromString(hspf1AugustStart1StSemesterDetails.getEndDate()));
			seasonF1DetailsRepository.saveAndFlush(allF1Details);
		}catch(Exception ex){
			ExceptionUtil.logException(ex, logger);
			return null;
		}
		return hspf1AugustStart1StSemesterDetails;
	}

	public HSPF1AugustStartFullYearDetails updateF1AugStartFullYearDetails(
			SeasonF1Detail allF1Details,
			HSPF1AugustStartFullYearDetails hspf1AugustStartFullYearDetails) {
		try{
			 allF1Details.setAugFullYearAppDeadlineDate(DateUtils.getDateFromString(hspf1AugustStartFullYearDetails.getApplicationDeadlineDate()));
			 allF1Details.setAugFullYearEndDate(DateUtils.getDateFromString(hspf1AugustStartFullYearDetails.getEndDate()));
			 allF1Details.setAugFullYearStartDate(DateUtils.getDateFromString(hspf1AugustStartFullYearDetails.getStartDate()));
			 allF1Details.setShowAugFullYearToNewHF((byte) (hspf1AugustStartFullYearDetails.isShowFullYearToNewHF()?1:0));
			 
			seasonF1DetailsRepository.saveAndFlush(allF1Details);
		}catch(Exception ex){
			ExceptionUtil.logException(ex, logger);
			return null;
		}
		return hspf1AugustStartFullYearDetails;
	}

	public HSPF1FieldSettings updateF1FieldSettings(
			SeasonF1Detail allF1Details, HSPF1FieldSettings hspf1FieldSettings) {
		try{
			 allF1Details.setHfInquiryDate(DateUtils.getDateFromString(hspf1FieldSettings.getAddOrStartHFInquiriesDate()));
			 allF1Details.setAllowFieldStaffToStartRenewalProcess((byte) (hspf1FieldSettings.isAllowFSToStartRenewalProcess()?1:0));
			 allF1Details.setLcPaymentScheduleId(hspf1FieldSettings.getDefaultLcPaymentSchedule());
			 allF1Details.setFsAgreementId(hspf1FieldSettings.getFsAgreement());
			 allF1Details.setHfReferences(hspf1FieldSettings.getHfReferencesNo());
			 allF1Details.setShowSeasonToCurrentHF((byte) (hspf1FieldSettings.isShowSeasonProgramToCurrentHF()?1:0));
			 allF1Details.setShowSpecialRequestStudent((byte) (hspf1FieldSettings.isShowSpecialRequestStudentToRD()?1:0));
			 allF1Details.setShowWelcomeFamily((byte) (hspf1FieldSettings.isShowWelcomeFamilyModle()?1:0));
			seasonF1DetailsRepository.saveAndFlush(allF1Details);
		}catch(Exception ex){
			ExceptionUtil.logException(ex, logger);
			return null;
		}
		return hspf1FieldSettings;
	}

	public HSPF1ProgramAllocations updateF1ProgramAllocation(
			SeasonF1Detail allF1Details,
			HSPF1ProgramAllocations hspf1ProgramAllocations) {
		try{
			 // TODO Not complete
			seasonF1DetailsRepository.saveAndFlush(allF1Details);
		}catch(Exception ex){
			ExceptionUtil.logException(ex, logger);
			return null;
		}
		return hspf1ProgramAllocations;
	}

	public void createSeasonProgram(Season seasonEntity, SeasonBean seasonBean) {
		createHSPF1Season(seasonEntity,seasonBean);
		createHSPJ1HSSeasonProgram(seasonBean, seasonEntity);
	}

	private void createHSPF1Season(Season seasonEntity, SeasonBean seasonBean) {
	if(seasonEntity.getSeasonId()>0 && seasonBean.getSeasonName()!=null){
		SeasonF1Detail seasonF1Detail = new SeasonF1Detail();
		seasonF1Detail.setSeason(seasonEntity);
		seasonF1Detail.setProgramName(seasonBean.getSeasonName());
		seasonF1Detail.setCreatedBy(1);
		seasonF1Detail.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
		seasonF1Detail.setModifiedBy(1);
		seasonF1Detail.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
		seasonF1DetailsRepository.saveAndFlush(seasonF1Detail);
	  }
	}
	
	/**
	  * This method creates j1hs season program for HSP high level season
	  * 
	  * @param seasonBean
	  */
	 private void createHSPJ1HSSeasonProgram(SeasonBean seasonBean, Season season){
	      if(season.getSeasonId()>0 && seasonBean.getSeasonName()!=null){
	         SeasonJ1Detail seasonJ1Detail = new SeasonJ1Detail();
	         seasonJ1Detail.setSeason(season);
	         seasonJ1Detail.setProgramName(seasonBean.getSeasonName());
	         seasonJ1Detail.setCreatedBy(1);
	         seasonJ1Detail.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
	         seasonJ1Detail.setModifiedBy(1);
	         seasonJ1Detail.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
	         seasonJ1DetailsRepository.saveAndFlush(seasonJ1Detail);
	      }
	   }

	public SeasonGHTDetails getGHTHSAbroad(SeasonHSADetail seasonHSADetail) {
		SeasonGHTDetails seasonGHTDetails = new SeasonGHTDetails();
		int seasonId = seasonHSADetail.getSeason().getSeasonId();
		seasonGHTDetails.setSeasonId(seasonId);
		GHTSection1Base ghtSection1Base=new GHTSection1Base();
		ghtSection1Base.setProgramName(seasonHSADetail.getProgramName());
		ghtSection1Base.setProgramStatus(seasonHSADetail.getSeasonStatus().getStatus());
		ghtSection1Base.setSeasonId(seasonId);
		seasonGHTDetails.setGhtBaseDetails(ghtSection1Base);
		return seasonGHTDetails;
	}

	public SeasonGHTDetails updateGHTHSAbroad(SeasonGHTDetails seasonGHTDetails) {
		try{
			SeasonHSADetail seasonHsaDetail = new SeasonHSADetail();
			seasonHsaDetail.setSeason(seasonRepository.findOne(seasonGHTDetails.getSeasonId()));
			seasonHsaDetail.setProgramName(seasonGHTDetails.getGhtBaseDetails().getProgramName());
			seasonHsaDetail.setSeasonStatus(seasonStatusRepository.getSeasonStatusByName(seasonGHTDetails.getGhtBaseDetails().getProgramStatus()));
		}catch(Exception ex){
			ExceptionUtil.logException(ex, logger);
			return null;
		}
		return seasonGHTDetails;
	}
	
}
package com.ccighgo.service.components.season;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.DepartmentProgram;
import com.ccighgo.db.entities.LookupDepartment;
import com.ccighgo.db.entities.Season;
import com.ccighgo.db.entities.SeasonF1Detail;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.jpa.repositories.SeasonF1DetailsRepository;
import com.ccighgo.jpa.repositories.SeasonRepository;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSAugStart;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSBasicDetail;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSFieldSettings;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSJanStart;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSProgramAllocations;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.SeasonHspJ1HSDetails;
import com.ccighgo.service.transport.season.beans.seasonprogram.SeasonProgram;
import com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatuses;
import com.ccighgo.service.transport.seasons.beans.season.SeasonBean;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1Accounting;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1AugustStart1StSemesterDetails;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1AugustStartFullYearDetails;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1BasicDetails;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1FieldSettings;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1JanuaryStart2NdSemesterDetails;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1JanuaryStartFullYearDetail;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1ProgramAllocations;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.SeasonHSPF1Details;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonListObject;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonsList;
import com.ccighgo.service.transport.utility.beans.department.Department;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.ExceptionUtil;
import com.ccighgo.utils.ValidationUtils;

@Component
public class SeasonServiceInterfaceImpl implements SeasonServiceInterface {

   private static final Logger LOGGER = LoggerFactory.getLogger(SeasonServiceInterfaceImpl.class);
   @Autowired
   SeasonRepository seasonRepository;
   @Autowired
   SeasonServiceImplUtil seasonServiceImplUtil;
	@Autowired
	SeasonRepositoryCustomImpl seasonRepositoryService;
	@Autowired
	SeasonF1DetailsRepository seasonF1DetailsRepository;


   SeasonServiceInterfaceImpl() {
   }


   @Override
   public SeasonsList getAllSeasons() {
      try {
         List<Season> allseasons = seasonRepository.getAllSeasons();
         SeasonsList seasonsList = new SeasonsList();
         if (allseasons != null && !(allseasons.isEmpty())) {
            seasonsList.setRecordCount(allseasons.size());
            for (int i = 0; i < allseasons.size(); i++) {
               SeasonListObject seasonBean = new SeasonListObject();
               Season seasonEntity = allseasons.get(i);
               seasonServiceImplUtil.convertEntitySeasonToSeasonListObject(seasonBean, seasonEntity);
               seasonsList.getSeasons().add(seasonBean);
            }
         }
         return seasonsList;
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Override
   public SeasonBean createSeason(SeasonBean seasonBean) {
      try {
         Season seasonEntity = new Season();
         seasonServiceImplUtil.convertSeasonBeanToSeasonEntity(seasonBean, seasonEntity, false);
         seasonEntity=seasonRepository.saveAndFlush(seasonEntity);
         seasonServiceImplUtil.createSeasonHspConfiguration(seasonBean, seasonEntity);
         return viewSeason(seasonEntity.getSeasonId() + CCIConstants.EMPTY_DATA);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Override
   public String deleteSeason(String id) {
      Season seasonEntity = seasonRepository.findOne(Integer.parseInt(id));
      if (seasonEntity != null) {
         //seasonEntity.setActive(CCIConstants.INACTIVE);
         seasonRepository.saveAndFlush(seasonEntity);
         return "Season Deactivated";
      }
      return "Error Deactivating Season";
   }

   @Override
   public SeasonBean editSeason(String id) {
      return viewSeason(id);
   }

   @Override
   public SeasonBean viewSeason(String id) {
      ValidationUtils.isValidSeasonId(id);
      try {
         Season seasonEntity = seasonRepository.findOne(Integer.parseInt(id));
         if (seasonEntity != null) {
            SeasonBean seasonBean = new SeasonBean();
            seasonServiceImplUtil.convertEntitySeasonToBeanSeason(seasonBean, seasonEntity);
            return seasonBean;
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Override
   public SeasonBean updateSeason(SeasonBean seasonBean) {
      try {
         Season seasonEntity = new Season();
         seasonServiceImplUtil.convertSeasonBeanToSeasonEntity(seasonBean, seasonEntity, true);
         seasonRepository.saveAndFlush(seasonEntity);
         seasonServiceImplUtil.updateSeasonHspConfiguration(seasonBean, seasonEntity);
         return viewSeason(seasonEntity.getSeasonId() + CCIConstants.EMPTY_DATA);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public SeasonProgram getSeasonPrograms(String seasonId) {
      SeasonProgram seasonProgram = null;
      try {
         Season season = seasonRepository.findOne(Integer.valueOf(seasonId));
         if (season != null) {
            List<String> seasonPrograms = new ArrayList<String>();
            seasonProgram = new SeasonProgram();
            LookupDepartment dept = season.getLookupDepartment();
            List<DepartmentProgram> departmentPrograms = dept.getDepartmentPrograms();
            for (DepartmentProgram dPrg : departmentPrograms) {
               String seasonPrg = season.getSeasonName() + CCIConstants.HYPHEN_SPACE + dPrg.getProgramName();
               seasonPrograms.add(seasonPrg);
            }
            seasonProgram.getSeasonPrograms().addAll(seasonPrograms);
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return seasonProgram;
   }


   public SeasonStatuses getSeasonStatus() {
      SeasonStatuses seasonStatuses = null;
      try{
         LOGGER.info("SeasonStatus: fetch");
         seasonStatuses = seasonServiceImplUtil.getSeasonStatus();
      }catch(CcighgoException e){
         ExceptionUtil.logException(e, LOGGER);
      }
      return seasonStatuses;
   }

   public SeasonHspJ1HSDetails getHSPJ1HSSeasonDetails(String seasonId) {
      SeasonHspJ1HSDetails seasonHspJ1HSDetails = null;
      return seasonHspJ1HSDetails;
   }


   public J1HSBasicDetail getHSPJ1HSSeasonNameAndStatus(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }


   public J1HSJanStart getHSPJ1HSSeasonJanStartDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }


   public J1HSAugStart getHSPJ1HSSeasonAugStartDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }


   public J1HSFieldSettings getHSPJ1HSSeasonFieldSettings(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }


   public J1HSProgramAllocations getHSPJ1HSSeasonProgramAllocation(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

@Override
	public String cloneSeason(String id, String newSeasonName) {
		try {
			return seasonRepositoryService.cloneSeason(id, newSeasonName);
		} catch (Exception e) {
			ExceptionUtil.logException(e, LOGGER);
		}
		return null;
	}

	public void customService(String id) {
		seasonRepositoryService.findSeasonByName("CAP-2010");
//		seasonRepositoryService.findSeasonByDepartment("1");
		System.out.println("Line for Debug");
	}


	public HSPF1ProgramAllocations getHSPF1ProgramAllocations(String seasonId) {
		SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(Integer.valueOf(seasonId));
		return seasonServiceImplUtil.getHSPF1ProgramAllocations(allF1Details,seasonId);
	}


	public HSPF1FieldSettings getHSPF1FieldSettings(String seasonId) {
		SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(Integer.valueOf(seasonId));
		return seasonServiceImplUtil.getHSPF1FieldSettings(allF1Details,seasonId);
	}


	public HSPF1AugustStart1StSemesterDetails getHSPF1AugustStart1StSemesterDetails(String seasonId) {
		SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(Integer.valueOf(seasonId));
		return seasonServiceImplUtil.getHSPF1AugustStart1StSemesterDetails(allF1Details,seasonId);
	}
	public HSPF1AugustStartFullYearDetails getHSPF1AugustStartFullYearDetails(String seasonId) {
		SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(Integer.valueOf(seasonId));
		return seasonServiceImplUtil.getHSPF1AugustStartFullYearDetails(allF1Details,seasonId);
	}


	public HSPF1JanuaryStart2NdSemesterDetails getHSPF1JanuaryStart2NdSemesterDetails(String seasonId) {
		SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(Integer.valueOf(seasonId));
		return seasonServiceImplUtil.getHSPF1JanuaryStart2NdSemesterDetails(allF1Details,seasonId);
	}
	
	public HSPF1JanuaryStartFullYearDetail getHSPF1JanuaryStartFullYearDetails(String seasonId) {
		SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(Integer.valueOf(seasonId));
		return seasonServiceImplUtil.getHSPF1JanuaryStartFullYearDetails(allF1Details,seasonId);
	}


	public HSPF1BasicDetails getHSPF1NameAndStatus(String seasonId) {
		SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(Integer.valueOf(seasonId));
		return seasonServiceImplUtil.getHSPF1NameAndStatus(allF1Details, seasonId);
	}

	public HSPF1Accounting getHSPF1Accounting(String seasonId) {
		SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(Integer.valueOf(seasonId));
		return seasonServiceImplUtil.getHSPF1Accounting(allF1Details, seasonId);
	}
	public SeasonHSPF1Details getSeasonHSPF1Details(String seasonId) {
		SeasonHSPF1Details seasonHSPF1Details =new SeasonHSPF1Details();
		seasonHSPF1Details.setSeasonId(Integer.valueOf(seasonId));
		SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(Integer.valueOf(seasonId));
		seasonHSPF1Details.setDetails(seasonServiceImplUtil.getHSPF1NameAndStatus(allF1Details, seasonId));
		seasonHSPF1Details.setJanuaryStart2NdSemesterDetails(seasonServiceImplUtil.getHSPF1JanuaryStart2NdSemesterDetails(allF1Details, seasonId));
		seasonHSPF1Details.setJanuaryStartFullYearDetail(seasonServiceImplUtil.getHSPF1JanuaryStartFullYearDetails(allF1Details, seasonId));
		seasonHSPF1Details.setAugustStart1StSemesterDetails(seasonServiceImplUtil.getHSPF1AugustStart1StSemesterDetails(allF1Details, seasonId));
		seasonHSPF1Details.setAugustStartFullYearDetails(seasonServiceImplUtil.getHSPF1AugustStartFullYearDetails(allF1Details, seasonId));
		seasonHSPF1Details.setAccounting(seasonServiceImplUtil.getHSPF1Accounting(allF1Details, seasonId));
		seasonHSPF1Details.setFieldSettings(seasonServiceImplUtil.getHSPF1FieldSettings(allF1Details, seasonId));
		seasonHSPF1Details.setProgramAllocations(seasonServiceImplUtil.getHSPF1ProgramAllocations(allF1Details, seasonId));
		return seasonHSPF1Details;
	}


	public SeasonHSPF1Details updateF1Details(SeasonHSPF1Details seasonHSPF1Details) {
		SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(seasonHSPF1Details.getSeasonId());
		if(allF1Details!=null ){
			SeasonHSPF1Details updatedSeasonHSPF1Details =seasonServiceImplUtil.updateF1Details(allF1Details,seasonHSPF1Details);
			seasonHSPF1Details = seasonServiceImplUtil.updateF1Details(allF1Details, seasonHSPF1Details);
		}
		
		return seasonHSPF1Details;
	}


	public HSPF1BasicDetails updateHSPF1NameAndStatus(
			HSPF1BasicDetails hspf1BasicDetails) {
		SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(hspf1BasicDetails.getSeasonId());
		if(allF1Details!=null ){
			hspf1BasicDetails= seasonServiceImplUtil.updateHSPF1NameAndStatus(allF1Details,hspf1BasicDetails);
		}
		return hspf1BasicDetails;
	}


	public HSPF1Accounting updateF1Accounting(HSPF1Accounting hspf1Accounting) {
		SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(hspf1Accounting.getSeasonId());
		if(allF1Details!=null ){
			hspf1Accounting=seasonServiceImplUtil.updateF1Accounting(allF1Details, hspf1Accounting);
		}
		return hspf1Accounting;
	}


	public HSPF1JanuaryStart2NdSemesterDetails updateF1JanStart2NdSemesterDetails(
			HSPF1JanuaryStart2NdSemesterDetails hspf1JanuaryStart2NdSemesterDetails) {
		SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(hspf1JanuaryStart2NdSemesterDetails.getSeasonId());
		if(allF1Details!=null ){
			hspf1JanuaryStart2NdSemesterDetails= seasonServiceImplUtil.updateF1JanStart2NdSemesterDetails(allF1Details,hspf1JanuaryStart2NdSemesterDetails);
		}
		return  hspf1JanuaryStart2NdSemesterDetails;
	}


	public HSPF1JanuaryStartFullYearDetail updateF1JanStartFullYearDetails(
			HSPF1JanuaryStartFullYearDetail hspf1JanuaryStartFullYearDetail) {
		SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(hspf1JanuaryStartFullYearDetail.getSeasonId());
		if(allF1Details!=null ){
			hspf1JanuaryStartFullYearDetail= seasonServiceImplUtil.updateF1JanStartFullYearDetails(allF1Details,hspf1JanuaryStartFullYearDetail);
		}
		return  hspf1JanuaryStartFullYearDetail;
	}


	public HSPF1AugustStart1StSemesterDetails updateF1AugStart1StSemesterDetails(
			HSPF1AugustStart1StSemesterDetails hspf1AugustStart1StSemesterDetails) {
		SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(hspf1AugustStart1StSemesterDetails.getSeasonId());
		if(allF1Details!=null ){
			hspf1AugustStart1StSemesterDetails= seasonServiceImplUtil.updateF1AugStart1StSemesterDetails(allF1Details,hspf1AugustStart1StSemesterDetails);
		}
		return  hspf1AugustStart1StSemesterDetails;
	}


	public HSPF1AugustStartFullYearDetails updateF1AugStartFullYearDetails(
			HSPF1AugustStartFullYearDetails hspf1AugustStartFullYearDetails) {
		SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(hspf1AugustStartFullYearDetails.getSeasonId());
		if(allF1Details!=null ){
			hspf1AugustStartFullYearDetails= seasonServiceImplUtil.updateF1AugStartFullYearDetails(allF1Details,hspf1AugustStartFullYearDetails);
		}
		return  hspf1AugustStartFullYearDetails;
	}


	public HSPF1FieldSettings updateF1FieldSettings(
			HSPF1FieldSettings hspf1FieldSettings) {
		SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(hspf1FieldSettings.getSeasonId());
		if(allF1Details!=null ){
			hspf1FieldSettings= seasonServiceImplUtil.updateF1FieldSettings(allF1Details,hspf1FieldSettings);
		}
		return  hspf1FieldSettings;
	}


	public HSPF1ProgramAllocations updateF1ProgramAllocation(
			HSPF1ProgramAllocations hspf1ProgramAllocations) {
		SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(hspf1ProgramAllocations.getSeasonId());
		if(allF1Details!=null ){
			hspf1ProgramAllocations= seasonServiceImplUtil.updateF1ProgramAllocation(allF1Details,hspf1ProgramAllocations);
		}
		return  hspf1ProgramAllocations;
	}

}

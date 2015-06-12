package com.ccighgo.service.components.season;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Department;
import com.ccighgo.db.entities.DepartmentProgram;
import com.ccighgo.db.entities.Season;
import com.ccighgo.db.entities.SeasonHSPConfiguration;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.InvalidServiceConfigurationException;
import com.ccighgo.jpa.repositories.SeasonRepository;
import com.ccighgo.service.transport.season.beans.seasonprogram.SeasonProgram;
import com.ccighgo.service.transport.seasons.beans.season.SeasonBean;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonListObject;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonsList;
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

   SeasonServiceInterfaceImpl() {
   }

   @Override
   public String getString() {
      return "<- Season Service ->";
   }

   @Override
   public SeasonsList getAllSeasons() {
      try {
         List<Season> allseasons = seasonRepository.findAll();
         SeasonsList seasonsList = new SeasonsList();
         if (allseasons != null) {
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
         seasonRepository.saveAndFlush(seasonEntity);
         seasonServiceImplUtil.createSeasonHspConfiguration(seasonBean, seasonEntity);
         return viewSeason(seasonEntity.getSeasonId() + "");
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Override
   public String deleteSeason(String id) {
      Season seasonEntity = seasonRepository.findOne(Integer.parseInt(id));
      if (seasonEntity != null) {
         seasonEntity.setActive(CCIConstants.INACTIVE);
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
         return viewSeason(seasonEntity.getSeasonId() + "");
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
            Department dept = season.getDepartment();
            List<DepartmentProgram> departmentPrograms = dept.getDepartmentPrograms();
            for (DepartmentProgram dPrg : departmentPrograms) {
               String seasonPrg = season.getSeasonName() + " - " + dPrg.getProgramName();
               seasonPrograms.add(seasonPrg);
            }
            seasonProgram.getSeasonPrograms().addAll(seasonPrograms);
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return seasonProgram;
   }

}

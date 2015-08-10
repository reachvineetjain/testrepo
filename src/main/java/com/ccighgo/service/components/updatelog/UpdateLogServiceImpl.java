package com.ccighgo.service.components.updatelog;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.DepartmentProgram;
import com.ccighgo.db.entities.Season;
import com.ccighgo.jpa.repositories.DepartmentProgramRepository;
import com.ccighgo.jpa.repositories.SeasonDepartmentUpdateLogRepository;
import com.ccighgo.jpa.repositories.SeasonProgramUpdateLogRepository;
import com.ccighgo.jpa.repositories.SeasonRepository;
import com.ccighgo.service.transport.seasons.beans.seasondepartmentupdatelog.SeasonDepartmentUpdateLog;
import com.ccighgo.service.transport.seasons.beans.seasonprogramupdatelog.SeasonProgramUpdateLog;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;

@Component
public class UpdateLogServiceImpl implements UpdateLogServiceInterface {

   @Autowired
   SeasonProgramUpdateLogRepository seasonProgramUpdateLogRepository;
   @Autowired
   SeasonDepartmentUpdateLogRepository seasonDepartmentUpdateLogRepository;
   @Autowired
   SeasonRepository seasonRepository;
   @Autowired
   DepartmentProgramRepository departmentProgramRepository;

   @Override
   public List<SeasonProgramUpdateLog> viewSeasonProgramLog(String programId, String seasonId) {
      List<com.ccighgo.db.entities.SeasonProgramUpdateLog> seasonProgram = seasonProgramUpdateLogRepository.findUpdateLogByDepartmentProgramIdAndSeasonID(
            Integer.parseInt(programId), Integer.parseInt(seasonId));
      List<SeasonProgramUpdateLog> listSeasonProgramUpdateLog = new ArrayList<SeasonProgramUpdateLog>();
      if (seasonProgram != null && !seasonProgram.isEmpty()) {
         for (com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLog : seasonProgram) {
            SeasonProgramUpdateLog spu = new SeasonProgramUpdateLog();
            if (seasonProgramUpdateLog.getDepartmentProgram() != null)
               spu.setDepartmentProgramId(seasonProgramUpdateLog.getDepartmentProgram().getDepartmentProgramId());
            spu.setModifiedBy(seasonProgramUpdateLog.getModifiedBy() + "");
            spu.setModifiedOn(DateUtils.getTimeStamp(seasonProgramUpdateLog.getModifiedOn()));
            spu.setSeasonId(seasonProgramUpdateLog.getSeason().getSeasonId());
            spu.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
            listSeasonProgramUpdateLog.add(spu);
         }
      }
      return listSeasonProgramUpdateLog;
   }

   @Override
   public List<SeasonDepartmentUpdateLog> viewSeasonDepartmentLog(String seasonId) {
      List<com.ccighgo.db.entities.SeasonDepartmentUpdateLog> seasonDepartmentUpdateLog = seasonDepartmentUpdateLogRepository.findUpdateLogBySeasonId(Integer.parseInt(seasonId));
      List<SeasonDepartmentUpdateLog> listSeasonDepartmentUpdateLogBean = new ArrayList<SeasonDepartmentUpdateLog>();
      if (seasonDepartmentUpdateLog != null && !seasonDepartmentUpdateLog.isEmpty()) {
         for (com.ccighgo.db.entities.SeasonDepartmentUpdateLog seasonDepartmentUpdateLog2 : seasonDepartmentUpdateLog) {
            SeasonDepartmentUpdateLog sdl = new SeasonDepartmentUpdateLog();
            sdl.setModifiedBy(seasonDepartmentUpdateLog2.getModifiedBy() + "");
            sdl.setModifiedOn(DateUtils.getTimeStamp(seasonDepartmentUpdateLog2.getModifiedOn()));
            sdl.setSeasonId(seasonDepartmentUpdateLog2.getSeason().getSeasonId());
            sdl.setUpdateLogObject(seasonDepartmentUpdateLog2.getUpdateLogObject());
            listSeasonDepartmentUpdateLogBean.add(sdl);
         }
      }
      return listSeasonDepartmentUpdateLogBean;
   }

   @Override
   public List<SeasonDepartmentUpdateLog> saveSeasonDepartmentLog(SeasonDepartmentUpdateLog seasonDepartmentUpdateLog) {
      com.ccighgo.db.entities.SeasonDepartmentUpdateLog seasonDepartmentUpdateLogEntity = new com.ccighgo.db.entities.SeasonDepartmentUpdateLog();
      seasonDepartmentUpdateLogEntity.setModifiedBy(1);
      seasonDepartmentUpdateLogEntity.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      Season season = seasonRepository.findOne(seasonDepartmentUpdateLog.getSeasonId());
      seasonDepartmentUpdateLogEntity.setSeason(season);
      seasonDepartmentUpdateLogEntity.setUpdateLogObject(seasonDepartmentUpdateLog.getUpdateLogObject());
      seasonDepartmentUpdateLogRepository.saveAndFlush(seasonDepartmentUpdateLogEntity);
      return viewSeasonDepartmentLog(seasonDepartmentUpdateLog.getSeasonId() + "");
   }

   @Override
   public List<SeasonProgramUpdateLog> saveSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLogEntity = new com.ccighgo.db.entities.SeasonProgramUpdateLog();
      DepartmentProgram departmentProgram = departmentProgramRepository.findOne(seasonProgramUpdateLog.getDepartmentProgramId());
      seasonProgramUpdateLogEntity.setDepartmentProgram(departmentProgram);
      seasonProgramUpdateLogEntity.setModifiedBy(1);
      seasonProgramUpdateLogEntity.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      Season season = seasonRepository.findOne(seasonProgramUpdateLog.getSeasonId());
      seasonProgramUpdateLogEntity.setSeason(season);
      seasonProgramUpdateLogEntity.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
      seasonProgramUpdateLogRepository.saveAndFlush(seasonProgramUpdateLogEntity);
      return viewSeasonProgramLog(seasonProgramUpdateLog.getDepartmentProgramId() + "", seasonProgramUpdateLog.getSeasonId() + "");
   }

   @Override
   public List<SeasonProgramUpdateLog> viewHSPF1SeasonProgramLog(String seasonId) {
      List<com.ccighgo.db.entities.SeasonProgramUpdateLog> seasonProgram = seasonProgramUpdateLogRepository.findUpdateLogByDepartmentProgramIdAndSeasonID(CCIConstants.HSP_F1_ID,
            Integer.parseInt(seasonId));
      List<SeasonProgramUpdateLog> listSeasonProgramUpdateLog = new ArrayList<SeasonProgramUpdateLog>();
      if (seasonProgram != null && !seasonProgram.isEmpty()) {
         for (com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLog : seasonProgram) {
            SeasonProgramUpdateLog spu = new SeasonProgramUpdateLog();
            if (seasonProgramUpdateLog.getDepartmentProgram() != null)
               spu.setDepartmentProgramId(seasonProgramUpdateLog.getDepartmentProgram().getDepartmentProgramId());
            spu.setModifiedBy(seasonProgramUpdateLog.getModifiedBy() + "");
            spu.setModifiedOn(DateUtils.getTimeStamp(seasonProgramUpdateLog.getModifiedOn()));
            spu.setSeasonId(seasonProgramUpdateLog.getSeason().getSeasonId());
            spu.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
            listSeasonProgramUpdateLog.add(spu);
         }
      }
      return listSeasonProgramUpdateLog;
   }

   @Override
   public List<SeasonProgramUpdateLog> viewWPCAPSeasonProgramLog(String seasonId) {
      List<com.ccighgo.db.entities.SeasonProgramUpdateLog> seasonProgram = seasonProgramUpdateLogRepository.findUpdateLogByDepartmentProgramIdAndSeasonID(
            CCIConstants.WP_WT_CAP_ID, Integer.parseInt(seasonId));
      List<SeasonProgramUpdateLog> listSeasonProgramUpdateLog = new ArrayList<SeasonProgramUpdateLog>();
      if (seasonProgram != null && !seasonProgram.isEmpty()) {
         for (com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLog : seasonProgram) {
            SeasonProgramUpdateLog spu = new SeasonProgramUpdateLog();
            if (seasonProgramUpdateLog.getDepartmentProgram() != null)
               spu.setDepartmentProgramId(seasonProgramUpdateLog.getDepartmentProgram().getDepartmentProgramId());
            spu.setModifiedBy(seasonProgramUpdateLog.getModifiedBy() + "");
            spu.setModifiedOn(DateUtils.getTimeStamp(seasonProgramUpdateLog.getModifiedOn()));
            spu.setSeasonId(seasonProgramUpdateLog.getSeason().getSeasonId());
            spu.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
            listSeasonProgramUpdateLog.add(spu);
         }
      }
      return listSeasonProgramUpdateLog;
   }

   @Override
   public List<SeasonProgramUpdateLog> viewHSPJ1SeasonProgramLog(String seasonId) {
      List<com.ccighgo.db.entities.SeasonProgramUpdateLog> seasonProgram = seasonProgramUpdateLogRepository.findUpdateLogByDepartmentProgramIdAndSeasonID(
            CCIConstants.HSP_J1_HS_ID, Integer.parseInt(seasonId));
      List<SeasonProgramUpdateLog> listSeasonProgramUpdateLog = new ArrayList<SeasonProgramUpdateLog>();
      if (seasonProgram != null && !seasonProgram.isEmpty()) {
         for (com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLog : seasonProgram) {
            SeasonProgramUpdateLog spu = new SeasonProgramUpdateLog();
            if (seasonProgramUpdateLog.getDepartmentProgram() != null)
               spu.setDepartmentProgramId(seasonProgramUpdateLog.getDepartmentProgram().getDepartmentProgramId());
            spu.setModifiedBy(seasonProgramUpdateLog.getModifiedBy() + "");
            spu.setModifiedOn(DateUtils.getTimeStamp(seasonProgramUpdateLog.getModifiedOn()));
            spu.setSeasonId(seasonProgramUpdateLog.getSeason().getSeasonId());
            spu.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
            listSeasonProgramUpdateLog.add(spu);
         }
      }
      return listSeasonProgramUpdateLog;
   }

   @Override
   public List<SeasonProgramUpdateLog> saveHSPF1SeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLogEntity = new com.ccighgo.db.entities.SeasonProgramUpdateLog();
      DepartmentProgram departmentProgram = departmentProgramRepository.findOne(CCIConstants.HSP_F1_ID);
      seasonProgramUpdateLogEntity.setDepartmentProgram(departmentProgram);
      seasonProgramUpdateLogEntity.setModifiedBy(1);
      seasonProgramUpdateLogEntity.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      Season season = seasonRepository.findOne(seasonProgramUpdateLog.getSeasonId());
      seasonProgramUpdateLogEntity.setSeason(season);
      seasonProgramUpdateLogEntity.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
      seasonProgramUpdateLogRepository.saveAndFlush(seasonProgramUpdateLogEntity);
      return viewSeasonProgramLog(CCIConstants.HSP_F1_ID + "", seasonProgramUpdateLog.getSeasonId() + "");
   }

   @Override
   public List<SeasonProgramUpdateLog> saveWPCAPSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLogEntity = new com.ccighgo.db.entities.SeasonProgramUpdateLog();
      DepartmentProgram departmentProgram = departmentProgramRepository.findOne(CCIConstants.WP_WT_CAP_ID);
      seasonProgramUpdateLogEntity.setDepartmentProgram(departmentProgram);
      seasonProgramUpdateLogEntity.setModifiedBy(1);
      seasonProgramUpdateLogEntity.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      Season season = seasonRepository.findOne(seasonProgramUpdateLog.getSeasonId());
      seasonProgramUpdateLogEntity.setSeason(season);
      seasonProgramUpdateLogEntity.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
      seasonProgramUpdateLogRepository.saveAndFlush(seasonProgramUpdateLogEntity);
      return viewSeasonProgramLog(CCIConstants.WP_WT_CAP_ID + "", seasonProgramUpdateLog.getSeasonId() + "");
   }

   @Override
   public List<SeasonProgramUpdateLog> saveHSPJ1SeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLogEntity = new com.ccighgo.db.entities.SeasonProgramUpdateLog();
      DepartmentProgram departmentProgram = departmentProgramRepository.findOne(CCIConstants.HSP_J1_HS_ID);
      seasonProgramUpdateLogEntity.setDepartmentProgram(departmentProgram);
      seasonProgramUpdateLogEntity.setModifiedBy(1);
      seasonProgramUpdateLogEntity.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      Season season = seasonRepository.findOne(seasonProgramUpdateLog.getSeasonId());
      seasonProgramUpdateLogEntity.setSeason(season);
      seasonProgramUpdateLogEntity.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
      seasonProgramUpdateLogRepository.saveAndFlush(seasonProgramUpdateLogEntity);
      return viewSeasonProgramLog(CCIConstants.HSP_J1_HS_ID + "", seasonProgramUpdateLog.getSeasonId() + "");
   }

   @Override
   public List<SeasonProgramUpdateLog> viewWPSummerSeasonProgramLog(String seasonId) {
      List<com.ccighgo.db.entities.SeasonProgramUpdateLog> seasonProgram = seasonProgramUpdateLogRepository.findUpdateLogByDepartmentProgramIdAndSeasonID(
            CCIConstants.WP_WT_SUMMER_ID, Integer.parseInt(seasonId));
      List<SeasonProgramUpdateLog> listSeasonProgramUpdateLog = new ArrayList<SeasonProgramUpdateLog>();
      if (seasonProgram != null && !seasonProgram.isEmpty()) {
         for (com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLog : seasonProgram) {
            SeasonProgramUpdateLog spu = new SeasonProgramUpdateLog();
            if (seasonProgramUpdateLog.getDepartmentProgram() != null)
               spu.setDepartmentProgramId(seasonProgramUpdateLog.getDepartmentProgram().getDepartmentProgramId());
            spu.setModifiedBy(seasonProgramUpdateLog.getModifiedBy() + "");
            spu.setModifiedOn(DateUtils.getTimeStamp(seasonProgramUpdateLog.getModifiedOn()));
            spu.setSeasonId(seasonProgramUpdateLog.getSeason().getSeasonId());
            spu.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
            listSeasonProgramUpdateLog.add(spu);
         }
      }
      return listSeasonProgramUpdateLog;
   }

   @Override
   public List<SeasonProgramUpdateLog> viewWPWinterSeasonProgramLog(String seasonId) {
      List<com.ccighgo.db.entities.SeasonProgramUpdateLog> seasonProgram = seasonProgramUpdateLogRepository.findUpdateLogByDepartmentProgramIdAndSeasonID(
            CCIConstants.WP_WT_WINTER_ID, Integer.parseInt(seasonId));
      List<SeasonProgramUpdateLog> listSeasonProgramUpdateLog = new ArrayList<SeasonProgramUpdateLog>();
      if (seasonProgram != null && !seasonProgram.isEmpty()) {
         for (com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLog : seasonProgram) {
            SeasonProgramUpdateLog spu = new SeasonProgramUpdateLog();
            if (seasonProgramUpdateLog.getDepartmentProgram() != null)
               spu.setDepartmentProgramId(seasonProgramUpdateLog.getDepartmentProgram().getDepartmentProgramId());
            spu.setModifiedBy(seasonProgramUpdateLog.getModifiedBy() + "");
            spu.setModifiedOn(DateUtils.getTimeStamp(seasonProgramUpdateLog.getModifiedOn()));
            spu.setSeasonId(seasonProgramUpdateLog.getSeason().getSeasonId());
            spu.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
            listSeasonProgramUpdateLog.add(spu);
         }
      }
      return listSeasonProgramUpdateLog;
   }

   @Override
   public List<SeasonProgramUpdateLog> viewWPSpringSeasonProgramLog(String seasonId) {
      List<com.ccighgo.db.entities.SeasonProgramUpdateLog> seasonProgram = seasonProgramUpdateLogRepository.findUpdateLogByDepartmentProgramIdAndSeasonID(
            CCIConstants.WP_WT_SPRING_ID, Integer.parseInt(seasonId));
      List<SeasonProgramUpdateLog> listSeasonProgramUpdateLog = new ArrayList<SeasonProgramUpdateLog>();
      if (seasonProgram != null && !seasonProgram.isEmpty()) {
         for (com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLog : seasonProgram) {
            SeasonProgramUpdateLog spu = new SeasonProgramUpdateLog();
            if (seasonProgramUpdateLog.getDepartmentProgram() != null)
               spu.setDepartmentProgramId(seasonProgramUpdateLog.getDepartmentProgram().getDepartmentProgramId());
            spu.setModifiedBy(seasonProgramUpdateLog.getModifiedBy() + "");
            spu.setModifiedOn(DateUtils.getTimeStamp(seasonProgramUpdateLog.getModifiedOn()));
            spu.setSeasonId(seasonProgramUpdateLog.getSeason().getSeasonId());
            spu.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
            listSeasonProgramUpdateLog.add(spu);
         }
      }
      return listSeasonProgramUpdateLog;
   }

   @Override
   public List<SeasonProgramUpdateLog> saveWPSummerSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLogEntity = new com.ccighgo.db.entities.SeasonProgramUpdateLog();
      DepartmentProgram departmentProgram = departmentProgramRepository.findOne(CCIConstants.WP_WT_SUMMER_ID);
      seasonProgramUpdateLogEntity.setDepartmentProgram(departmentProgram);
      seasonProgramUpdateLogEntity.setModifiedBy(1);
      seasonProgramUpdateLogEntity.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
      Season season = seasonRepository.findOne(seasonProgramUpdateLog.getSeasonId());
      seasonProgramUpdateLogEntity.setSeason(season);
      seasonProgramUpdateLogEntity.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
      seasonProgramUpdateLogRepository.saveAndFlush(seasonProgramUpdateLogEntity);
      return viewSeasonProgramLog(CCIConstants.WP_WT_SUMMER_ID + "", seasonProgramUpdateLog.getSeasonId() + "");
   }

   @Override
   public List<SeasonProgramUpdateLog> saveWPWinterSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLogEntity = new com.ccighgo.db.entities.SeasonProgramUpdateLog();
      DepartmentProgram departmentProgram = departmentProgramRepository.findOne(CCIConstants.WP_WT_WINTER_ID);
      seasonProgramUpdateLogEntity.setDepartmentProgram(departmentProgram);
      seasonProgramUpdateLogEntity.setModifiedBy(1);
      seasonProgramUpdateLogEntity.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
      Season season = seasonRepository.findOne(seasonProgramUpdateLog.getSeasonId());
      seasonProgramUpdateLogEntity.setSeason(season);
      seasonProgramUpdateLogEntity.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
      seasonProgramUpdateLogRepository.saveAndFlush(seasonProgramUpdateLogEntity);
      return viewSeasonProgramLog(CCIConstants.WP_WT_WINTER_ID + "", seasonProgramUpdateLog.getSeasonId() + "");
   }

   @Override
   public List<SeasonProgramUpdateLog> saveWPSpringSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLogEntity = new com.ccighgo.db.entities.SeasonProgramUpdateLog();
      DepartmentProgram departmentProgram = departmentProgramRepository.findOne(CCIConstants.WP_WT_SPRING_ID);
      seasonProgramUpdateLogEntity.setDepartmentProgram(departmentProgram);
      seasonProgramUpdateLogEntity.setModifiedBy(1);
      seasonProgramUpdateLogEntity.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
      Season season = seasonRepository.findOne(seasonProgramUpdateLog.getSeasonId());
      seasonProgramUpdateLogEntity.setSeason(season);
      seasonProgramUpdateLogEntity.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
      seasonProgramUpdateLogRepository.saveAndFlush(seasonProgramUpdateLogEntity);
      return viewSeasonProgramLog(CCIConstants.WP_WT_SPRING_ID + "", seasonProgramUpdateLog.getSeasonId() + "");
   }

   @Override
   public List<SeasonProgramUpdateLog> saveGHTLanguageSchoolSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLogEntity = new com.ccighgo.db.entities.SeasonProgramUpdateLog();
      DepartmentProgram departmentProgram = departmentProgramRepository.findOne(CCIConstants.GHT_LANG_SCL_ID);
      seasonProgramUpdateLogEntity.setDepartmentProgram(departmentProgram);
      seasonProgramUpdateLogEntity.setModifiedBy(1);
      seasonProgramUpdateLogEntity.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
      Season season = seasonRepository.findOne(seasonProgramUpdateLog.getSeasonId());
      seasonProgramUpdateLogEntity.setSeason(season);
      seasonProgramUpdateLogEntity.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
      seasonProgramUpdateLogRepository.saveAndFlush(seasonProgramUpdateLogEntity);
      return viewSeasonProgramLog(CCIConstants.GHT_LANG_SCL_ID + "", seasonProgramUpdateLog.getSeasonId() + "");
   }

   @Override
   public List<SeasonProgramUpdateLog> saveGHTTeachAbroadSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLogEntity = new com.ccighgo.db.entities.SeasonProgramUpdateLog();
      DepartmentProgram departmentProgram = departmentProgramRepository.findOne(CCIConstants.GHT_TEACH_ABRD_ID);
      seasonProgramUpdateLogEntity.setDepartmentProgram(departmentProgram);
      seasonProgramUpdateLogEntity.setModifiedBy(1);
      seasonProgramUpdateLogEntity.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
      Season season = seasonRepository.findOne(seasonProgramUpdateLog.getSeasonId());
      seasonProgramUpdateLogEntity.setSeason(season);
      seasonProgramUpdateLogEntity.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
      seasonProgramUpdateLogRepository.saveAndFlush(seasonProgramUpdateLogEntity);
      return viewSeasonProgramLog(CCIConstants.GHT_TEACH_ABRD_ID + "", seasonProgramUpdateLog.getSeasonId() + "");
   }

   @Override
   public List<SeasonProgramUpdateLog> saveGHTHSAbroadSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLogEntity = new com.ccighgo.db.entities.SeasonProgramUpdateLog();
      DepartmentProgram departmentProgram = departmentProgramRepository.findOne(CCIConstants.GHT_HS_ABRD_ID);
      seasonProgramUpdateLogEntity.setDepartmentProgram(departmentProgram);
      seasonProgramUpdateLogEntity.setModifiedBy(1);
      seasonProgramUpdateLogEntity.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
      Season season = seasonRepository.findOne(seasonProgramUpdateLog.getSeasonId());
      seasonProgramUpdateLogEntity.setSeason(season);
      seasonProgramUpdateLogEntity.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
      seasonProgramUpdateLogRepository.saveAndFlush(seasonProgramUpdateLogEntity);
      return viewSeasonProgramLog(CCIConstants.GHT_HS_ABRD_ID + "", seasonProgramUpdateLog.getSeasonId() + "");
   }

   @Override
   public List<SeasonProgramUpdateLog> saveGHTVOLAbroadSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLogEntity = new com.ccighgo.db.entities.SeasonProgramUpdateLog();
      DepartmentProgram departmentProgram = departmentProgramRepository.findOne(CCIConstants.GHT_VOL_ABRD_ID);
      seasonProgramUpdateLogEntity.setDepartmentProgram(departmentProgram);
      seasonProgramUpdateLogEntity.setModifiedBy(1);
      seasonProgramUpdateLogEntity.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
      Season season = seasonRepository.findOne(seasonProgramUpdateLog.getSeasonId());
      seasonProgramUpdateLogEntity.setSeason(season);
      seasonProgramUpdateLogEntity.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
      seasonProgramUpdateLogRepository.saveAndFlush(seasonProgramUpdateLogEntity);
      return viewSeasonProgramLog(CCIConstants.GHT_VOL_ABRD_ID + "", seasonProgramUpdateLog.getSeasonId() + "");
   }

   @Override
   public List<SeasonProgramUpdateLog> saveGHTWorkAbroadSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLogEntity = new com.ccighgo.db.entities.SeasonProgramUpdateLog();
      DepartmentProgram departmentProgram = departmentProgramRepository.findOne(CCIConstants.GHT_WRK_ABRD_ID);
      seasonProgramUpdateLogEntity.setDepartmentProgram(departmentProgram);
      seasonProgramUpdateLogEntity.setModifiedBy(1);
      seasonProgramUpdateLogEntity.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
      Season season = seasonRepository.findOne(seasonProgramUpdateLog.getSeasonId());
      seasonProgramUpdateLogEntity.setSeason(season);
      seasonProgramUpdateLogEntity.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
      seasonProgramUpdateLogRepository.saveAndFlush(seasonProgramUpdateLogEntity);
      return viewSeasonProgramLog(CCIConstants.GHT_WRK_ABRD_ID + "", seasonProgramUpdateLog.getSeasonId() + "");
   }

   @Override
   public List<SeasonProgramUpdateLog> viewGHTHSAbroadSeasonProgramLog(String seasonId) {
      List<com.ccighgo.db.entities.SeasonProgramUpdateLog> seasonProgram = seasonProgramUpdateLogRepository.findUpdateLogByDepartmentProgramIdAndSeasonID(
            CCIConstants.GHT_HS_ABRD_ID, Integer.parseInt(seasonId));
      List<SeasonProgramUpdateLog> listSeasonProgramUpdateLog = new ArrayList<SeasonProgramUpdateLog>();
      if (seasonProgram != null && !seasonProgram.isEmpty()) {
         for (com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLog : seasonProgram) {
            SeasonProgramUpdateLog spu = new SeasonProgramUpdateLog();
            if (seasonProgramUpdateLog.getDepartmentProgram() != null)
               spu.setDepartmentProgramId(seasonProgramUpdateLog.getDepartmentProgram().getDepartmentProgramId());
            spu.setModifiedBy(seasonProgramUpdateLog.getModifiedBy() + "");
            spu.setModifiedOn(DateUtils.getTimeStamp(seasonProgramUpdateLog.getModifiedOn()));
            spu.setSeasonId(seasonProgramUpdateLog.getSeason().getSeasonId());
            spu.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
            listSeasonProgramUpdateLog.add(spu);
         }
      }
      return listSeasonProgramUpdateLog;
   }

   @Override
   public List<SeasonProgramUpdateLog> viewGHTLanguageSchoolSeasonProgramLog(String seasonId) {
      List<com.ccighgo.db.entities.SeasonProgramUpdateLog> seasonProgram = seasonProgramUpdateLogRepository.findUpdateLogByDepartmentProgramIdAndSeasonID(
            CCIConstants.GHT_LANG_SCL_ID, Integer.parseInt(seasonId));
      List<SeasonProgramUpdateLog> listSeasonProgramUpdateLog = new ArrayList<SeasonProgramUpdateLog>();
      if (seasonProgram != null && !seasonProgram.isEmpty()) {
         for (com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLog : seasonProgram) {
            SeasonProgramUpdateLog spu = new SeasonProgramUpdateLog();
            if (seasonProgramUpdateLog.getDepartmentProgram() != null)
               spu.setDepartmentProgramId(seasonProgramUpdateLog.getDepartmentProgram().getDepartmentProgramId());
            spu.setModifiedBy(seasonProgramUpdateLog.getModifiedBy() + "");
            spu.setModifiedOn(DateUtils.getTimeStamp(seasonProgramUpdateLog.getModifiedOn()));
            spu.setSeasonId(seasonProgramUpdateLog.getSeason().getSeasonId());
            spu.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
            listSeasonProgramUpdateLog.add(spu);
         }
      }
      return listSeasonProgramUpdateLog;
   }

   @Override
   public List<SeasonProgramUpdateLog> viewGHTTeachAbroadSeasonProgramLog(String seasonId) {
      List<com.ccighgo.db.entities.SeasonProgramUpdateLog> seasonProgram = seasonProgramUpdateLogRepository.findUpdateLogByDepartmentProgramIdAndSeasonID(
            CCIConstants.GHT_TEACH_ABRD_ID, Integer.parseInt(seasonId));
      List<SeasonProgramUpdateLog> listSeasonProgramUpdateLog = new ArrayList<SeasonProgramUpdateLog>();
      if (seasonProgram != null && !seasonProgram.isEmpty()) {
         for (com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLog : seasonProgram) {
            SeasonProgramUpdateLog spu = new SeasonProgramUpdateLog();
            if (seasonProgramUpdateLog.getDepartmentProgram() != null)
               spu.setDepartmentProgramId(seasonProgramUpdateLog.getDepartmentProgram().getDepartmentProgramId());
            spu.setModifiedBy(seasonProgramUpdateLog.getModifiedBy() + "");
            spu.setModifiedOn(DateUtils.getTimeStamp(seasonProgramUpdateLog.getModifiedOn()));
            spu.setSeasonId(seasonProgramUpdateLog.getSeason().getSeasonId());
            spu.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
            listSeasonProgramUpdateLog.add(spu);
         }
      }
      return listSeasonProgramUpdateLog;
   }

   @Override
   public List<SeasonProgramUpdateLog> viewGHTVOLAbroadSeasonProgramLog(String seasonId) {
      List<com.ccighgo.db.entities.SeasonProgramUpdateLog> seasonProgram = seasonProgramUpdateLogRepository.findUpdateLogByDepartmentProgramIdAndSeasonID(
            CCIConstants.GHT_VOL_ABRD_ID, Integer.parseInt(seasonId));
      List<SeasonProgramUpdateLog> listSeasonProgramUpdateLog = new ArrayList<SeasonProgramUpdateLog>();
      if (seasonProgram != null && !seasonProgram.isEmpty()) {
         for (com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLog : seasonProgram) {
            SeasonProgramUpdateLog spu = new SeasonProgramUpdateLog();
            if (seasonProgramUpdateLog.getDepartmentProgram() != null)
               spu.setDepartmentProgramId(seasonProgramUpdateLog.getDepartmentProgram().getDepartmentProgramId());
            spu.setModifiedBy(seasonProgramUpdateLog.getModifiedBy() + "");
            spu.setModifiedOn(DateUtils.getTimeStamp(seasonProgramUpdateLog.getModifiedOn()));
            spu.setSeasonId(seasonProgramUpdateLog.getSeason().getSeasonId());
            spu.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
            listSeasonProgramUpdateLog.add(spu);
         }
      }
      return listSeasonProgramUpdateLog;
   }

   @Override
   public List<SeasonProgramUpdateLog> viewGHTWorkAbroadSeasonProgramLog(String seasonId) {
      List<com.ccighgo.db.entities.SeasonProgramUpdateLog> seasonProgram = seasonProgramUpdateLogRepository.findUpdateLogByDepartmentProgramIdAndSeasonID(
            CCIConstants.GHT_WRK_ABRD_ID, Integer.parseInt(seasonId));
      List<SeasonProgramUpdateLog> listSeasonProgramUpdateLog = new ArrayList<SeasonProgramUpdateLog>();
      if (seasonProgram != null && !seasonProgram.isEmpty()) {
         for (com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLog : seasonProgram) {
            SeasonProgramUpdateLog spu = new SeasonProgramUpdateLog();
            if (seasonProgramUpdateLog.getDepartmentProgram() != null)
               spu.setDepartmentProgramId(seasonProgramUpdateLog.getDepartmentProgram().getDepartmentProgramId());
            spu.setModifiedBy(seasonProgramUpdateLog.getModifiedBy() + "");
            spu.setModifiedOn(DateUtils.getTimeStamp(seasonProgramUpdateLog.getModifiedOn()));
            spu.setSeasonId(seasonProgramUpdateLog.getSeason().getSeasonId());
            spu.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
            listSeasonProgramUpdateLog.add(spu);
         }
      }
      return listSeasonProgramUpdateLog;
   }

}

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
   public List<SeasonProgramUpdateLog> viewSeasonProgramLog(String id) {
      List<com.ccighgo.db.entities.SeasonProgramUpdateLog> seasonProgram = seasonProgramUpdateLogRepository.findUpdateLogBySeasonIdOne(Integer.parseInt(id));
      List<SeasonProgramUpdateLog> listSeasonProgramUpdateLog = new ArrayList<SeasonProgramUpdateLog>();
      if (seasonProgram != null && !seasonProgram.isEmpty()) {
         for (com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLog : seasonProgram) {
            SeasonProgramUpdateLog spu = new SeasonProgramUpdateLog();
            if (seasonProgramUpdateLog.getDepartmentProgram() != null)
               spu.setDepartmentProgramId(seasonProgramUpdateLog.getDepartmentProgram().getDepartmentProgramId());
            spu.setModifiedBy(seasonProgramUpdateLog.getModifiedBy() + "");
            spu.setModifiedOn(DateUtils.getMMddyyDate(seasonProgramUpdateLog.getModifiedOn()));
            spu.setSeasonId(seasonProgramUpdateLog.getSeason().getSeasonId());
            spu.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
            listSeasonProgramUpdateLog.add(spu);
         }
         return listSeasonProgramUpdateLog;
      }
      return null;
   }

   @Override
   public List<SeasonDepartmentUpdateLog> viewSeasonDepartmentLog(String id) {
      List<com.ccighgo.db.entities.SeasonDepartmentUpdateLog> seasonDepartmentUpdateLog = seasonDepartmentUpdateLogRepository.findUpdateLogBySeasonId(Integer.parseInt(id));
      List<SeasonDepartmentUpdateLog> listSeasonDepartmentUpdateLogBean = new ArrayList<SeasonDepartmentUpdateLog>();
      if (seasonDepartmentUpdateLog != null && !seasonDepartmentUpdateLog.isEmpty()) {
         for (com.ccighgo.db.entities.SeasonDepartmentUpdateLog seasonDepartmentUpdateLog2 : seasonDepartmentUpdateLog) {
            SeasonDepartmentUpdateLog sdl = new SeasonDepartmentUpdateLog();
            sdl.setModifiedBy(seasonDepartmentUpdateLog2.getModifiedBy() + "");
            sdl.setModifiedOn(DateUtils.getMMddyyDate(seasonDepartmentUpdateLog2.getModifiedOn()));
            sdl.setSeasonId(seasonDepartmentUpdateLog2.getSeason().getSeasonId());
            sdl.setUpdateLogObject(seasonDepartmentUpdateLog2.getUpdateLogObject());
            listSeasonDepartmentUpdateLogBean.add(sdl);
         }
         return listSeasonDepartmentUpdateLogBean;
      }
      return null;
   }

   @Override
   public List<SeasonDepartmentUpdateLog> saveSeasonDepartmentLog(SeasonDepartmentUpdateLog seasonDepartmentUpdateLog) {
      com.ccighgo.db.entities.SeasonDepartmentUpdateLog seasonDepartmentUpdateLogEntity = new com.ccighgo.db.entities.SeasonDepartmentUpdateLog();
      seasonDepartmentUpdateLogEntity.setModifiedBy(1);
      seasonDepartmentUpdateLogEntity.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
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
      seasonProgramUpdateLogEntity.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
      Season season = seasonRepository.findOne(seasonProgramUpdateLog.getSeasonId());
      seasonProgramUpdateLogEntity.setSeason(season);
      seasonProgramUpdateLogEntity.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
      seasonProgramUpdateLogRepository.saveAndFlush(seasonProgramUpdateLogEntity);
      return viewSeasonProgramLog(seasonProgramUpdateLog.getDepartmentProgramId() + "");
   }

   @Override
   public List<SeasonProgramUpdateLog> viewHSPF1SeasonProgramLog() {
      List<com.ccighgo.db.entities.SeasonProgramUpdateLog> seasonProgram = seasonProgramUpdateLogRepository.findUpdateLogBySeasonIdOne(CCIConstants.HSP_F1_ID);
      List<SeasonProgramUpdateLog> listSeasonProgramUpdateLog = new ArrayList<SeasonProgramUpdateLog>();
      if (seasonProgram != null && !seasonProgram.isEmpty()) {
         for (com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLog : seasonProgram) {
            SeasonProgramUpdateLog spu = new SeasonProgramUpdateLog();
            if (seasonProgramUpdateLog.getDepartmentProgram() != null)
               spu.setDepartmentProgramId(seasonProgramUpdateLog.getDepartmentProgram().getDepartmentProgramId());
            spu.setModifiedBy(seasonProgramUpdateLog.getModifiedBy() + "");
            spu.setModifiedOn(DateUtils.getMMddyyDate(seasonProgramUpdateLog.getModifiedOn()));
            spu.setSeasonId(seasonProgramUpdateLog.getSeason().getSeasonId());
            spu.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
            listSeasonProgramUpdateLog.add(spu);
         }
         return listSeasonProgramUpdateLog;
      }
      return null;
   }

   @Override
   public List<SeasonProgramUpdateLog> viewWPCAPSeasonProgramLog() {
      List<com.ccighgo.db.entities.SeasonProgramUpdateLog> seasonProgram = seasonProgramUpdateLogRepository.findUpdateLogBySeasonIdOne(CCIConstants.WP_WT_CAP_ID);
      List<SeasonProgramUpdateLog> listSeasonProgramUpdateLog = new ArrayList<SeasonProgramUpdateLog>();
      if (seasonProgram != null && !seasonProgram.isEmpty()) {
         for (com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLog : seasonProgram) {
            SeasonProgramUpdateLog spu = new SeasonProgramUpdateLog();
            if (seasonProgramUpdateLog.getDepartmentProgram() != null)
               spu.setDepartmentProgramId(seasonProgramUpdateLog.getDepartmentProgram().getDepartmentProgramId());
            spu.setModifiedBy(seasonProgramUpdateLog.getModifiedBy() + "");
            spu.setModifiedOn(DateUtils.getMMddyyDate(seasonProgramUpdateLog.getModifiedOn()));
            spu.setSeasonId(seasonProgramUpdateLog.getSeason().getSeasonId());
            spu.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
            listSeasonProgramUpdateLog.add(spu);
         }
         return listSeasonProgramUpdateLog;
      }
      return null;
   }

   @Override
   public List<SeasonProgramUpdateLog> viewHSPJ1SeasonProgramLog() {
      List<com.ccighgo.db.entities.SeasonProgramUpdateLog> seasonProgram = seasonProgramUpdateLogRepository.findUpdateLogBySeasonIdOne(CCIConstants.HSP_J1_HS_ID);
      List<SeasonProgramUpdateLog> listSeasonProgramUpdateLog = new ArrayList<SeasonProgramUpdateLog>();
      if (seasonProgram != null && !seasonProgram.isEmpty()) {
         for (com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLog : seasonProgram) {
            SeasonProgramUpdateLog spu = new SeasonProgramUpdateLog();
            if (seasonProgramUpdateLog.getDepartmentProgram() != null)
               spu.setDepartmentProgramId(seasonProgramUpdateLog.getDepartmentProgram().getDepartmentProgramId());
            spu.setModifiedBy(seasonProgramUpdateLog.getModifiedBy() + "");
            spu.setModifiedOn(DateUtils.getMMddyyDate(seasonProgramUpdateLog.getModifiedOn()));
            spu.setSeasonId(seasonProgramUpdateLog.getSeason().getSeasonId());
            spu.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
            listSeasonProgramUpdateLog.add(spu);
         }
         return listSeasonProgramUpdateLog;
      }
      return null;
   }

   @Override
   public List<SeasonProgramUpdateLog> saveHSPF1SeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLogEntity = new com.ccighgo.db.entities.SeasonProgramUpdateLog();
      DepartmentProgram departmentProgram = departmentProgramRepository.findOne(CCIConstants.HSP_F1_ID);
      seasonProgramUpdateLogEntity.setDepartmentProgram(departmentProgram);
      seasonProgramUpdateLogEntity.setModifiedBy(1);
      seasonProgramUpdateLogEntity.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
      Season season = seasonRepository.findOne(seasonProgramUpdateLog.getSeasonId());
      seasonProgramUpdateLogEntity.setSeason(season);
      seasonProgramUpdateLogEntity.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
      seasonProgramUpdateLogRepository.saveAndFlush(seasonProgramUpdateLogEntity);
      return viewSeasonProgramLog(CCIConstants.HSP_F1_ID + "");
   }

   @Override
   public List<SeasonProgramUpdateLog> saveWPCAPSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLogEntity = new com.ccighgo.db.entities.SeasonProgramUpdateLog();
      DepartmentProgram departmentProgram = departmentProgramRepository.findOne(CCIConstants.WP_WT_CAP_ID);
      seasonProgramUpdateLogEntity.setDepartmentProgram(departmentProgram);
      seasonProgramUpdateLogEntity.setModifiedBy(1);
      seasonProgramUpdateLogEntity.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
      Season season = seasonRepository.findOne(seasonProgramUpdateLog.getSeasonId());
      seasonProgramUpdateLogEntity.setSeason(season);
      seasonProgramUpdateLogEntity.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
      seasonProgramUpdateLogRepository.saveAndFlush(seasonProgramUpdateLogEntity);
      return viewSeasonProgramLog(CCIConstants.WP_WT_CAP_ID + "");
   }

   @Override
   public List<SeasonProgramUpdateLog> saveHSPJ1SeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      com.ccighgo.db.entities.SeasonProgramUpdateLog seasonProgramUpdateLogEntity = new com.ccighgo.db.entities.SeasonProgramUpdateLog();
      DepartmentProgram departmentProgram = departmentProgramRepository.findOne(CCIConstants.HSP_J1_HS_ID);
      seasonProgramUpdateLogEntity.setDepartmentProgram(departmentProgram);
      seasonProgramUpdateLogEntity.setModifiedBy(1);
      seasonProgramUpdateLogEntity.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
      Season season = seasonRepository.findOne(seasonProgramUpdateLog.getSeasonId());
      seasonProgramUpdateLogEntity.setSeason(season);
      seasonProgramUpdateLogEntity.setUpdateLogObject(seasonProgramUpdateLog.getUpdateLogObject());
      seasonProgramUpdateLogRepository.saveAndFlush(seasonProgramUpdateLogEntity);
      return viewSeasonProgramLog(CCIConstants.HSP_J1_HS_ID + "");
   }

}

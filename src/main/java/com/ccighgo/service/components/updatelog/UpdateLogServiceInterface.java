package com.ccighgo.service.components.updatelog;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.seasons.beans.seasondepartmentupdatelog.SeasonDepartmentUpdateLog;
import com.ccighgo.service.transport.seasons.beans.seasonprogramupdatelog.SeasonProgramUpdateLog;

@Service
public interface UpdateLogServiceInterface {

   public List<SeasonProgramUpdateLog> viewSeasonProgramLog(String programId, String seasonId);

   public List<SeasonDepartmentUpdateLog> viewSeasonDepartmentLog(String id);

   public List<SeasonDepartmentUpdateLog> saveSeasonDepartmentLog(SeasonDepartmentUpdateLog seasonDepartmentUpdateLog);

   public List<SeasonProgramUpdateLog> saveSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog);

   public List<SeasonProgramUpdateLog> viewHSPF1SeasonProgramLog(String seasonId);

   public List<SeasonProgramUpdateLog> viewWPCAPSeasonProgramLog(String seasonId);

   public List<SeasonProgramUpdateLog> viewHSPJ1SeasonProgramLog(String seasonId);

   public List<SeasonProgramUpdateLog> saveHSPF1SeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog);

   public List<SeasonProgramUpdateLog> saveWPCAPSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog);

   public List<SeasonProgramUpdateLog> saveHSPJ1SeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog);

   public List<SeasonProgramUpdateLog> viewWPSummerSeasonProgramLog(String seasonId);

   public List<SeasonProgramUpdateLog> viewWPWinterSeasonProgramLog(String seasonId);

   public List<SeasonProgramUpdateLog> viewWPSpringSeasonProgramLog(String seasonId);

   public List<SeasonProgramUpdateLog> saveWPSummerSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog);

   public List<SeasonProgramUpdateLog> saveWPWinterSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog);

   public List<SeasonProgramUpdateLog> saveWPSpringSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog);

   public List<SeasonProgramUpdateLog> saveGHTLanguageSchoolSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog);

   public List<SeasonProgramUpdateLog> saveGHTTeachAbroadSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog);

   public List<SeasonProgramUpdateLog> saveGHTHSAbroadSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog);

   public List<SeasonProgramUpdateLog> saveGHTVOLAbroadSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog);

   public List<SeasonProgramUpdateLog> saveGHTWorkAbroadSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog);

   public List<SeasonProgramUpdateLog> viewGHTHSAbroadSeasonProgramLog(String seasonId);

   public List<SeasonProgramUpdateLog> viewGHTLanguageSchoolSeasonProgramLog(String seasonId);

   public List<SeasonProgramUpdateLog> viewGHTTeachAbroadSeasonProgramLog(String seasonId);

   public List<SeasonProgramUpdateLog> viewGHTVOLAbroadSeasonProgramLog(String seasonId);

   public List<SeasonProgramUpdateLog> viewGHTWorkAbroadSeasonProgramLog(String seasonId);

   public List<SeasonProgramUpdateLog> saveIHPSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog);

   public List<SeasonProgramUpdateLog> viewIHPSeasonProgramLog(String seasonId);

}

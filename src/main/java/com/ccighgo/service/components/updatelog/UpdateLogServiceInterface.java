package com.ccighgo.service.components.updatelog;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.seasons.beans.seasondepartmentupdatelog.SeasonDepartmentUpdateLog;
import com.ccighgo.service.transport.seasons.beans.seasonprogramupdatelog.SeasonProgramUpdateLog;

@Service
public interface UpdateLogServiceInterface {

   public List<SeasonProgramUpdateLog> viewSeasonProgramLog(String id);

   public List<SeasonDepartmentUpdateLog> viewSeasonDepartmentLog(String id);

   public List<SeasonDepartmentUpdateLog> saveSeasonDepartmentLog(SeasonDepartmentUpdateLog seasonDepartmentUpdateLog);

   public List<SeasonProgramUpdateLog> saveSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog);

}

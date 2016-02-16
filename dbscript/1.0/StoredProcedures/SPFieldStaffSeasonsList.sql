DELIMITER $$

USE `cci_gh_go`$$

DROP PROCEDURE IF EXISTS `SPFieldStaffSeasonsList`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPFieldStaffSeasonsList`(IN fieldStaffId INT)
BEGIN 
	DECLARE fGoId INT;
	DECLARE typeId INT;
	
	SET @fGoId = fieldStaffId;
	
	SELECT fsl.filedStaffSeasonId AS fieldStaffSeasonId,fsl.seasonId,fsl.departmentProgramId,ps.programName AS seasonName,ps.`startDate` AS StartDate,ps.`endDate` AS EndDate,ss.status AS seasonStatus,fss.fieldStaffStatusName AS FieldStaffStatus,
		ld.departmentId,dp.programName,fst.fieldStaffTypeName,ld.acronym
		FROM `ProgramSeasons` ps
		INNER JOIN SeasonStatus ss ON  ps.programStatusId = ss.seasonStatusId
		INNER JOIN `FieldStaffSeason` fsl ON ps.`seasonId` = fsl.`seasonId` AND ps.departmentProgramId = fsl.departmentProgramId
		INNER JOIN `FieldStaff` fs ON  fs.fieldStaffGoId = fsl.fieldStaffGoId		
		INNER JOIN `FieldStaffStatus` fss ON fss.fieldStaffStatusId = fsl.fieldStaffSeasonStatusId
		INNER JOIN Season s ON fsl.seasonId = s.seasonId AND ps.seasonId = s.seasonId	
		INNER JOIN LookupDepartments ld ON ld.departmentId = s.departmentId
		INNER JOIN FieldStaffType fst ON fst.fieldStaffTypeId = fs.fieldStaffTypeId
		INNER JOIN DepartmentPrograms dp ON ld.departmentId = dp.departmentId AND ps.departmentProgramId = dp.departmentProgramId
		WHERE fs.`fieldStaffGoId` = @fGoId AND fsl.active = 1
		GROUP BY fsl.seasonId,fsl.departmentProgramId;
                             
END$$

DELIMITER ;
DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPFieldStaffSeasonsList`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPFieldStaffSeasonsList`(IN fieldStaffId INT)
BEGIN 
	DECLARE fGoId INT;
	DECLARE typeId INT;
	
	SET @fGoId = fieldStaffId;
	SET @typeId = (SELECT fieldStaffTypeId FROM `FieldStaff` WHERE fieldStaffGoId = @fGoId);
	
	IF @typeId IN (5,2,3) THEN
		SELECT ps.programName AS seasonName,ps.`startDate` AS StartDate,ps.`endDate` AS EndDate,ss.status AS seasonStatus,fs.fieldStaffStatusName AS FieldStaffStatus
		FROM `ProgramSeasons` ps
		INNER JOIN SeasonStatus ss ON  ps.programStatusId = ss.seasonStatusId
		INNER JOIN `FieldStaffSeason` fsl ON ps.`seasonId` = fsl.`seasonId` AND ps.departmentProgramId = fsl.departmentProgramId
		INNER JOIN `FieldStaffLeadershipSeason` fsls ON fsls.seasonId = fsl.seasonId AND fsls.fieldStaffGoId = fsl.fieldStaffGoId		
		INNER JOIN `FieldStaffStatus` fs ON fs.fieldStaffStatusId = fsl.fieldStaffSeasonStatusId	
		WHERE fsls.`fieldStaffGoId` = @fGoId; 
                             
	END IF;
	
	IF @typeId IN (4,1) THEN
		
		SELECT ps.programName AS seasonName,ps.`startDate` AS StartDate,ps.`endDate` AS EndDate,ss.status AS seasonStatus,fs.fieldStaffStatusName AS FieldStaffStatus
		FROM `ProgramSeasons` ps
		INNER JOIN SeasonStatus ss ON  ps.programStatusId = ss.seasonStatusId
		INNER JOIN `FieldStaffSeason` fsl ON ps.`seasonId` = fsl.`seasonId` AND ps.departmentProgramId = fsl.departmentProgramId
		INNER JOIN FieldStaff f ON f.fieldStaffGoId = fsl.fieldStaffGoId AND f.fieldStaffTypeId IN (1,4)
		WHERE fsl.`fieldStaffGoId` = @fGoId;
                             
	END IF; 
END$$

DELIMITER ;
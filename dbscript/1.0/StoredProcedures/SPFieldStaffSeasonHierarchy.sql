DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPFieldStaffSeasonHierarchy`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPFieldStaffSeasonHierarchy`(IN fieldStaffId INT)
BEGIN 
	DECLARE roleId,sgcId,ssnId INT;  
	DECLARE c_done INT DEFAULT 0;
	DECLARE goId,sId,pgmId INT;
	SET @goId = fieldStaffId;
--	SET @sId = seasnId;
--	SET @pgmId = depPgmId;	
		
	SET @roleId = (SELECT fieldStaffTypeId FROM FieldStaff WHERE fieldStaffGoId = @goId);
	
	DROP TEMPORARY TABLE IF EXISTS FSHierarchy;
	CREATE TEMPORARY TABLE  FSHierarchy (fsGoId INT,firstName VARCHAR(150),lastName VARCHAR (150),fsType VARCHAR (50),photo VARCHAR(200),season VARCHAR(100),seasonStatus VARCHAR(50));
	
	CASE @roleId
	WHEN 5 THEN
		BEGIN
			SELECT  fsl.seasonId AS seasonId,
				fsl.departmentProgramId AS departmentProgramId,
				ps.programName AS seasonName,
				ss.status AS seasonStatus
			
			FROM `ProgramSeasons` ps
			INNER JOIN SeasonStatus ss ON  ps.programStatusId = ss.seasonStatusId
			INNER JOIN `FieldStaffSeason` fsl ON ps.`seasonId` = fsl.`seasonId` AND ps.departmentProgramId = fsl.departmentProgramId
			WHERE fsl.`fieldStaffGoId` = @goId AND fsl.active = 1;
			
			
				
		END;
		
	WHEN 4 THEN
		BEGIN
				SELECT  sal.seasonId AS seasonId,
				sal.departmentProgramId AS departmentProgramId,
				sal.season AS season,
--				COALESCE(sal.seasonId,sel.seasonId,sdl.seasonId,sml.seasonId) AS seasonId,
--				COALESCE(sal.departmentProgramId,sel.departmentProgramId,sdl.departmentProgramId,sml.departmentProgramId) AS departmentProgramId,
--				COALESCE(sal.season,sel.season,sdl.season,sml.season) AS season,
--				GROUP_CONCAT(DISTINCT sal.firstName) AS acFirstName,
--				GROUP_CONCAT(DISTINCT sal.lastName) AS acLastName,
				GROUP_CONCAT(DISTINCT sel.firstName SEPARATOR ';') AS erdFirstName,
				GROUP_CONCAT(DISTINCT sel.lastName SEPARATOR ';') AS erdLastName,
				GROUP_CONCAT(DISTINCT sdl.firstName SEPARATOR ';') AS rdFirstName,
				GROUP_CONCAT(DISTINCT sdl.lastName SEPARATOR ';') AS rdLastName,
				GROUP_CONCAT(DISTINCT sml.firstName SEPARATOR ';') AS rmFirstName,	
				GROUP_CONCAT(DISTINCT sml.lastName SEPARATOR ';') AS rmLastName,
--				GROUP_CONCAT(DISTINCTsal.photo AS acPhoto,
				GROUP_CONCAT(DISTINCT IFNULL(sel.photo,'NoPhoto') SEPARATOR ';') AS erdPhoto,
				GROUP_CONCAT(DISTINCT IFNULL(sdl.photo,'NoPhoto') SEPARATOR ';') AS rdPhoto,
				GROUP_CONCAT(DISTINCT IFNULL(sml.photo,'NoPhoto') SEPARATOR ';') AS rmPhoto
				FROM `SeasonACList` sal 
				LEFT JOIN `SeasonERDList` sel ON (sal.seasonId = sel.seasonId AND sal.departmentProgramId = sel.departmentProgramId AND sal.superRegionId = sel.superRegionId) AND sel.regionId IS NULL AND sel.usStatesId IS NULL
				LEFT JOIN `SeasonRDList` sdl ON (sal.seasonId = sdl.seasonId AND sal.departmentProgramId = sdl.departmentProgramId AND sal.superRegionId = sdl.superRegionId AND sal.regionId = sdl.regionId)AND sdl.usStatesId IS NULL
				LEFT JOIN `SeasonRMList` sml ON (sal.seasonId = sml.seasonId AND sal.departmentProgramId = sml.departmentProgramId AND sal.superRegionId = sml.superRegionId AND sal.regionId = sml.regionId) AND sml.usStatesId IS NULL
				WHERE sal.fieldStaffGoId = @goId 
				GROUP BY seasonId,departmentProgramId;
		END;
		
	WHEN 3 THEN
		BEGIN
				SELECT  sdl.seasonId AS seasonId,
				sdl.departmentProgramId AS departmentProgramId,
				sdl.season AS season,
--				COALESCE(sal.seasonId,sel.seasonId,sdl.seasonId,sml.seasonId) AS seasonId,
--				COALESCE(sal.departmentProgramId,sel.departmentProgramId,sdl.departmentProgramId,sml.departmentProgramId) AS departmentProgramId,
--				COALESCE(sal.season,sel.season,sdl.season,sml.season) AS season,
--				GROUP_CONCAT(DISTINCT sal.firstName) AS acFirstName,
--				GROUP_CONCAT(DISTINCT sal.lastName) AS acLastName,
				GROUP_CONCAT(DISTINCT sel.firstName SEPARATOR ';') AS erdFirstName,
				GROUP_CONCAT(DISTINCT sel.lastName SEPARATOR ';') AS erdLastName,
--				GROUP_CONCAT(DISTINCT sdl.firstName) AS rdFirstName,
--				GROUP_CONCAT(DISTINCT sdl.lastName) AS rdLastName,
--				GROUP_CONCAT(DISTINCT sml.firstName) AS rmFirstName,	
--				GROUP_CONCAT(DISTINCT sml.lastName) AS rmLastName,
--				GROUP_CONCAT(DISTINCTsal.photo AS acPhoto,
				GROUP_CONCAT(DISTINCT IFNULL(sel.photo,'NoPhoto') SEPARATOR ';') AS erdPhoto
--				GROUP_CONCAT(DISTINCT IFNULL(sdl.photo,'NoPhoto') SEPARATOR ';') AS rdPhoto,
--				GROUP_CONCAT(DISTINCT IFNULL(sml.photo,'NoPhoto') SEPARATOR ';') AS rmPhoto
				FROM  `SeasonRDList` sdl
				LEFT JOIN `SeasonERDList` sel ON (sel.seasonId = sdl.seasonId AND sel.departmentProgramId = sdl.departmentProgramId AND sdl.superRegionId = sel.superRegionId) AND sel.regionId IS NULL AND sel.usStatesId IS NULL
				WHERE sdl.fieldStaffGoId = @goId 
				GROUP BY seasonId,departmentProgramId;
				
		END;
    
    WHEN 2 THEN
		BEGIN
				SELECT  sml.seasonId AS seasonId,
				sml.departmentProgramId AS departmentProgramId,
				sml.season AS season,
--				COALESCE(sal.seasonId,sel.seasonId,sdl.seasonId,sml.seasonId) AS seasonId,
--				COALESCE(sal.departmentProgramId,sel.departmentProgramId,sdl.departmentProgramId,sml.departmentProgramId) AS departmentProgramId,
--				COALESCE(sal.season,sel.season,sdl.season,sml.season) AS season,
--				GROUP_CONCAT(DISTINCT sal.firstName) AS acFirstName,
--				GROUP_CONCAT(DISTINCT sal.lastName) AS acLastName,
				GROUP_CONCAT(DISTINCT sel.firstName SEPARATOR ';') AS erdFirstName,
				GROUP_CONCAT(DISTINCT sel.lastName SEPARATOR ';') AS erdLastName,
				GROUP_CONCAT(DISTINCT sdl.firstName SEPARATOR ';') AS rdFirstName,
				GROUP_CONCAT(DISTINCT sdl.lastName SEPARATOR ';') AS rdLastName,
--				GROUP_CONCAT(DISTINCT sml.firstName) AS rmFirstName,	
--				GROUP_CONCAT(DISTINCT sml.lastName) AS rmLastName,
--				GROUP_CONCAT(DISTINCTsal.photo AS acPhoto,
				GROUP_CONCAT(DISTINCT IFNULL(sel.photo,'NoPhoto') SEPARATOR ';') AS erdPhoto,
				GROUP_CONCAT(DISTINCT IFNULL(sdl.photo,'NoPhoto') SEPARATOR ';') AS rdPhoto
--				GROUP_CONCAT(DISTINCT IFNULL(sml.photo,'NoPhoto') SEPARATOR ';') AS rmPhoto
				FROM `SeasonRMList` sml 
				LEFT JOIN `SeasonERDList` sel ON (sml.seasonId = sel.seasonId AND sml.departmentProgramId = sel.departmentProgramId AND sml.superRegionId = sel.superRegionId) AND sel.regionId IS NULL AND sel.usStatesId IS NULL
				LEFT JOIN `SeasonRDList` sdl ON (sml.seasonId = sdl.seasonId AND sml.departmentProgramId = sdl.departmentProgramId AND sml.superRegionId = sdl.superRegionId AND sml.regionId = sdl.regionId)AND sdl.usStatesId IS NULL
				WHERE sml.fieldStaffGoId = 50012
				GROUP BY seasonId,departmentProgramId;
				
		END;
   	WHEN 1 THEN
	BEGIN
			
				
				SELECT  sal.seasonId AS seasonId,
				sal.departmentProgramId AS departmentProgramId,
				sal.season AS season,
--				COALESCE(sal.seasonId,sel.seasonId,sdl.seasonId,sml.seasonId) AS seasonId,
--				COALESCE(sal.departmentProgramId,sel.departmentProgramId,sdl.departmentProgramId,sml.departmentProgramId) AS departmentProgramId,
--				COALESCE(sal.season,sel.season,sdl.season,sml.season) AS season,
				GROUP_CONCAT(DISTINCT sal.firstName SEPARATOR ';') AS acFirstName,
				GROUP_CONCAT(DISTINCT sal.lastName SEPARATOR ';') AS acLastName,
				GROUP_CONCAT(DISTINCT sel.firstName SEPARATOR ';') AS erdFirstName,
				GROUP_CONCAT(DISTINCT sel.lastName SEPARATOR ';') AS erdLastName,
				GROUP_CONCAT(DISTINCT sdl.firstName SEPARATOR ';') AS rdFirstName,
				GROUP_CONCAT(DISTINCT sdl.lastName SEPARATOR ';') AS rdLastName,
				GROUP_CONCAT(DISTINCT sml.firstName SEPARATOR ';') AS rmFirstName,	
				GROUP_CONCAT(DISTINCT sml.lastName SEPARATOR ';') AS rmLastName,
				GROUP_CONCAT(DISTINCT IFNULL(sal.photo,'NoPhoto') SEPARATOR ';') AS acPhoto,
				GROUP_CONCAT(DISTINCT IFNULL(sel.photo,'NoPhoto') SEPARATOR ';') AS erdPhoto,
				GROUP_CONCAT(DISTINCT IFNULL(sdl.photo,'NoPhoto') SEPARATOR ';') AS rdPhoto,
				GROUP_CONCAT(DISTINCT IFNULL(sml.photo,'NoPhoto') SEPARATOR ';') AS rmPhoto
				FROM `SeasonACList` sal 
				LEFT JOIN `SeasonERDList` sel ON (sal.seasonId = sel.seasonId AND sal.departmentProgramId = sel.departmentProgramId AND sal.superRegionId = sel.superRegionId) AND sel.regionId IS NULL AND sel.usStatesId IS NULL
				LEFT JOIN `SeasonRDList` sdl ON (sal.seasonId = sdl.seasonId AND sal.departmentProgramId = sdl.departmentProgramId AND sal.superRegionId = sdl.superRegionId AND sal.regionId = sdl.regionId)AND sdl.usStatesId IS NULL
				LEFT JOIN `SeasonRMList` sml ON (sal.seasonId = sml.seasonId AND sal.departmentProgramId = sml.departmentProgramId AND sal.superRegionId = sml.superRegionId AND sal.regionId = sml.regionId) AND sml.usStatesId IS NULL
				WHERE sal.fieldStaffGoId = 50039 
				GROUP BY seasonId,departmentProgramId;
				
	END;			
		
         
	ELSE
		BEGIN
		END;
	END CASE;
END$$

DELIMITER ;
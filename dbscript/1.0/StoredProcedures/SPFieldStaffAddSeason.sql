DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPFieldStaffAddSeason`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPFieldStaffAddSeason`(IN fieldStaffId INT)
BEGIN
	DECLARE goId INT;
	DECLARE typeId,fslscount,fsnscount INT;
	SET @goId = fieldStaffId;
	/*-- SET @typeId = (SELECT fieldStaffTypeId FROM `FieldStaff` WHERE fieldStaffGoId = @goId);
	-- IF @typeId IN (5,2,3) THEN
		
		SET @scount = (SELECT COUNT(*) FROM `FieldStaffSeason` WHERE fieldStaffGoId = @goId);
		
		IF (@scount = 0) THEN
		
			SELECT psv.seasonId,psv.departmentProgramId,psv.ProgramName AS SeasonName,st.status AS SeasonStatus FROM ProgramSeasons psv
			INNER JOIN `SeasonStatus` st ON psv.programStatusId = seasonStatusId
			WHERE psv.seasonId NOT IN (SELECT fsld.seasonId FROM  `FieldStaffSeason` fsld 
					        fsld.seasonId = psv.seasonId AND fsld.departmentProgramId = psv.departmentProgramId
			AND fsl.fieldStaffGoId = @goId) AND psv.startDate > CURRENT_TIMESTAMP AND psv.departmentProgramId IN (1,2);
		 END IF;
		*/
	-- ELSEIF @typeId IN (1,4) THEN
		
		SET @fsnscount = (SELECT COUNT(*) FROM `FieldStaffSeason` WHERE fieldStaffGoId = @goId);
		
		IF (@fsnscount = 0) THEN
			
			SELECT 	
				psv.seasonId,
				psv.departmentProgramId,
				psv.ProgramName AS SeasonName,
				st.status AS SeasonStatus
			FROM ProgramSeasons psv 
			INNER JOIN Season s ON psv.seasonId = s.seasonId
			INNER JOIN SeasonStatus st ON psv.programStatusId = st.seasonStatusId
			WHERE s.departmentId IN (1)
			AND psv.startDate > CURRENT_DATE;
		 ELSE
			
			SELECT 	psv.seasonId,
				psv.departmentProgramId,
				psv.ProgramName AS SeasonName,
				st.status AS SeasonStatus 
			FROM ProgramSeasons psv
			INNER JOIN Season s ON s.seasonId = psv.seasonId
			INNER JOIN `SeasonStatus` st ON psv.programStatusId = st.seasonStatusId
			WHERE (psv.seasonId,psv.departmentProgramId) NOT IN (SELECT fsld.seasonId,fsld.departmentProgramId
									     FROM  `FieldStaffSeason` fsld  
									     WHERE  fsld.fieldStaffGoId = @goId) 
			AND psv.startDate > CURRENT_DATE 
			AND s.departmentId IN (1);
		 END IF;
	 
END$$

DELIMITER ;
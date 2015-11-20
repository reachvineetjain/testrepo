DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPLookupActiveSeasonSearch`$$

CREATE DEFINER=`phanipothula`@`%` PROCEDURE `SPLookupActiveSeasonSearch`()
BEGIN
		SELECT seasonId,programName,departmentProgramId 
		FROM ProgramSeasons 
		WHERE programStatusId = 1
		ORDER BY seasonId;
	
    END$$

DELIMITER ;
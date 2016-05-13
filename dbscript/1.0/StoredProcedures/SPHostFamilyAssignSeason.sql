DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPHostFamilyAssignSeason`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPHostFamilyAssignSeason`(IN hfId INT)
BEGIN
    
         DECLARE hfGoId INT;
         SET @hfGoId = hfGoId;
         
         
       
       SELECT psv.programName AS Season
       FROM `ProgramSeasons` psv 
       WHERE (psv.seasonId , psv.departmentProgramId) NOT IN
       (SELECT seasonId,departmentProgramId FROM `HostFamilySeason` hfs 
       WHERE hfs.`seasonId` = psv.`seasonId` AND hfs.`departmentProgramId` = psv.`departmentProgramId`
       AND hfs.hostFamilyGoId = @hfGoId)
       AND psv.startDate > CURRENT_TIMESTAMP;
    END$$

DELIMITER ;
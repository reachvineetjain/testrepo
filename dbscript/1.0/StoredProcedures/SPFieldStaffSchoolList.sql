DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPFieldStaffSchoolList`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPFieldStaffSchoolList`(IN fieldStaffId INT)
BEGIN
    
                       SELECT 1 /*us.`usSchoolId`*/ AS SchoolId,
                              'Governer Thomas Schoo' AS SchoolName,
                              'Fredrick' AS SchoolCity,
                              'MD' AS SchoolState,
                              'Cindy Holy' AS ContactName,
                              '240-345-6789' AS ContactPhone,
                              'sss@ss.ss' AS ContactEmail
           
                       FROM `USSchool` us;
   --                    inner join `USSchoolSeason` uss on uss.`usSchoolId` = us.`usSchoolId`
    --                   inner join `ProgramSeasons` ps on ps.seasonId = uss.seasonId 
    END$$

DELIMITER ;
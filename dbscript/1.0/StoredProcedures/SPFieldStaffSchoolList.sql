DELIMITER $$

USE `cci_gh_go`$$

DROP PROCEDURE IF EXISTS `SPFieldStaffSchoolList`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPFieldStaffSchoolList`(IN fieldStaffId INT)
BEGIN
    
                       SELECT 1  AS SchoolId,
                              'Governer Thomas Schoo' AS SchoolName,
                              'Fredrick' AS SchoolCity,
                              'MD' AS SchoolState,
                              'Cindy Holy' AS ContactName,
                              '240-345-6789' AS ContactPhone,
                              'sss@ss.ss' AS ContactEmail
           
                       FROM `USSchool` us;
   
    
    END$$

DELIMITER ;
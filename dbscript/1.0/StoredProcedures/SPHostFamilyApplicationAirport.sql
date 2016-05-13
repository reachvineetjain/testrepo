DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPHostFamilyApplicationAirport`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPHostFamilyApplicationAirport`(IN hostfamilyId INT,IN ssId INT,IN deptprgmId INT)
BEGIN
    
    DECLARE hfId,sId,dpId INT;
   
    SET @hfId = hostfamilyId;
    SET @sId = ssId;
    SET @dpId = deptprgmId;
    
    IF (@sId IS NOT NULL AND @dpId IS NOT NULL) THEN
    
        SELECT a.airportCity,
               hfa.`distanceToAirport`,
               hfa.`hostFamilyAirportId`
               
         FROM `HostFamilyAirport` hfa
         INNER JOIN `Airport` a ON a.`airportId` = hfa.`airportId`
         INNER JOIN `HostFamily` hf ON hf.`hostFamilyGoId` = hfa.`hostFamilyGoId` 
         INNER JOIN HostFamilySeason hfs ON hfs.`hostFamilyGoId` = hf.`hostFamilyGoId`   
         WHERE hfs.`hostFamilyGoId` = @hfId AND hfs.`seasonId` = @sId  AND hfs.`departmentProgramId` = @dpId;
        
     END IF;
     
     IF (@sId IS NULL AND @dpId IS NULL) THEN 
     
        SELECT a.`airportCity`,
               hfa.`distanceToAirport`,
               hfa.`hostFamilyAirportId`
               
         FROM `HostFamilyAirport` hfa
         INNER JOIN `Airport` a ON a.`airportId` = hfa.`airportId`
         INNER JOIN `HostFamily` hf ON hf.`hostFamilyGoId` = hfa.`hostFamilyGoId` 
         INNER JOIN HostFamilySeason hfs ON hfs.`hostFamilyGoId` = hf.`hostFamilyGoId` AND hfs.`seasonId` = hf.`currentSeasonId`  
         WHERE hfs.`hostFamilyGoId` = @hfId ;
         
     END IF;
    END$$

DELIMITER ;
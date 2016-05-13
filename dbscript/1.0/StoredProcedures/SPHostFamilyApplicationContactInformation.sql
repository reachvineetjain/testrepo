DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPHostFamilyApplicationContactInformation`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPHostFamilyApplicationContactInformation`(IN hostfamilyId INT,IN ssId INT,IN deptprgmId INT)
BEGIN
    
        DECLARE hfId,sId,dpId INT;
        SET @hfId = hostfamilyId;
        SET @sId = ssId;
        SET @dpId = deptprgmId;
        
            IF (@sId IS NULL AND @dpId IS NULL) THEN
               
		SELECT MAX(hfs.seasonId) INTO @sId FROM HostFamilySeason hfs WHERE hfs.hostFamilyGoId = @hfId;
		SELECT MAX(hfs.departmentProgramId) INTO @dpId FROM HostFamilySeason hfs WHERE hfs.seasonId = @sId AND hfs.hostFamilyGoId = @hfId;
            
            END IF;    
                
                SELECT hf.`haveAHomePhone`,
                       hf.`homePhone`,
                       hf.`preferredContactMethodPhone`,
                       hf.`preferredContactMethodEmail`,
                       hf.`preferredPhone`,
                       hf.`preferredEmail`,
                       hf.`emergencyContact`,
                       hf.`emergencyPhone`,
                       hf.`physicalAddress`,
                       hf.`physicalCity`,
                       hf.`physicalStateId` AS physicalState,
                       hf.`physicalZipCode`,
                       hf.`mailingAddress`,
                       hf.`mailingCity`,
                       hf.`mailingStateId` AS mailingState,
                       hf.`mailingZipCode`,
                       hf.`mailingAddressSameAsCurrentAddress`,
                       hfs.seasonId,
                       hfs.departmentProgramId,
                       hfs.hostFamilySeasonId
                 FROM `HostFamily` hf
                 INNER JOIN `HostFamilySeason` hfs ON hf.`hostFamilyGoId` = hfs.`hostFamilyGoId`
                 INNER JOIN `LookupUSStates` us1 ON us1.`usStatesId` = hf.`physicalStateId`
                 INNER JOIN `LookupUSStates` us2 ON us2.usStatesId = hf.`mailingStateId`
                 WHERE hfs.`hostFamilyGoId` = @hfId AND hfs.`seasonId` = @sId AND hfs.`departmentProgramId` = @dpId;
           
   
    END$$

DELIMITER ;
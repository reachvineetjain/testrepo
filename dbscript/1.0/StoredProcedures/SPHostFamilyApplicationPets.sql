DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPHostFamilyApplicationPets`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPHostFamilyApplicationPets`(IN hostfamilyId INT,IN ssId INT,IN deptprgmId INT)
BEGIN
    
        DECLARE hfId,sId,dpId INT;
   
        SET @hfId = hostfamilyId;
        SET @sId = ssId;
        SET @dpId = deptprgmId;
        
    IF (@sId IS NULL AND @dpId IS NULL) THEN
    
		SELECT MAX(hfs.seasonId) INTO @sId FROM HostFamilySeason hfs WHERE hfs.hostFamilyGoId = @hfId;
		SELECT MAX(hfs.departmentProgramId) INTO @dpId FROM HostFamilySeason hfs WHERE hfs.seasonId = @sId AND hfs.hostFamilyGoId = @hfId;
    END IF;        
         
         SELECT 
		hfpt.`hostFamilyPetTypeId`,
                hfp.`number`,
                hfp.`isIndoor`,
                hfp.`isOutdoor`,
                hfp.`additionalInformation`,
                hfp.`hostFamilyPetId`,
                hfs.seasonId,
                hfs.departmentProgramId,
                hfs.hostFamilySeasonId
         FROM `HostFamilyPet` hfp
         INNER JOIN `HostFamilyPetType` hfpt ON hfp.`animalTypeId` = hfpt.`hostFamilyPetTypeId`
         INNER JOIN `HostFamilySeason` hfs ON hfs.`hostFamilySeasonId` = hfp.`hostFamilySeasonId`
         WHERE hfs.`hostFamilyGoId` = @hfId AND hfs.`seasonId` = @sId AND hfs.`departmentProgramId` = @dpId;
         
    
    END$$

DELIMITER ;
DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPHostFamilyApplicationFamilyBasics`$$

CREATE DEFINER=`phanipothula`@`%` PROCEDURE `SPHostFamilyApplicationFamilyBasics`(IN hostfamilyId INT,IN ssId INT,IN deptprgmId INT)
BEGIN
    
    DECLARE hfId,sId,dpId INT;
   
    SET @hfId = hostfamilyId;
    SET @sId = ssId;
    SET @dpId = deptprgmId;
  
    
    IF (@sId IS NULL AND @dpId IS NULL) THEN
    
		SELECT MAX(hfs.seasonId) INTO @sId FROM HostFamilySeason hfs WHERE hfs.hostFamilyGoId = @hfId;
		SELECT MAX(hfs.departmentProgramId) INTO @dpId FROM HostFamilySeason hfs WHERE hfs.seasonId = @sId AND hfs.hostFamilyGoId = @hfId;
    END IF;
        
         SELECT hfp.`filePath` AS familyPhoto,
                hfm.`isSingleAdult`,
                hfm.`relationship`,
                hfm.`firstName`,
                hfm.`lastName`,
                hfm.`isHostParent`,
                hfm.`memberEmail`,
                hfm.`memberPhone`,
                hfm.`birthDate`,
                hfm.`genderId`,
                hfm.`educationLevel`,
                hfm.`livingAtHome`,
                hfm.`livingAtHomeExplanation`,
                hfm.`communityInvolvement`,
                hfm.`interests`,
                hfm.`employed`,
                hfm.`employer1`,
                hfm.`jobTitle1`,
                hfm.`contactName1`,
                hfm.`phone1`,
                hfm.`haveAnotherJob`,
                hfm.`employer2`,
                hfm.`jobTitle2`,
                hfm.`contactName2`,
                hfm.`phone2`,
                hfp.`hostFamilyPhotoId`,
                hfm.`hostFamilyMemberId`,
                hfs.seasonId,
                hfs.departmentProgramId,
                hfs.hostFamilySeasonId,
                hfp.description
        FROM `HostFamilyPhotos` hfp
        INNER JOIN `HostFamilyMember` hfm ON hfm.`hostFamilySeasonId` = hfp.`hostFamilySeasonId`
        INNER JOIN `HostFamilySeason` hfs ON hfs.`hostFamilySeasonId` = hfm.`hostFamilySeasonId`
        WHERE hfs.`hostFamilyGoId` = @hfId AND hfs.`seasonId` = @sId AND hfs.`departmentProgramId` = @dpId AND hfp.hostFamilyPhotoTypeId = 1
        GROUP BY hfm.`hostFamilyMemberId`;
    END$$

DELIMITER ;
DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPHostFamilyApplicationFamilyBasics`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPHostFamilyApplicationFamilyBasics`(IN hostfamilyId INT,IN ssId INT,IN deptprgmId INT)
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
                hfm.`residencyTime`,
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
                hfp.description,
                hfm.employmentType
        FROM `HostFamilyMember` hfm
        INNER JOIN `HostFamilySeason` hfs ON hfs.`hostFamilySeasonId` = hfm.`hostFamilySeasonId`
	LEFT JOIN `HostFamilyPhotos` hfp ON hfm.`hostFamilySeasonId` = hfp.`hostFamilySeasonId` AND hfp.hostFamilyPhotoTypeId = 1
        WHERE hfs.`hostFamilyGoId` = @hfId AND hfs.`seasonId` = @sId AND hfs.`departmentProgramId` = @dpId 
        GROUP BY hfm.`hostFamilyMemberId`;
    END$$

DELIMITER ;
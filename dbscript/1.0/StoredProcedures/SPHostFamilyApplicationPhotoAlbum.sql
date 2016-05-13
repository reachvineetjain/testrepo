DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPHostFamilyApplicationPhotoAlbum`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPHostFamilyApplicationPhotoAlbum`(IN hostfamilyId INT,IN ssId INT,IN deptprgmId INT)
BEGIN
    DECLARE hfId,sId,dpId INT;
   
    SET @hfId = hostfamilyId;
    SET @sId = ssId;
    SET @dpId = deptprgmId;
    
      IF (@sId IS NULL AND @dpId IS NULL) THEN
    
		SELECT MAX(hfs.seasonId) INTO @sId FROM HostFamilySeason hfs WHERE hfs.hostFamilyGoId = @hfId;
		SELECT MAX(hfs.departmentProgramId) INTO @dpId FROM HostFamilySeason hfs WHERE hfs.seasonId = @sId AND hfs.hostFamilyGoId = @hfId;
      END IF;   
      
      
         SELECT hfpt.`hostFamilyPhotoTypeName`,
                hfp.`description`,
                hfp.`isOptional`,
                hfs.seasonId,
                hfs.departmentProgramId,
                hfs.hostFamilySeasonId
         FROM `HostFamilyPhotos` hfp
         INNER JOIN `HostFamilyPhotosType` hfpt ON hfp.`hostFamilyPhotoTypeId` = hfpt.`hostFamilyPhotoTypeId`
         INNER JOIN `HostFamilySeason` hfs ON hfs.`hostFamilySeasonId` = hfp.`hostFamilySeasonId`
         WHERE hfs.`hostFamilyGoId` = @hfId AND hfs.`seasonId` = @sId AND hfs.`departmentProgramId` = @dpId
         GROUP BY hfpt.`hostFamilyPhotoTypeName`,hfp.`description`,hfp.`isOptional`,hfs.seasonId,hfs.departmentProgramId,hfs.hostFamilySeasonId;
         
         
    END$$

DELIMITER ;
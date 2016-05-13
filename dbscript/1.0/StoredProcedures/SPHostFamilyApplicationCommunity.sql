DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPHostFamilyApplicationCommunity`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPHostFamilyApplicationCommunity`(IN hostfamilyId INT,IN ssId INT,IN deptprgmId INT)
BEGIN
    DECLARE hfId,sId,dpId INT;
   
    SET @hfId = hostfamilyId;
    SET @sId = ssId;
    SET @dpId = deptprgmId;
    
    IF (@sId IS  NULL AND @dpId IS  NULL) THEN
    
		SELECT MAX(hfs.seasonId) INTO @sId FROM HostFamilySeason hfs WHERE hfs.hostFamilyGoId = @hfId;
		SELECT MAX(hfs.departmentProgramId) INTO @dpId FROM HostFamilySeason hfs WHERE hfs.seasonId = @sId AND hfs.hostFamilyGoId = @hfId;
    END IF;
    
    
          SELECT hfc.`population`,
                 hfc.`cityWebsite`,
                 hfc.`nearestCity`,
                 hfc.`nearestCityPopulation`,
                 hfc.`distanceFromCity`,
                 hfc.`uniquenessAboutCommunity`,
                 hfc.`placesOfInterest`,
                 hfc.`areasToAvoid`,
                 hfc.`volunteeringOpportunitiesCommunity`,
                 hfs.seasonId,
                 hfs.departmentProgramId,
                 hfs.hostFamilySeasonId,
                 hfc.hostFamilyCommunityId
          FROM `HostFamilyCommunity` hfc
          INNER JOIN `HostFamilySeason` hfs ON hfs.`hostFamilySeasonId` = hfc.`hostFamilySeasonId`
          WHERE hfs.`hostFamilyGoId` = @hfId AND hfs.`seasonId` = @sId AND hfs.`departmentProgramId` = @dpId;
     
    END$$

DELIMITER ;
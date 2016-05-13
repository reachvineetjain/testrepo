DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPHostFamilyApplicationSchoolLife`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPHostFamilyApplicationSchoolLife`(IN hostfamilyId INT,IN ssId INT,IN deptprgmId INT)
BEGIN
    
    DECLARE hfId,sId,dpId INT;
   
    SET @hfId = hostfamilyId;
    SET @sId = ssId;
    SET @dpId = deptprgmId;
    
    IF (@sId IS NULL AND @dpId IS NULL) THEN
    
		SELECT MAX(hfs.seasonId) INTO @sId FROM HostFamilySeason hfs WHERE hfs.hostFamilyGoId = @hfId;
		SELECT MAX(hfs.departmentProgramId) INTO @dpId FROM HostFamilySeason hfs WHERE hfs.seasonId = @sId AND hfs.hostFamilyGoId = @hfId;
    END IF;        
    
    
       SELECT hfc.`schoolTravelMethod`,
               hfc.`distanceToSchool`,
               hfc.`transportationToActivities`,
               hfc.`transportationToActivitiesDetails`,
               hfc.`childrenEnrolled`,
               hfc.`childrenActivities`,
               hfc.`contactACoach`,
               hfc.`parentIsTeacher`,
               hfc.`contactByCoachDetails`,
               hfs.seasonId,
               hfs.departmentProgramId,
               hfs.hostFamilySeasonId,
               hfc.hostFamilyCommunityId
        FROM `HostFamilyCommunity` hfc
        INNER JOIN `HostFamilySeason` hfs ON hfs.`hostFamilySeasonId` = hfc.`hostFamilySeasonId`
        WHERE hfs.`hostFamilyGoId` = @hfId AND hfs.`seasonId` = @sId AND hfs.`departmentProgramId` = @dpId;
        
    
    END$$

DELIMITER ;
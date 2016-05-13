DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPHostFamilyApplicationHosting`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPHostFamilyApplicationHosting`(IN hostfamilyId INT,IN ssId INT,IN deptprgmId INT)
BEGIN
    
    DECLARE hfId,sId,dpId INT;
   
    SET @hfId = hostfamilyId;
    SET @sId = ssId;
    SET @dpId = deptprgmId;
    
    IF (@sId IS NULL AND @dpId IS NULL) THEN
    
		SELECT MAX(hfs.seasonId) INTO @sId FROM HostFamilySeason hfs WHERE hfs.hostFamilyGoId = @hfId;
		SELECT MAX(hfs.departmentProgramId) INTO @dpId FROM HostFamilySeason hfs WHERE hfs.seasonId = @sId AND hfs.hostFamilyGoId = @hfId;
   END IF;
       
        SELECT hfh.`hostingReason`,
               hfh.`hopeToLearn`,
               hfh.`extraActivities`,
               hfh.`localCoordinatorOther`,
               hfh.`localCoordinatorDetails`,
               hfh.`hostedOther`,
               hfh.`hostedOtherDetails`,
               hfh.`studentsResponsibilities`,
               hfs.seasonId,
               hfs.departmentProgramId,
               hfs.hostFamilySeasonId
         FROM `HostFamilyHome` hfh
         INNER JOIN `HostFamilySeason` hfs ON hfs.`hostFamilySeasonId` = hfh.`hostFamilySeasonId`
         WHERE hfs.`hostFamilyGoId` = @hfId AND hfs.`seasonId` = @sId AND hfs.`departmentProgramId` = @dpId;
         
    END$$

DELIMITER ;
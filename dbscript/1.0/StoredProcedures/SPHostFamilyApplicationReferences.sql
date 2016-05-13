DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPHostFamilyApplicationReferences`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPHostFamilyApplicationReferences`(IN hostfamilyId INT,IN ssId INT,IN deptprgmId INT)
BEGIN
          DECLARE hfId,sId,dpId INT;
   
          SET @hfId = hostfamilyId;
          SET @sId = ssId;
          SET @dpId = deptprgmId;
    
          IF (@sId IS NULL AND @dpId IS NULL) THEN
          
		SELECT MAX(hfs.seasonId) INTO @sId FROM HostFamilySeason hfs WHERE hfs.hostFamilyGoId = @hfId;
		SELECT MAX(hfs.departmentProgramId) INTO @dpId FROM HostFamilySeason hfs WHERE hfs.seasonId = @sId AND hfs.hostFamilyGoId = @hfId;
          
          END IF;
          
                SELECT hfr.`firstName`,
                       hfr.`lastName`,
                       hfr.`address`,
                       hfr.`city`,
                       us.`stateName`,
                       hfr.`zipCode`,
                       hfr.`phone`,
                       hfr.`relationship`,
                       hfr.`personNotRelatedToBlood`,
                       hfr.`secondReferenceRelationtoFirst`,
                       hfs.seasonId,
                       hfs.departmentProgramId,
                       hfs.hostFamilySeasonId
               FROM `HostFamilyReference` hfr 
               INNER JOIN `HostFamilySeason` hfs ON hfs.`hostFamilySeasonId` = hfr.`hostFamilySeasonId`
               INNER JOIN `LookupUSStates` us ON us.`usStatesId` = hfr.`usStateId`
               WHERE hfs.`hostFamilyGoId` = @hfId AND hfs.`seasonId` = @sId AND hfs.`departmentProgramId` = @dpId;
          
    END$$

DELIMITER ;
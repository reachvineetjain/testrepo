DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SpHostFamilyPreAppSeasonAssign`$$

CREATE DEFINER=`surender.sarwa`@`%` PROCEDURE `SpHostFamilyPreAppSeasonAssign`(IN pHFGoId INT(10))
BEGIN
-- ===================================================================================================
SELECT hostFor INTO @HFPref FROM HostFamily WHERE hostFamilyGoId = pHFGoId;
SELECT seasonId INTO @LatestSeason FROM Season WHERE departmentId = 1 AND seasonStatusId= 1 ORDER BY createdOn DESC LIMIT 1;
SELECT @HFPref, @LatestSeason;
-- ===================================================================================================
IF @HFPref = 'A Short Term' THEN
	INSERT INTO HostFamilySeason (hostFamilyGoId,seasonId,departmentProgramId,hostFamilySeasonStatusId)
	SELECT pHFGoId, @LatestSeason, 3, 2;
ELSE
	INSERT INTO HostFamilySeason (hostFamilyGoId,seasonId,departmentProgramId,hostFamilySeasonStatusId)
	SELECT pHFGoId, @LatestSeason, 1, 2;
END IF;
-- ===================================================================================================
-- ===================================================================================================
END$$

DELIMITER ;
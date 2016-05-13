DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPRegionAndFSRollover`$$

CREATE DEFINER=`surender.sarwa`@`%` PROCEDURE `SPRegionAndFSRollover`(IN pNewSeasonId INT(10))
BEGIN
SET @pSeasonId = pNewSeasonId;
SET @OldSeasonId= (SELECT SeasonId FROM `Season` WHERE departmentid=1 ORDER BY seasonid DESC LIMIT 1,1);
-- SELECT @pSeasonId,  @OldSeasonId;
-- ====================================================================================
-- Insert data in SeasonGeographyConfiguration Table
-- SELECT MAX(`seasonId`) INTO @pSeasonId FROM SeasonGeographyConfiguration;
/* select superRegionId, regionId, usStatesId, @pSeasonId+1 AS  seasonId, Now() as CreatedOn,createdBy, now() as modifiedOn, modifiedBy
from SeasonGeographyConfiguration where seasonId = @pSeasonId
order by seasonGeographyConfigurationId asc; */
DROP TEMPORARY TABLE IF EXISTS Temp_seasonRollover;
CREATE TEMPORARY TABLE Temp_seasonRollover (
  seasonGeographyConfigurationId INT(11) NOT NULL AUTO_INCREMENT,
  old_seasonGeographyConfigurationId INT(11),
  superRegionId INT(3) DEFAULT NULL,
  regionId INT(3) DEFAULT NULL,
  usStatesId INT(3) DEFAULT NULL,
  seasonId INT(11) DEFAULT NULL,
  createdOn TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  createdBy INT(11) NOT NULL,
  modifiedOn TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  modifiedBy INT(11) NOT NULL,
  PRIMARY KEY (seasonGeographyConfigurationId)
) ENGINE=MEMORY;
-- =========================================================================
INSERT INTO Temp_seasonRollover (seasonGeographyConfigurationId,superRegionId,regionId,usStatesId,seasonId,createdOn,createdBy,modifiedOn,modifiedBy)
SELECT * FROM SeasonGeographyConfiguration ORDER BY seasonGeographyConfigurationId DESC LIMIT 1;
DELETE FROM Temp_seasonRollover WHERE seasonGeographyConfigurationId <> '';
INSERT INTO Temp_seasonRollover (old_seasonGeographyConfigurationId,superRegionId,regionId,usStatesId,seasonId,createdOn,createdBy,modifiedOn,modifiedBy)
SELECT seasonGeographyConfigurationId, superRegionId, regionId, usStatesId, @pSeasonId AS  seasonId, NOW() AS CreatedOn,createdBy, NOW() AS modifiedOn, modifiedBy
FROM SeasonGeographyConfiguration WHERE seasonId = @OldSeasonId
ORDER BY seasonGeographyConfigurationId ASC;
-- SELECT * FROM Temp_seasonRollover;
INSERT INTO SeasonGeographyConfiguration 
SELECT seasonGeographyConfigurationId, superRegionId, regionId, usStatesId, seasonId, createdOn, createdBy, modifiedOn, modifiedBy
FROM Temp_seasonRollover;
-- ====================================================================================
-- Insert data into FieldStaffLeadershipSeason
DROP TEMPORARY TABLE IF EXISTS Temp_FslRollover;
CREATE TEMPORARY TABLE Temp_FslRollover (
  fieldStaffLeadershipSeasonId INT(11) NOT NULL AUTO_INCREMENT,
  fieldStaffGoId INT(11) DEFAULT NULL,
  seasonId INT(11) DEFAULT NULL,
  old_seasonGeographyConfigurationId INT(11) DEFAULT NULL,
  new_seasonGeographyConfigurationId INT(11) DEFAULT NULL,
  createdBy INT(11) DEFAULT NULL,
  createdOn DATETIME DEFAULT NULL,
  modifiedBy INT(11) DEFAULT NULL,
  modifiedOn DATETIME DEFAULT NULL,
  PRIMARY KEY (fieldStaffLeadershipSeasonId)
) ENGINE=MEMORY ;
INSERT INTO Temp_FslRollover (`fieldStaffLeadershipSeasonId`,`fieldStaffGoId`,`seasonId`,`old_seasonGeographyConfigurationId`,`createdBy`,`createdOn`,`modifiedBy`,`modifiedOn`)
SELECT * FROM FieldStaffLeadershipSeason ORDER BY fieldStaffLeadershipSeasonId DESC LIMIT 1;
DELETE FROM Temp_FslRollover WHERE fieldStaffLeadershipSeasonId<> '';
-- =========================================================================
INSERT INTO Temp_FslRollover (fieldStaffGoId,seasonId,old_seasonGeographyConfigurationId,createdBy,createdOn, modifiedBy, modifiedOn)
SELECT B.fieldStaffGoId, @pSeasonId AS  seasonId, B.seasonGeographyConfigurationId, B.createdBy, NOW() AS CreatedBy, 
B.modifiedBy, NOW() AS ModifiedOn
FROM SeasonGeographyConfiguration A
INNER JOIN FieldStaffLeadershipSeason B ON (A.seasonId=B.seasonId AND A.seasonGeographyConfigurationId = B.seasonGeographyConfigurationId)
WHERE A.seasonId = @OldSeasonId;
-- =========================================================================
UPDATE Temp_FslRollover A, Temp_seasonRollover B
SET A.new_seasonGeographyConfigurationId = B.seasonGeographyConfigurationId
WHERE A.old_seasonGeographyConfigurationId=B.old_seasonGeographyConfigurationId;
-- SELECT * FROM Temp_FslRollover;
INSERT INTO FieldStaffLeadershipSeason
SELECT fieldStaffLeadershipSeasonId, fieldStaffGoId, seasonId, new_seasonGeographyConfigurationId, createdBy, createdOn, modifiedBy, modifiedOn
FROM Temp_FslRollover;
-- ====================================================================================
END$$

DELIMITER ;
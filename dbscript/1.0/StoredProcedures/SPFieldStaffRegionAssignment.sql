DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPFieldStaffRegionAssignment`$$

CREATE DEFINER=`surender.sarwa`@`%` PROCEDURE `SPFieldStaffRegionAssignment`(IN pSeasonId INT(10), IN pFlag INT)
BEGIN
-- ====================================================================================
IF pFlag=1 THEN
	SELECT IFNULL(FSL.seasonId,pSeasonId) AS seasonId, /* FSL.seasonGeographyConfigurationId, */ FS.fieldStaffGoId, FS.firstName, FS.lastName,
	-- GROUP_CONCAT(DISTINCT SGC.superRegionId) AS superRegionId
	GROUP_CONCAT(SR.superRegionName) superRegionName
	FROM FieldStaffLeadershipSeason FSL
	INNER JOIN SeasonGeographyConfiguration SGC ON (FSL.seasonGeographyConfigurationId= SGC.seasonGeographyConfigurationId AND FSL.seasonId= pSeasonId AND regionId IS NULL AND usStatesId IS NULL)
	RIGHT JOIN FieldStaff FS ON (FSL.fieldStaffGoId= FS.fieldStaffGoId )
	LEFT JOIN SuperRegion SR ON (SR.superRegionId= SGC.superRegionId)
	WHERE fieldStaffTypeId=5 
	GROUP BY FS.fieldStaffGoId;
ELSE 
	IF pFlag=2 THEN
		SELECT seasonId, fieldStaffGoId, firstName, lastName, 
		fieldStaffTypeName, GROUP_CONCAT(DISTINCT regionName) regionName, GROUP_CONCAT(DISTINCT stateCode) stateCode
		FROM(
		SELECT IFNULL(FSL.seasonId,pSeasonId) AS seasonId, /* FSL.seasonGeographyConfigurationId, */ FS.fieldStaffGoId, 
		FS.firstName, FS.lastName, FS.fieldStaffTypeId,
		IF(SGC.usStatesId IS NOT NULL, NULL, R.regionId) AS regionId, IF(SGC.usStatesId IS NOT NULL, NULL, R.regionName) AS regionName,
		SGC.usStatesId AS usStatesId, US.stateCode AS stateCode,
		FST.fieldStaffTypeName
		FROM FieldStaffLeadershipSeason FSL
		INNER JOIN SeasonGeographyConfiguration SGC ON (FSL.seasonGeographyConfigurationId= SGC.seasonGeographyConfigurationId AND FSL.seasonId= pSeasonId)
		RIGHT JOIN FieldStaff FS ON (FSL.fieldStaffGoId= FS.fieldStaffGoId )
		LEFT JOIN Region R ON (R.regionId = SGC.regionId)
		LEFT JOIN LookupUSStates US ON (US.usStatesId=SGC.usStatesId)
		LEFT JOIN FieldStaffType FST ON  (FST.fieldStaffTypeId = FS.fieldStaffTypeId)
		WHERE FS.fieldStaffTypeId =3
		-- GROUP BY FS.fieldStaffGoId
		UNION ALL
		SELECT IFNULL(FSL.seasonId,pSeasonId) AS seasonId, /* FSL.seasonGeographyConfigurationId, */ FS.fieldStaffGoId, 
		FS.firstName, FS.lastName, FS.fieldStaffTypeId,
		R.regionId AS regionId, R.regionName AS regionName,
		SGC.usStatesId AS usStatesId, US.stateCode AS stateCode,
		FST.fieldStaffTypeName
		FROM FieldStaffLeadershipSeason FSL
		INNER JOIN SeasonGeographyConfiguration SGC ON (FSL.seasonGeographyConfigurationId= SGC.seasonGeographyConfigurationId AND FSL.seasonId= pSeasonId)
		RIGHT JOIN FieldStaff FS ON (FSL.fieldStaffGoId= FS.fieldStaffGoId )
		LEFT JOIN Region R ON (R.regionId = SGC.regionId)
		LEFT JOIN LookupUSStates US ON (US.usStatesId=SGC.usStatesId)
		LEFT JOIN FieldStaffType FST ON  (FST.fieldStaffTypeId = FS.fieldStaffTypeId)
		WHERE FS.fieldStaffTypeId =3
		-- GROUP BY FS.fieldStaffGoId
		) TAB1
		GROUP BY fieldStaffGoId;
	
	ELSE
		IF pFlag=3 THEN
			SELECT seasonId, fieldStaffGoId, firstName, lastName, 
			fieldStaffTypeName, GROUP_CONCAT(DISTINCT regionName) regionName, GROUP_CONCAT(DISTINCT stateCode) stateCode
			FROM(
			
			SELECT IFNULL(FSL.seasonId,pSeasonId) AS seasonId, /* FSL.seasonGeographyConfigurationId, */ FS.fieldStaffGoId, 
			FS.firstName, FS.lastName, FS.fieldStaffTypeId,
			IF(SGC.usStatesId IS NOT NULL, NULL, R.regionId) AS regionId, IF(SGC.usStatesId IS NOT NULL, NULL, R.regionName) AS regionName,
			SGC.usStatesId AS usStatesId, US.stateCode AS stateCode,
			FST.fieldStaffTypeName
			FROM FieldStaffLeadershipSeason FSL
			INNER JOIN SeasonGeographyConfiguration SGC ON (FSL.seasonGeographyConfigurationId= SGC.seasonGeographyConfigurationId AND FSL.seasonId= pSeasonId)
			RIGHT JOIN FieldStaff FS ON (FSL.fieldStaffGoId= FS.fieldStaffGoId )
			LEFT JOIN Region R ON (R.regionId = SGC.regionId)
			LEFT JOIN LookupUSStates US ON (US.usStatesId=SGC.usStatesId)
			LEFT JOIN FieldStaffType FST ON  (FST.fieldStaffTypeId = FS.fieldStaffTypeId)
			WHERE SGC.usStatesId IS NOT NULL AND FS.fieldStaffTypeId IN (2,3,4)
			-- GROUP BY FS.fieldStaffGoId
			
			UNION 
			
			SELECT IFNULL(FSL.seasonId,pSeasonId) AS seasonId, /* FSL.seasonGeographyConfigurationId, */ FS.fieldStaffGoId, 
			FS.firstName, FS.lastName, FS.fieldStaffTypeId,
			R.regionId AS regionId, R.regionName AS regionName,
			SGC.usStatesId AS usStatesId, US.stateCode AS stateCode,
			FST.fieldStaffTypeName
			FROM FieldStaffLeadershipSeason FSL
			INNER JOIN SeasonGeographyConfiguration SGC ON (FSL.seasonGeographyConfigurationId= SGC.seasonGeographyConfigurationId AND FSL.seasonId= pSeasonId)
			RIGHT JOIN FieldStaff FS ON (FSL.fieldStaffGoId= FS.fieldStaffGoId )
			LEFT JOIN Region R ON (R.regionId = SGC.regionId)
			LEFT JOIN LookupUSStates US ON (US.usStatesId=SGC.usStatesId)
			LEFT JOIN FieldStaffType FST ON  (FST.fieldStaffTypeId = FS.fieldStaffTypeId)
			WHERE SGC.usStatesId IS NULL AND FS.fieldStaffTypeId IN (2,3,4)
			-- GROUP BY FS.fieldStaffGoId
			
			) TAB1
			GROUP BY fieldStaffGoId;
		END IF;
	END IF;
END IF;
-- ====================================================================================
END$$

DELIMITER ;
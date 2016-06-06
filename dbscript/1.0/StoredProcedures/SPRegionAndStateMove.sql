DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPRegionAndStateMove`$$

CREATE DEFINER=`surender.sarwa`@`%` PROCEDURE `SPRegionAndStateMove`(IN pSourceSRId INT(10), IN pSourceRegionId INT(10), IN pSourceStateId INT(10), IN pSeasonId INT(10), IN pDestSRId INT(10), IN pDestRegionId INT(10), pFlag INT(10))
BEGIN
-- ===================================================================================================
-- pFlag : 0 (Move State into different region)
-- pFlag : 1 (Move Region and all its states in different SuperRegion)
-- ===================================================================================================
IF IFNULL(pDestRegionId, '') = '' THEN
	SET pDestRegionId = NULL;
END IF;
IF IFNULL(pSourceStateId, '') = '' THEN
	SET pSourceStateId = NULL;
END IF;
-- ===================================================================================================
-- pFlag = 0 for STATE
IF IFNULL(pFlag , '') = 0 THEN
	SET @SQL= CONCAT_WS('','UPDATE SeasonGeographyConfiguration
	SET superRegionId = ',pDestSRId,',
	regionId = ',pDestRegionId,'
	WHERE superRegionId = ',pSourceSRId,' AND regionId = ',pSourceRegionId,' AND usStatesId = ',pSourceStateId,' AND seasonId= ',pSeasonId);
	-- SELECT @SQL;
	PREPARE STMT FROM @SQL;
	EXECUTE STMT;
ELSE
	IF IFNULL(pFlag , '') = 1 THEN
		SET @SQL= CONCAT_WS('','UPDATE SeasonGeographyConfiguration
		SET superRegionId = ',pDestSRId,'
		WHERE superRegionId = ',pSourceSRId,' AND regionId = ',pSourceRegionId,' AND seasonId= ',pSeasonId);
		-- SELECT @SQL;
		PREPARE STMT FROM @SQL;
		EXECUTE STMT;
	END IF;
	
END IF;
-- ===================================================================================================
END$$

DELIMITER ;
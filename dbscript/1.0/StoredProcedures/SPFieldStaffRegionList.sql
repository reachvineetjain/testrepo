DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPFieldStaffRegionList`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPFieldStaffRegionList`(IN fieldStaffId INT)
BEGIN
    
	DECLARE goId,typeId,seasnId INT;
	
    
	SET @goId = fieldStaffId;
	SET @typeId = (SELECT `fieldStaffTypeId` FROM `FieldStaff` WHERE `fieldStaffGoId` = @goId);
	SET @seasnId = (SELECT MAX(`seasonId`) FROM `FieldStaffLeadershipSeason` WHERE `fieldStaffGoId` = @goId);
	
	CASE @typeId
	WHEN 5 THEN
	 
	           SELECT `superRegionId`,`regionId`,`usStatesId` INTO @srId,@rId,@usId 
	           FROM `SeasonGeographyConfiguration` sgc 
	           INNER JOIN `FieldStaffLeadershipSeason` fsl ON fsl.`seasonGeographyConfigurationId` = sgc.`seasonGeographyConfigurationId`
	           WHERE fsl.`fieldStaffGoId` = @goId AND fsl.`seasonId` = @seasnId;
	                             
	           SELECT  GROUP_CONCAT(DISTINCT sp.`superRegionName`) AS superRegion,
					           GROUP_CONCAT(DISTINCT  r.`regionName`) AS region,
					            GROUP_CONCAT(DISTINCT us.`stateName`) AS States
		   FROM `SuperRegion` sp
		   INNER JOIN `SeasonGeographyConfiguration` sgc ON sgc.`superRegionId` = sp.`superRegionId`
		   INNER JOIN `Region` r ON r.`regionId` = sgc.`regionId`
		   INNER JOIN `LookupUSStates` us ON sgc.`usStatesId` = us.`usStatesId`
		   INNER JOIN `FieldStaffLeadershipSeason` fls ON fls.`seasonId` = sgc.`seasonId`
		   WHERE fls.`fieldStaffGoId` = @goId AND fls.`seasonId` = @seasnId
		   AND sgc.superRegionId = @srId;
		    				
		 
	WHEN 3 THEN
	
		       SELECT `superRegionId`,`regionId`,`usStatesId` INTO @srId,@rId,@usId 
	           FROM `SeasonGeographyConfiguration` sgc 
	           INNER JOIN `FieldStaffLeadershipSeason` fsl ON fsl.`seasonGeographyConfigurationId` = sgc.`seasonGeographyConfigurationId`
	           WHERE fsl.`fieldStaffGoId` = @goId AND fsl.`seasonId` = @seasnId;
	                
					IF (@usId IS NULL) THEN
					
						
						SELECT  GROUP_CONCAT(DISTINCT sp.`superRegionName`) AS superRegion,
					           GROUP_CONCAT(DISTINCT  r.`regionName`) AS region,
					            GROUP_CONCAT(DISTINCT us.`stateName`) AS States
					    FROM `Region` r 
						INNER JOIN `SeasonGeographyConfiguration` sgc ON r.`regionId` = sgc.`regionId`
						INNER JOIN `LookupUSStates` us ON us.`usStatesId` = sgc.`usStatesId`
						INNER JOIN `SuperRegion` sp ON sgc.`superRegionId` = sp.`superRegionId`
						INNER JOIN `FieldStaffLeadershipSeason` fls ON fls.`seasonId` = sgc.`seasonId` 
				        WHERE fls.`fieldStaffGoId` = @goId AND fls.`seasonId` = @seasnId
				        AND sgc.superRegionId = @srId AND sgc.regionId = @rId;
                    END IF;
	WHEN 2 THEN
	
		       SELECT `superRegionId`,`regionId`,`usStatesId` INTO @srId,@rId,@usId 
	           FROM `SeasonGeographyConfiguration` sgc 
	           INNER JOIN `FieldStaffLeadershipSeason` fsl ON fsl.`seasonGeographyConfigurationId` = sgc.`seasonGeographyConfigurationId`
	           WHERE fsl.`fieldStaffGoId` = @goId AND fsl.`seasonId` = @seasnId;	
	                
					IF (@usId IS NULL) THEN
					
						
						SELECT  GROUP_CONCAT(DISTINCT sp.`superRegionName`) AS superRegion,
					           GROUP_CONCAT(DISTINCT  r.`regionName`) AS region,
					            GROUP_CONCAT(DISTINCT us.`stateName`) AS States
					    FROM `Region` r 
						INNER JOIN `SeasonGeographyConfiguration` sgc ON r.`regionId` = sgc.`regionId`
						INNER JOIN `LookupUSStates` us ON us.`usStatesId` = sgc.`usStatesId`
						INNER JOIN `SuperRegion` sp ON sgc.`superRegionId` = sp.`superRegionId`
						INNER JOIN `FieldStaffLeadershipSeason` fls ON fls.`seasonId` = sgc.`seasonId` 
				        WHERE fls.`fieldStaffGoId` = @goId AND fls.`seasonId` = @seasnId
				        AND sgc.superRegionId = @srId AND sgc.regionId = @rId ;
    
	                END IF;
	ELSE
		
		BEGIN
		END;
				
	END CASE;
    END$$

DELIMITER ;
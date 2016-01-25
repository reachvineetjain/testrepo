DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPFieldStaffFSLAndNetworkListing`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPFieldStaffFSLAndNetworkListing`(IN fieldStaffId INT,IN flag TINYINT(1))
BEGIN
       DECLARE fsGoId INT;
       DECLARE fsFlag TINYINT(1);
       DECLARE tId INT;
       SET @fsGoId = fieldStaffId;
       SET @fsFlag = flag;
       SET @typeId = (SELECT fieldStaffTypeId FROM `FieldStaff` WHERE fieldStaffGoId = @fsGoId); 
       SET @typeId1 = (SELECT fieldStaffTypeId FROM `FieldStaff` WHERE fieldStaffGoId = @fsGoId);
       
       IF (@fsFlag = 1) THEN -- FieldStaffNetWork
             
             CASE @typeId1
             
             WHEN 4 THEN
             
             SELECT f.`fieldStaffGoId`,
		     f.`firstName`,
		     f.`lastName`, 
		     f.`currentCity`,
		     us.`stateName`,
		     fst.`fieldStaffTypeName`,
		     f.`phone` AS HomePhone,
		     l.`email`,
		     f.`submittedDate` AS DateOfInquiry,
		     s.`fieldStaffStatusName` AS STATUS,
		     ss1.`fieldStaffStatusName` AS SeasonStatus,
		     f.`submittedDate` AS SubmittedToCCI,
		     GROUP_CONCAT(psv.programName) AS seasonName
	      FROM   `FieldStaff` f 
	      INNER JOIN `Login` l ON f.`fieldStaffGoId` = l.`goId`
	      INNER JOIN `LookupUSStates` us ON us.`usStatesId` = f.`currentStateId`
	      INNER JOIN  `FieldStaffSeason` flc ON flc.`fieldStaffGoId` = f.`fieldStaffGoId` 
	      INNER JOIN  `ProgramSeasons` psv ON psv.seasonId = flc.`seasonId` AND psv.departmentProgramId = flc.`departmentProgramId`
	      INNER JOIN `DepartmentPrograms` dp ON dp.`departmentProgramId` = flc.`departmentProgramId`
	      INNER JOIN `FieldStaffType` fst ON fst.fieldStaffTypeId = f.`fieldStaffTypeId` AND f.`fieldStaffTypeId` IN (1)
	      INNER JOIN `FieldStaffStatus` s ON f.`fieldStaffStatusId` = s.`fieldStaffStatusId`
	      INNER JOIN `FieldStaffStatus` ss1 ON ss1.fieldStaffStatusId = flc.`fieldStaffSeasonStatusId`
	      INNER JOIN `FieldStaff` fs1 ON fs1.`fieldStaffGoId` = @fsGoId AND fs1.fieldStaffTypeId = 4
	      GROUP BY f.`fieldStaffGoId`; 	
	      
	      ELSE
    
              SELECT f.`fieldStaffGoId`,
		     f.`firstName`,
		     f.`lastName`, 
		     f.`currentCity`,
		     us.`stateName`,
		     fst.`fieldStaffTypeName`,
		     f.`phone` AS HomePhone,
		     l.`email`,
		     f.`submittedDate` AS DateOfInquiry,
		     s.`fieldStaffStatusName` AS STATUS,
		     ss1.`fieldStaffStatusName` AS SeasonStatus,
		     f.`submittedDate` AS SubmittedToCCI,
		     GROUP_CONCAT(psv.programName) AS seasonName
	      FROM   `FieldStaff` f 
	      INNER JOIN `Login` l ON f.`fieldStaffGoId` = l.`goId`
	      INNER JOIN `LookupUSStates` us ON us.`usStatesId` = f.`currentStateId`
	      INNER JOIN  `FieldStaffSeason` flc ON flc.`fieldStaffGoId` = f.`fieldStaffGoId` 
	      INNER JOIN  `ProgramSeasons` psv ON psv.seasonId = flc.`seasonId` AND psv.departmentProgramId = flc.`departmentProgramId`
	      INNER JOIN `DepartmentPrograms` dp ON dp.`departmentProgramId` = flc.`departmentProgramId`
	      INNER JOIN `FieldStaffType` fst ON fst.fieldStaffTypeId = f.`fieldStaffTypeId` AND f.`fieldStaffTypeId` IN (1,4)
	      INNER JOIN `FieldStaffStatus` s ON f.`fieldStaffStatusId` = s.`fieldStaffStatusId`
	      INNER JOIN `FieldStaffStatus` ss1 ON ss1.fieldStaffStatusId = flc.fieldStaffSeasonStatusId
	      INNER JOIN  FieldStaff fs ON fs.fieldStaffGoId = @fsGoId AND fs.fieldStaffTypeId IN (2,3,5) 
	      GROUP BY f.`fieldStaffGoId`; 
	      
	   END CASE;   
	      
                    
	      
      
        END IF;
        
        
      
        IF (@fsFlag = 0)THEN -- FieldStaffLeadership
		
		CASE @typeId
		
		WHEN 5 THEN
	
		      SELECT f.`fieldStaffGoId`,
			     f.`firstName`,
			     f.`lastName`,
			     l.`email`,
			     f.`currentCity`,
			     us.`stateName`,
			     GROUP_CONCAT(psv.programName) AS seasonName,
			     fst.`fieldStaffTypeName`,
			     GROUP_CONCAT(dp.`programName`) AS Programs
		      FROM   `FieldStaff` f 
		      INNER JOIN `Login` l ON f.`fieldStaffGoId` = l.`goId`
		      INNER JOIN `LookupUSStates` us ON us.`usStatesId` = f.`currentStateId`
		      INNER JOIN  `FieldStaffLeadershipSeason` flc ON flc.`fieldStaffGoId` = f.`fieldStaffGoId` 
		      INNER JOIN `FieldStaffSeason` flcd ON flcd.`SeasonId` = flc.`SeasonId` AND flc.fieldStaffGoId = flcd.fieldStaffGoId
		      INNER JOIN  `ProgramSeasons` psv ON psv.seasonId = flcd.`seasonId` AND psv.departmentProgramId = flcd.`departmentProgramId`
		      INNER JOIN `DepartmentPrograms` dp ON dp.`departmentProgramId` = flcd.`departmentProgramId`
		      INNER JOIN `FieldStaffType` fst ON fst.fieldStaffTypeId = f.`fieldStaffTypeId` AND f.`fieldStaffTypeId` IN (2,3)
		      INNER JOIN `FieldStaffStatus` s ON f.`fieldStaffStatusId` = s.`fieldStaffStatusId`
		      INNER JOIN  FieldStaff fs ON fs.fieldStaffGoId = @fsGoId AND fs.`fieldStaffTypeId` = 5
		      GROUP BY f.`fieldStaffGoId`; 
		      
		WHEN 3 THEN
		      
		     SELECT f.`fieldStaffGoId`,
			    f.`firstName`,
			    f.`lastName`,
			    l.`email`,
			    f.`currentCity`,
			    us.`stateName`,
			    GROUP_CONCAT(psv.programName) AS seasonName,
			    fst.`fieldStaffTypeName`,
			    GROUP_CONCAT(dp.`programName`) AS Programs
		      FROM   `FieldStaff` f 
		      INNER JOIN `Login` l ON f.`fieldStaffGoId` = l.`goId`
		      INNER JOIN `LookupUSStates` us ON us.`usStatesId` = f.`currentStateId`
		      INNER JOIN  `FieldStaffLeadershipSeason` flc ON flc.`fieldStaffGoId` = f.`fieldStaffGoId` 
		      INNER JOIN `FieldStaffSeason` flcd ON flcd.`seasonId` = flc.`seasonId`
		      INNER JOIN  `ProgramSeasons` psv ON psv.seasonId = flcd.`seasonId` AND psv.departmentProgramId = flcd.`departmentProgramId`
		      INNER JOIN `DepartmentPrograms` dp ON dp.`departmentProgramId` = flcd.`departmentProgramId`
		      INNER JOIN `FieldStaffType` fst ON fst.fieldStaffTypeId = f.`fieldStaffTypeId` AND f.`fieldStaffTypeId` IN (2)
		      INNER JOIN `FieldStaffStatus` s ON f.`fieldStaffStatusId` = s.`fieldStaffStatusId`
		      INNER JOIN `FieldStaff` f1 ON f1.fieldStaffGoId = @fsGoId AND f1.fieldStaffTypeId = 5
		      GROUP BY f.`fieldStaffGoId`;
		   
		 ELSE
		   
		      BEGIN
		      END;		   
	
		END CASE;
	      
	END IF;
      
	
	  
    /*IF (@fsFlag = 0 AND @typeId = 2) THEN
	
    SELECT f.`fieldStaffGoId`,f.`firstName`,f.`lastName`,l.`email`,f.`currentCity`,us.`stateName`,
             GROUP_CONCAT(psv.programName) AS seasonName,GROUP_CONCAT(dp.`programName`) AS Programs,
             fst.`fieldStaffTypeName`
      FROM   `FieldStaff` f 
      INNER JOIN `Login` l ON f.`fieldStaffGoId` = l.`goId`
      INNER JOIN `LookupUSStates` us ON f.`currentStateId` = us.`usStatesId`
      INNER JOIN  `FieldStaffLeadershipSeason` flc ON flc.`fieldStaffGoId` = f.`fieldStaffGoId` 
      INNER JOIN `FieldStaffLeadershipSeasonDetails` flc1 ON  
      flc1.`fieldStaffLeadershipSeasonId` = flc.`fieldStaffLeadershipSeasonId`
      INNER JOIN  `ProgramSeasons` psv ON psv.seasonId = flc.`seasonId` 
      AND psv.seasonId = flc1.seasonId AND psv.departmentProgramId = flc1.`departmentProgramId`
      INNER JOIN `DepartmentPrograms` dp ON dp.`departmentProgramId` = flc1.`departmentProgramId`
      INNER JOIN `FieldStaffType` fst ON fst.fieldStaffTypeId = f.`fieldStaffTypeId` AND fst.fieldStaffTypeId = 5
      INNER JOIN `FieldStaff` f1 ON f.`fieldStaffGoId` = f1.`ERDId` 
      WHERE f1.`fieldStaffGoId` = 50003 AND f1.`fieldStaffTypeId` = 3;
      
      END IF;*/
      
     
     
END$$

DELIMITER ;
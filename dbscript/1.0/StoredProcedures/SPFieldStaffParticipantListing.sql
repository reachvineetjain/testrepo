DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPFieldStaffParticipantListing`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPFieldStaffParticipantListing`(IN fieldStaffId INT,IN flag TINYINT(1),IN category VARCHAR(50))
BEGIN
       DECLARE fsGoId INT;
       DECLARE fsFlag TINYINT(1);
       
       
       SET @fsGoId = fieldStaffId;
       SET @fsFlag = flag;
       SET @typeId = (SELECT fieldStaffTypeId FROM `FieldStaff` WHERE fieldStaffGoId = @fsGoId); 
        
        IF (@fsFlag = 0) THEN -- for My Participants
                  
                  SELECT fp.`participantGoId` AS participantGoId,
                         p.`firstName` AS firstName,
                         p.`lastName` AS lastName,
                         pp.`companyName` AS Partner,
                         lc.`countryName` AS countryName,
                         psv.programName AS SeasonName,
                         dp.`programName` AS Programs,
                         ps.`participantStatusName`,
                         fp.`holdRequested`,
                         'photo' AS photo,
                         'countryFlag' AS countryFlag
                         
                  FROM `FieldStaffParticipant` fp
                  INNER JOIN `Participants` p ON fp.`participantGoId` = p.`participantGoId`
                  INNER JOIN `Partner` pp ON pp.`partnerGoId` = p.`partnerGoId`
                  INNER JOIN `LookupCountries` lc ON lc.`countryId` = p.`countryId`
                  INNER JOIN `ProgramSeasons` psv ON psv.seasonId = p.seasonId AND psv.departmentProgramId = p.departmentProgramId
                  INNER JOIN `DepartmentPrograms` dp ON dp.departmentProgramId = psv.departmentProgramId
                  INNER JOIN `ParticipantStatus` ps ON p.`participantStatusId` = ps.`participantStatusId` AND ps.`isPlacementStatus` = 1
                  WHERE fp.`fieldStaffGoId` = @fsGoId;
                  
         END IF;
         
         IF (@fsFlag = 1) THEN -- for My Teams Participants
         
           CASE @typeId 
           
           WHEN 5 THEN
         
                  SELECT fp.`participantGoId` AS CCIID,
                         f.`firstName` AS fieldStaffFirstName,
                         f.`lastName` AS fieldStaffLastName,
                         p.`firstName` AS paxFirstName,
                         p.`lastName` AS paxLastName,
                         pp.`companyName` AS Partner,
                         lc.`countryName`,
                         psv.programName AS SeasonName,
                         dp.`programName` AS Programs,
                         ps.`participantStatusName` AS STATUS,
                         fp.`holdRequested` AS Holds,
                         'photo' AS paxPhoto 
                  FROM `FieldStaffParticipant` fp
                  INNER JOIN `Participants` p ON fp.`participantGoId` = p.`participantGoId`
                  INNER JOIN `Partner` pp ON pp.`partnerGoId` = p.`partnerGoId`
                  INNER JOIN `LookupCountries` lc ON lc.`countryId` = p.`countryId`
                  INNER JOIN `ProgramSeasons` psv ON psv.seasonId = p.seasonId AND psv.departmentProgramId = p.departmentProgramId
                  INNER JOIN `DepartmentPrograms` dp ON dp.departmentProgramId = psv.departmentProgramId
                  INNER JOIN `ParticipantStatus` ps ON p.`participantStatusId` = ps.`participantStatusId` AND ps.`isPlacementStatus` = 1
                  INNER JOIN `FieldStaff` f ON f.`fieldStaffGoId` = fp.`fieldStaffGoId` AND f.fieldStaffTypeId IN (1,2,3,4)
                  INNER JOIN `FieldStaff` f1 ON f1.`fieldStaffGoId` = @fsGoId AND f1.fieldStaffTypeId = 5;
                  
           WHEN 3 THEN   
           
                  SELECT fp.`participantGoId` AS CCIID,
                         f.`firstName` AS fieldStaffFirstName,
                         f.`lastName` AS fieldStaffLastName,
                         p.`firstName` AS paxFirstName,
                         p.`lastName` AS paxLastName,
                         pp.`companyName` AS Partner,
                         lc.`countryName`,
                         psv.programName AS SeasonName,
                         dp.`programName` AS Programs,
                         ps.`participantStatusName` AS STATUS,
                         fp.`holdRequested` AS Holds,
                         'photo' AS paxPhoto
                  FROM `FieldStaffParticipant` fp
                  INNER JOIN `Participants` p ON fp.`participantGoId` = p.`participantGoId`
                  INNER JOIN `Partner` pp ON pp.`partnerGoId` = p.`partnerGoId`
                  INNER JOIN `LookupCountries` lc ON lc.`countryId` = p.`countryId`
                  INNER JOIN `ProgramSeasons` psv ON psv.seasonId = p.seasonId AND psv.departmentProgramId = p.departmentProgramId
                  INNER JOIN `DepartmentPrograms` dp ON dp.departmentProgramId = psv.departmentProgramId
                  INNER JOIN `ParticipantStatus` ps ON p.`participantStatusId` = ps.`participantStatusId` AND ps.`isPlacementStatus` = 1
                  INNER JOIN `FieldStaff` f ON f.`fieldStaffGoId` = fp.`fieldStaffGoId` AND f.fieldStaffTypeId IN (1,2,4)
                  INNER JOIN `FieldStaff` f1 ON f1.`fieldStaffGoId` = @fsGoId AND f1.fieldStaffTypeId = 3;
                        
           WHEN 2 THEN   
           
                  SELECT fp.`participantGoId` AS CCIID,
                         f.`firstName` AS fieldStaffFirstName,
                         f.`lastName` AS fieldStaffLastName,
                         p.`firstName` AS paxFirstName,
                         p.`lastName` AS paxLastName,
                        pp.`companyName` AS Partner,
                        lc.`countryName`,
                        psv.programName AS SeasonName,
                        dp.`programName` AS Programs,
                        ps.`participantStatusName` AS STATUS,
                        fp.`holdRequested` AS Holds,
                        'photo' AS paxPhoto
                  FROM `FieldStaffParticipant` fp
                  INNER JOIN `Participants` p ON fp.`participantGoId` = p.`participantGoId`
                  INNER JOIN `Partner` pp ON pp.`partnerGoId` = p.`partnerGoId`
                  INNER JOIN `LookupCountries` lc ON lc.`countryId` = p.`countryId`
                  INNER JOIN `ProgramSeasons` psv ON psv.seasonId = p.seasonId AND psv.departmentProgramId = p.departmentProgramId
                  INNER JOIN `DepartmentPrograms` dp ON dp.departmentProgramId = psv.departmentProgramId
                  INNER JOIN `ParticipantStatus` ps ON p.`participantStatusId` = ps.`participantStatusId` AND ps.`isPlacementStatus` = 1
                  INNER JOIN `FieldStaff` f ON f.`fieldStaffGoId` = fp.`fieldStaffGoId` AND f.fieldStaffTypeId IN (4)
                  INNER JOIN `FieldStaff` f1 ON f1.`fieldStaffGoId` = @fsGoId AND f1.fieldStaffTypeId = 2;
                  
           WHEN 4 THEN   
           
                  SELECT fp.`participantGoId` AS CCIID,
                         f.`firstName` AS fieldStaffFirstName,
                         f.`lastName` AS fieldStaffLastName,
                         p.`firstName` AS paxFirstName,
                         p.`lastName` AS paxLastName,
                         pp.`companyName` AS Partner,
                         lc.`countryName`,psv.programName AS SeasonName,
                         dp.`programName` AS Programs,
                         ps.`participantStatusName` AS STATUS,
                         fp.`holdRequested` AS Holds,
                         'photo' AS paxPhoto
                  FROM `FieldStaffParticipant` fp
                  INNER JOIN `Participants` p ON fp.`participantGoId` = p.`participantGoId`
                  INNER JOIN `Partner` pp ON pp.`partnerGoId` = p.`partnerGoId`
                  INNER JOIN `LookupCountries` lc ON lc.`countryId` = p.`countryId`
                  INNER JOIN `ProgramSeasons` psv ON psv.seasonId = p.seasonId AND psv.departmentProgramId = p.departmentProgramId
                  INNER JOIN `DepartmentPrograms` dp ON dp.departmentProgramId = psv.departmentProgramId
                  INNER JOIN `ParticipantStatus` ps ON p.`participantStatusId` = ps.`participantStatusId` AND ps.`isPlacementStatus` = 1
                  INNER JOIN `FieldStaff` f ON f.`fieldStaffGoId` = fp.`fieldStaffGoId` AND f.`fieldStaffStatusId` = 1
                  INNER JOIN `FieldStaff` f1 ON f1.`fieldStaffGoId` = @fsGoId AND f1.fieldStaffTypeId = 4;
                  
           ELSE 
            
                BEGIN
                END;
                
           END CASE;
           
        END IF;   
        
        IF (@fsFlag = 2) THEN        -- Participants
		
		SELECT participantGoId,
			firstName,
			lastName,
			'USA' AS country,
			'HSP2016-17' AS seasonName,
			'J1-HS' AS program,
			'Lori' AS ERD,
			CURRENT_DATE AS approvedOn,
			'placed' AS STATUS,
			'photo' AS photo,
			'waitList' AS waitList
			FROM Participants;
	END IF;
        
END$$

DELIMITER ;
DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPFieldStaffMonitoringParticipantListing`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPFieldStaffMonitoringParticipantListing`(IN fieldStaffId INT,IN flag TINYINT(1),IN category VARCHAR(50))
BEGIN
       
       
       DECLARE fsGoId INT;
       DECLARE fsFlag TINYINT(1);
       
       
       SET @fsGoId = fieldStaffId;
       SET @fsFlag = flag;
       SET @typeId = (SELECT fieldStaffTypeId FROM `FieldStaff` WHERE fieldStaffGoId = @fsGoId); 
        
        
           IF (@fsFlag = 0) THEN 
                  
                  SELECT fp.`participantGoId` AS CCIID,
                  p.`firstName` AS firstName,
                  p.`lastName` AS LastName,
                  pp.`companyName` AS Partner,
                  lc.`countryName`,
                  
                  dp.`programName` AS Programs,
                  'Male'  AS Gender,
                  CURRENT_TIMESTAMP  AS ApprovedDate,
                  'Yvonne Colton'  AS LocalCordinator,
                  'Lee White'  AS RegionalDirector,
                  'Patrida Smith'  AS HostFamily,
                  'https://s3.amazonaws.com/%2Fccighgodocs-dev%2Fpartner/profile-1450399222670.jpg' AS photo
                  FROM `FieldStaffParticipant` fp
                  INNER JOIN `Participants` p ON fp.`participantGoId` = p.`participantGoId`
                   INNER JOIN `Partner` pp ON pp.`partnerGoId` = p.`partnerGoId`
                  
                  
                  
                  INNER JOIN `LookupCountries` lc ON lc.`countryId` = p.`countryId`
                  INNER JOIN `ProgramSeasons` psv ON psv.seasonId = p.seasonId AND psv.departmentProgramId = p.departmentProgramId
                  INNER JOIN `DepartmentPrograms` dp ON dp.departmentProgramId = psv.departmentProgramId
                  INNER JOIN `ParticipantStatus` ps ON p.`participantStatusId` = ps.`participantStatusId` 
                  
                  
                  WHERE fp.`fieldStaffGoId` = @fsGoId;
                  
         END IF;
         
         IF (@fsFlag = 1) THEN 
         
           CASE @typeId 
           
           WHEN 5 THEN
         
                  SELECT fp.`participantGoId` AS CCIID,
                         p.`firstName` AS firstName,
                         p.`lastName` AS lastName,
                         pp.`companyName` AS Partner,
                         lc.`countryName`,
                         
                         dp.`programName` AS Programs,
                         'Male'  AS Gender,
                         CURRENT_TIMESTAMP  AS ApprovedDate,
                         'Peter Schreiber'  AS LocalCordinator,
                         'Teresa Clark'  AS RegionalDirector,
                         'Patrida Smith'  AS HostFamily,
                         'https://s3.amazonaws.com/%2Fccighgodocs-dev%2Fpartner/profile-1450399222670.jpg' AS photo
                  FROM `FieldStaffParticipant` fp
                  INNER JOIN `Participants` p ON fp.`participantGoId` = p.`participantGoId`
                  
                  
                  
                  INNER JOIN `Partner` pp ON pp.`partnerGoId` = p.`partnerGoId`
                  INNER JOIN `LookupCountries` lc ON lc.`countryId` = p.`countryId`
                  INNER JOIN `ProgramSeasons` psv ON psv.seasonId = p.seasonId AND psv.departmentProgramId = p.departmentProgramId
                  INNER JOIN `DepartmentPrograms` dp ON dp.departmentProgramId = psv.departmentProgramId
                  INNER JOIN `ParticipantStatus` ps ON p.`participantStatusId` = ps.`participantStatusId` AND ps.`isPlacementStatus` = 0
                  
                  
                  INNER JOIN `FieldStaff` f ON f.`fieldStaffGoId` = fp.`fieldStaffGoId` AND f.fieldStaffTypeId IN (1,2,3,4)
                  INNER JOIN `FieldStaff` f1 ON f1.`fieldStaffGoId` = @fsGoId AND f1.fieldStaffTypeId = 5;
                  
           WHEN 3 THEN   
           
                  SELECT fp.`participantGoId` AS CCIID,
                          p.`firstName` AS firstName,
                         p.`lastName` AS lastName,
                         pp.`companyName` AS Partner,
                         lc.`countryName`,
                         
                         dp.`programName` AS Programs,
                         'Male'  AS Gender,
                         CURRENT_TIMESTAMP  AS ApprovedDate,
                         'Peter Schreiber'  AS LocalCordinator,
                         'Teresa Clark'  AS RegionalDirector,
                         'Patrida Smith'  AS HostFamily,
                         'https://s3.amazonaws.com/%2Fccighgodocs-dev%2Fpartner/profile-1450399222670.jpg' AS photo
                  FROM `FieldStaffParticipant` fp
                  INNER JOIN `Participants` p ON fp.`participantGoId` = p.`participantGoId`
                  
                  
                  
                  INNER JOIN `Partner` pp ON pp.`partnerGoId` = p.`partnerGoId`
                  INNER JOIN `LookupCountries` lc ON lc.`countryId` = p.`countryId`
                  INNER JOIN `ProgramSeasons` psv ON psv.seasonId = p.seasonId AND psv.departmentProgramId = p.departmentProgramId
                  INNER JOIN `DepartmentPrograms` dp ON dp.departmentProgramId = psv.departmentProgramId
                  INNER JOIN `ParticipantStatus` ps ON p.`participantStatusId` = ps.`participantStatusId` AND ps.`isPlacementStatus` = 0
                  
                  
                  INNER JOIN `FieldStaff` f ON f.`fieldStaffGoId` = fp.`fieldStaffGoId` AND f.fieldStaffTypeId IN (1,2,4)
                  INNER JOIN `FieldStaff` f1 ON f1.`fieldStaffGoId` = @fsGoId AND f1.fieldStaffTypeId = 3;
                        
           WHEN 2 THEN   
           
                  SELECT fp.`participantGoId` AS CCIID,
                          p.`firstName` AS firstName,
                         p.`lastName` AS lastName,
                         pp.`companyName` AS Partner,
                         lc.`countryName`,
                         
                         dp.`programName` AS Programs,
                         'Male'  AS Gender,
                         CURRENT_TIMESTAMP  AS ApprovedDate,
                         'Peter Schreiber'  AS LocalCordinator,
                         'Teresa Clark'  AS RegionalDirector,
                         'Patrida Smith'  AS HostFamily,
                         'https://s3.amazonaws.com/%2Fccighgodocs-dev%2Fpartner/profile-1450399222670.jpg' AS photo
                  FROM `FieldStaffParticipant` fp
                  INNER JOIN `Participants` p ON fp.`participantGoId` = p.`participantGoId`
                  
                  
                  
                  INNER JOIN `Partner` pp ON pp.`partnerGoId` = p.`partnerGoId`
                  INNER JOIN `LookupCountries` lc ON lc.`countryId` = p.`countryId`
                  INNER JOIN `ProgramSeasons` psv ON psv.seasonId = p.seasonId AND psv.departmentProgramId = p.departmentProgramId
                  INNER JOIN `DepartmentPrograms` dp ON dp.departmentProgramId = psv.departmentProgramId
                  INNER JOIN `ParticipantStatus` ps ON p.`participantStatusId` = ps.`participantStatusId` AND ps.`isPlacementStatus` = 0
                  
                  
                  INNER JOIN `FieldStaff` f ON f.`fieldStaffGoId` = fp.`fieldStaffGoId` AND f.fieldStaffTypeId IN (4)
                  INNER JOIN `FieldStaff` f1 ON f1.`fieldStaffGoId` = @fsGoId AND f1.fieldStaffTypeId = 2;
                  
           WHEN 4 THEN   
           
                  SELECT fp.`participantGoId` AS CCIID,
                          p.`firstName` AS firstName,
                         p.`lastName` AS lastName,
                         pp.`companyName` AS Partner,
                         lc.`countryName`,
                         
                         dp.`programName` AS Programs,
                         'Male'  AS Gender,
                         CURRENT_TIMESTAMP  AS ApprovedDate,
                         'Peter Schreiber'  AS LocalCordinator,
                         'Teresa Clark'  AS RegionalDirector,
                         'Patrida Smith'  AS HostFamily,
                         'https://s3.amazonaws.com/%2Fccighgodocs-dev%2Fpartner/profile-1450399222670.jpg' AS photo 
                  FROM `FieldStaffParticipant` fp
                  INNER JOIN `Participants` p ON fp.`participantGoId` = p.`participantGoId`
                  
                  
                  
                  INNER JOIN `Partner` pp ON pp.`partnerGoId` = p.`partnerGoId`
                  INNER JOIN `LookupCountries` lc ON lc.`countryId` = p.`countryId`
                  INNER JOIN `ProgramSeasons` psv ON psv.seasonId = p.seasonId AND psv.departmentProgramId = p.departmentProgramId
                  INNER JOIN `DepartmentPrograms` dp ON dp.departmentProgramId = psv.departmentProgramId
                  INNER JOIN `ParticipantStatus` ps ON p.`participantStatusId` = ps.`participantStatusId` AND ps.`isPlacementStatus` = 0
                  
                  
                  INNER JOIN `FieldStaff` f ON f.`fieldStaffGoId` = fp.`fieldStaffGoId` AND f.`fieldStaffTypeId` = 1
                  INNER JOIN `FieldStaff` f1 ON f1.`fieldStaffGoId` = @fsGoId AND f1.fieldStaffTypeId = 4;
                  
	  WHEN 1 THEN   
           
                  SELECT fp.`participantGoId` AS CCIID,
                          p.`firstName` AS firstName,
                         p.`lastName` AS lastName,
                         pp.`companyName` AS Partner,
                         lc.`countryName`,
                         
                         dp.`programName` AS Programs,
                         'Male'  AS Gender,
                         CURRENT_TIMESTAMP  AS ApprovedDate,
                         'Peter Schreiber'  AS LocalCordinator,
                         'Teresa Clark'  AS RegionalDirector,
                         'Patrida Smith'  AS HostFamily,
                         'https://s3.amazonaws.com/%2Fccighgodocs-dev%2Fpartner/profile-1450399222670.jpg' AS photo 
                  FROM `FieldStaffParticipant` fp
                  INNER JOIN `Participants` p ON fp.`participantGoId` = p.`participantGoId`
                  
                  
                  
                  INNER JOIN `Partner` pp ON pp.`partnerGoId` = p.`partnerGoId`
                  INNER JOIN `LookupCountries` lc ON lc.`countryId` = p.`countryId`
                  INNER JOIN `ProgramSeasons` psv ON psv.seasonId = p.seasonId AND psv.departmentProgramId = p.departmentProgramId
                  INNER JOIN `DepartmentPrograms` dp ON dp.departmentProgramId = psv.departmentProgramId
                  INNER JOIN `ParticipantStatus` ps ON p.`participantStatusId` = ps.`participantStatusId` AND ps.`isPlacementStatus` = 0
                  
                  
                  INNER JOIN `FieldStaff` f ON f.`fieldStaffGoId` = fp.`fieldStaffGoId` AND f.`fieldStaffTypeId` = 1
                  INNER JOIN `FieldStaff` f1 ON f1.`fieldStaffGoId` = 50039; -- AND f1.fieldStaffTypeId = 4;		
                  
           ELSE 
            
                BEGIN
                END;
                
           END CASE;
           
        END IF;          
END$$

DELIMITER ;
DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPFieldStaffMonitoringParticipantListing`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPFieldStaffMonitoringParticipantListing`(IN fieldStaffId INT,IN flag TINYINT(1),IN category VARCHAR(50))
BEGIN
       
       
       DECLARE fsGoId INT;
       DECLARE fsFlag TINYINT(1);
       
       
       SET @fsGoId = fieldStaffId;
       SET @fsFlag = flag;
       SET @typeId = (SELECT fieldStaffTypeId FROM `FieldStaff` WHERE fieldStaffGoId = @fsGoId); 
        
        
           IF (@fsFlag = 0) THEN -- for My Participants
                  
                  SELECT fp.`participantGoId` AS CCIID,
                  p.`firstName` AS firstName,
                  p.`lastName` AS LastName,
                  pp.`companyName` AS Partner,
                  lc.`countryName`,
                  /*psv.programName,*/
                  dp.`programName` AS Programs,
                  'Male' /*g.`genderName`*/ AS Gender,
                  CURRENT_TIMESTAMP /*f.approvedDate*/ AS ApprovedDate,
                  'Yvonne Colton' /*concat(f.firstName,' ',f.lastName)*/ AS LocalCordinator,
                  'Lee White' /*concat(f1.firstName,' ',f1.lastName)*/ AS RegionalDirector,
                  'Patrida Smith' /*concat(hf.firstName,' ',hf.lastName)*/ AS HostFamily
                  FROM `FieldStaffParticipant` fp
                  INNER JOIN `Participants` p ON fp.`participantGoId` = p.`participantGoId`
                   INNER JOIN `Partner` pp ON pp.`partnerGoId` = p.`partnerGoId`
                  -- inner join `LookupGender` g on p.genderId = p.genderId
                  -- inner join `HostFamilyParticipant` hfp on p.participantGoId = hfp.participantGoId
                  -- inner join `HostFamily` hf on hf.hostFamilyGoId = hfp.hostFamilyGoId                  INNER JOIN `Partner` pp ON pp.`partnerGoId` = p.`partnerGoId`
                  INNER JOIN `LookupCountries` lc ON lc.`countryId` = p.`countryId`
                  INNER JOIN `ProgramSeasons` psv ON psv.seasonId = p.seasonId AND psv.departmentProgramId = p.departmentProgramId
                  INNER JOIN `DepartmentPrograms` dp ON dp.departmentProgramId = psv.departmentProgramId
                  INNER JOIN `ParticipantStatus` ps ON p.`participantStatusId` = ps.`participantStatusId` AND ps.`isPlacementStatus` = 0
                  -- inner join FieldStaff f on f.fieldStaffGoId = fp.fieldStaffGoId and f.fieldStaffTypeId = 1
                  -- inner join FieldStaff f1 on f.fieldStaffGoId = fp.fieldStaffGoId and f.fieldStaffTypeId = 3
                  WHERE fp.`fieldStaffGoId` = @fsGoId;
                  
         END IF;
         
         IF (@fsFlag = 1) THEN -- for My Teams Participants
         
           CASE @typeId 
           
           WHEN 5 THEN
         
                  SELECT fp.`participantGoId` AS CCIID,
                         p.`firstName` AS firstName,
                         p.`lastName` AS lastName,
                         pp.`companyName` AS Partner,
                         lc.`countryName`,
                         /*psv.programName,*/
                         dp.`programName` AS Programs,
                         'Male' /*g.genderId*/ AS Gender,
                         CURRENT_TIMESTAMP /*f.approvedDate*/ AS ApprovedDate,
                         'Peter Schreiber' /*concat(f.firstName,' ',f.lastName)*/ AS LocalCordinator,
                         'Teresa Clark' /*concat(f1.firstName,' ',f1.lastName)*/ AS RegionalDirector,
                         'Patrida Smith' /*concat(hf.firstName,' ',hf.lastName)*/ AS HostFamily
                  FROM `FieldStaffParticipant` fp
                  INNER JOIN `Participants` p ON fp.`participantGoId` = p.`participantGoId`
                  -- inner join `LookupGender` g on p.genderId = p.gender
                  -- inner join `HostFamilyParticipant` hfp on p.participantGoId = hfp.participantGoId
                  -- inner join `HostFamily` hf on hf.hostFamilyGoId = hfp.hostFamilyGoId
                  INNER JOIN `Partner` pp ON pp.`partnerGoId` = p.`partnerGoId`
                  INNER JOIN `LookupCountries` lc ON lc.`countryId` = p.`countryId`
                  INNER JOIN `ProgramSeasons` psv ON psv.seasonId = p.seasonId AND psv.departmentProgramId = p.departmentProgramId
                  INNER JOIN `DepartmentPrograms` dp ON dp.departmentProgramId = psv.departmentProgramId
                  INNER JOIN `ParticipantStatus` ps ON p.`participantStatusId` = ps.`participantStatusId` AND ps.`isPlacementStatus` = 0
                  -- inner join FieldStaff f on f.fieldStaffGoId = fp.fieldStaffGoId and f.fieldStaffTypeId = 1
                  -- inner join FieldStaff f1 on f.fieldStaffGoId = fp.fieldStaffGoId and f.fieldStaffTypeId = 3
                  INNER JOIN `FieldStaff` f ON f.`fieldStaffGoId` = fp.`fieldStaffGoId` AND f.fieldStaffTypeId IN (1,2,3,4)
                  INNER JOIN `FieldStaff` f1 ON f1.`fieldStaffGoId` = @fsGoId AND f1.fieldStaffTypeId = 5;
                  
           WHEN 3 THEN   
           
                  SELECT fp.`participantGoId` AS CCIID,
                         CONCAT(p.`firstName`,' ',p.`lastName`) AS NAME,
                         pp.`companyName` AS Partner,
                         lc.`countryName`,
                         /*psv.programName,*/
                         dp.`programName` AS Programs,
                         'Male' /*g.genderName*/ AS Gender,
                         CURRENT_TIMESTAMP /*f.approvedDate*/ AS ApprovedDate,
                         'Peter Schreiber' /*concat(f.firstName,' ',f.lastName)*/ AS LocalCordinator,
                         'Teresa Clark' /*concat(f1.firstName,' ',f1.lastName)*/ AS RegionalDirector,
                         'Patrida Smith' /*concat(hf.firstName,' ',hf.lastName)*/ AS HostFamily
                  FROM `FieldStaffParticipant` fp
                  INNER JOIN `Participants` p ON fp.`participantGoId` = p.`participantGoId`
                  -- inner join `LookupGender` g on p.genderId = p.gender
                  -- inner join `HostFamilyParticipant` hfp on p.participantGoId = hfp.participantGoId
                  -- inner join `HostFamily` hf on hf.hostFamilyGoId = hfp.hostFamilyGoId
                  INNER JOIN `Partner` pp ON pp.`partnerGoId` = p.`partnerGoId`
                  INNER JOIN `LookupCountries` lc ON lc.`countryId` = p.`countryId`
                  INNER JOIN `ProgramSeasons` psv ON psv.seasonId = p.seasonId AND psv.departmentProgramId = p.departmentProgramId
                  INNER JOIN `DepartmentPrograms` dp ON dp.departmentProgramId = psv.departmentProgramId
                  INNER JOIN `ParticipantStatus` ps ON p.`participantStatusId` = ps.`participantStatusId` AND ps.`isPlacementStatus` = 0
                  -- inner join FieldStaff f on f.fieldStaffGoId = fp.fieldStaffGoId and f.fieldStaffTypeId = 1
                  -- inner join FieldStaff f1 on f.fieldStaffGoId = fp.fieldStaffGoId and f.fieldStaffTypeId = 3
                  INNER JOIN `FieldStaff` f ON f.`fieldStaffGoId` = fp.`fieldStaffGoId` AND f.fieldStaffTypeId IN (1,2,4)
                  INNER JOIN `FieldStaff` f1 ON f1.`fieldStaffGoId` = @fsGoId AND f1.fieldStaffTypeId = 3;
                        
           WHEN 2 THEN   
           
                  SELECT fp.`participantGoId` AS CCIID,
                         CONCAT(p.`firstName`,' ',p.`lastName`) AS NAME,
                         pp.`companyName` AS Partner,
                         lc.`countryName`,
                         /*psv.programName,*/
                         dp.`programName` AS Programs,
                         'Male' /*g.genderName*/ AS Gender,
                         CURRENT_TIMESTAMP /*f.approvedDate*/ AS ApprovedDate,
                         'Peter Schreiber' /*concat(f.firstName,' ',f.lastName)*/ AS LocalCordinator,
                         'Teresa Clark' /*concat(f1.firstName,' ',f1.lastName)*/ AS RegionalDirector,
                         'Patrida Smith' /*concat(hf.firstName,' ',hf.lastName)*/ AS HostFamily
                  FROM `FieldStaffParticipant` fp
                  INNER JOIN `Participants` p ON fp.`participantGoId` = p.`participantGoId`
                  -- inner join `LookupGender` g on p.genderId = p.gender
                  -- inner join `HostFamilyParticipant` hfp on p.participantGoId = hfp.participantGoId
                  -- inner join `HostFamily` hf on hf.hostFamilyGoId = hfp.hostFamilyGoId
                  INNER JOIN `Partner` pp ON pp.`partnerGoId` = p.`partnerGoId`
                  INNER JOIN `LookupCountries` lc ON lc.`countryId` = p.`countryId`
                  INNER JOIN `ProgramSeasons` psv ON psv.seasonId = p.seasonId AND psv.departmentProgramId = p.departmentProgramId
                  INNER JOIN `DepartmentPrograms` dp ON dp.departmentProgramId = psv.departmentProgramId
                  INNER JOIN `ParticipantStatus` ps ON p.`participantStatusId` = ps.`participantStatusId` AND ps.`isPlacementStatus` = 0
                  -- inner join FieldStaff f on f.fieldStaffGoId = fp.fieldStaffGoId and f.fieldStaffTypeId = 1
                  -- inner join FieldStaff f1 on f.fieldStaffGoId = fp.fieldStaffGoId and f.fieldStaffTypeId = 3
                  INNER JOIN `FieldStaff` f ON f.`fieldStaffGoId` = fp.`fieldStaffGoId` AND f.fieldStaffTypeId IN (4)
                  INNER JOIN `FieldStaff` f1 ON f1.`fieldStaffGoId` = @fsGoId AND f1.fieldStaffTypeId = 2;
                  
           WHEN 4 THEN   
           
                  SELECT fp.`participantGoId` AS CCIID,
                         CONCAT(p.`firstName`,' ',p.`lastName`) AS NAME,
                         pp.`companyName` AS Partner,
                         lc.`countryName`,
                         /*psv.programName,*/
                         dp.`programName` AS Programs,
                         'Male' /*g.genderName*/ AS Gender,
                         CURRENT_TIMESTAMP /*f.approvedDate*/ AS ApprovedDate,
                         'Peter Schreiber' /*concat(f.firstName,' ',f.lastName)*/ AS LocalCordinator,
                         'Teresa Clark' /*concat(f1.firstName,' ',f1.lastName)*/ AS RegionalDirector,
                         'Patrida Smith' /*concat(hf.firstName,' ',hf.lastName)*/ AS HostFamily                  FROM `FieldStaffParticipant` fp
                  INNER JOIN `Participants` p ON fp.`participantGoId` = p.`participantGoId`
                  -- inner join `LookupGender` g on p.genderId = p.gender
                  -- inner join `HostFamilyParticipant` hfp on p.participantGoId = hfp.participantGoId
                  -- inner join `HostFamily` hf on hf.hostFamilyGoId = hfp.hostFamilyGoId
                  INNER JOIN `Partner` pp ON pp.`partnerGoId` = p.`partnerGoId`
                  INNER JOIN `LookupCountries` lc ON lc.`countryId` = p.`countryId`
                  INNER JOIN `ProgramSeasons` psv ON psv.seasonId = p.seasonId AND psv.departmentProgramId = p.departmentProgramId
                  INNER JOIN `DepartmentPrograms` dp ON dp.departmentProgramId = psv.departmentProgramId
                  INNER JOIN `ParticipantStatus` ps ON p.`participantStatusId` = ps.`participantStatusId` AND ps.`isPlacementStatus` = 0
                  -- inner join FieldStaff f on f.fieldStaffGoId = fp.fieldStaffGoId and f.fieldStaffTypeId = 1
                  -- inner join FieldStaff f1 on f.fieldStaffGoId = fp.fieldStaffGoId and f.fieldStaffTypeId = 3
                  INNER JOIN `FieldStaff` f ON f.`fieldStaffGoId` = fp.`fieldStaffGoId` AND f.`fieldStaffTypeId` = 1
                  INNER JOIN `FieldStaff` f1 ON f1.`fieldStaffGoId` = @fsGoId AND f1.fieldStaffTypeId = 4;
                  
           ELSE 
            
                BEGIN
                END;
                
           END CASE;
           
        END IF;          
END$$

DELIMITER ;
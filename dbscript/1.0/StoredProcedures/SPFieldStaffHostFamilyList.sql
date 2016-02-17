DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPFieldStaffHostFamilyList`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPFieldStaffHostFamilyList`(IN fieldStaffId INT,IN flag TINYINT(1),IN category VARCHAR(50))
BEGIN
        DECLARE goId INT;
        DECLARE categoryName VARCHAR(50);
        DECLARE fsearch TINYINT(1);
        
        SET @goId = fieldstaffId;
        SET @fsearch = flag;
        SET @categoryName = category;
        
        IF ((@fsearch = 1) && (@categoryName IS NOT NULL)) THEN        -- MyHostFamilies
        
               SELECT hf.`hostFamilyGoId` AS GoId,
                      CONCAT(hf.`firstName`,' ',hf.`lastName`) AS NAME,
                      CONCAT(hf.`mailingAddress`,' ',' '/*,ls.`stateName`*/,' ',hf.`mailingCity`,' ',hf.`mailingZipCode`) AS Address,
                      l.email,
                      'Josef Mcde' AS LocalCordinator,
                      'AYP - J1' /*psv.programName*/ AS Seasons,
                      hfs.`hostFamilyStatusName` AS ApplicationStatus
               FROM `HostFamily` hf 
               INNER JOIN `HostFamilyStatus` hfs ON hfs.`hostFamilyStatusId` = hf.`hostFamilyStatusId`
               INNER JOIN `Login` l ON l.goId = hf.hostFamilyGoId;
           --    INNER JOIN `LookupUSStates` ls ON ls.`usStatesId` = hf.`mailingStateId`
           --   INNER JOIN `HostFamilySeason` hfss ON hfss.hostFamilyGoId = hf.hostFamilyGoId
           --    INNER JOIN `ProgramSeasons` psv ON psv.seasonId = hfss.seasonId AND psv.departmentProgramId = hfss.departmentProgramId
           --    INNER JOIN `FieldStaffSeason` fss ON fss.seasonId = hfss.seasonId AND fss.departmentProgramId = hfss.departmentProgramId
           --    INNER JOIN `FieldStaff` fs ON fs.fieldStaffGoId = fss.fieldStaffGoId AND fs.`fieldStaffTypeId` = 1
           --    INNER JOIN `FieldStaffSeason` fs1 ON fss.seasonId = fs1.seasonId AND fss.departmentProgramId = fs1.departmentProgramId
           --    WHERE fs1.`fieldStaffGoId` = @goId;
         
        END IF;
        
        IF ((@fsearch = 0) && (@categoryName IS NULL)) THEN       -- AllHostFamilies
        
               SELECT hf.`hostFamilyGoId` AS GoId,
                      CONCAT(hf.`firstName`,' ',hf.`lastName`) AS NAME,
                      CONCAT(hf.`mailingAddress`,' ',' '/*,ls.`stateName`*/,' ',hf.`mailingCity`,' ',hf.`mailingZipCode`) AS Address,
                      hf.`phone` AS phone,
                      hfs.`hostFamilyStatusName` AS STATUS,
                      l.email,
                      'Stain Lc' AS localCordinator,
                      'Eve Moertin'/*GROUP_CONCAT(p.`firstName`,' ',p.`lastName`)*/ AS Participants,
                      'AYP - J1' /*psv.programName */ AS Seasons       
               FROM `HostFamily` hf
               INNER JOIN `HostFamilyStatus` hfs ON hfs.`hostFamilyStatusId` = hf.`hostFamilyStatusId`
               INNER JOIN `Login` l ON l.goId = hf.hostFamilyGoId
               INNER JOIN `LookupUSStates` ls ON ls.`usStatesId` = hf.`mailingStateId`;
            --   INNER JOIN `HostFamilySeason` hfss ON hfss.hostFamilyGoId = hf.hostFamilyGoId
            --   INNER JOIN `ProgramSeasons` psv ON psv.seasonId = hfss.seasonId AND psv.departmentProgramId = hfss.departmentProgramId
            --   INNER JOIN `FieldStaffSeason` fss ON fss.seasonId = hfss.seasonId AND fss.departmentProgramId = hfss.departmentProgramId
            --   INNER JOIN `FieldStaff` fs ON fss.fieldStaffGoId = fs.fieldStaffGoId AND fs.`fieldStaffTypeId` = 1
            --   INNER JOIN `Participants` p ON p.seasonId = hfss.seasonId AND p.departmentProgramId = hfss.departmentProgramId;
               
         END IF;
    END$$

DELIMITER ;
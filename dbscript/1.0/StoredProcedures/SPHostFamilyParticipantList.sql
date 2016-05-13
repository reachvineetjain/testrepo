DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPHostFamilyParticipantList`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPHostFamilyParticipantList`(IN hfId INT,IN catrs VARCHAR(25))
BEGIN
    
         DECLARE hfGoId INT;
         DECLARE categories VARCHAR(25);
         
         SET @hfGoId = hfId;
         SET @categories = catrs;
         
         SELECT p.`photo`,
                p.`firstName`,
                p.`lastName`,
                p.`participantGoId`,
                3 AS Ranking,
                dp.`programName` AS Program,
                dpo.`programOptionCode` AS ProgramOption,
                psv.startDate AS programStartDate,
                psv.endDate AS programEndDate,
                15 AS Age,
                p.`countryId`,
                lc.`countryName`,
                lc.`countryFlag`,
                1 AS genderId,
                'Mr' AS Gender,
                hfp.`hostFamilyInterested`,
                psv.programName,
                psv.seasonId
         FROM `Participants` p 
         INNER JOIN `LookupCountries` lc ON lc.`countryId` = p.`countryId`
--       inner join `LookupGender` g on g.`genderId` = p.`genderId`
         INNER JOIN `HostFamilyParticipant` hfp ON hfp.`participantGoId` = p.`participantGoId`
         INNER JOIN `HostFamilySeason` hfs ON hfs.`hostFamilySeasonId` = hfp.`hostFamilySeasonId`
         INNER JOIN `DepartmentPrograms` dp ON dp.`departmentProgramId` = p.`departmentProgramId`
         INNER JOIN `DepartmentProgramOptions` dpo ON dpo.`departmentProgramOptionId` = p.`departmentProgramOptionId`
         INNER JOIN ProgramSeasons psv ON psv.seasonId = hfs.seasonId AND psv.departmentProgramId = hfs.departmentProgramId 
         WHERE hfs.`hostFamilyGoId` = @hfGoId;
                
                
                
    END$$

DELIMITER ;
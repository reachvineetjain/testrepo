DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPHostFamilySeasonList`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPHostFamilySeasonList`(IN hostFamilyGoId INT)
BEGIN
	DECLARE hGoId INT;
	SET @hGoId = hostFamilyGoId;
	
        SELECT psv.programName AS Season,
	       hfsn.seasonId,
	       hfsn.departmentProgramId,	
               hfsn.hostFamilySeasonId
       FROM `HostFamily` hf
       INNER JOIN `HostFamilySeason` hfsn ON hf.`hostFamilyGoId`=hfsn.`hostFamilyGoId`
--       INNER JOIN `HostFamilyStatus` hfs ON hfs.`hostFamilyStatusId` = hfsn.`hostFamilySeasonStatusId` 
       INNER JOIN `ProgramSeasons` psv ON hfsn.`seasonId`=psv.`seasonId` AND psv.departmentProgramId=hfsn.`departmentProgramId`
 --      INNER JOIN `SeasonStatus` ss ON ss.`seasonStatusId`= psv.programStatusId
 --      INNER JOIN `FieldStaff` fsrd ON hfsn.`regionalDirectorId`=fsrd.`fieldStaffGoId` 
 --      INNER JOIN `FieldStaff` fslc ON hfsn.`areaRepresentativeId`=fslc.`fieldStaffGoId` 
 --      INNER JOIN `FieldStaff` fs1 ON hfsn.`regionalManagerId`=fs1.`fieldStaffGoId` 
--     INNER JOIN `FieldStaff` fs2 ON hfsn.`localCoordinatorId`=fs2.`fieldStaffGoId` 
--       INNER JOIN `DepartmentPrograms` dp ON dp.`departmentProgramId` = psv.`departmentProgramId` 
--       INNER JOIN `LookupDepartments` ld ON ld.`departmentId` = dp.`departmentId`
       WHERE hf.hostFamilyGoId = @hGoId;
       
 
       
       
END$$

DELIMITER ;
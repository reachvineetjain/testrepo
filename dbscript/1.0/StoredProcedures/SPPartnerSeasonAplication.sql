DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPPartnerSeasonAplication`$$

CREATE DEFINER=`phanipothula`@`%` PROCEDURE `SPPartnerSeasonAplication`( IN Id INT (11))
BEGIN
    DECLARE GoId INT(11) ;
    DECLARE pCount INT(3);
    SET @GoId = Id;
    SET @pCount = (SELECT COUNT(*) FROM PartnerSeason WHERE partnerGoId = @GoId);
    
    IF (@pCount =0) THEN
		SELECT psa.programName,psa.seasonId,psa.departmentProgramId,ld.`acronym`,dp.`programName` AS depProgramName,startDate AS seasonStartDate,endDate AS seasonEndDate,AppDeadLineDate AS appDeadlineDate
		FROM ProgramSeasons psa 
		INNER JOIN `DepartmentPrograms` dp ON dp.`departmentProgramId` = psa.`departmentProgramId`
		INNER JOIN PartnerProgram pp ON pp.lookupDepartmentProgramId = dp.departmentProgramId	
		INNER JOIN `LookupDepartments` ld ON ld.`departmentId` = dp.`departmentId`
		WHERE psa.startDate > CURDATE() 
		AND psa.programStatusId =1
		AND pp.lookupDepartmentProgramId NOT IN (6,7)
		AND dp.departmentProgramId NOT IN (6,7,8,9)
		AND pp.partnerGoId = @GoId
		AND pp.isEligible = 1
		GROUP BY psa.programName
		UNION
		SELECT psa.programName,psa.seasonId,psa.departmentProgramId,ld.`acronym`,dp.`programName` AS depProgramName,startDate AS seasonStartDate,endDate AS seasonEndDate,AppDeadLineDate AS appDeadlineDate
		FROM ProgramSeasons psa 
		INNER JOIN `DepartmentPrograms` dp ON dp.`departmentProgramId` = psa.`departmentProgramId`
		INNER JOIN `LookupDepartments` ld ON ld.`departmentId` = dp.`departmentId`
		JOIN PartnerProgram pp 
		WHERE psa.startDate > CURDATE() 
		AND psa.programStatusId =1
		AND pp.lookupDepartmentProgramId  IN (6,7)
		AND dp.departmentProgramId IN (6,7,8,9)
		AND pp.partnerGoId = @GoId
		AND pp.isEligible = 1
		GROUP BY psa.programName;
		
    ELSE
		SELECT p.programName,p.seasonId,p.departmentProgramId,ld.`acronym`,dp.`programName` AS depProgramName,startDate AS seasonStartDate,endDate AS seasonEndDate,AppDeadLineDate AS appDeadlineDate
		FROM ProgramSeasons p 
		INNER JOIN `DepartmentPrograms` dp ON dp.`departmentProgramId` = p.`departmentProgramId`
		INNER JOIN `LookupDepartments` ld ON ld.`departmentId` = dp.`departmentId`
		INNER JOIN PartnerProgram pp ON pp.lookupDepartmentProgramId = dp.departmentProgramId
		WHERE (p.seasonId,p.departmentProgramId) NOT IN
		(SELECT ps.seasonId,ps.departmentProgramId FROM PartnerSeason ps WHERE ps.partnerGoId = @GoId)
		AND p.startDate > CURDATE() 
		AND pp.lookupDepartmentProgramId NOT IN (6,7)
		AND dp.departmentProgramId NOT IN (6,7,8,9)
		AND pp.partnerGoId = @GoId
		AND pp.isEligible = 1
		AND p.programStatusId =1
		GROUP BY p.programName
		UNION
		SELECT p.programName,p.seasonId,p.departmentProgramId,ld.`acronym`,dp.`programName` AS depProgramName,startDate AS seasonStartDate,endDate AS seasonEndDate,AppDeadLineDate AS appDeadlineDate
		FROM ProgramSeasons p 
		INNER JOIN `DepartmentPrograms` dp ON dp.`departmentProgramId` = p.`departmentProgramId`
		INNER JOIN `LookupDepartments` ld ON ld.`departmentId` = dp.`departmentId`
		JOIN PartnerProgram pp
		WHERE (p.seasonId,p.departmentProgramId) NOT IN
		(SELECT ps.seasonId,ps.departmentProgramId FROM PartnerSeason ps WHERE ps.partnerGoId = @GoId)
		AND p.startDate > CURDATE() 
		AND pp.lookupDepartmentProgramId  IN (6,7)
		AND dp.departmentProgramId IN (6,7,8,9)
		AND pp.partnerGoId = @GoId
		AND pp.isEligible = 1
		AND p.programStatusId =1
		GROUP BY p.programName;
    END IF;
    END$$

DELIMITER ;
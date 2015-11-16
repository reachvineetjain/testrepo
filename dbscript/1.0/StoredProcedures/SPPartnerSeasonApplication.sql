DELIMITER $$

USE `cci_gh_go_login`$$

DROP PROCEDURE IF EXISTS `SPPartnerSeasonAplication`$$

CREATE DEFINER=`phanipothula`@`%` PROCEDURE `SPPartnerSeasonAplication`( IN Id INT (11))
BEGIN
    DECLARE GoId INT(11) ;
    DECLARE pCount INT(3);
    SET @GoId = Id;
    SET @pCount = (SELECT COUNT(*) FROM PartnerSeason WHERE partnerGoId = @GoId);
    
    IF (@pCount =0) THEN
		SELECT psa.programName,psa.seasonId,psa.departmentProgramId
		FROM ProgramSeasons psa 
		WHERE psa.latestStartDate > CURDATE() 
		AND psa.programStatusId =1
		GROUP BY psa.programName;
    ELSE
		SELECT p.programName,p.seasonId,p.departmentProgramId FROM ProgramSeasons p 
		WHERE (p.seasonId,p.departmentProgramId) NOT IN
		(SELECT ps.seasonId,ps.departmentProgramId FROM PartnerSeason ps WHERE ps.partnerGoId = @GoId)
		AND p.latestStartDate > CURDATE() 
		AND p.programStatusId =1
		GROUP BY p.programName;
    END IF;
    END$$

DELIMITER ;
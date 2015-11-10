DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPPartnerParticipantSeasons`$$

CREATE DEFINER=`phanipothula`@`%` PROCEDURE `SPPartnerParticipantSeasons`(IN partnerId INT)
BEGIN
		DECLARE goId INT;
		SET @goId = partnerId;
    
		SELECT pgs.programName,ps.seasonId,ps.departmentProgramId 
		FROM ProgramSeasons pgs
		INNER JOIN 	PartnerSeason ps ON pgs.seasonId = ps.seasonId AND pgs.departmentProgramId = ps.departmentProgramId
		WHERE ps.partnerGoId = @goId;
    END$$

DELIMITER ;
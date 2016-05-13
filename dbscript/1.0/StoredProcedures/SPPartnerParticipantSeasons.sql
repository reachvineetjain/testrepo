DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPPartnerParticipantSeasons`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPPartnerParticipantSeasons`(IN partnerId INT,IN participantId INT)
BEGIN
		DECLARE goId,paxGoId INT;
		SET @goId = partnerId;
		SET @paxGoId = participantId;
		
		IF @paxGoId = '' THEN
			SET @paxGoId = NULL;
		END IF;
		
-- =========For Add Pax Pag ===============================		
		IF @paxGoId IS NULL
		THEN
    
		     SELECT pgs.programName,
		            pgs.seasonId,
		            pgs.departmentProgramId 
		     FROM ProgramSeasons pgs
		     INNER JOIN PartnerSeason ps ON pgs.seasonId = ps.seasonId 
		                                 AND pgs.departmentProgramId = ps.departmentProgramId
		                                 AND ps.partnerGoId = @goId  
		                                 AND ps.disableAddParticipant = 0 
		                                 AND pgs.AppDeadlineDate > CURRENT_TIMESTAMP
		                                 AND ps.partnerSeasonStatusId = 5
		                                 AND pgs.startDate != '9999-09-09 00:00:00'
		                                 AND pgs.programStatusId NOT IN (2,3,4);
		
		END IF;
-- =========For Added Pax page=============================	
		IF @paxGoId IS NOT NULL
		THEN 
		
		    SELECT pgs.programName,pgs.seasonId,pgs.departmentProgramId 
                    FROM ProgramSeasons pgs
                    WHERE (pgs.seasonId,pgs.departmentProgramId) IN
                          (SELECT seasonId,departmentProgramId 
                          FROM PartnerSeason ps 
                          WHERE pgs.seasonId = ps.seasonId AND ps.partnerSeasonStatusId = 5
                          AND pgs.departmentProgramId = ps.departmentProgramId
                          AND ps.partnerGoId = @goId 
                          AND (ps.seasonId,ps.departmentProgramId) NOT IN
                              (SELECT seasonId,departmentProgramId 
                               FROM `Participants` p WHERE p.seasonId = ps.seasonId 
                               AND ps.departmentProgramId = p.departmentProgramId
                               AND p.partnerGoId = ps.partnerGoId))
                               -- AND pgs.startDate > CURRENT_TIMESTAMP 
                               AND pgs.AppDeadlineDate > CURRENT_TIMESTAMP
                               AND pgs.startDate != '9999-09-09 00:00:00'
                               AND pgs.programStatusId NOT IN (2,3,4);
               END IF;
 END$$

DELIMITER ;
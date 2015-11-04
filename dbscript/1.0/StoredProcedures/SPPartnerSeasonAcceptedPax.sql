DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPPartnerSeasonAcceptedPax`$$

CREATE DEFINER=`phanipothula`@`%` PROCEDURE `SPPartnerSeasonAcceptedPax`( IN partnerId INT, IN seasonId INT, IN depPgmId INT, depPgmOptionId INT)
BEGIN
		DECLARE goId,sId,pgmId,pgmOptionId INT;
		SET @goId = partnerId;
		SET @sId = seasonId;
		SET @pgmId = depPgmId;
		SET @pgmOptionId = depPgmOptionId;
		
		
	IF (@pgmId = 1) THEN
		CASE @pgmOptionId
		WHEN 1 THEN
			SELECT COUNT(*)
			FROM Participants p
			INNER JOIN ParticipantStatus ps ON p.participantStatusId = ps.participantStatusId
			WHERE partnerGoId = @goId
			AND seasonId = @sId
			AND departmentProgramId = @pgmId
			AND departmentProgramOptionId IN (1,2)
			AND ps.participantStatusName= 'Accepted';
			
		WHEN 3 THEN
			SELECT COUNT(*)
			FROM Participants p
			INNER JOIN ParticipantStatus ps ON p.participantStatusId = ps.participantStatusId
			WHERE partnerGoId = @goId
			AND seasonId = @sId
			AND departmentProgramId = @pgmId
			AND departmentProgramOptionId IN (3,4)
			AND ps.participantStatusName= 'Accepted';
		ELSE
			BEGIN
			END;
		END CASE;
	END IF;
	IF (@pgmId = 2) THEN
		
		CASE @pgmOptionId
		WHEN 5 THEN
			SELECT COUNT(*)
			FROM Participants p
			INNER JOIN ParticipantStatus ps ON p.participantStatusId = ps.participantStatusId
			WHERE partnerGoId = @goId
			AND seasonId = @sId
			AND departmentProgramId = @pgmId
			AND departmentProgramOptionId IN (5,7)
			AND ps.participantStatusName= 'Accepted';
		WHEN 8 THEN
			SELECT COUNT(*)
			FROM Participants p
			INNER JOIN ParticipantStatus ps ON p.participantStatusId = ps.participantStatusId
			WHERE partnerGoId = @goId
			AND seasonId = @sId
			AND departmentProgramId = @pgmId
			AND departmentProgramOptionId IN (8,9)
			AND ps.participantStatusName= 'Accepted';
		ELSE
			BEGIN
			END;
		END CASE;
	END IF;
		
    END$$

DELIMITER ;
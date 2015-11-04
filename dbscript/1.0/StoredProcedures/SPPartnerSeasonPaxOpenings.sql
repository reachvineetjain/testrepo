DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPPartnerSeasonPaxOpenings`$$

CREATE DEFINER=`phanipothula`@`%` PROCEDURE `SPPartnerSeasonPaxOpenings`(IN partnerId INT, IN seasonId INT, IN depPgmId INT, IN depPgmOptionId INT)
BEGIN
		DECLARE goId,sId,pgmId,pgmOptionId,cnt INT;
		SET @goId = partnerId;
		SET @sId = seasonId;
		SET @pgmId = depPgmId;
		SET @pgmOptionId = depPgmOptionId;
		
	IF (@pgmId = 1) THEN
		CASE @pgmOptionId
		WHEN 1 THEN
			SET @cnt = (SELECT COUNT(*)
			FROM Participants p
			INNER JOIN ParticipantStatus ps ON p.participantStatusId = ps.participantStatusId
			WHERE partnerGoId = @goId
			AND seasonId = @sId
			AND departmentProgramId = @pgmId
			AND departmentProgramOptionId IN (1,2)
			AND ps.participantStatusName= 'Accepted');		
			
			
			SELECT ((psa.maxPax + psa.maxGuaranteedPax) - @cnt) AS Openings
			FROM PartnerSeasonAllocation psa
			INNER JOIN PartnerSeason ps ON psa.partnerSeasonId = ps.partnerSeasonId
			WHERE ps.partnerGoId = @goId
			AND ps.seasonId = @sId
			AND ps.departmentProgramId = @pgmId
			AND psa.departmentProgramOptionId = @pgmOptionId;
			
			
		WHEN 3 THEN
			SET @cnt = (SELECT COUNT(*)
			FROM Participants p
			INNER JOIN ParticipantStatus ps ON p.participantStatusId = ps.participantStatusId
			WHERE partnerGoId = @goId
			AND seasonId = @sId
			AND departmentProgramId = @pgmId
			AND departmentProgramOptionId IN (3,4)
			AND ps.participantStatusName= 'Accepted');		
			
			
			SELECT ((psa.maxPax + psa.maxGuaranteedPax) - @cnt) AS Openings
			FROM PartnerSeasonAllocation psa
			INNER JOIN PartnerSeason ps ON psa.partnerSeasonId = ps.partnerSeasonId
			WHERE ps.partnerGoId = @goId
			AND ps.seasonId = @sId
			AND ps.departmentProgramId = @pgmId
			AND psa.departmentProgramOptionId = @pgmOptionId;
			
		ELSE
			BEGIN
			END;
		END CASE;
	END IF;
	IF (@pgmId = 2) THEN
		
		CASE @pgmOptionId
		WHEN 5 THEN
			SET @cnt = (SELECT COUNT(*)
			FROM Participants p
			INNER JOIN ParticipantStatus ps ON p.participantStatusId = ps.participantStatusId
			WHERE partnerGoId = @goId
			AND seasonId = @sId
			AND departmentProgramId = @pgmId
			AND departmentProgramOptionId IN (5,7)
			AND ps.participantStatusName= 'Accepted');		
			
			
			SELECT ((psa.maxPax + psa.maxGuaranteedPax) - @cnt) AS Openings
			FROM PartnerSeasonAllocation psa
			INNER JOIN PartnerSeason ps ON psa.partnerSeasonId = ps.partnerSeasonId
			WHERE ps.partnerGoId = @goId
			AND ps.seasonId = @sId
			AND ps.departmentProgramId = @pgmId
			AND psa.departmentProgramOptionId = @pgmOptionId;
			
		WHEN 8 THEN
			SET @cnt = (SELECT COUNT(*)
			FROM Participants p
			INNER JOIN ParticipantStatus ps ON p.participantStatusId = ps.participantStatusId
			WHERE partnerGoId = @goId
			AND seasonId = @sId
			AND departmentProgramId = @pgmId
			AND departmentProgramOptionId IN (8,9)
			AND ps.participantStatusName= 'Accepted');		
			
			
			SELECT ((psa.maxPax + psa.maxGuaranteedPax) - @cnt) AS Openings
			FROM PartnerSeasonAllocation psa
			INNER JOIN PartnerSeason ps ON psa.partnerSeasonId = ps.partnerSeasonId
			WHERE ps.partnerGoId = @goId
			AND ps.seasonId = @sId
			AND ps.departmentProgramId = @pgmId
			AND psa.departmentProgramOptionId = @pgmOptionId;
			
		ELSE
			BEGIN
			END;
		END CASE;
	END IF;
    END$$

DELIMITER ;
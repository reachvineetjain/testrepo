DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPPartnerSeasonPaxAllocated`$$

CREATE DEFINER=`phanipothula`@`%` PROCEDURE `SPPartnerSeasonPaxAllocated`(IN partnerId INT, IN seasonId INT, IN depPgmId INT)
BEGIN
		 DECLARE goId,sId,pgmId INT;
		 SET @goId = partnerId;
		 SET @sId = seasonId;
		 SET @pgmId = depPgmId;
	 
	 
		 SELECT SUM(psa.maxPax + psa.maxGuaranteedPax) AS paxAllocated 
		 FROM PartnerSeasonAllocation psa
		 INNER JOIN PartnerSeason ps ON ps.partnerSeasonId = psa.partnerSeasonId
		 WHERE ps.partnerGoId = @goId
		 AND ps.seasonId = @sId
		 AND ps.departmentProgramId = @pgmId;
  
    END$$

DELIMITER ;
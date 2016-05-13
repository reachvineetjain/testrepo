DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPAdminWQPartnerAllocationChange`$$

CREATE DEFINER=`phanipothula`@`%` PROCEDURE `SPAdminWQPartnerAllocationChange`(IN typeId INT(3), IN categoryId INT(3), IN cciStaffUserId INT, roletype VARCHAR(45))
BEGIN
		DECLARE GoId,tId,cId INT;
		DECLARE rType VARCHAR(45);
		SET @GoId = cciStaffUserId;
		SET @tId = typeId;
		SET @cId = categoryId;
		SET @rType = roleType;
				
		SELECT p.companyName,p.partnerGoId,pst.partnerStatusName,psv.programName AS seasonName,psa.departmentProgramOptionId,psa.maxPax,psa.`maxGuaranteedPax`,psa.requestedMaxPax,psa.followupDate,
		psa.requestedMaxGuaranteedPax,lc.countryName,psa.`allocationRequestedOn`,lc.countryFlag,hspa.maxGuaranteedPax AS SeasonGuaranteedAllocations,hspa.maxUnguaranteedPax AS SeasonUnGuaranteedAllocations, psa.partnerSeasonAllocationId
		FROM `Partner` p
		INNER JOIN `PartnerReviewStatus` prs ON p.partnerGoId = prs.partnerGoId
		INNER JOIN `PartnerStatus` pst ON prs.partnerAgentStatusId = pst.partnerStatusId
		INNER JOIN `LookupCountries` lc ON p.countryId = lc.countryId
		INNER JOIN `PartnerSeason` ps ON ps.partnerGoId = p.partnerGoId
		INNER JOIN `PartnerSeasonAllocation` psa ON ps.partnerSeasonId = psa.partnerSeasonId
		INNER JOIN `ProgramSeasons` psv ON psv.seasonId = ps.seasonId AND psv.departmentProgramId=ps.`departmentProgramId`
		LEFT JOIN `SeasonHSPAllocation` hspa ON hspa.seasonId = ps.seasonId AND psa.departmentProgramOptionId = hspa.departmentProgramOptionId
		INNER JOIN AdminWorkQueue aq ON aq.`seasonId` = psv.seasonId AND aq.departmentProgramId = psv.`departmentProgramId` AND adminWQTypeId = @tId 
		AND adminWQCategoryId = @cId AND cciStaffUserGoId = @GoId AND targetRoleType = @rType
		WHERE p.partnerGoId IN (SELECT targetGoId 
						FROM AdminWorkQueue
						WHERE adminWQTypeId = @tId
						AND adminWQCategoryId = @cId
						AND cciStaffUserGoId = @GoId
						AND targetRoleType = @rType GROUP BY targetGoId)
		AND (psa.requestedMaxGuaranteedPax IS NOT NULL
		OR psa.requestedMaxPax IS NOT NULL);
		
		
    END$$

DELIMITER ;
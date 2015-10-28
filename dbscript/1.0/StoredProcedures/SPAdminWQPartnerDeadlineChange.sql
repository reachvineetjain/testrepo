DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPAdminWQPartnerDeadlineChange`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPAdminWQPartnerDeadlineChange`(IN typeId INT(3), IN categoryId INT(3), IN cciStaffUserId INT, roletype VARCHAR(45))
BEGIN
		DECLARE GoId,tId,cId INT;
		DECLARE rType VARCHAR(45);
		SET @GoId = cciStaffUserId;
		SET @tId = typeId;
		SET @cId = categoryId;
		SET @rType = roleType;
				
		SELECT p.companyName,p.partnerGoId,pst.partnerStatusName,psv.programName AS seasonName,
		lc.countryName,ps.`deadlineRequestedOn`,lc.countryFlag,ps.`partnerSeasonAppDeadlineDate`,ps.`partnerSeasonExtAppDeadlineDate`,
		ps.`partnerSeasonSecSemDeadlineDate`,ps.`partnerSeasonExtSecSemDeadlineDate`
		FROM `Partner` p
		INNER JOIN `PartnerReviewStatus` prs ON p.partnerGoId = prs.partnerGoId
		INNER JOIN `PartnerStatus` pst ON prs.partnerAgentStatusId = pst.partnerStatusId
		INNER JOIN `LookupCountries` lc ON p.countryId = lc.countryId
		INNER JOIN `PartnerSeason` ps ON ps.partnerGoId = p.partnerGoId
		INNER JOIN `ProgramSeasons` psv ON psv.seasonId = ps.seasonId AND psv.departmentProgramId = ps.`departmentProgramId`
                WHERE p.partnerGoId IN (SELECT targetGoId 
						FROM AdminWorkQueue
						WHERE adminWQTypeId = @tId
						AND adminWQCategoryId = @cId
						AND cciStaffUserGoId = @GoId
						AND targetRoleType = @rType GROUP BY targetGoId)
		AND (ps.`partnerSeasonExtAppDeadlineDate` IS NOT NULL
		OR ps.`partnerSeasonExtSecSemDeadlineDate` IS NOT NULL);
		
		
    END$$

DELIMITER ;
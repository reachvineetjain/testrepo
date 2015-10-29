DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPAdminWQPartnerNotesReview`$$

CREATE DEFINER=`cbishoi`@`%` PROCEDURE `SPAdminWQPartnerNotesReview`(IN typeId INT(3), IN categoryId INT(3), IN cciStaffUserId INT, roletype VARCHAR(45))
BEGIN
DECLARE GoId,tId,cId INT;
		DECLARE rType VARCHAR(45);
		SET @GoId = cciStaffUserId;
		SET @tId = typeId;
		SET @cId = categoryId;
		SET @rType = roleType;
    
SELECT p.`companyName`,p.`partnerGoId`,l.`countryName`,l.`countryFlag`,
prs.`partnerAgentStatusId`,pnt.`PartnerNoteTopicName`,pnt.`isPublic`,pn.`createdOn` AS notesCreatedOn,pn.`createdBy` AS notesCreatedBy,
pn.`partnerNote`,pnt.`createdBy` AS notesTopicCreatedBy, pnt.`createdOn` AS notesTopicCreatedOn
FROM `Partner` p
INNER JOIN `LookupCountries` l ON p.`countryId` = l.`countryId`
INNER JOIN `PartnerReviewStatus` prs ON prs.`partnerGoId`=p.`partnerGoId`
INNER JOIN `PartnerNoteTopics` pnt ON pnt.`partnerGoId`=p.`partnerGoId`
INNER JOIN `PartnerNotes` pn ON pn.`partnerGoId`=p.`partnerGoId` AND pn.`partnerNoteTopicId`=pnt.`partnerNoteTopicId`
WHERE p.partnerGoId IN (SELECT targetGoId 
						FROM AdminWorkQueue
						WHERE adminWQTypeId = @tId
						AND adminWQCategoryId = @cId
						AND cciStaffUserGoId = @GoId
						AND targetRoleType = @rType GROUP BY targetGoId) 
						AND pn.`hasRead` = 0 AND pnt.`isVisibleToPartner` = 0;
    END$$

DELIMITER ;
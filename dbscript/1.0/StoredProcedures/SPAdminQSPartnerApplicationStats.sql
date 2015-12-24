DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPAdminQSPartnerApplicationStats`$$

CREATE DEFINER=`phanipothula`@`%` PROCEDURE `SPAdminQSPartnerApplicationStats`()
BEGIN
DELETE FROM `AdminQuickStatsCategoryAggregate` 
WHERE `adminQSTypeId` = 1 AND `adminQSCategoryId` = 1;
INSERT INTO `AdminQuickStatsCategoryAggregate` 
(`adminQSTypeId`,`adminQSCategoryId`,`adminQSCategoryName`,`adminQSCategoryAggregate`,`status`,`modifiedDate`)
VALUES 
(1,1,'Partner',(SELECT COUNT(`partnerGoId`) FROM `PartnerReviewStatus` WHERE `partnerAgentStatusId` = 4),
'Pending',CURRENT_TIMESTAMP),
(1,1,'Partner',(SELECT COUNT(`partnerGoId`) FROM `PartnerReviewStatus` WHERE `partnerAgentStatusId` = 6),
'Questionnaire Due',CURRENT_TIMESTAMP),
(1,1,'Partner',(SELECT COUNT(`partnerGoId`) FROM `PartnerReviewStatus` WHERE `partnerAgentStatusId` = 5),
'Approved',CURRENT_TIMESTAMP),
(1,1,'Partner',(SELECT COUNT(`partnerGoId`) FROM `PartnerReviewStatus` WHERE `partnerAgentStatusId` = 2),
'Not Approved',CURRENT_TIMESTAMP),
(1,1,'Partner',(SELECT COUNT(`partnerGoId`) FROM `PartnerReviewStatus` WHERE `partnerAgentStatusId` = 3),
'BlackListed',CURRENT_TIMESTAMP);
/*update `AdminQuickStatsCategoryAggregate` aq
set aq.`adminQSCategoryAggregate` = (select count(`partnerGoId`) from `PartnerReviewStatus` prs
                                                              inner join `PartnerStatus` ps
                                                              on prs.`partnerAgentStatusId` = ps.`partnerStatusId`
                                                              and prs.`partnerAgentStatusId` is not null)
where aq.`status` = ps.`partnerStatusName`;*/
 END$$

DELIMITER ;
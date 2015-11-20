DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPPartnerQSParticipantsStats`$$

CREATE DEFINER=`phanipothula`@`%` PROCEDURE `SPPartnerQSParticipantsStats`()
BEGIN
DECLARE f_partnerGoId INTEGER DEFAULT 0;
DECLARE goid INT;
DECLARE partnergoid CURSOR FOR
SELECT DISTINCT `partnerGoId`
FROM `Partner`;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET f_partnerGoId = 1;
DELETE FROM `PartnerQuickStatsCategoryAggregate` 
WHERE `partnerQSTypeId` = 1 ;
ALTER TABLE `PartnerQuickStatsCategoryAggregate` AUTO_INCREMENT = 1;
OPEN partnergoid;
get_partner:LOOP
FETCH partnergoid INTO goid;
IF f_partnerGoId = 1 THEN 
 LEAVE get_partner;
END IF;
BLOCK_2: BEGIN
 DECLARE f_depPgmId INTEGER DEFAULT 0;
 DECLARE pgmId INT;
 
 DECLARE depProgramId CURSOR FOR 
 SELECT lookupDepartmentProgramId 
 FROM LookupDepartmentPrograms
 WHERE lookupDepartmentProgramId < 4;
 
 DECLARE CONTINUE HANDLER FOR NOT FOUND SET f_depPgmId = 1;
 OPEN  depProgramId;
 get_pgmId: LOOP
 FETCH depProgramId INTO pgmId;
 IF f_depPgmId = 1 THEN 
  LEAVE get_pgmId;
 END IF;
 
INSERT INTO `PartnerQuickStatsCategoryAggregate` 
(`partnerQSCategoryId`,`partnerQSCategoryName`,`partnerQSTypeId`,`partnerQSCategoryAggregate`,`partnerGoId`,`lookupDepartmentProgramId`,`modifiedDate`)
VALUES 
(1,'Submitted',1,(SELECT COUNT(*) FROM `Participants` WHERE `participantStatusId` = 14 AND departmentProgramId = pgmId AND (partnerGoId = goid OR subPartnerId = goid)),goid,pgmId, CURRENT_TIMESTAMP),
(2,'Partner Review',1,(SELECT COUNT(*) FROM `Participants` WHERE `participantStatusId` = 3 AND departmentProgramId = pgmId AND (partnerGoId = goid OR subPartnerId = goid)),goid,pgmId,CURRENT_TIMESTAMP),
(3,'CCI review',1,(SELECT COUNT(*) FROM `Participants` WHERE `participantStatusId` = 4 AND departmentProgramId = pgmId AND (partnerGoId = goid OR subPartnerId = goid)),goid,pgmId,CURRENT_TIMESTAMP),
(4,'Accepted',1,(SELECT COUNT(*) FROM `Participants` WHERE `participantStatusId` = 5 AND departmentProgramId = pgmId AND (partnerGoId = goid OR subPartnerId = goid)),goid,pgmId,CURRENT_TIMESTAMP),
(5,'Rejected',1,(SELECT COUNT(*) FROM `Participants` WHERE `participantStatusId` = 7 AND departmentProgramId = pgmId AND (partnerGoId = goid OR subPartnerId = goid)),goid,pgmId,CURRENT_TIMESTAMP); 
END LOOP get_pgmId;
CLOSE depProgramId;
END BLOCK_2;
END LOOP get_partner;
CLOSE partnergoid;
 END$$

DELIMITER ;
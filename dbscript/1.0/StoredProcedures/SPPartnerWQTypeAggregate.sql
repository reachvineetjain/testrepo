DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPPartnerWQTypeAggregate`$$

CREATE DEFINER=`phanipothula`@`%` PROCEDURE `SPPartnerWQTypeAggregate`()
BEGIN
DECLARE partner_done INT DEFAULT 0;
DECLARE goId INT;
DECLARE cciId CURSOR FOR 
SELECT partnerGoId FROM Partner;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET partner_done = 1;
TRUNCATE TABLE `PartnerWorkQueueTypeAggregate`;
ALTER TABLE `PartnerWorkQueueTypeAggregate` AUTO_INCREMENT = 1;
OPEN cciId;
get_goId : LOOP
FETCH cciId INTO goId;
IF partner_done = 1 THEN
LEAVE get_goId;
END IF;
  BLOCK_DepPgm: BEGIN
				DECLARE pgm_done INT DEFAULT 0;
				DECLARE pgmId INT;
				DECLARE depPgmId CURSOR FOR 
				SELECT lookupDepartmentProgramId FROM LookupDepartmentPrograms WHERE lookupDepartmentProgramId IN (1,2,3);
				DECLARE CONTINUE HANDLER FOR NOT FOUND SET pgm_done = 1;
				OPEN depPgmId;
				get_pgmId : LOOP
				FETCH depPgmId INTO pgmId;
				IF pgm_done = 1 THEN
				LEAVE get_pgmId;
				END IF;   
				BLOCK_Type:	BEGIN	
							DECLARE t_done INT DEFAULT 0;
							DECLARE tId INT;
							DECLARE typeId CURSOR FOR
							SELECT `partnerWQTypeId` FROM `PartnerWorkQueueType`;
							DECLARE CONTINUE HANDLER FOR NOT FOUND SET t_done = 1;
	
							OPEN typeId;
							get_typeId : LOOP
							FETCH typeId INTO tId;
							IF t_done = 1 THEN
							LEAVE get_typeId;
							END IF;
											INSERT INTO `PartnerWorkQueueTypeAggregate` (`partnerWQTypeId`,`partnerWQTypeName`,`partnerGoId`,`lookupDepartmentProgramId`,`partnerWQTypeAggregate`,`modifiedDate`) 
											SELECT IFNULL((SELECT `partnerWQTypeId` FROM `PartnerWorkQueue` WHERE  partnerWQTypeId = tId AND partnerGoId = goId AND lookupDepartmentProgramId = pgmId GROUP BY partnerWQTypeId),tId),
											       (SELECT `partnerWQTypeName` FROM `PartnerWorkQueueType` WHERE partnerWQTypeId = tId),
												   IFNULL((SELECT `partnerGoId` FROM `PartnerWorkQueue` WHERE partnerWQTypeId = tId AND partnerGoId = goId AND lookupDepartmentProgramId = pgmId GROUP BY partnerGoId),goId), 
												   IFNULL((SELECT lookupDepartmentProgramId FROM `PartnerWorkQueue` WHERE partnerWQTypeId = tId AND partnerGoId = goId AND lookupDepartmentProgramId = pgmId GROUP BY lookupDepartmentProgramId),pgmId),
												   (SELECT COUNT(partnerWQTypeId) FROM `PartnerWorkQueue` WHERE partnerWQTypeId = tId AND partnerGoId = goId AND lookupDepartmentProgramId = pgmId),  
											       CURRENT_TIMESTAMP;
												
							END LOOP get_typeId;
							CLOSE typeId;
							
				END BLOCK_Type;	
				END LOOP get_pgmId;
				CLOSE depPgmId;
END BLOCK_DepPgm;  
END LOOP get_goId;
CLOSE cciId;
END$$

DELIMITER ;
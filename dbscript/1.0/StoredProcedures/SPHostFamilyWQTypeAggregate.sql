DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPHostFamilyWQTypeAggregate`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPHostFamilyWQTypeAggregate`()
BEGIN
DECLARE hostFamily_done INT DEFAULT 0;
DECLARE goId INT;
DECLARE cciId CURSOR FOR 
SELECT hostFamilyGoId FROM HostFamily;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET hostFamily_done = 1;
TRUNCATE TABLE `HostFamilyWorkQueueTypeAggregate`;
ALTER TABLE `HostFamilyWorkQueueTypeAggregate` AUTO_INCREMENT = 1;
OPEN cciId;
get_goId : LOOP
FETCH cciId INTO goId;
IF hostFamily_done = 1 THEN
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
							SELECT `hostFamilyWQTypeId` FROM `HostFamilyWorkQueueType`;
							DECLARE CONTINUE HANDLER FOR NOT FOUND SET t_done = 1;
	
							OPEN typeId;
							get_typeId : LOOP
							FETCH typeId INTO tId;
							IF t_done = 1 THEN
							LEAVE get_typeId;
							END IF;
											INSERT INTO `HostFamilyWorkQueueTypeAggregate` (`hostFamilyWQTypeId`,`hostFamilyWQTypeName`,`hostFamilyGoId`,`lookupDepartmentProgramId`,`hostFamilyWQTypeAggregate`,`modifiedDate`) 
											SELECT IFNULL((SELECT `hostFamilyWQTypeId` FROM `HostFamilyWorkQueue` WHERE  hostFamilyWQTypeId = tId AND hostFamilyGoId = goId AND lookupDepartmentProgramId = pgmId GROUP BY hostFamilyWQTypeId),tId),
											       (SELECT `hostFamilyWQTypeName` FROM `HostFamilyWorkQueueType` WHERE hostFamilyWQTypeId = tId),
												   IFNULL((SELECT `hostFamilyGoId` FROM `HostFamilyWorkQueue` WHERE hostFamilyWQTypeId = tId AND hostFamilyGoId = goId AND lookupDepartmentProgramId = pgmId GROUP BY hostFamilyGoId),goId), 
												   IFNULL((SELECT lookupDepartmentProgramId FROM `HostFamilyWorkQueue` WHERE hostFamilyWQTypeId = tId AND hostFamilyGoId = goId AND lookupDepartmentProgramId = pgmId GROUP BY lookupDepartmentProgramId),pgmId),
												   (SELECT COUNT(hostFamilyWQTypeId) FROM `HostFamilyWorkQueue` WHERE hostFamilyWQTypeId = tId AND hostFamilyGoId = goId AND lookupDepartmentProgramId = pgmId),  
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
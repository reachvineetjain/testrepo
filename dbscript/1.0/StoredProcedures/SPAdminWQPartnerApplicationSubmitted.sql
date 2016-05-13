DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPAdminWQPartnerApplicationSubmitted`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPAdminWQPartnerApplicationSubmitted`(IN partnerId INT)
BEGIN
		DECLARE t_done INT DEFAULT 0;	
		DECLARE pId,uId INT;
		 
	
		DECLARE goId CURSOR FOR 
		SELECT csu.cciStaffUserId 
		FROM `CCIStaffUsers` csu
		INNER JOIN `CCIStaffUsersCCIStaffRoles` csur ON csu.cciStaffUserId = csur.cciStaffUserId
		INNER JOIN  `CCIStaffRoles` csr ON csur.cciStaffRoleId = csr.cciStaffRoleId AND csr.cciStaffRolename = 'System Administrator';
		DECLARE CONTINUE HANDLER FOR NOT FOUND SET t_done = 1;	
		
		SET @pId = partnerId; 
		OPEN goId;
		get_goId : LOOP
		FETCH goId INTO uId;
		IF (t_done=1) THEN
			LEAVE get_goId;
		END IF;
		
		
	
START TRANSACTION;
	
		INSERT INTO AdminWorkQueue (adminWQTypeId,adminWQCategoryId,cciStaffUserGoId,cciStaaffUserRole,targetGoId,targetRoleType,createdDate)
		VALUES (1,1,uId,'System Administrator',@pId,'partner',CURRENT_TIMESTAMP);
		
		END LOOP get_goId;
		CLOSE goId;
		
COMMIT;
-- =======================================================================		
		 
    END$$

DELIMITER ;
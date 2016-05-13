DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPHostFamilyBackgroundCheckSubmit`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPHostFamilyBackgroundCheckSubmit`(IN hfGoId INT,IN hfMemberId INT)
BEGIN
	DECLARE hfId,hfmbrId INT;
	SET @hfId = hfGoId;
	SET @hfmbrId = hfMemberId;
	
	SELECT 		hfm.hostFamilyMemberId,
			hfb.hostFamilyGoId,
			hfm.firstName,
			hfm.lastName,
			hfm.birthDate,
			hfm.memberPhone
	FROM 		`HostFamilyBackground`	hfb
	INNER JOIN 	`HostFamilyMember` hfm ON hfb.hostFamilyMemberId = hfm.hostFamilyMemberId
	WHERE 		hfb.hostFamilyMemberId = @hfmbrId AND hfb.hostFamilyGoId = @hfId;
	
	
	
    END$$

DELIMITER ;
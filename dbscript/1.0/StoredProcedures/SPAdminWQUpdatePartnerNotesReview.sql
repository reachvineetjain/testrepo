DELIMITER $$

USE `cci_gh_go_login`$$

DROP PROCEDURE IF EXISTS `SPAdminWQUpdatePartnerNotesReview`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPAdminWQUpdatePartnerNotesReview`(IN pNotesId INT, IN pGoId INT, IN cciGoId INT)
BEGIN
         DECLARE notesId INT;
         DECLARE goId INT;
         DECLARE adminId INT;
         SET @notesId = pNotesId;
         SET @goId = pGoId;
         SET @adminId = cciGoId; 
         START TRANSACTION ;
                     
			     UPDATE PartnerNotes 
			     SET hasRead=1 
			     WHERE partnerGoId = @goId 
			     AND partnerNotesId = @notesId;     
			     DELETE FROM `AdminWorkQueue`
			     WHERE `adminWQTypeId` = 2
			     AND `adminWQCategoryId` = 6
			     AND `genericId` = @notesId
			     AND `cciStaffUserGoId` = @adminId
			     AND `targetGoId` = @goId;  
	 COMMIT;
END$$

DELIMITER ;
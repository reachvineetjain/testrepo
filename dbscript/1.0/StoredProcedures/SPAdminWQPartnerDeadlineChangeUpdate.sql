DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPAdminWQPartnerDeadlineChangeUpdate`$$

CREATE DEFINER=`cbishoi`@`%` PROCEDURE `SPAdminWQPartnerDeadlineChangeUpdate`( IN typeId INT, IN categoryId INT, IN cciUserId INT, IN partnerGoId INT, IN seasonId 																		INT, IN depPgmId INT, IN depPgmOptionId INT,IN flag TINYINT(1), IN deadlineDate 																	    DATETIME)
BEGIN
		DECLARE tId,cId,uId,pId,sId,pgmId,pOptionId INT;
		DECLARE fId TINYINT(1);
		DECLARE dd DATETIME;
		
		SET @tId = typeId;
		SET @cId = categoryId;
		SET @uId = (SELECT loginId FROM Login WHERE goId = cciUserId);
		SET @pId = partnerGoId;
		SET @sId = seasonId;
		SET @pgmId = depPgmId;
		SET @pOptionId = depPgmOptionId;
		SET @fId = flag;
		SET @dd = deadlineDate;
		
		
			
		IF (@fId = 0)	THEN 	-- Reject 
			BEGIN
				DECLARE EXIT HANDLER FOR SQLEXCEPTION
				
				BEGIN
				    ROLLBACK;	-- ERROR
				END;
				DECLARE EXIT HANDLER FOR SQLWARNING
				
				BEGIN
				    ROLLBACK;   -- WARNING
				END;
				
				IF (@pOptionId = 1)	THEN
					BEGIN
						START TRANSACTION;
							UPDATE PartnerSeason
						    SET partnerDeadlineRequestStatusId = 2,
						    deadlineRequestReviewedBy = @uId,
						    deadlineRequestreviewdOn = CURRENT_TIMESTAMP,
						    partnerSeasonExtAppdeadlineDate = NULL
						    WHERE partnerGoId = @pId
						    AND seasonId = @sId
						    AND departmentProgramId = @pgmId;
						    
						    DELETE FROM `AdminWorkQueue` 
						    WHERE adminWQTypeId = @tId
						    AND adminWorkQueueCategoryId = @cId
						    AND cciStaffUserGoId = @cId
						    AND targetGoId = @pId
						    AND seasonId = @sId
						    AND departmentProgramId = @pgmId
						    AND departmentProgramOptionId = @pOptionId;
						COMMIT;
					END; 
				END IF;
			    IF (@pOptionId = 3)	THEN
					BEGIN
						
						START TRANSACTION;
							UPDATE PartnerSeason
						    SET partnerDeadlineRequestStatusId = 2,
						    deadlineRequestReviewedBy = @uId,
						    deadlineRequestreviewdOn = CURRENT_TIMESTAMP,
						    partnerSeasonExtSecSemDeadlineDate = NULL
						    WHERE partnerGoId = @pId
						    AND seasonId = @sId
						    AND departmentProgramId = @pgmId;
						    
						    DELETE FROM `AdminWorkQueue` 
						    WHERE adminWQTypeId = @tId
						    AND adminWorkQueueCategoryId = @cId
						    AND cciStaffUserGoId = @cId
						    AND targetGoId = @pId
						    AND seasonId = @sId
						    AND departmentProgramId = @pgmId
						    AND departmentProgramOptionId = @pOptionId;
						COMMIT;	
					END; 
				END IF;    
			END;
		
		END IF;	
 
		IF (@fId = 1) THEN    -- Accept
		
			BEGIN
				DECLARE EXIT HANDLER FOR SQLEXCEPTION
				
				BEGIN
				    ROLLBACK;	-- ERROR
				END;
				DECLARE EXIT HANDLER FOR SQLWARNING
				
				BEGIN
				    ROLLBACK;   -- WARNING
				END;
				
				IF (@pOptionId = 1)	THEN
					BEGIN
						START TRANSACTION;
							UPDATE PartnerSeason
						    SET partnerSeasonAppDeadlineDate = @dd,
						    partnerDeadlineRequestStatusId = 5,
						    deadlineRequestReviewedBy = @uId,
						    deadlineRequestreviewdOn = CURRENT_TIMESTAMP,
						    partnerSeasonExtAppdeadlineDate = NULL
						     WHERE partnerGoId = @pId
						    AND seasonId = @sId
						    AND departmentProgramId = @pgmId;
						    
						    DELETE FROM `AdminWorkQueue` 
						    WHERE adminWQTypeId = @tId
						    AND adminWorkQueueCategoryId = @cId
						    AND cciStaffUserGoId = @cId
						    AND targetGoId = @pId
						    AND seasonId = @sId
						    AND departmentProgramId = @pgmId
						    AND departmentProgramOptionId = @pOptionId;
						COMMIT;	
					END; 
				END IF;
			    IF (@pOptionId = 3)	THEN
				   
					BEGIN
						
						START TRANSACTION;
						
							UPDATE PartnerSeason
						    SET partnerSeasonSecSemDeadlineDate = @dd,
						    partnerDeadlineRequestStatusId = 5,
						    deadlineRequestReviewedBy = @uId,
						    deadlineRequestreviewdOn = CURRENT_TIMESTAMP,
						    partnerSeasonExtSecSemDeadlineDate = NULL
						    WHERE partnerGoId = @pId
						    AND seasonId = @sId
						    AND departmentProgramId = @pgmId;
						    
						    DELETE FROM `AdminWorkQueue` 
						    WHERE adminWQTypeId = @tId
						    AND adminWorkQueueCategoryId = @cId
						    AND cciStaffUserGoId = @cId
						    AND targetGoId = @pId
						    AND seasonId = @sId
						    AND departmentProgramId = @pgmId
						    AND departmentProgramOptionId = @pOptionId;
						
						COMMIT;
					END; 
				END IF;    
			END;
		
		END IF;	
 
		 
    END$$

DELIMITER ;
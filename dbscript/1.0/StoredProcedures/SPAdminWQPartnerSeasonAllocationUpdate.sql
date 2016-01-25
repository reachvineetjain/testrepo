DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPAdminWQPartnerSeasonAllocationUpdate`$$

CREATE DEFINER=`cbishoi`@`%` PROCEDURE `SPAdminWQPartnerSeasonAllocationUpdate`( IN typeId INT, IN categoryId INT, IN cciUserId INT, IN partnerGoId INT, IN seasonId INT, IN depPgmId INT, IN depPgmOptionId INT,IN flag TINYINT(1))
BEGIN
		DECLARE tId,cId,uId,pId,sId,pgmId,pOptionId INT;
		DECLARE fId TINYINT(1);
		DECLARE psId INT;
		
		
		SET @tId = typeId;
		SET @cId = categoryId;
		SET @uId = (SELECT loginId FROM Login WHERE goId = cciUserId);
		SET @pId = partnerGoId;
		SET @sId = seasonId;
		SET @pgmId = depPgmId;
		SET @pOptionId = depPgmOptionId;
		SET @fId = flag;
		SET @psId = (SELECT partnerSeasonId FROM PartnerSeason WHERE partnerGoId = @pId AND seasonId = @sId AND departmentProgramId = @pgmId);
		
		
		
			
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
						    UPDATE PartnerSeasonAllocation
						    SET allocationRequestStatusId = 2,
						    allocationRequestReviewedBy = @uId,
						    allocationRequestReviewedOn = CURRENT_TIMESTAMP,
						    requestedMaxPax = NULL,
						    requestedMaxGuaranteedPax = NULL
						    WHERE partnerSeasonId = @psId;
						    
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
							UPDATE PartnerSeasonAllocation
						    SET allocationRequestStatusId = 2,
						    allocationRequestReviewedBy = @uId,
						    allocationRequestReviewedOn = CURRENT_TIMESTAMP,
						    requestedMaxPax = NULL,
						    requestedMaxGuaranteedPax = NULL
						    WHERE partnerSeasonId = @psId;
						    
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
						    UPDATE PartnerSeasonAllocation
						    SET maxPax = requestedMaxPax,
						    maxGuaranteedPax = requestedMaxGuaranteedPax,
						    allocationRequestStatusId = 5,
						    allocationRequestReviewedBy = @uId,
						    allocationRequestReviewedOn = CURRENT_TIMESTAMP
						    WHERE partnerSeasonId = @psId;
						    
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
						    SET maxPax = requestedMaxPax,
						    maxGuaranteedPax = requestedMaxGuaranteedPax,
						    allocationRequestStatusId = 5,
						    allocationRequestReviewedBy = @uId,
						    allocationRequestReviewedOn = CURRENT_TIMESTAMP
						    WHERE partnerSeasonId = @psId;
						    
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
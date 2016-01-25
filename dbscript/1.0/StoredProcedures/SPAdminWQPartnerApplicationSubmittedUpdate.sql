DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPAdminWQPartnerApplicationSubmittedUpdate`$$

CREATE DEFINER=`phanipothula`@`%` PROCEDURE `SPAdminWQPartnerApplicationSubmittedUpdate`( IN typeId INT, IN categoryId INT, IN cciUserId INT, IN partnerGoId INT, IN statusId INT, IN confMsg TEXT)
BEGIN
		DECLARE tId,cId,uId,pId,sId INT;
		DECLARE msg TEXT;
		
		SET @tId = typeId;
		SET @cId = categoryId;
		SET @uId = (SELECT loginId FROM Login WHERE goId = cciUserId);
		SET @pId = partnerGoId;
		SET @sId = statusId;
		SET @fId = flag;
		SET @msg = confMsg;
		
		IF (@sId = 11)  THEN  -- Valid
			BEGIN
				DECLARE EXIT HANDLER FOR SQLEXCEPTION
				
				BEGIN
				    ROLLBACK;	-- ERROR
				END;
				DECLARE EXIT HANDLER FOR SQLWARNING
				
				BEGIN
				    ROLLBACK;   -- WARNING
				END;
				
				BEGIN
					START TRANSACTION;
						
						UPDATE `PartnerReviewStatus`
						SET partnerLeadStatus =@sId,
							partnerStatusReason = @msg,
							partnerAgentStatusId = 4,
							cciStaffUserId = @uId  
						WHERE partnerGoId = @pId;
						
						DELETE FROM `AdminWorkQueue` 
						WHERE adminWQTypeId = @tId
						AND adminWorkQueueCategoryId = @cId
						AND targetGoId = @pId;
					COMMIT;
				END; 
			END;
		END IF;
		
		IF (@sId IN (3,12))  THEN  -- Blacklisted, Invalid
			BEGIN
				DECLARE EXIT HANDLER FOR SQLEXCEPTION
				
				BEGIN
				    ROLLBACK;	-- ERROR
				END;
				DECLARE EXIT HANDLER FOR SQLWARNING
				
				BEGIN
				    ROLLBACK;   -- WARNING
				END;
				
				BEGIN
					START TRANSACTION;
						
						UPDATE `PartnerReviewStatus`
						SET partnerLeadStatus =@sId,
							partnerStatusReason = @msg,
							partnerAgentStatusId = NULL,
							cciStaffUserId = @uId  
						WHERE partnerGoId = @pId;
						
						DELETE FROM `AdminWorkQueue` 
						WHERE adminWQTypeId = @tId
						AND adminWorkQueueCategoryId = @cId
						AND targetGoId = @pId;
					COMMIT;
				END; 
			END;
		END IF;
		
		IF (@sId = 10)  THEN   -- Junk 
			
			BEGIN
				DECLARE EXIT HANDLER FOR SQLEXCEPTION
				
				BEGIN
				    ROLLBACK;	-- ERROR
				END;
				DECLARE EXIT HANDLER FOR SQLWARNING
				
				BEGIN
				    ROLLBACK;   -- WARNING
				END;
				
				BEGIN
					START TRANSACTION;
						
						DELETE FROM `PartnerAgentInquiries`
						WHERE partnerAgentGoId = @pId;
						DELETE FROM Partner
						WHERE partnerGoId = @pId;
						DELETE FROM PartnerReviewStatus
						WHERE partnerGoId = @pId;
						DELETE FROM `PartnerProgram`
						WHERE partnerGoId = @pId;
			
						DELETE FROM `AdminWorkQueue` 
						WHERE adminWQTypeId = @tId
						AND adminWorkQueueCategoryId = @cId
						AND targetGoId = @pId;
					COMMIT;
				END; 
			END;
		END IF;
 
		 
 END$$

DELIMITER ;
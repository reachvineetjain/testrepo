DELIMITER $$

USE `cci_gh_go_WIP`$$

DROP PROCEDURE IF EXISTS `SPSeasonClone`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SPSeasonClone`(IN ssnId INT, IN newSeasonName VARCHAR(50), IN newStartdate DATETIME, IN newEndDate DATETIME)
BEGIN
	DECLARE depId INT;
	DECLARE clonedSsnName VARCHAR(50);
	DECLARE ssId INT;
	DECLARE j1secondSemEarliestBirthDate DATETIME;
	DECLARE j1secondSemLatestBirthDate DATETIME; 
	DECLARE j1showSecondSemToNewHF TINYINT(1);
	DECLARE j1activeFullYearJanProgram TINYINT(1);
	DECLARE j1showJanFullYearToNewHF TINYINT(1);
	DECLARE j1firstSemEarliestBirthDate DATETIME;
	DECLARE j1firstSemLatestBirthDate DATETIME;
	DECLARE j1showFirstSemToNewHF TINYINT(1); 
	DECLARE j1showAugFullYearToNewHF TINYINT(1); 
	DECLARE j1showSeasonToCurrentHF TINYINT(1); 
	DECLARE j1fieldStaffHoldLength INT(3); 
	DECLARE j1hoursBeforeHoldExpirationWarning INT(3);
	DECLARE j1lcPaymentScheduleId INT;
	DECLARE j1fsAgreementId INT; 
	DECLARE j1hfReferences INT(3); 
	DECLARE j1hfInquiryDate DATE; 
	DECLARE j1welcomeFamily TINYINT(1); 
	DECLARE j1showGuranteed TINYINT(1);
	DECLARE j1showUnguaranteed TINYINT(1);
	DECLARE j1showSpecialRequestStudent TINYINT(1); 
	DECLARE j1JanMaxUnguaranteed INT;
	DECLARE j1JanMaxGuaranteed INT;
	DECLARE j1AugMaxUnguaranteed INT;
	DECLARE j1AugMaxGuaranteed INT;
	DECLARE f1secondSemEarliestBirthDate DATETIME;
	DECLARE f1secondSemLatestBirthDate DATETIME;
	DECLARE f1showSecondSemToNewHF TINYINT(1);
	DECLARE f1activeFullYearJanProgram TINYINT(1);
	DECLARE f1showJanFullyearToHF TINYINT(1);
	DECLARE f1firstSemEarliestBirthDate DATETIME; 
	DECLARE f1firstSemLatestBirthDate DATETIME; 
	DECLARE f1showFirstSemToNewHF TINYINT(1);
	DECLARE f1showAugFullYearToNewHF TINYINT(1); 
	DECLARE f1showSeasonToCurrentHF TINYINT(1); 
	DECLARE f1lcPaymentScheduleId INT;
	DECLARE f1fsAgreementId INT;
	DECLARE f1hfReferences INT(3); 
	DECLARE f1hfInquiryDate DATE;
	DECLARE f1welcomeFamily TINYINT(1); 
	DECLARE f1allowFieldStafftoStartRenewelProcess TINYINT(1);
	DECLARE f1showSpecialRequestStudent TINYINT(1); 
	DECLARE f1greenHeartMargin INT;
	DECLARE f1JanMaxGuaranteed INT;
	DECLARE f1AugMaxGuaranteed INT;
 START TRANSACTION;
	SELECT s.departmentId,s.seasonFullname INTO depId, clonedSsnName FROM Season s WHERE s.seasonId = ssnId;
	
	CASE depId
	
	WHEN (depId = 1) THEN 
	
	INSERT INTO Season(seasonName,seasonFullName,departmentId,seasonStatusId,clonedSeasonName,createdOn,createdBy,modifiedOn,modifiedBy)
	VALUES (newSeasonName,newSeasonName,depId,3,clonedSsnName,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1);
	
	SELECT seasonId INTO ssId FROM Season s WHERE s.seasonName = newSeasonName;
	
	SET j1secondSemEarliestBirthDate = (SELECT secondSemEarliestBirthDate FROM SeasonJ1Details j1 WHERE j1.seasonId = ssnId);
	SET j1secondSemLatestBirthDate = (SELECT secondSemLatestBirthDate FROM SeasonJ1Details j1 WHERE j1.seasonId = ssnId);
	SET j1showSecondSemToNewHF = (SELECT showSecondSemToNewHF FROM SeasonJ1Details j1 WHERE j1.seasonId = ssnId);
	SET j1activeFullYearJanProgram = (SELECT activeFullYearJanProgram FROM SeasonJ1Details j1 WHERE j1.seasonId = ssnId);
	SET j1showJanFullYearToNewHF = (SELECT showJanFullYearToNewHF FROM SeasonJ1Details j1 WHERE j1.seasonId = ssnId);
	SET j1firstSemEarliestBirthDate = (SELECT firstSemEarliestBirthDate FROM SeasonJ1Details j1 WHERE j1.seasonId = ssnId);
	SET j1firstSemLatestBirthDate = (SELECT firstSemLatestBirthDate FROM SeasonJ1Details j1 WHERE j1.seasonId = ssnId);
	SET j1showFirstSemToNewHF = (SELECT showFirstSemToNewHF FROM SeasonJ1Details j1 WHERE j1.seasonId = ssnId);
	SET j1showAugFullYearToNewHF = (SELECT showAugFullYearToNewHF FROM SeasonJ1Details j1 WHERE j1.seasonId = ssnId);
	SET j1showSeasonToCurrentHF = (SELECT showSeasonToCurrentHF FROM SeasonJ1Details j1 WHERE j1.seasonId = ssnId);
	SET j1fieldStaffHoldLength = (SELECT fieldStaffHoldLength FROM SeasonJ1Details j1 WHERE j1.seasonId = ssnId);
	SET j1hoursBeforeHoldExpirationWarning = (SELECT hoursBeforeHoldExpirationWarning FROM SeasonJ1Details j1 WHERE j1.seasonId = ssnId);
	SET j1lcPaymentScheduleId = (SELECT lcPaymentScheduleId FROM SeasonJ1Details j1 WHERE j1.seasonId = ssnId);
	SET j1fsAgreementId = (SELECT fsAgreementId FROM SeasonJ1Details j1 WHERE j1.seasonId = ssnId);
	SET j1hfReferences = (SELECT hfReferences FROM SeasonJ1Details j1 WHERE j1.seasonId = ssnId);
	SET j1hfInquiryDate = (SELECT hfInquiryDate FROM SeasonJ1Details j1 WHERE j1.seasonId = ssnId);
	SET j1welcomeFamily = (SELECT showWelcomeFamily FROM SeasonJ1Details j1 WHERE j1.seasonId = ssnId);
	SET j1showGuranteed = (SELECT showGuaranteed FROM SeasonJ1Details j1 WHERE j1.seasonId = ssnId);
	SET j1showUnguaranteed = (SELECT showUnguaranteed FROM SeasonJ1Details j1 WHERE j1.seasonId = ssnId);
	SET j1showSpecialRequestStudent = (SELECT showSpecialRequestStudent FROM SeasonJ1Details j1 WHERE j1.seasonId = ssnId);
	SET j1JanMaxUnguaranteed = (SELECT maxUnguaranteedPax FROM SeasonHSPAllocation WHERE seasonId = ssnId AND departmentProgramOptionId = 15);
	SET j1JanMaxGuaranteed = (SELECT maxGuaranteedPax FROM SeasonHSPAllocation WHERE seasonId = ssnId AND departmentProgramOptionId = 15);
	SET j1AugMaxUnguaranteed = (SELECT maxUnguaranteedPax FROM SeasonHSPAllocation WHERE seasonId = ssnId AND departmentProgramOptionId = 1);
	SET j1AugMaxGuaranteed = (SELECT maxGuaranteedPax FROM SeasonHSPAllocation WHERE seasonId = ssnId AND departmentProgramOptionId = 1);
	SET f1secondSemEarliestBirthDate = (SELECT secondSemEarliestBirthDate FROM SeasonF1Details f1 WHERE f1.seasonId = ssnId);
	SET f1secondSemLatestBirthDate = (SELECT secondSemLatestBirthDate FROM SeasonF1Details f1 WHERE f1.seasonId = ssnId);
	SET f1showSecondSemToNewHF = (SELECT showSecSemToNewHF FROM SeasonF1Details f1 WHERE f1.seasonId = ssnId);
	SET f1activeFullYearJanProgram = (SELECT activeFullYearJanProgram FROM SeasonF1Details f1 WHERE f1.seasonId = ssnId);
	SET f1showJanFullyearToHF = (SELECT showJanFullYearToNewHF FROM SeasonF1Details f1 WHERE f1.seasonId = ssnId);
	SET f1firstSemEarliestBirthDate = (SELECT firstSemEarliestBirthDate FROM SeasonF1Details f1 WHERE f1.seasonId = ssnId);
	SET f1firstSemLatestBirthDate = (SELECT firstSemLatestBirthDate FROM SeasonF1Details f1 WHERE f1.seasonId = ssnId);
	SET f1showFirstSemToNewHF = (SELECT showFirstSemToNewHF FROM SeasonF1Details f1 WHERE f1.seasonId = ssnId);
	SET f1showAugFullYearToNewHF = (SELECT showAugFullYearToNewHF FROM SeasonF1Details f1 WHERE f1.seasonId = ssnId);
	SET f1showSeasonToCurrentHF = (SELECT showSeasonToCurrentHF FROM SeasonF1Details f1 WHERE f1.seasonId = ssnId);
	SET f1lcPaymentScheduleId = (SELECT lcPaymentScheduleId FROM SeasonF1Details f1 WHERE f1.seasonId = ssnId);
	SET f1fsAgreementId = (SELECT fsAgreementId FROM SeasonF1Details f1 WHERE f1.seasonId = ssnId);
	SET f1hfReferences = (SELECT hfReferences FROM SeasonF1Details f1 WHERE f1.seasonId = ssnId);
	SET f1hfInquiryDate = (SELECT hfInquiryDate FROM SeasonF1Details f1 WHERE f1.seasonId = ssnId);
	SET f1welcomeFamily = (SELECT showWelcomeFamily FROM SeasonF1Details f1 WHERE f1.seasonId = ssnId);
	SET f1allowFieldStafftoStartRenewelProcess = (SELECT allowFieldStaffToStartRenewalProcess FROM SeasonF1Details f1 WHERE f1.seasonId = ssnId);
	SET f1showSpecialRequestStudent = (SELECT showSpecialRequestStudent FROM SeasonF1Details f1 WHERE f1.seasonId = ssnId);
	SET f1greenHeartMargin = (SELECT greenHeartMargin FROM SeasonF1Details f1 WHERE f1.seasonId = ssnId);
	SET f1JanMaxGuaranteed = (SELECT maxGuaranteedPax FROM SeasonHSPAllocation WHERE seasonId = ssnId AND departmentProgramOptionId = 17);
	SET f1AugMaxGuaranteed = (SELECT maxGuaranteedPax FROM SeasonHSPAllocation WHERE seasonId = ssnId AND departmentProgramOptionId = 16);
	
	
	INSERT INTO SeasonHSPConfiguration (seasonId,seasonStartDate,seasonEndDate,createdBy,modifiedBy) VALUES (ssId,newStartdate,newEndDate,1,1);	
	INSERT INTO SeasonJ1Details (seasonId,
					programName,
					programStatusId,
					secondSemStartDate, 
					secondSemEndDate, 
					secondSemAppDeadlineDate,
					secondSemEarliestBirthDate,
					secondSemLatestBirthDate, 
					showSecondSemToNewHF,
					activeFullYearJanProgram,
					janFullYearStartDate, 
					janFullYearAppDeadlineDate, 
					janFullYearEndDate,
					showJanFullYearToNewHF,
					firstSemStartDate,
					firstSemEndDate, 
					firstSemAppDeadlineDate, 
					firstSemEarliestBirthDate,
					firstSemLatestBirthDate,
					showFirstSemToNewHF, 
					augFullYearStartDate,
					augFullYearEndDate,
					augFullYearAppDeadlineDate,
					showAugFullYearToNewHF, 
					showSeasonToCurrentHF, 
					fieldStaffHoldLength, 
					hoursBeforeHoldExpirationWarning,
					lcPaymentScheduleId,
					fsAgreementId, 
					hfReferences, 
					hfInquiryDate, 
					showWelcomeFamily, 
					showGuaranteed,
					showUnguaranteed,
					showSpecialRequestStudent,
					createdBy,
					modifiedBy)
			    VALUES(ssId,
					NULL,
					3,
					NULL, 
					NULL, 
					NULL,
					NULL,
					NULL, 
					j1showSecondSemToNewHF,
					j1activeFullYearJanProgram,
					NULL, 
					NULL, 
					NULL,
					j1showJanFullYearToNewHF,
					NULL,
					NULL, 
					NULL, 
					NULL,
					NULL,
					j1showFirstSemToNewHF, 
					NULL,
					NULL,
					NULL,
					j1showAugFullYearToNewHF, 
					j1showSeasonToCurrentHF, 
					j1fieldStaffHoldLength, 
					j1hoursBeforeHoldExpirationWarning,
					j1lcPaymentScheduleId,
					j1fsAgreementId, 
					j1hfReferences, 
					j1hfInquiryDate, 
					j1welcomeFamily, 
					j1showGuranteed,
					j1showUnguaranteed,
					j1showSpecialRequestStudent,
					1,
					1); 	
	INSERT INTO SeasonF1Details (seasonId,
					programName,
					programStatusId,
					secondSemStartDate,
					secondSemEndDate,
					secondSemAppDeadlineDate,
					secondSemEarliestBirthDate,
					secondSemLatestBirthDate,
					showSecSemToNewHF,
					activeFullYearJanProgram,
					janFullYearStartDate,
					janFullyearAppDeadlineDate,
					janFullYearEndDate, 
					showJanFullYearToNewHF,
					firstSemStartDate, 
					firstSemEndDate, 
					firstSemAppDeadlineDate, 
					firstSemEarliestBirthDate, 
					firstSemLatestBirthDate, 
					showFirstSemToNewHF,
					augFullYearStartDate, 
					augFullYearEndDate, 
					augFullYearAppDeadlineDate,
					showAugFullYearToNewHF, 
					showSeasonToCurrentHF, 
					lcPaymentScheduleId,
					fsAgreementId,
					hfReferences, 
					hfInquiryDate,
					showWelcomeFamily, 
					allowFieldStaffToStartRenewalProcess,
					showSpecialRequestStudent, 
					greenHeartMargin,
					createdBy,
					modifiedBy)
			   VALUES (ssId,
					NULL,
					3,
					NULL,
					NULL,
					NULL,
					NULL,
					NULL,
					f1showSecondSemToNewHF,
					f1activeFullYearJanProgram,
					NULL,
					NULL,
					NULL, 
					f1showJanFullyearToHF,
					NULL, 
					NULL, 
					NULL, 
					NULL, 
					NULL, 
					f1showFirstSemToNewHF,
					NULL, 
					NULL, 
					NULL,
					f1showAugFullYearToNewHF, 
					f1showSeasonToCurrentHF, 
					f1lcPaymentScheduleId,
					f1fsAgreementId,
					f1hfReferences, 
					f1hfInquiryDate,
					f1welcomeFamily, 
					f1allowFieldStafftoStartRenewelProcess,
					f1showSpecialRequestStudent, 
					f1greenHeartMargin,
					1,
					1);
	
	
	/* In the below Insert and Update queries departmentProgramOptionId is hard coded
	  1= AugFY for J1, 15 = JanFY for J1, 16 = AugFY for F1, 17 = JanFY for F1. 
	  These values changes as DepartmentProgramOptions table changes
	*/
	INSERT INTO SeasonHSPAllocation(seasonId,departmentProgramOptionId,createdBy,modifiedBy) VALUES (ssId,15,1,1);
	INSERT INTO SeasonHSPAllocation(seasonId,departmentProgramOptionId,createdBy,modifiedBy) VALUES (ssId,1,1,1);
	INSERT INTO SeasonHSPAllocation(seasonId,departmentProgramOptionId,createdBy,modifiedBy) VALUES (ssId,16,1,1);
	INSERT INTO SeasonHSPAllocation(seasonId,departmentProgramOptionId,createdBy,modifiedBy) VALUES (ssId,17,1,1);
	UPDATE SeasonHSPAllocation SET maxGuaranteedPax = j1JanMaxGuaranteed, maxUnguaranteedPax = j1JanMaxUnguaranteed WHERE departmentProgramOptionId = 15 AND seasonId = ssId;
	UPDATE SeasonHSPAllocation SET maxGuaranteedPax = j1AugMaxGuaranteed, maxUnguaranteedPax = j1AugMaxUnGuaranteed WHERE departmentProgramOptionId = 1 AND seasonId = ssId;
	UPDATE SeasonHSPAllocation SET maxGuaranteedPax = f1JanMaxGuaranteed WHERE departmentProgramOptionId = 17 AND seasonId = ssId;
	UPDATE SeasonHSPAllocation SET maxGuaranteedPax = f1AugMaxGuaranteed WHERE departmentProgramOptionId = 16 AND seasonId = ssId;
	
	SELECT ssId;	
				   
	END CASE;
   
    COMMIT;				   
	
    END$$

DELIMITER ;
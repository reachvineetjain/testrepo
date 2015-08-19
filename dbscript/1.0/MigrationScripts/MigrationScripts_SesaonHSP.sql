
-- USE cci_gh_go_qa;

SET FOREIGN_KEY_CHECKS= 0;

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Truncating Season Module tables.
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/

TRUNCATE `SeasonHSPConfiguration` ;
TRUNCATE `SeasonF1Details` ;
TRUNCATE `SeasonJ1Details` ;
TRUNCATE `SeasonHSPAllocation` ;
TRUNCATE `SeasonGeographyConfiguration` ;
TRUNCATE `SeasonIHPDetails` ;
TRUNCATE `SeasonIHPDetailsRegionApplications`;
TRUNCATE `SeasonIHPGeographyConfiguration`;


/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Season Migration
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
/*  Remove NOT NULL for seasonStatusId column in Season table */

INSERT INTO Season (seasonId,seasonName,seasonFullName,departmentId,createdBy,modifiedBy) 
VALUES  
(1,'HSP AYP 2009-2010','HSP AYP 2009-2010',1,1,1),
(2,'HSP AYP 2010-2011','HSP AYP 2010-2011',1,1,1),
(3,'HSP AYP 2011-2012','HSP AYP 2011-2012',1,1,1),
(4,'HSP AYP 2012-2013','HSP AYP 2012-2013',1,1,1),
(5,'HSP AYP 2013-2014','HSP AYP 2013-2014',1,1,1),
(6,'HSP AYP 2014-2015','HSP AYP 2014-2015',1,1,1),
(7,'HSP AYP 2015-2016','HSP AYP 2015-2016',1,1,1);
		

UPDATE `Season`
SET `seasonStatusId` = 2 WHERE seasonId= 1;
UPDATE `Season`
SET `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`season` WHERE `SeasonID` IN(39,49) ORDER BY `SeasonStatusID` ASC LIMIT 1)
WHERE seasonId= 2;
UPDATE `Season`
SET `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`season` WHERE `SeasonID` IN(48,60) ORDER BY `SeasonStatusID` ASC LIMIT 1)
WHERE seasonId= 3;
UPDATE `Season`
SET `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`season` WHERE `SeasonID` IN(67,69) ORDER BY `SeasonStatusID` ASC LIMIT 1)
WHERE seasonId= 4;
UPDATE `Season`
SET `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`season` WHERE `SeasonID` IN(74,75) ORDER BY `SeasonStatusID` ASC LIMIT 1)
WHERE seasonId= 5;
UPDATE `Season`
SET `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`season` WHERE `SeasonID` IN(88,89) ORDER BY `SeasonStatusID` ASC LIMIT 1)
WHERE seasonId= 6;		
UPDATE `Season`
SET `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`season` WHERE `SeasonID` IN(92,94) ORDER BY `SeasonStatusID` ASC LIMIT 1)
WHERE seasonId= 7;


/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          SeasonHSPConfiguration Migration
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
/*  Remove NOT NULL for seasonStartDate, seasonEndDate column in Season table */

INSERT INTO SeasonHSPConfiguration (seasonId,createdBy,modifiedBy) 
VALUES
(1,1,1),
(2,1,1),
(3,1,1),
(4,1,1),
(5,1,1),
(6,1,1),
(7,1,1);
		
UPDATE SeasonHSPConfiguration 
SET seasonStartDate = (SELECT StartDate FROM `cci_go`.`season` WHERE SeasonID IN (37)),
	seasonEndDate = (SELECT EndDate FROM `cci_go`.`season` WHERE SeasonID IN (37))
WHERE seasonId = 1;
		
UPDATE SeasonHSPConfiguration 
SET seasonStartDate = (SELECT MIN(StartDate) FROM `cci_go`.`season` WHERE SeasonID IN (39,49)),
	seasonEndDate = (SELECT MAX(EndDate) FROM `cci_go`.`season` WHERE SeasonID IN (39,49))
WHERE seasonId = 2;

UPDATE SeasonHSPConfiguration 
SET seasonStartDate = (SELECT MIN(StartDate) FROM `cci_go`.`season` WHERE SeasonID IN (48,60)),
	seasonEndDate = (SELECT MAX(EndDate) FROM `cci_go`.`season` WHERE SeasonID IN (48,60))
WHERE seasonId = 3;

UPDATE SeasonHSPConfiguration 
SET seasonStartDate = (SELECT MIN(StartDate) FROM `cci_go`.`season` WHERE SeasonID IN (67,69)),
	seasonEndDate = (SELECT MAX(EndDate) FROM `cci_go`.`season` WHERE SeasonID IN (67,69))
WHERE seasonId = 4;

UPDATE SeasonHSPConfiguration 
SET seasonStartDate = (SELECT MIN(StartDate) FROM `cci_go`.`season` WHERE SeasonID IN (74,75)),
	seasonEndDate = (SELECT MAX(EndDate) FROM `cci_go`.`season` WHERE SeasonID IN (74,75))
WHERE seasonId = 5;

UPDATE SeasonHSPConfiguration 
SET seasonStartDate = (SELECT MIN(StartDate) FROM `cci_go`.`season` WHERE SeasonID IN (88,89)),
	seasonEndDate = (SELECT MAX(EndDate) FROM `cci_go`.`season` WHERE SeasonID IN (88,89))
WHERE seasonId = 6;

UPDATE SeasonHSPConfiguration 
SET seasonStartDate = (SELECT MIN(StartDate) FROM `cci_go`.`season` WHERE SeasonID IN (92,94)),
	seasonEndDate = (SELECT MAX(EndDate) FROM `cci_go`.`season` WHERE SeasonID IN (92,94))
WHERE seasonId = 7;

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
        SeasonF1Details Migration
	-- `allowFieldStaffToStartRenewalProcess`,`showSpecialRequstStudent`,`greenHeartMargin`,`hfInquiryDate`,`activeFullYearJanProgram`,
	   `janFullYearStartDate`,`janFullYearAppDeadlineDate`,`janFullYearEndDate`, `showJanFullYearToHF` column values would be NULL in SeasonF1Details table
		  
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
-- Remove NOT NULL for seasonId in SeasonF1Details
-- Make sure data is populated in PaymentSchedule and FieldStaffAgreement tables.
INSERT INTO `SeasonF1Details`(`seasonF1DetailsId`,`seasonId`,`programName`,`programStatusId`,`secondSemStartDate`,`secondSemEndDate`,`secondSemAppDeadlineDate`,`secondSemEarliestBirthDate`,`secondSemLatestBirthDate`,`showSecSemToNewHF`,`activeFullYearJanProgram`,`janFullYearStartDate`,`janFullYearAppDeadlineDate`,`janFullYearEndDate`,`showJanFullYearToNewHF`,`firstSemStartDate`,`firstSemEndDate`,`firstSemAppDeadlineDate`,`firstSemEarliestBirthDate`,`firstSemLatestBirthDate`,`showFirstSemToNewHF`,`augFullYearStartDate`,`augFullYearEndDate`,`augFullYearAppDeadlineDate`,`showAugFullYearToNewHF`,`showSeasonToCurrentHF`,`lcPaymentScheduleId`,`fsAgreementId`,`hfReferences`,`hfInquiryDate`,`showWelcomeFamily`,`allowFieldStaffToStartRenewalProcess`,`showSpecialRequestStudent`,`greenHeartMargin`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`) 
VALUES 
(37,1,'AYP-PSPP 2009-10',2,'9999-09-09 00:00:00','9999-09-09 00:00:00','9999-09-09 00:00:00','9999-09-09 00:00:00','9999-09-09 00:00:00',0,0,'9999-09-09 00:00:00','9999-09-09 00:00:00','9999-09-09 00:00:00',0,'9999-09-09 00:00:00','9999-09-09 00:00:00','9999-09-09 00:00:00','9999-09-09 00:00:00','9999-09-09 00:00:00',0,'9999-09-09 00:00:00','9999-09-09 00:00:00','9999-09-09 00:00:00',0,0,1,1,0,'9999-09-09',0,0,0,0,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1);



INSERT INTO `SeasonF1Details` (`seasonF1DetailsId`,`programName`,`programStatusId`,`secondSemStartDate`,`secondSemEndDate`,
                                           `secondSemAppDeadlineDate`,`secondSemEarliestBirthDate`,`secondSemLatestBirthDate`,`showSecSemToNewHF`,
                                           `firstSemStartDate`,`firstSemEndDate`,`firstSemAppDeadlineDate`,
                                           `firstSemEarliestBirthDate`,`firstSemLatestBirthDate`,`showFirstSemToNewHF`,`augFullYearStartDate`,
                                           `augFullYearEndDate`,`augFullYearAppDeadlineDate`,`showAugFullYearToNewHF`,`showSeasonToCurrentHF`,
                                           `lcPaymentScheduleId`,`fsAgreementId`,`hfReferences`,
                                           `showWelcomeFamily`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`SeasonStatusID`,`SecondStartDate`,`EndDate`,
                                           `SecondDeadlineDate`,`SecondEarliestBirthDate`,`SecondLatestBirthDate`,`HFCanHostSecondSemester`,
                                            `StartDate`,`SecondEndDate`,`ApplicationDeadlineDate`,
                                            `EarliestBirthDate`,`LatestBirthDate`,`HFCanHostFirstSemester`,`StartDate`,
                                            `EndDate`,`ApplicationDeadlineDate`,`HFCanHostFirstSemester`,`HFCanReturningView`,
                                            `DefaultPaymentScheduleID`,`FieldStaffAgreementID`,`HFRequiredReferences`,
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`season` WHERE `SeasonID`= 49;


UPDATE `SeasonF1Details` 
SET `seasonId` = 2,activeFullYearJanProgram = 0,janFullYearStartDate ='9999-09-09 00:00:00' ,
janFullYearAppDeadlineDate ='9999-09-09 00:00:00' ,janFullYearEndDate ='9999-09-09 00:00:00',showJanFullYearToNewHF=0,
hfInquiryDate='9999-09-09',allowFieldStaffToStartRenewalProcess=0,showSpecialRequestStudent=0,greenHeartMargin=0	
WHERE `seasonF1DetailsId`=49;


INSERT INTO `SeasonF1Details` (`seasonF1DetailsId`,`programName`,`programStatusId`,`secondSemStartDate`,`secondSemEndDate`,
                                           `secondSemAppDeadlineDate`,`secondSemEarliestBirthDate`,`secondSemLatestBirthDate`,`showSecSemToNewHF`,
                                           `firstSemStartDate`,`firstSemEndDate`,`firstSemAppDeadlineDate`,
                                           `firstSemEarliestBirthDate`,`firstSemLatestBirthDate`,`showFirstSemToNewHF`,`augFullYearStartDate`,
                                           `augFullYearEndDate`,`augFullYearAppDeadlineDate`,`showAugFullYearToNewHF`,`showSeasonToCurrentHF`,
                                           `lcPaymentScheduleId`,`fsAgreementId`,`hfReferences`,
                                           `showWelcomeFamily`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`SeasonStatusID`,`SecondStartDate`,`EndDate`,
                                           `SecondDeadlineDate`,`SecondEarliestBirthDate`,`SecondLatestBirthDate`,`HFCanHostSecondSemester`,
                                            `StartDate`,`SecondEndDate`,`ApplicationDeadlineDate`,
                                            `EarliestBirthDate`,`LatestBirthDate`,`HFCanHostFirstSemester`,`StartDate`,
                                            `EndDate`,`ApplicationDeadlineDate`,`HFCanHostFirstSemester`,`HFCanReturningView`,
                                            `DefaultPaymentScheduleID`,`FieldStaffAgreementID`,`HFRequiredReferences`,
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`season` WHERE `SeasonID`= 60;



UPDATE `SeasonF1Details` 
SET `seasonId` = 3,activeFullYearJanProgram = 0,janFullYearStartDate ='9999-09-09 00:00:00' ,
janFullYearAppDeadlineDate ='9999-09-09 00:00:00' ,janFullYearEndDate ='9999-09-09 00:00:00',showJanFullYearToNewHF=0,
hfInquiryDate='9999-09-09',allowFieldStaffToStartRenewalProcess=0,showSpecialRequestStudent=0,greenHeartMargin=0
WHERE `seasonF1DetailsId`=60;

INSERT INTO `SeasonF1Details` (`seasonF1DetailsId`,`programName`,`programStatusId`,`secondSemStartDate`,`secondSemEndDate`,
                                           `secondSemAppDeadlineDate`,`secondSemEarliestBirthDate`,`secondSemLatestBirthDate`,`showSecSemToNewHF`,
                                           `firstSemStartDate`,`firstSemEndDate`,`firstSemAppDeadlineDate`,
                                           `firstSemEarliestBirthDate`,`firstSemLatestBirthDate`,`showFirstSemToNewHF`,`augFullYearStartDate`,
                                           `augFullYearEndDate`,`augFullYearAppDeadlineDate`,`showAugFullYearToNewHF`,`showSeasonToCurrentHF`,
                                           `lcPaymentScheduleId`,`fsAgreementId`,`hfReferences`,
                                           `showWelcomeFamily`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`SeasonStatusID`,`SecondStartDate`,`EndDate`,
                                           `SecondDeadlineDate`,`SecondEarliestBirthDate`,`SecondLatestBirthDate`,`HFCanHostSecondSemester`,
                                            `StartDate`,`SecondEndDate`,`ApplicationDeadlineDate`,
                                            `EarliestBirthDate`,`LatestBirthDate`,`HFCanHostFirstSemester`,`StartDate`,
                                            `EndDate`,`ApplicationDeadlineDate`,`HFCanHostFirstSemester`,`HFCanReturningView`,
                                            `DefaultPaymentScheduleID`,`FieldStaffAgreementID`,`HFRequiredReferences`,
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`season` WHERE `SeasonID`= 69;




UPDATE `SeasonF1Details` 
SET `seasonId` = 4,activeFullYearJanProgram = 0,janFullYearStartDate ='9999-09-09 00:00:00' ,
janFullYearAppDeadlineDate ='9999-09-09 00:00:00' ,janFullYearEndDate ='9999-09-09 00:00:00',showJanFullYearToNewHF=0,
hfInquiryDate='9999-09-09',allowFieldStaffToStartRenewalProcess=0,showSpecialRequestStudent=0,greenHeartMargin=0
WHERE `seasonF1DetailsId`=69;


INSERT INTO `SeasonF1Details` (`seasonF1DetailsId`,`programName`,`programStatusId`,`secondSemStartDate`,`secondSemEndDate`,
                                           `secondSemAppDeadlineDate`,`secondSemEarliestBirthDate`,`secondSemLatestBirthDate`,`showSecSemToNewHF`,
                                           `firstSemStartDate`,`firstSemEndDate`,`firstSemAppDeadlineDate`,
                                           `firstSemEarliestBirthDate`,`firstSemLatestBirthDate`,`showFirstSemToNewHF`,`augFullYearStartDate`,
                                           `augFullYearEndDate`,`augFullYearAppDeadlineDate`,`showAugFullYearToNewHF`,`showSeasonToCurrentHF`,
                                           `lcPaymentScheduleId`,`fsAgreementId`,`hfReferences`,
                                           `showWelcomeFamily`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`SeasonStatusID`,`SecondStartDate`,`EndDate`,
                                           `SecondDeadlineDate`,`SecondEarliestBirthDate`,`SecondLatestBirthDate`,`HFCanHostSecondSemester`,
                                            `StartDate`,`SecondEndDate`,`ApplicationDeadlineDate`,
                                            `EarliestBirthDate`,`LatestBirthDate`,`HFCanHostFirstSemester`,`StartDate`,
                                            `EndDate`,`ApplicationDeadlineDate`,`HFCanHostFirstSemester`,`HFCanReturningView`,
                                            `DefaultPaymentScheduleID`,`FieldStaffAgreementID`,`HFRequiredReferences`,
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`season` WHERE `SeasonID`= 75;



UPDATE `SeasonF1Details` 
SET `seasonId` = 5,activeFullYearJanProgram = 0,janFullYearStartDate ='9999-09-09 00:00:00' ,
janFullYearAppDeadlineDate ='9999-09-09 00:00:00' ,janFullYearEndDate ='9999-09-09 00:00:00',showJanFullYearToNewHF=0,
hfInquiryDate='9999-09-09',allowFieldStaffToStartRenewalProcess=0,showSpecialRequestStudent=0,greenHeartMargin=0
WHERE `seasonF1DetailsId`=75;


INSERT INTO `SeasonF1Details` (`seasonF1DetailsId`,`programName`,`programStatusId`,`secondSemStartDate`,`secondSemEndDate`,
                                           `secondSemAppDeadlineDate`,`secondSemEarliestBirthDate`,`secondSemLatestBirthDate`,`showSecSemToNewHF`,
                                           `firstSemStartDate`,`firstSemEndDate`,`firstSemAppDeadlineDate`,
                                           `firstSemEarliestBirthDate`,`firstSemLatestBirthDate`,`showFirstSemToNewHF`,`augFullYearStartDate`,
                                           `augFullYearEndDate`,`augFullYearAppDeadlineDate`,`showAugFullYearToNewHF`,`showSeasonToCurrentHF`,
                                           `lcPaymentScheduleId`,`fsAgreementId`,`hfReferences`,
                                           `showWelcomeFamily`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
 SELECT `SeasonID`,`SeasonFullName`,`SeasonStatusID`,`SecondStartDate`,`EndDate`,
                                           `SecondDeadlineDate`,`SecondEarliestBirthDate`,`SecondLatestBirthDate`,`HFCanHostSecondSemester`,
                                            `StartDate`,`SecondEndDate`,`ApplicationDeadlineDate`,
                                            `EarliestBirthDate`,`LatestBirthDate`,`HFCanHostFirstSemester`,`StartDate`,
                                            `EndDate`,`ApplicationDeadlineDate`,`HFCanHostFirstSemester`,`HFCanReturningView`,
                                            `DefaultPaymentScheduleID`,`FieldStaffAgreementID`,`HFRequiredReferences`,
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`season` WHERE `SeasonID`= 89;



UPDATE `SeasonF1Details` 
SET `seasonId` = 6,activeFullYearJanProgram = 0,janFullYearStartDate ='9999-09-09 00:00:00' ,
janFullYearAppDeadlineDate ='9999-09-09 00:00:00' ,janFullYearEndDate ='9999-09-09 00:00:00',showJanFullYearToNewHF=0,
hfInquiryDate='9999-09-09',allowFieldStaffToStartRenewalProcess=0,showSpecialRequestStudent=0,greenHeartMargin=0
WHERE `seasonF1DetailsId`=89;


INSERT INTO `SeasonF1Details` (`seasonF1DetailsId`,`programName`,`programStatusId`,`secondSemStartDate`,`secondSemEndDate`,
                                           `secondSemAppDeadlineDate`,`secondSemEarliestBirthDate`,`secondSemLatestBirthDate`,`showSecSemToNewHF`,
                                           `firstSemStartDate`,`firstSemEndDate`,`firstSemAppDeadlineDate`,
                                           `firstSemEarliestBirthDate`,`firstSemLatestBirthDate`,`showFirstSemToNewHF`,`augFullYearStartDate`,
                                           `augFullYearEndDate`,`augFullYearAppDeadlineDate`,`showAugFullYearToNewHF`,`showSeasonToCurrentHF`,
                                           `lcPaymentScheduleId`,`fsAgreementId`,`hfReferences`,
                                           `showWelcomeFamily`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`SeasonStatusID`,`SecondStartDate`,`EndDate`,
                                           `SecondDeadlineDate`,`SecondEarliestBirthDate`,`SecondLatestBirthDate`,`HFCanHostSecondSemester`,
                                            `StartDate`,`SecondEndDate`,`ApplicationDeadlineDate`,
                                            `EarliestBirthDate`,`LatestBirthDate`,`HFCanHostFirstSemester`,`StartDate`,
                                            `EndDate`,`ApplicationDeadlineDate`,`HFCanHostFirstSemester`,`HFCanReturningView`,
                                            `DefaultPaymentScheduleID`,`FieldStaffAgreementID`,`HFRequiredReferences`,
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`season` WHERE `SeasonID`= 94;
 


UPDATE `SeasonF1Details` 
SET `seasonId` = 7,activeFullYearJanProgram = 0,janFullYearStartDate ='9999-09-09 00:00:00' ,
janFullYearAppDeadlineDate ='9999-09-09 00:00:00' ,janFullYearEndDate ='9999-09-09 00:00:00',showJanFullYearToNewHF=0,
hfInquiryDate='9999-09-09',allowFieldStaffToStartRenewalProcess=0,showSpecialRequestStudent=0,greenHeartMargin=0
WHERE `seasonF1DetailsId`=94;


UPDATE `SeasonF1Details`
SET fsAgreementId = 8
WHERE fsAgreementId = 0;

UPDATE `SeasonF1Details`
SET lcPaymentScheduleId = 32
WHERE lcPaymentScheduleId = 0;



/*------------------------------------------------------------------------------------------------------------------------------------------------------------
       SeasonJ1Details Migration
	-- activeFullYearJanProgram, janFullYearStartDate, janFullYearAppDeadlineDate,  janFullYearEndDate, showJanFullYearToNewHF, hfInquiryDate, 		      	  showSpecialRequstStudent column values would be NULL in SeasonJ1Details Table	  
	--showGuranateed,showUnguaranteed column values in SeasonJ1Details table has been hard-coded based on FieldStaffStudentView column value in Adage Season  table
------------------------------------------------------------------------------------------------------------------------------------------------------------*/  --  Remove NOT NULL for seasonId in SeasonJ1Details                               
                 
INSERT INTO `SeasonJ1Details` (`seasonJ1DetailsId`,`programName`,`programStatusId`,`secondSemStartDate`,`secondSemEndDate`,
                                           `secondSemAppDeadlineDate`,`secondSemEarliestBirthDate`,`secondSemLatestBirthDate`,`showSecondSemToNewHF`,
                                           `firstSemStartDate`,`firstSemEndDate`,`firstSemAppDeadlineDate`,
                                           `firstSemEarliestBirthDate`,`firstSemLatestBirthDate`,`showFirstSemToNewHF`,`augFullYearStartDate`,
                                           `augFullYearEndDate`,`augFullYearAppDeadlineDate`,`showAugFullYearToNewHF`,`showSeasonToCurrentHF`,
                                           `fieldStaffHoldLength`,`hoursBeforeHoldExpirationWarning`,`lcPaymentScheduleId`,`fsAgreementId`,`hfReferences`,
                                           `showWelcomeFamily`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`SeasonStatusID`,`SecondStartDate`,`EndDate`,
                                           `SecondDeadlineDate`,`SecondEarliestBirthDate`,`SecondLatestBirthDate`,`HFCanHostSecondSemester`,
                                            `StartDate`,`SecondEndDate`,`ApplicationDeadlineDate`,
                                            `EarliestBirthDate`,`LatestBirthDate`,`HFCanHostFirstSemester`,`StartDate`,
                                            `EndDate`,`ApplicationDeadlineDate`,`HFCanHostFirstSemester`,`HFCanReturningView`,
                                            `FieldStaffHoldLength`,`HoursBeforeHoldExpirationWarning`,`DefaultPaymentScheduleID`,`FieldStaffAgreementID`,`HFRequiredReferences`,
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`season` WHERE `SeasonID`=37;


UPDATE `SeasonJ1Details` 
SET `seasonId` = 1,
	 `showGuaranteed` = 0, 
	 `showUnguaranteed` = 0,activeFullYearJanProgram = 0,janFullYearStartDate ='9999-09-09 00:00:00' ,
	  janFullYearAppDeadlineDate ='9999-09-09 00:00:00' ,janFullYearEndDate ='9999-09-09 00:00:00',showJanFullYearToNewHF=0,
      hfInquiryDate='9999-09-09',showSpecialRequestStudent=0
WHERE `seasonJ1DetailsId`=37;


INSERT INTO `SeasonJ1Details` (`seasonJ1DetailsId`,`programName`,`programStatusId`,`secondSemStartDate`,`secondSemEndDate`,
                                           `secondSemAppDeadlineDate`,`secondSemEarliestBirthDate`,`secondSemLatestBirthDate`,`showSecondSemToNewHF`,
                                           `firstSemStartDate`,`firstSemEndDate`,`firstSemAppDeadlineDate`,
                                           `firstSemEarliestBirthDate`,`firstSemLatestBirthDate`,`showFirstSemToNewHF`,`augFullYearStartDate`,
                                           `augFullYearEndDate`,`augFullYearAppDeadlineDate`,`showAugFullYearToNewHF`,`showSeasonToCurrentHF`,
                                           `fieldStaffHoldLength`,`hoursBeforeHoldExpirationWarning`,`lcPaymentScheduleId`,`fsAgreementId`,`hfReferences`,
                                           `showWelcomeFamily`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`SeasonStatusID`,`SecondStartDate`,`EndDate`,
                                           `SecondDeadlineDate`,`SecondEarliestBirthDate`,`SecondLatestBirthDate`,`HFCanHostSecondSemester`,
                                            `StartDate`,`SecondEndDate`,`ApplicationDeadlineDate`,
                                            `EarliestBirthDate`,`LatestBirthDate`,`HFCanHostFirstSemester`,`StartDate`,
                                            `EndDate`,`ApplicationDeadlineDate`,`HFCanHostFirstSemester`,`HFCanReturningView`,
                                            `FieldStaffHoldLength`,`HoursBeforeHoldExpirationWarning`,`DefaultPaymentScheduleID`,`FieldStaffAgreementID`,`HFRequiredReferences`,
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`season` WHERE `SeasonID`=39;

	
UPDATE `SeasonJ1Details` 
SET `seasonId` = 2,
	 `showGuaranteed` = 0, 
	 `showUnguaranteed` = 0,activeFullYearJanProgram = 0,janFullYearStartDate ='9999-09-09 00:00:00' ,
	  janFullYearAppDeadlineDate ='9999-09-09 00:00:00' ,janFullYearEndDate ='9999-09-09 00:00:00',showJanFullYearToNewHF=0,
      hfInquiryDate='9999-09-09',showSpecialRequestStudent=0
WHERE `seasonJ1DetailsId`=39;

INSERT INTO `SeasonJ1Details` (`seasonJ1DetailsId`,`programName`,`programStatusId`,`secondSemStartDate`,`secondSemEndDate`,
                                           `secondSemAppDeadlineDate`,`secondSemEarliestBirthDate`,`secondSemLatestBirthDate`,`showSecondSemToNewHF`,
                                           `firstSemStartDate`,`firstSemEndDate`,`firstSemAppDeadlineDate`,
                                           `firstSemEarliestBirthDate`,`firstSemLatestBirthDate`,`showFirstSemToNewHF`,`augFullYearStartDate`,
                                           `augFullYearEndDate`,`augFullYearAppDeadlineDate`,`showAugFullYearToNewHF`,`showSeasonToCurrentHF`,
                                           `fieldStaffHoldLength`,`hoursBeforeHoldExpirationWarning`,`lcPaymentScheduleId`,`fsAgreementId`,`hfReferences`,
                                           `showWelcomeFamily`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`SeasonStatusID`,`SecondStartDate`,`EndDate`,
                                           `SecondDeadlineDate`,`SecondEarliestBirthDate`,`SecondLatestBirthDate`,`HFCanHostSecondSemester`,
                                            `StartDate`,`SecondEndDate`,`ApplicationDeadlineDate`,
                                            `EarliestBirthDate`,`LatestBirthDate`,`HFCanHostFirstSemester`,`StartDate`,
                                            `EndDate`,`ApplicationDeadlineDate`,`HFCanHostFirstSemester`,`HFCanReturningView`,
                                            `FieldStaffHoldLength`,`HoursBeforeHoldExpirationWarning`,`DefaultPaymentScheduleID`,`FieldStaffAgreementID`,`HFRequiredReferences`,
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`season` WHERE `SeasonID`=48;


UPDATE `SeasonJ1Details` 
SET `seasonId` = 3,
	 `showGuaranteed` = 0, 
	 `showUnguaranteed` = 0,activeFullYearJanProgram = 0,janFullYearStartDate ='9999-09-09 00:00:00' ,
	  janFullYearAppDeadlineDate ='9999-09-09 00:00:00' ,janFullYearEndDate ='9999-09-09 00:00:00',showJanFullYearToNewHF=0,
      hfInquiryDate='9999-09-09',showSpecialRequestStudent=0
WHERE `seasonJ1DetailsId`=48;

INSERT INTO `SeasonJ1Details` (`seasonJ1DetailsId`,`programName`,`programStatusId`,`secondSemStartDate`,`secondSemEndDate`,
                                           `secondSemAppDeadlineDate`,`secondSemEarliestBirthDate`,`secondSemLatestBirthDate`,`showSecondSemToNewHF`,
                                           `firstSemStartDate`,`firstSemEndDate`,`firstSemAppDeadlineDate`,
                                           `firstSemEarliestBirthDate`,`firstSemLatestBirthDate`,`showFirstSemToNewHF`,`augFullYearStartDate`,
                                           `augFullYearEndDate`,`augFullYearAppDeadlineDate`,`showAugFullYearToNewHF`,`showSeasonToCurrentHF`,
                                           `fieldStaffHoldLength`,`hoursBeforeHoldExpirationWarning`,`lcPaymentScheduleId`,`fsAgreementId`,`hfReferences`,
                                           `showWelcomeFamily`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`SeasonStatusID`,`SecondStartDate`,`EndDate`,
                                           `SecondDeadlineDate`,`SecondEarliestBirthDate`,`SecondLatestBirthDate`,`HFCanHostSecondSemester`,
                                            `StartDate`,`SecondEndDate`,`ApplicationDeadlineDate`,
                                            `EarliestBirthDate`,`LatestBirthDate`,`HFCanHostFirstSemester`,`StartDate`,
                                            `EndDate`,`ApplicationDeadlineDate`,`HFCanHostFirstSemester`,`HFCanReturningView`,
                                            `FieldStaffHoldLength`,`HoursBeforeHoldExpirationWarning`,`DefaultPaymentScheduleID`,`FieldStaffAgreementID`,`HFRequiredReferences`,
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`season` WHERE `SeasonID`=67;


UPDATE `SeasonJ1Details` 
SET `seasonId` = 4,
	 `showGuaranteed` = 1, 
	 `showUnguaranteed` = 1,activeFullYearJanProgram = 0,janFullYearStartDate ='9999-09-09 00:00:00' ,
	  janFullYearAppDeadlineDate ='9999-09-09 00:00:00' ,janFullYearEndDate ='9999-09-09 00:00:00',showJanFullYearToNewHF=0,
      hfInquiryDate='9999-09-09',showSpecialRequestStudent=0
WHERE `seasonJ1DetailsId`=67;

INSERT INTO `SeasonJ1Details` (`seasonJ1DetailsId`,`programName`,`programStatusId`,`secondSemStartDate`,`secondSemEndDate`,
                                           `secondSemAppDeadlineDate`,`secondSemEarliestBirthDate`,`secondSemLatestBirthDate`,`showSecondSemToNewHF`,
                                           `firstSemStartDate`,`firstSemEndDate`,`firstSemAppDeadlineDate`,
                                           `firstSemEarliestBirthDate`,`firstSemLatestBirthDate`,`showFirstSemToNewHF`,`augFullYearStartDate`,
                                           `augFullYearEndDate`,`augFullYearAppDeadlineDate`,`showAugFullYearToNewHF`,`showSeasonToCurrentHF`,
                                           `fieldStaffHoldLength`,`hoursBeforeHoldExpirationWarning`,`lcPaymentScheduleId`,`fsAgreementId`,`hfReferences`,
                                           `showWelcomeFamily`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`SeasonStatusID`,`SecondStartDate`,`EndDate`,
                                           `SecondDeadlineDate`,`SecondEarliestBirthDate`,`SecondLatestBirthDate`,`HFCanHostSecondSemester`,
                                            `StartDate`,`SecondEndDate`,`ApplicationDeadlineDate`,
                                            `EarliestBirthDate`,`LatestBirthDate`,`HFCanHostFirstSemester`,`StartDate`,
                                            `EndDate`,`ApplicationDeadlineDate`,`HFCanHostFirstSemester`,`HFCanReturningView`,
                                            `FieldStaffHoldLength`,`HoursBeforeHoldExpirationWarning`,`DefaultPaymentScheduleID`,`FieldStaffAgreementID`,`HFRequiredReferences`,
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`season` WHERE `SeasonID`=74;


UPDATE `SeasonJ1Details` 
SET `seasonId` = 5,
	 `showGuaranteed` = 1, 
	 `showUnguaranteed` = 1,activeFullYearJanProgram = 0,janFullYearStartDate ='9999-09-09 00:00:00' ,
	  janFullYearAppDeadlineDate ='9999-09-09 00:00:00' ,janFullYearEndDate ='9999-09-09 00:00:00',showJanFullYearToNewHF=0,
      hfInquiryDate='9999-09-09',showSpecialRequestStudent=0
WHERE `seasonJ1DetailsId`=74;

INSERT INTO `SeasonJ1Details` (`seasonJ1DetailsId`,`programName`,`programStatusId`,`secondSemStartDate`,`secondSemEndDate`,
                                           `secondSemAppDeadlineDate`,`secondSemEarliestBirthDate`,`secondSemLatestBirthDate`,`showSecondSemToNewHF`,
                                           `firstSemStartDate`,`firstSemEndDate`,`firstSemAppDeadlineDate`,
                                           `firstSemEarliestBirthDate`,`firstSemLatestBirthDate`,`showFirstSemToNewHF`,`augFullYearStartDate`,
                                           `augFullYearEndDate`,`augFullYearAppDeadlineDate`,`showAugFullYearToNewHF`,`showSeasonToCurrentHF`,
                                           `fieldStaffHoldLength`,`hoursBeforeHoldExpirationWarning`,`lcPaymentScheduleId`,`fsAgreementId`,`hfReferences`,
                                           `showWelcomeFamily`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`SeasonStatusID`,`SecondStartDate`,`EndDate`,
                                           `SecondDeadlineDate`,`SecondEarliestBirthDate`,`SecondLatestBirthDate`,`HFCanHostSecondSemester`,
                                            `StartDate`,`SecondEndDate`,`ApplicationDeadlineDate`,
                                            `EarliestBirthDate`,`LatestBirthDate`,`HFCanHostFirstSemester`,`StartDate`,
                                            `EndDate`,`ApplicationDeadlineDate`,`HFCanHostFirstSemester`,`HFCanReturningView`,
                                            `FieldStaffHoldLength`,`HoursBeforeHoldExpirationWarning`,`DefaultPaymentScheduleID`,`FieldStaffAgreementID`,`HFRequiredReferences`,
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`season` WHERE `SeasonID`=88;

	
UPDATE `SeasonJ1Details` 
SET `seasonId` = 6,
	 `showGuaranteed` = 1, 
	 `showUnguaranteed` = 1,activeFullYearJanProgram = 0,janFullYearStartDate ='9999-09-09 00:00:00' ,
	  janFullYearAppDeadlineDate ='9999-09-09 00:00:00' ,janFullYearEndDate ='9999-09-09 00:00:00',showJanFullYearToNewHF=0,
      hfInquiryDate='9999-09-09',showSpecialRequestStudent=0
WHERE `seasonJ1DetailsId`=88;

INSERT INTO `SeasonJ1Details` (`seasonJ1DetailsId`,`programName`,`programStatusId`,`secondSemStartDate`,`secondSemEndDate`,
                                           `secondSemAppDeadlineDate`,`secondSemEarliestBirthDate`,`secondSemLatestBirthDate`,`showSecondSemToNewHF`,
                                           `firstSemStartDate`,`firstSemEndDate`,`firstSemAppDeadlineDate`,
                                           `firstSemEarliestBirthDate`,`firstSemLatestBirthDate`,`showFirstSemToNewHF`,`augFullYearStartDate`,
                                           `augFullYearEndDate`,`augFullYearAppDeadlineDate`,`showAugFullYearToNewHF`,`showSeasonToCurrentHF`,
                                           `fieldStaffHoldLength`,`hoursBeforeHoldExpirationWarning`,`lcPaymentScheduleId`,`fsAgreementId`,`hfReferences`,
                                           `showWelcomeFamily`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`SeasonStatusID`,`SecondStartDate`,`EndDate`,
                                           `SecondDeadlineDate`,`SecondEarliestBirthDate`,`SecondLatestBirthDate`,`HFCanHostSecondSemester`,
                                            `StartDate`,`SecondEndDate`,`ApplicationDeadlineDate`,
                                            `EarliestBirthDate`,`LatestBirthDate`,`HFCanHostFirstSemester`,`StartDate`,
                                            `EndDate`,`ApplicationDeadlineDate`,`HFCanHostFirstSemester`,`HFCanReturningView`,
                                            `FieldStaffHoldLength`,`HoursBeforeHoldExpirationWarning`,`DefaultPaymentScheduleID`,`FieldStaffAgreementID`,`HFRequiredReferences`,
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`season` WHERE `SeasonID`=92;


UPDATE `SeasonJ1Details` 
SET `seasonId` = 7,
	 `showGuaranteed` = 1, 
	 `showUnguaranteed` = 1,activeFullYearJanProgram = 0,janFullYearStartDate ='9999-09-09 00:00:00' ,
	  janFullYearAppDeadlineDate ='9999-09-09 00:00:00' ,janFullYearEndDate ='9999-09-09 00:00:00',showJanFullYearToNewHF=0,
      hfInquiryDate='9999-09-09',showSpecialRequestStudent=0
WHERE `seasonJ1DetailsId`=92;


/*UPDATE `SeasonJ1Details` sj,`dummy`.`season` s
SET sj.`showGuaranteed` = 1, sj.`showUnguaranteed` = 1
WHERE s.`FieldStaffStudentView`=2 AND s.`seasonId`=92 AND sj.`seasonId`=7;*/

UPDATE `SeasonJ1Details`
SET fsAgreementId = 8
WHERE fsAgreementId = 0;

UPDATE `SeasonJ1Details`
SET lcPaymentScheduleId = 32
WHERE lcPaymentScheduleId = 0;

/*------------------------------------------------------------------------------------------------------------------------------------------------------------
          SeasonIHPDetails Migration
------------------------------------------------------------------------------------------------------------------------------------------------------------*/


INSERT  INTO `SeasonIHPDetails`(`seasonIHPDetailsId`,`seasonId`,`programName`,`startDate`,`endDate`,`programStatusId`,`maxParticipants`,`lcHoldTime`,`numberOfLCToRequestHold`,`splitPlacementPending`,`stopAcceptingApps`,`stopAcceptingAppsByGender`,`genderId`,`applicationDeadLineWeeks`,`stopAcceptingAppsStandardIHP`,`stopAcceptingAppsVolunteerHomestay`,`stopAcceptingAppsLanguageBuddy`,`stopAcceptingAppsHolidayHomestay`,`stopAcceptingAppsHighSchoolVisits`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
VALUES 
(1,7,'IHP-2015','9999-09-09 00:00:00','9999-09-09 00:00:00',2,0,0,0,0,0,0,1,0,0,0,0,0,0,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1);

/*------------------------------------------------------------------------------------------------------------------------------------------------------------
          SeasonIHPDetailsRegionApplications Migration
------------------------------------------------------------------------------------------------------------------------------------------------------------*/

INSERT  INTO `SeasonIHPDetailsRegionApplications`(`seasonIHPDetailsRegionApplicationId`,`seasonIHPDetailsId`,`regionIHPId`,`stopAcceptingApps`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`) 
VALUES  
(1,1,1,1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(2,1,2,1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(3,1,3,1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(4,1,4,1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(5,1,5,1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),  
(6,1,6,1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1);
     


/*------------------------------------------------------------------------------------------------------------------------------------------------------------
          SeasonHSPAllocation Migration
------------------------------------------------------------------------------------------------------------------------------------------------------------*/



INSERT INTO `SeasonHSPAllocation` (`seasonId`,`departmentProgramOptionId`,`createdBy`,`modifiedBy`)
VALUES 
(1,1,1,1), 
(1,3,1,1),
(1,5,1,1), 
(1,8,1,1);
INSERT INTO `SeasonHSPAllocation` (`seasonId`,`departmentProgramOptionId`,`createdBy`,`modifiedBy`)
VALUES 
(2,1,1,1), 
(2,3,1,1),
(2,5,1,1), 
(2,8,1,1);
INSERT INTO `SeasonHSPAllocation` (`seasonId`,`departmentProgramOptionId`,`createdBy`,`modifiedBy`)
VALUES 
(3,1,1,1), 
(3,3,1,1),
(3,5,1,1), 
(3,8,1,1);
INSERT INTO `SeasonHSPAllocation` (`seasonId`,`departmentProgramOptionId`,`createdBy`,`modifiedBy`)
VALUES 
(4,1,1,1), 
(4,3,1,1),
(4,5,1,1), 
(4,8,1,1);
INSERT INTO `SeasonHSPAllocation` (`seasonId`,`departmentProgramOptionId`,`createdBy`,`modifiedBy`)
VALUES 
(5,1,1,1), 
(5,3,1,1),
(5,5,1,1), 
(5,8,1,1);
INSERT INTO `SeasonHSPAllocation` (`seasonId`,`departmentProgramOptionId`,`createdBy`,`modifiedBy`)
VALUES 
(6,1,1,1), 
(6,3,1,1),
(6,5,1,1), 
(6,8,1,1);

INSERT INTO `SeasonHSPAllocation` (`seasonId`,`departmentProgramOptionId`,`createdBy`,`modifiedBy`)
VALUES 
(7,1,1,1), 
(7,3,1,1),
(7,5,1,1), 
(7,8,1,1);
       
--  J1  Allocations
    
UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`season` so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 1 AND shsp.`seasonId`=1 AND so.`SeasonID` = 37;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 3 AND shsp.`seasonId`=1 AND so.`SeasonID` = 37;


UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 1 AND shsp.`seasonId`=2 AND so.`SeasonID` = 39;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 3 AND shsp.`seasonId`=2 AND so.`SeasonID` = 39;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 1 AND shsp.`seasonId`=3 AND so.`SeasonID` = 48;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 3 AND shsp.`seasonId`=3 AND so.`SeasonID` = 48;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 1 AND shsp.`seasonId`=4 AND so.`SeasonID` = 67;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 3 AND shsp.`seasonId`=4 AND so.`SeasonID` = 67;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 1 AND shsp.`seasonId`=5 AND so.`SeasonID` = 74;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 3 AND shsp.`seasonId`=5 AND so.`SeasonID` = 74;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 1 AND shsp.`seasonId`=6 AND so.`SeasonID` = 88;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 3 AND shsp.`seasonId`=6 AND so.`SeasonID` = 88;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 1 AND shsp.`seasonId`=7 AND so.`SeasonID` = 92;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 3 AND shsp.`seasonId`=7 AND so.`SeasonID` = 92;





--  F1 Allocations     

UPDATE `SeasonHSPAllocation` 
SET `maxGuaranteedPax`= 0,
    `maxUnguaranteedPax`= 0
WHERE `departmentProgramOptionId` = 5 AND `seasonId`=1 ;

UPDATE `SeasonHSPAllocation` 
SET `maxGuaranteedPax`= 0,
    `maxUnguaranteedPax`= 0
WHERE `departmentProgramOptionId` = 8 AND `seasonId`=1;


UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 5 AND shsp.`seasonId`=2 AND so.`SeasonID` = 49;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 8 AND shsp.`seasonId`=2 AND so.`SeasonID` = 49;


UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 5 AND shsp.`seasonId`=3 AND so.`SeasonID` = 60;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 8 AND shsp.`seasonId`=3 AND so.`SeasonID` = 60;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 5 AND shsp.`seasonId`=4 AND so.`SeasonID` = 69;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 8 AND shsp.`seasonId`=4 AND so.`SeasonID` = 69;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 5 AND shsp.`seasonId`=5 AND so.`SeasonID` = 75;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 8 AND shsp.`seasonId`=5 AND so.`SeasonID` = 75;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 5 AND shsp.`seasonId`=6 AND so.`SeasonID` = 89;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 8 AND shsp.`seasonId`=6 AND so.`SeasonID` = 89;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 5 AND shsp.`seasonId`=7 AND so.`SeasonID` = 94;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 8 AND shsp.`seasonId`=7 AND so.`SeasonID` = 94;  

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Insert data for SeasonGeographyConfiguration table 
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/


INSERT INTO `SeasonGeographyConfiguration` (superRegionId,regionId,usStatesId,seasonId)
VALUES
(1,NULL,NULL,7),
(1,1,NULL,7),
(1,1,48,7),
(1,1,32,7),
(1,1,13,7),
(1,1,21,7),
(1,2,NULL,7),
(1,2,23,7),
(1,2,45,7),
(1,2,3,7),
(1,2,26,7),
(1,3,NULL,7),
(1,3,5,7),
(1,3,51,7),
(1,3,6,7),
(1,3,22,7),
(1,3,17,7),
(1,3,2,7),
(1,3,12,7),
(2,NULL,NULL,7),
(2,4,NULL,7),
(2,4,29,7),
(2,4,42,7),
(2,4,36,7),
(2,4,16,7),
(2,4,50,7),
(2,4,14,7),
(2,5,NULL,7),
(2,5,38,7),
(2,5,31,7),
(2,5,44,7),
(2,5,1,7),
(2,6,NULL,7),
(2,6,18,7),
(2,6,43,7),
(2,6,4,7),
(2,6,37,7),
(2,6,19,7),
(2,7,NULL,7),
(3,NULL,NULL,7),
(3,8,NULL,7),
(3,8,35,7),
(3,8,15,7),
(3,8,30,7),
(3,8,49,7),
(3,9,NULL,7),
(3,9,27,7),
(3,9,39,7),
(3,9,25,7),
(3,9,33,7),
(3,9,8,7),
(3,9,47,7),
(3,9,28,7),
(3,9,41,7),
(3,9,11,7),
(3,9,10,7),
(3,10,NULL,7),
(3,10,20,7),
(3,10,46,7),
(3,10,24,7),
(3,10,34,7),
(3,10,7,7),
(3,10,40,7);

UPDATE SeasonGeographyConfiguration SET createdBy = 1,modifiedBy = 1;   


/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Insert data for SeasonIHPGeographyConfiguration table 
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/

INSERT INTO SeasonIHPGeographyConfiguration (regionIHPId,usStatesId,seasonId)
VALUES 
(1,NULL,7),
(1,7,7),
(1,9,7),
(1,22,7),
(1,21,7),
(1,20,7),
(1,31,7),
(1,32,7),
(1,35,7),
(1,39,7),
(1,40,7),
(1,47,7),
(1,46,7),
(1,48,7),
(2,NULL,7),
(2,15,7),
(2,16,7),
(2,13,7),
(2,23,7),
(2,24,7),
(2,25,7),
(2,36,7),
(2,50,7),
(2,49,7),
(2,30,7),
(2,17,7),
(3,NULL,7),
(3,6,7),
(3,4,7),
(3,14,7),
(3,27,7),
(3,33,7),
(3,34,7),
(3,42,7),
(3,29,7),
(3,45,7),
(3,51,7),
(3,48,7),
(3,38,7),
(4,NULL,7),
(4,5,7),
(5,NULL,7),
(5,2,7),
(5,3,7),
(5,10,7),
(5,11,7),
(5,18,7),
(5,19,7),
(5,26,7),
(5,28,7),
(5,41,7),
(5,43,7),
(5,44,7),
(5,37,7),
(5,50,7),
(6,NULL,7),
(6,12,7),
(6,1,7);

UPDATE SeasonIHPGeographyConfiguration
SET createdOn = CURRENT_TIMESTAMP,
createdBy = 1,
modifiedOn = CURRENT_TIMESTAMP,
modifiedBy = 1;



		

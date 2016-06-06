
-- USE cci_gh_go_qa;

SET FOREIGN_KEY_CHECKS= 0;

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Truncating Season Module tables.
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
TRUNCATE TABLE `FieldStaffAgreement` ;
TRUNCATE TABLE `PaymentSchedule` ;
TRUNCATE TABLE `Season` ;
TRUNCATE TABLE `SeasonHSPConfiguration` ;
TRUNCATE TABLE `SeasonF1Details` ;
TRUNCATE TABLE `SeasonJ1Details` ;
TRUNCATE TABLE `SeasonHSPAllocation` ;
TRUNCATE TABLE `SeasonGeographyConfiguration` ;
TRUNCATE TABLE `SeasonIHPDetails` ;
TRUNCATE TABLE `SeasonIHPDetailsRegionApplications`;
TRUNCATE TABLE `SeasonIHPGeographyConfiguration`;

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Inserting Data Into FieldStaffAgreement.
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/

INSERT INTO `FieldStaffAgreement` (`fieldStaffAgreementId`,`agreementName`)
/*SELECT `FieldStaffAgreementID`,`AgreementName` FROM `cci_go`.`FieldStaffAgreement` WHERE `FieldStaffAgreementID` <> 0;*/
VALUES
(1,'2009-2010'),
(2,'2010-2011'),
(3,'2011-2012'),
(4,'2012-2013'),
(5,'2013-2014'),
(6,'2014-2015'),
(7,'2015-2016'),
(8,'Undefined'),
(9,'2013-2014'),
(10,'2013-2014');
/*INSERT INTO `FieldStaffAgreement` (fsAgreementId,`agreementName`)
VALUES (11,'Undefined');*/

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Inserting Data Into PaymentSchedule.
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/

INSERT INTO `PaymentSchedule` (`paymentScheduleId`,`scheduleName`)
/*SELECT `PaymentScheduleID`,`ScheduleName` FROM `cci_go`.`PaymentSchedule` WHERE `PaymentScheduleID` <> 0;*/
VALUES 
(1,'Area Representative Stipend Schedule'),
(2,'PSPP Area Representative Stipend Schedule'),
(3,'Field Staff Schedule'),
(4,'PSPP Field Staff Schedule'),
(6,'PSPP Field Staff Schedule'),
(21,'PSPP Area Representative Stipend Schedule'),
(24,'PSPP Area Representative Stipend Schedule'),
(30,'PSPP Area Representative Stipend Schedule'),
(31,'PSPP Area Representative Stipend Schedule'),
(32,'Undefined');
/*INSERT INTO `PaymentSchedule` (lcPaymentScheduleId,`scheduleName`)
VALUES (33,'Undefined');*/


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
(7,'HSP AYP 2015-2016','HSP AYP 2015-2016',1,1,1),
(22,'HSP AYP 2016-2017','HSP AYP 2016-2017',1,1,1);
		

UPDATE `Season`
SET `seasonStatusId` = 2 WHERE seasonId= 1;
UPDATE `Season`
SET `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`Season` WHERE `SeasonID` IN(39,49) ORDER BY `SeasonStatusID` ASC LIMIT 1)
WHERE seasonId= 2;
UPDATE `Season`
SET `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`Season` WHERE `SeasonID` IN(48,60) ORDER BY `SeasonStatusID` ASC LIMIT 1)
WHERE seasonId= 3;
UPDATE `Season`
SET `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`Season` WHERE `SeasonID` IN(67,69) ORDER BY `SeasonStatusID` ASC LIMIT 1)
WHERE seasonId= 4;
UPDATE `Season`
SET `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`Season` WHERE `SeasonID` IN(74,75) ORDER BY `SeasonStatusID` ASC LIMIT 1)
WHERE seasonId= 5;
UPDATE `Season`
SET `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`Season` WHERE `SeasonID` IN(88,89) ORDER BY `SeasonStatusID` ASC LIMIT 1)
WHERE seasonId= 6;		
UPDATE `Season`
SET `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`Season` WHERE `SeasonID` IN(92,94) ORDER BY `SeasonStatusID` ASC LIMIT 1)
WHERE seasonId= 7;
UPDATE `Season`
SET `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`Season` WHERE `SeasonID` IN(106) ORDER BY `SeasonStatusID` ASC LIMIT 1)
WHERE seasonId= 22;


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
(7,1,1),
(22,1,1);
		
UPDATE SeasonHSPConfiguration 
SET seasonStartDate = (SELECT StartDate FROM `cci_go`.`Season` WHERE SeasonID IN (37)),
	seasonEndDate = (SELECT EndDate FROM `cci_go`.`Season` WHERE SeasonID IN (37))
WHERE seasonId = 1;
		
UPDATE SeasonHSPConfiguration 
SET seasonStartDate = (SELECT MIN(StartDate) FROM `cci_go`.`Season` WHERE SeasonID IN (39,49)),
	seasonEndDate = (SELECT MAX(EndDate) FROM `cci_go`.`Season` WHERE SeasonID IN (39,49))
WHERE seasonId = 2;

UPDATE SeasonHSPConfiguration 
SET seasonStartDate = (SELECT MIN(StartDate) FROM `cci_go`.`Season` WHERE SeasonID IN (48,60)),
	seasonEndDate = (SELECT MAX(EndDate) FROM `cci_go`.`Season` WHERE SeasonID IN (48,60))
WHERE seasonId = 3;

UPDATE SeasonHSPConfiguration 
SET seasonStartDate = (SELECT MIN(StartDate) FROM `cci_go`.`Season` WHERE SeasonID IN (67,69)),
	seasonEndDate = (SELECT MAX(EndDate) FROM `cci_go`.`Season` WHERE SeasonID IN (67,69))
WHERE seasonId = 4;

UPDATE SeasonHSPConfiguration 
SET seasonStartDate = (SELECT MIN(StartDate) FROM `cci_go`.`Season` WHERE SeasonID IN (74,75)),
	seasonEndDate = (SELECT MAX(EndDate) FROM `cci_go`.`Season` WHERE SeasonID IN (74,75))
WHERE seasonId = 5;

UPDATE SeasonHSPConfiguration 
SET seasonStartDate = (SELECT MIN(StartDate) FROM `cci_go`.`Season` WHERE SeasonID IN (88,89)),
	seasonEndDate = (SELECT MAX(EndDate) FROM `cci_go`.`Season` WHERE SeasonID IN (88,89))
WHERE seasonId = 6;

UPDATE SeasonHSPConfiguration 
SET seasonStartDate = (SELECT MIN(StartDate) FROM `cci_go`.`Season` WHERE SeasonID IN (92,94)),
	seasonEndDate = (SELECT MAX(EndDate) FROM `cci_go`.`Season` WHERE SeasonID IN (92,94))
WHERE seasonId = 7;

UPDATE SeasonHSPConfiguration 
SET seasonStartDate = (SELECT MIN(StartDate) FROM `cci_go`.`Season` WHERE SeasonID IN (106)),
	seasonEndDate = (SELECT MAX(EndDate) FROM `cci_go`.`Season` WHERE SeasonID IN (106))
WHERE seasonId = 22;

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
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID`= 49;


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
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID`= 60;



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
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID`= 69;




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
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID`= 75;



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
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID`= 89;



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
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID`= 94;
 


UPDATE `SeasonF1Details` 
SET `seasonId` = 7,activeFullYearJanProgram = 0,janFullYearStartDate ='9999-09-09 00:00:00' ,
janFullYearAppDeadlineDate ='9999-09-09 00:00:00' ,janFullYearEndDate ='9999-09-09 00:00:00',showJanFullYearToNewHF=0,
hfInquiryDate='9999-09-09',allowFieldStaffToStartRenewalProcess=0,showSpecialRequestStudent=0,greenHeartMargin=0
WHERE `seasonF1DetailsId`=94;

INSERT INTO `SeasonF1Details`(`seasonF1DetailsId`,`seasonId`,`programName`,`programStatusId`,`secondSemStartDate`,`secondSemEndDate`,`secondSemAppDeadlineDate`,`secondSemEarliestBirthDate`,`secondSemLatestBirthDate`,`showSecSemToNewHF`,`activeFullYearJanProgram`,`janFullYearStartDate`,`janFullYearAppDeadlineDate`,`janFullYearEndDate`,`showJanFullYearToNewHF`,`firstSemStartDate`,`firstSemEndDate`,`firstSemAppDeadlineDate`,`firstSemEarliestBirthDate`,`firstSemLatestBirthDate`,`showFirstSemToNewHF`,`augFullYearStartDate`,`augFullYearEndDate`,`augFullYearAppDeadlineDate`,`showAugFullYearToNewHF`,`showSeasonToCurrentHF`,`lcPaymentScheduleId`,`fsAgreementId`,`hfReferences`,`hfInquiryDate`,`showWelcomeFamily`,`allowFieldStaffToStartRenewalProcess`,`showSpecialRequestStudent`,`greenHeartMargin`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`) 
VALUES 
(106,22,'AYP-PSPP 2016-17',2,'9999-09-09 00:00:00','9999-09-09 00:00:00','9999-09-09 00:00:00','9999-09-09 00:00:00','9999-09-09 00:00:00',0,0,'9999-09-09 00:00:00','9999-09-09 00:00:00','9999-09-09 00:00:00',0,'9999-09-09 00:00:00','9999-09-09 00:00:00','9999-09-09 00:00:00','9999-09-09 00:00:00','9999-09-09 00:00:00',0,'9999-09-09 00:00:00','9999-09-09 00:00:00','9999-09-09 00:00:00',0,0,1,1,0,'9999-09-09',0,0,0,0,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1);


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
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID`=37;


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
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID`=39;

	
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
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID`=48;


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
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID`=67;


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
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID`=74;


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
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID`=88;

	
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
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID`=92;


UPDATE `SeasonJ1Details` 
SET `seasonId` = 7,
	 `showGuaranteed` = 1, 
	 `showUnguaranteed` = 1,activeFullYearJanProgram = 0,janFullYearStartDate ='9999-09-09 00:00:00' ,
	  janFullYearAppDeadlineDate ='9999-09-09 00:00:00' ,janFullYearEndDate ='9999-09-09 00:00:00',showJanFullYearToNewHF=0,
      hfInquiryDate='9999-09-09',showSpecialRequestStudent=0
WHERE `seasonJ1DetailsId`=92;

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
                                            `WelcomeFamilyActive`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID`=106;


UPDATE `SeasonJ1Details` 
SET `seasonId` = 22,
	 `showGuaranteed` = 0, 
	 `showUnguaranteed` = 0,activeFullYearJanProgram = 0,janFullYearStartDate ='9999-09-09 00:00:00' ,
	  janFullYearAppDeadlineDate ='9999-09-09 00:00:00' ,janFullYearEndDate ='9999-09-09 00:00:00',showJanFullYearToNewHF=0,
      hfInquiryDate='9999-09-09',showSpecialRequestStudent=0
WHERE `seasonJ1DetailsId`=106;


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
(1,22,'IHP-2016','9999-09-09 00:00:00','9999-09-09 00:00:00',2,0,0,0,0,0,0,1,0,0,0,0,0,0,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1);

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

INSERT INTO `SeasonHSPAllocation` (`seasonId`,`departmentProgramOptionId`,`createdBy`,`modifiedBy`)
VALUES 
(22,1,1,1), 
(22,3,1,1),
(22,5,1,1), 
(22,8,1,1);
       
--  J1  Allocations
    
UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season` so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 1 AND shsp.`seasonId`=1 AND so.`SeasonID` = 37;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 3 AND shsp.`seasonId`=1 AND so.`SeasonID` = 37;


UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 1 AND shsp.`seasonId`=2 AND so.`SeasonID` = 39;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 3 AND shsp.`seasonId`=2 AND so.`SeasonID` = 39;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 1 AND shsp.`seasonId`=3 AND so.`SeasonID` = 48;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 3 AND shsp.`seasonId`=3 AND so.`SeasonID` = 48;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 1 AND shsp.`seasonId`=4 AND so.`SeasonID` = 67;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 3 AND shsp.`seasonId`=4 AND so.`SeasonID` = 67;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 1 AND shsp.`seasonId`=5 AND so.`SeasonID` = 74;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 3 AND shsp.`seasonId`=5 AND so.`SeasonID` = 74;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 1 AND shsp.`seasonId`=6 AND so.`SeasonID` = 88;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 3 AND shsp.`seasonId`=6 AND so.`SeasonID` = 88;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 1 AND shsp.`seasonId`=7 AND so.`SeasonID` = 92;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 3 AND shsp.`seasonId`=7 AND so.`SeasonID` = 92;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season` so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 1 AND shsp.`seasonId`=22 AND so.`SeasonID` = 106;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 3 AND shsp.`seasonId`=22 AND so.`SeasonID` = 106;



--  F1 Allocations     

UPDATE `SeasonHSPAllocation` 
SET `maxGuaranteedPax`= 0,
    `maxUnguaranteedPax`= 0
WHERE `departmentProgramOptionId` = 5 AND `seasonId`=1 ;

UPDATE `SeasonHSPAllocation` 
SET `maxGuaranteedPax`= 0,
    `maxUnguaranteedPax`= 0
WHERE `departmentProgramOptionId` = 8 AND `seasonId`=1;


UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 5 AND shsp.`seasonId`=2 AND so.`SeasonID` = 49;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 8 AND shsp.`seasonId`=2 AND so.`SeasonID` = 49;


UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 5 AND shsp.`seasonId`=3 AND so.`SeasonID` = 60;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 8 AND shsp.`seasonId`=3 AND so.`SeasonID` = 60;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 5 AND shsp.`seasonId`=4 AND so.`SeasonID` = 69;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 8 AND shsp.`seasonId`=4 AND so.`SeasonID` = 69;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 5 AND shsp.`seasonId`=5 AND so.`SeasonID` = 75;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 8 AND shsp.`seasonId`=5 AND so.`SeasonID` = 75;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 5 AND shsp.`seasonId`=6 AND so.`SeasonID` = 89;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 8 AND shsp.`seasonId`=6 AND so.`SeasonID` = 89;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationAugustGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationAugust`
WHERE shsp.`departmentProgramOptionId` = 5 AND shsp.`seasonId`=7 AND so.`SeasonID` = 94;

UPDATE `SeasonHSPAllocation` shsp,`cci_go`.`Season`  so
SET shsp.`maxGuaranteedPax`= so.`AllocationJanuaryGuaranteed`,
    shsp.`maxUnguaranteedPax`= so.`AllocationJanuary`
WHERE shsp.`departmentProgramOptionId` = 8 AND shsp.`seasonId`=7 AND so.`SeasonID` = 94;  

UPDATE `SeasonHSPAllocation` 
SET `maxGuaranteedPax`= 0,
    `maxUnguaranteedPax`= 0
WHERE `departmentProgramOptionId` = 5 AND `seasonId`=22;

UPDATE `SeasonHSPAllocation` 
SET `maxGuaranteedPax`= 0,
    `maxUnguaranteedPax`= 0
WHERE `departmentProgramOptionId` = 8 AND `seasonId`=22;

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Insert data for SeasonGeographyConfiguration table 
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/


INSERT INTO `SeasonGeographyConfiguration` (superRegionId,regionId,usStatesId,seasonId)
VALUES
(1,NULL,NULL,22),
(1,1,NULL,22),
(1,1,48,22),
(1,1,32,22),
(1,1,13,22),
(1,1,21,22),
(1,2,NULL,22),
(1,2,23,22),
(1,2,45,22),
(1,2,3,22),
(1,2,26,22),
(1,3,NULL,22),
(1,3,5,22),
(1,3,51,22),
(1,3,6,22),
(1,3,22,22),
(1,3,17,22),
(1,3,2,22),
(1,3,12,22),
(2,NULL,NULL,22),
(2,4,NULL,22),
(2,4,29,22),
(2,4,42,22),
(2,4,36,22),
(2,4,16,22),
(2,4,50,22),
(2,4,14,22),
(2,5,NULL,22),
(2,5,38,22),
(2,5,31,22),
(2,5,44,22),
(2,5,1,22),
(2,6,NULL,22),
(2,6,18,22),
(2,6,43,22),
(2,6,4,22),
(2,6,37,22),
(2,6,19,22),
(2,7,NULL,22),
(3,NULL,NULL,22),
(3,8,NULL,22),
(3,8,35,22),
(3,8,15,22),
(3,8,30,22),
(3,8,49,22),
(3,9,NULL,22),
(3,9,27,22),
(3,9,39,22),
(3,9,25,22),
(3,9,33,22),
(3,9,8,22),
(3,9,47,22),
(3,9,28,22),
(3,9,41,22),
(3,9,11,22),
(3,9,10,22),
(3,10,NULL,22),
(3,10,20,22),
(3,10,46,22),
(3,10,24,22),
(3,10,34,22),
(3,10,7,22),
(3,10,40,22);

UPDATE SeasonGeographyConfiguration SET createdBy = 1,modifiedBy = 1;   


/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Insert data for SeasonIHPGeographyConfiguration table 
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/

INSERT INTO SeasonIHPGeographyConfiguration (regionIHPId,usStatesId,seasonId)
VALUES 
(1,NULL,22),
(1,7,22),
(1,9,22),
(1,22,22),
(1,21,22),
(1,20,22),
(1,31,22),
(1,32,22),
(1,35,22),
(1,39,22),
(1,40,22),
(1,47,22),
(1,46,22),
(1,48,22),
(2,NULL,22),
(2,15,22),
(2,16,22),
(2,13,22),
(2,23,22),
(2,24,22),
(2,25,22),
(2,36,22),
(2,50,22),
(2,49,22),
(2,30,22),
(2,17,22),
(3,NULL,22),
(3,6,22),
(3,4,22),
(3,14,22),
(3,27,22),
(3,33,22),
(3,34,22),
(3,42,22),
(3,29,22),
(3,45,22),
(3,51,22),
(3,48,22),
(3,38,22),
(4,NULL,22),
(4,5,22),
(5,NULL,22),
(5,2,22),
(5,3,22),
(5,10,22),
(5,11,22),
(5,18,22),
(5,19,22),
(5,26,22),
(5,28,22),
(5,41,22),
(5,43,22),
(5,44,22),
(5,37,22),
(5,50,22),
(6,NULL,22),
(6,12,22),
(6,1,22);

UPDATE SeasonIHPGeographyConfiguration
SET createdOn = CURRENT_TIMESTAMP,
createdBy = 1,
modifiedOn = CURRENT_TIMESTAMP,
modifiedBy = 1;

SET FOREIGN_KEY_CHECKS= 1;

		

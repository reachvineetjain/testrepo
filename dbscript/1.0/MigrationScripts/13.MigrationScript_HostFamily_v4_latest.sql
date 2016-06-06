-- USE `cci_gh_go_login`;

SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE Airport;
TRUNCATE TABLE `HostFamilyApplicationCategories`;
TRUNCATE TABLE `HostFamilyStatus`;
TRUNCATE TABLE `HostFamily`;
TRUNCATE TABLE `HostFamilyAirport`;
TRUNCATE TABLE `HostFamilyAnnouncement`;
TRUNCATE TABLE `HostFamilyAnnouncementResults`;
TRUNCATE TABLE `HostFamilyCommunity`;
TRUNCATE TABLE `HostFamilyDetail`;
TRUNCATE TABLE `HostFamilyDocument`;
-- TRUNCATE TABLE `HostFamilyEmployment`;
TRUNCATE TABLE `HostFamilyHome`;
TRUNCATE TABLE `HostFamilyInterview`;
TRUNCATE TABLE `HostFamilyLookup`;
TRUNCATE TABLE `HostFamilyMember`;
TRUNCATE TABLE `HostFamilyNote`;
TRUNCATE TABLE `HostFamilyNoteTopics`;
TRUNCATE TABLE `HostFamilyParticipant`;
TRUNCATE TABLE `HostFamilyParticipantHistory`;
TRUNCATE TABLE `HostFamilyReference`;
TRUNCATE TABLE `HostFamilySeason`;
TRUNCATE TABLE `HostFamilySeasonNoteTopics`;
TRUNCATE TABLE `HostFamilySeasonNote`;
TRUNCATE TABLE `HostFamilySeasonCategories`;
TRUNCATE TABLE `HostFamilyInquiry`;
TRUNCATE TABLE `HostFamilyPotentialReference`;
TRUNCATE TABLE `HostFamilyContactHistory`;
TRUNCATE TABLE `HostFamilyPet`;
TRUNCATE TABLE `HostFamilyPetType`;
TRUNCATE TABLE `Relationship`;
TRUNCATE TABLE `HostFamilyPhotosType`;
TRUNCATE TABLE `HostFamilyPhotos`;
TRUNCATE TABLE `HostFamilyFinalEvaluation`;
TRUNCATE TABLE `MoveReason`;
TRUNCATE TABLE `HostFamilyEvaluation`;
TRUNCATE TABLE `HostFamilyBackground`;
TRUNCATE TABLE `HostFamilyGeneralQuestions`;
TRUNCATE TABLE `HostFamilyMileageCheck`;
TRUNCATE TABLE `HostFamilyPermissions`;
-- TRUNCATE TABLE `HostFamilyResources`;

-- ----------------------------------------------------------------------------------------------------------------------------------------------
-- Insert into Airport
-- ----------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO `Airport`(`airportId`,`airportName`,`airportCity`,`airportCode`,`active`,`isInternational`)
SELECT `AirportID`,`AirportName`,`AirportCity`,`AirportCode`,`Active`,`International` FROM `cci_go`.`Airport` WHERE AirportID <> 0;

-- ----------------------------------------------------------------------------------------------------------------------------------------------
-- Insert into HostFamilyApplicationCategories
-- ----------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO `HostFamilyApplicationCategories`(`hostFamilyApplicationCategoryName`)
VALUES ('Family basics'),
       ('Family lifestyle'),
       ('House & home'),
       ('Community & school'),
       ('Why host?'),
       ('Photo album'),
       ('References'),
       ('Background check'),
       ('Submit application');
	   
-- ------------------------------------------------------------------------------------------------------------------------------
-- Insert into HostFamilyStatus
-- ------------------------------------------------------------------------------------------------------------------------------

INSERT INTO `HostFamilyStatus` (`hostFamilyStatusId`,`hostFamilyStatusName`,`active`)
SELECT `hostFamilyStatusId`,`hostFamilyStatusName`,`active` FROM `cci_go`.`HostFamilyStatus` WHERE hostFamilyStatusId <> 0;

INSERT INTO `HostFamilyStatus` (`hostFamilyStatusName`,`active`)
SELECT `HostFamilySeasonStatusName`,`Active` FROM cci_go.`HostFamilySeasonStatus` WHERE `HostFamilySeasonStatusID` <> 0;

UPDATE `HostFamilyStatus` SET `isSeasonStatus`=1 WHERE  hostFamilyStatusId > 4;

INSERT INTO `HostFamilyStatus`(`hostFamilyStatusName`,`active`)
SELECT `HostFamilyParticipantStatusName`,`Active` FROM `cci_go`.`HostFamilyParticipantStatus` WHERE HostFamilyParticipantStatusID <> 0;

UPDATE `HostFamilyStatus` SET `isParticipantStatus`=1 WHERE hostFamilyStatusId > 13;

-- --------------------------------------------------------------------------------------------------------
-- Insert into HostFamily
-- --------------------------------------------------------------------------------------------------------
-- The following code would import data which is common for Field Staff & Host Family i.e., FS also acting as HF --


DELETE FROM cci_go.`GoMapping` WHERE `loginTypeId`=4;

/*ALTER TABLE `cci_go`.`GoMapping` ADD COLUMN secCciId INT,
                                 ADD COLUMN secLoginId INT,
                                 ADD COLUMN isHostFamily TINYINT(1) DEFAULT 0;*/
                                 
UPDATE `cci_go`.`GoMapping` gm,cci_go.FieldStaff fs,cci_go.HostFamily hf
SET gm.secCciId = hf.`HostFamilyID`,
    gm.secLoginId = hf.`LoginID`,
    gm.isHostFamily = 1
WHERE fs.`FieldStaffID` = gm.cciId
AND fs.MyHostFamilyID = hf.HostFamilyID
AND fs.MyHostFamilyID != 0
AND gm.loginTypeId = 3;

INSERT INTO `LoginUserType` (`loginId`,`userTypeId`,defaultUserType,active,createdOn,createdBy,modifiedOn,modifiedBy) 
SELECT l.loginId,4,0,1,CURRENT_TIMESTAMP,18,CURRENT_TIMESTAMP,18 FROM Login l
INNER JOIN `cci_go`.`GoMapping` gm ON gm.loginId = l.loginId AND isHostFamily = 1;

/*INSERT INTO `LoginHistory`    (`loggedOn`,`loginId`,`ipAddress`)
SELECT     LastLoggedIn,LoginID,LastIPAddress
FROM       `cci_go`.`UserLogin` WHERE loginTypeId =8;*/

-- ======count mismatch ===============
INSERT INTO cci_go.`GoMapping` (`cciId`,`loginId`)
SELECT `HostFamilyID`,`LoginID` FROM cci_go.`HostFamily` hf WHERE (`HostFamilyID`,`LoginID`) NOT IN
(SELECT gm.`secCciId`,gm.`secLoginId` FROM cci_go.`GoMapping` gm WHERE hf.HostFamilyID = gm.`secCciId`AND hf.loginId = gm.`secLoginId`
AND gm.`isHostFamily` = 1)
AND HostFamilyID <> 0  ORDER BY `HostFamilyID` ASC ;

UPDATE cci_go.`GoMapping` SET `loginTypeId` = 4 WHERE `loginTypeId` IS NULL;
UPDATE cci_go.`GoMapping` SET `loginTypeName`= 'HostFamily' WHERE `loginTypeId` = 4;

INSERT INTO GoIdSequence 
SELECT `goId` FROM `cci_go`.`GoMapping` WHERE `LoginTypeId` = 4;

-- DELETE FROM LoginHistory WHERE loginId  IN (SELECT `loginId` FROM cci_go.`GoMapping` WHERE `loginTypeId` = 4 AND `loginTypeName`='Host Family');
DELETE FROM Login WHERE loginId  IN (SELECT `loginId` FROM cci_go.`GoMapping` WHERE `loginTypeId` = 4 AND `loginTypeName`='Host Family');

-- Duplicate and blank emails
INSERT IGNORE INTO `Login` (`loginId`,goId,`loginName`,`password`,`keyValue`,email,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`,active)
SELECT l.LoginID,gm.goId,l.LoginName,l.Password,l.PasswordSalt,
IF(hf.Email='' , IF(LOCATE('@', l.LoginName)> 1, l.LoginName, hf.Email), hf.Email ) AS Email,l.CreatedOn,l.CreatedBy,l.ModifiedOn,l.ModifiedBy,
hf.Active
FROM cci_go.UserLogin l 
INNER JOIN cci_go.GoMapping gm ON l.loginId = gm.loginId AND gm.loginTypeId = 4
INNER JOIN cci_go.HostFamily hf ON l.loginId = hf.LoginID
WHERE l.LoginID <> 0 AND l.LoginTypeID = 8 ;

-- =============================================================================
-- =============================================================================
-- Error Code: 1452
-- Cannot add or update a child row: a foreign key constraint fails (`cci_gh_go_dev`.`loginusertype`, CONSTRAINT `FK_LoginUserType_Login` FOREIGN KEY (`loginId`) REFERENCES `login` (`loginId`) ON DELETE NO ACTION ON UPDATE NO ACTION)
INSERT INTO `LoginUserType` (`loginId`,defaultUserType,active,createdOn,createdBy,modifiedOn,modifiedBy,userTypeId) 
SELECT ul.LoginID,1,1,CURRENT_TIMESTAMP,18,CURRENT_TIMESTAMP,18,4 FROM cci_go.UserLogin ul 
INNER JOIN `cci_go`.`GoMapping` gm ON gm.loginId = ul.loginId AND gm.loginTypeId = 4
WHERE ul.LoginID <> 0 AND ul.`LoginTypeID` = 8;

-- ALternate Query
INSERT INTO `LoginUserType` (`loginId`,defaultUserType,active,createdOn,createdBy,modifiedOn,modifiedBy,userTypeId) 
SELECT ul.LoginID,1,1,CURRENT_TIMESTAMP,18,CURRENT_TIMESTAMP,18,4 FROM cci_go.UserLogin ul 
INNER JOIN `cci_go`.`GoMapping` gm ON gm.loginId = ul.loginId AND gm.loginTypeId = 4
INNER JOIN Login l ON l.loginId = gm.loginId
WHERE ul.LoginID <> 0 AND ul.`LoginTypeID` = 8;
-- =============================================================================
-- =============================================================================


-- --------------------------------------------------------------------------------------------------------
-- Insert into HostFamily
-- --------------------------------------------------------------------------------------------------------

INSERT INTO `HostFamily`(`hostFamilyGoId`)
SELECT `goId` FROM `cci_go`.`GoMapping` WHERE isHostFamily = 1;

INSERT INTO `HostFamily`(`hostFamilyGoId`)
SELECT `goId` FROM `cci_go`.`GoMapping` WHERE `LoginTypeId` = 4;

UPDATE `cci_go`.`HostFamily` hfo, `HostFamily` hfn,`cci_go`.`GoMapping` gm
SET hfn.`hostFamilyStatusId` = hfo.`HostFamilyStatusID`,
hfn.`firstName` = hfo.`FirstName`,
hfn.`lastName` = hfo.`LastName`,
hfn.`phone` = hfo.`Phone`,
hfn.`mailingAddress` = hfo.`MailingAddress`,
hfn.`mailingCity` = hfo.`MailingCity`,
hfn.`mailingStateId` = hfo.`MailingStateID`,
hfn.`mailingZipCode` = hfo.`MailingPostalCode`,
hfn.`physicalAddress` = hfo.`CurrentAddress`,
hfn.`physicalCity` = hfo.`CurrentCity`,
hfn.`physicalStateId` = hfo.`CurrentStateID`,
hfn.`physicalCountryId` = hfo.`CurrentCountryID`,
hfn.`physicalZipCode` = hfo.`CurrentPostalCode`,
hfn.`emergencyContact` = hfo.`EmergencyContact`,
hfn.`emergencyPhone` = hfo.`EmergencyPhone`,
-- hfn.`currentSeasonId` = hfo.`CurrentSeasonID`,
hfn.`receiveEmails` = hfo.`ReceiveEmails`,
-- hfn.`originalData` = hfo.`OriginalData`,
hfn.`isBlacklisted` = hfo.`IsBlacklisted`,
hfn.`isDoNotContact` = hfo.`IsDoNotContact`,
hfn.`createdOn` = hfo.`CreatedOn`,
hfn.`createdBy` = hfo.`CreatedBy`,
hfn.`modifiedOn` = hfo.`ModifiedOn`,
hfn.`modifiedBy` = hfo.`ModifiedBy`,
hfn.`active` = hfo.`Active`,
hfn.`liveAlone` = hfo.`LiveAlone`,
hfn.`hasHomeBusiness` = hfo.`HasHomeBusiness`,
hfn.`homeBusinessType` = hfo.`HomeBusinessType`,
hfn.`skipIntroScreen` = hfo.`SkipIntroScreen`
WHERE hfn.`hostFamilyGoId` = gm.`goId`
AND gm.`loginId` = hfo.`LoginID`
AND gm.`cciId` = hfo.`HostFamilyID`
AND gm.`loginTypeId` = 4;

UPDATE `cci_go`.`HostFamily` hfo, `HostFamily` hfn,`cci_go`.`GoMapping` gm
SET hfn.`hostFamilyStatusId` = hfo.`HostFamilyStatusID`,
hfn.`firstName` = hfo.`FirstName`,
hfn.`lastName` = hfo.`LastName`,
hfn.`phone` = hfo.`Phone`,
hfn.`mailingAddress` = hfo.`MailingAddress`,
hfn.`mailingCity` = hfo.`MailingCity`,
hfn.`mailingStateId` = hfo.`MailingStateID`,
hfn.`mailingZipCode` = hfo.`MailingPostalCode`,
hfn.`physicalAddress` = hfo.`CurrentAddress`,
hfn.`physicalCity` = hfo.`CurrentCity`,
hfn.`physicalStateId` = hfo.`CurrentStateID`,
hfn.`physicalCountryId` = hfo.`CurrentCountryID`,
hfn.`physicalZipCode` = hfo.`CurrentPostalCode`,
hfn.`emergencyContact` = hfo.`EmergencyContact`,
hfn.`emergencyPhone` = hfo.`EmergencyPhone`,
-- hfn.`currentSeasonId` = hfo.`CurrentSeasonID`,
hfn.`receiveEmails` = hfo.`ReceiveEmails`,
-- hfn.`originalData` = hfo.`OriginalData`,
hfn.`isBlacklisted` = hfo.`IsBlacklisted`,
hfn.`isDoNotContact` = hfo.`IsDoNotContact`,
hfn.`createdOn` = hfo.`CreatedOn`,
hfn.`createdBy` = hfo.`CreatedBy`,
hfn.`modifiedOn` = hfo.`ModifiedOn`,
hfn.`modifiedBy` = hfo.`ModifiedBy`,
hfn.`active` = hfo.`Active`,
hfn.`liveAlone` = hfo.`LiveAlone`,
hfn.`hasHomeBusiness` = hfo.`HasHomeBusiness`,
hfn.`homeBusinessType` = hfo.`HomeBusinessType`,
hfn.`skipIntroScreen` = hfo.`SkipIntroScreen`
WHERE hfn.`hostFamilyGoId` = gm.`goId`
AND gm.`secLoginId` = hfo.`LoginID`
AND gm.`secCciId` = hfo.`HostFamilyID`
AND gm.isHostFamily = 1;

UPDATE `HostFamily` 
SET `mailingStateId`= NULL
WHERE `mailingStateId`= 0;

UPDATE `HostFamily` 
SET `physicalStateId`= NULL
WHERE `physicalStateId` = 0; 

UPDATE `HostFamily` 
SET `physicalCountryId`= NULL
WHERE `physicalCountryId` = 0; 

/*update `HostFamily` 
set `currentCountryId`= null
where `currentCountryId` = 0; 
*/

UPDATE `HostFamily`
SET mailingAddressSameAsCurrentAddress = 1
WHERE mailingAddress = physicalAddress
AND mailingCity = physicalCity
AND mailingStateId = physicalStateId
AND mailingZipCode = physicalZipCode;

UPDATE `HostFamily` hfa, `cci_go`.`SeasonMapping` sm,cci_go.HostFamily hfo,cci_go.GoMapping gm
SET hfa.`currentSeasonId` = sm.newSeasonId
WHERE sm.`oldSeasonId` = hfo.CurrentSeasonID
AND gm.goId = hfa.hostFamilyGoId AND gm.`LoginTypeId` = 4 
AND gm.cciId = hfo.HostFamilyID;

UPDATE `HostFamily` hfa, `cci_go`.`SeasonMapping` sm,cci_go.HostFamily hfo,cci_go.GoMapping gm
SET hfa.`currentSeasonId` = sm.newSeasonId
WHERE sm.`oldSeasonId` = hfo.CurrentSeasonID
AND gm.goId = hfa.hostFamilyGoId  AND gm.isHostFamily = 1
AND gm.`secCciId` = hfo.HostFamilyID;


-- -------------------------------------------------------------------------------------------------------------------------------
-- Insert into HostFamilyAirport
-- -------------------------------------------------------------------------------------------------------------------------------

INSERT INTO `HostFamilyAirport`(hostFamilyAirportId,`hostFamilyGoId`,airportId,createdBy,createdOn,modifiedBy,modifiedOn,active)
SELECT hfao.`HostFamilyAirportID`,hf.`hostFamilyGoId`,hfao.`AirportID`,hfao.`CreatedBy`,hfao.`CreatedOn`,hfao.`ModifiedBy`,hfao.`ModifiedOn`,hfao.`Active` 
FROM  HostFamily hf
INNER JOIN cci_go.GoMapping gm ON gm.goId = hf.`hostFamilyGoId` 
INNER JOIN `cci_go`.`HostFamilyAirport` hfao ON gm.`cciId`=hfao.`HostFamilyID`
AND gm.`LoginTypeId` = 4; 

INSERT INTO `HostFamilyAirport`(hostFamilyAirportId,`hostFamilyGoId`,airportId,createdBy,createdOn,modifiedBy,modifiedOn,active)
SELECT hfao.`HostFamilyAirportID`,hf.`hostFamilyGoId`,hfao.`AirportID`,hfao.`CreatedBy`,hfao.`CreatedOn`,hfao.`ModifiedBy`,hfao.`ModifiedOn`,hfao.`Active` 
FROM  HostFamily hf
INNER JOIN cci_go.GoMapping gm ON gm.goId = hf.`hostFamilyGoId` 
INNER JOIN `cci_go`.`HostFamilyAirport` hfao ON gm.`cciId`=hfao.`HostFamilyID`
AND gm.isHostFamily = 1;

UPDATE `cci_go`.`HostFamily` hf,`HostFamilyAirport` hfan,`cci_go`.`GoMapping` gm
SET hfan.distanceToAirport = hf.DistanceToAirport
WHERE hfan.`hostFamilyGoId`= gm.`goId`
AND gm.`cciId`= hf.`HostFamilyId`
AND gm.`LoginTypeId` = 4;

UPDATE `cci_go`.`HostFamily` hf,`HostFamilyAirport` hfan,`cci_go`.`GoMapping` gm
SET hfan.distanceToAirport = hf.DistanceToAirport
WHERE hfan.`hostFamilyGoId`= gm.`goId`
AND gm.`cciId`= hf.`HostFamilyId`
AND gm.isHostFamily = 1;

-- --------------------------------------------------------------------------------------------------------------------------------
-- Insert into HostFamilyAnnouncement
-- --------------------------------------------------------------------------------------------------------------------------------
-- Need to add hostFamilyGoId into HostFamilyAnnouncement
INSERT INTO `HostFamilyAnnouncement` (`hostFamilyAnnouncementId`,`announcement`,`title`,`active`,`archived`,
`currentlyHosting`,`unplaced`,`createdBy`,`createdOn`,`modifiedBy`,`modifiedOn`) 
SELECT `HostFamilyAnnouncementID`,`Announcement`,`Title`,`Active`,`Archived`,`CurrentlyHosting`,`Unplaced`,`CreatedBy`,
`CreatedOn`,`ModifiedBy`,`ModifiedOn` FROM `cci_go`.`HostFamilyAnnouncement` WHERE hostFamilyAnnouncementId <> 0;

UPDATE `HostFamilyAnnouncement` hfa, `cci_go`.`SeasonMapping` sm ,cci_go.HostFamilyAnnouncement hfao,cci_go.GoMapping gm
SET hfa.`seasonId` = sm.`newSeasonId`,
    hfa.hostFamilyGoId = gm.goId
WHERE sm.`oldSeasonId` = hfao.`SeasonID`
-- and gm.goId = hfa.hostFamilyGoId 
AND gm.`LoginTypeId` = 4;
AND gm.cciId = hfao.HostFamilyID;


UPDATE `HostFamilyAnnouncement` hfa, `cci_go`.`SeasonMapping` sm ,cci_go.HostFamilyAnnouncement hfao,cci_go.GoMapping gm
SET hfa.`seasonId` = sm.`newSeasonId`
WHERE sm.`oldSeasonId` = hfao.`SeasonID`
AND gm.isHostFamily = 1;

UPDATE `HostFamilyAnnouncement` hfa, `cci_go`.`SeasonMapping` sm 
SET hfa.`departmentProgramId` = sm.`departmentProgramId`
WHERE hfa.`seasonId` = sm.`newSeasonId`;

-- -------------------------------------------------------------------------------------------------------------------------------------------------
-- Insert into HostFamilySeason
-- -------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO `HostFamilySeason`(`hostFamilyGoId`,hostFamilySeasonId,seasonId,departmentProgramId,paymentScheduleId,learnAboutCCIMethod,
localNewspaperName,recommendHost,hostRecommendationsName,hostRecommendationsPhone,agreeToTerms,signature,rejectionReason,hasNoChildren,
createdOn,createdBy,modifiedOn,modifiedBy,active,applicationSubmittedDate,applicationApprovedDate,isDoublePlacement)
SELECT gm.`goId`,hfso.`HostFamilySeasonID`,sm.`newSeasonId`,sm.departmentProgramId,hfso.`PaymentScheduleID`,hfso.`LearnAboutCCIMethod`,
hfso.`LocalNewspaperName`,hfso.`RecommendHost`,hfso.`HostRecommendationsName`,hfso.`HostRecommendationsPhone`,hfso.`AgreeToTerms`,
hfso.`Signature`,hfso.`RejectionReason`,hfso.`HasNoChildren`,hfso.`CreatedOn`,hfso.`CreatedBy`,hfso.`ModifiedOn`,hfso.`ModifiedBy`,
hfso.`Active`,hfso.`ApplicationSubmittedDate`,hfso.`ApplicationApprovedDate`,hfso.`IsDoublePlacement`
FROM `cci_go`.`GoMapping` gm 
INNER JOIN cci_go.HostFamilySeason hfso ON hfso.HostFamilyID = gm.cciId 
INNER JOIN  `cci_go`.`SeasonMapping` sm ON hfso.SeasonID = sm.oldSeasonId
WHERE gm.`LoginTypeId` = 4 AND hfso.`HostFamilySeasonID` <> 0;

INSERT INTO `HostFamilySeason`(`hostFamilyGoId`,hostFamilySeasonId,seasonId,departmentProgramId,paymentScheduleId,learnAboutCCIMethod,
localNewspaperName,recommendHost,hostRecommendationsName,hostRecommendationsPhone,agreeToTerms,signature,rejectionReason,hasNoChildren,
createdOn,createdBy,modifiedOn,modifiedBy,active,applicationSubmittedDate,applicationApprovedDate,isDoublePlacement)
SELECT gm.`goId`,hfso.`HostFamilySeasonID`,sm.`newSeasonId`,sm.departmentProgramId,hfso.`PaymentScheduleID`,hfso.`LearnAboutCCIMethod`,
hfso.`LocalNewspaperName`,hfso.`RecommendHost`,hfso.`HostRecommendationsName`,hfso.`HostRecommendationsPhone`,hfso.`AgreeToTerms`,
hfso.`Signature`,hfso.`RejectionReason`,hfso.`HasNoChildren`,hfso.`CreatedOn`,hfso.`CreatedBy`,hfso.`ModifiedOn`,hfso.`ModifiedBy`,
hfso.`Active`,hfso.`ApplicationSubmittedDate`,hfso.`ApplicationApprovedDate`,hfso.`IsDoublePlacement`
FROM `cci_go`.`GoMapping` gm 
INNER JOIN cci_go.HostFamilySeason hfso ON hfso.HostFamilyID = gm.cciId 
INNER JOIN  `cci_go`.`SeasonMapping` sm ON hfso.SeasonID = sm.oldSeasonId
WHERE gm.isHostFamily = 1 AND hfso.`HostFamilySeasonID` <> 0;

UPDATE `cci_go`.`HostFamilySeason` hfso,`HostFamilySeason` hfsn,
`cci_go`.`GoMapping` gm,`cci_go`.`GoMapping` gm1
SET hfsn.areaRepresentativeId = gm1.`goId`
WHERE  hfsn.hostFamilyGoId = gm.`goId`
AND gm.cciId = hfso.HostFamilyID 
AND hfsn.hostFamilySeasonId = hfso.HostFamilySeasonID
AND gm1.cciId = hfso.AreaRepresentativeID
AND gm1.loginTypeId = 3 AND gm.loginTypeId = 4;

UPDATE `cci_go`.`HostFamilySeason` hfso,`HostFamilySeason` hfsn,
`cci_go`.`GoMapping` gm,`cci_go`.`GoMapping` gm1
SET hfsn.areaRepresentativeId = gm1.`goId`
WHERE  hfsn.hostFamilyGoId = gm.`goId`
AND gm.`secCciId` = hfso.HostFamilyID 
AND hfsn.hostFamilySeasonId = hfso.HostFamilySeasonID
AND gm1.cciId = hfso.AreaRepresentativeID
AND gm1.loginTypeId = 3 AND gm.isHostFamily = 1;

UPDATE `cci_go`.`HostFamilySeason` hfso,`HostFamilySeason` hfsn,
`cci_go`.`GoMapping` gm,`cci_go`.`GoMapping` gm1
SET hfsn.regionalManagerId = gm1.`goId`
WHERE  hfsn.hostFamilyGoId = gm.`goId`
AND gm.cciId = hfso.HostFamilyID 
AND hfsn.hostFamilySeasonId = hfso.HostFamilySeasonID
AND gm1.cciId = hfso.RegionalManagerID
AND gm1.loginTypeId = 3 AND gm.loginTypeId = 4;

UPDATE `cci_go`.`HostFamilySeason` hfso,`HostFamilySeason` hfsn,
`cci_go`.`GoMapping` gm,`cci_go`.`GoMapping` gm1
SET hfsn.regionalManagerId = gm1.`goId`
WHERE  hfsn.hostFamilyGoId = gm.`goId`
AND gm.secCciId = hfso.HostFamilyID 
AND hfsn.hostFamilySeasonId = hfso.HostFamilySeasonID
AND gm1.cciId = hfso.RegionalManagerID
AND gm1.loginTypeId = 3 AND gm.isHostFamily = 1;

UPDATE `cci_go`.`HostFamilySeason` hfso,`HostFamilySeason` hfsn,
`cci_go`.`GoMapping` gm,`cci_go`.`GoMapping` gm1
SET hfsn.regionalDirectorId = gm1.`goId`
WHERE  hfsn.hostFamilyGoId = gm.`goId`
AND gm.cciId = hfso.HostFamilyID 
AND hfsn.hostFamilySeasonId = hfso.HostFamilySeasonID
AND gm1.cciId = hfso.RegionalDirectorID
AND gm1.loginTypeId = 3 AND gm.loginTypeId = 4;

UPDATE `cci_go`.`HostFamilySeason` hfso,`HostFamilySeason` hfsn,
`cci_go`.`GoMapping` gm,`cci_go`.`GoMapping` gm1
SET hfsn.regionalDirectorId = gm1.`goId`
WHERE  hfsn.hostFamilyGoId = gm.`goId`
AND gm.secCciId = hfso.HostFamilyID 
AND hfsn.hostFamilySeasonId = hfso.HostFamilySeasonID
AND gm1.cciId = hfso.RegionalDirectorID
AND gm1.loginTypeId = 3 AND gm.isHostFamily = 1;

UPDATE `cci_go`.`HostFamilySeason` hfso,`HostFamilySeason` hfsn
SET hfsn.hostFamilySeasonStatusId = 5
WHERE hfso.HostFamilySeasonStatusID = 1
AND hfsn.`hostFamilySeasonId` = hfso.`HostFamilySeasonID`;

UPDATE `cci_go`.`HostFamilySeason` hfso,`HostFamilySeason` hfsn
SET hfsn.hostFamilySeasonStatusId = 6
WHERE hfso.HostFamilySeasonStatusID = 2
AND hfsn.`hostFamilySeasonId` = hfso.`HostFamilySeasonID`;

UPDATE `cci_go`.`HostFamilySeason` hfso,`HostFamilySeason` hfsn
SET hfsn.hostFamilySeasonStatusId = 7
WHERE hfso.HostFamilySeasonStatusID = 3
AND hfsn.`hostFamilySeasonId` = hfso.`HostFamilySeasonID`;

UPDATE `cci_go`.`HostFamilySeason` hfso,`HostFamilySeason` hfsn
SET hfsn.hostFamilySeasonStatusId = 8
WHERE hfso.HostFamilySeasonStatusID = 4
AND hfsn.`hostFamilySeasonId` = hfso.`HostFamilySeasonID`;

UPDATE `cci_go`.`HostFamilySeason` hfso,`HostFamilySeason` hfsn
SET hfsn.hostFamilySeasonStatusId = 9
WHERE hfso.HostFamilySeasonStatusID = 5
AND hfsn.`hostFamilySeasonId` = hfso.`HostFamilySeasonID`;

UPDATE `cci_go`.`HostFamilySeason` hfso,`HostFamilySeason` hfsn
SET hfsn.hostFamilySeasonStatusId = 10
WHERE hfso.HostFamilySeasonStatusID = 7
AND hfsn.`hostFamilySeasonId` = hfso.`HostFamilySeasonID`;

UPDATE `cci_go`.`HostFamilySeason` hfso,`HostFamilySeason` hfsn
SET hfsn.hostFamilySeasonStatusId = 11
WHERE hfso.HostFamilySeasonStatusID = 8
AND hfsn.`hostFamilySeasonId` = hfso.`HostFamilySeasonID`;

UPDATE `cci_go`.`HostFamilySeason` hfso,`HostFamilySeason` hfsn
SET hfsn.hostFamilySeasonStatusId = 12
WHERE hfso.HostFamilySeasonStatusID = 9
AND hfsn.`hostFamilySeasonId` = hfso.`HostFamilySeasonID`;

UPDATE `cci_go`.`HostFamilySeason` hfso,`HostFamilySeason` hfsn
SET hfsn.hostFamilySeasonStatusId = 13
WHERE hfso.HostFamilySeasonStatusID = 10
AND hfsn.`hostFamilySeasonId` = hfso.`HostFamilySeasonID`;

UPDATE `cci_go`.`HostFamilySeason` hfso,`HostFamilySeason` hfsn
SET hfsn.hostFamilySeasonStatusId = NULL
WHERE hfso.HostFamilySeasonStatusID = 0
AND hfsn.`hostFamilySeasonId` = hfso.`HostFamilySeasonID`;

-- --------------------------------------------------------------------------------------------------------------------------------------
-- Insert into HostFamilyMember
-- --------------------------------------------------------------------------------------------------------------------------------------
-- Need to add employer varchar(100) into HostFamilyMember --
-- LivingAtHome need to delete this

INSERT INTO `HostFamilyMember`(`hostFamilyMemberId`,`hostFamilySeasonId`,`firstName`,`lastName`,`birthDate`,`genderId`,
`relationship`,`interests`,`isHostParent`,educationLevel,`communityInvolvement`,`isFamilyChild`,livingAtHome,`residencyTime`
`reasonForRejection`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`,
`active`)
SELECT `HostFamilyMemberID`,`HostFamilySeasonID`,`FirstName`,`LastName`,`BirthDate`,`GenderID`,`Relationship`,`Interests`,
`IsHostParent`,`EducationLevel`,`CommunityInvolvement`,IsFamilyChild,`LivingAtHome`,`ResidencyTime`,
`ReasonForRejection`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy`,`Active`,
FROM `cci_go`.`HostFamilyMember` WHERE HostFamilyMemberID <> 0;

/*INSERT INTO `HostFamilyMember`(`hostFamilyMemberId`,`hostFamilySeasonId`,`firstName`,`lastName`,`birthDate`,`genderId`,
`isSingleAdult`,`interests`,`relationship`,`isHostParent`,`isFamilyChild`,
`reasonForRejection`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`,
`active`,`residencyTime`,`educationLevel`,`communityInvolvement`)
SELECT `HostFamilyMemberID`,`HostFamilySeasonID`,`FirstName`,`LastName`,`BirthDate`,`GenderID`,`LivingAtHome`,`Interests`,
`Relationship`,`IsHostParent`,`IsFamilyChild`,`ReasonForRejection`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy`,`Active`,
`ResidencyTime`,`EducationLevel`,`CommunityInvolvement`
FROM `cci_go`.`HostFamilyMember` WHERE HostFamilyMemberID <> 0;*/

-- `memberEmail`,`memberPhone` values will be null because these are new fields
-- `employed`,,`contactName1`,`phone1`,`employer2`,`jobTitle2`,`contactName2`,`phone2`,`haveAnotherJob`,

employmentType,employer2,
jobTitle2,contactName2,phone2,haveAnotherJob,livingAtHomeExplanation,`isSingleAdult`,

UPDATE HostFamilyMember hfm,cci_go.HostFamilyEmployment hfe
SET 
hfm.contactName1 = hfe.ContactName,
hfm.phone1 = hfe.PhoneNumber,
hfm.`jobTitle1` = hfe.JobTitle,
hfm.`employer1` = hfe.EmployerName
WHERE hfm.hostFamilySeasonId=hfe.HostFamilySeasonId;


UPDATE HostFamilyMember SET employed=1 WHERE jobTitle IS NOT NULL;

-- UPDATE HostFamilyMember hfm,cci_go.HostFamilyEmployment hfe
-- SET hfm.jobTitle=hfe.JobTitle WHERE hfm.jobTitle IS NOT NULL AND hfm.hostFamilySeasonId=hfe.HostFamilySeasonId;

-- ----------------------------------------------------------------------------------------------------------------------------------
-- Insert into HostFamilyLookup
-- ----------------------------------------------------------------------------------------------------------------------------------

INSERT INTO `HostFamilyLookup`(`hostFamilyLookUpId`,`displayValue`,`displayType`,`active`)
SELECT `HostFamilyLookupID`,`DisplayValue`,`DisplayType`,`Active` FROM `cci_go`.`HostFamilyLookup` WHERE HostFamilyLookupID <> 0;

-- ----------------------------------------------------------------------------------------------------------------------------------
-- Insert into HostFamilyNoteTopics
-- ----------------------------------------------------------------------------------------------------------------------------------
INSERT INTO HostFamilyNoteTopics (`hostFamilyNoteTopicsId`,hostFamilyGoId)
SELECT fsn.HostFamilyNoteID,gm.goId FROM cci_go.GoMapping gm 
INNER JOIN cci_go.HostFamilyNote fsn 
ON fsn.HostFamilyID = gm.cciId AND gm.loginTypeId = 4; 

INSERT INTO HostFamilyNoteTopics (`hostFamilyNoteTopicsId`,hostFamilyGoId)
SELECT fsn.HostFamilyNoteID,gm.goId FROM cci_go.GoMapping gm 
INNER JOIN cci_go.HostFamilyNote fsn 
ON fsn.HostFamilyID = gm.`secCciId`AND gm.isHostFamily=1;

-- ----------------------------------------------------------------------------------------------------------------------------------
-- Insert into HostFamilyNote
-- ----------------------------------------------------------------------------------------------------------------------------------
INSERT INTO `HostFamilyNote`(`hostFamilyNoteTopicsId`,`hostFamilyGoId`,`note`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT fsnt.HostFamilyNoteTopicsId,fsnt.hostFamilyGoId,fsno.`Note`,fsno.`CreatedOn`,fsno.`CreatedBy`,fsno.`ModifiedOn`,fsno.`ModifiedBy`
FROM HostFamilyNoteTopics fsnt
INNER JOIN `cci_go`.`HostFamilyNote` fsno
WHERE fsnt.hostFamilyNoteTopicsId = fsno.`HostFamilyNoteID`;

UPDATE `HostFamilyNote` 
SET `hasRead` = 1;

-- ----------------------------------------------------------------------------------------------------------------------------------
-- Insert into HostFamilyInterview
-- ----------------------------------------------------------------------------------------------------------------------------------

INSERT INTO `HostFamilyInterview`(`hostFamilyInterviewId`,`hostFamilySeasonId`,`reasonsForHosting`,
`childrenFeelingsAboutHosting`,`roomSharedWithDescription`,`hasFamilyHostedBefore`,`previousHostingDescription`,
`financialSituation`,`homeCondition`,`religiousBackground`,`discussedPlacement`,`interviewDate`,`interviewIsAtHome`,
`hostingCommitment`,`isStarted`,`isCompleted`,`createdBy`,`createdOn`,`modifiedBy`,
`modifiedOn`)
SELECT `HostFamilyInterviewID`,`HostFamilySeasonID`,`ReasonsForHosting`,`ChildrensFeelingsAboutHosting`,`RoomSharedWithDescription`,
`HasFamilyHostedBefore`,`PreviousHostingDescription`,`FinancialSituation`,`HomeCondition`,`ReligiousBackground`,`DiscussedPlacement`,
`InterviewDate`,`InterviewIsAtHome`,`HostingCommitment`,`IsStarted`,`IsCompleted`,`CreatedBy`,`CreatedOn`,`ModifiedBy`,`ModifiedOn`
FROM `cci_go`.`HostFamilyInterview` WHERE `HostFamilyInterviewID` <> 0;


-- ----------------------------------------------------------------------------------------------------------------------------------
-- Insert into HostFamilySeasonNoteTopics
-- ----------------------------------------------------------------------------------------------------------------------------------
INSERT INTO HostFamilySeasonNoteTopics (`hostFamilySeasonNoteTopicsId`,hostFamilySeasonId)
SELECT fsn.HostFamilySeasonNoteID,fsn.HostFamilySeasonID FROM cci_go.HostFamilySeasonNote fsn WHERE HostFamilySeasonNoteID <> 0; 

-- ------------------------------------------------------------------------------------------------------------------------------------
-- Insert into HostFamilySeasonNote
-- ------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO `HostFamilySeasonNote`(hostFamilySeasonNoteTopicsId,`hostFamilySeasonId`,`note`,`createdBy`,`createdOn`,
`modifiedBy`,`modifiedOn`)
SELECT hfsnt.hostFamilySeasonNoteTopicsId,hfsnt.hostFamilySeasonId,hfsn.`Note`,hfsn.`CreatedBy`,hfsn.`CreatedOn`,hfsn.`ModifiedBy`,hfsn.`ModifiedOn` 
FROM `cci_go`.`hostfamilyseasonnote` hfsn
INNER JOIN HostFamilySeasonNoteTopics hfsnt ON hfsnt.hostFamilySeasonNoteTopicsId=hfsn.HostFamilySeasonNoteID AND hfsnt.hostFamilySeasonId=hfsn.HostFamilySeasonID;


-- -------------------------------------------------------------------------------------------------------------------------------------
-- Insert into HostFamilyCommunity
-- -------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO `HostFamilyCommunity`(`hostFamilyCommunityId`,`hostFamilySeasonId`,`population`,`cityWebsite`,`nearestCity`,nearestCityPopulation,
`distanceFromCity`,uniquenessAboutCommunity,`placesOfInterest`,`areasToAvoid`,`schoolTravelMethod`,
`distanceToSchool`,`transportationToActivities`,`transportationToActivitiesDetails`,`childrenEnrolled`,
`childrenActivities`,contactACoach,`parentIsTeacher`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`,`active`)
SELECT `HostFamilyCommunityID`,`HostFamilySeasonID`,`Population`,`CityWebsite`,`NearestCity`,`CityPopulation`,
`DistanceFromCity`,`CommunityDetails`,`PlacesOfInterest`,`AreasToAvoid`,`SchoolTravelMethod`,
`DistanceToSchool`,`TransportationToActivities`,`TransportationToActivitiesDetails`,`ChildrenEnrolled`,
`ChildrenActivities`,`ContactedByCoach`,`ParentIsTeacher`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy`,`Active` FROM `cci_go`.`HostFamilyCommunity` WHERE HostFamilyCommunityID <> 0;

/*
volunteeringOpportunitiesCommunity
contactByCoachDetails
parentIsTeacherDetails
involvedInVolunteerService
involvedInVolunteerServiceDetails*/

-- ----------------------------------------------------------------------------------------------------------------------------------------
-- Insert into HostFamilyReference
-- ----------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO `HostFamilyReference`(`hostFamilyReferenceId`,`hostFamilySeasonId`,`dateOfReference`,`firstName`,
`lastName`,`address`,`city`,`usStatesId`,`zipCode`,`phone`,`relationship`,`lengthKnownQuantity`,`lengthKnown`,`knownFamilyMethod`,
`allowOwnChildStay`,`ownChildStayReasons`,`positiveExperiences`,`stability`,`closeness`,`flexibility`,`internationalAwareness`,
`interestVariety`,`communityInvolvement`,`comments`,`completionMethod`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`,`active`,
`visitFrequency`,`lastVisit`,`additionalSupport`,`supportExplanation`,`communityTies`)
SELECT `HostFamilyReferenceID`,`HostFamilySeasonID`,`DateOfReference`,`FirstName`,`LastName`,`Address`,`City`,`StateID`,`PostalCode`,
`Phone`,`Relationship`,`LengthKnownQuantity`,`LengthKnown`,`KnownFamilyMethod`,`AllowOwnChildStay`,`OwnChildStayReasons`,
`PositiveExperiences`,`Stability`,`Closeness`,`Flexibility`,`InternationalAwareness`,`InterestVariety`,`CommunityInvolvement`,
`Comments`,`CompletionMethod`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy`,`Active`,`VisitFrequency`,`LastVisit`,
`AdditionalSupport`,`SupportExplanation`,`CommunityTies` FROM `cci_go`.`HostFamilyReference`
WHERE HostFamilyReferenceID <> 0;

UPDATE HostFamilyReference
SET usStatesId = NULL
WHERE usStatesId = 0;

UPDATE HostFamilyReference SET personNotRelatedToBlood=1;

-- --------------------------------------------------------------------------------------------------------------------------------------------
-- Insert into MoveReason
-- --------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO MoveReason(moveReasonId,moveReasonName,active,changeHomeCode,reasonDescription)
SELECT MoveReasonID,MoveReasonName,Active,ChangeHomeCode,ReasonDescription FROM cci_go.MoveReason WHERE moveReasonId <> 0;

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- Insert into HostFamilyDetail
-- ------------------------------------------------------------------------------------------------------------------------------------------
/*houseHoldType,homeLanguage,otherLaungage,otherReligiousDetails,happyToInviteStudent,inviteStudentForReligiousExperience,
problemWithReligiousDifference,describeDietaryRestrictions,descPaxDietaryRestrictions,familySmokingPlace,*/

INSERT INTO `HostFamilyDetail`(hostFamilyDetailsId,hostFamilySeasonId,familyMemberDescription,adaptCircumtances,
typicalWeekday,typicalWeekend,favouriteWeekend,religiousAffiliation,religiousAttendance,
preferStudentJoins,agreeToServeMeals,
dietaryRestrictions,participantFollowDiet,comfortableHostingDiet,
hasAutoInsurance,familySmoker,drinkAlcohol,illness,illnessDetails,disability,disabilityDetails,crimeConviction,
crimeConvictionDetails,childServicesContact,childServicesContactDetails,incomeRange,receivePublicAssistance,publicAssistanceExplanation,
createdOn,createdBy,modifiedOn,modifiedBy,active) 
SELECT `HostFamilyDetailsID`,`HostFamilySeasonID`,`FamilyMembers`,`AdaptCircumstances`,`TypicalWeekday`,`TypicalWeekend`,
`SpecialWeekend`,`ReligiousAffiliation`,`ReligiousAttendance`,`PreferStudentJoins`,AgreeToServeMeals,DietaryRestrictions,
ParticipantFollowDiet,ComfortableHostingDiet,HasAutoInsurance,FamilySmoker,DrinkAlcohol,Illness,IllnessDetails,Disability,
DisabilityDetails,CrimeConviction,CrimeConvictionDetails,ChildServicesContact,ChildServicesContactDetails,IncomeRange,
ReceiveGovernmentAssistance,GovernmentAssistanceMembers,CreatedOn,CreatedBy,ModifiedOn,ModifiedBy,Active
FROM `cci_go`.`HostFamilyDetail` WHERE HostFamilyDetailsID <> 0;


UPDATE HostFamilyDetail hfd,cci_go.HostFamilyHome hfh
SET hfd.houseHoldType=hfh.HouseHoldActivity
WHERE hfd.hostFamilySeasonId=hfh.HostFamilySeasonID;

-- --------------------------------------------------------------------------------------------------------------------------------------------
-- Insert into HostFamilyHome
-- --------------------------------------------------------------------------------------------------------------------------------------------

bedroomNumber,bathroomNumber,interestedForTwoStudents,sharingBedroomGenderId,sharingAge,isStudentsRoomBasement
exitBasement,studentHomeNeighbourhood,residenceSiteFunctioningBusiness,specifyTypeOfBusiness,otherTypeOfBusiness,
utilities,specialFeaturesInHome,amenities,aspectsOfAmericanLife,extraFacilities,

INSERT INTO `HostFamilyHome`(hostFamilyHomeId,hostFamilySeasonId,homeType,homeLocation,homeDescription,sharesBedroom,sharesBedroomWith,
utilities,amenities,hostingReason,hopeToLearn,extraActivities,localCoordinatorOther,localCoordinatorDetails,hostedOther,hostedOtherDetails,
studentsResponsibilities,createdOn,createdBy,modifiedOn,modifiedBy,active)
SELECT HostFamilyHomeID,HostFamilySeasonID,HomeType,HomeLocation,HomeDescription,SharesBedroom,SharesBedroomWith,
Utilities,Amenities,HostingReason,HopeToLearn,ExtraActivities,LocalCoordinatorOther,LocalCoordinatorDetails,HostedOther,HostedOtherDetails,
ExpectedResponsibilities,CreatedOn,CreatedBy,ModifiedOn,ModifiedBy,Active
FROM `cci_go`.`HostFamilyHome` WHERE HostFamilyHomeID <> 0;

UPDATE HostFamilyHome hfh, cci_go.HostFamily hf,cci_go.HostFamilySeason hfs
SET hfh.residenceSiteFunctioningBusiness = hf.HasHomeBusiness,
    hfh.specifyTypeOfBusiness = hf.HomeBusinessType
WHERE hfh.hostFamilySeasonId = hfs.hostFamilySeasonId
AND hfs.hostFamilyId = hf.hostFamilyId;

-- These fields are new and we will migrate the data into sharing other field --
/*sharingBedroomGenderId
sharingAge
sharingOther
extraFacilities
isStudentsRoomBasement
exitBasement
studentHomeNeighbourhood
specifyTypeOfBusiness
otherTypeOfBusiness
specialFeaturesInHome
isLocalCordinator
petLocation
HasPiano
HasComputer
`dogCount`,`catCount`,`rabbitCount`,`birdCount`,`reptileCount`,
`otherAnimalText`,`otherAnimalCount`
*/

-- --------------------------------------------------------------------------------------------------------------------------------------------
-- Insert into HostFamilyPet
-- --------------------------------------------------------------------------------------------------------------------------------------------
-- Need to add isOutdoor column into HostFamilyPet table and change the quieies accordingly.
ALTER TABLE HostFamilyPet ADD COLUMN otherType VARCHAR(25) AFTER animalTypeId;
INSERT INTO HostFamilyAnimalType (hostFamilyAnimalType)
VALUES ('Dog'),('Cat'),('Bird'),('Reptile'),('Other');

INSERT INTO HostFamilyPet(hostFamilySeasonId,animalTypeId,number)
SELECT hostFamilySeasonId,1,DogCount FROM cci_go.HostFamilyhome WHERE DogCount != 0;

UPDATE HostFamilyPet hfp,cci_go.HostFamilyHome hfh
SET hfp.isIndoor = 1 
WHERE hfh.petLocation = 'Indoor' AND hfh.hostFamilySeasonId = hfp.HostFamilySeasonID AND hfh.DogCount != 0;

INSERT INTO HostFamilyPet(hostFamilySeasonId,animalTypeId,number)
SELECT hostFamilySeasonId,2,CatCount FROM cci_go.HostFamilyhome WHERE CatCount != 0;

UPDATE HostFamilyPet hfp,cci_go.HostFamilyHome hfh
SET hfp.isIndoor = 1 
WHERE hfh.petLocation = 'Indoor' AND hfh.hostFamilySeasonId = hfp.HostFamilySeasonID AND hfh.CatCount != 0;

INSERT INTO HostFamilyPet(hostFamilySeasonId,animalTypeId,otherType,number)
SELECT hostFamilySeasonId,5,'Rabbit',RabbitCount FROM cci_go.HostFamilyhome WHERE RabbitCount != 0;

UPDATE HostFamilyPet hfp,cci_go.HostFamilyHome hfh
SET hfp.isIndoor = 1 
WHERE hfh.petLocation = 'Indoor' AND hfh.hostFamilySeasonId = hfp.HostFamilySeasonID AND hfh.RabbitCount != 0;

INSERT INTO HostFamilyPet(hostFamilySeasonId,animalTypeId,number)
SELECT hostFamilySeasonId,3,BirdCount FROM cci_go.HostFamilyhome WHERE BirdCount != 0;

UPDATE HostFamilyPet hfp,cci_go.HostFamilyHome hfh
SET hfp.isIndoor = 1 
WHERE hfh.petLocation = 'Indoor' AND hfh.hostFamilySeasonId = hfp.HostFamilySeasonID AND hfh.BirdCount != 0;

INSERT INTO HostFamilyPet(hostFamilySeasonId,animalTypeId,number)
SELECT hostFamilySeasonId,4,ReptileCount FROM cci_go.HostFamilyhome WHERE ReptileCount != 0;

UPDATE HostFamilyPet hfp,cci_go.HostFamilyHome hfh
SET hfp.isIndoor = 1 
WHERE hfh.petLocation = 'Indoor' AND hfh.hostFamilySeasonId = hfp.HostFamilySeasonID AND hfh.ReptileCount != 0;

INSERT INTO HostFamilyPet(hostFamilySeasonId,animalTypeId,otherType,number)
SELECT (hostFamiySeasonId,5,NULL,OtherAnimalCount FROM cci_go.HostFamilyhome WHERE OtherAnimalCount != 0;

UPDATE HostFamilyPet hfp,cci_go.HostFamilyHome hfh
SET hfp.isIndoor = 1 
WHERE hfh.petLocation = 'Indoor' AND hfh.hostFamiySeasonId = hfp.HostFamiySeasonID AND hfh.OtherAnimalCount != 0;




-- --------------------------------------------------------------------------------------------------------------------------------------------
-- Insert into HostFamilyDocument
-- --------------------------------------------------------------------------------------------------------------------------------------------
-- ****************** Working Fine ***********************
DELETE FROM cci_go.DocumentMapping WHERE roleType='HostFamilySeason';

INSERT INTO DocumentType (documentTypeName) 
SELECT HostFamilyDocumentTypeName FROM cci_go.HostFamilyDocumentType WHERE HostFamilyDocumentTypeID <> 0 AND HostFamilyDocumentTypeName IN ('Agreement','SchoolPermission','Orientation','MileageCheck');

INSERT INTO DocumentTypeDocumentCategoryProcess(documentTypeId,documentCategoryProcessId,documentTypeRole)
VALUES (14,2,'HostFamilySeason'),
(15,2,'HostFamilySeason'),
(16,2,'HostFamilySeason'),
(17,2,'HostFamilySeason');

INSERT INTO cci_go.DocumentMapping (oldDocumentId,cciId,roleType) 
SELECT HostFamilyDocumentID,HostFamilySeasonID,'HostFamilySeason' FROM cci_go.HostFamilyDocument WHERE HostFamilyDocumentID <> 0;

ALTER TABLE DocumentInformation CHANGE documentTypeDocumentCategoryProcessId documentTypeDocumentCategoryProcessId INT(11) NULL, CHANGE createdOn createdOn TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL, CHANGE createdBy createdBy INT(11) NULL, CHANGE modifiedOn modifiedOn TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL, CHANGE modifiedBy modifiedBy INT(11) NULL; 

INSERT INTO DocumentInformation( documentInformationId)
SELECT documentInformationId FROM cci_go.DocumentMapping WHERE roleType='HostFamilySeason';

UPDATE DocumentInformation di,cci_go.DocumentMapping dm,cci_go.HostFamilyDocument hfd
SET di.documentName=hfd.DocumentName,
di.fileName=hfd.FileName,
di.url= CONCAT(hfd.FilePath,' \ ',hfd.DocumentTitle),
di.active=hfd.Active,
di.createdOn=hfd.CreatedOn,
di.createdBy=hfd.CreatedBy,
di.modifiedOn=hfd.ModifiedOn,
di.modifiedBy=hfd.ModifiedBy
WHERE di.documentInformationId = dm.documentInformationId
AND dm.oldDocumentId = hfd.HostFamilyDocumentID
AND dm.roleType = 'HostFamilySeason';

UPDATE DocumentInformation di,DocumentTypeDocumentCategoryProcess dt
SET di.documentTypeDocumentCategoryProcessId = dt.documentTypeDocumentCategoryProcessId,
    di.CreatedBy = 18,
	di.ModifiedBy = 18
WHERE dt.documentTypeId IN (14,15,16,17) AND di.documentTypeDocumentCategoryProcessId IS NULL;


INSERT INTO HostFamilyDocument (documentInformationId)
SELECT documentInformationId FROM cci_go.DocumentMapping WHERE roleType = 'HostFamilySeason' ORDER BY documentInformationId ASC;

UPDATE  `HostFamilyDocument` pd,cci_go.HostFamilyDocument pdo,cci_go.DocumentMapping dm
SET pd.`hostFamilySeasonId` = dm.cciId
WHERE pdo.hostFamilySeasonId = dm.cciId
AND pdo.HostFamilyDocumentID = dm.oldDocumentId AND dm.documentInformationId = pd.documentInformationId;
-- ****************** Working Fine ***********************
-- --------------------------------------------------------------------------------------------------------------------------------------------
-- Insert into HostFamilyPhotosType
-- --------------------------------------------------------------------------------------------------------------------------------------------
-- ****************** Working Fine ***********************
INSERT INTO HostFamilyPhotosType (hostFamilyPhotoTypeId,hostFamilyPhotoTypeName)
VALUES (1,'MainPhoto'),(2,'GeneralPhoto');

INSERT INTO HostFamilyPhotos (hostFamilySeasonId,fileName,filePath,photoName,hostFamilyPhotoTypeId,title,description,createdOn,createdBy,modifiedOn,modifiedBy,active)
SELECT HostFamilySeasonID,FileName,FilePath,DocumentName,HostFamilyDocumentTypeID,DocumentTitle,Description,CreatedOn,CreatedBy,ModifiedOn,ModifiedBy,Active
FROM cci_go.HostFamilyDocument WHERE HostFamilyDocumentTypeID IN (1,2);

-- ****************** Working Fine ***********************
-- ------------------------------------------------------------------------------------------------------------------------------------------
-- Insert into HostFamilyParticipant
-- ------------------------------------------------------------------------------------------------------------------------------------------
-- ****************** Working Fine ***********************
-- Data to be added after Participant Migration in participantGOId in HostFamilyParticipant table

INSERT INTO `HostFamilyParticipant`(`hostFamilyParticipantId`,`hostFamilySeasonId`,`hostFamilyInterested`,`hostFamilyScore`,
/*`hostFamilyParticipantStatusId`,*/`showToParticipant`,`uploadPermissionFormLater`,`uploadPermissionLaterReason`/*,`usHighSchoolId`*/,`permissionFormFileName`,
`permissionFormFilePath`,`permissionFormName`,`participantPayableId`,`approvedDate`,`movedDate`,`moveReasonId`,`moveExplanation`,`rejectionMessage`,
`movingParticipant`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`,`active`,`preferredDepartureDate`,`preferredArrivalDate`,`isWelcomeFamily`,
`isWelcomeFamilyChangedDate`,`hidePlacement`,`uploadPermissionLaterExpectedDate`,`partnerApprovalDate`,`partnerRejectionMessage`,/*`secondVisitLCId`,*/
`isRecruiterLCLeadId`,`isRecruiterLCLead`)
SELECT `HostFamilyParticipantID`,`HostFamilySeasonID`,`HostFamilyInterested`,`HostFamilyScore`/*,`HostFamilyParticipantStatusID`*/,
`ShowToParticipant`,`UploadPermissionFormLater`,`UploadPermissionLaterReason`/*,`USHighSchoolID`*/,`PermissionFormFileName`,
`PermissionFormFilePath`,`PermissionFormName`,`ParticipantPayableID`,`ApprovedDate`,`MovedDate`,`MoveReasonID`,`MoveExplanation`,
`RejectionMessage`,`MovingParticipant`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy`,`Active`,`PreferredDepartureDate`,
`PreferredArrivalDate`,`IsWelcomeFamily`,`IsWelcomeFamilyChangedDate`,`HidePlacement`,`UploadPermissionLaterExpectedDate`,
`PartnerApprovalDate`,`PartnerRejectionMessage`,/*`SecondVisitLCID`,*/`IsRecruiterLCLeadID`,`IsRecruiterLCLead`
FROM `cci_go`.`HostFamilyParticipant` WHERE HostFamilyParticipantID <> 0;

UPDATE HostFamilyParticipant hfp,`cci_go`.`HostFamilyParticipant` hfpo
SET hfp.hostFamilyParticipantStatusId = 14
WHERE hfpo.HostFamilyParticipantStatusID = 1
AND hfp.hostFamilyParticipantId = hfpo.HostFamilyParticipantID;

UPDATE HostFamilyParticipant hfp,`cci_go`.`HostFamilyParticipant` hfpo
SET hfp.hostFamilyParticipantStatusId = 15
WHERE hfpo.HostFamilyParticipantStatusID = 2
AND hfp.hostFamilyParticipantId = hfpo.HostFamilyParticipantID;

UPDATE HostFamilyParticipant hfp,`cci_go`.`HostFamilyParticipant` hfpo
SET hfp.hostFamilyParticipantStatusId = 16
WHERE hfpo.HostFamilyParticipantStatusID = 3
AND hfp.hostFamilyParticipantId = hfpo.HostFamilyParticipantID;

UPDATE HostFamilyParticipant hfp,`cci_go`.`HostFamilyParticipant` hfpo
SET hfp.hostFamilyParticipantStatusId = 17
WHERE hfpo.HostFamilyParticipantStatusID = 4
AND hfp.hostFamilyParticipantId = hfpo.HostFamilyParticipantID;

UPDATE HostFamilyParticipant hfp,`cci_go`.`HostFamilyParticipant` hfpo
SET hfp.hostFamilyParticipantStatusId = 18
WHERE hfpo.HostFamilyParticipantStatusID = 5
AND hfp.hostFamilyParticipantId = hfpo.HostFamilyParticipantID;

UPDATE HostFamilyParticipant hfp,`cci_go`.`HostFamilyParticipant` hfpo
SET hfp.hostFamilyParticipantStatusId = 19
WHERE hfpo.HostFamilyParticipantStatusID = 6
AND hfp.hostFamilyParticipantId = hfpo.HostFamilyParticipantID;

UPDATE HostFamilyParticipant hfp,`cci_go`.`HostFamilyParticipant` hfpo
SET hfp.hostFamilyParticipantStatusId = 20
WHERE hfpo.HostFamilyParticipantStatusID = 7
AND hfp.hostFamilyParticipantId = hfpo.HostFamilyParticipantID;

UPDATE HostFamilyParticipant hfp,`cci_go`.`HostFamilyParticipant` hfpo,cci_go.GoMapping gm
SET hfp.secondVisitLCId = gm.`goId`
WHERE hfp.hostFamilyParticipantId = hfpo.HostFamilyParticipantID
AND hfpo.SecondVisitLCID = gm.cciId AND gm.loginTypeId = 3;
-- ****************** Working Fine ***********************

-- -------------------------------------------------------------------------------------------------------------------------------------
-- Insert into HostFamilyParticipantHistory
-- -------------------------------------------------------------------------------------------------------------------------------------
-- ****************** Working Fine ***********************
INSERT INTO `HostFamilyParticipantHistory`(`hostFamilyGoId`,hostFamilyParticipantHistoryId,hostFamilySeasonId,hostFamilyName,
localCoordinatorName,beginDate,endDate,createdOn,createdBy,modifiedOn,modifiedBy,active)
SELECT gm.`goId`,hfpho.`HostFamilyParticipantHistoryID`,hfpho.`HostFamilySeasonID`,
hfpho.`HostFamilyName`,hfpho.`LocalCoordinatorName`,hfpho.`BeginDate`,hfpho.`EndDate`,hfpho.`CreatedOn`,
hfpho.`CreatedBy`,hfpho.`ModifiedOn`,hfpho.`ModifiedBy`,hfpho.`Active` FROM `cci_go`.`GoMapping` gm 
INNER JOIN `cci_go`.`HostFamilyParticipantHistory` hfpho ON hfpho.HostFamilyID = gm.cciId
WHERE gm.`LoginTypeId` = 4; 

INSERT INTO `HostFamilyParticipantHistory`(`hostFamilyGoId`,hostFamilyParticipantHistoryId,hostFamilySeasonId,hostFamilyName,
localCoordinatorName,beginDate,endDate,createdOn,createdBy,modifiedOn,modifiedBy,active)
SELECT gm.`goId`,hfpho.`HostFamilyParticipantHistoryID`,hfpho.`HostFamilySeasonID`,
hfpho.`HostFamilyName`,hfpho.`LocalCoordinatorName`,hfpho.`BeginDate`,hfpho.`EndDate`,hfpho.`CreatedOn`,
hfpho.`CreatedBy`,hfpho.`ModifiedOn`,hfpho.`ModifiedBy`,hfpho.`Active` FROM `cci_go`.`GoMapping` gm 
INNER JOIN `cci_go`.`HostFamilyParticipantHistory` hfpho ON hfpho.HostFamilyID = gm.`secCciId`
WHERE gm.isHostFamily = 1;

UPDATE `cci_go`.`HostFamilyParticipantHistory` hfpho,`HostFamilyParticipantHistory` hfphn,
`cci_go`.`GoMapping` gm
SET hfphn.`localCoordinatorId` = gm.`goId`
WHERE hfphn.`hostFamilyParticipantHistoryId`=hfpho.`HostFamilyParticipantHistoryID`
AND gm.`cciId`=hfpho.`LocalCoordinatorID`
AND gm.loginTypeId = 3; 
-- ****************** Working Fine ***********************

-- --------------------------------------------------------------------------------------------------------------------------------------------
-- Insert into HostFamilyEvaluation
-- --------------------------------------------------------------------------------------------------------------------------------------------
-- ***************************** Get clarification about LocalCoordinatorID ****************************************
INSERT INTO HostFamilyEvaluation (hostFamilyEvaluationId,hostFamilySeasonId,relationship,relationshipComments,relationshipScale,
communicationEffort,effortToAdopt,interestInFamily,effortForHygiene,additionalComments,familyActivityParticipation,behaviorProblems,academicProblems,
contactWithFieldStaff,problems,evaluationMonth,submittedToCCI,approvedByCCI,showToPartner,submittedDate,approvedDate,
createdOn,createdBy,modifiedOn,modifiedBy,active)
SELECT HostFamilyEvaluationId,HostFamilySeasonId,Relationship,RelationshipComments,RelationshipScale,
CommunicationEffort,EffortToAdapt,InterestInFamily,EffortForHygiene,AdditionalComments,FamilyActivityParticipation,BehaviorProblems,AcademicProblems,
ContactWithFieldStaff,Problems,EvaluationMonth,SubmittedToCCI,ApprovedByCCI,ShowToPartner,SubmittedDate,ApprovedDate,
CreatedOn,CreatedBy,ModifiedOn,ModifiedBy,Active FROM cci_go.HostFamilyEvaluation WHERE HostFamilyEvaluationId <> 0;


UPDATE `cci_go`.`HostFamilyEvaluation` hfpho,`HostFamilyEvaluation` hfphn,
`cci_go`.`GoMapping` gm
SET hfphn.`fieldStaffGoId` = gm.`goId`
WHERE hfphn.`hostFamilyEvaluationId`=hfpho.`hostFamilyEvaluationId`
AND gm.`cciId`=hfpho.`LocalCoordinatorID`
AND gm.loginTypeId = 3; 


UPDATE `cci_go`.`HostFamilyEvaluation` hfpho,`HostFamilyEvaluation` hfphn,
`cci_go`.`GoMapping` gm
SET hfphn.`participantGoId` = gm.`goId`
WHERE hfphn.`hostFamilyEvaluationId`=hfpho.`hostFamilyEvaluationId`
AND gm.`cciId`=hfpho.`LocalCoordinatorID`
AND gm.loginTypeId = 6; 


-- --------------------------------------------------------------------------------------------------------------------------------------------
-- Insert into HostFamilyFinalEvaluation
-- --------------------------------------------------------------------------------------------------------------------------------------------
-- ***************************** Get clarification about LocalCoordinatorID ****************************************
INSERT INTO HostFamilyFinalEvaluation(hostFamilyFinalEvaluationId,hostFamilySeasonId,overallExperience,participantFlexibility,participantCompatibility,
fieldStaffHelpfulness,materialQuality,insuranceQuality,didChores,followedRules,followedHygiene,didWellAcademically,didExtraCurriculars,relatedToUSStudents,
adequateOrientation,problem,problemHandledBy,overallExpectations,adviceForHostFamilies,recommendCCI,submittedToCCI,approvedByCCI,showToPartner,submittedDate,
approvedDate,createdOn,createdBy,modifiedOn,modifiedBy,active)
SELECT hostFamilyFinalEvaluationId,hostFamilySeasonId,overallExperience,participantFlexibility,participantCompatibility,
fieldStaffHelpfulness,materialQuality,insuranceQuality,didChores,followedRules,followedHygiene,didWellAcademically,didExtraCurriculars,relatedToUSStudents,
adequateOrientation,problem,problemHandledBy,overallExpectations,adviceForHostFamilies,recommendCCI,submittedToCCI,approvedByCCI,showToPartner,submittedDate,
approvedDate,createdOn,createdBy,modifiedOn,modifiedBy,active FROM HostFamilyFinalEvaluation WHERE hostFamilyFinalEvaluationId <> 0;

UPDATE cci_go.HostFamilyFinalEvaluation hfpho, HostFamilyFinalEvaluation hfphn, cci_go.GoMapping gm
SET hfphn.participantGoId = gm.goId
WHERE hfphn.hostFamilyFinalEvaluationId=hfpho.hostFamilyFinalEvaluationId
AND gm.cciId= hfpho.LocalCoordinatorID
AND gm.loginTypeId = 6; 
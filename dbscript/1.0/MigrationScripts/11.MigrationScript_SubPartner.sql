SET FOREIGN_KEY_CHECKS = 0;
-- --------------------------------------------------------------------------------------------------------
-- Insert into GoMapping AND GoIdSequence
-- --------------------------------------------------------------------------------------------------------
DELETE FROM cci_go.`GoMapping` WHERE `loginTypeId` = 6;
ALTER TABLE cci_go.`GoMapping` AUTO_INCREMENT = 1000;

INSERT INTO cci_go.`GoMapping` (`cciId`,`loginId`,loginTypeId,loginTypeName)
SELECT `SubPartnerID`,`LoginID`,2,'Sub-Partner' FROM cci_go.`SubPartner` WHERE `SubPartnerID` <> 0 ORDER BY `SubPartnerID` ASC;

DELETE FROM `GoIdSequence` WHERE `goId` IN (SELECT `goId` FROM `cci_go`.`GoMapping` WHERE `loginTypeName` = 'Sub-Partner');



INSERT INTO GoIdSequence 
SELECT `goId` FROM `cci_go`.`GoMapping` WHERE `loginTypeName` = 'Sub-Partner';

-- --------------------------------------------------------------------------------------------------------
-- Insert into PartnerReviewStatus
-- --------------------------------------------------------------------------------------------------------
INSERT INTO `Partner` (`partnerGoId`) SELECT `goId` FROM `cci_go`.`GoMapping` WHERE `loginTypeName` = 'Sub-Partner';

UPDATE Partner p,`cci_go`.SubPartner sp,`cci_go`.`GoMapping` gm
SET p.`companyName` = sp.`SubPartnerName`,
    p.`subscribeToCCINewsletter` = sp.`SubscribeToCCINewsletter`,
	p.`contractSigner` = sp.ContractSigner,
	p.`email` = sp.`ContactEmail`,
	p.`physicalAddressLineOne` = sp.`Address1`,
	p.`physicalAddressLineTwo` = sp.`Address2`,
	p.`physicalCity` = sp.`City`,
	p.`physicalZipcode` = sp.`PostalCode`,
	p.`physicalstate` = sp.`State`,
	p.`physicalcountryId` = sp.`CountryID`,
	p.`mailingAddressIsSameAsPhysicalAdress` = sp.MailingAddressSameAsPhysicalAddress,
	p.`addressLineOne` = sp.MailingAddress1,
	p.`addressLineTwo` = sp.MailingAddress2,
	p.`city` = sp.MailingCity,
	p.`zipcode` = sp.MailingPostalCode,
	p.`state` = sp.MailingState,
	p.`countryId` = sp.MailingCountryID,
	p.`partnerGuid` = sp.`SubPartnerGuid`,
	p.`lastSelectedProgramId` = sp.LastSelectedProgramID,
	p.`deliverDSForms` = sp.`DeliverDS2019Form`,
	p.`isSubPartner` = 1
WHERE p.`PartnerGoId` = gm.goId
AND gm.loginId = sp.`LoginID`
AND gm.cciId = sp.`SubPartnerID`
AND gm.`LoginTypeId` = 2
AND `loginTypeName` = 'Sub-Partner';


UPDATE Partner p1,Partner p2,`cci_go`.SubPartner sp,`cci_go`.`GoMapping` gm1,`cci_go`.`GoMapping` gm2
SET p1.`parentPartnerGoId` = p2.`partnerGoId`
WHERE sp.`SubPartnerID` = gm1.cciId
AND p1.partnerGoId = gm1.goId
AND gm1.cciId=sp.SubPartnerId
AND sp.PartnerId = gm2.cciId
AND gm2.goId = p2.partnerGoId
AND p1.`isSubPartner` = 1
AND gm1.`loginTypeName` = 'Sub-Partner'
AND gm2.loginTypeId=2;

-- --------------------------------------------------------------------------------------------------------
-- Insert into PartnerReviewStatus
-- --------------------------------------------------------------------------------------------------------
INSERT INTO `PartnerReviewStatus` (partnerGoId)
SELECT goId FROM `cci_go`.`GoMapping` gm WHERE gm.loginTypeId = 6;

UPDATE `PartnerReviewStatus` prs,`cci_go`.SubPartner sp,`cci_go`.`GoMapping` gm
SET prs.partnerAgentStatusId = 2
WHERE prs.partnerGoId = gm.goId
AND sp.SubPartnerID = gm.cciId
AND gm.loginTypeId = 2
AND gm.`loginTypeName` = 'Sub-Partner'
AND sp.Active = 1;

UPDATE `PartnerReviewStatus` prs,`cci_go`.SubPartner sp,`cci_go`.`GoMapping` gm
SET prs.partnerAgentStatusId = 3
WHERE prs.partnerGoId = gm.goId
AND sp.SubPartnerID = gm.cciId
AND gm.loginTypeId = 2
AND gm.`loginTypeName` = 'Sub-Partner'
AND sp.Active = 0;

/*UPDATE Partner p,`cci_go`.SubPartner sp,cci_go.GoMapping gm
SET p.`partnerStatusId` = 2
WHERE sp.Active = 1
AND sp.SubPartnerID = gm.cciId
AND p.partnerGoId = gm.GoId
AND p.isSubPartner = 1
AND gm.loginTypeId = 6;*/

/*UPDATE Partner p,`cci_go`.SubPartner sp,cci_go.GoMapping gm
SET p.`partnerStatusId` = 3
WHERE sp.Active = 0
AND sp.SubPartnerID = gm.cciId
AND p.partnerGoId = gm.GoId
AND p.isSubPartner = 1
AND gm.loginTypeId = 6;*/
-- --------------------------------------------------------------------------------------------------------
-- Insert into Login
-- --------------------------------------------------------------------------------------------------------
INSERT INTO `Login` (`loginId`,goId,`loginName`,`password`,`keyValue`,email,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`,active)
SELECT l.`LoginID`,gm.goId,l.`LoginName`,l.`Password`,l.`PasswordSalt`,sp.ContactEmail,l.`CreatedOn`,l.`CreatedBy`,l.`ModifiedOn`,l.`ModifiedBy`,sp.Active
FROM `cci_go`.`UserLogin` l
inner join `cci_go`.`GoMapping` gm ON l.loginId = gm.`loginId` AND gm.loginTypeId = 2
INNER JOIN cci_go.`SubPartner` sp ON l.`loginId` = sp.`LoginID` 
WHERE l.`LoginID` <> 0 AND l.`LoginTypeID` = 4;


/*INSERT INTO `Login` (`loginId`,`loginName`,`password`,`keyValue`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `LoginID`,`LoginName`,`Password`,`PasswordSalt`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy`
FROM `cci_go`.`UserLogin` WHERE `LoginID` <> 0 AND `LoginTypeID` = 6;


UPDATE `Login` l,`cci_go`.`SubPartner` sp
SET l.`email` = sp.`ContactEmail`,
    l.active = sp.Active
WHERE l.`loginId` = sp.`LoginID`;

UPDATE `Login` l,`cci_go`.`GoMapping` gm
SET l.`goId` = gm.`goId`
WHERE l.loginId = gm.`loginId`;*/

INSERT INTO `LoginUserType` (`loginId`,`userTypeId`,defaultUserType,active,createdOn,createdBy,modifiedOn,modifiedBy) 
SELECT LoginID,LoginTypeId,1,1,CURRENT_TIMESTAMP,18,CURRENT_TIMESTAMP,18 FROM cci_go.UserLogin WHERE LoginID <> 0 AND `LoginTypeID` = 6;

/*UPDATE `LoginUserType`
SET `defaultUserType` = 1,
    `active` = 1,
    `createdOn` = CURRENT_TIMESTAMP,
    `createdBy` = 18,
    `modifiedOn` = CURRENT_TIMESTAMP,
    `modifiedBy` = 18;*/
	

INSERT INTO `LoginHistory`    (`loggedOn`,`loginId`,`ipAddress`)
SELECT     LastLoggedIn,LoginID,LastIPAddress
FROM       `cci_go`.`UserLogin` WHERE loginTypeId =6;

-- --------------------------------------------------------------------------------------------------------
-- Insert into PartnerContact
-- --------------------------------------------------------------------------------------------------------
INSERT INTO PartnerUser (`partnerGoId`)
SELECT `partnerGoId` FROM Partner WHERE `isSubPartner` = 1;

UPDATE `PartnerUser` pc,`cci_go`.`SubPartner` sp,`cci_go`.`GoMapping` gm
SET pc.`firstName` = sp.`FirstName`,
    pc.`lastName` = sp.`LastName`,
	pc.`title` = sp.`Title`,
--	pc.`salutationId` = sp.`salutationID`,
--	pc.`email` = sp.`ContactEmail`,
	/*pc.`partnerOfficeId` = sp.`PartnerOfficeID`,*/
	pc.`phone` = sp.`PhoneNumber`,
	pc.`emergencyPhone` = sp.`EmergencyPhone`,
	pc.`fax` = sp.`FaxNumber`,
	/*pc.`receiveNotificationEmails` = pco.,*/
	/*pc.`skypeId` = pco.`SkypeID`,*/
	pc.`website` = sp.Website,
	pc.`active` = sp.`Active`
WHERE pc.partnerGoId = gm.goId
AND sp.SubPartnerID = gm.cciId
AND gm.loginTypeId = 2
AND gm.`loginTypeName` = 'Sub-Partner';

UPDATE `PartnerUser` pu,`cci_go`.`UserLoginPartner` ulp
SET pu.`salutationId` = ulp.`SalutationID`
WHERE pu.`loginId` = ulp.`UserLoginID` AND ulp.SalutationID <> 0;

-- --------------------------------------------------------------------------------------------------------
-- Insert into PartnerSeason
-- --------------------------------------------------------------------------------------------------------

INSERT INTO `PartnerSeason` (`seasonId`,`partnerGoId`,`departmentProgramId`,`partnerSeasonStatusId`,`insuranceProvidedByCCI`,`sevisFeesPaidByCCI`,
`insuranceCarrierName`,`insurancePhoneNumber`,`insurancePolicyNumber`,`questionaireRequired`,`questionnaireSubmittedOn`,
`disableAddParticipant`,`partnerSeasonStartDate`,`partnerSeasonEndDate`,`partnerSeasonAppDeadlineDate`,`partnerSeasonExtAppDeadlineDate`,
`partnerSeasonSecSemDeadlineDate`,`partnerSeasonExtSecSemDeadlineDate`,`contractScheduleId`,`canAccessJobBoard`,
`partnerTaxCompanyId`,`timelyReturnReportReceivedDate`,`originalsReceivedDate`,`participantPaysDeposit`,`exceptionComments`,`active`,
`createdBy`,`createdOn`,`modifiedBy`,`modifiedOn`,`cciStaffUserId`)
SELECT ps1.seasonId,gm1.goId,ps1.departmentProgramId,ps1.`partnerSeasonStatusId`,ps1.`insuranceProvidedByCCI`,
ps1.`sevisFeesPaidByCCI`,ps1.`insuranceCarrierName`,ps1.`insurancePhoneNumber`,ps1.`insurancePolicyNumber`,ps1.`questionaireRequired`,
ps1.`questionnaireSubmittedOn`,ps1.`disableAddParticipant`,ps1.`partnerSeasonStartDate`,ps1.`partnerSeasonEndDate`,ps1.`partnerSeasonAppDeadlineDate`,
ps1.`partnerSeasonExtAppDeadlineDate`,ps1.`partnerSeasonSecSemDeadlineDate`,ps1.`partnerSeasonExtSecSemDeadlineDate`,ps1.`contractScheduleId`,
ps1.`canAccessJobBoard`,ps1.`partnerTaxCompanyId`,ps1.`timelyReturnReportReceivedDate`,ps1.`originalsReceivedDate`,ps1.`participantPaysDeposit`,
ps1.`exceptionComments`,ps1.`active`,ps1.`createdBy`,ps1.`createdOn`,ps1.`modifiedBy`,ps1.`modifiedOn`,ps1.`cciStaffUserId`
FROM cci_go.`SubPartnerSeason` ss
INNER JOIN cci_go.`PartnerSeason` ps ON ss.partnerseasonId=ps.partnerseasonId
INNER JOIN cci_go.GoMapping gm1 ON gm1.cciId = ss.subPartnerId
INNER JOIN cci_go.GoMapping gm2 ON gm2.cciId = ps.PartnerId
INNER JOIN PartnerSeason ps1  ON ps1.partnerSeasonId = ss.partnerSeasonId 
WHERE gm1.loginTypeId=2 AND gm1.`loginTypeName` = 'Sub-Partner'
AND gm2.loginTypeId= 2
GROUP BY ss.subPartnerId,ps.partnerId,ps1.seasonId,ps1.departmentProgramId;

-- --------------------------------------------------------------------------------------------------------
-- Insert into PartnerNoteTopics
-- --------------------------------------------------------------------------------------------------------
INSERT INTO `PartnerNoteTopics` (/*partnerNoteTopicId,*/`partnerGoId`,`PartnerNoteTopicName`)
SELECT /*pn.SubPartnerNoteID,*/gm.`goId`,'Sub-PartnerNotes' 
FROM cci_go.GoMapping gm
INNER JOIN cci_go.SubPartnerNote pn
ON pn.SubPartnerID = gm.cciId AND pn.SubPartnerNoteId <> 0 AND gm.loginTypeId = 2 AND gm.`loginTypeName` = 'Sub-Partner';

INSERT INTO PartnerNotes (partnerNoteTopicId,partnerGoId,partnerNote,createdOn,createdBy,modifiedOn,modifiedBy)
SELECT pnt.partnerNoteTopicId,pnt.PartnerGoId,pn.Note,pn.CreatedOn,pn.CreatedBy,pn.ModifiedOn,pn.ModifiedBy
FROM `PartnerNoteTopics` pnt 
INNER JOIN cci_go.GoMapping gm ON pnt.partnerGoId = gm.goId AND pnt.PartnerNoteTopicName = 'Sub-PartnerNotes'
INNER JOIN `cci_go`.`SubPartnerNote` pn ON gm.cciId = pn.SubpartnerID ;

UPDATE PartnerNoteTopics
SET PartnerNoteTopicName = NULL
WHERE PartnerNoteTopicName = 'Sub-PartnerNotes' ;

SET FOREIGN_KEY_CHECKS = 1;
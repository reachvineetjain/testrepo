-- USE `cci_gh_go_login`;

SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE `Partner`;
TRUNCATE TABLE `PartnerStatus`;
TRUNCATE TABLE `PartnerReviewStatus`;
TRUNCATE TABLE `PartnerProgram`;
TRUNCATE TABLE `PartnerReferenceChecks`;
TRUNCATE TABLE `PartnerUser`;
TRUNCATE TABLE `PartnerPermissionsCategory`;
TRUNCATE TABLE `PartnerPermissions`;
TRUNCATE TABLE `PartnerUserRoles`;
TRUNCATE TABLE `PartnerOfficeType`;
TRUNCATE TABLE `PartnerOffice`;
-- TRUNCATE TABLE `PartnerContact`;
TRUNCATE TABLE `PartnerAnnouncement`;
TRUNCATE TABLE `PartnerSeason`;
TRUNCATE TABLE `PartnerSeasonAllocation`;
TRUNCATE TABLE `PartnerSeasonContract`;
TRUNCATE TABLE `PartnerNoteTags`;
TRUNCATE TABLE `PartnerNoteTopics`;
TRUNCATE TABLE `PartnerNotes`;
TRUNCATE TABLE `PartnerDocument`;
TRUNCATE TABLE `PartnerSeasonContract`;
TRUNCATE TABLE `PartnerSeasonDocument`;
TRUNCATE TABLE `PartnerHelpOption`;
TRUNCATE TABLE `PartnerHelpOptionProgram`;
TRUNCATE TABLE `HelpContactMode`;
TRUNCATE TABLE `PartnerHelpRequest`;


-- --------------------------------------------------------------------------------------------------------
-- Insert into Partner
-- --------------------------------------------------------------------------------------------------------
DELETE FROM cci_go.`GoMapping` WHERE `loginTypeId` = 2;
ALTER TABLE cci_go.`GoMapping` AUTO_INCREMENT = 1000;

INSERT INTO cci_go.`GoMapping` (`cciId`,`loginId`)
SELECT `PartnerID`,`LoginID` FROM cci_go.`Partner` WHERE PartnerID <> 0 ORDER BY `PartnerID` ASC;

INSERT INTO cci_go.`GoMapping` (`loginId`)
SELECT l.`LoginID` FROM cci_go.`UserLogin` l WHERE NOT EXISTS 
( SELECT 1 FROM cci_go.`GoMapping` gm WHERE l.`LoginID`=gm.loginId ) AND l.`LoginTypeID` = 4;

UPDATE cci_go.`GoMapping` SET `loginTypeId` = 2 WHERE `loginTypeId` IS NULL;
UPDATE cci_go.`GoMapping` SET `loginTypeName`= 'Partner' WHERE `loginTypeId` = 2;

DELETE FROM `GoIdSequence` WHERE `goId` > 1214;

INSERT INTO GoIdSequence 
SELECT `goId` FROM `cci_go`.`GoMapping` WHERE `LoginTypeId` = 2;


-- --------------------------------------------------------------------------------------------------------
-- Insert into Login
-- --------------------------------------------------------------------------------------------------------
DELETE FROM `Login` WHERE `goId` > 1214;
DELETE FROM `Login` WHERE `goId` = 0;
INSERT INTO `Login` (`loginId`,goId,`loginName`,`password`,`keyValue`,email,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`,active)
SELECT l.`LoginID`,gm.goId,l.`LoginName`,l.`Password`,l.`PasswordSalt`,p.Email,l.`CreatedOn`,l.`CreatedBy`,l.`ModifiedOn`,l.`ModifiedBy`,p.Active
FROM `cci_go`.`UserLogin` l
inner join `cci_go`.`GoMapping` gm ON l.loginId = gm.`loginId` AND gm.loginTypeId = 2
INNER JOIN cci_go.Partner p ON l.`loginId` = p.`LoginID` 
WHERE l.`LoginID` <> 0 AND l.`LoginTypeID` = 4;

/*UPDATE `Login` l,`cci_go`.`Partner` sp
SET l.`email` = sp.`Email`,
   l.active = sp.Active
WHERE l.`loginId` = sp.`LoginID`;

UPDATE `Login` l,`cci_go`.`GoMapping` gm
SET l.`goId` = gm.`goId`
WHERE l.loginId = gm.`loginId`;*/

/*UPDATE `Login` l,`PartnerUser` gm
SET l.`goId` = gm.`partnerGoId`
WHERE l.loginId = gm.`loginId`;*/

INSERT INTO `LoginUserType` (`loginId`,`userTypeId`) 
SELECT LoginID,LoginTypeId FROM cci_go.UserLogin WHERE LoginID <> 0 AND `LoginTypeID` = 4;

UPDATE `LoginUserType` ult,cci_go.GoMapping gm
SET ult.`defaultUserType` = 1,
    ult.`active` = 1,
    ult.`createdOn` = CURRENT_TIMESTAMP,
    ult.`createdBy` = 18,
    ult.`modifiedOn` = CURRENT_TIMESTAMP,
    ult.`modifiedBy` = 18
where ult.loginId = gm.loginId and gm.loginTypeId = 2;
	
INSERT INTO `LoginHistory`    (`loggedOn`,`loginId`,`ipAddress`)
SELECT LastLoggedIn,LoginID,LastIPAddress
FROM `cci_go`.`UserLogin` WHERE loginTypeId =4;

-- --------------------------------------------------------------------------------------------------------
-- Insert into Partner
-- --------------------------------------------------------------------------------------------------------
INSERT INTO `Partner` (`partnerGoId`) SELECT `goId` FROM `cci_go`.`GoMapping` WHERE `LoginTypeId` = 2;

INSERT INTO `PartnerStatus` (`partnerStatusId`,`partnerStatusName`,`active`)
SELECT PartnerStatusID,PartnerStatusName,Active FROM `cci_go`.PartnerStatus WHERE PartnerStatusID <> 0;

UPDATE `Partner` p,`cci_go`.`GoMapping` gm,`cci_go`.`Partner` po
SET p.`companyName` = po.`CompanyName`,
    p.`quickbooksCode` = po.`QuickbooksCode`,
	p.`acronym` = po.`Acronym`,
	p.`dandBNumber` = po.DandBNumber,
	p.`receiveAYPMails` = po.`ReceiveAYPEmails`,
	p.`subscribeToCCINewsletter` = po.SubscribeToCCINewsletter,
	p.`contractSigner` = po.ContractSigner,
	p.`canHaveSubPartner` = po.`CanHaveSubPartner`,
	p.`hasSubPartners` = po.`HasSubPartner`,
	-- p.`partnerStatusId` = po.`PartnerStatusId`,
	p.`email` = po.Email,
	p.`invoiceMail` = po.InvoiceEmail,
	p.`multiCountrySender` = po.MultiCountrySender,
	p.`mailingAddressIsSameAsPhysicalAdress` = po.MailingAddressSameAsPhysicalAddress,
	p.`addressLineOne` = po.MailingAddress1,
	p.`addressLineTwo` = po.MailingAddress2,
	p.`city` = po.MailingCity,
	p.`zipcode` = po.MailingPostalCode,
	p.`state` = po.MailingState,
	p.`countryId` = po.`MailingCountryID`,
	p.`partnerGuid` = po.PartnerGuid,
	p.`billingNotes` = po.BillingNotes,
	p.`lastSelectedProgramId` = po.LastSelectedProgramID,
	p.`participantMedicalReleaseRequired` = po.ParticipantMedicalReleaseRequired,
	p.`participantSLEPRequired` = po.ParticipantSLEPRequired,
	p.`participantTranscriptRequired` = po.ParticipantTranscriptRequired,
	p.`oldId` = po.OldID,
	p.`unguaranteedFormRequired` = po.UnguaranteedFormRequired,
	p.`participantELTISRequired` = po.ParticipantELTISRequired,
	p.`createdBy` = po.`CreatedBy`,
	p.`createdOn` = po.`CreatedOn`,
	p.`modifiedBy` = po.`ModifiedBy`,
	p.`modifiedOn` = po.`ModifiedOn`,
	p.`isSubPartner` = 0,
	p.`payGreenheartDirectly` = 0,
	p.`deliverDSForms` = 0,
	p.`needPartnerReview` = 0
WHERE p.`PartnerGoId` = gm.goId
AND   gm.loginId = po.`LoginID`
AND   gm.cciId = po.`PartnerID`;

UPDATE `Partner`
SET `physicalAddressLineOne` = `addressLineOne`,
    `physicalAddressLineTwo` = `addressLineTwo`,
    `physicalCity` = `city`,
    `physicalZipcode` = `zipcode`,
    `physicalstate` = `state`,
    `physicalcountryId` = `countryId`
WHERE `mailingAddressIsSameAsPhysicalAdress` = 1; 

UPDATE Partner
SET countryId = NULL WHERE countryId =0;


/* SubPartner table contains some columns like FirstName, LastName,Title,PhoneNumber,EmergencyPhone,FaxNumber,ContactEmail,Website
and we need to add these column into contact.*/


-- --------------------------------------------------------------------------------------------------------
-- Insert into PartnerStatus
-- --------------------------------------------------------------------------------------------------------

INSERT INTO `PartnerReviewStatus` (partnerGoId)
SELECT partnerGoId FROM Partner;

UPDATE `PartnerReviewStatus` prs,`cci_go`.Partner p,`cci_go`.`GoMapping` gm
SET prs.partnerAgentStatusId = p.PartnerStatusID
WHERE prs.partnerGoId = gm.goId
AND p.partnerID = gm.cciId
AND gm.loginTypeId = 2;

-- --------------------------------------------------------------------------------------------------------
-- Insert into PartnerProgram
-- --------------------------------------------------------------------------------------------------------
/*INSERT INTO PartnerCCIContact (partnerGoId,CCIStaffUserId)
SELECT gm1.goId AS PartnerGoId,gm.goId AS CCIStaffUserId 
FROM cci_go.Partner p
INNER JOIN cci_go.GoMapping gm ON  p.CCIContactID = gm.cciId AND p.CCIContactID <>0 AND gm.LoginTypeId =1
INNER JOIN cci_go.GoMapping gm1 ON gm1.cciId = p.PartnerID AND gm1.LoginTypeId =4;

INSERT INTO `PartnerCCIContact` (`partnerGoId`,`CCIStaffUserId`,lookupDepartmentProgramId)
SELECT gm1.goId AS PartnerGoId,gm.goId AS CCIStaffUserId,1 FROM cci_go.Partner p
INNER JOIN cci_go.GoMapping gm ON  p.CCIContactAYPID = gm.cciId AND p.CCIContactAYPID <>0
AND gm.LoginTypeId =1
INNER JOIN cci_go.GoMapping gm1 ON gm1.cciId = p.PartnerId AND gm1.LoginTypeId =4;

INSERT INTO `PartnerCCIContact` (`partnerGoId`,`CCIStaffUserId`,lookupDepartmentProgramId)
SELECT gm1.goId AS PartnerGoId,gm.goId AS CCIStaffUserId,6 FROM cci_go.Partner p
INNER JOIN cci_go.GoMapping gm ON  p.CCIContactWTID = gm.cciId AND p.CCIContactWTID <>0
AND gm.LoginTypeId =1
INNER JOIN cci_go.GoMapping gm1 ON gm1.cciId = p.PartnerId AND gm1.LoginTypeId =4;

INSERT INTO `PartnerCCIContact` (`partnerGoId`,`CCIStaffUserId`,lookupDepartmentProgramId)
SELECT gm1.goId AS PartnerGoId,gm.goId AS CCIStaffUserId,7 FROM cci_go.Partner p
INNER JOIN cci_go.GoMapping gm ON  p.CCIContactCAPID = gm.cciId AND p.CCIContactCAPID <>0
AND gm.LoginTypeId =1
INNER JOIN cci_go.GoMapping gm1 ON gm1.cciId = p.PartnerId AND gm1.LoginTypeId =4;

INSERT INTO PartnerCCIContact (partnerGoId,CCIStaffUserId,lookupDepartmentProgramId)
SELECT gm1.goId AS PartnerGoId,gm.goId AS CCIStaffUserId,8 FROM cci_go.Partner p
INNER JOIN cci_go.GoMapping gm ON  p.CCIContactGHTID = gm.cciId AND CCIContactGHTID <>0
AND gm.LoginTypeId =1
INNER JOIN cci_go.GoMapping gm1 ON gm1.cciId = p.PartnerId AND gm1.LoginTypeId =4;*/

INSERT INTO PartnerProgram (partnerGoId,CCIStaffUserId)
SELECT gm1.goId AS PartnerGoId,gm.goId AS CCIStaffUserId 
FROM cci_go.Partner p
INNER JOIN cci_go.GoMapping gm ON  p.CCIContactID = gm.cciId AND p.CCIContactID <>0 AND gm.LoginTypeId =1
INNER JOIN cci_go.GoMapping gm1 ON gm1.cciId = p.PartnerID AND gm1.LoginTypeId =2;

INSERT INTO `PartnerProgram` (`partnerGoId`,`CCIStaffUserId`,lookupDepartmentProgramId)
SELECT gm1.goId AS PartnerGoId,gm.goId AS CCIStaffUserId,1 FROM cci_go.Partner p
INNER JOIN cci_go.GoMapping gm ON  p.CCIContactAYPID = gm.cciId AND p.CCIContactAYPID <>0
AND gm.LoginTypeId =1
INNER JOIN cci_go.GoMapping gm1 ON gm1.cciId = p.PartnerId AND gm1.LoginTypeId =2;

INSERT INTO `PartnerProgram` (`partnerGoId`,`CCIStaffUserId`,lookupDepartmentProgramId)
SELECT gm1.goId AS PartnerGoId,gm.goId AS CCIStaffUserId,6 FROM cci_go.Partner p
INNER JOIN cci_go.GoMapping gm ON  p.CCIContactWTID = gm.cciId AND p.CCIContactWTID <>0
AND gm.LoginTypeId =1
INNER JOIN cci_go.GoMapping gm1 ON gm1.cciId = p.PartnerId AND gm1.LoginTypeId =2;

INSERT INTO `PartnerProgram` (`partnerGoId`,`CCIStaffUserId`,lookupDepartmentProgramId)
SELECT gm1.goId AS PartnerGoId,gm.goId AS CCIStaffUserId,7 FROM cci_go.Partner p
INNER JOIN cci_go.GoMapping gm ON  p.CCIContactCAPID = gm.cciId AND p.CCIContactCAPID <>0
AND gm.LoginTypeId =1
INNER JOIN cci_go.GoMapping gm1 ON gm1.cciId = p.PartnerId AND gm1.LoginTypeId =2;

INSERT INTO PartnerProgram (partnerGoId,CCIStaffUserId,lookupDepartmentProgramId)
SELECT gm1.goId AS PartnerGoId,gm.goId AS CCIStaffUserId,8 FROM cci_go.Partner p
INNER JOIN cci_go.GoMapping gm ON  p.CCIContactGHTID = gm.cciId AND CCIContactGHTID <>0
AND gm.LoginTypeId =1
INNER JOIN cci_go.GoMapping gm1 ON gm1.cciId = p.PartnerId AND gm1.LoginTypeId =2;

UPDATE PartnerProgram
SET hasApplied = 1,
    isEligible = 1;

-- --------------------------------------------------------------------------------------------------------
-- Insert into PartnerReferenceChecks
-- --------------------------------------------------------------------------------------------------------
INSERT INTO PartnerReferenceChecks (`partnerGoId`)
SELECT `PartnerGoId` FROM `Partner`;

UPDATE PartnerReferenceChecks prc,`cci_go`.`Partner` p,`cci_go`.`GoMapping` gm
SET prc.referenceCompletedOn = p.ReferenceDateCompleted,
    prc.referenceApprovedOn = p.ReferenceDateApproved,
	prc.referenceCompletedBy = p.ReferenceDateCompletedBy,
	prc.referenceApprovedBy = p.ReferenceDateApprovedBy,
	prc.businessLicenseExpiryDate = p.BusinessLicenseExpiresDate,
	prc.ReferenceCheckNotes = p.ReferenceNotes
WHERE prc.partnerGoId = gm.`goId`
AND gm.cciId = p.PartnerID
AND gm.loginTypeId = 2;

/*------------------------------------------------------------------------------------------------------------
   Data For PartnerOffice
--------------------------------------------------------------------------------------------------------------*/

INSERT INTO `PartnerOfficeType` (`partnerOfficeTypeId`,`partnerOfficeType`)
SELECT `PartnerOfficeTypeID`,`PartnerOfficeTypeName` FROM `cci_go`.`PartnerOfficeType` WHERE `PartnerOfficeTypeID` <> 0; 

INSERT INTO `PartnerOffice` (`partnerGoId`,`partnerOfficeId`)
SELECT p.`partnerGoId`,cpo.`PartnerOfficeID` FROM Partner p 
INNER JOIN `cci_go`.`GoMapping` gm ON p.`partnerGoId` = gm.`goId`
INNER JOIN `cci_go`.`PartnerOffice` cpo ON cpo.PartnerID = gm.cciId;

UPDATE `PartnerOffice` po,`cci_go`.`PartnerOffice` cpo,`cci_go`.`GoMapping` gm
SET     po.`partnerOfficeTypeId` = cpo.`PartnerOfficeTypeID`,
        po.`adressOne` = cpo.`Address1`,
	po.`adressTwo` = cpo.`Address2`,
	po.`city` = cpo.`City`,
	po.`state` = cpo.`State`,
	po.`postalCode` = cpo.`PostalCode`,
	po.`countryId` = cpo.`CountryID`,
	po.`faxNumber` = cpo.`FaxNumber`,
	po.`phoneNumber` = cpo.`PhoneNumber`,
	po.`website` = cpo.`Website`,
	po.`officeNotes` = cpo.`OfficeNotes`,
	po.`createdBy` = cpo.`CreatedBy`,
	po.`createdOn` = cpo.`CreatedOn`,
	po.`modifiedBy` = cpo.`ModifiedBy`,
	po.`modifiedOn` = cpo.`ModifiedOn`
WHERE po.`partnerGoId` = gm.`goId`
AND gm.`cciId` = cpo.`PartnerID`;


/*------------------------------------------------------------------------------------------------------------------
    Data For PartnerUser
-------------------------------------------------------------------------------------------------------------------*/

INSERT INTO PartnerUser (loginId)
SELECT UserLoginId FROM cci_go.UserLoginPartner;

UPDATE PartnerUser pu,cci_go.GoMapping gm, cci_go.UserLoginPartner ulp
SET pu.partnerGoId = gm.goId
WHERE pu.loginId = ulp.UserLoginId
AND ulp.PartnerId = gm.cciId
AND gm.loginTypeId =2;

UPDATE `PartnerUser` pu,`cci_go`.`UserLoginPartner` ulp
SET     pu.`title` = ulp.`Title`,
      /*pu.`salutationId = ulp.salutationID,*/ -- salutationID should not be zero.
	    pu.`firstName` = ulp.`FirstName`,
        pu.`lastName` = ulp.`LastName`,
		pu.`isPrimary` = ulp.`IsMaster`,
	    pu.`phone` = ulp.`Phone`,
	--    pu.`email` = ulp.`Email`,
	    pu.`emergencyPhone` = ulp.`EmergencyPhone`,
	    pu.`fax` = ulp.`Fax`,
	    pu.`skypeId` = ulp.`SkypeID`,
	    pu.active = ulp.Active,
	    pu.partnerOfficeId = ulp.PartnerOfficeID
WHERE  pu.loginId = ulp.UserLoginId;

UPDATE `PartnerUser` pu,`cci_go`.`UserLoginPartner` ulp
SET pu.`salutationId` = ulp.`SalutationID`
WHERE pu.`loginId` = ulp.`UserLoginID` AND ulp.SalutationID <> 0;

/*------------------------------------------------------------------------------------------------------------------
    Data For PartnerPermissionsCategory and PartnerPermissions
-------------------------------------------------------------------------------------------------------------------*/


INSERT INTO PartnerPermissionsCategory (partnerPermissionsCategory)
 VALUES ('Admin'),
        ('Applications'),
        ('Flights'),
        ('PlacementInfo'),
        ('Monitoring'),
        ('AccountingInsurance'),
        ('StudentPreProgram'),
        ('Contracting'),
        ('Insurance');
/*
INSERT INTO PartnerPermissions (`partnerUserId`)
SELECT partnerUserId FROM PartnerUser  ORDER BY partnerUserId ASC;

UPDATE PartnerPermissions pp,PartnerUser pu, cci_go.UserLoginPartner ulp
SET pp.j1Admin =1,
pp.j1Applications =1,
pp.j1Flights =1,
pp.j1PlacementInfo =1,
pp.j1Monitoring =1,
pp.j1AccountingInsurance=1,
pp.j1StudentsPreProgram =1,
pp.j1Contracting =1,j1Insurance =1
WHERE pu.loginId = ulp.UserLoginId
AND pp.partnerUserId = pu.partnerUserId
AND ulp.IsAYP =1;


UPDATE PartnerPermissions pp,PartnerUser pu, cci_go.UserLoginPartner ulp
SET pp.f1Admin =1,
pp.f1Applications =1,
pp.f1Flights =1,
pp.f1PlacementInfo =1,
pp.f1Monitoring =1,
pp.f1AccountingInsurance=1,
pp.f1StudentsPreProgram =1,
pp.f1Contracting =1,
pp.f1Insurance =1
WHERE pu.loginId = ulp.UserLoginId
AND pp.partnerUserId = pu.partnerUserId
AND ulp.IsPSPP =1;

UPDATE PartnerPermissions pp,PartnerUser pu, cci_go.UserLoginPartner ulp
SET pp.wtAdmin =1,
pp.wtFlights =1,
pp.wtPlacementInfo =1,
pp.wtMonitoring =1,
pp.`wtAccountingInsurance`=1,
pp.wtStudentsPreProgram =1,
pp.wtContracting =1,
pp.wtInsurance =1
WHERE pu.loginId = ulp.UserLoginId
AND pp.partnerUserId = pu.partnerUserId
AND ulp.IsWT =1;*/

INSERT INTO `PartnerPermissions` (`partnerUserId`,`lookupDepartmentProgramId`,`partnerPermissionsCategoryId`)
VALUES (
(SELECT pu.`partnerUserId` FROM `PartnerUser` pu 
INNER JOIN cci_go.UserLoginPartner ulp ON pu.loginId = ulp.UserLoginId AND ulp.`IsAYP` = 1)
,1,(SELECT `partnerPermissionsCategoryId` FROM `PartnerPermissionsCategory`));

INSERT INTO `PartnerPermissions` (`partnerUserId`,`lookupDepartmentProgramId`,`partnerPermissionsCategoryId`)
VALUES (
(SELECT pu.`partnerUserId` FROM `PartnerUser` pu 
INNER JOIN cci_go.UserLoginPartner ulp ON pu.loginId = ulp.UserLoginId AND ulp.`IsPSPP` = 1)
,2,(SELECT `partnerPermissionsCategoryId` FROM `PartnerPermissionsCategory`));

INSERT INTO `PartnerPermissions` (`partnerUserId`,`lookupDepartmentProgramId`,`partnerPermissionsCategoryId`)
VALUES (
(SELECT pu.`partnerUserId` FROM `PartnerUser` pu 
INNER JOIN cci_go.UserLoginPartner ulp ON pu.loginId = ulp.UserLoginId AND ulp.`IsWT` = 1)
,6,(SELECT `partnerPermissionsCategoryId` FROM `PartnerPermissionsCategory`));

INSERT INTO `PartnerPermissions` (`partnerUserId`,`lookupDepartmentProgramId`,`partnerPermissionsCategoryId`)
VALUES (
(SELECT pu.`partnerUserId` FROM `PartnerUser` pu 
INNER JOIN cci_go.UserLoginPartner ulp ON pu.loginId = ulp.UserLoginId AND ulp.`IsTrainee` = 1 AND ulp.`IsIntern` = 1)
,7,(SELECT `partnerPermissionsCategoryId` FROM `PartnerPermissionsCategory`));

INSERT INTO `PartnerPermissions` (`partnerUserId`,`lookupDepartmentProgramId`,`partnerPermissionsCategoryId`)
VALUES (
(SELECT pu.`partnerUserId` FROM `PartnerUser` pu 
INNER JOIN cci_go.UserLoginPartner ulp ON pu.loginId = ulp.UserLoginId AND ulp.`IsTrainee` = 0 AND ulp.`IsIntern` = 1)
,7,(SELECT `partnerPermissionsCategoryId` FROM `PartnerPermissionsCategory`));

-- needs to add for GHT 

/*------------------------------------------------------------------------------------------------------------------
    Data For PartnerContact
-------------------------------------------------------------------------------------------------------------------

INSERT INTO PartnerContact (`partnerGoId`)
SELECT `partnerGoId` FROM Partner;

UPDATE `PartnerContact` pc,`cci_go`.`PartnerContact` pco,`cci_go`.`GoMapping` gm
SET pc.`firstName` = pco.`FirstName`,
    pc.`lastName` = pco.`LastName`,
	pc.`title` = pco.`Title`,
	pc.`isPrimary` = 0,
	/*pc.`salutationId` = pco.`salutationID`,
	pc.`email` = pco.`Email`,
	pc.`partnerOfficeId` = pco.`PartnerOfficeID`,
	pc.`phone` = pco.`Phone`,
	pc.`emergencyPhone` = pco.`EmergencyPhone`,
	pc.`fax` = pco.`Fax`,
	/*pc.`receiveNotificationEmails` = pco.,
	pc.`skypeId` = pco.`SkypeID`,
	/*pc.`website` = pco.,
	pc.`active` = pco.`Active`,
	pc.`createdBy` = pco.`CreatedBy`,
	pc.`createdOn` = pco.`CreatedOn`,
	pc.`modifiedBy` = pco.`ModifiedBy`,
	pc.`modifiedOn` = pco.`ModifiedOn`
WHERE pc.partnerGoId = gm.goId
AND pco.PartnerID = gm.cciId;

UPDATE `PartnerContact` pc,`cci_go`.`PartnerContact` pco,`cci_go`.`GoMapping` gm
SET pc.`salutationId` = pco.`SalutationID`
WHERE pc.partnerGoId = gm.goId
AND pco.PartnerID = gm.cciId
AND pco.SalutationID <> 0;*/

/*-------------------------------------------------------------------------------------------
  Data For PartnerAnnouncement
--------------------------------------------------------------------------------------------*/

INSERT INTO PartnerAnnouncement (seasonId,departmentProgramId,announcement,title,active,createdBy,createdOn,modifiedBy,modifiedOn)
VALUES (6,1,'Please promote the Greenheart Service Leader Prep Program(SLPP) to your students!  This exciting program takes place in Chicago August 11-16, 2014 and costs $995.  During this weeklong program students will get an in-country orientation, an introduction to the concept of volunteer service, training on how to be a service leader, as well as get to see the wonderful sights of Chicago and interact with exchange students from different countries as well as the CCI Greenheart staff.  For more details, look in your Resources Tab, check out this link: http://www.cci-exchange.com/greenheart/service-leader-prep-program/ or write to your Program Manager.  Applications for the program are due March 31. Thank you!',
        'Service Leader Prep Program - Chicago 2014',1,37159,'2014-03-14 08:09:32.000000',37159,'2014-03-14 08:09:37.000000');

/*-------------------------------------------------------------------------------------------
  Data For PartnerSeason
--------------------------------------------------------------------------------------------*/
INSERT INTO `PartnerStatus` (`partnerStatusId`,`partnerStatusName`)
VALUES (8,'Active'),
       (9,'Questionnaire Due'),
	   (10,'Questionnaire Under Review'),
	   (11,'Needs Signed Agreement'),
	   (12,'Rejected'),
	   (13,'Closed');

INSERT INTO `PartnerSeason` (`partnerSeasonId`,`partnerGoId`)
SELECT ops.`PartnerSeasonID`,p.`partnerGoId` 
FROM `Partner` p 
INNER JOIN `cci_go`.`GoMapping` gm ON gm.`goId`=p.`partnerGoId` AND gm.`loginTypeId` = 2
INNER JOIN `cci_go`.`PartnerSeason` ops ON ops.`PartnerID` = gm.`cciId` ;


UPDATE `PartnerSeason` ps,`cci_go`.`GoMapping` gm,`cci_go`.`PartnerSeason` ops
SET ps.`cciStaffUserId` = gm.`goId`
WHERE ops.SPCCIContactID = gm.cciId
AND ps.partnerSeasonId = ops.PartnerSeasonID;

UPDATE `PartnerSeason` ps,`cci_go`.`PartnerSeason` ops,`cci_go`.`SeasonMapping` sm
SET ps.`seasonId` = sm.`newSeasonId`,
    ps.`departmentProgramId` = sm.`departmentProgramId`
WHERE ps.`partnerSeasonId` = ops.`PartnerSeasonID`
AND ops.SeasonID = sm.`oldSeasonId`;

UPDATE `PartnerSeason` ps,`cci_go`.`PartnerSeason` po -- problem for PartnerTaxCompanyID
SET /*ps.signedContract = po.,
    ps.canCreateSubPartner = po.,*/
	ps.`insuranceProvidedByCCI` = po.InsuranceProvidedByCCI,
	ps.`sevisFeesPaidByCCI` = po.SevisFeesPaidByCCI,
	ps.insuranceCarrierName = po.`InsuranceCompanyName`,
	ps.insurancePhoneNumber = po.`InsurancePhoneNumber`,
	ps.insurancePolicyNumber = po.`InsurancePolicyNumber`,
	ps.questionaireRequired = po.`RequireQuestionnaire`,
	ps.disableAddParticipant = po.`DisableAddParticipant`,
	ps.partnerSeasonStartDate = po.`StartDate`,
	ps.partnerSeasonEndDate = po.`EndDate`,
	ps.partnerSeasonAppDeadlineDate = po.`ApplicationDeadlineDate`,
	ps.partnerSeasonExtAppDeadlineDate = po.`ApplicationDeadlineExtensionDate`,
	ps.partnerSeasonSecSemDeadlineDate = po.`SecondDeadlineDate`,
	ps.partnerSeasonExtSecSemDeadlineDate= po.`SecondDeadlineExtensionDate`,
	ps.contractScheduleId = po.`ContractScheduleID`,
	ps.canAccessJobBoard = po.`CanAccessJobBoard`,
	ps.partnerTaxCompanyId = po.PartnerTaxCompanyID,
	ps.timelyReturnReportReceivedDate = po.TimelyReturnReportReceivedDate,
	ps.originalsReceivedDate = po.OriginalsReceivedDate,
	ps.participantPaysDeposit = po.ParticipantPaysDeposit,
	ps.exceptionComments = po.ExceptionComments,
	ps.active = po.Active,
	ps.createdBy = po.CreatedBy,
	ps.createdOn = po.CreatedOn,
	ps.modifiedBy = po.ModifiedBy,
	ps.modifiedOn = po.ModifiedOn
WHERE ps.partnerSeasonId = po.PartnerSeasonID;

UPDATE `PartnerSeason`
SET `PartnerTaxCompanyID` = NULL
WHERE `PartnerTaxCompanyID` = 0;

UPDATE `PartnerSeason` ps,`cci_go`.`PartnerSeason` ops
SET ps.`partnerSeasonStatusId` = 8
WHERE ops.`PartnerSeasonStatusID` = 2
AND ps.`partnerSeasonId` = ops.`PartnerSeasonID`;

UPDATE `PartnerSeason` ps,`cci_go`.`PartnerSeason` ops
SET ps.`partnerSeasonStatusId` = 3
WHERE ops.`PartnerSeasonStatusID` = 3
AND ps.`partnerSeasonId` = ops.`PartnerSeasonID`;

UPDATE `PartnerSeason` ps,`cci_go`.`PartnerSeason` ops
SET ps.`partnerSeasonStatusId` = 9
WHERE ops.`PartnerSeasonStatusID` = 4
AND ps.`partnerSeasonId` = ops.`PartnerSeasonID`;

UPDATE `PartnerSeason` ps,`cci_go`.`PartnerSeason` ops
SET ps.`partnerSeasonStatusId` = 10
WHERE ops.`PartnerSeasonStatusID` = 5
AND ps.`partnerSeasonId` = ops.`PartnerSeasonID`;

UPDATE `PartnerSeason` ps,`cci_go`.`PartnerSeason` ops
SET ps.`partnerSeasonStatusId` = 11
WHERE ops.`PartnerSeasonStatusID` = 6
AND ps.`partnerSeasonId` = ops.`PartnerSeasonID`;

UPDATE `PartnerSeason` ps,`cci_go`.`PartnerSeason` ops
SET ps.`partnerSeasonStatusId` = 12
WHERE ops.`PartnerSeasonStatusID` = 7
AND ps.`partnerSeasonId` = ops.`PartnerSeasonID`;

UPDATE `PartnerSeason` ps,`cci_go`.`PartnerSeason` ops
SET ps.`partnerSeasonStatusId` = 13
WHERE ops.`PartnerSeasonStatusID` = 8
AND ps.`partnerSeasonId` = ops.`PartnerSeasonID`;

/*UPDATE `PartnerSeason` 
SET `partnerDeadlineRequestStatusId` = NULL,
    `partnerSecSemDeadlineRequestStatusId` = NULL,
    `deadlineRequestedBy` = NULL,
    `deadlineRequestedOn` = '9999-09-09 00:00:00',
    `deadlineRequestReviewedBy`= NULL,
    `deadlineRequestReviewedOn`='9999-09-09 00:00:00';*/

/*-----------------------------------------------------------------------------------------------
   Data For PartnerSeasonAllocation
------------------------------------------------------------------------------------------------*/
 -- J1 Program
INSERT INTO `PartnerSeasonAllocation`(partnerSeasonAllocationId,partnerSeasonId,maxPax,maxGuaranteedPax,expectedPaxCount,createdBy,createdOn,ModifiedOn,ModifiedBy)
SELECT psa.partnerSeasonAllocationId,psa.partnerSeasonId,psa.MaximumParticipantCount,psa.MaximumParticipantCountGuaranteed,psa.ExpectedParticipantCount,psa.createdBy,psa.createdOn,psa.ModifiedOn,psa.ModifiedBy
FROM cci_go.`PartnerSeason` ps, cci_go.`SeasonMapping` sm, cci_go.`PartnerSeasonAllocation` psa
WHERE sm.departmentProgramId = 1 AND sm.oldSeasonId = ps.seasonId
AND psa.partnerSeasonId = ps.partnerSeasonId;

UPDATE `PartnerSeasonAllocation` psa, cci_go.`PartnerSeasonAllocation` cpsa
SET departmentProgramOptionId =2
WHERE psa.partnerSeasonAllocationId = cpsa.partnerSeasonAllocationId
AND cpsa.programOptionId =6;

UPDATE `PartnerSeasonAllocation` psa, cci_go.`PartnerSeasonAllocation` cpsa
SET departmentProgramOptionId =4
WHERE psa.partnerSeasonAllocationId = cpsa.partnerSeasonAllocationId
AND cpsa.programOptionId =7;

-- F1 Program
INSERT INTO `PartnerSeasonAllocation`(partnerSeasonAllocationId,partnerSeasonId,maxPax,maxGuaranteedPax,expectedPaxCount,createdBy,createdOn,ModifiedOn,ModifiedBy)
SELECT psa.partnerSeasonAllocationId,psa.partnerSeasonId,psa.MaximumParticipantCount,psa.MaximumParticipantCountGuaranteed,psa.ExpectedParticipantCount,psa.createdBy,psa.createdOn,psa.ModifiedOn,psa.ModifiedBy
FROM cci_go.`PartnerSeason` ps, cci_go.`SeasonMapping` sm, cci_go.`PartnerSeasonAllocation` psa
WHERE sm.departmentProgramId = 2 AND sm.oldSeasonId = ps.seasonId
AND psa.partnerSeasonId = ps.partnerSeasonId;

UPDATE `PartnerSeasonAllocation` psa, cci_go.`PartnerSeasonAllocation` cpsa, cci_go.SeasonMapping sm, cci_go.PartnerSeason ps
SET departmentProgramOptionId =7
WHERE psa.partnerSeasonAllocationId = cpsa.partnerSeasonAllocationId
AND cpsa.programOptionId =6
AND sm.departmentProgramId = 2 AND sm.oldSeasonId = ps.seasonId
AND psa.partnerSeasonId = ps.partnerSeasonId;

UPDATE `PartnerSeasonAllocation` psa, cci_go.`PartnerSeasonAllocation` cpsa, cci_go.SeasonMapping sm, cci_go.PartnerSeason ps
SET departmentProgramOptionId =9
WHERE psa.partnerSeasonAllocationId = cpsa.partnerSeasonAllocationId
AND cpsa.programOptionId =7
AND sm.departmentProgramId = 2 AND sm.oldSeasonId = ps.seasonId
AND psa.partnerSeasonId = ps.partnerSeasonId;

-- WT Summer Program

INSERT INTO `PartnerSeasonAllocation`(partnerSeasonAllocationId,partnerSeasonId,maxPax,maxGuaranteedPax,expectedPaxCount,createdBy,createdOn,ModifiedOn,ModifiedBy)
SELECT psa.partnerSeasonAllocationId,psa.partnerSeasonId,psa.MaximumParticipantCount,psa.MaximumParticipantCountGuaranteed,psa.ExpectedParticipantCount,psa.createdBy,psa.createdOn,psa.ModifiedOn,psa.ModifiedBy
FROM cci_go.`PartnerSeason` ps, cci_go.`SeasonMapping` sm, cci_go.`PartnerSeasonAllocation` psa
WHERE sm.departmentProgramId = 6 AND sm.oldSeasonId = ps.seasonId
AND psa.partnerSeasonId = ps.partnerSeasonId
AND psa.programOptionId IN (2,4,20);

UPDATE `PartnerSeasonAllocation` psa, cci_go.`PartnerSeasonAllocation` cpsa, cci_go.SeasonMapping sm, cci_go.PartnerSeason ps
SET departmentProgramOptionId =11
WHERE psa.partnerSeasonAllocationId = cpsa.partnerSeasonAllocationId
AND cpsa.programOptionId =2
AND sm.departmentProgramId = 6 AND sm.oldSeasonId = ps.seasonId
AND psa.partnerSeasonId = ps.partnerSeasonId;

UPDATE `PartnerSeasonAllocation` psa, cci_go.`PartnerSeasonAllocation` cpsa, cci_go.SeasonMapping sm, cci_go.PartnerSeason ps
SET departmentProgramOptionId =10
WHERE psa.partnerSeasonAllocationId = cpsa.partnerSeasonAllocationId
AND cpsa.programOptionId =4
AND sm.departmentProgramId = 6 AND sm.oldSeasonId = ps.seasonId
AND psa.partnerSeasonId = ps.partnerSeasonId;

UPDATE `PartnerSeasonAllocation` psa, cci_go.`PartnerSeasonAllocation` cpsa, cci_go.SeasonMapping sm, cci_go.PartnerSeason ps
SET departmentProgramOptionId =12
WHERE psa.partnerSeasonAllocationId = cpsa.partnerSeasonAllocationId
AND cpsa.programOptionId =20
AND sm.departmentProgramId = 6 AND sm.oldSeasonId = ps.seasonId
AND psa.partnerSeasonId = ps.partnerSeasonId;


-- WT  Winter Program


INSERT INTO `PartnerSeasonAllocation`(partnerSeasonAllocationId,partnerSeasonId,maxPax,maxGuaranteedPax,expectedPaxCount,createdBy,createdOn,ModifiedOn,ModifiedBy)
SELECT psa.partnerSeasonAllocationId,psa.partnerSeasonId,psa.MaximumParticipantCount,psa.MaximumParticipantCountGuaranteed,psa.ExpectedParticipantCount,psa.createdBy,psa.createdOn,psa.ModifiedOn,psa.ModifiedBy
FROM cci_go.`PartnerSeason` ps, cci_go.`SeasonMapping` sm, cci_go.`PartnerSeasonAllocation` psa
WHERE sm.departmentProgramId = 7 AND sm.oldSeasonId = ps.seasonId
AND psa.partnerSeasonId = ps.partnerSeasonId
AND psa.programOptionId IN (2,4,20);

UPDATE `PartnerSeasonAllocation` psa, cci_go.`PartnerSeasonAllocation` cpsa, cci_go.SeasonMapping sm, cci_go.PartnerSeason ps
SET departmentProgramOptionId =14
WHERE psa.partnerSeasonAllocationId = cpsa.partnerSeasonAllocationId
AND cpsa.programOptionId =2
AND sm.departmentProgramId = 7 AND sm.oldSeasonId = ps.seasonId
AND psa.partnerSeasonId = ps.partnerSeasonId;

UPDATE `PartnerSeasonAllocation` psa, cci_go.`PartnerSeasonAllocation` cpsa, cci_go.SeasonMapping sm, cci_go.PartnerSeason ps
SET departmentProgramOptionId =13
WHERE psa.partnerSeasonAllocationId = cpsa.partnerSeasonAllocationId
AND cpsa.programOptionId =4
AND sm.departmentProgramId = 7 AND sm.oldSeasonId = ps.seasonId
AND psa.partnerSeasonId = ps.partnerSeasonId;

UPDATE `PartnerSeasonAllocation` psa, cci_go.`PartnerSeasonAllocation` cpsa, cci_go.SeasonMapping sm, cci_go.PartnerSeason ps
SET departmentProgramOptionId =15
WHERE psa.partnerSeasonAllocationId = cpsa.partnerSeasonAllocationId
AND cpsa.programOptionId =20
AND sm.departmentProgramId = 7 AND sm.oldSeasonId = ps.seasonId
AND psa.partnerSeasonId = ps.partnerSeasonId;


-- WT Spring Program


INSERT INTO `PartnerSeasonAllocation`(partnerSeasonAllocationId,partnerSeasonId,maxPax,maxGuaranteedPax,expectedPaxCount,createdBy,createdOn,ModifiedOn,ModifiedBy)
SELECT psa.partnerSeasonAllocationId,psa.partnerSeasonId,psa.MaximumParticipantCount,psa.MaximumParticipantCountGuaranteed,psa.ExpectedParticipantCount,psa.createdBy,psa.createdOn,psa.ModifiedOn,psa.ModifiedBy
FROM cci_go.`PartnerSeason` ps, cci_go.`SeasonMapping` sm, cci_go.`PartnerSeasonAllocation` psa
WHERE sm.departmentProgramId = 8 AND sm.oldSeasonId = ps.seasonId
AND psa.partnerSeasonId = ps.partnerSeasonId
AND psa.programOptionId IN (2,4,20);

UPDATE `PartnerSeasonAllocation` psa, cci_go.`PartnerSeasonAllocation` cpsa, cci_go.SeasonMapping sm, cci_go.PartnerSeason ps
SET departmentProgramOptionId =17
WHERE psa.partnerSeasonAllocationId = cpsa.partnerSeasonAllocationId
AND cpsa.programOptionId =2
AND sm.departmentProgramId = 8 AND sm.oldSeasonId = ps.seasonId
AND psa.partnerSeasonId = ps.partnerSeasonId;

UPDATE `PartnerSeasonAllocation` psa, cci_go.`PartnerSeasonAllocation` cpsa, cci_go.SeasonMapping sm, cci_go.PartnerSeason ps
SET departmentProgramOptionId =16
WHERE psa.partnerSeasonAllocationId = cpsa.partnerSeasonAllocationId
AND cpsa.programOptionId =4
AND sm.departmentProgramId = 8 AND sm.oldSeasonId = ps.seasonId
AND psa.partnerSeasonId = ps.partnerSeasonId;

UPDATE `PartnerSeasonAllocation` psa, cci_go.`PartnerSeasonAllocation` cpsa, cci_go.SeasonMapping sm, cci_go.PartnerSeason ps
SET departmentProgramOptionId =18
WHERE psa.partnerSeasonAllocationId = cpsa.partnerSeasonAllocationId
AND cpsa.programOptionId =20
AND sm.departmentProgramId = 8 AND sm.oldSeasonId = ps.seasonId
AND psa.partnerSeasonId = ps.partnerSeasonId;


-- CAP Program

INSERT INTO `PartnerSeasonAllocation`(partnerSeasonAllocationId,partnerSeasonId,maxPax,maxGuaranteedPax,expectedPaxCount,createdBy,createdOn,ModifiedOn,ModifiedBy)
SELECT psa.partnerSeasonAllocationId,psa.partnerSeasonId,psa.MaximumParticipantCount + psa1.MaximumParticipantCount,
psa.MaximumParticipantCountGuaranteed + psa1.MaximumParticipantCountGuaranteed,
psa.ExpectedParticipantCount + psa1.ExpectedParticipantCount,
psa.createdBy,psa.createdOn,psa.ModifiedOn,psa.ModifiedBy
FROM cci_go.`PartnerSeason` ps, cci_go.`SeasonMapping` sm, cci_go.`PartnerSeasonAllocation` psa,cci_go.`PartnerSeasonAllocation` psa1
WHERE sm.departmentProgramId = 9 
AND sm.oldSeasonId = ps.seasonId
AND psa.partnerSeasonId = ps.partnerSeasonId
AND psa.ProgramOptionID = psa1.ProgramOptionID
AND psa.PartnerSeasonId = psa1.PartnerSeasonID
AND psa.programOptionId IN (10,11)
GROUP BY psa.PartnerSeasonID;

UPDATE `PartnerSeasonAllocation` psa, cci_go.`PartnerSeasonAllocation` cpsa, cci_go.SeasonMapping sm, cci_go.PartnerSeason ps
SET psa.departmentProgramOptionId =19
WHERE psa.partnerSeasonAllocationId = cpsa.partnerSeasonAllocationId
AND cpsa.programOptionId IN (10,11)
AND sm.departmentProgramId = 9 AND sm.oldSeasonId = ps.seasonId
AND psa.partnerSeasonId = ps.partnerSeasonId
AND cpsa.partnerSeasonId = psa.partnerSeasonId;



INSERT INTO `PartnerSeasonAllocation`(partnerSeasonAllocationId,partnerSeasonId,maxPax,maxGuaranteedPax,expectedPaxCount,createdBy,createdOn,ModifiedOn,ModifiedBy)
SELECT psa.partnerSeasonAllocationId,psa.partnerSeasonId,psa.MaximumParticipantCount + psa1.MaximumParticipantCount,
psa.MaximumParticipantCountGuaranteed + psa1.MaximumParticipantCountGuaranteed,
psa.ExpectedParticipantCount + psa1.ExpectedParticipantCount,
psa.createdBy,psa.createdOn,psa.ModifiedOn,psa.ModifiedBy
FROM cci_go.`PartnerSeason` ps, cci_go.`SeasonMapping` sm, cci_go.`PartnerSeasonAllocation` psa,cci_go.`PartnerSeasonAllocation` psa1
WHERE sm.departmentProgramId = 9 
AND sm.oldSeasonId = ps.seasonId
AND psa.partnerSeasonId = ps.partnerSeasonId
AND psa.ProgramOptionID = psa1.ProgramOptionID
AND psa.PartnerSeasonId = psa1.PartnerSeasonID
AND psa.programOptionId IN (15,16)
GROUP BY psa.PartnerSeasonID;

UPDATE `PartnerSeasonAllocation` psa, cci_go.`PartnerSeasonAllocation` cpsa, cci_go.SeasonMapping sm, cci_go.PartnerSeason ps
SET psa.departmentProgramOptionId =20
WHERE psa.partnerSeasonAllocationId = cpsa.partnerSeasonAllocationId
AND cpsa.programOptionId IN (15,16)
AND sm.departmentProgramId = 9 AND sm.oldSeasonId = ps.seasonId
AND psa.partnerSeasonId = ps.partnerSeasonId
AND cpsa.partnerSeasonId = psa.partnerSeasonId;
	
/*UPDATE `PartnerSeasonAllocation` 
SET `requestedMaxPax` = NULL,
     `requestedMaxGuaranteedPax` = NULL,
     `allocationRequestStatusId` = NULL,
     `allocationRequestedBy` = NULL,
     `allocationRequestedOn` = '9999-09-09 00-00-00',
     `allocationRequestedOn` = '9999-09-09 00-00-00',
     `allocationRequestReviewedBy` = NULL,
     `allocationRequestReviewedOn` = '9999-09-09 00-00-00';*/
	 
/*---------------------------------------------------------------------------------------------
-- Insert into PartnerNotes
----------------------------------------------------------------------------------------------*/
INSERT INTO `PartnerNoteTags` (`tagName`)
VALUES ('Work&Travels'),
       ('HSP-J1'),
	   ('GHT'),
	   ('STBound'),
	   ('Intern'),
	   ('Trainee'),
	   ('Meeting/Visit'),
	   ('CompitetorInfo'),
	   ('Embassy/VisaInfo'),
	   ('SeasonInfo'),
	   ('HSPF1');

INSERT INTO `PartnerNoteTopics` (partnerNoteTopicId,`partnerGoId`)
SELECT pn.partnerNoteID,gm.`goId` 
FROM cci_go.GoMapping gm
INNER JOIN cci_go.PartnerNote pn
ON pn.PartnerID = gm.cciId AND partnerNoteId <> 0 AND gm.loginTypeId = 2;

UPDATE `PartnerNoteTopics` pnt,`cci_go`.`BitsMapping` bm,`cci_go`.`PartnerNote` pn
SET     pnt.`w&t` = bm.wt,
        pnt.`j1` = bm.j1,
    	pnt.`ght` = bm.ght,
		pnt.`stInbound` = bm.stInbound,
		pnt.`intern` = bm.intern,
		pnt.`trainee` = bm.trainee,
		pnt.`meeting/visit` = bm.meeting,
		pnt.`competitorInfo` = bm.compInfo,
		pnt.`embassy/VisaInfo` = bm.`embassy/VisaInfo`,
		pnt.`seasonInfo` = bm.seasonInfo,
		pnt.`f1` = bm.f1,
		pnt.createdBy = 18,
		pnt.modifiedBy = 18
WHERE pn.BitsProgramOption = bm.bitsProgramOption
AND pn.partnerNoteID = pnt.partnerNoteTopicId;

INSERT INTO PartnerNotes (partnerNoteTopicId,partnerGoId,partnerNote,createdOn,createdBy,modifiedOn,modifiedBy)
SELECT pnt.partnerNoteTopicId,pnt.PartnerGoId,pn.Note,pn.CreatedOn,pn.CreatedBy,pn.ModifiedOn,pn.ModifiedBy
FROM `PartnerNoteTopics` pnt 
INNER JOIN `cci_go`.`PartnerNote` pn ON pnt.partnerNoteTopicId = pn.partnerNoteID;


INSERT INTO `PartnerNoteTopics` (`partnerGoId`,`isVisibleToPartner`,`PartnerNoteTopicName`)
SELECT gm.`goId`,1,'PartnerProgramNotes'
FROM cci_go.GoMapping gm
INNER JOIN cci_go.`PartnerProgramNote` pn
ON pn.PartnerID = gm.cciId AND pn.`PartnerProgramNoteID` <> 0 AND gm.loginTypeId = 2;


INSERT INTO PartnerNotes (partnerNoteTopicId,partnerGoId,partnerNote,createdOn,createdBy,modifiedOn,modifiedBy)
SELECT pnt.partnerNoteTopicId,pnt.PartnerGoId,pn.Note,pn.CreatedOn,pn.CreatedBy,pn.ModifiedOn,pn.ModifiedBy
FROM `PartnerNoteTopics` pnt 
INNER JOIN cci_go.GoMapping gm ON pnt.partnerGoId = gm.goId AND pnt.PartnerNoteTopicName = 'PartnerProgramNotes'
INNER JOIN cci_go.`PartnerProgramNote` pn ON gm.cciId = pn.PartnerID;

UPDATE PartnerNoteTopics
SET `PartnerNoteTopicName` = NULL
WHERE `PartnerNoteTopicName` = 'PartnerProgramNotes';

/*---------------------------------------------------------------------------------------------
-- Insert into PartnerSeasonNotes
----------------------------------------------------------------------------------------------*/
/*
INSERT INTO `PartnerSeasonNoteTopics` (partnerSeasonNoteTopicId,`partnerSeasonId`)
SELECT pn.partnerNoteID,gm.`goId` 
FROM cci_go.GoMapping gm
INNER JOIN cci_go.PartnerNote pn
ON pn.PartnerID = gm.cciId AND partnerNoteId <> 0 AND gm.loginTypeId = 2;

UPDATE `PartnerNoteTopics` pnt,`cci_go`.`BitsMapping` bm,`cci_go`.`PartnerNote` pn
SET     pnt.`w&t` = bm.wt,
        pnt.`j1` = bm.j1,
    	pnt.`ght` = bm.ght,
		pnt.`stInbound` = bm.stInbound,
		pnt.`intern` = bm.intern,
		pnt.`trainee` = bm.trainee,
		pnt.`meeting/visit` = bm.meeting,
		pnt.`competitorInfo` = bm.compInfo,
		pnt.`embassy/VisaInfo` = bm.`embassy/VisaInfo`,
		pnt.`seasonInfo` = bm.seasonInfo,
		pnt.`f1` = bm.f1,
		pnt.createdBy = 18,
		pnt.modifiedBy = 18
WHERE pn.BitsProgramOption = bm.bitsProgramOption
AND pn.partnerNoteID = pnt.partnerNoteTopicId;

INSERT INTO PartnerNotes (partnerNoteTopicId,partnerGoId,partnerNote,createdOn,createdBy,modifiedOn,modifiedBy)
SELECT pnt.partnerNoteTopicId,pnt.PartnerGoId,pn.Note,pn.CreatedOn,pn.CreatedBy,pn.ModifiedOn,pn.ModifiedBy
FROM `PartnerNoteTopics` pnt 
INNER JOIN `cci_go`.`PartnerNote` pn ON pnt.partnerNoteTopicId = pn.partnerNoteID;


INSERT INTO `PartnerNoteTopics` (`partnerGoId`,`isVisibleToPartner`,`PartnerNoteTopicName`)
SELECT gm.`goId`,1,'PartnerProgramNotes'
FROM cci_go.GoMapping gm
INNER JOIN cci_go.`PartnerProgramNote` pn
ON pn.PartnerID = gm.cciId AND pn.`PartnerProgramNoteID` <> 0 AND gm.loginTypeId = 2;


INSERT INTO PartnerNotes (partnerNoteTopicId,partnerGoId,partnerNote,createdOn,createdBy,modifiedOn,modifiedBy)
SELECT pnt.partnerNoteTopicId,pnt.PartnerGoId,pn.Note,pn.CreatedOn,pn.CreatedBy,pn.ModifiedOn,pn.ModifiedBy
FROM `PartnerNoteTopics` pnt 
INNER JOIN cci_go.GoMapping gm ON pnt.partnerGoId = gm.goId AND pnt.PartnerNoteTopicName = 'PartnerProgramNotes'
INNER JOIN cci_go.`PartnerProgramNote` pn ON gm.cciId = pn.PartnerID;

UPDATE PartnerNoteTopics
SET `PartnerNoteTopicName` = NULL
WHERE `PartnerNoteTopicName` = 'PartnerProgramNotes';
*/
/*--------------------------------------------------------------------------------------------------
   Data For PartnerDocument
----------------------------------------------------------------------------------------------------*/

INSERT INTO `DocumentType` (`documentTypeName`)
SELECT `partnerDocumentTypeName` FROM cci_go.`PartnerDocumentType` WHERE `PartnerDocumentTypeID` <> 0; -- DOC MIGRN SCRIPT

/* 6.Partner Questionnaire
   7.Mission Report
   8.Miscellaneous*/
   
INSERT INTO `DocumentType` (`documentTypeName`)
VALUES ('Legacy Type');

INSERT INTO `DocumentCategoryProcess` (`documentCategoryProcessName`)
VALUES
('Approval Process'); -- need to identify some more processes

INSERT INTO `DocumentTypeDocumentCategoryProcess` (`documentTypeId`)
VALUES /*(6),(8),*/(9);

/*INSERT INTO `DocumentTypeDocumentCategoryProcess` (`documentTypeId`)
SELECT `documentTypeId` FROM `DocumentType` dt WHERE EXISTS
(SELECT partnerDocumentTypeName FROM cci_go.`PartnerDocumentType` pdt 
WHERE pdt.partnerDocumentTypeName = dt.documentTypeName);*/

UPDATE `DocumentTypeDocumentCategoryProcess`
SET `documentCategoryProcessId` = 4
WHERE `documentTypeId` IN (9);

/*UPDATE `DocumentTypeDocumentCategoryProcess`
SET `documentCategoryProcessId` = 5
WHERE `documentTypeId` IN (8);*/
DELETE FROM cci_go.`DocumentMapping` WHERE roleType = 'Partner';
ALTER TABLE cci_go.`DocumentMapping` AUTO_INCREMENT = 1;
INSERT INTO cci_go.`DocumentMapping` (`oldDocumentId`,`roleType`,`cciId`)
SELECT `PartnerDocumentID`,'Partner',`PartnerID` FROM cci_go.`PartnerDocument` WHERE `PartnerDocumentID` <> 0;

DELETE FROM cci_go.`DocumentMapping` WHERE roleType = 'PartnerSeason';
ALTER TABLE cci_go.`DocumentMapping` AUTO_INCREMENT = 1;
INSERT INTO cci_go.`DocumentMapping` (`oldDocumentId`,`roleType`,`cciId`)
SELECT `PartnerSeasonDocumentID`,'PartnerSeason',`PartnerSeasonID` FROM cci_go.`PartnerSeasonDocument` WHERE `PartnerSeasonDocumentID` <> 0;

DELETE FROM cci_go.`DocumentMapping` WHERE roleType = 'PartnerSeasonContract';
ALTER TABLE cci_go.`DocumentMapping` AUTO_INCREMENT = 1;
INSERT INTO cci_go.`DocumentMapping` (`oldDocumentId`,`roleType`,`cciId`)
SELECT `PartnerSeasonContractID`,'PartnerSeasonContract',`PartnerSeasonID` FROM cci_go.`PartnerSeasonContract` WHERE `PartnerSeasonContractID` <> 0;

INSERT INTO `DocumentInformation` (`documentInformationId`)
SELECT `documentInformationId` FROM cci_go.`DocumentMapping` WHERE `roleType` = 'Partner';

UPDATE `DocumentInformation` di,cci_go.`PartnerDocument` pd,cci_go.`DocumentMapping` dm
SET di.`documentName` = pd.`DocumentName`,
    di.`fileName` = pd.`FileName`,
	di.url = '',
	di.active = pd.`Active`
WHERE di.documentInformationId = dm.documentInformationId
AND dm.oldDocumentId = pd.`PartnerDocumentID`
AND dm.roleType = 'Partner'
AND pd.FilePath = '';

UPDATE `DocumentInformation` di,`DocumentTypeDocumentCategoryProcess` dt
SET di.`documentTypeDocumentCategoryProcessId` = dt.documentTypeDocumentCategoryProcessId,
    di.`CreatedBy` = 18,
	di.`ModifiedBy` = 18
WHERE dt.documentTypeId = 9 AND di.`documentTypeDocumentCategoryProcessId` IS NULL;

/*INSERT INTO `DocumentInformation` (`documentInformationId`,`documentName`,`fileName`,`url`,`active`)
SELECT `PartnerDocumentID`,`DocumentName`,`FileName`,``,`Active`
FROM `cci_go`.`PartnerDocument` WHERE DocumentTypeID = 2 AND FilePath = ''; -- partnerDocumentTypeId is not present in Partner Document.

INSERT INTO `DocumentInformation` (`documentInformationId`,`documentName`,`fileName`,`url`,`active`)
SELECT `PartnerSeasonDocumentID`,`DocumentName`,`FileName`,`DocumentURL`,`Active`
FROM `cci_go`.`PartnerSeasonDocument` WHERE DocumentTypeID = 2 AND FilePath = '';*/

INSERT INTO `PartnerDocument` (`documentInformationId`)
SELECT `documentInformationId` FROM cci_go.DocumentMapping WHERE roleType = 'Partner' ORDER BY documentInformationId ASC;

UPDATE  `PartnerDocument` pd,Partner p,cci_go.GoMapping gm,cci_go.PartnerDocument pdo,cci_go.DocumentMapping dm
SET pd.`partnerGoId` = p.partnerGoId
WHERE p.partnerGoId = gm.goId
AND pdo.PartnerID = gm.cciId
AND pdo.PartnerDocumentID = dm.oldDocumentId AND dm.documentInformationId = pd.documentInformationId;

UPDATE `PartnerDocument` pd,PartnerProgram pp
SET pd.partnerProgramId = pp.partnerProgramId
WHERE pd.partnerGoId = pp.partnerGoId; 

INSERT INTO `DocumentInformation` (`documentInformationId`)
SELECT `documentInformationId` FROM cci_go.`DocumentMapping` WHERE `roleType` = 'PartnerSeason';

UPDATE `DocumentInformation` di,cci_go.`PartnerSeasonDocument` pd,cci_go.`DocumentMapping` dm
SET di.`documentName` = pd.`DocumentName`,
    di.`fileName` = pd.`FileName`,
	di.url = '',
	di.active = pd.`Active`
WHERE di.documentInformationId = dm.documentInformationId
AND dm.oldDocumentId = pd.`PartnerSeasonDocumentID`
AND dm.roleType = 'PartnerSeason'
AND pd.DocumentURL = '';

UPDATE `DocumentInformation` di,`DocumentTypeDocumentCategoryProcess` dt
SET di.`documentTypeDocumentCategoryProcessId` = dt.documentTypeDocumentCategoryProcessId,
    di.`CreatedBy` = 18,
	di.`ModifiedBy` = 18
WHERE dt.documentTypeId = 1 AND di.`documentTypeDocumentCategoryProcessId` IS NULL;

/*INSERT INTO `SeasonProgramDocument` (`documentInformationId`)
SELECT `documentInformationId` FROM cci_go.`DocumentMapping` WHERE `roleType` = 'PartnerSeason';

UPDATE `SeasonProgramDocument` spd,cci_go.PartnerSeasonDocument psd,PartnerSeason ps,cci_go.DocumentMapping dm
SET spd.`seasonId` = ps.seasonId,
    spd.`departmentProgramId` = ps.departmentProgramId,
    spd.`createdOn` = psd.`CreatedOn`,
    spd.`createdBy` = psd.`CreatedBy`,
    spd.`ModifiedOn` = psd.`ModifiedOn`,
    spd.`modifiedBy` = psd.`ModifiedBy`
    spd.active = psd.Active
WHERE spd.documentInformationId = dm.documentInformationId
AND dm.cciId = psd.PartnerSeasonID = ps.partnerSeasonId
AND dm.roleType = 'PartnerSeason';*/

INSERT INTO `PartnerSeasonDocument` (documentInformationId)
SELECT dm.documentInformationId FROM cci_go.DocumentMapping dm
WHERE dm.roleType = 'PartnerSeason';

UPDATE `PartnerSeasonDocument` psd,PartnerSeason ps,cci_go.DocumentMapping dm
SET psd.`partnerSeasonId` = dm.cciID
WHERE dm.roleType = 'PartnerSeason';

INSERT INTO `DocumentType` (`documentTypeName`)
VALUES ('Operating Agreement-Legacy');

INSERT INTO `DocumentCategoryProcess` (`documentCategoryProcessName`)
VALUES
('Operational Process'); -- need to identify some more processes

INSERT INTO `DocumentTypeDocumentCategoryProcess` (`documentTypeId`)
VALUES (10);

UPDATE `DocumentTypeDocumentCategoryProcess`
SET `documentCategoryProcessId` = 5
WHERE `documentTypeId` IN (10);

INSERT INTO `DocumentInformation` (`documentInformationId`)
SELECT `documentInformationId` FROM cci_go.`DocumentMapping` WHERE `roleType` = 'PartnerSeasonContract';

UPDATE `DocumentInformation` di,cci_go.`PartnerSeasonContract` pd,cci_go.`DocumentMapping` dm
SET di.`documentName` = pd.`DisplayName`,
    di.`fileName` = pd.`FileName`,
	di.url = '',
	di.active = pd.`Active`
WHERE di.documentInformationId = dm.documentInformationId
AND dm.oldDocumentId = pd.`PartnerSeasonContractID`
AND dm.roleType = 'PartnerSeasonContract';
-- AND pd.DocumentURL = '';

UPDATE `DocumentInformation` di,`DocumentTypeDocumentCategoryProcess` dt
SET di.`documentTypeDocumentCategoryProcessId` = dt.documentTypeDocumentCategoryProcessId,
    di.`CreatedBy` = 18,
	di.`ModifiedBy` = 18
WHERE dt.documentTypeId = 10 AND di.`documentTypeDocumentCategoryProcessId` IS NULL;

INSERT INTO `PartnerSeasonContract` (documentInformationId)
SELECT documentInformationId FROM cci_go.`DocumentMapping` WHERE `roleType` = 'PartnerSeasonContract';

UPDATE `PartnerSeasonContract` psc ,cci_go.PartnerSeasonContract opsc,cci_go.DocumentMapping dm
SET psc.partnerSeasonId = opsc.PartnerSeasonID,
    psc.isSigned = opsc.IsSigned
WHERE psc.documentInformationId = dm.documentInformationId
AND dm.oldDocumentId = opsc.PartnerSeasonContractID;

/*--------------------------------------------------------------------------------------------------
   Data For PartnerHelp
----------------------------------------------------------------------------------------------------*/
INSERT INTO `PartnerHelpOption` (`partnerHelpOptionId`,`partnerHelpOptionName`,`createdBy`,`createdOn`,`modifiedBy`,`modifiedOn`,`active`)
SELECT `PartnerHelpOptionID`,`PartnerHelpOptionName`,`CreatedBy`,`CreatedOn`,`ModifiedBy`,`ModifiedOn`,`Active`
FROM `cci_go`.`PartnerHelpOption` WHERE `partnerHelpOptionId` <> 0;
-- CreatedBy and ModifiedBy columns are with 0 values in existing table. 

/*UPDATE PartnerHelpOptionProgram
SET CreatedBy = 18,
    ModifiedBy = 18;*/

INSERT INTO `PartnerHelpOptionProgram` (`partnerHelpOptionProgramId`,`partnerHelpOptionId`,`createdBy`,`createdOn`, `modifiedBy`,`modifiedOn`)
SELECT `PartnerHelpOptionProgramID`,`PartnerHelpOptionID`,`CreatedBy`,`CreatedOn`, `ModifiedBy`,`ModifiedOn` 
FROM `cci_go`.`PartnerHelpOptionProgram` WHERE `PartnerHelpOptionProgramID` <> 0;
-- CreatedBy and ModifiedBy columns are with 0 values in existing table. 

/*UPDATE PartnerHelpOptionProgram
SET CreatedBy = 18,
    ModifiedBy = 18;*/

UPDATE `PartnerHelpOptionProgram` ph,`cci_go`.`PartnerHelpOptionProgram` pho
SET ph.`lookupDepartmentProgramId`= 1
WHERE pho.`programID` = 2
AND pho.`PartnerHelpOptionID` = ph.`partnerHelpOptionId`
AND pho.`PartnerHelpOptionProgramID` = ph.`partnerHelpOptionProgramId`;

UPDATE `PartnerHelpOptionProgram` ph,`cci_go`.`PartnerHelpOptionProgram` pho
SET ph.`lookupDepartmentProgramId` = 6
WHERE pho.`programID` = 3
AND pho.`PartnerHelpOptionID` = ph.`partnerHelpOptionId`
AND pho.`PartnerHelpOptionProgramID` = ph.`partnerHelpOptionProgramId`;

INSERT INTO `HelpContactMode` (`helpContactModeId`,`helpContactModeName`,`active`)
SELECT `HelpContactModeID`,`HelpContactModeName`,`Active` FROM `cci_go`.`HelpContactMode` WHERE `helpContactModeId` <> 0;

INSERT INTO `PartnerHelpRequest` (partnerHelpRequestId,partnerHelpRequestGUID,partnerHelpOptionId,helpContactModeId,loginId,requestName,
requestEmailAddress,requestMessage,createdBy,createdOn,modifiedBy,modifiedOn,active)
SELECT `PartnerHelpRequestID`,`PartnerHelpRequestGUID`,`PartnerHelpOptionID`,`HelpContactModeID`,`CCILoginID`,`RequestName`,
`RequestEmailAddress`,`RequestMessage`,`CreatedBy`,`CreatedOn`,`ModifiedBy`,`ModifiedOn`,`Active`
FROM cci_go.`PartnerHelpRequest` WHERE `PartnerHelpRequestID` <> 0 AND CCILoginID <> 0;

UPDATE `PartnerHelpRequest` phr,cci_go.GoMapping gm
SET phr.`partnerGoId` = gm.goId
WHERE phr.`loginId` = gm.loginId
AND gm.loginTypeId = 2;

UPDATE `PartnerHelpRequest` phr,cci_go.`PartnerHelpRequest` cphr
SET `lookupDepartmentProgramId` = 6
WHERE cphr.`ProgramID` = 2
AND phr.`partnerHelpRequestId` = cphr.`PartnerHelpRequestID`
AND phr.`partnerHelpRequestGUID` = cphr.`PartnerHelpRequestGUID`;

UPDATE `PartnerHelpRequest` phr,cci_go.`PartnerHelpRequest` cphr
SET `lookupDepartmentProgramId` = 1
WHERE cphr.`ProgramID` = 3
AND phr.`partnerHelpRequestId` = cphr.`PartnerHelpRequestID`
AND phr.`partnerHelpRequestGUID` = cphr.`PartnerHelpRequestGUID`;

SET FOREIGN_KEY_CHECKS = 1;
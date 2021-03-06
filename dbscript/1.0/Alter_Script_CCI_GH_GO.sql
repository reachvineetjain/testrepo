
--------------------------------------------------------------------------------------------------------------------------------------------------
--- Alter script for adding columns passwordSalt to Login Table and isVisibleToSeason to LookupDepartments table on 6th August 2015---------------
--------------------------------------------------------------------------------------------------------------------------------------------------

ALTER TABLE `cci_gh_go`.`Login` 
  CHANGE `password` `password` VARCHAR(200) NOT NULL, 
  ADD COLUMN `passwordSalt` VARCHAR(200) NOT NULL AFTER `password`;

ALTER TABLE `cci_gh_go`.`LookupDepartments` 
  ADD COLUMN `isVisibleToSeason` TINYINT(1) DEFAULT 0 AFTER `acronym`;


--------------------------------------------------------------------------------------------------------------------------------------------------
--- Alter script for adding PK for cciStaffUserId and dropping previously created id column and UNIQUE INDEX on cciStaffUserId on 12th Aug 2015---
--------------------------------------------------------------------------------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;
ALTER TABLE `cci_gh_go`.`CCIStaffUsers`   
  CHANGE `cciStaffUserId` `cciStaffUserId` INT(11), 
  DROP FOREIGN KEY `FK_CCIStaffUsers_login`,
  DROP COLUMN `loginId`,
  ADD CONSTRAINT `FK_CCIStaffUsers_GoIdSequence` FOREIGN KEY (`cciStaffUserId`) REFERENCES `cci_gh_go`.`GoIdSequence`(`goId`) ON UPDATE NO ACTION;

SET FOREIGN_KEY_CHECKS = 1;


--------------------------------------------------------------------------------------------------------------------------------------------------
--- Alter script for adding Unique Key to sesaonName column in Season table on 20th Aug 2015------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------

ALTER TABLE `cci_gh_go`.`Season` ADD UNIQUE INDEX IND_seasonName(seasonName);

--------------------------------------------------------------------------------------------------------------------------------------------------
--- Alter script for adding email column and changing passwordSalt column to keyValue in Login table on 24th Aug 2015 ----------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------
ALTER TABLE `Login` CHANGE `passwordSalt` `keyValue` VARCHAR(200) NOT NULL; 

ALTER TABLE `Login` ADD COLUMN `email` VARCHAR(50) NOT NULL AFTER `keyValue`;


--------------------------------------------------------------------------------------------------------------------------------------------------
--- Alter script for changing departmentProgram column to lookupDepartmentProgram in CCIStaffUserProgram table on 8th Sep 2015 -------------------
--------------------------------------------------------------------------------------------------------------------------------------------------
SET FOREIGN_KEY_CHECKS =0;

ALTER TABLE `cci_gh_go`.`CCIStaffUserProgram`    
  CHANGE `departmentProgramId` `lookupDepartmentProgramId` INT(11) NOT NULL  AFTER `cciStaffUserId`, 
  DROP INDEX `FK_CCIStaffUserProgram_DepartmentPrograms`,
  DROP FOREIGN KEY `FK_CCIStaffUserProgram_DepartmentPrograms`,
  ADD CONSTRAINT `FK_CCIStaffUserProgram_LookupDepartmentPrograms` FOREIGN KEY (`lookupDepartmentProgramId`) REFERENCES `cci_gh_go`.`LookupDepartmentPrograms`(`lookupDepartmentProgramId`);
 
 SET FOREIGN_KEY_CHECKS =1;
 
 
---------------------------------------------------------------------------------------------------------------------------------------------------
--- Alter script for changing length of seasonName and programName columns in all Season related tables  as per BUG # 171,192,197 on 8th Sep 2015--
---------------------------------------------------------------------------------------------------------------------------------------------------

ALTER TABLE Season MODIFY seasonName VARCHAR(35);
ALTER TABLE Season MODIFY seasonFullName VARCHAR(35);
ALTER TABLE SeasonCAPDetails MODIFY programName VARCHAR(55);
ALTER TABLE SeasonF1Details MODIFY programName VARCHAR(55);
ALTER TABLE SeasonHSADetails MODIFY programName VARCHAR(55);
ALTER TABLE SeasonIHPDetails MODIFY programName VARCHAR(55);
ALTER TABLE SeasonJ1Details MODIFY programName VARCHAR(55);
ALTER TABLE SeasonLSDetails MODIFY programName VARCHAR(55);
ALTER TABLE SeasonTADetails MODIFY programName VARCHAR(55);
ALTER TABLE SeasonVADetails MODIFY programName VARCHAR(55);
ALTER TABLE SeasonWADetails MODIFY programName VARCHAR(55);
ALTER TABLE SeasonWnTSpringDetails MODIFY programName VARCHAR(55);
ALTER TABLE SeasonWnTSummerDetails MODIFY programName VARCHAR(55);
ALTER TABLE SeasonWnTWinterDetails MODIFY programName VARCHAR(55);

---------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------- Alter script in Partner DB Model based on new mock up screens changes on 20th October 2015------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
ALTER TABLE `PartnerAgentInquiries` ADD COLUMN rating INT(3) AFTER companyName;

ALTER TABLE `PartnerContact` ADD COLUMN isPrimary TINYINT(1) DEFAULT 0 AFTER salutationId;

DROP TABLE `PartnerCCIContact`;

ALTER TABLE `PartnerProgram`   DROP COLUMN isOther,DROP COLUMN isPDNotified;

ALTER TABLE `PartnerProgram` DROP FOREIGN KEY FK_PartnerProgram_CCIStaffUser,
DROP INDEX FK_PartnerProgram_CCIStaffUser,
DROP COLUMN markedEligibleBy;

ALTER TABLE `PartnerProgram` ADD COLUMN cciStaffUserId INT(11),
ADD INDEX `FK_PartnerProgram_CCIStaffUser_idx` (`cciStaffUserId` ASC),
ADD CONSTRAINT `FK_PartnerProgram_CCIStaffUser`
    FOREIGN KEY (`cciStaffUserId`)
    REFERENCES `CCIStaffUsers` (`cciStaffUserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION	;
 
ALTER TABLE `PartnerSeason` ADD COLUMN canCreateSubPartner TINYINT(1) DEFAULT 0; 

---------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------- Alter script for Partner Change Requests for Deadline date and Allocations 26th October 2015----------------------
---------------------------------------------------------------------------------------------------------------------------------------------------

ALTER TABLE `PartnerSeason` ADD COLUMN isSignedContract TINYINT(1) NOT NULL DEFAULT 0;

ALTER TABLE PartnerSeason   
ADD COLUMN partnerDeadlineRequestStatusId  INT ,
ADD COLUMN partnerSecSemDeadlineRequestStatusId  INT ,
ADD COLUMN deadlineRequestedBy  INT,
ADD COLUMN deadlineRequestedOn DATETIME,
ADD COLUMN deadlineRequestReviewedBy INT,
ADD COLUMN deadlineRequestReviewedOn  DATETIME,
ADD CONSTRAINT `FK_PartnerSeason_PartnerStatus1`
    FOREIGN KEY (partnerDeadlineRequestStatusId)
    REFERENCES `PartnerStatus` (`partnerStatusId`),
ADD CONSTRAINT `FK_PartnerSeason_PartnerStatus2`
    FOREIGN KEY (partnerSecSemDeadlineRequestStatusId)
    REFERENCES `PartnerStatus` (`partnerStatusId`),
ADD CONSTRAINT `FK_PartnerSeason_Login`
    FOREIGN KEY (deadlineRequestedBy)
    REFERENCES `Login` (`loginId`),
ADD CONSTRAINT `FK_PartnerSeason_CCIStaffUsers1`
    FOREIGN KEY (deadlineRequestReviewedBy)
    REFERENCES `CCIStaffUsers` (`cciStaffUserId`);
	

ALTER TABLE PartnerSeasonAllocation   
ADD COLUMN requestedMaxPax  INT AFTER maxPax,
ADD COLUMN requestedMaxGuaranteedPax INT AFTER maxGuaranteedPax ,
ADD COLUMN allocationRequestStatusId INT,
ADD COLUMN allocationRequestedBy  INT,
ADD COLUMN allocationRequestedOn  DATETIME,
ADD COLUMN allocationRequestReviewedBy  INT,
ADD COLUMN allocationRequestReviewedOn DATETIME,
ADD CONSTRAINT `FK_PartnerSeasonAllocation_PartnerStatus`
    FOREIGN KEY (allocationRequestStatusId)
    REFERENCES PartnerStatus (partnerStatusId),
ADD CONSTRAINT `FK_PartnerSeasonAllocation_Login`
    FOREIGN KEY (allocationRequestedBy)
    REFERENCES `Login` (`loginId`),
ADD CONSTRAINT `FK_PartnerSeasonAllocation_CCIStaffUsers`
    FOREIGN KEY (allocationRequestReviewedBy)
    REFERENCES `CCIStaffUsers` (`cciStaffUserId`);
							 

---------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------- Alter script for Partner Note & Partner Note Topics on 29th October 2015------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------

							 
ALTER TABLE PartnerUser ADD COLUMN `partnerOfficeId` INT AFTER `salutationId`,
ADD CONSTRAINT `FK_PartnerUser_PartnerOffice`
FOREIGN KEY (`partnerOfficeId`)
REFERENCES PartnerOffice (`partnerOfficeId`);

ALTER TABLE PartnerNoteTopics
ADD COLUMN isVisibleToPartner TINYINT(1) DEFAULT 0 AFTER f1,
ADD COLUMN createdOn TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP AFTER isVisibleToPartner,
ADD COLUMN createdBy INT NULL AFTER createdOn,
ADD COLUMN modifiedOn TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP AFTER createdBy,
ADD COLUMN modifiedBy INT NULL AFTER modifiedOn;

ALTER TABLE PartnerNotes
ADD COLUMN hasRead TINYINT(1) DEFAULT 0 AFTER `partnerNote`;

---------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------- Alter script for Admin QS on 17th November 2015-------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------

DROP TABLE `AdminQuickStatsTypeAggregate`;

ALTER TABLE `cci_gh_go_login`.`AdminQuickStatsCategoryAggregate` 
DROP INDEX `FK_AdminQSCategoryAggregate_LookupDepartmentPrograms`, 
DROP FOREIGN KEY `FK_AdminQSCategoryAggregate_LookupDepartmentPrograms`,
DROP COLUMN `lookupdepartmentProgramId`,
ADD COLUMN `adminQSCategoryAggregate` INT NULL AFTER `adminQSCategoryName`, 
ADD COLUMN `status` VARCHAR(50) NULL AFTER `adminQSCategoryAggregate`; 

ALTER TABLE `cci_gh_go_login`.`AdminQuickStatsCategoryAggregate` 
DROP INDEX `FK_AdminQSCategoryAggregate_CCIStaffUSers`, 
DROP FOREIGN KEY `FK_AdminQSCategoryAggregate_CCIStaffUSers`,
DROP COLUMN `adminGoId`;

---------------------------------------------------------------------------------------------------------------------------------------------------
-- Alter script for adding followup Date column in PartnerSeason,PartnerSeasonAllocations, Partner Notes tables on 25th November 2015 -------------
---------------------------------------------------------------------------------------------------------------------------------------------------

ALTER TABLE PartnerNotes ADD COLUMN followupDate DATETIME;

ALTER TABLE `PartnerSeason` ADD COLUMN appDeadlineFollowupDate DATETIME, ADD COLUMN appSecSemDeadlineFollowupDate DATETIME;

ALTER TABLE `PartnerSeasonAllocation` ADD COLUMN janStartFollowupDate DATETIME, ADD COLUMN augStartFollowupDate DATETIME;


---------------------------------------------------------------------------------------------------------------------------------------------------
-- Alter script for adding column in PartenrUser and delete PartnerContact tables on 23rd December 2015 -------------
---------------------------------------------------------------------------------------------------------------------------------------------------
 ALTER TABLE `PartnerUser` ADD COLUMN recieveNotificationEmails TINYINT(1) DEFAULT 0;
 ALTER TABLE `PartnerUser` ADD COLUMN website VARCHAR (50);
 DROP TABLE  `PartnerContact`;
 
 --------------------------------------------------------------------------------------------------------------------------------------------
-- Alter script for adding column in Partner, FS on 2nd Feb 2016  -------------
---------------------------------------------------------------------------------------------------------------------------------------------
ALTER TABLE PartnerOffice ADD COLUMN email VARCHAR(100);

ALTER TABLE Partner ADD COLUMN cciGeneralContact INT(11),
 ADD CONSTRAINT `FK_Partner_CCIStaffUsers` FOREIGN KEY (`cciGeneralContact`) REFERENCES `CCIStaffUsers`(`cciStaffuserId`) ON UPDATE NO ACTION;
 

ALTER TABLE FieldStaff ADD COLUMN bestNumberHome TINYINT(1) DEFAULT 0;
ALTER TABLE FieldStaff ADD COLUMN bestNumberWork TINYINT(1) DEFAULT 0;
ALTER TABLE FieldStaff ADD COLUMN bestNumberCell TINYINT(1) DEFAULT 0;

ALTER TABLE FieldStaffStatus ADD COLUMN isSeasonStatus TINYINT(1);

ALTER TABLE FieldStaffAnnouncement ADD COLUMN departmentProgramId INT,
	ADD CONSTRAINT `FK_FieldStaffAnnouncement_DepartmentPrograms` FOREIGN KEY (`departmentProgramId`) REFERENCES `DepartmentPrograms`(`departmentProgramId`) ON UPDATE NO ACTION;
	
ALTER TABLE FieldStaffAnnouncement ADD COLUMN fieldStaffGoId INT,
	ADD CONSTRAINT `FK_FieldStaffAnnouncement_FieldStaff` FOREIGN KEY (`fieldStaffGoId`) REFERENCES `FieldStaff`(`fieldStaffGoId`) ON UPDATE NO ACTION;
	
ALTER TABLE Participants DROP COLUMN email;





-- ----------------------------------------------------------------------------------------------------
-- Schema cci_gh_go
-- ----------------------------------------------------------------------------------------------------
SET FOREIGN_KEY_CHECKS = 0;
DROP SCHEMA IF EXISTS `cci_gh_go` ;
SET FOREIGN_KEY_CHECKS = 1;
CREATE SCHEMA IF NOT EXISTS `cci_gh_go` ;

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.Countries
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `LookupCountries` (
  `countryId` INT(3) NOT NULL AUTO_INCREMENT,
  `countryCode` VARCHAR(5) NOT NULL,
  `countryName` VARCHAR(50) NOT NULL,
  `countryFlag` VARCHAR(300) NULL,
  `isReqFinalSOAonDS` TINYINT(1) NOT NULL DEFAULT 0,
  `active` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`countryId`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.LookupUSStates
-- ----------------------------------------------------------------------------------------------------   
CREATE TABLE IF NOT EXISTS `LookupUSStates` (
  `usStatesId` INT(3) NOT NULL AUTO_INCREMENT,
  `stateName` VARCHAR(50) NOT NULL,
  `stateCode` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`usStatesId`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.LookupGender
-- ----------------------------------------------------------------------------------------------------   
CREATE TABLE IF NOT EXISTS `LookupGender` (
    `genderId` INT(3) NOT NULL AUTO_INCREMENT,
    `genderName` VARCHAR(1) NOT NULL,
    PRIMARY KEY(`genderId`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.LookupDepartments
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `LookupDepartments` (
  `departmentId` INT(3) NOT NULL AUTO_INCREMENT,
  `departmentName` VARCHAR(50) NOT NULL,
  `acronym` VARCHAR(10) NULL DEFAULT NULL,
  `isVisibleToSeason` TINYINT(1) NOT NULL DEFAULT 0,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`departmentId`)
);


-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.LookupDepartmentPrograms
-- ---------------------------------------------------------------------------------------------------- 
CREATE TABLE `LookupDepartmentPrograms` (
  `lookupDepartmentProgramId` INT(3) NOT NULL AUTO_INCREMENT,
  `departmentId` INT(3) NOT NULL,
  `programName` VARCHAR(50) NOT NULL,
  `description` VARCHAR(100) DEFAULT NULL,
  `createdBy` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`lookupDepartmentProgramId`),
  KEY `FK_LookupDepartmentPrograms_LookupDepartments` (`departmentId`),
  CONSTRAINT `FK_LookupDepartmentPrograms_LookupDepartments` 
  FOREIGN KEY (`departmentId`) 
  REFERENCES `LookupDepartments` (`departmentId`) 
  ON DELETE NO ACTION 
  ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.Salutation
-- ---------------------------------------------------------------------------------------------------- 
CREATE TABLE `Salutation`
(`salutationId` int(3) NOT NULL AUTO_INCREMENT,
 `salutationName` VARCHAR(30),
 `active` TINYINT(1) DEFAULT 0,
 PRIMARY KEY (`salutationId`));

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.GoIdSequence 
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `GoIdSequence` (
  `goId` INT(3) NOT NULL AUTO_INCREMENT,
   PRIMARY KEY (`goId`)
)AUTO_INCREMENT = 1000;

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.UserType whether cciuser, lc, partner, participant etc
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `UserType` (
  `userTypeId` INT(3) NOT NULL AUTO_INCREMENT,
  `userTypeCode` VARCHAR(20) NOT NULL,
  `userTypeName` VARCHAR(50) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`userTypeId`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.Login
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `Login` (
  `loginId` INT(11) NOT NULL AUTO_INCREMENT,
  `goId` INT NOT NULL,
  `loginName` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `keyValue` VARCHAR(200) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn`TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`loginId`),
  UNIQUE INDEX `loginName` (`loginName` ASC),
  INDEX `FK_Login_GoIdSequence_idx` (`goId` ASC),
  CONSTRAINT `FK_Login_GoIdSequence`
    FOREIGN KEY (`goId`)
    REFERENCES `GoIdSequence` (`goId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.LoginHistory
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `LoginHistory` (
  `loginHistoryId` INT(11) NOT NULL AUTO_INCREMENT,
  `loggedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `loginId` INT(11) NOT NULL,
  `ipAddress` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`loginHistoryId`),
  INDEX `FK_History_Login` (`loginId` ASC),
  CONSTRAINT `FK_History_Login`
    FOREIGN KEY (`loginId`)
    REFERENCES `Login` (`loginId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.PasswordHistory
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `PasswordHistory` (
  `passwordHistoryId` INT(11) NOT NULL AUTO_INCREMENT,
  `password1` VARCHAR(40) NULL DEFAULT NULL,
  `password2` VARCHAR(40) NULL DEFAULT NULL,
  `password3` VARCHAR(40) NULL DEFAULT NULL,
  `password4` VARCHAR(40) NULL DEFAULT NULL,
  `loginId` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`passwordHistoryId`),
  INDEX `FK_PasswordHistory_Login` (`loginId` ASC),
  CONSTRAINT `FK_PasswordHistory_Login`
    FOREIGN KEY (`loginId`)
    REFERENCES `Login` (`loginId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.LoginUserType
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `LoginUserType` (
  `loginUserTypeId` INT(11) NOT NULL AUTO_INCREMENT,
  `loginId` INT(11) NOT NULL,
  `userTypeId` INT(3) NOT NULL,
  `defaultUserType` TINYINT(1) NOT NULL DEFAULT 0,
  `active` TINYINT(1) NOT NULL DEFAULT 0,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`loginUserTypeId`),
  INDEX `FK_LoginUserType_Login_idx` (`loginId` ASC),
  INDEX `FK_LoginUserType_UserType_idx` (`userTypeId` ASC),
  CONSTRAINT `FK_LoginUserType_Login`
    FOREIGN KEY (`loginId`)
    REFERENCES `Login` (`loginId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_LoginUserType_UserType`
    FOREIGN KEY (`userTypeId`)
    REFERENCES `UserType` (`userTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.DepartmentPrograms
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `DepartmentPrograms` (
  `departmentProgramId` INT(3) NOT NULL AUTO_INCREMENT,
  `departmentId` INT(3) NOT NULL,
  `programName` VARCHAR(50) NOT NULL,
  `description` VARCHAR(100) NULL DEFAULT NULL,
  `createdBy` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`departmentProgramId`),
  INDEX `FK_DepartmentPrograms_LookupDepartments` (`departmentId` ASC),
  CONSTRAINT `FK_DepartmentPrograms_LookupDepartments`
    FOREIGN KEY (`departmentId`)
    REFERENCES `LookupDepartments` (`departmentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------
-- Table cci_gh_go.DepartmentProgramOptions
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `DepartmentProgramOptions` (
  `departmentProgramOptionId` INT(11) NOT NULL AUTO_INCREMENT,
  `departmentProgramId` INT(11) NOT NULL,
  `programOptionCode` VARCHAR(10) NOT NULL,
  `programOptionName` VARCHAR(50) NOT NULL,
PRIMARY KEY (`departmentProgramOptionId`),
  INDEX `FK_DepartmentProgramOptions_DepartmentPrograms` (`departmentProgramId` ASC),
  CONSTRAINT `FK_DepartmentProgramOptions_DepartmentPrograms`
   FOREIGN KEY (`departmentProgramId`)
   REFERENCES `DepartmentPrograms` (`departmentProgramId`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION
);
    
    
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.ResourceActions actions such as add, edit, update delete on resources
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `ResourceActions` (
  `resourceActionId` INT(11) NOT NULL AUTO_INCREMENT,
  `resourceAction` VARCHAR(50) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 0,
  `visibleToUser` TINYINT(1) NOT NULL DEFAULT 1,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`resourceActionId`)
);
   
-- -------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffRoles such as program manager, director, sys admin etc
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CCIStaffRoles` (
  `cciStaffRoleId` INT(11) NOT NULL AUTO_INCREMENT,
  `cciStaffRoleName` VARCHAR(50) NOT NULL,
  `hierarchy` INT(11) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
PRIMARY KEY (`cciStaffRoleId`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffUsers
-- ---------------------------------------------------------------------------------------------------- 
CREATE TABLE IF NOT EXISTS `CCIStaffUsers` (
  `cciStaffUserId` INT(11) NOT NULL,
  `supervisorId` INT(11) NULL,
  `cciAdminGuid` VARCHAR(64) UNIQUE NOT NULL,
  `firstName` VARCHAR(30) NOT NULL,
  `lastName` VARCHAR(30) NOT NULL,
  `genderId` INT(3) NULL,
  `primaryPhone` VARCHAR(15) NULL,
  `emergencyPhone` VARCHAR(15) NULL,
  `phoneExtension` VARCHAR(10) NULL,
  `homeAddressLineOne` VARCHAR(100) NULL,
  `homeAddressLineTwo` VARCHAR(100) NULL,
  `city` VARCHAR(50) NULL,
  `usStatesId` INT(11) NULL,
  `zip` VARCHAR(50) NULL,
  `countryId` INT(11) NULL,
  `photo` VARCHAR(300) NULL,
  `sevisId` VARCHAR(20) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`cciStaffUserId`),
  CONSTRAINT `FK_CCIStaffUsers_LookupUSStates`
    FOREIGN KEY (`usStatesId`)
    REFERENCES `LookupUSStates` (`usStatesId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsers_LookupCountries`
    FOREIGN KEY (`countryId`)
    REFERENCES `LookupCountries` (`countryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsers_LookupGender`
    FOREIGN KEY (`genderId`)
    REFERENCES `LookupGender` (`genderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsers_GoIdSequence`
    FOREIGN KEY (`cciStaffUserId`)
    REFERENCES `GoIdSequence` (`goId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffUsersCCIStaffRoles
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CCIStaffUsersCCIStaffRoles` (  
  `cciStaffUserId` INT(11) NOT NULL,
  `cciStaffRoleId` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`cciStaffUserId`, `cciStaffRoleId`),
  CONSTRAINT `FK_CCIStaffUsersCCIStaffRoles_CCIStaffRoles`
    FOREIGN KEY (`cciStaffRoleId`)
    REFERENCES `CCIStaffRoles` (`cciStaffRoleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsersCCIStaffRoles_CCIStaffUsers`
    FOREIGN KEY (`cciStaffUserId`)
    REFERENCES `CCIStaffUsers` (`cciStaffUserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- --------------------------------------------------------------------------------------------------
-- Creating Table CCIStaffUserProgram
-- --------------------------------------------------------------------------------------------------
CREATE TABLE `CCIStaffUserProgram`( 
  `cciStaffUserId` INT(11) NOT NULL,
  `lookupDepartmentProgramId` INT(11) NOT NULL,  
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
    PRIMARY KEY (`cciStaffUserId`, `lookupDepartmentProgramId`), 
    CONSTRAINT `FK_CCIStaffUserProgram_CCIStaffUser` 
    FOREIGN KEY (`cciStaffUserId`) 
    REFERENCES `CCIStaffUsers`(`cciStaffUserId`), 
    CONSTRAINT `FK_CCIStaffUserProgram_LookupDepartmentPrograms` 
    FOREIGN KEY (`lookupDepartmentProgramId`) 
    REFERENCES `LookupDepartmentPrograms`(`lookupDepartmentProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);  

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.DepartmentResourceGroups such as seasons, SEVIS, accounting etc
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `DepartmentResourceGroups` (
  `departmentResourceGroupId` INT(11) NOT NULL AUTO_INCREMENT,
  `departmentId` INT(11) NOT NULL,
  `resourceGroupName` VARCHAR(50) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`departmentResourceGroupId`),
  CONSTRAINT `FK_DepartmentResourceGroups_LookupDepartments` 
   FOREIGN KEY (`departmentId`)
   REFERENCES `LookupDepartments`(`departmentId`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION
);

-- --------------------------------------------------------------------------------------------------
-- Table cci_gh_go.ResourcePermissions
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `ResourcePermissions` (
  `resourcePermissionId` INT(11) NOT NULL AUTO_INCREMENT,
  `departmentResourceGroupId` INT(11) NOT NULL,
  `resourceActionID` INT(11) NOT NULL,
  `resourceName` VARCHAR(50) NOT NULL,
  `resourceDescription` VARCHAR(200) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
   PRIMARY KEY (`resourcePermissionId`),
  CONSTRAINT `FK_ResourcePermissions_DepartmentResourceGroups`
    FOREIGN KEY (`departmentResourceGroupId`)
    REFERENCES `DepartmentResourceGroups` (`departmentResourceGroupId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ResourcePermissions_ResourceActions`
    FOREIGN KEY (`resourceActionId`)
    REFERENCES `ResourceActions` (`resourceActionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
    
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffUsersResourcePermissions
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CCIStaffUsersResourcePermissions` (  
  `cciStaffUserId` INT(11) NOT NULL,
  `departmentResourceGroupId` INT(11) NOT NULL,
  `resourcePermissionId` INT(11) NOT NULL,  
  `resourceActionId` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`cciStaffUserId`, `resourcePermissionId`),
  CONSTRAINT `FK_CCIStaffUsersResourcePermissions_CCIStaffUsers`
    FOREIGN KEY (`cciStaffUserId`)
    REFERENCES `CCIStaffUsers` (`cciStaffUserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsersResourcePermissions_ResourcePermissions`
    FOREIGN KEY (`resourcePermissionId`)
    REFERENCES `ResourcePermissions` (`resourcePermissionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsersResourcePermissions_DepartmentResourceGroups`
    FOREIGN KEY (`departmentResourceGroupId`)
    REFERENCES `DepartmentResourceGroups` (`departmentResourceGroupId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsersResourcePermissions_ResourceActions`
    FOREIGN KEY (`resourceActionId`)
    REFERENCES `ResourceActions` (`resourceActionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffRolesDepartments
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CCIStaffRolesDepartments` (
  `cciStaffRolesDepartmentId` INT(11) NOT NULL AUTO_INCREMENT,
  `cciStaffRoleId` INT(11) NOT NULL,
  `departmentId` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`cciStaffRolesDepartmentId`), 
  CONSTRAINT `FK_CCIStaffRolesDepartments_CCIStaffRoles`
    FOREIGN KEY (`cciStaffRoleId`)
    REFERENCES `CCIStaffRoles` (`cciStaffRoleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffRolesDepartments_LookupDepartments`
    FOREIGN KEY (`departmentId`)
    REFERENCES `LookupDepartments` (`departmentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
    
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffRolesDefaultResourcePermissions
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CCIStaffRolesDefaultResourcePermissions` (  
  `cciStaffRolesDepartmentId` INT(11) NOT NULL,
  `departmentResourceGroupId` INT(11) NOT NULL,
  `resourcePermissionId` INT(11) NOT NULL,
  `resourceActionId` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`cciStaffRolesDepartmentId`, `resourcePermissionId`),
 CONSTRAINT `FK_CCIStaffRolesDefaultPermissions_CCIStaffRolesDepartments`
    FOREIGN KEY (`cciStaffRolesDepartmentId`)
    REFERENCES `CCIStaffRolesDepartments` (`cciStaffRolesDepartmentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffRolesDefaultPermissions_ResourcePermissions`
    FOREIGN KEY (`resourcePermissionId`)
    REFERENCES `ResourcePermissions` (`resourcePermissionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffRolesDefaultPermissions_DepartmentResourceGroups`
    FOREIGN KEY (`departmentResourceGroupId`)
    REFERENCES `DepartmentResourceGroups` (`departmentResourceGroupId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffRolesDefaultPermissions_ResourceActions`
    FOREIGN KEY (`resourceActionId`)
    REFERENCES `ResourceActions` (`resourceActionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ---------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffUserNotes
-- -----------------------------------------------------------------------------------------------------    
CREATE TABLE IF NOT EXISTS `CCIStaffUserNotes`(
  `cciStaffUserNoteId` INT(11) NOT NULL AUTO_INCREMENT,
  `ccistaffuserID` INT(11) NOT NULL,
  `note` VARCHAR(1000) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,    
 PRIMARY KEY (`cciStaffUserNoteId`),
 CONSTRAINT `FK_CCIStaffUserNotes_CCIStaffUsers`
   FOREIGN KEY (`cciStaffUserId`)
   REFERENCES `CCIStaffUsers` (`cciStaffUserId`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION
);

-- -----------------------------------------------------------------------------------------------------------------
-- SEASONS TABLES :Table cci_gh_go.SeasonStatus
-- -----------------------------------------------------------------------------------------------------------------
  CREATE TABLE IF NOT EXISTS `SeasonStatus` (
  `seasonStatusId` INT(3) NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(50) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`seasonStatusId`)
);

-- -----------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.FieldStaffAgreement
-- -----------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `FieldStaffAgreement` (
  `fieldStaffAgreementId` INT(3) NOT NULL ,
  `agreementName` VARCHAR(50) NOT NULL,
   PRIMARY KEY (`fieldStaffAgreementId`)
);

-- -----------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.PaymentSchedule
-- -----------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `PaymentSchedule` (
  `paymentScheduleId` INT(3) NOT NULL ,
  `scheduleName` VARCHAR(50) NOT NULL,
   PRIMARY KEY (`paymentScheduleId`)
);
 
-- -----------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.Season
-- --------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `Season` (
  `seasonId` INT NOT NULL AUTO_INCREMENT,
  `seasonName` VARCHAR(50) UNIQUE NOT NULL,
  `seasonFullName` VARCHAR(50) NOT NULL,
  `departmentId` INT(3) NOT NULL,
  `seasonStatusId` INT(3) NOT NULL,
  `clonedSeasonName` VARCHAR(50) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT  NULL,
   PRIMARY KEY (`seasonId`),
  INDEX `FK_Season_SeasonStatus_idx` (`seasonStatusId` ASC),
  INDEX `FK_Season_LookupDepartments_idx` (`departmentId` ASC),
  CONSTRAINT `FK_Season_SeasonStatus`
    FOREIGN KEY (`seasonStatusId`)
    REFERENCES `SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Season_LookupDepartments`
    FOREIGN KEY (`departmentId`)
    REFERENCES `LookupDepartments` (`departmentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


-- ------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonWnTSummerDetails
-- ------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonWnTSummerDetails` (
  `seasonWnTSummerDetailsId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT(11) NOT NULL,
  `programName` VARCHAR(50),
  `startDate` DATETIME,
  `endDate` DATETIME,
  `applicationDeadlineDate` DATETIME,
  `isJobBoardOpen` TINYINT(1) DEFAULT 0,
  `maxPendingJobApps` INT,
  `programStatusId` INT(3) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonWnTSummerDetailsId`),
  UNIQUE KEY (`programName`),
  INDEX `FK_SeasonWnTSummerDetails_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonWnTSummerDetails_SeasonStatus_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_seasonWnTSummerDetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_seasonWnTSummerDetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonWnTWinterDetails
-- -------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonWnTWinterDetails` (
  `seasonWnTWinterDetailsId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT(11) NOT NULL,
  `programName` VARCHAR(50),
  `startDate` DATETIME,
  `endDate` DATETIME,
  `applicationDeadlineDate` DATETIME,
  `isJobBoardOpen` TINYINT(1) DEFAULT 0,
  `maxPendingJobApps` INT,
  `programStatusId` INT(3),
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonWnTWinterDetailsId`),
  UNIQUE KEY (`programName`),
  INDEX `FK_seasonWnTWinterDetails_Season_idx` (`seasonId` ASC),
  INDEX `FK_seasonWnTWinterDetails_SeasonStatus_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_SeasonWnTWinterDetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonWnTWinterDetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonWnTSpringDetails
-- -------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonWnTSpringDetails` (
  `seasonWnTSpringDetailsId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT(11) NOT NULL,
  `programName` VARCHAR(50),
  `startDate` DATETIME,
  `endDate` DATETIME,
  `applicationDeadlineDate` DATETIME,
  `isJobBoardOpen` TINYINT(1) DEFAULT 0,
  `maxPendingJobApps` INT,
  `programStatusId` INT(3),
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonWnTSpringDetailsId`),
  UNIQUE KEY (`programName`),
  INDEX `FK_SeasonWnTSpringDetails_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonWnTSpringDetails_SeasonStatus_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_seasonWnTSpringDetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_seasonWnTSpringDetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
-- -------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonCAPDetails
-- -------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonCAPDetails` (
  `seasonCAPDetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `programName` VARCHAR(45),
  `internStartDate` DATETIME,
  `internEndDate` DATETIME,
  `internAppDeadlineDate` DATETIME,
  `traineeStartDate` DATETIME,
  `traineeEndDate` DATETIME,
  `traineeAppDeadlineDate` DATETIME,
  `programStatusId` INT(3),
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
   PRIMARY KEY (`seasonCAPDetailsId`),
  UNIQUE KEY (`programName`),
  CONSTRAINT `FK_SeasonCAPDetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonCAPdetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


-- -------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonWPAllocation
-- -------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonWPAllocation` (
  `seasonWPAllocationId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT(11) NOT NULL,
  `departmentProgramOptionId` INT(3) NOT NULL,
  `maxPax` INT DEFAULT 0,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP() ,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonWPAllocationId`),
  INDEX `FK_SeasonWPAloocation_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonWPAllocation_DepartmentProgramOptions_idx` (`departmentProgramOptionId` ASC),
  CONSTRAINT `FK_SeasonWPAllocation_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonWPAllocation_DepartmentProgramOptions`
    FOREIGN KEY (`departmentProgramOptionId`)
    REFERENCES `DepartmentProgramOptions` (`departmentProgramOptionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
 
-- ----------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonWPConfiguration
-- -----------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonWPConfiguration` (
  `seasonWPConfigurationId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `seasonStartDate` DATETIME NOT NULL,
  `seasonEndDate` DATETIME NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonWPConfigurationId`),
  INDEX `FK_SeasonWPConfiguration_Season_idx` (`seasonId` ASC),
  CONSTRAINT `FK_SeasonWPConfiguration_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
 
-- ------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonHSPConfiguration
-- ----------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonHSPConfiguration` (
  `seasonHSPConfigurationId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `seasonStartDate` DATETIME NOT NULL,
  `seasonEndDate` DATETIME NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonHSPConfigurationId`),
  INDEX `FK_SeasonHSPConfiguration_Season_idx` (`seasonId` ASC),
  CONSTRAINT `FK_SeasonHSPConfiguration_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
 
-- -------------------------------------------------------------------------------------------------------------------------- 
-- Table cci_gh_go.SeasonJ1Details
-- -------------------------------------------------------------------------------------------------------------
  CREATE TABLE IF NOT EXISTS `SeasonJ1Details` (
  `seasonJ1DetailsId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT(11) NOT NULL,
  `programName` VARCHAR(45) UNIQUE,
  `programStatusId` INT(3),
  `secondSemStartDate` DATETIME,
  `secondSemEndDate` DATETIME,
  `secondSemAppDeadlineDate` DATETIME,
  `secondSemEarliestBirthDate` DATETIME,
  `secondSemLatestBirthDate` DATETIME,
  `showSecondSemToNewHF` TINYINT(1) DEFAULT 0,
  `activeFullYearJanProgram` TINYINT(1) DEFAULT 0,
  `janFullYearStartDate` DATETIME,
  `janFullYearAppDeadlineDate` DATETIME,
  `janFullYearEndDate` DATETIME,
  `showJanFullYearToNewHF` TINYINT(1) DEFAULT 0,
  `firstSemStartDate` DATETIME,
  `firstSemEndDate` DATETIME,
  `firstSemAppDeadlineDate` DATETIME,
  `firstSemEarliestBirthDate` DATETIME,
  `firstSemLatestBirthDate` DATETIME,
  `showFirstSemToNewHF` TINYINT(1) DEFAULT 0,
  `augFullYearStartDate` DATETIME,
  `augFullYearEndDate` DATETIME,
  `augFullYearAppDeadlineDate` DATETIME,
  `showAugFullYearToNewHF` TINYINT(1) DEFAULT 0,
  `showSeasonToCurrentHF` TINYINT(1) DEFAULT 0,
  `fieldStaffHoldLength` INT(3),
  `hoursBeforeHoldExpirationWarning` INT(3),
  `lcPaymentScheduleId` INT,
  `fsAgreementId` INT,
  `hfReferences` INT(3),
  `hfInquiryDate` DATE,
  `showWelcomeFamily` TINYINT(1) DEFAULT 0,
  `showGuaranteed` TINYINT(1) DEFAULT 0,
  `showUnguaranteed` TINYINT(1) DEFAULT 0,
  `showSpecialRequestStudent` TINYINT(1) DEFAULT 0,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
   PRIMARY KEY (`seasonJ1DetailsId`),
   INDEX `FK_J1HSPSeason_Season_idx` (`seasonId` ASC),
   INDEX `FK_SeasonJ1Details_Status_idx` (`programStatusId` ASC),
   INDEX `FK_seasonJ1Details_FieldStaffAgreement_idx` (`fsAgreementId` ASC),
   INDEX `FK_seasonJ1Details_PaymentSchedule_idx` (`lcPaymentScheduleId` ASC),
   CONSTRAINT `FK_SeasonJ1Details_Season`
   FOREIGN KEY (`seasonId`)
   REFERENCES `Season` (`seasonId`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION,
   CONSTRAINT `FK_SeasonJ1Details_SeasonStatus`
   FOREIGN KEY (`programStatusId`)
   REFERENCES `SeasonStatus` (`seasonStatusId`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION,
   CONSTRAINT `FK_seasonJ1Details_FieldStaffAgreement`
   FOREIGN KEY (`fsAgreementId`)
   REFERENCES cci_gh_go.FieldStaffAgreement (`fieldStaffAgreementId`)
   ON UPDATE NO ACTION
   ON DELETE NO ACTION,
   CONSTRAINT `FK_seasonJ1Details_PaymentSchedule` 
   FOREIGN KEY (`lcPaymentScheduleId`)
   REFERENCES cci_gh_go.PaymentSchedule (`paymentScheduleId`)
   ON UPDATE NO ACTION
   ON DELETE NO ACTION
);
 
-- -------------------------------------------------------------------------------------------------------------- 
-- Table cci_gh_go.SeasonF1Details
-- ------------------------------------------------------------------------------------------------------------------
 CREATE TABLE IF NOT EXISTS `SeasonF1Details` (
  `seasonF1DetailsId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT(11) NOT NULL,
  `programName`VARCHAR(45) UNIQUE,
  `programStatusId` INT(3),
  `secondSemStartDate` DATETIME,
  `secondSemEndDate` DATETIME,
  `secondSemAppDeadlineDate` DATETIME,
  `secondSemEarliestBirthDate` DATETIME,
  `secondSemLatestBirthDate` DATETIME,
  `showSecSemToNewHF` TINYINT(1) DEFAULT 0,
  `activeFullYearJanProgram` TINYINT(1) DEFAULT 0,
  `janFullYearStartDate` DATETIME,
  `janFullYearAppDeadlineDate` DATETIME,
  `janFullYearEndDate` DATETIME,
  `showJanFullYearToNewHF` TINYINT(1) DEFAULT 0,
  `firstSemStartDate` DATETIME,
  `firstSemEndDate` DATETIME,
  `firstSemAppDeadlineDate` DATETIME,
  `firstSemEarliestBirthDate` DATETIME,
  `firstSemLatestBirthDate` DATETIME,
  `showFirstSemToNewHF` TINYINT(1) DEFAULT 0,
  `augFullYearStartDate` DATETIME,
  `augFullYearEndDate` DATETIME,
  `augFullYearAppDeadlineDate` DATETIME,
  `showAugFullYearToNewHF` TINYINT(1) DEFAULT 0,
  `showSeasonToCurrentHF` TINYINT(1) DEFAULT 0,
  `lcPaymentScheduleId` INT(11),
  `fsAgreementId` INT(11),
  `hfReferences` INT(3),
  `hfInquiryDate` DATE,
  `showWelcomeFamily` TINYINT(1) DEFAULT 0,
  `allowFieldStaffToStartRenewalProcess` TINYINT(1) DEFAULT 0,
  `showSpecialRequestStudent` TINYINT(1) DEFAULT 0,
  `greenHeartMargin` INT(11),
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonF1DetailsId`),
  INDEX `FK_J1HSPSeason_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonF1Details_status_idx` (`programStatusId` ASC),
  INDEX `FK_seasonF1Details_FieldStaffAgreement_idx` (`fsAgreementId` ASC),
  INDEX `FK_seasonF1Details_PaymentSchedule_idx` (`lcPaymentScheduleId` ASC),
  CONSTRAINT `FK_SeasonF1Details_Season`
  FOREIGN KEY (`seasonId`)
  REFERENCES `Season` (`seasonId`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonF1Details_SeasonStatus`
  FOREIGN KEY (`programStatusId`)
  REFERENCES `SeasonStatus` (`seasonStatusId`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  CONSTRAINT `FK_seasonF1Details_FieldStaffAgreement`
  FOREIGN KEY (`fsAgreementId`)
  REFERENCES `FieldStaffAgreement` (`fieldStaffAgreementId`)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT `FK_seasonF1Details_PaymentSchedule` 
  FOREIGN KEY (`lcPaymentScheduleId`)
  REFERENCES `PaymentSchedule` (`paymentScheduleId`)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);

-- --------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonHSPAllocation
-- --------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonHSPAllocation` (
  `seasonHSPAllocationId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `maxGuaranteedPax` INT DEFAULT 0,
  `maxUnguaranteedPax` INT DEFAULT 0,
  `departmentProgramOptionId` INT(3) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonHSPAllocationId`),
  INDEX `FK_SeasonHSPAllocation_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonHSPAllocation_DepartmentProgramOptions_idx` (`departmentProgramOptionId` ASC),
  CONSTRAINT `FK_SeasonHSPAllocation_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonHSPAllocation_DepartmentProgramOptions`
    FOREIGN KEY (`departmentProgramOptionId`)
    REFERENCES `DepartmentProgramOptions` (`departmentProgramOptionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
 
-- -----------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonGHTConfiguration
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonGHTConfiguration` (
  `seasonGHTConfigurationId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `seasonStartDate` DATETIME NOT NULL,
  `seasonEndDate` DATETIME NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonGHTConfigurationId`),
  INDEX `FK_SeasonGHTConfiguration_Season_idx` (`seasonId` ASC),
  CONSTRAINT `FK_SeasonGHTConfiguration_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonHSADetails
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonHSADetails` (
  `seasonHSADetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `programName` VARCHAR(45) UNIQUE,
  `startDate` DATETIME,
  `endDate` DATETIME,
  `programStatusId` INT(3),
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
   PRIMARY KEY (`seasonHSADetailsId`),
  INDEX `FK_SeasonHighScoolAbroadDetails_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonHSADetails_Status_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_SeasonHSADetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonHSADetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
); 

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonLSDetails
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonLSDetails` (
  `seasonLSDetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `programName` VARCHAR(50) UNIQUE,  
  `startDate` DATETIME,
  `endDate` DATETIME,
  `programStatusId` INT(3),
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonLSDetailsId`),
  INDEX `FK_seasonLSDetails_season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonLSDetails_Status_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_SeasonLSDetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonLSDetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonTADetails
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonTADetails` (
  `seasonTADetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `programName` VARCHAR(50) UNIQUE,
  `startDate` DATETIME,
  `endDate` DATETIME,
  `programStatusId` INT(3),
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonTADetailsId`),
  INDEX `FK_seasonTADetails_season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonTADetails_status_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_seasonTADetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonTADetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonVADetails
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonVADetails` (
  `seasonVADetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `programName` VARCHAR(50) UNIQUE,
  `startDate` DATETIME,
  `endDate` DATETIME,
  `programStatusId` INT(3),
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonVADetailsId`),
  INDEX `FK_seasonVolunteerDetails_season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonVolunteerDetails_Status_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_SeasonVolunteerDetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonVolunteerDetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonWADetails
-- ----------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonWADetails` (
  `seasonWADetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `programName` VARCHAR(45) UNIQUE,
  `startDate` DATETIME,
  `endDate` DATETIME,
  `programStatusId` INT(3),
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonWADetailsId`),
  INDEX `FK_seasonWADetails_season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonWADetails_Status_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_seasonWADetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonWADetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.Region
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `Region` (
  `regionId` INT(3) NOT NULL AUTO_INCREMENT,
  `regionName` VARCHAR(50) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 0,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`regionId`)
);

-- -----------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.USSchool
-- -----------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `USSchool` (
  `usSchoolId` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`usSchoolId`)
); 

-- -----------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonUSSchoolSeasonStatus
-- -----------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `USSchoolSeason` (
  `usSchoolSeasonId` INT NOT NULL AUTO_INCREMENT,
  `usSchoolId` INT NOT NULL,
  `seasonId` INT NOT NULL,
  `schoolStartDate` DATETIME NOT NULL,
  `secondSemStartDate` DATETIME NOT NULL,
  `schoolEndDate` DATETIME NOT NULL,
  PRIMARY KEY (`usSchoolSeasonId`),
  INDEX `FK_USHighSchoolSeason_Season_idx` (`seasonId` ASC),
  INDEX `FK_USHighSchoolSeason_USHighSchool_idx` (`usSchoolId` ASC),
  CONSTRAINT `FK_USSchoolSeason_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_USSchoolSeason_USSchool`
    FOREIGN KEY (`usSchoolId`)
    REFERENCES `USSchool` (`usSchoolId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ---------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonDepartmentNotes
-- ---------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonDepartmentNotes` (
`seasonDepartmentNotesId` INT NOT NULL AUTO_INCREMENT,
`seasonId` INT NOT NULL,
`departmentNote` VARCHAR(1000),
`active` TINYINT(1) DEFAULT 0,
`createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
`createdBy` INT(11) NOT NULL,
`modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
`modifiedBy` INT(11) NOT NULL,
PRIMARY KEY (`seasonDepartmentNotesId`),
CONSTRAINT `FK_SeasonDepartmentNotes_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ---------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonProgramNotes
-- ---------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonProgramNotes` (
`seasonProgramNotesId` INT NOT NULL AUTO_INCREMENT,
`seasonId` INT NOT NULL,
`departmentProgramId` INT(3),
`programNote` VARCHAR(1000),
`active` TINYINT(1) DEFAULT 0,
`createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
`createdBy` INT(11) NOT NULL,
`modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
`modifiedBy` INT(11) NOT NULL,
PRIMARY KEY (`seasonProgramNotesId`),
CONSTRAINT `FK_SeasonProgramNotes_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `FK_SeasonProgramNotes_DepartmentPrograms`
    FOREIGN KEY (`departmentProgramId`)
    REFERENCES `DepartmentPrograms` (`departmentProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION 
);

-- -----------------------------------------------------
-- Table `DocumentType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DocumentType` (
  `documentTypeId` INT(11) NOT NULL AUTO_INCREMENT,
  `documentTypeName` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`documentTypeId`)
);

-- -----------------------------------------------------
-- Table `DocumentCategoryProcess`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DocumentCategoryProcess` (
  `documentCategoryProcessId` INT(11) NOT NULL AUTO_INCREMENT,
  `documentCategoryProcessName` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`documentCategoryProcessId`)
);

-- -----------------------------------------------------
-- Table `DocumentTypeDocumentCategoryProcess`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DocumentTypeDocumentCategoryProcess` (
  `documentTypeDocumentCategoryProcessId` INT(11) NOT NULL AUTO_INCREMENT,
  `documentTypeId` INT(11) NULL,
  `documentCategoryProcessId` INT(11) NULL,
  `documentTypeRole` VARCHAR(50) NULL,
  PRIMARY KEY (`documentTypeDocumentCategoryProcessId`),
  INDEX `FK_DocumentTypeDocumentCategoryProcess_DocumentType_idx` (`documentTypeId` ASC),
  INDEX `FK_DocumentTypeDocumentCategoryProcess_DocumentCateogoryPro_idx` (`documentCategoryProcessId` ASC),
  CONSTRAINT `FK_DocumentTypeDocumentCategoryProcess_DocumentType`
    FOREIGN KEY (`documentTypeId`)
    REFERENCES `DocumentType` (`documentTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_DocumentTypeDocumentCategoryProcess_DocumentCateogoryProcess`
    FOREIGN KEY (`documentCategoryProcessId`)
    REFERENCES `DocumentCategoryProcess` (`documentCategoryProcessId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `DocumentInformation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DocumentInformation` (
  `documentInformationId` INT(11) NOT NULL AUTO_INCREMENT,
  `documentTypeDocumentCategoryProcessId` INT(11) NULL,
  `documentName` VARCHAR(100) NULL,
  `fileName` VARCHAR(1000) NULL,
  `url` VARCHAR(1000) NULL,
  `active` TINYINT(1) DEFAULT 0,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NULL,
  PRIMARY KEY (`documentInformationId`),
  INDEX `FK_DocumentInformation_DocumentTypeDocumentCategoryProcess_idx` (`documentTypeDocumentCategoryProcessId` ASC),
  CONSTRAINT `FK_DocumentInformation_DocumentTypeDocumentCategoryProcess`
    FOREIGN KEY (`documentTypeDocumentCategoryProcessId`)
    REFERENCES `DocumentTypeDocumentCategoryProcess` (`documentTypeDocumentCategoryProcessId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `SeasonDepartmentDocument`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonDepartmentDocument` (
  `seasonDepartmentDocumentID` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `documentInformationId` INT(11) NOT NULL,
  `active` TINYINT(1) DEFAULT 0,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonDepartmentDocumentID`),
  INDEX `FK_SeasonDepartmentDocument_Season_idx` (`seasonId` ASC),  
  INDEX `FK_SeasonDepartmentDocument_DocumentInformation_idx` (`documentInformationId` ASC),
  CONSTRAINT `FK_SeasonDepartmentDocument_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,  
  CONSTRAINT `FK_SeasonDepartmentDocument_DocumentInformation`
    FOREIGN KEY (`documentInformationId`)
    REFERENCES `DocumentInformation` (`documentInformationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `SeasonProgramDocument`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonProgramDocument` (
  `seasonProgramDocumentId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `departmentProgramId` INT NOT NULL,
  `documentInformationId` INT(11) NOT NULL,
  `active` TINYINT(1) DEFAULT 0,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonProgramDocumentId`),
  INDEX `FK_SeasonProgramDocument_Season_idx` (`seasonId` ASC),  
  INDEX `FK_SeasonProgramDocument_DocumentInformation_idx` (`documentInformationId` ASC),
  INDEX `FK_SeasonProgramDocument_DepartmentPrograms_idx` (`departmentProgramId` ASC),  
  CONSTRAINT `FK_SeasonProgramDocument_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,  
  CONSTRAINT `FK_SeasonProgramDocument_DocumentInformation`
    FOREIGN KEY (`documentInformationId`)
    REFERENCES `DocumentInformation` (`documentInformationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonProgramDocument_DepartmentPrograms`
    FOREIGN KEY (`departmentProgramId`)
    REFERENCES `DepartmentPrograms` (`departmentProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `AddendumDocumentInformation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AddendumDocumentInformation` (
  `addendumDocumentInformationId` INT(11) NOT NULL AUTO_INCREMENT,
  `documentInformationId` INT(11) NOT NULL,
  `documentName` VARCHAR(50) NULL,
  `fileName` VARCHAR(50) NULL,
  `url` VARCHAR(1000) NULL,
  `active` TINYINT(1) DEFAULT 0,
  `updateDate` DATETIME NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`addendumDocumentInformationId`),
  INDEX `FK_AddendumDocumentInformation_DocumentInformation_idx` (`documentInformationId` ASC),
  CONSTRAINT `FK_AddendumDocumentInformation_DocumentInformation`
    FOREIGN KEY (`documentInformationId`)
    REFERENCES `DocumentInformation` (`documentInformationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
 );
 
 -- -----------------------------------------------------
-- Table `SeasonDepartmentUpdateLog`
-- -----------------------------------------------------
CREATE TABLE `SeasonDepartmentUpdateLog` (
  `updateDepartmentLogId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT(11) NOT NULL,
  `updateLogObject` LONGTEXT,
  `modifiedBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`updateDepartmentLogId`),
  INDEX `FK_SeasonDepartmentUpdateLog_Season_idx` (`seasonId` ASC),
  CONSTRAINT `FK_SeasonDepartmentUpdateLog_Season` 
    FOREIGN KEY (`seasonId`) 
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

 -- -----------------------------------------------------
-- Table `SeasonProgramUpdateLog`
-- -----------------------------------------------------
CREATE TABLE `SeasonProgramUpdateLog` (
  `updateProgramLogId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT(11) DEFAULT NULL,
  `departmentProgramId` INT(11) DEFAULT NULL,
  `updateLogObject` LONGTEXT,
  `modifiedBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`updateProgramLogId`),
  INDEX `FK_SeasonProgramUpdateLog_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonProgramUpdateLog_DepartmentPrograms_idx` (`departmentProgramId` ASC),
  CONSTRAINT `FK_SeasonProgramUpdateLog_Season` 
    FOREIGN KEY (`seasonId`) 
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonProgramUpdateLog_DepartmentPrograms` 
    FOREIGN KEY (`departmentProgramId`) 
    REFERENCES `DepartmentPrograms` (`departmentProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

 -- -----------------------------------------------------
-- Table `SeasonIHPDetails`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonIHPDetails` (
  `seasonIHPDetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `programName` VARCHAR(50) UNIQUE,
  `startDate` DATETIME,
  `endDate` DATETIME,
  `programStatusId` INT(3),
  `maxParticipants` INT,
  `lcHoldTime` INT(3),
  `numberOfLCToRequestHold` INT(3),
  `splitPlacementPending` INT(3),
  `stopAcceptingApps` TINYINT(1) DEFAULT 0,
  `stopAcceptingAppsByGender` TINYINT(1) DEFAULT 0,
  `genderId` INT(3),
  `applicationDeadLineWeeks` INT(3),
  `stopAcceptingAppsStandardIHP`  TINYINT(1) DEFAULT 0,
  `stopAcceptingAppsVolunteerHomestay`  TINYINT(1) DEFAULT 0,
  `stopAcceptingAppsLanguageBuddy`  TINYINT(1) DEFAULT 0,
  `stopAcceptingAppsHolidayHomestay`  TINYINT(1) DEFAULT 0,
  `stopAcceptingAppsHighSchoolVisits`  TINYINT(1) DEFAULT 0,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonIHPDetailsId`),
  INDEX `FK_SeasonIHPDetails_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonIHPDetails_LookupGender` (`genderId` ASC),
  INDEX `FK_SeasonIHPDetails_Status_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_SeasonIHPDetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonIHPDetails_LookupGender`
    FOREIGN KEY (`genderId`)
    REFERENCES `LookupGender` (`genderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonIHPDetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
); 

-- -----------------------------------------------------
-- Table `RegionIHP`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RegionIHP` (
  `regionIHPId` INT NOT NULL AUTO_INCREMENT,
  `regionName` VARCHAR(45),
  `active` TINYINT(1) DEFAULT 0,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP() ,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`regionIHPId`)
);
-- -----------------------------------------------------
-- Table `SeasonIHPDetailsRegionApplications`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonIHPDetailsRegionApplications` (
  `seasonIHPDetailsRegionApplicationId` INT NOT NULL AUTO_INCREMENT,  
  `seasonIHPDetailsId` INT,
  `regionIHPId` INT,
  `stopAcceptingApps`  TINYINT(1) DEFAULT 0,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonIHPDetailsRegionApplicationId`),
  INDEX `FK_SeasonIHPDetailsRegionApplications_SeasonIHPDetails_idx` (`seasonIHPDetailsId` ASC),
  INDEX `FK_SeasonIHPDetailsRegionApplications_RegionIHP_idx` (`regionIHPId` ASC),
  CONSTRAINT `FK_SeasonIHPDetailsRegionApplications_SeasonIHPDetails`
    FOREIGN KEY (`seasonIHPDetailsId`)
    REFERENCES `SeasonIHPDetails` (`seasonIHPDetailsId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonIHPDetailsRegionApplications_RegionIHP`
    FOREIGN KEY (`regionIHPId`)
    REFERENCES `RegionIHP` (`regionIHPId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION 
);

-- -----------------------------------------------------
-- Table `SeasonIHPGeographyConfiguration`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonIHPGeographyConfiguration` (
  `seasonIHPGeographyConfigurationId` INT NOT NULL AUTO_INCREMENT,
  `regionIHPId` INT,
  `usStatesId` INT,
  `seasonId` INT,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonIHPGeographyConfigurationId`),
  INDEX `FK_SeasonIHPGeographyConfiguration_Season_idx` (`seasonId` ASC), 
  INDEX `FK_SeasonIHPGeographyConfiguration_RegionIHP_idx` (`regionIHPId` ASC),
  INDEX `FK_SeasonIHPGeographyConfiguration_LookupUSStates_idx` (`usStatesId` ASC),
  CONSTRAINT `FK_SeasonIHPGeographyConfiguration_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonIHPGeographyConfiguration_RegionIHP`
    FOREIGN KEY (`regionIHPId`)
    REFERENCES `RegionIHP` (`regionIHPId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonIHPGeographyConfiguration_LookupUSStates`
    FOREIGN KEY (`usStatesId`)
    REFERENCES `LookupUSStates` (`usStatesId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION 
);

-- -----------------------------------------------------
-- Table `SuperRegion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperRegion` (
  `superRegionId` INT(3) NOT NULL AUTO_INCREMENT,
  `superRegionName` VARCHAR(50),
  `active` TINYINT(1) DEFAULT 0,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`superRegionID`)
);

 -- -----------------------------------------------------
-- Table `SeasonGeographyConfiguration`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonGeographyConfiguration` (
  `seasonGeographyConfigurationId` INT NOT NULL AUTO_INCREMENT,
  `superRegionId` INT(3),
  `regionId` INT(3) DEFAULT NULL,
  `usStatesId` INT(3) DEFAULT NULL,
  `seasonId` INT,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonGeographyConfigurationId`),
  INDEX `FK_SeasonGeographyConfiguration_SuperRegion_idx` (`superRegionId` ASC),
  INDEX `FK_SeasonGeographyConfiguration_Region_idx` (`regionId` ASC),
  INDEX `FK_SeasonGeographyConfiguration_LookupUSStates_idx` (`usStatesId` ASC),
  INDEX `FK_SeasonGeographyConfiguration_Season_idx` (`seasonId` ASC),
  CONSTRAINT `FK_SeasonGeographyConfiguration_SuperRegion`
    FOREIGN KEY (`superRegionId`)
    REFERENCES `SuperRegion` (`superRegionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,  
  CONSTRAINT `FK_SeasonGeographyConfiguration_Region`
    FOREIGN KEY (`regionId`)
    REFERENCES `Region` (`regionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION, 
  CONSTRAINT `FK_SeasonGeographyConfiguration_LookupUSStates`
    FOREIGN KEY (`usStatesId`)
    REFERENCES `LookupUSStates` (`usStatesId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonGeographyConfiguration_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `FieldStaffType`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `FieldStaffType` (
  `fieldStaffTypeId` INT NOT NULL AUTO_INCREMENT,
  `fieldStaffTypeCode` VARCHAR(10),
  `fieldStaffType` VARCHAR(50),
  PRIMARY KEY (`fieldStaffTypeId`)
 );
 
  -- -----------------------------------------------------
-- Table `FieldStaff`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `FieldStaff` (
  `fieldStaffId` INT NOT NULL AUTO_INCREMENT,
  `fieldStaffTypeId` INT,
  `firstName` VARCHAR(50),
  `lastName` VARCHAR(50),
  `photo` VARCHAR(100),
   PRIMARY KEY (`fieldStaffId`),
   CONSTRAINT `FK_FieldStaff_FieldStaffType`
    FOREIGN KEY (`fieldStaffTypeId`)
    REFERENCES `FieldStaffType` (`fieldStaffTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION   
);


-- -----------------------------------------------------
-- Table `FieldStaffLeadershipSeason`
-- ----------------------------------------------------- 
 CREATE TABLE IF NOT EXISTS `FieldStaffLeadershipSeason` (
  `fieldStaffLeadershipSeasonId` INT NOT NULL AUTO_INCREMENT,
  `fieldStaffId` INT,
  `seasonId` INT,
  `seasonGeographyConfigurationId` INT,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`fieldStaffLeadershipSeasonId`),
  INDEX `FK_FieldStaffLeadershipSeason_Season_idx` (`seasonId` ASC),
  INDEX `FK_FieldStaffLeadershipSeason_FieldStaff_idx` (`fieldStaffId` ASC),
  INDEX `FK_FieldStaffLeadershipSeason_SeasonGeographyConfiguration_idx` (`seasonGeographyConfigurationId` ASC),
  CONSTRAINT `FK_FieldStaffLeadershipSeason_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_FieldStaffLeadershipSeason_FieldStaff`
    FOREIGN KEY (`fieldStaffId`)
    REFERENCES `FieldStaff`(`fieldStaffId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,    
  CONSTRAINT `FK_FieldStaffLeadershipSeason_SeasonGeographyConfiguration`
    FOREIGN KEY (`seasonGeographyConfigurationId`)
    REFERENCES `SeasonGeographyConfiguration` (`seasonGeographyConfigurationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `FieldStaffLCSeason`
-- ----------------------------------------------------- 

CREATE TABLE IF NOT EXISTS `FieldStaffLCSeason` (
  `fieldStaffLCSeasonId` INT NOT NULL AUTO_INCREMENT,
  `fieldStaffId` INT,
  `departmentProgramId` INT(3),
  `seasonId` INT,
  `seasonGeographyConfigurationId` INT,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`fieldStaffLCSeasonId`),
  INDEX `FK_FieldStaffLCSeason_Season_idx` (`seasonId` ASC),
  INDEX `FK_FieldStaffLCSeason_FieldStaff_idx` (`fieldStaffId` ASC),
  INDEX `FK_FieldStaffLCSeason_DepartmentPrograms_idx` (`departmentProgramId` ASC),
  INDEX `FK_FieldStaffLCSeason_SeasonGeographyConfiguration_idx` (`seasonGeographyConfigurationId` ASC),
  CONSTRAINT `FK_FieldStaffLCSeason_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_FieldStaffLCSeason_FieldStaff`
    FOREIGN KEY (`fieldStaffId`)
    REFERENCES `FieldStaff`(`fieldStaffId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_FieldStaffLCSeason_DepartmentPrograms`
    FOREIGN KEY (`departmentProgramId`)
    REFERENCES `DepartmentPrograms` (`departmentProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_FieldStaffLCSeason_SeasonGeographyConfiguration`
    FOREIGN KEY (`seasonGeographyConfigurationId`)
    REFERENCES `SeasonGeographyConfiguration` (`seasonGeographyConfigurationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `HostFamily`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `HostFamily` (
  `hostFamilyGoId` INT NOT NULL,
  `hostFamilyStatusId` INT(3) NULL,
  PRIMARY KEY (`hostFamilyGoId`),
  CONSTRAINT `FK_HostFamily_GoIdSequence`
    FOREIGN KEY (`hostFamilyGoId`)
    REFERENCES `GoIdSequence` (`goId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `HostFamilyPermissions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS  `HostFamilyPermissions` (
  `hostFamilyPermissionsId` INT NOT NULL AUTO_INCREMENT,
  `hostFamilyGoId` INT NULL,
  PRIMARY KEY (`hostFamilyPermissionsId`),
  INDEX `FK_HostFamilyPermissions_HostFamily_idx` (`hostFamilyGoId` ASC),
  CONSTRAINT `FK_HostFamilyPermissions_HostFamily`
    FOREIGN KEY (`hostFamilyGoId`)
    REFERENCES `HostFamily` (`hostFamilyGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `FieldStaffPermissions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FieldStaffPermissions` (
  `fieldStaffPermissionsId` INT NOT NULL AUTO_INCREMENT,
  `fieldStaffGoId` INT NULL,
  PRIMARY KEY (`fieldStaffPermissionsId`),
  INDEX `FK_FieldStaffPermission_FieldStaff_idx` (`fieldStaffGoId` ASC),
  CONSTRAINT `FK_FieldStaffPermission_FieldStaff`
    FOREIGN KEY (`fieldStaffGoId`)
    REFERENCES `FieldStaff` (`fieldStaffId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `Employer`
-- -----------------------------------------------------
	
CREATE TABLE IF NOT EXISTS  `Employer` (
  `employerGoId` INT NOT NULL,
  `employerStatusId` INT(3) NULL,
  PRIMARY KEY (`employerGoId`),
  CONSTRAINT `FK_Employer_GoIdSequence`
    FOREIGN KEY (`employerGoId`)
    REFERENCES `GoIdSequence` (`goId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
	

-- -----------------------------------------------------
-- Table `EmployerPermissions`
-- -----------------------------------------------------	
CREATE TABLE IF NOT EXISTS `EmployerPermissions` (
  `empolyerPermissionsId` INT NOT NULL AUTO_INCREMENT,
  `employerGoId` INT NULL,
  PRIMARY KEY (`empolyerPermissionsId`),
  INDEX `FK_EmployerPermissions_Employer_idx` (`employerGoId` ASC),
  CONSTRAINT `FK_EmployerPermissions_Employer`
    FOREIGN KEY (`employerGoId`)
    REFERENCES `Employer` (`employerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
	
-- -----------------------------------------------------
-- Table `PartnerStatus`
-- -----------------------------------------------------	
	
CREATE TABLE IF NOT EXISTS  `PartnerStatus` (
  `partnerStatusId` INT NOT NULL AUTO_INCREMENT,
  `partnerStatusName` VARCHAR(50) NULL,
  `active` TINYINT(1) DEFAULT 0,
  PRIMARY KEY (`partnerStatusId`))
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `ParticipantStatus`
-- -----------------------------------------------------

CREATE TABLE `ParticipantStatus` (
  `participantStatusId` INT(3) NOT NULL AUTO_INCREMENT,
  `participantStatusName` VARCHAR(50) NOT NULL,
  `active` TINYINT(1) DEFAULT 0,
  PRIMARY KEY (`participantStatusId`))
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `Partner`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `Partner` (
  `partnerGoId` INT NOT NULL,
  `companyName` VARCHAR(250) NULL,
  `acronym` VARCHAR(150) NULL,
  `dandBNumber` INT(11) NULL,
  `receiveAYPMails` TINYINT(1) DEFAULT 0,
  `subscribeToCCINewsletter` TINYINT(1) DEFAULT 0,
  `contractSigner` VARCHAR(50) NULL,
  `quickbooksCode` VARCHAR(40) NULL,
  `canHaveSubPartner` TINYINT(1) DEFAULT 0,
  `hasSubPartners` TINYINT(1) DEFAULT 0,
  `invoiceMail` VARCHAR(100) NULL,
  `multiCountrySender` TINYINT(1) DEFAULT 0,
  `isSubPartner` TINYINT(1) DEFAULT 0,
  `parentPartnerGoId` INT(11) NULL,
  `payGreenheartDirectly` TINYINT(1) DEFAULT 0,
  `deliverDSForms` TINYINT(1) DEFAULT 0,
  `needPartnerReview` TINYINT(1) DEFAULT 0,
  `mailingAddressIsSameAsPhysicalAdress` TINYINT(1) DEFAULT 0,
  `addressLineOne` VARCHAR(150) NULL,
  `addressLineTwo` VARCHAR(150) NULL,
  `city` VARCHAR(50) NULL,
  `zipcode` VARCHAR(15) NULL,
  `state` VARCHAR(50) NULL,
  `countryId` INT(3) NULL,
  `email` VARCHAR(100) DEFAULT NULL,
  `partnerLogo` VARCHAR(300) DEFAULT NULL,
  `physicalAddressLineOne` VARCHAR(150) NULL,
  `physicalAddressLineTwo` VARCHAR(150) NULL,
  `physicalCity` VARCHAR(50) NULL,
  `physicalZipcode` VARCHAR(15) NULL,
  `physicalstate` VARCHAR(50) NULL,
  `physicalcountryId` INT(3) NULL,
  `partnerGuid` VARCHAR(64) NULL,
  `contactNotes` VARCHAR(2000) NULL,
  `billingNotes` VARCHAR(1000) NULL,
  `lastSelectedProgramId` INT(11) NULL,
  `participantMedicalReleaseRequired` TINYINT(1) DEFAULT 0,
  `participantSLEPRequired` TINYINT(1) DEFAULT 0,
  `participantTranscriptRequired` TINYINT(1) DEFAULT 0,
  `oldId` INT(11) NULL,
  `unguaranteedFormRequired` TINYINT(1) DEFAULT 0,
  `participantELTISRequired` TINYINT(1) DEFAULT 0,
  `createdBy` INT(11) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`partnerGoId`),
  INDEX `FK_Partner_LookupCountries_idx` (`physicalcountryId` ASC),
  INDEX `FK_Partner_LookupCountries1_idx` (`countryId` ASC),
 CONSTRAINT `FK_Partner_GoIdSequence`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `GoIdSequence` (`goId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Partner_LookupCountries`
    FOREIGN KEY (`physicalcountryId`)
    REFERENCES `LookupCountries` (`countryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Partner_LookupCountries1`
    FOREIGN KEY (`countryId`)
    REFERENCES `LookupCountries` (`countryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `PartnerSeason`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `PartnerSeason` (
  `partnerSeasonId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NULL,
  `partnerGoId` INT NULL,
  `departmentProgramId` INT(3) NULL,
  `partnerSeasonStatusId` INT(3) NULL,
  `canCreateSubPartner` TINYINT(1) DEFAULT 0,
  `insuranceProvidedByCCI` TINYINT(1)  DEFAULT 0,
  `sevisFeesPaidByCCI` TINYINT(1)  DEFAULT 0,
  `insuranceCarrierName` VARCHAR(200) NULL,
  `insurancePhoneNumber` VARCHAR(50) NULL,
  `insurancePolicyNumber` VARCHAR(100) NULL,
  `questionaireRequired` TINYINT(1)  DEFAULT 0,
  `questionnaireSubmittedOn` DATETIME ,
  `disableAddParticipant` TINYINT(1)  DEFAULT 0,
  `partnerSeasonStartDate` DATETIME ,
  `partnerSeasonEndDate` DATETIME ,
  `partnerSeasonAppDeadlineDate` DATETIME ,
  `partnerSeasonExtAppDeadlineDate` DATETIME ,
  `partnerSeasonSecSemDeadlineDate` DATETIME ,
  `partnerSeasonExtSecSemDeadlineDate` DATETIME,
  `contractScheduleId` INT NULL,
  `canAccessJobBoard` TINYINT(1)  DEFAULT 0,
  `partnerTaxCompanyId` INT NULL,
  `timelyReturnReportReceivedDate` DATETIME NULL,
  `originalsReceivedDate` DATETIME NULL,
  `participantPaysDeposit` TINYINT(1)  DEFAULT 0,
  `exceptionComments` VARCHAR(2000) NULL,
  `cciStaffUserId` INT(11) DEFAULT NULL,
  `active` TINYINT(1)  DEFAULT 0,
  `createdBy` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`partnerSeasonId`),
  INDEX `FK_PArtnerSeason_PartnerStatus_idx` (`partnerSeasonStatusId` ASC),
  INDEX `FK_PartnerSeason_Partner_idx` (`partnerGoId` ASC),
  INDEX `FK_PartnerSeason_Season_idx` (`seasonId` ASC),
  INDEX `FK_PartnerSeason_DepartmentPrograms_idx` (`departmentProgramId` ASC),
  CONSTRAINT `FK_PartnerSeason_PartnerStatus`
    FOREIGN KEY (`partnerSeasonStatusId`)
    REFERENCES `PartnerStatus` (`partnerStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerSeason_Partner`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerSeason_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerSeason_DepartmentPrograms`
    FOREIGN KEY (`departmentProgramId`)
    REFERENCES `DepartmentPrograms` (`departmentProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerSeason_CCIStaffUsers`
    FOREIGN KEY (`cciStaffUserId`)
    REFERENCES `CCIStaffUsers` (`cciStaffUserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION	)
ENGINE = INNODB;



-- -----------------------------------------------------
-- Table `PartnerSeasonAllocation`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `PartnerSeasonAllocation` (
  `partnerSeasonAllocationId` INT NOT NULL AUTO_INCREMENT,
  `partnerSeasonId` INT NULL,
  `departmentProgramOptionId` INT(3) NULL,
  `maxPax` INT NULL,
  `maxGuaranteedPax` INT NULL,
  `expectedPaxCount` INT NULL,
  `createdBy` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`partnerSeasonAllocationId`),
  INDEX `FK_PartnerSeasonAllocation_PartnerSeason_idx` (`partnerSeasonId` ASC),
  INDEX `FK_PartnerSeasonAllocation_DepartmentProgramOptions_idx` (`departmentProgramOptionId` ASC),
  CONSTRAINT `FK_PartnerSeasonAllocation_PartnerSeason`
    FOREIGN KEY (`partnerSeasonId`)
    REFERENCES `PartnerSeason` (`partnerSeasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerSeasonAllocation_DepartmentProgramOptions`
    FOREIGN KEY (`departmentProgramOptionId`)
    REFERENCES `DepartmentProgramOptions` (`departmentProgramOptionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `PartnerOfficeType`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS  `PartnerOfficeType` (
  `partnerOfficeTypeId` INT NOT NULL AUTO_INCREMENT,
  `partnerOfficeType` VARCHAR(50) NULL,
  PRIMARY KEY (`partnerOfficeTypeId`))
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `PartnerOffice`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `PartnerOffice` (
  `partnerOfficeId` INT NOT NULL AUTO_INCREMENT,
  `partnerOfficeTypeId` INT(3) NULL,
  `partnerGoId` INT NULL,
  `adressOne` VARCHAR(150) NULL,
  `adressTwo` VARCHAR(150) NULL,
  `city` VARCHAR(30) NULL,
  `state` VARCHAR(30) NULL,
  `postalCode` VARCHAR(13) NULL,
  `countryId` INT NULL,
  `faxNumber` VARCHAR(150) NULL,
  `phoneNumber` VARCHAR(150) NULL,
  `website` VARCHAR(150) NULL,
  `officeNotes` VARCHAR(2000) NULL,
  `createdBy` INT(11) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`partnerOfficeId`),
  INDEX `FK_PartnerOffice_Partner_idx` (`partnerGoId` ASC),
  INDEX `FK_PartnerOffice_LookupCountries_idx` (`countryId` ASC),
  INDEX `FK_PartnerOffice_PartnerOfficeType_idx` (`partnerOfficeTypeId` ASC),
  CONSTRAINT `FK_PartnerOffice_Partner`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerOffice_LookupCountries`
    FOREIGN KEY (`countryId`)
    REFERENCES `LookupCountries` (`countryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerOffice_PartnerOfficeType`
    FOREIGN KEY (`partnerOfficeTypeId`)
    REFERENCES `PartnerOfficeType` (`partnerOfficeTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `PartnerAgentInquiries`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `PartnerAgentInquiries` (
  `partnerAgentInquiriesId` INT NOT NULL AUTO_INCREMENT,
  `partnerAgentGoId` INT NULL,
  `companyName` VARCHAR(250) NULL,
  `salutationId` INT(3) NULL,
  `rating` INT(3) NULL,
  `firstName` VARCHAR(50) NULL,
  `lastName` VARCHAR(50) NULL,
  `adressLineOne` VARCHAR(150) NULL,
  `adressLineTwo` VARCHAR(150) NULL,
  `city` VARCHAR(30) NULL,
  `state` VARCHAR(30) NULL,
  `countryId` INT NULL,
  `phone` VARCHAR(25) NULL,
  `email` VARCHAR(50) NULL,
  `website` VARCHAR(50) NULL,
  `submittedOn` DATETIME NULL,
  `teachAbroad` TINYINT(1) DEFAULT 0,
  `volunteerAbroad` TINYINT(1) DEFAULT 0,
  `highSchoolAbroad` TINYINT(1) DEFAULT 0,
  `other` TINYINT(1) DEFAULT 0,
  `ambassadorScholershipParticipants` TINYINT(1) DEFAULT 0,
  `currentlySendingParticipantToUS` TINYINT(1) DEFAULT 0,
  `businessYears` VARCHAR(50) NULL,
  `currentlyOfferingPrograms` VARCHAR(150) NULL,
  `howDidYouHearAboutCCI` VARCHAR(100) NULL,
  `followUpDate` DATETIME NULL,
  `businessName` VARCHAR(250),
  `countryFlag` VARCHAR(300) DEFAULT NULL,
  `logo` VARCHAR(300) DEFAULT NULL,
  `participantsForHomeCountry` TINYINT(4) DEFAULT 0,
  PRIMARY KEY (`partnerAgentInquiriesId`),
  INDEX `FK_PartnerAgentInquiriesPartner_idx` (`partnerAgentGoId` ASC),
  CONSTRAINT `FK_PartnerAgentInquiries_Partner`
    FOREIGN KEY (`partnerAgentGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerAgentInquiries_LookupCountries`
    FOREIGN KEY (`countryId`)
    REFERENCES `LookupCountries` (`countryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerAgentInquiries_Salutation`
    FOREIGN KEY (`salutationId`)
    REFERENCES `Salutation` (`salutationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION	)
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `PartnerProgram`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `PartnerProgram` (
  `partnerProgramId` INT NOT NULL AUTO_INCREMENT,
  `partnerGoId` INT NULL,
  `lookupDepartmentProgramId` INT NULL,
  `hasApplied` TINYINT(1) DEFAULT 0,
  `cciStaffUserId` INT(11),
  `isEligible` TINYINT(1) DEFAULT 0,
  PRIMARY KEY (`partnerProgramId`),
  INDEX `FK_PartnerProgramListDepartmentPrograms_idx` (`lookupDepartmentProgramId` ASC),
  INDEX `FK_PartnerAgentProgram_Partner_idx` (`partnerGoId` ASC),
  INDEX `FK_PartnerProgram_CCIStaffUser_idx` (`cciStaffUserId` ASC),
  CONSTRAINT `FK_PartnerProgram_LookupDepartmentPrograms`
    FOREIGN KEY (`lookupDepartmentProgramId`)
    REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerProgram_Partner`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
 CONSTRAINT `FK_PartnerProgram_CCIStaffUser`
    FOREIGN KEY (`cciStaffUserId`)
    REFERENCES `CCIStaffUsers` (`cciStaffUserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION	)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `PartnerMessages`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `PartnerMessages` (
  `partnerInquiryMessageId` INT NOT NULL AUTO_INCREMENT,
  `partnerInquiryMessage` VARCHAR(1000) NULL,
  `partnerGoId` INT NULL,
  `cciStaffUserId` INT NULL,
  `createdOn` DATETIME NULL,
  PRIMARY KEY (`partnerInquiryMessageId`),
  INDEX `FK_PartnerInquiryNotes_Partner_idx` (`partnerGoId` ASC),
  CONSTRAINT `FK_PartnerMessages_Partner`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
 CONSTRAINT `FK_PartnerMessages_CCIStaffUsers`
    FOREIGN KEY (`cciStaffUserId`)
    REFERENCES `CCIStaffUsers` (`cciStaffUserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `PartnerUser`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `PartnerUser` (
  `partnerUserId` INT NOT NULL AUTO_INCREMENT,
  `partnerGoId` INT NULL,
  `loginId` INT NULL,
  `genderId` INT(3) DEFAULT NULL,
  `salutationId` INT(3) NULL,
  `title` VARCHAR(150) NULL,
  `firstName` VARCHAR(150) NULL,
  `lastName` VARCHAR(150) NULL,
  `photo` varchar(300) DEFAULT NULL,
  `email` VARCHAR(50),
  `phone` VARCHAR(150) NULL,
  `emergencyPhone` VARCHAR(150) NULL,
  `isPrimary` TINYINT(1) DEFAULT 0,
  `fax` VARCHAR(150) NULL,
  `skypeId` VARCHAR(150) NULL,
  `active` TINYINT(1) DEFAULT 0,
  INDEX `FK_PartnerUser_Partner_idx` (`partnerGoId` ASC),
  INDEX `FK_PartnerUser_Login_idx` (`loginId` ASC),
  PRIMARY KEY (`partnerUserId`),
  CONSTRAINT `FK_PartnerUser_Partner`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerUser_Login`
    FOREIGN KEY (`loginId`)
    REFERENCES `Login` (`loginId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerUser_LookupGender`
    FOREIGN KEY (`genderId`)
    REFERENCES `LookupGender` (`genderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerUser_Salutation`
    FOREIGN KEY (`salutationId`)
    REFERENCES `Salutation` (`salutationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `PartnerDocument`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS  `PartnerDocument` (
  `partnerDocumentId` INT NOT NULL AUTO_INCREMENT,
  `partnerGoId` INT NULL,
  `documentInformationId` INT NULL,
  `partnerProgramId` INT NULL,
  `description` TEXT,
  PRIMARY KEY (`partnerDocumentId`),
  INDEX `FK_PartnerDocument_Partner_idx` (`partnerGoId` ASC),
  INDEX `FK_PartnerDocument_PartnerProgram_idx` (`partnerProgramId` ASC),
  INDEX `FK_PartnerDocument_DocumentInformation_idx` (`documentInformationId` ASC),
  CONSTRAINT `FK_PartnerDocument_Partner`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerDocument_PartnerProgram`
    FOREIGN KEY (`partnerProgramId`)
    REFERENCES `PartnerProgram` (`partnerProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerDocument_DocumentInformation`
    FOREIGN KEY (`documentInformationId`)
    REFERENCES `DocumentInformation` (`documentInformationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `PartnerAnnouncement`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS  `PartnerAnnouncement` (
  `partnerAnnouncementId` INT NOT NULL AUTO_INCREMENT,
  `partnerGoId` INT NULL,
  `seasonId` INT NOT NULL,
  `departmentProgramId` INT NOT NULL,
  `announcement` LONGTEXT NOT NULL,
  `title` VARCHAR(250) NOT NULL,
  `active` TINYINT(1) DEFAULT 0,
  `createdBy` INT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`partnerAnnouncementId`),
  INDEX `FK_PartnerAnnouncement_Partner_idx` (`partnerGoId` ASC),
  INDEX `FK_PartnerAnnouncement_DepartmentPrograms_idx` (`departmentProgramId` ASC),
  INDEX `FK_PartnerAnnouncement_Season_idx` (`seasonId` ASC),
  CONSTRAINT `FK_PartnerAnnouncement_Partner`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerAnnouncement_DepartmentPrograms`
    FOREIGN KEY (`departmentProgramId`)
    REFERENCES `DepartmentPrograms` (`departmentProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerAnnouncement_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `PartnerPermissions`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS  `PartnerPermissions` (
  `partnerPermissionsId` INT NOT NULL AUTO_INCREMENT,
  `partnerUserId` INT NOT NULL,
  `j1Admin` TINYINT(1) DEFAULT 0,
  `j1Applications` TINYINT(1) DEFAULT 0,
  `j1Flights` TINYINT(1) DEFAULT 0,
  `j1PlacementInfo` TINYINT(1) DEFAULT 0,
  `j1Monitoring` TINYINT(1) DEFAULT 0,
  `j1AccountingInsurance` TINYINT(1) DEFAULT 0,
  `j1StudentsPreProgram` TINYINT(1) DEFAULT 0,
  `j1Contracting` TINYINT(1) DEFAULT 0,
  `j1Insurance` TINYINT(1) DEFAULT 0,
  `f1Admin` TINYINT(1) DEFAULT 0,
  `f1Applications` TINYINT(1) DEFAULT 0,
  `f1Flights` TINYINT(1) DEFAULT 0,
  `f1PlacementInfo` TINYINT(1) DEFAULT 0,
  `f1Monitoring` TINYINT(1) DEFAULT 0,
  `f1AccountingInsurance` TINYINT(1) DEFAULT 0,
  `f1StudentsPreProgram` TINYINT(1) DEFAULT 0,
  `f1Contracting` TINYINT(1) DEFAULT 0,
  `f1Insurance` TINYINT(1) DEFAULT 0,
  `ihpAdmin` TINYINT(1) DEFAULT 0,
  `ihpApplications` TINYINT(1) DEFAULT 0,
  `ihpFlights` TINYINT(1) DEFAULT 0,
  `ihpPlacementInfo` TINYINT(1) DEFAULT 0,
  `ihpMonitoring` TINYINT(1) DEFAULT 0,
  `ihpAccountingInsurance` TINYINT(1) DEFAULT 0,
  `ihpStudentsPreProgram` TINYINT(1) DEFAULT 0,
  `ihpContracting` TINYINT(1) DEFAULT 0,
  `ihpInsurance` TINYINT(1) DEFAULT 0,
  `wtAdmin` TINYINT(1) DEFAULT 0,
  `wtApplications` TINYINT(1) DEFAULT 0,
  `wtFlights` TINYINT(1) DEFAULT 0,
  `wtPlacementInfo` TINYINT(1) DEFAULT 0,
  `wtMonitoring` TINYINT(1) DEFAULT 0,
  `wtAccountingInsurance` TINYINT(1) DEFAULT 0,
  `wtStudentsPreProgram` TINYINT(1) DEFAULT 0,
  `wtContracting` TINYINT(1) DEFAULT 0,
  `wtInsurance` TINYINT(1) DEFAULT 0,
  `capAdmin` TINYINT(1) DEFAULT 0,
  `capApplications` TINYINT(1) DEFAULT 0,
  `capFlights` TINYINT(1) DEFAULT 0,
  `capPlacementInfo` TINYINT(1) DEFAULT 0,
  `capMonitoring` TINYINT(1) DEFAULT 0,
  `capAccountingInsurance` TINYINT(1) DEFAULT 0,
  `capStudentsPreProgram` TINYINT(1) DEFAULT 0,
  `capContracting` TINYINT(1) DEFAULT 0,
  `capInsurance` TINYINT(1) DEFAULT 0,
  PRIMARY KEY (`partnerPermissionsId`),
  INDEX `FK_PartnerPermissions_Partner_idx` (`partnerUserId` ASC),
  CONSTRAINT `FK_PartnerPermissions_PartnerUser`
    FOREIGN KEY (`partnerUserId`)
    REFERENCES `PartnerUser` (`partnerUserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `Participants`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `Participants` (
  `participantGoId` INT NOT NULL,
  `firstName` VARCHAR(50) NULL,
  `lastName` VARCHAR(50) NULL,
  `partnerGoId` INT NULL,
  `countryId` INT NULL,
  `participantStatusId` INT NULL,
  `seasonId` INT NULL,
  `departmentProgramId` INT NULL,
  `startDate` DATETIME NULL,
  `endDate` DATETIME NULL,
  `guaranteed` TINYINT(1) DEFAULT 0,
  `submittedFlightInfo` TINYINT(1) NULL,
  `subPartnerId` INT NULL,
  `email` varchar(50) DEFAULT NULL,
  `isLead` tinyint(1) DEFAULT 0,
  `departmentProgramOptionId` int(3) DEFAULT NULL,
  `photo` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`participantGoId`),
  INDEX `FK_Participants_Partner_idx` (`partnerGoId` ASC),
  INDEX `FK_Participants_Partner1_idx` (`subPartnerId` ASC),
  INDEX `FK_Participant_LookupCountries_idx` (`countryId` ASC),
  INDEX `FK_Participants_Season_idx` (`seasonId` ASC),
  INDEX `FK_Participants_DepartmentProgram_idx` (`departmentProgramId` ASC),
  CONSTRAINT `FK_Participants_Partner`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Participants_Partner1`
    FOREIGN KEY (`subPartnerId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Participant_LookupCountries`
    FOREIGN KEY (`countryId`)
    REFERENCES `LookupCountries` (`countryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Participants_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Participants_DepartmentPrograms`
    FOREIGN KEY (`departmentProgramId`)
    REFERENCES `DepartmentPrograms` (`departmentProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
   CONSTRAINT `FK_Participants_DepartmentProgramOptions`
    FOREIGN KEY (`departmentProgramOptionId`)
    REFERENCES `DepartmentProgramOptions` (`departmentProgramOptionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
	)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `ParticipantPermissions`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS  `ParticipantPermissions` (
  `participantPermissionsId` INT NOT NULL AUTO_INCREMENT,
  `participantGoId` INT NULL,
  PRIMARY KEY (`participantPermissionsId`),
  INDEX `FK_ParticipantPermissions_Participant_idx` (`participantGoId` ASC),
  CONSTRAINT `FK_ParticipantPermissions_Participants`
    FOREIGN KEY (`participantGoId`)
    REFERENCES `Participants` (`participantGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `PartnerReviewStatus`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `PartnerReviewStatus` (
  `partnerReviewStatusId` INT NOT NULL AUTO_INCREMENT,
  `partnerGoId` INT NULL,
  `partnerLeadStatusId` INT(3) NULL,
  `partnerAgentStatusId` INT(3) NULL,
  `partnerStatusReason` VARCHAR(1000) NULL,
  `cciStaffUserId` INT NULL,
  PRIMARY KEY (`partnerReviewStatusId`),
  INDEX `FK_PartnerAgentStatusReasonPartnerStatus_idx` (`partnerLeadStatusId` ASC),
  INDEX `FK_PartnerAgentStatus_Partner_idx` (`partnerGoId` ASC),
  INDEX `FK_PartnerReviewStatus_PartnerStatus1_idx` (`partnerAgentStatusId` ASC),
  CONSTRAINT `FK_PartnerReviewStatus_Partner`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerReviewStatus_PartnerStatus`
    FOREIGN KEY (`partnerLeadStatusId`)
    REFERENCES `PartnerStatus` (`partnerStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerReviewStatus_PartnerStatus1`
    FOREIGN KEY (`partnerAgentStatusId`)
    REFERENCES `PartnerStatus` (`partnerStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerReviewStatus_CCIStaffUsers`
    FOREIGN KEY (`cciStaffUserId`)
    REFERENCES `CCIStaffUsers` (`cciStaffUserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `PartnerNoteTopics`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS  `PartnerNoteTopics` (
  `partnerNoteTopicId` INT NOT NULL AUTO_INCREMENT,
  `PartnerNoteTopicName` VARCHAR(50) NULL,
  `partnerGoId` INT NULL,
  `isPublic` TINYINT(1) DEFAULT 0,
  `w&t` TINYINT(1) DEFAULT 0,
  `j1` TINYINT(1) DEFAULT 0,
  `ght` TINYINT(1) DEFAULT 0,
  `stInbound` TINYINT(1) DEFAULT 0,
  `intern` TINYINT(1) DEFAULT 0,
  `trainee` TINYINT(1) DEFAULT 0,
  `meeting/visit` TINYINT(1) DEFAULT 0,
  `competitorInfo` TINYINT(1) DEFAULT 0,
  `embassy/VisaInfo` TINYINT(1) DEFAULT 0,
  `seasonInfo` TINYINT(1) DEFAULT 0,
  `f1` TINYINT(1) DEFAULT 0,
  PRIMARY KEY (`partnerNoteTopicId`),
  INDEX `FK_PartnerNoteTopics_Partner_idx` (`partnerGoId` ASC),
  CONSTRAINT `FK_PartnerNoteTopics_Partner`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `PartnerNotes`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `PartnerNotes` (
  `partnerNotesId` INT NOT NULL AUTO_INCREMENT,
  `partnerNoteTopicId` INT NULL,
  `partnerGoId` INT NULL,
  `partnerNote` LONGTEXT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`partnerNotesId`),
  INDEX `FK_PartnerNotes_Partner_idx` (`partnerGoId` ASC),
  INDEX `FK_PartnerNotes_PartnerNoteTopics_idx` (`partnerNoteTopicId` ASC),
  CONSTRAINT `FK_PartnerNotes_Partner`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerNotes_PartnerNoteTopics`
    FOREIGN KEY (`partnerNoteTopicId`)
    REFERENCES `PartnerNoteTopics` (`partnerNoteTopicId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `PartnerContact`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS  `PartnerContact` (
  `partnerContactId` INT NOT NULL AUTO_INCREMENT,
  `partnerGoId` INT NULL,
  `salutationId` INT(3) NULL,
  `isPrimary` TINYINT(1) DEFAULT 0,
  `firstName` VARCHAR(150) NULL,
  `lastName` VARCHAR(150) NULL,
  `title` VARCHAR(150) NULL,
  `partnerOfficeId` INT(11) NULL,
  `email` VARCHAR(150) NULL,
  `phone` VARCHAR(150) NULL,
  `emergencyPhone` VARCHAR(150) NULL,
  `fax` VARCHAR(150) NULL,
  `receiveNotificationEmails` TINYINT(1) DEFAULT 0,
  `website` VARCHAR(50) DEFAULT NULL,
  `skypeId` VARCHAR(50) NULL,
  `active` TINYINT(1) DEFAULT 0,
  `createdBy` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`partnerContactId`),
  INDEX `FK_partnerContact_Partner_idx` (`partnerGoId` ASC),
  INDEX `FK_PartnerContact_PartnerOffice_idx` (`partnerOfficeId` ASC),
  CONSTRAINT `FK_partnerContact_Partner`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerContact_PartnerOffice`
    FOREIGN KEY (`partnerOfficeId`)
    REFERENCES `PartnerOffice` (`partnerOfficeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerContact_Salutation`
    FOREIGN KEY (salutationId)
	REFERENCES `Salutation` (salutationId)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `PartnerReferenceChecks`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS  `PartnerReferenceChecks` (
  `partnerReferenceCheckId` INT NOT NULL AUTO_INCREMENT,
  `partnerGoId` INT NULL,
  `referenceCompletedOn` DATETIME NULL,
  `referenceCompletedBy` VARCHAR(50) NULL,
  `referenceApprovedOn` DATETIME NULL,
  `referenceApprovedBy` VARCHAR(50) NULL,
  `businessLicenseExpiryDate` DATETIME NULL,
  `referenceCheckNotes` LONGTEXT NULL,
  PRIMARY KEY (`partnerReferenceCheckId`),
  INDEX `FK_PartnerReferenceChecks_Partner_idx` (`partnerGoId` ASC),
  CONSTRAINT `FK_PartnerReferenceChecks_Partner`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `PartnerNoteTags`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS  `PartnerNoteTags` (
  `partnerNoteTagId` INT NOT NULL AUTO_INCREMENT,
  `tagName` VARCHAR(50) NULL,
  PRIMARY KEY (`partnerNoteTagId`))
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `PartnerUserRoles`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `PartnerUserRoles` (
  `partnerUserRoleId` INT NOT NULL AUTO_INCREMENT,
  `partnerUserRoleName` VARCHAR(50) NULL,
  `partnerUserId` INT(11) DEFAULT NULL,
  PRIMARY KEY (`partnerUserRoleId`),
  CONSTRAINT FK_PartnerUserRoles_PartnerUsers
  FOREIGN KEY (partnerUserId)
  REFERENCES PartnerUser (partnerUserId)
  )
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `PartnerSeasonContract`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS  `PartnerSeasonContract` (
  `partnerSeasonContractId` INT NOT NULL AUTO_INCREMENT,
  `partnerSeasonId` INT NULL,
  `isSigned` TINYINT(1) DEFAULT 0,
  `documentInformationId` INT,
  `description` TEXT,
  PRIMARY KEY (`partnerSeasonContractId`),
  INDEX `FK_PartnerSeasonContract_PartnerSeason_idx` (`partnerSeasonId` ASC),
  CONSTRAINT `FK_PartnerSeasonContract_PartnerSeason`
    FOREIGN KEY (`partnerSeasonId`)
    REFERENCES `PartnerSeason` (`partnerSeasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerSeasonContract_DocumentInformation`
    FOREIGN KEY (`documentInformationId`)
    REFERENCES `DocumentInformation` (`documentInformationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `PartnerSeasonDocument`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `PartnerSeasonDocument` (
  `partnerSeasonDocumentId` INT NOT NULL AUTO_INCREMENT,
  `partnerSeasonId` INT,
  `documentInformationId` INT,
  `description` TEXT,
  PRIMARY KEY (`partnerSeasonDocumentId`),
  INDEX `FK_PartnerSeasonDocument_PartnerSeason_idx` (`partnerSeasonId` ASC),
  INDEX `FK_PartnerSeasonDocument_DepartmentInformation_idx` (`documentInformationId` ASC),
  CONSTRAINT `FK_PartnerSeasonDocument_PartnerSeason`
    FOREIGN KEY (`partnerSeasonId`)
    REFERENCES `PartnerSeason` (`partnerSeasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerSeasonDocument_DocumentInformation`
    FOREIGN KEY (`documentInformationId`)
    REFERENCES `DocumentInformation` (`documentInformationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
	)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `PartnerHelpOption`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS  `PartnerHelpOption` (
  `partnerHelpOptionId` INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `partnerHelpOptionName` VARCHAR(50) NULL,
  `createdBy` INT(11) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `active` TINYINT(1) DEFAULT 0
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `PartnerHelpOptionProgram`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `PartnerHelpOptionProgram` (
  `partnerHelpOptionProgramId` INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `partnerHelpOptionId` INT(11) NULL,
  `lookupDepartmentProgramId` INT(11) NULL,
  `createdBy` INT(11) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    CONSTRAINT `FK_PartnerHelpOptionProgram_LookupDepartmentPrograms`
  FOREIGN KEY(`lookupDepartmentProgramId`)
  REFERENCES `LookupDepartmentPrograms`(`lookupDepartmentProgramId`)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `HelpContactMode`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `HelpContactMode` (
 `helpContactModeId` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
 `helpContactModeName` VARCHAR(50) NULL,
 `active` TINYINT(1) DEFAULT 0
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `PartnerHelpRequest`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `PartnerHelpRequest` (
  `partnerHelpRequestId` INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `partnerHelpRequestGUID` VARCHAR(64) NULL,
  `partnerHelpOptionId` INT(11) NULL,
  `helpContactModeId` INT(11) NULL,
  `partnerGoId` INT(11) NULL,
  `subPartnerGoId` INT(11) NULL,
  `loginId` INT(11) NULL,
  `lookupDepartmentProgramId` INT(11) NULL,
  `requestName` VARCHAR(200) NULL,
  `requestEmailAddress` VARCHAR(50) NULL,
  `requestMessage` VARCHAR(1000) NULL,
  `createdBy` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `active` TINYINT(1) DEFAULT 0,
  UNIQUE KEY `PartnerHelpRequestGUID` (`PartnerHelpRequestGUID`),
  CONSTRAINT `FK_PartnerHelpRequest_PartnerHelpOption`
  FOREIGN KEY(`partnerHelpOptionId`)
  REFERENCES `PartnerHelpOption`(`partnerHelpOptionId`)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT `FK_PartnerHelpRequest_Partner`
  FOREIGN KEY (`partnerGoId`)
  REFERENCES `Partner`(`partnerGoId`)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT `FK_PartnerHelpRequest_Partner1`
  FOREIGN KEY (`subPartnerGoId`)
  REFERENCES `Partner`(`partnerGoId`)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT `FK_PartnerHelpRequest_Login`
  FOREIGN KEY (`loginId`)
  REFERENCES `Login`(`loginId`)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT `FK_PartnerHelpRequest_LookupDepartmentPrograms`
  FOREIGN KEY (`lookupDepartmentProgramId`)
  REFERENCES `LookupDepartmentPrograms`(`lookupDepartmentProgramId`)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT `FK_PartnerHelpRequest_HelpContactMode`
  FOREIGN KEY (`helpContactModeId`)
  REFERENCES `HelpContactMode`(`helpContactModeId`)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `StateProcess`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StateProcess` (
  `stateProcessId` int(11) NOT NULL AUTO_INCREMENT,
  `stateProcessName` varchar(50) DEFAULT NULL,
  `workQueue` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`stateProcessId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `StateTypes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StateTypes` (
  `stateTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `stateName` varchar(45) DEFAULT NULL,
  `stateProcessId` int(11) DEFAULT NULL,
  `isLastStep` tinyint(1) DEFAULT 0,
  `workQueueRoleType` varchar(50) DEFAULT NULL,
  `workQueueTypeId` int(11) DEFAULT NULL,
  `workQueueCategoryId` int(11) DEFAULT NULL,
  `isVisibleToAdmin` tinyint(1) DEFAULT 0,
  `isVisibleToPartner` tinyint(1) DEFAULT 0,
  `isVisibleToParticipant` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`stateTypeId`),
  KEY `FK_StateTypes_Stateprocess` (`stateProcessId`),
  CONSTRAINT `FK_StateTypes_Stateprocess` 
  FOREIGN KEY (`stateProcessId`) 
  REFERENCES `StateProcess` (`stateProcessId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `StateActions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StateActions` (
  `stateActionId` int(11) NOT NULL AUTO_INCREMENT,
  `action` varchar(50) DEFAULT NULL,
  `stateTypeId` int(11) NOT NULL,
  PRIMARY KEY (`stateActionId`),
  KEY `FK_StateActions_StateTypes_idx` (`stateTypeId`),
  CONSTRAINT `FK_StateActions_StateTypes` 
  FOREIGN KEY (`stateTypeId`) 
  REFERENCES `StateTypes` (`stateTypeId`) 
  ON DELETE NO ACTION 
  ON UPDATE NO ACTION
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `StateTransitions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StateTransitions` (
  `stateTransitionId` int(11) NOT NULL AUTO_INCREMENT,
  `fromStateTypeId` int(11) NOT NULL,
  `toStateTypeId` int(11) NOT NULL,
  `fromRoletype` varchar(45) DEFAULT NULL,
  `toRoleType` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`stateTransitionId`),
  KEY `FK_Statetransitions_StateTypes_idx` (`fromStateTypeId`),
  KEY `FK_Statetransitions_StateTypes_1_idx` (`toStateTypeId`),
  CONSTRAINT `FK_Statetransitions_StateTypes` 
  FOREIGN KEY (`fromStateTypeId`) 
  REFERENCES `StateTypes` (`stateTypeId`) 
  ON DELETE NO ACTION 
  ON UPDATE NO ACTION,
  CONSTRAINT `FK_Statetransitions_StateTypes_1` 
  FOREIGN KEY (`toStateTypeId`) 
  REFERENCES `StateTypes` (`stateTypeId`) 
  ON DELETE NO ACTION 
  ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `StateTypeResourcePermission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StateTypeResourcePermission` (
  `stateTypeRPId` int(11) NOT NULL AUTO_INCREMENT,
  `stateTypeId` int(11) DEFAULT NULL,
  `resourcePermissionId` int(11) DEFAULT NULL,
  `lookupDepartmentProgramId` int(11) DEFAULT NULL,
  PRIMARY KEY (`stateTypeRPId`),
  KEY `FK_StateTypeRP_StateType` (`stateTypeId`),
  KEY `FK_StateTypeRP_ResourcePermissions` (`resourcePermissionId`),
  KEY `FK_StateTypeRP_LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_StateTypeRP_LookupDepartmentPrograms` 
  FOREIGN KEY (`lookupDepartmentProgramId`) 
  REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_StateTypeRP_ResourcePermissions` 
  FOREIGN KEY (`resourcePermissionId`) 
  REFERENCES `ResourcePermissions` (`resourcePermissionId`),
  CONSTRAINT `FK_StateTypeRP_StateType` 
  FOREIGN KEY (`stateTypeId`) 
  REFERENCES `StateTypes` (`stateTypeId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `PartnerQuickStatsType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PartnerQuickStatsType` (
  `partnerQSTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `partnerQSTypeName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`partnerQSTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `PartnerQuickStatsCategories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PartnerQuickStatsCategories` (
  `partnerQSCategoryId` int(11) NOT NULL AUTO_INCREMENT,
  `partnerQSCategoryName` varchar(50) DEFAULT NULL,
  `partnerQSTypeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`partnerQSCategoryId`),
  KEY `FK_PartnerQuickStatsCategories_PartnerQuickStatsType` (`partnerQSTypeId`),
  CONSTRAINT `FK_PartnerQuickStatsCategories_PartnerQuickStatsType` 
  FOREIGN KEY (`partnerQSTypeId`) 
  REFERENCES `PartnerQuickStatsType` (`partnerQSTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `PartnerQuickStatsTypeAggregate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PartnerQuickStatsTypeAggregate` (
  `partnerQSTypeAggregateId` int(11) NOT NULL AUTO_INCREMENT,
  `partnerQSTypeId` int(11) DEFAULT NULL,
  `partnerQSTypeName` varchar(50) DEFAULT NULL,
  `partnerQSTypeAggregate` int(11) DEFAULT NULL,
  `partnerGoId` int(11) DEFAULT NULL,
  `lookupDepartmentProgramId` int(11) DEFAULT NULL,
  `modifiedDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`partnerQSTypeAggregateId`),
  KEY `FK_PartnerQSTypeAggregate_PartnerQSType` (`partnerQSTypeId`),
  KEY `FK_PartnerQSTypeAggregate_Partner` (`partnerGoId`),
  KEY `FK_PartnerQSTypeAggregate_LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_PartnerQSTypeAggregate_LookupDepartmentPrograms` 
  FOREIGN KEY (`lookupDepartmentProgramId`) 
  REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_PartnerQSTypeAggregate_Partner` 
  FOREIGN KEY (`partnerGoId`) 
  REFERENCES `Partner` (`partnerGoId`),
  CONSTRAINT `FK_PartnerQSTypeAggregate_PartnerQSType` 
  FOREIGN KEY (`partnerQSTypeId`) 
  REFERENCES `PartnerQuickStatsType` (`partnerQSTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `PartnerQuickStatsCategoryAggregate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PartnerQuickStatsCategoryAggregate` (
  `PartnerQSCategoryAggregateId` int(11) NOT NULL AUTO_INCREMENT,
  `partnerQSCategoryId` int(11) DEFAULT NULL,
  `partnerQSCategoryName` varchar(50) DEFAULT NULL,
  `partnerQSTypeId` int(11) DEFAULT NULL,
  `partnerGoId` int(11) DEFAULT NULL,
  `lookupdepartmentProgramId` int(11) DEFAULT NULL,
  `modifiedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`PartnerQSCategoryAggregateId`),
  KEY `FK_PartnerQSCategoryAggregate_PartnerQSCategories` (`partnerQSCategoryId`),
  KEY `FK_PartnerQSCategoryAggregate_PartnerQSType` (`partnerQSTypeId`),
  KEY `FK_PartnerQSCategoryAggregate_Partner` (`partnerGoId`),
  KEY `FK_PartnerQSCategoryAggregate_LookupDepartmentPrograms` (`lookupdepartmentProgramId`),
  CONSTRAINT `FK_PartnerQSCategoryAggregate_LookupDepartmentPrograms` 
  FOREIGN KEY (`lookupdepartmentProgramId`) 
  REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_PartnerQSCategoryAggregate_Partner` 
  FOREIGN KEY (`partnerGoId`) 
  REFERENCES `Partner` (`partnerGoId`),
  CONSTRAINT `FK_PartnerQSCategoryAggregate_PartnerQSCategories` 
  FOREIGN KEY (`partnerQSCategoryId`) 
  REFERENCES `PartnerQuickStatsCategories` (`partnerQSCategoryId`),
  CONSTRAINT `FK_PartnerQSCategoryAggregate_PartnerQSType` 
  FOREIGN KEY (`partnerQSTypeId`) 
  REFERENCES `PartnerQuickStatsType` (`partnerQSTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- -----------------------------------------------------
-- Table `PartnerWorkQueueType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PartnerWorkQueueType` (
  `partnerWQTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `partnerWQTypeName` varchar(50) DEFAULT NULL,
  `lookupDepartmentProgramId` int(11) DEFAULT NULL,
  `stateProcessId` int(11) DEFAULT NULL,
  `displayWQTypeName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`partnerWQTypeId`),
  KEY `FK_PartnerWQType_LookupDepartment` (`lookupDepartmentProgramId`),
  KEY `FK_PartnerWQType_StateProcess` (`stateProcessId`),
  CONSTRAINT `FK_PartnerWQType_LookupDepartment` 
  FOREIGN KEY (`lookupDepartmentProgramId`)
  REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_PartnerWQType_StateProcess` 
  FOREIGN KEY (`stateProcessId`) 
  REFERENCES `StateProcess` (`stateProcessId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `PartnerWorkQueueCategories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PartnerWorkQueueCategories` (
  `partnerWQCategoryId` int(11) NOT NULL AUTO_INCREMENT,
  `partnerWQCategoryName` varchar(50) DEFAULT NULL,
  `partnerWQTypeId` int(11) NOT NULL,
  PRIMARY KEY (`partnerWQCategoryId`),
  KEY `FK_PartnerWorkQueueCategories_PartnerWorkQueueType_idx` (`partnerWQTypeId`),
  CONSTRAINT `FK_PartnerWorkQueueCategories_PartnerWorkQueueType` 
  FOREIGN KEY (`partnerWQTypeId`) 
  REFERENCES `PartnerWorkQueueType` (`partnerWQTypeId`) 
  ON DELETE NO ACTION 
  ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `PartnerWorkQueueCategoryAggregate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PartnerWorkQueueCategoryAggregate` (
  `partnerWQCategoryAggregateId` int(11) NOT NULL AUTO_INCREMENT,
  `partnerWQCategoryId` int(11) DEFAULT NULL,
  `partnerWQTypeId` int(11) DEFAULT NULL,
  `partnerWQCategoryName` varchar(50) DEFAULT NULL,
  `partnerGoId` int(11) DEFAULT NULL,
  `lookupDepartmentProgramId` int(11) DEFAULT NULL,
  `partnerWQCategoryAggregate` int(11) DEFAULT NULL,
  `modifiedDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`partnerWQCategoryAggregateId`),
  KEY `FK_PartnerWQCategoryAggregate_PartnerWQCategory` (`partnerWQCategoryId`),
  KEY `FK_PartnerWQCategoryAggregate_ParnerWQType` (`partnerWQTypeId`),
  KEY `FK_PartnerWQCategoryAggregate_Partner` (`partnerGoId`),
  KEY `FK_PartnerWQCategoryAggregate_LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_PartnerWQCategoryAggregate_LookupDepartmentPrograms` 
  FOREIGN KEY (`lookupDepartmentProgramId`) 
  REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_PartnerWQCategoryAggregate_ParnerWQType` 
  FOREIGN KEY (`partnerWQTypeId`) 
  REFERENCES `PartnerWorkQueueType` (`partnerWQTypeId`),
  CONSTRAINT `FK_PartnerWQCategoryAggregate_Partner` 
  FOREIGN KEY (`partnerGoId`) 
  REFERENCES `Partner` (`partnerGoId`),
  CONSTRAINT `FK_PartnerWQCategoryAggregate_PartnerWQCategory` 
  FOREIGN KEY (`partnerWQCategoryId`) 
  REFERENCES `PartnerWorkQueueCategories` (`partnerWQCategoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `PartnerWorkQueueTypeAggregate`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `PartnerWorkQueueTypeAggregate` (
  `partnerWQTypeAggregateId` INT(11) NOT NULL AUTO_INCREMENT,
  `partnerWQTypeId` INT(11) DEFAULT NULL,
  `partnerWQTypeName` VARCHAR(50) DEFAULT NULL,
  `partnerGoId` INT(11) DEFAULT NULL,
  `lookupDepartmentProgramId` INT(11) DEFAULT NULL,
  `partnerWQTypeAggregate` INT(11) DEFAULT NULL,
  `modifiedDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`partnerWQTypeAggregateId`),
  KEY `FK_PartnerWQTypeAggregate_PartnerWorkQueueType` (`partnerWQTypeId`),
  KEY `FK_PartnerWQTypeAggregate_Partner` (`partnerGoId`),
  KEY `FK_PartnerWQTypeAggregate_LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_PartnerWQTypeAggregate_LookupDepartmentPrograms` 
  FOREIGN KEY (`lookupDepartmentProgramId`) 
  REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_PartnerWQTypeAggregate_Partner` 
  FOREIGN KEY (`partnerGoId`) 
  REFERENCES `Partner` (`partnerGoId`),
  CONSTRAINT `FK_PartnerWQTypeAggregate_PartnerWorkQueueType` 
  FOREIGN KEY (`partnerWQTypeId`) 
  REFERENCES `PartnerWorkQueueType` (`partnerWQTypeId`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `PartnerWorkQueue`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PartnerWorkQueue` (
  `partnerWQId` int(11) NOT NULL AUTO_INCREMENT,
  `queueData` longtext,
  `createdDate` datetime DEFAULT NULL,
  `partnerGoId` int(11) DEFAULT NULL,
  `seasonId` int(11) DEFAULT NULL,
  `departmentProgramId` int(3) DEFAULT NULL,
  `partnerWQTypeId` int(11) DEFAULT NULL,
  `partnerWQCategoryId` int(11) DEFAULT NULL,
  `stateTypeId` int(11) DEFAULT NULL,
  `lookupDepartmentProgramId` int(3) DEFAULT NULL,
  `targetGoId` int(11) DEFAULT NULL,
  `targetRoleType` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`partnerWQId`),
  KEY `FK_PartnerWQ_Partner` (`partnerGoId`),
  KEY `FK_PartnerWQ_Season` (`seasonId`),
  KEY `FK_PartnerWQ_DepartmentProgram` (`departmentProgramId`),
  KEY `FK_PartnerWQ_PartnerWQType` (`partnerWQTypeId`),
  KEY `FK_PartnerWQ_PartnerWQCategory` (`partnerWQCategoryId`),
  KEY `FK_PartnerWQ_StateType` (`stateTypeId`),
  KEY `FK_PartnerWQ_LookupDepartmentPRogram` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_PartnerWQ_DepartmentProgram` 
  FOREIGN KEY (`departmentProgramId`) 
  REFERENCES `DepartmentPrograms` (`departmentProgramId`),
  CONSTRAINT `FK_PartnerWQ_LookupDepartmentPRogram` 
  FOREIGN KEY (`lookupDepartmentProgramId`) 
  REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_PartnerWQ_Partner` 
  FOREIGN KEY (`partnerGoId`) 
  REFERENCES `Partner` (`partnerGoId`),
  CONSTRAINT `FK_PartnerWQ_PartnerWQCategory` 
  FOREIGN KEY (`partnerWQCategoryId`) 
  REFERENCES `PartnerWorkQueueCategories` (`partnerWQCategoryId`),
  CONSTRAINT `FK_PartnerWQ_PartnerWQType` 
  FOREIGN KEY (`partnerWQTypeId`) 
  REFERENCES `PartnerWorkQueueType` (`partnerWQTypeId`),
  CONSTRAINT `FK_PartnerWQ_Season` 
  FOREIGN KEY (`seasonId`) 
  REFERENCES `Season` (`seasonId`),
  CONSTRAINT `FK_PartnerWQ_StateType` 
  FOREIGN KEY (`stateTypeId`) 
  REFERENCES `StateTypes` (`stateTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `AdminQuickStatsType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AdminQuickStatsType` (
  `adminQSTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `adminQSTypeName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`adminQSTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `AdminQuickStatsCategories`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `AdminQuickStatsCategories` (
  `adminQSCategoryId` int(11) NOT NULL AUTO_INCREMENT,
  `adminQSCategoryName` varchar(50) DEFAULT NULL,
  `adminQSTypeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`adminQSCategoryId`),
  KEY `FK_AdminQuickStatsCategories_AdminQuickStatsType_idx` (`adminQSTypeId`),
  CONSTRAINT `FK_AdminQuickStatsCategories_AdminQuickStatsType` 
  FOREIGN KEY (`adminQSTypeId`) 
  REFERENCES `AdminQuickStatsType` (`adminQSTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- -----------------------------------------------------
-- Table `AdminQuickStatsCategoryAggregate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AdminQuickStatsCategoryAggregate` (
  `adminQSCategoryAggregateId` int(11) NOT NULL AUTO_INCREMENT,
  `adminQSCategoryId` int(11) DEFAULT NULL,
  `adminQSCategoryName` varchar(50) DEFAULT NULL,
  `adminQSCategoryAggregate` INT NULL,
  `status` VARCHAR(50) NULL,
  `adminQSTypeId` int(11) DEFAULT NULL,
  `modifiedDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`adminQSCategoryAggregateId`),
  KEY `FK_AdminQSCategoryAggregate_AdminQSCategories` (`adminQSCategoryId`),
  KEY `FK_AdminQSCategoryAggregate_AdminQSType` (`adminQSTypeId`),
  CONSTRAINT `FK_AdminQSCategoryAggregate_AdminQSCategories` 
  FOREIGN KEY (`adminQSCategoryId`) 
  REFERENCES `AdminQuickStatsCategories` (`adminQSCategoryId`),
  CONSTRAINT `FK_AdminQSCategoryAggregate_AdminQSType` 
  FOREIGN KEY (`adminQSTypeId`) 
  REFERENCES `AdminQuickStatsType` (`adminQSTypeId`)
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `AdminWorkQueueType`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `AdminWorkQueueType` (
  `adminWQTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `adminWQTypeName` varchar(45) DEFAULT NULL,
  `lookupDepartmentProgramId` int(11) DEFAULT NULL,
  `roleType` VARCHAR(45),
  PRIMARY KEY (`adminWQTypeId`),
  KEY `FK_AdminWQType` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_AdminWQType` 
  FOREIGN KEY (`lookupDepartmentProgramId`) 
  REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `AdminWorkQueueCategories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AdminWorkQueueCategories` (
  `adminWorkQueueCategoryId` int(11) NOT NULL AUTO_INCREMENT,
  `adminWorkQueueCategoryName` varchar(45) DEFAULT NULL,
  `adminWQTypeId` int(11) NOT NULL,
  `roleType` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`adminWorkQueueCategoryId`),
  KEY `FK_AdminWorkQueueCategories_AdminWorkQueueType_idx` (`adminWQTypeId`),
  CONSTRAINT `FK_AdminWorkQueueCategories_AdminWorkQueueType` 
  FOREIGN KEY (`adminWQTypeId`) 
  REFERENCES `AdminWorkQueueType` (`adminWQTypeId`) 
  ON DELETE NO ACTION 
  ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `AdminWorkQueueTypeAggregate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AdminWorkQueueTypeAggregate` (
  `adminWQTypeAggregateId` int(11) NOT NULL AUTO_INCREMENT,
  `adminWQTypeId` int(11) DEFAULT NULL,
  `adminWQTypeName` varchar(50) DEFAULT NULL,
  `adminWQTypeAggregate` int(11) DEFAULT NULL,
  `adminGoId` int(11) DEFAULT NULL,
  `lookupDepartmentProgramId` int(11) DEFAULT NULL,
  `modifiedDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`adminWQTypeAggregateId`),
  KEY `FK_AdminWQTypeAggregate_AdminWorkQueueType_idx` (`adminWQTypeId`),
  KEY `FK_AdminWQTypeAggregate_CCIStaffUsers` (`adminGoId`),
  KEY `FK_AdminWQTypeAggregate_LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_AdminWQTypeAggregate_AdminWorkQueueType` 
  FOREIGN KEY (`adminWQTypeId`) 
  REFERENCES `AdminWorkQueueType` (`adminWQTypeId`),
  CONSTRAINT `FK_AdminWQTypeAggregate_CCIStaffUsers` 
  FOREIGN KEY (`adminGoId`) 
  REFERENCES `CCIStaffUsers` (`cciStaffUserId`),
  CONSTRAINT `FK_AdminWQTypeAggregate_LookupDepartmentPrograms` 
  FOREIGN KEY (`lookupDepartmentProgramId`) 
  REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `AdminWorkQueueCategoryAggregate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AdminWorkQueueCategoryAggregate` (
  `adminWQCategoryAggregateId` int(11) NOT NULL AUTO_INCREMENT,
  `adminWQCategoryId` int(11) DEFAULT NULL,
  `adminWQCategoryAggregate` int(11) DEFAULT NULL,
  `adminWQTypeId` int(11) DEFAULT NULL,
  `adminWQCategoryName` varchar(50) DEFAULT NULL,
  `modifiedDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `adminGoId` int(11) DEFAULT NULL,
  `lookupDepartmentProgramId` int(11) DEFAULT NULL,
  PRIMARY KEY (`adminWQCategoryAggregateId`),
  KEY `FK_AdminWQCategoryAggregate_AdminWQCategory_idx` (`adminWQCategoryId`),
  KEY `FK_AdminWQCategoryAggregate_AdminWQType` (`adminWQTypeId`),
  KEY `FK_AdminWQCategoryAggregate_CCIStaffUsers` (`adminGoId`),
  KEY `FK_AdminWQCategoryAggregate_LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_AdminWQCategoryAggregate_AdminWQCategory` 
  FOREIGN KEY (`adminWQCategoryId`) 
  REFERENCES `AdminWorkQueueCategories` (`adminWorkQueueCategoryId`),
  CONSTRAINT `FK_AdminWQCategoryAggregate_AdminWQType` 
  FOREIGN KEY (`adminWQTypeId`) 
  REFERENCES `AdminWorkQueueType` (`adminWQTypeId`),
  CONSTRAINT `FK_AdminWQCategoryAggregate_CCIStaffUsers` 
  FOREIGN KEY (`adminGoId`) 
  REFERENCES `CCIStaffUsers` (`cciStaffUserId`),
  CONSTRAINT `FK_AdminWQCategoryAggregate_LookupDepartmentPrograms` 
  FOREIGN KEY (`lookupDepartmentProgramId`) 
  REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `AdminWorkQueue`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AdminWorkQueue` (
  `adminWQId` int(11) NOT NULL AUTO_INCREMENT,
  `queueData` longtext,
  `adminWQTypeId` int(11) NOT NULL,
  `adminWQCategoryId` int(11) NOT NULL,
  `cciStaffUserGoId` int(11) DEFAULT NULL,
  `cciStaaffUserRole` varchar(45) DEFAULT NULL,
  `targetGoId` int(11) DEFAULT NULL,
  `targetRoleType` varchar(45) DEFAULT NULL,
  `seasonId` int(11) DEFAULT NULL,
  `departmentProgramId` int(11) DEFAULT NULL,
  `lookupDepartmentProgramId` int(11) DEFAULT NULL,
  `stateTypeId` int(11) DEFAULT NULL,
  `createdDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`adminWQId`),
  KEY `FK_AdminWorkQueue_AdminWorkQueueType_idx` (`adminWQTypeId`),
  KEY `FK_AdminWorkQueue_AdminWorkQueueCategories_idx` (`adminWQCategoryId`),
  KEY `FK_AdminWorkQueue_StateType_idx` (`stateTypeId`),
  CONSTRAINT `FK_AdminWorkQueue_AdminWorkQueueCategories` 
  FOREIGN KEY (`adminWQCategoryId`) 
  REFERENCES `AdminWorkQueueCategories` (`adminWorkQueueCategoryId`) 
  ON DELETE NO ACTION 
  ON UPDATE NO ACTION,
  CONSTRAINT `FK_AdminWorkQueue_AdminWorkQueueType` 
  FOREIGN KEY (`adminWQTypeId`) 
  REFERENCES `AdminWorkQueueType` (`adminWQTypeId`) 
  ON DELETE NO ACTION 
  ON UPDATE NO ACTION,
  CONSTRAINT `FK_AdminWorkQueue_StateType` 
  FOREIGN KEY (`stateTypeId`) 
  REFERENCES `StateTypes` (`stateTypeId`) 
  ON DELETE NO ACTION 
  ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `WorkQueueHistory` (
  `workQueueHistoryId` INT(11) NOT NULL AUTO_INCREMENT,
  `shooterGoId` INT(11) DEFAULT NULL,
  `shooterRoleType` VARCHAR(50) DEFAULT NULL,
  `targetGoId` INT(11) DEFAULT NULL,
  `targetRoleType` VARCHAR(50) DEFAULT NULL,
  `fromStateTypeId` INT(11) DEFAULT NULL,
  `toStateTypeId` INT(11) DEFAULT NULL,
  `createdDate` DATETIME DEFAULT NULL,
  PRIMARY KEY (`workQueueHistoryId`),
  CONSTRAINT `FK_WorkQueueHistory_StateType` 
  FOREIGN KEY (`toStateTypeId`) 
  REFERENCES `StateTypes` (`stateTypeId`) 
  ON DELETE NO ACTION 
  ON UPDATE NO ACTION,
  CONSTRAINT `FK_WorkQueueHistory_StateType` 
  FOREIGN KEY (`fromStateTypeId`) 
  REFERENCES `StateTypes` (`stateTypeId`) 
  ON DELETE NO ACTION 
  ON UPDATE NO ACTION,
  CONSTRAINT `FK_WorkQueueHistory_CCIStaffUsers` 
  FOREIGN KEY (`shooterGoId`) 
  REFERENCES `CCIStaffUsers` (`cciStaffUserId`) 
  ON DELETE NO ACTION 
  ON UPDATE NO ACTION  
) ENGINE=INNODB DEFAULT CHARSET=utf8;

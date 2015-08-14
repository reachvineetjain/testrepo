-- ----------------------------------------------------------------------------------------------------
-- Schema cci_gh_go
-- ----------------------------------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `cci_gh_go` ;
CREATE SCHEMA IF NOT EXISTS `cci_gh_go` ;

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.Countries
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`LookupCountries` (
  `countryId` INT(3) NOT NULL AUTO_INCREMENT,
  `countryCode` VARCHAR(5) NOT NULL,
  `countryName` VARCHAR(50) NOT NULL,
  `countryFlag` VARCHAR(300) NULL,
  `isReqFinalSOAonDS` TINYINT(1) NOT NULL,
  `active` TINYINT(1) NOT NULL,
  PRIMARY KEY (`countryId`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.USStates
-- ----------------------------------------------------------------------------------------------------   
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`LookupUSStates` (
  `usStatesId` INT(3) NOT NULL AUTO_INCREMENT,
  `stateName` VARCHAR(50) NOT NULL,
  `stateCode` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`usStatesId`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.LookupGender
-- ----------------------------------------------------------------------------------------------------   
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`LookupGender` (
    `genderId` INT(3) NOT NULL AUTO_INCREMENT,
    `genderName` VARCHAR(1) NOT NULL,
    PRIMARY KEY(`genderId`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.GoIdSequence 
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`GoIdSequence` (
  `goId` INT(3) NOT NULL AUTO_INCREMENT,
   PRIMARY KEY (`goId`)
)AUTO_INCREMENT = 1000;


-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.UserType whether cciuser, lc, partner, participant etc
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`UserType` (
  `userTypeId` INT(3) NOT NULL AUTO_INCREMENT,
  `userTypeCode` VARCHAR(20) NOT NULL,
  `userTypeName` VARCHAR(50) NOT NULL,
  `createdOn` TIMESTAMP NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP DEFAULT NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`userTypeId`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.OauthHistory
-- ---------------------------------------------------------------------------- -----------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`OauthHistory` (
  `nonce` VARCHAR(40) NOT NULL,
  `timeValue` DECIMAL(38,0) NOT NULL,
  PRIMARY KEY (`nonce`, `timeValue`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SysDiagrams
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SysDiagrams` (
  `name` VARCHAR(160) NOT NULL,
  `principalId` INT(11) NOT NULL,
  `diagramId` INT(11) NOT NULL AUTO_INCREMENT,
  `version` INT(11) NULL DEFAULT NULL,
  `definition` LONGBLOB NULL DEFAULT NULL,
  PRIMARY KEY (`diagramId`),
  UNIQUE INDEX `UK_principal_name` (`principalId` ASC, `name` ASC)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.Login
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`Login` (
  `loginId` INT(11) NOT NULL AUTO_INCREMENT,
  `goId` INT NOT NULL,
  `loginName` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `passwordSalt` VARCHAR(200) NOT NULL,
  `createdOn` TIMESTAMP  NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`loginId`),
  UNIQUE INDEX `loginName` (`loginName` ASC),
  INDEX `FK_Login_GoIdSequence_idx` (`goId` ASC),
  CONSTRAINT `FK_Login_GoIdSequence`
    FOREIGN KEY (`goId`)
    REFERENCES `cci_gh_go`.`GoIdSequence` (`goId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.History
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`LoginHistory` (
  `loginHistoryId` INT(11) NOT NULL AUTO_INCREMENT,
  `loggedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  `loginId` INT(11) NOT NULL,
  `ipAddress` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`loginHistoryId`),
  INDEX `FK_History_Login` (`loginId` ASC),
  CONSTRAINT `FK_History_Login`
    FOREIGN KEY (`loginId`)
    REFERENCES `cci_gh_go`.`Login` (`loginId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.PasswordHistory
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`PasswordHistory` (
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
    REFERENCES `cci_gh_go`.`Login` (`loginId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.LoginUserType
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`LoginUserType` (
  `loginUserTypeId` INT(11) NOT NULL AUTO_INCREMENT,
  `loginId` INT(11),
  `userTypeId` INT(3),
  `defaultUserType` TINYINT(1) DEFAULT 0,
  `active` TINYINT(1),
  `createdOn` TIMESTAMP  NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`loginUserTypeId`),
  INDEX `FK_LoginUserType_Login_idx` (`loginId` ASC),
  INDEX `FK_LoginUserType_UserType_idx` (`userTypeId` ASC),
  CONSTRAINT `FK_LoginUserType_Login`
    FOREIGN KEY (`loginId`)
    REFERENCES `cci_gh_go`.`Login` (`loginId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_LoginUserType_UserType`
    FOREIGN KEY (`userTypeId`)
    REFERENCES `cci_gh_go`.`UserType` (`userTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.LookupDepartments
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`LookupDepartments` (
  `departmentId` INT(3) NOT NULL,
  `departmentName` VARCHAR(50) NOT NULL,
  `acronym` VARCHAR(10) NULL DEFAULT NULL,
  `isVisibleToSeason` TINYINT(1) DEFAULT 0,
  `createdOn` TIMESTAMP NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP DEFAULT NOW(),
  `modifiedBy` INT(11) NOT NULL,
  `active` TINYINT(1) NOT NULL,
  PRIMARY KEY (`departmentId`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.DepartmentPrograms
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`DepartmentPrograms` (
  `departmentProgramId` INT(3) NOT NULL AUTO_INCREMENT,
  `departmentId` INT(3) NOT NULL,
  `programName` VARCHAR(50) NOT NULL,
  `description` VARCHAR(100) NULL DEFAULT NULL,
  `createdBy` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NULL,
  `modifiedBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`departmentProgramId`),
  INDEX `FK_DepartmentPrograms_LookupDepartments` (`departmentId` ASC),
  CONSTRAINT `FK_DepartmentPrograms_LookupDepartments`
    FOREIGN KEY (`departmentId`)
    REFERENCES `cci_gh_go`.`LookupDepartments` (`departmentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------
-- Table cci_gh_go.DepartmentProgramOptions
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`DepartmentProgramOptions` (
  `departmentProgramOptionId` INT(11) NOT NULL AUTO_INCREMENT,
  `departmentProgramId` INT(11) NOT NULL,
  `programOptionCode` VARCHAR(10) NOT NULL,
  `programOptionName` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`departmentProgramOptionId`),
  INDEX `FK_DepartmentProgramOptions_DepartmentPrograms` (`departmentProgramId` ASC),
  CONSTRAINT `FK_DepartmentProgramOptions_DepartmentPrograms`
   FOREIGN KEY (`departmentProgramId`)
   REFERENCES `cci_gh_go`.`DepartmentPrograms` (`departmentProgramId`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION
);
    
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.DepartmentFunctions
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`DepartmentFunctions` (
  `deptFunctionId` INT(11) NOT NULL AUTO_INCREMENT,
  `departmentId` INT(11) NOT NULL,
  `functionName` VARCHAR(100) NULL,
  `functionDescription` VARCHAR(200) NULL,
  `createdOn` TIMESTAMP  NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`deptFunctionId`)
);
    
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.ResourceActions actions such as add, edit, update delete on resources
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`ResourceActions` (
  `resourceActionId` INT(11) NOT NULL AUTO_INCREMENT,
  `resourceAction` VARCHAR(50) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  `visibleToUser` TINYINT(1) NOT NULL DEFAULT 1,
  `createdOn` TIMESTAMP  NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`resourceActionId`)
);
   
-- -------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffRoles such as program manager, director, sys admin etc
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffRoles` (
  `cciStaffRoleId` INT(11) NOT NULL AUTO_INCREMENT,
  `cciStaffRoleName` VARCHAR(50) NOT NULL,
  `hierarchy` INT(11) NULL,
  `createdOn` TIMESTAMP  NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`cciStaffRoleId`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffUsers
-- ---------------------------------------------------------------------------------------------------- 
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffUsers` (
  `cciStaffUserId` INT(11) NOT NULL,
  `supervisorId` INT(11) NULL,
  `cciAdminGuid` VARCHAR(64) UNIQUE NOT NULL,
  `firstName` VARCHAR(30) NOT NULL,
  `lastName` VARCHAR(30) NOT NULL,
  `genderId` INT(3) NULL,
  `primaryPhone` VARCHAR(15) NULL,
  `emergencyPhone` VARCHAR(15) NULL,
  `email` VARCHAR(50) NOT NULL,
  `homeAddressLineOne` VARCHAR(100) NULL,
  `homeAddressLineTwo` VARCHAR(100) NULL,
  `city` VARCHAR(50) NULL,
  `usStatesId` INT(11) NULL,
  `zip` VARCHAR(50) NULL,
  `countryId` INT(11) NULL,
  `photo` VARCHAR(300) NULL,
  `sevisId` VARCHAR(20) NULL,
  `createdOn` TIMESTAMP  NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`cciStaffUserId`),
  CONSTRAINT `FK_CCIStaffUsers_LookupUSStates`
    FOREIGN KEY (`usStatesId`)
    REFERENCES `cci_gh_go`.`LookupUSStates` (`usStatesId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsers_LookupCountries`
    FOREIGN KEY (`countryId`)
    REFERENCES `cci_gh_go`.`LookupCountries` (`countryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsers_LookupGender`
    FOREIGN KEY (`genderId`)
    REFERENCES `cci_gh_go`.`LookupGender` (`genderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsers_GoIdSequence`
    FOREIGN KEY (`cciStaffUserId`)
    REFERENCES `cci_gh_go`.`GoIdSequence` (`goId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffUsersCCIStaffRoles
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffUsersCCIStaffRoles` (  
  `cciStaffUserId` INT(11) NOT NULL,
  `cciStaffRoleId` INT(11) NOT NULL,
  `createdOn` TIMESTAMP  NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`cciStaffUserId`, `cciStaffRoleId`),
  CONSTRAINT `FK_CCIStaffUsersCCIStaffRoles_CCIStaffRoles`
    FOREIGN KEY (`cciStaffRoleId`)
    REFERENCES `cci_gh_go`.`CCIStaffRoles` (`cciStaffRoleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsersCCIStaffRoles_CCIStaffUsers`
    FOREIGN KEY (`cciStaffUserId`)
    REFERENCES `cci_gh_go`.`CCIStaffUsers` (`cciStaffUserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- --------------------------------------------------------------------------------------------------
-- Creating Table CCIStaffUserProgram
-- --------------------------------------------------------------------------------------------------
CREATE TABLE `cci_gh_go`.`CCIStaffUserProgram`( 
  `cciStaffUserId` INT(11) NOT NULL,
  `departmentProgramId` INT(11) NOT NULL,  
  `createdOn` TIMESTAMP  NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NOT NULL,
    PRIMARY KEY (`cciStaffUserId`, `departmentProgramId`), 
    CONSTRAINT `FK_CCIStaffUserProgram_CciStaffUser` 
    FOREIGN KEY (`cciStaffUserId`) 
    REFERENCES `cci_gh_go`.`CCIStaffUsers`(`cciStaffUserId`), 
    CONSTRAINT `FK_CCIStaffUserProgram_DepartmentPrograms` 
    FOREIGN KEY (`departmentProgramId`) 
    REFERENCES `cci_gh_go`.`DepartmentPrograms`(`departmentProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);  

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.DepartmentResourceGroups such as seasons, SEVIS, accounting etc
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`DepartmentResourceGroups` (
  `departmentResourceGroupId` INT(11) NOT NULL AUTO_INCREMENT,
  `departmentId` INT(11) NOT NULL,
  `resourceGroupName` VARCHAR(50) NOT NULL,
  `createdOn` TIMESTAMP  NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`departmentResourceGroupId`),
  CONSTRAINT `FK_DepartmentResourceGroups_LookupDepartments` 
   FOREIGN KEY (`departmentId`)
   REFERENCES `cci_gh_go`.`LookupDepartments`(`departmentId`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION
);

-- --------------------------------------------------------------------------------------------------
-- Table cci_gh_go.ResourcePermissions
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`ResourcePermissions` (
  `resourcePermissionId` INT(11) NOT NULL AUTO_INCREMENT,
  `departmentResourceGroupId` INT(11) NOT NULL,
  `resourceActionID` INT(11) NOT NULL,
  `resourceName` VARCHAR(50) NULL,
  `resourceDescription` VARCHAR(200) NULL,
  `createdOn` TIMESTAMP  NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`resourcePermissionId`),
  CONSTRAINT `FK_ResourcePermissions_DepartmentResourceGroups`
    FOREIGN KEY (`departmentResourceGroupId`)
    REFERENCES `cci_gh_go`.`DepartmentResourceGroups` (`departmentResourceGroupId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ResourcePermissions_ResourceActions`
    FOREIGN KEY (`resourceActionId`)
    REFERENCES `cci_gh_go`.`ResourceActions` (`resourceActionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
    
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffUsersResourcePermissions
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffUsersResourcePermissions` (  
  `cciStaffUserId` INT(11) NOT NULL,
  `departmentResourceGroupId` INT(11) NOT NULL,
  `resourcePermissionId` INT(11) NOT NULL,  
  `resourceActionId` INT(11),
  `createdOn` TIMESTAMP  NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`cciStaffUserId`, `resourcePermissionId`),
  CONSTRAINT `FK_CCIStaffUsersResourcePermissions_CCIStaffUsers`
    FOREIGN KEY (`cciStaffUserId`)
    REFERENCES `cci_gh_go`.`CCIStaffUsers` (`cciStaffUserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsersResourcePermissions_ResourcePermissions`
    FOREIGN KEY (`resourcePermissionId`)
    REFERENCES `cci_gh_go`.`ResourcePermissions` (`resourcePermissionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsersResourcePermissions_DepartmentResourceGroups`
    FOREIGN KEY (`departmentResourceGroupId`)
    REFERENCES `cci_gh_go`.`DepartmentResourceGroups` (`departmentResourceGroupId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsersResourcePermissions_ResourceActions`
    FOREIGN KEY (`resourceActionId`)
    REFERENCES `cci_gh_go`.`ResourceActions` (`resourceActionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffRolesDepartments
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffRolesDepartments` (
  `cciStaffRolesDepartmentId` INT(11) NOT NULL AUTO_INCREMENT,
  `cciStaffRoleId` INT(11) NOT NULL,
  `departmentId` INT(11) NOT NULL,
  `createdOn` TIMESTAMP  NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`cciStaffRolesDepartmentId`), 
  CONSTRAINT `FK_CCIStaffRolesDepartments_CCIStaffRoles`
    FOREIGN KEY (`cciStaffRoleId`)
    REFERENCES `cci_gh_go`.`CCIStaffRoles` (`cciStaffRoleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffRolesDepartments_LookupDepartments`
    FOREIGN KEY (`departmentId`)
    REFERENCES `cci_gh_go`.`LookupDepartments` (`departmentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
    
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffRolesDefaultResourcePermissions
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffRolesDefaultResourcePermissions` (  
  `cciStaffRolesDepartmentId` INT(11) NOT NULL,
  `departmentResourceGroupId` INT(11) NOT NULL,
  `resourcePermissionId` INT(11) NOT NULL,
  `resourceActionId` INT(11) NOT NULL,
  `createdOn` TIMESTAMP  NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`cciStaffRolesDepartmentId`, `resourcePermissionId`),
 CONSTRAINT `FK_CCIStaffRolesDefaultPermissions_CCIStaffRolesDepartments`
    FOREIGN KEY (`cciStaffRolesDepartmentId`)
    REFERENCES `cci_gh_go`.`CCIStaffRolesDepartments` (`cciStaffRolesDepartmentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffRolesDefaultPermissions_ResourcePermissions`
    FOREIGN KEY (`resourcePermissionId`)
    REFERENCES `cci_gh_go`.`ResourcePermissions` (`resourcePermissionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffRolesDefaultPermissions_DepartmentResourceGroups`
    FOREIGN KEY (`departmentResourceGroupId`)
    REFERENCES `cci_gh_go`.`DepartmentResourceGroups` (`departmentResourceGroupId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffRolesDefaultPermissions_ResourceActions`
    FOREIGN KEY (`resourceActionId`)
    REFERENCES `cci_gh_go`.`ResourceActions` (`resourceActionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ---------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffUserNotes
-- -----------------------------------------------------------------------------------------------------    
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffUserNotes`(
  `cciStaffUserNoteId` INT(11) AUTO_INCREMENT,
  `ccistaffuserID` INT(11) NOT NULL,
  `note` VARCHAR(1000) NOT NULL,
  `createdOn` TIMESTAMP  NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NOT NULL,    
 PRIMARY KEY (`cciStaffUserNoteId`),
 CONSTRAINT `FK_CCIStaffUserNotes_CCIStaffUsers`
   FOREIGN KEY (`cciStaffUserId`)
   REFERENCES `cci_gh_go`.`CCIStaffUsers` (`cciStaffUserId`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION
);

-- -----------------------------------------------------------------------------------------------------------------
-- SEASONS TABLES :Table cci_gh_go.SeasonStatus
-- -----------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonStatus` (
  `seasonStatusId` INT(3) NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(50) NOT NULL,
  `active` TINYINT(1) NOT NULL,
  PRIMARY KEY (`seasonStatusId`)
);

-- -----------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.FieldStaffAgreement
-- -----------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`FieldStaffAgreement` (
  `fieldStaffAgreementId` INT(3) NOT NULL ,
  `agreementName` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`fieldStaffAgreementId`)
);

-- -----------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.FieldStaffAgreement
-- -----------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`PaymentSchedule` (
  `paymentScheduleId` INT(3) NOT NULL ,
  `scheduleName` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`paymentScheduleId`)
);
 
-- -----------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.Season
-- --------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`Season` (
  `seasonId` INT NOT NULL AUTO_INCREMENT,
  `seasonName` VARCHAR(50) NOT NULL,
  `seasonFullName` VARCHAR(50) NOT NULL,
  `departmentId` INT(3) NOT NULL,
  `seasonStatusId` INT(3) NOT NULL,
  `clonedSeasonName` VARCHAR(50),
  `createdOn` TIMESTAMP NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NOT  NULL,
  PRIMARY KEY (`seasonId`),
  INDEX `FK_Season_SeasonStatus_idx` (`seasonStatusId` ASC),
  INDEX `FK_Season_LookupDepartments_idx` (`departmentId` ASC),
  CONSTRAINT `FK_Season_SeasonStatus`
    FOREIGN KEY (`seasonStatusId`)
    REFERENCES `cci_gh_go`.`SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Season_LookupDepartments`
    FOREIGN KEY (`departmentId`)
    REFERENCES `cci_gh_go`.`LookupDepartments` (`departmentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


-- ------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonWnTSummerDetails
-- ------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonWnTSummerDetails` (
  `seasonWnTSummerDetailsId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT(11) NOT NULL,
  `programName` VARCHAR(45),
  `startDate` DATETIME,
  `endDate` DATETIME,
  `applicationDeadlineDate` DATETIME,
  `isJobBoardOpen` TINYINT(1),
  `maxPendingJobApps` INT,
  `programStatusId` INT(3),
  `createdOn` TIMESTAMP NULL ,
  `createdBy` INT(11) ,
  `modifiedOn` TIMESTAMP  DEFAULT  NOW(),
  `modifiedBy` INT(11) ,
  PRIMARY KEY (`seasonWnTSummerDetailsId`),
  INDEX `FK_SeasonWnTSummerDetails_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonWnTSummerDetails_SeasonStatus_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_seasonWnTSummerDetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_seasonWnTSummerDetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `cci_gh_go`.`SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonWnTWinterDetails
-- -------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonWnTWinterDetails` (
  `seasonWnTWinterDetailsId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT(11) NOT NULL,
  `programName` VARCHAR(45),
  `startDate` DATETIME,
  `endDate` DATETIME,
  `applicationDeadlineDate` DATETIME,
  `isJobBoardOpen` TINYINT(1),
  `maxPendingJobApps` INT,
  `programStatusId` INT(3),
  `createdOn` TIMESTAMP  NULL,
  `createdBy` INT(11) ,
  `modifiedOn` TIMESTAMP  DEFAULT  NOW(),
  `modifiedBy` INT(11) ,
  PRIMARY KEY (`seasonWnTWinterDetailsId`),
  INDEX `FK_seasonWnTWinterDetails_Season_idx` (`seasonId` ASC),
  INDEX `FK_seasonWnTWinterDetails_SeasonStatus_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_SeasonWnTWinterDetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonWnTWinterDetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `cci_gh_go`.`SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonWnTSpringDetails
-- -------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonWnTSpringDetails` (
  `seasonWnTSpringDetailsId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT(11) NOT NULL,
  `programName` VARCHAR(45),
  `startDate` DATETIME,
  `endDate` DATETIME,
  `applicationDeadlineDate` DATETIME,
  `isJobBoardOpen` TINYINT(1),
  `maxPendingJobApps` INT,
  `programStatusId` INT(3),
  `createdOn` TIMESTAMP  NULL,
  `createdBy` INT(11) ,
  `modifiedOn` TIMESTAMP  DEFAULT NOW(),
  `modifiedBy` INT(11) ,
  PRIMARY KEY (`seasonWnTSpringDetailsId`),
  INDEX `FK_SeasonWnTSpringDetails_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonWnTSpringDetails_SeasonStatus_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_seasonWnTSpringDetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_seasonWnTSpringDetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `cci_gh_go`.`SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonCAPDetails
-- -------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonCAPDetails` (
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
  `createdOn` TIMESTAMP   NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonCAPDetailsId`),
  CONSTRAINT `FK_SeasonCAPDetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonCAPdetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `cci_gh_go`.`SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonWPAllocation
-- -------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonWPAllocation` (
  `seasonWPAllocationId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT(11) NOT NULL,
  `departmentProgramOptionId` INT(3),
  `maxPax` INT DEFAULT 0,
  `createdOn` TIMESTAMP   NULL ,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT  NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonWPAllocationId`),
  INDEX `FK_SeasonWPAloocation_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonWPAllocation_DepartmentProgramOptions_idx` (`departmentProgramOptionId` ASC),
  CONSTRAINT `FK_SeasonWPAllocation_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonWPAllocation_DepartmentProgramOptions`
    FOREIGN KEY (`departmentProgramOptionId`)
    REFERENCES `cci_gh_go`.`DepartmentProgramOptions` (`departmentProgramOptionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
 
-- ----------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonWPConfiguration
-- -----------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonWPConfiguration` (
  `seasonWPConfigurationId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `seasonStartDate` DATETIME NOT NULL,
  `seasonEndDate` DATETIME NOT NULL,
  `createdOn` TIMESTAMP   NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT  NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonWPConfigurationId`),
  INDEX `FK_SeasonWPConfiguration_Season_idx` (`seasonId` ASC),
  CONSTRAINT `FK_SeasonWPConfiguration_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
 
-- ------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonHSPConfiguration
-- ----------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonHSPConfiguration` (
  `seasonHSPConfigurationId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `seasonStartDate` DATETIME NOT NULL,
  `seasonEndDate` DATETIME NOT NULL,
  `createdOn` TIMESTAMP   NULL ,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT  NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonHSPConfigurationId`),
  INDEX `FK_SeasonHSPConfiguration_Season_idx` (`seasonId` ASC),
  CONSTRAINT `FK_SeasonHSPConfiguration_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
 
-- -------------------------------------------------------------------------------------------------------------------------- 
-- Table cci_gh_go.SeasonJ1Details
-- -------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonJ1Details` (
  `seasonJ1DetailsId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT(11) NOT NULL,
  `programName` VARCHAR(45),
  `programStatusId` INT(3),
  `secondSemStartDate` DATETIME,
  `secondSemEndDate` DATETIME,
  `secondSemAppDeadlineDate` DATETIME,
  `secondSemEarliestBirthDate` DATETIME,
  `secondSemLatestBirthDate` DATETIME,
  `showSecondSemToNewHF` TINYINT(1),
  `activeFullYearJanProgram` TINYINT(1),
  `janFullYearStartDate` DATETIME,
  `janFullYearAppDeadlineDate` DATETIME,
  `janFullYearEndDate` DATETIME,
  `showJanFullYearToNewHF` TINYINT(1),
  `firstSemStartDate` DATETIME,
  `firstSemEndDate` DATETIME,
  `firstSemAppDeadlineDate` DATETIME,
  `firstSemEarliestBirthDate` DATETIME,
  `firstSemLatestBirthDate` DATETIME,
  `showFirstSemToNewHF` TINYINT(1),
  `augFullYearStartDate` DATETIME,
  `augFullYearEndDate` DATETIME,
  `augFullYearAppDeadlineDate` DATETIME,
  `showAugFullYearToNewHF` TINYINT(1),
  `showSeasonToCurrentHF` TINYINT(1),
  `fieldStaffHoldLength` INT(3),
  `hoursBeforeHoldExpirationWarning` INT(3),
  `lcPaymentScheduleId` INT,
  `fsAgreementId` INT,
  `hfReferences` INT(3),
  `hfInquiryDate` DATE,
  `showWelcomeFamily` TINYINT(1),
  `showGuaranteed` TINYINT(1),
  `showUnguaranteed` TINYINT(1),
  `showSpecialRequestStudent` TINYINT(1),
  `createdOn` TIMESTAMP  NULL ,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT  NOW(),
  `modifiedBy` INT(11) NOT NULL,
   PRIMARY KEY (`seasonJ1DetailsId`),
   INDEX `FK_J1HSPSeason_Season_idx` (`seasonId` ASC),
   INDEX `FK_SeasonJ1Details_Status_idx` (`programStatusId` ASC),
   INDEX `FK_seasonJ1Details_FieldStaffAgreement_idx` (`fsAgreementId` ASC),
   INDEX `FK_seasonJ1Details_PaymentSchedule_idx` (`lcPaymentScheduleId` ASC),
   CONSTRAINT `FK_SeasonJ1Details_Season`
   FOREIGN KEY (`seasonId`)
   REFERENCES `cci_gh_go`.`Season` (`seasonId`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION,
   CONSTRAINT `FK_SeasonJ1Details_SeasonStatus`
   FOREIGN KEY (`programStatusId`)
   REFERENCES `cci_gh_go`.`SeasonStatus` (`seasonStatusId`)
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
 CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonF1Details` (
  `seasonF1DetailsId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT(11) NOT NULL,
  `programName`VARCHAR(45),
  `programStatusId` INT(3),
  `secondSemStartDate` DATETIME,
  `secondSemEndDate` DATETIME,
  `secondSemAppDeadlineDate` DATETIME,
  `secondSemEarliestBirthDate` DATETIME,
  `secondSemLatestBirthDate` DATETIME,
  `showSecSemToNewHF` TINYINT(1),
  `activeFullYearJanProgram` TINYINT(1),
  `janFullYearStartDate` DATETIME,
  `janFullYearAppDeadlineDate` DATETIME,
  `janFullYearEndDate` DATETIME,
  `showJanFullYearToNewHF` TINYINT(1),
  `firstSemStartDate` DATETIME,
  `firstSemEndDate` DATETIME,
  `firstSemAppDeadlineDate` DATETIME,
  `firstSemEarliestBirthDate` DATETIME,
  `firstSemLatestBirthDate` DATETIME,
  `showFirstSemToNewHF` TINYINT(1),
  `augFullYearStartDate` DATETIME,
  `augFullYearEndDate` DATETIME,
  `augFullYearAppDeadlineDate` DATETIME,
  `showAugFullYearToNewHF` TINYINT(1),
  `showSeasonToCurrentHF` TINYINT(1),
  `lcPaymentScheduleId` INT(11),
  `fsAgreementId` INT(11),
  `hfReferences` INT(3),
  `hfInquiryDate` DATE,
  `showWelcomeFamily` TINYINT(1),
  `allowFieldStaffToStartRenewalProcess` TINYINT(1),
  `showSpecialRequestStudent` TINYINT(1),
  `greenHeartMargin` INT(11),
  `createdOn` TIMESTAMP  NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT  NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonF1DetailsId`),
  INDEX `FK_J1HSPSeason_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonF1Details_status_idx` (`programStatusId` ASC),
  INDEX `FK_seasonF1Details_FieldStaffAgreement_idx` (`fsAgreementId` ASC),
  INDEX `FK_seasonF1Details_PaymentSchedule_idx` (`lcPaymentScheduleId` ASC),
  CONSTRAINT `FK_SeasonF1Details_Season`
  FOREIGN KEY (`seasonId`)
  REFERENCES `cci_gh_go`.`Season` (`seasonId`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonF1Details_SeasonStatus`
  FOREIGN KEY (`programStatusId`)
  REFERENCES `cci_gh_go`.`SeasonStatus` (`seasonStatusId`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  CONSTRAINT `FK_seasonF1Details_FieldStaffAgreement`
  FOREIGN KEY (`fsAgreementId`)
  REFERENCES `cci_gh_go`.`FieldStaffAgreement` (`fieldStaffAgreementId`)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT `FK_seasonF1Details_PaymentSchedule` 
  FOREIGN KEY (`lcPaymentScheduleId`)
  REFERENCES `cci_gh_go`.`PaymentSchedule` (`paymentScheduleId`)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);

-- --------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonHSPAllocation
-- --------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonHSPAllocation` (
  `seasonHSPAllocationId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `maxGuaranteedPax` INT,
  `maxUnguaranteedPax` INT,
  `departmentProgramOptionId` INT(3) NOT NULL,
  `createdOn` TIMESTAMP  NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonHSPAllocationId`),
  INDEX `FK_SeasonHSPAllocation_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonHSPAllocation_DepartmentProgramOptions_idx` (`departmentProgramOptionId` ASC),
  CONSTRAINT `FK_SeasonHSPAllocation_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonHSPAllocation_DepartmentProgramOptions`
    FOREIGN KEY (`departmentProgramOptionId`)
    REFERENCES `cci_gh_go`.`DepartmentProgramOptions` (`departmentProgramOptionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
 
-- -----------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonGHTConfiguration
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonGHTConfiguration` (
  `seasonGHTConfigurationId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `seasonStartDate` DATETIME NOT NULL,
  `seasonEndDate` DATETIME NOT NULL,
  `createdOn` TIMESTAMP  NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT  NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonGHTConfigurationId`),
  INDEX `FK_SeasonGHTConfiguration_Season_idx` (`seasonId` ASC),
  CONSTRAINT `FK_SeasonGHTConfiguration_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonHSADetails
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonHSADetails` (
  `seasonHSADetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `programName` VARCHAR(45),
  `startDate` DATETIME,
  `endDate` DATETIME,
  `programStatusId` INT,
  `createdOn` TIMESTAMP  NULL ,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT  NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonHSADetailsId`),
  INDEX `FK_SeasonHighScoolAbroadDetails_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonHSADetails_Status_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_SeasonHSADetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonHSADetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `cci_gh_go`.`SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
); 

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonLSDetails
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonLSDetails` (
  `seasonLSDetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `programName` VARCHAR(45),  
  `startDate` DATETIME,
  `endDate` DATETIME,
  `programStatusId` INT(3),
  `createdOn` TIMESTAMP  NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT  NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonLSDetailsId`),
  INDEX `FK_seasonLSDetails_season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonLSDetails_Status_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_SeasonLSDetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonLSDetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `cci_gh_go`.`SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonTADetails
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonTADetails` (
  `seasonTADetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `programName` VARCHAR(45),
  `startDate` DATETIME,
  `endDate` DATETIME,
  `programStatusId` INT(3),
  `createdOn` TIMESTAMP  NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT  NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonTADetailsId`),
  INDEX `FK_seasonTADetails_season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonTADetails_status_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_seasonTADetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonTADetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `cci_gh_go`.`SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonWADetails
-- ----------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonWADetails` (
  `seasonWADetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `programName` VARCHAR(45) NOT NULL,
  `startDate` DATETIME,
  `endDate` DATETIME,
  `programStatusId` INT(3),
  `createdOn` TIMESTAMP  NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT  NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonWADetailsId`),
  INDEX `FK_seasonWADetails_season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonWADetails_Status_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_seasonWADetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonWADetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `cci_gh_go`.`SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonVADetails
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonVADetails` (
  `seasonVADetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `programName` VARCHAR(45),
  `startDate` DATETIME,
  `endDate` DATETIME,
  `programStatusId` INT(3),
  `createdOn` TIMESTAMP  NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT  NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonVADetailsId`),
  INDEX `FK_seasonVolunteerDetails_season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonVolunteerDetails_Status_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_SeasonVolunteerDetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonVolunteerDetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `cci_gh_go`.`SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.Region
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`Region` (
  `regionId` INT(3) NOT NULL AUTO_INCREMENT,
  `regionName` VARCHAR(50) NOT NULL,
  `active` TINYINT(1) NOT NULL,
  `createdOn` TIMESTAMP NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`regionId`)
);

-- -----------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.USSchool
-- -----------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`USSchool` (
  `usSchoolId` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`usSchoolId`)
); 

-- -----------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonUSSchoolSeasonStatus
-- -----------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`USSchoolSeason` (
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
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_USSchoolSeason_USSchool`
    FOREIGN KEY (`usSchoolId`)
    REFERENCES `cci_gh_go`.`USSchool` (`usSchoolId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ---------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonDepartmentNotes
-- ---------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonDepartmentNotes` (
`seasonDepartmentNotesId` INT NOT NULL AUTO_INCREMENT,
`seasonId` INT NOT NULL,
`departmentNote` VARCHAR(1000),
`active` TINYINT(1),
`createdOn` TIMESTAMP  NULL ,
`createdBy` INT(11) NOT NULL,
`modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
`modifiedBy` INT(11) NOT NULL,
PRIMARY KEY (`seasonDepartmentNotesId`),
CONSTRAINT `FK_SeasonDepartmentNotes_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ---------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonProgramNotes
-- ---------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonProgramNotes` (
`seasonProgramNotesId` INT NOT NULL AUTO_INCREMENT,
`seasonId` INT NOT NULL,
`departmentProgramId` INT(3),
`programNote` VARCHAR(1000),
`active` TINYINT(1),
`createdOn` TIMESTAMP  NULL,
`createdBy` INT(11) NOT NULL,
`modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
`modifiedBy` INT(11) NOT NULL,
PRIMARY KEY (`seasonProgramNotesId`),
CONSTRAINT `FK_SeasonProgramNotes_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `FK_SeasonProgramNotes_DepartmentPrograms`
    FOREIGN KEY (`departmentProgramId`)
    REFERENCES `cci_gh_go`.`DepartmentPrograms` (`departmentProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION 
);

-- -----------------------------------------------------
-- Table `cci_gh_go`.`DocumentType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`DocumentType` (
  `documentTypeId` INT(11) NOT NULL AUTO_INCREMENT,
  `documentTypeName` VARCHAR(50) NULL,
  PRIMARY KEY (`documentTypeId`)
);

-- -----------------------------------------------------
-- Table `cci_gh_go`.`DocumentCategoryProcess`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`DocumentCategoryProcess` (
  `documentCategoryProcessId` INT(11) NOT NULL AUTO_INCREMENT,
  `documentCategoryProcessName` VARCHAR(50) NULL,
  PRIMARY KEY (`documentCategoryProcessId`)
);

-- -----------------------------------------------------
-- Table `cci_gh_go`.`DocumentTypeDocumentCategoryProcess`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`DocumentTypeDocumentCategoryProcess` (
  `documentTypeDocumentCategoryProcessId` INT(11) NOT NULL AUTO_INCREMENT,
  `documentTypeId` INT(11) NULL,
  `documentCategoryProcessId` INT(11) NULL,
  `documentTypeRole` VARCHAR(50) NULL,
  PRIMARY KEY (`documentTypeDocumentCategoryProcessId`),
  INDEX `FK_DocumentTypeDocumentCategoryProcess_DocumentType_idx` (`documentTypeId` ASC),
  INDEX `FK_DocumentTypeDocumentCategoryProcess_DocumentCateogoryPro_idx` (`documentCategoryProcessId` ASC),
  CONSTRAINT `FK_DocumentTypeDocumentCategoryProcess_DocumentType`
    FOREIGN KEY (`documentTypeId`)
    REFERENCES `cci_gh_go`.`DocumentType` (`documentTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_DocumentTypeDocumentCategoryProcess_DocumentCateogoryProcess`
    FOREIGN KEY (`documentCategoryProcessId`)
    REFERENCES `cci_gh_go`.`DocumentCategoryProcess` (`documentCategoryProcessId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `cci_gh_go`.`DocumentInformation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`DocumentInformation` (
  `documentInformationId` INT(11) NOT NULL AUTO_INCREMENT,
  `documentTypeDocumentCategoryProcessId` INT(11) NULL,
  `documentName` VARCHAR(50) NULL,
  `fileName` VARCHAR(50) NULL,
  `url` VARCHAR(1000) NULL,
  `active` TINYINT(1) NULL,
  `createdOn` TIMESTAMP NULL,
  `createdBy` INT(11) NULL,
  `modifiedOn` TIMESTAMP NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NULL,
  PRIMARY KEY (`documentInformationId`),
  INDEX `FK_DocumentInformation_DocumentTypeDocumentCategoryProcess_idx` (`documentTypeDocumentCategoryProcessId` ASC),
  CONSTRAINT `FK_DocumentInformation_DocumentTypeDocumentCategoryProcess`
    FOREIGN KEY (`documentTypeDocumentCategoryProcessId`)
    REFERENCES `cci_gh_go`.`DocumentTypeDocumentCategoryProcess` (`documentTypeDocumentCategoryProcessId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `cci_gh_go`.`SeasonDepartmentDocument`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonDepartmentDocument` (
  `seasonDepartmentDocumentID` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT NULL,
  `documentInformationId` INT(11) NULL,
  `active` TINYINT(1) NULL,
  `createdOn` TIMESTAMP NULL,
  `createdBy` INT(11) NULL,
  `modifiedOn` TIMESTAMP NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NULL,
  PRIMARY KEY (`seasonDepartmentDocumentID`),
  INDEX `FK_SeasonDepartmentDocument_Season_idx` (`seasonId` ASC),  
  INDEX `FK_SeasonDepartmentDocument_DocumentInformation_idx` (`documentInformationId` ASC),
  CONSTRAINT `FK_SeasonDepartmentDocument_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,  
  CONSTRAINT `FK_SeasonDepartmentDocument_DocumentInformation`
    FOREIGN KEY (`documentInformationId`)
    REFERENCES `cci_gh_go`.`DocumentInformation` (`documentInformationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `cci_gh_go`.`SeasonProgramDocument`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonProgramDocument` (
  `seasonProgramDocumentId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT NULL,
  `departmentProgramId` INT NULL,
  `documentInformationId` INT(11) NULL,
  `active` TINYINT(1) NULL,
  `createdOn` TIMESTAMP NULL ,
  `createdBy` INT(11) NULL,
  `modifiedOn` TIMESTAMP NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NULL,
  PRIMARY KEY (`seasonProgramDocumentId`),
  INDEX `FK_SeasonProgramDocument_Season_idx` (`seasonId` ASC),  
  INDEX `FK_SeasonProgramDocument_DocumentInformation_idx` (`documentInformationId` ASC),
  INDEX `FK_SeasonProgramDocument_DepartmentPrograms_idx` (`departmentProgramId` ASC),  
  CONSTRAINT `FK_SeasonProgramDocument_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,  
  CONSTRAINT `FK_SeasonProgramDocument_DocumentInformation`
    FOREIGN KEY (`documentInformationId`)
    REFERENCES `cci_gh_go`.`DocumentInformation` (`documentInformationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonProgramDocument_DepartmentPrograms`
    FOREIGN KEY (`departmentProgramId`)
    REFERENCES `cci_gh_go`.`DepartmentPrograms` (`departmentProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `cci_gh_go`.`AddendumDocumentInformation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`AddendumDocumentInformation` (
  `addendumDocumentInformationId` INT(11) NOT NULL AUTO_INCREMENT,
  `documentInformationId` INT(11) NOT NULL,
  `documentName` VARCHAR(50) NULL,
  `fileName` VARCHAR(50) NULL,
  `url` VARCHAR(1000) NULL,
  `active` TINYINT(1) NULL,
  `updateDate` DATETIME NULL,
  `createdOn` TIMESTAMP NULL,
  `createdBy` INT(11) NULL,
  `modifiedOn` TIMESTAMP NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NULL,
  PRIMARY KEY (`addendumDocumentInformationId`),
  INDEX `FK_AddendumDocumentInformation_DocumentInformation_idx` (`documentInformationId` ASC),
  CONSTRAINT `FK_AddendumDocumentInformation_DocumentInformation`
    FOREIGN KEY (`documentInformationId`)
    REFERENCES `cci_gh_go`.`DocumentInformation` (`documentInformationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
 );
 
 -- -----------------------------------------------------
-- Table `cci_gh_go`.`SeasonDepartmentUpdateLog`
-- -----------------------------------------------------
CREATE TABLE `cci_gh_go`.`SeasonDepartmentUpdateLog` (
  `updateDepartmentLogId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT(11) DEFAULT NULL,
  `updateLogObject` LONGTEXT,
  `modifiedBy` INT(11) DEFAULT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`updateDepartmentLogId`),
  INDEX `FK_SeasonDepartmentUpdateLog_Season_idx` (`seasonId` ASC),
  CONSTRAINT `FK_SeasonDepartmentUpdateLog_Season` 
    FOREIGN KEY (`seasonId`) 
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

 -- -----------------------------------------------------
-- Table `cci_gh_go`.`SeasonProgramUpdateLog`
-- -----------------------------------------------------
CREATE TABLE `cci_gh_go`.`SeasonProgramUpdateLog` (
  `updateProgramLogId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT(11) DEFAULT NULL,
  `departmentProgramId` INT(11) DEFAULT NULL,
  `updateLogObject` LONGTEXT,
  `modifiedBy` INT(11) DEFAULT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`updateProgramLogId`),
  INDEX `FK_SeasonProgramUpdateLog_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonProgramUpdateLog_DepartmentPrograms_idx` (`departmentProgramId` ASC),
  CONSTRAINT `FK_SeasonProgramUpdateLog_Season` 
    FOREIGN KEY (`seasonId`) 
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonProgramUpdateLog_DepartmentPrograms` 
    FOREIGN KEY (`departmentProgramId`) 
    REFERENCES `cci_gh_go`.`DepartmentPrograms` (`departmentProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

 -- -----------------------------------------------------
-- Table `cci_gh_go`.`SeasonIHPDetails`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonIHPDetails` (
  `seasonIHPDetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `programName` VARCHAR(45),
  `startDate` DATETIME,
  `endDate` DATETIME,
  `programStatusId` INT,
  `maxParticipants` INT,
  `lcHoldTime` INT(3),
  `numberOfLCToRequestHold` INT(3),
  `splitPlacementPending` INT(3),
  `stopAcceptingApps` TINYINT(1),
  `stopAcceptingAppsByGender` TINYINT(1),
  `genderId` INT(3),
  `applicationDeadLineWeeks` INT(3),
  `stopAcceptingAppsStandardIHP`  TINYINT(1),
  `stopAcceptingAppsVolunteerHomestay`  TINYINT(1),
  `stopAcceptingAppsLanguageBuddy`  TINYINT(1),
  `stopAcceptingAppsHolidayHomestay`  TINYINT(1),
  `stopAcceptingAppsHighSchoolVisits`  TINYINT(1),
  `createdOn` TIMESTAMP  NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonIHPDetailsId`),
  INDEX `FK_SeasonIHPDetails_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonIHPDetails_LookupGender` (`genderId` ASC),
  INDEX `FK_SeasonIHPDetails_Status_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_SeasonIHPDetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonIHPDetails_LookupGender`
    FOREIGN KEY (`genderId`)
    REFERENCES `cci_gh_go`.`LookupGender` (`genderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonIHPDetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `cci_gh_go`.`SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
); 


 -- -----------------------------------------------------
-- Table `cci_gh_go`.`RegionIHP`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`RegionIHP` (
  `regionIHPId` INT NOT NULL AUTO_INCREMENT,
  `regionName` VARCHAR(45),
  `active` TINYINT(1),
  `createdOn` TIMESTAMP NULL ,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`regionIHPId`)
);
 -- -----------------------------------------------------
-- Table `cci_gh_go`.`SeasonIHPDetailsRegionApplications`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonIHPDetailsRegionApplications` (
  `seasonIHPDetailsRegionApplicationId` INT NOT NULL AUTO_INCREMENT,  
  `seasonIHPDetailsId` INT,
  `regionIHPId` INT,
  `stopAcceptingApps`  TINYINT(1),
  `createdOn` TIMESTAMP  NULL ,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonIHPDetailsRegionApplicationId`),
  INDEX `FK_SeasonIHPDetailsRegionApplications_SeasonIHPDetails_idx` (`seasonIHPDetailsId` ASC),
  INDEX `FK_SeasonIHPDetailsRegionApplications_RegionIHP_idx` (`regionIHPId` ASC),
  CONSTRAINT `FK_SeasonIHPDetailsRegionApplications_SeasonIHPDetails`
    FOREIGN KEY (`seasonIHPDetailsId`)
    REFERENCES `cci_gh_go`.`SeasonIHPDetails` (`seasonIHPDetailsId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonIHPDetailsRegionApplications_RegionIHP`
    FOREIGN KEY (`regionIHPId`)
    REFERENCES `cci_gh_go`.`RegionIHP` (`regionIHPId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION 
);
 -- -----------------------------------------------------
-- Table `cci_gh_go`.`SeasonIHPGeographyConfiguration`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonIHPGeographyConfiguration` (
  `seasonIHPGeographyConfigurationId` INT NOT NULL AUTO_INCREMENT,
  `regionIHPId` INT,
  `usStatesId` INT,
  `seasonId` INT,
  `createdOn` TIMESTAMP  NULL ,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonIHPGeographyConfigurationId`),
  INDEX `FK_SeasonIHPGeographyConfiguration_Season_idx` (`seasonId` ASC), 
  INDEX `FK_SeasonIHPGeographyConfiguration_RegionIHP_idx` (`regionIHPId` ASC),
  INDEX `FK_SeasonIHPGeographyConfiguration_LookupUSStates_idx` (`usStatesId` ASC),
  CONSTRAINT `FK_SeasonIHPGeographyConfiguration_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonIHPGeographyConfiguration_RegionIHP`
    FOREIGN KEY (`regionIHPId`)
    REFERENCES `cci_gh_go`.`RegionIHP` (`regionIHPId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonIHPGeographyConfiguration_LookupUSStates`
    FOREIGN KEY (`usStatesId`)
    REFERENCES `cci_gh_go`.`LookupUSStates` (`usStatesId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION 
);


 -- -----------------------------------------------------
-- Table `cci_gh_go`.`SuperRegion`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SuperRegion` (
  `superRegionId` INT(3) NOT NULL AUTO_INCREMENT,
  `superRegionName` VARCHAR(45),
  `active` TINYINT(1),
  `createdOn` TIMESTAMP  NULL ,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`superRegionID`)
);

 -- -----------------------------------------------------
-- Table `cci_gh_go`.`SeasonGeographyConfiguration`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonGeographyConfiguration` (
  `seasonGeographyConfigurationId` INT NOT NULL AUTO_INCREMENT,
  `superRegionId` INT(3),
  `regionId` INT(3) DEFAULT NULL,
  `usStatesId` INT(3) DEFAULT NULL,
  `seasonId` INT,
  `createdOn` TIMESTAMP NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonGeographyConfigurationId`),
  INDEX `FK_SeasonGeographyConfiguration_SuperRegion_idx` (`superRegionId` ASC),
  INDEX `FK_SeasonGeographyConfiguration_Region_idx` (`regionId` ASC),
  INDEX `FK_SeasonGeographyConfiguration_LookupUSStates_idx` (`usStatesId` ASC),
  INDEX `FK_SeasonGeographyConfiguration_Season_idx` (`seasonId` ASC),
  CONSTRAINT `FK_SeasonGeographyConfiguration_SuperRegion`
    FOREIGN KEY (`superRegionId`)
    REFERENCES `cci_gh_go`.`SuperRegion` (`superRegionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,  
  CONSTRAINT `FK_SeasonGeographyConfiguration_Region`
    FOREIGN KEY (`regionId`)
    REFERENCES `cci_gh_go`.`Region` (`regionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION, 
  CONSTRAINT `FK_SeasonGeographyConfiguration_LookupUSStates`
    FOREIGN KEY (`usStatesId`)
    REFERENCES `cci_gh_go`.`LookupUSStates` (`usStatesId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonGeographyConfiguration_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


 -- -----------------------------------------------------
-- Table `cci_gh_go`.`FieldStaffType`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `cci_gh_go`.`FieldStaffType` (
  `fieldStaffTypeId` INT NOT NULL AUTO_INCREMENT,
  `fieldStaffTypeCode` VARCHAR(10),
  `fieldStaffType` VARCHAR(50),
  PRIMARY KEY (`fieldStaffTypeId`)
 );
 
  -- -----------------------------------------------------
-- Table `cci_gh_go`.`FieldStaff`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `cci_gh_go`.`FieldStaff` (
  `fieldStaffId` INT NOT NULL AUTO_INCREMENT,
  `fieldStaffTypeId` INT,
  `firstName` VARCHAR(45),
  `lastName` VARCHAR(45),
  `photo` VARCHAR(100),
   PRIMARY KEY (`fieldStaffId`),
   CONSTRAINT `FK_FieldStaff_FieldStaffType`
    FOREIGN KEY (`fieldStaffTypeId`)
    REFERENCES `cci_gh_go`.`FieldStaffType` (`fieldStaffTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION   
);

 -- -----------------------------------------------------
-- Table `cci_gh_go`.`FieldStaffLeadershipSeason`
-- ----------------------------------------------------- 
 CREATE TABLE IF NOT EXISTS `cci_gh_go`.`FieldStaffLeadershipSeason` (
  `fieldStaffLeadershipSeasonId` INT NOT NULL AUTO_INCREMENT,
  `fieldStaffId` INT,
  `seasonId` INT,
  `seasonGeographyConfigurationId` INT,
  `createdOn` TIMESTAMP  NULL ,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`fieldStaffLeadershipSeasonId`),
  INDEX `FK_FieldStaffLeadershipSeason_Season_idx` (`seasonId` ASC),
  INDEX `FK_FieldStaffLeadershipSeason_FieldStaff_idx` (`fieldStaffId` ASC),
  INDEX `FK_FieldStaffLeadershipSeason_SeasonGeographyConfiguration_idx` (`seasonGeographyConfigurationId` ASC),
  CONSTRAINT `FK_FieldStaffLeadershipSeason_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_FieldStaffLeadershipSeason_FieldStaff`
    FOREIGN KEY (`fieldStaffId`)
    REFERENCES `cci_gh_go`.`FieldStaff`(`fieldStaffId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,    
  CONSTRAINT `FK_FieldStaffLeadershipSeason_SeasonGeographyConfiguration`
    FOREIGN KEY (`seasonGeographyConfigurationId`)
    REFERENCES `cci_gh_go`.`SeasonGeographyConfiguration` (`seasonGeographyConfigurationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


 -- -----------------------------------------------------
-- Table `cci_gh_go`.`FieldStaffLCSeason`
-- ----------------------------------------------------- 

CREATE TABLE IF NOT EXISTS `cci_gh_go`.`FieldStaffLCSeason` (
  `fieldStaffLCSeasonId` INT NOT NULL AUTO_INCREMENT,
  `fieldStaffId` INT,
  `departmentProgramId` INT(3),
  `seasonId` INT,
  `seasonGeographyConfigurationId` INT,
  `createdOn` TIMESTAMP  NULL,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`fieldStaffLCSeasonId`),
  INDEX `FK_FieldStaffLCSeason_Season_idx` (`seasonId` ASC),
  INDEX `FK_FieldStaffLCSeason_FieldStaff_idx` (`fieldStaffId` ASC),
  INDEX `FK_FieldStaffLCSeason_DepartmentPrograms_idx` (`departmentProgramId` ASC),
  INDEX `FK_FieldStaffLCSeason_SeasonGeographyConfiguration_idx` (`seasonGeographyConfigurationId` ASC),
  CONSTRAINT `FK_FieldStaffLCSeason_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_FieldStaffLCSeason_FieldStaff`
    FOREIGN KEY (`fieldStaffId`)
    REFERENCES `cci_gh_go`.`FieldStaff`(`fieldStaffId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_FieldStaffLCSeason_DepartmentPrograms`
    FOREIGN KEY (`departmentProgramId`)
    REFERENCES `cci_gh_go`.`DepartmentPrograms` (`departmentProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_FieldStaffLCSeason_SeasonGeographyConfiguration`
    FOREIGN KEY (`seasonGeographyConfigurationId`)
    REFERENCES `cci_gh_go`.`SeasonGeographyConfiguration` (`seasonGeographyConfigurationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE GoIdSequence;
TRUNCATE TABLE UserType;
TRUNCATE TABLE Login;
TRUNCATE TABLE LoginUserType;
TRUNCATE TABLE CCIStaffUsers;
TRUNCATE TABLE CCIStaffUserNotes;
TRUNCATE TABLE CCIStaffUsersCCIStaffRoles;
TRUNCATE TABLE CCIStaffUserProgram;
TRUNCATE TABLE LoginHistory;
/* ---------------------------------------------------------------------------------------------------------------------------------------------------------
---------------------  Created a mapping table called gomapping with GoID, loginId,CCIStaffUserId, LoginTypeId to insert data into CCIStaffUser table-------
------------------------------------------------------------------------------------------------------------------------------------------------------------*/ 

 /* --------------------------------------------------------------------------------------------------------------------------------------------------------
---------------------  Inserting data into GoIdSequence table using gomapping table ------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------*/ 
INSERT INTO `GoIdSequence` (`goId`) SELECT `goId` FROM `cci_go`.`GoMapping` where loginTypeId =1;

/* ---------------------------------------------------------------------------------------------------------------------------------------------------------
---------------------  Inserting data into UserType table --------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------*/
INSERT INTO `UserType` (`userTypeId`,`userTypeName`)
SELECT  LoginTypeID,LoginTypeName
FROM    `cci_go`.`UserLoginType` WHERE `LoginTypeID` <> 0 ;
									  
UPDATE `UserType`
SET    `userTypeCode` = 'ADMIN'
WHERE  `userTypeId` =1;
UPDATE `UserType`
SET    `userTypeCode` = 'EMP'
WHERE  `userTypeId` =2;
UPDATE `UserType`
SET    `userTypeCode` = 'PPT'
WHERE  `userTypeId` =3;
UPDATE `UserType`
SET    `userTypeCode` = 'PTR'
WHERE  `userTypeId` =4;
UPDATE `UserType`
SET    `userTypeCode` = 'SP'
WHERE  `userTypeId` =6;
UPDATE `UserType`
SET    `userTypeCode` = 'FS'
WHERE  `userTypeId` =7;
UPDATE `UserType`
SET    `userTypeCode` = 'HF'
WHERE  `userTypeId` =8;
UPDATE  `UserType`
SET `createdOn` = CURRENT_TIMESTAMP,
	`createdBy` = 1,
    `modifiedBy`= 1; 
/* ---------------------------------------------------------------------------------------------------------------------------------------------------------
---------------------  Inserting data into Login table -----------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------*/
INSERT INTO `Login` (`loginId`,`loginName`,`password`,`keyValue`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `LoginID`,`LoginName`,`Password`,`PasswordSalt`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy`
FROM `cci_go`.`UserLogin` WHERE `LoginID` <> 0 AND `LoginTypeID` = 1;

UPDATE `Login` l,`cci_go`.`CCIAdmin` ca
SET l.`email` = ca.`Email`
WHERE l.`loginId` = ca.`LoginID`;

UPDATE `Login` l,`cci_go`.`GoMapping` gm
SET l.`goId` = gm.`goId`
WHERE l.loginId = gm.`loginId`;

/* ---------------------------------------------------------------------------------------------------------------------------------------------------------
---------------------  Inserting data into LoginUserType table  --------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------*/
ALTER  TABLE `LoginUserType` AUTO_INCREMENT = 1;

INSERT INTO `LoginUserType` (`loginId`,`userTypeId`) 
SELECT LoginID,LoginTypeId FROM cci_go.UserLogin WHERE LoginID <> 0 AND `LoginTypeID` = 1;

UPDATE `LoginUserType`
SET `defaultUserType` = 1,
    `active` = 1,
    `createdOn` = CURRENT_TIMESTAMP,
    `createdBy` = 1,
    `modifiedOn` = CURRENT_TIMESTAMP,
    `modifiedBy` = 1;

	
/* ---------------------------------------------------------------------------------------------------------------------------------------------------------
---------------------  Inserting data into CCIStaffUsers table  --------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------*/

 ALTER TABLE CCIStaffUsers DROP INDEX cciAdminGuid;
	
INSERT INTO `CCIStaffUsers` (`cciStaffUserId`) 
SELECT `goId` FROM `cci_go`.`GoMapping` WHERE `LoginTypeId` = 1;


UPDATE `CCIStaffUsers` csu,`cci_go`.`GoMapping` gm,`cci_go`.`CCIAdmin` ca
SET csu.`cciAdminGuid` = ca.CCIAdminGuid,
    csu.`firstName` = ca.FirstName,
	csu.`lastName` = ca.LastName,
	csu.`primaryPhone` = ca.Phone,
	csu.`emergencyPhone` = ca.EmergencyPhone,
	csu.`homeAddressLineOne` = ca.HomeAddressLineOne,
	csu.`homeAddressLineTwo` = ca.HomeAddressLineTwo,
	csu.`city` = ca.City,
	csu.`usStatesId` = ca.StateID,
	csu.`zip` = ca.Zip,
	csu.`countryId` = ca.CountryID,
	csu.`sevisId` = ca.SevisID,
	csu.`createdOn` = ca.CreatedOn,
	csu.`createdBy` = ca.CreatedBy,
	csu.`modifiedOn` = ca.ModifiedOn,
	csu.`modifiedBy` = ca.ModifiedBy,
	csu.`active` = ca.Active
WHERE csu.`cciStaffUserId` = gm.goId
AND   gm.loginId = ca.`LoginID`
AND   gm.cciId = ca.`CCIAdminID`;
									  

UPDATE `CCIStaffUsers` 
SET `genderId` = NULL
WHERE `genderId` = 0;

UPDATE `CCIStaffUsers`
SET `usStatesId` = NULL
WHERE `usStatesId` = 0;

UPDATE `CCIStaffUsers`
SET `countryId` = NULL
WHERE `countryId` = 0;

UPDATE `CCIStaffUsers` csu, `cci_go`.`UserRolesByCCI` urc, `cci_go`.`UserRolesByCCI` urc1
SET csu.`supervisorId` = urc1.`cciStaffUserId`
WHERE csu.cciStaffUserId = urc.cciStaffUserId
AND urc.supervisor = urc1.userName;

UPDATE `CCIStaffUsers` csu,`LookupGender` lg,`cci_go`.`UserRolesByCCI` urc
SET csu.`genderId` = lg.`genderId`
WHERE csu.`cciStaffUserId` = urc.`cciStaffUserId` 
AND SUBSTR(urc.`Gender`,1,1)=lg.`genderName`;

ALTER TABLE CCIStaffUsers ADD UNIQUE INDEX cciAdminGuid (cciAdminGuid) ;

/* ---------------------------------------------------------------------------------------------------------------------------------------------------------
---------------------  Inserting data into CCIStaffUserProgram table -----------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------- */
INSERT INTO `CCIStaffUserProgram` (cciStaffUserId)
SELECT cciStaffUserId FROM CCIStaffUsers;

UPDATE CCIStaffUserProgram csp, cci_go.UserRolesByCCI urc
SET lookupDepartmentProgramId = 6 
WHERE urc.program = 'W&T' 
AND csp.cciStaffUserId = urc.cciStaffUserId;

UPDATE CCIStaffUserProgram csp, cci_go.UserRolesByCCI urc
SET lookupDepartmentProgramId = 7 
WHERE urc.program = 'CAP' 
AND csp.cciStaffUserId = urc.cciStaffUserId;


/* ---------------------------------------------------------------------------------------------------------------------------------------------------------
---------------------  Inserting data into CCIStaffUserNotes table -----------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------- */

INSERT INTO `CCIStaffUserNotes` (`cciStaffUserId`)
SELECT `goId` FROM `cci_go`.`GoMapping` gm,`cci_go`.`CCIAdminNote` cn WHERE gm.cciId = cn.cciAdminID;

UPDATE CCIStaffUserNotes csn,cci_go.GoMapping gm,cci_go.CCIAdminNote cn 
SET csn.note = cn.note,
csn.createdOn = cn.CreatedOn,
csn.createdBy = cn.CreatedBy,
csn.modifiedOn = cn.modifiedOn,
csn.modifiedBy = cn.modifiedBy
WHERE csn.`cciStaffUserId` = gm.goId
AND   gm.cciId = cn.`CCIAdminID`;

/* ---------------------------------------------------------------------------------------------------------------------------------------------------------
---------------------  Inserting data into CCIStaffUsersCCIStaffRoles table ----------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------*/

INSERT INTO `CCIStaffUsersCCIStaffRoles` (`cciStaffUserId`) 
SELECT `cciStaffUserId` FROM `CCIStaffUsers`;

UPDATE `CCIStaffUsersCCIStaffRoles` csu,`CCIStaffRoles` csr,`CCIStaffUsers` cu,`cci_go`.`UserRolesByCCI` urc
SET csu.`cciStaffRoleId` = csr.`cciStaffRoleId`
WHERE csr.`cciStaffRoleName` = urc.`Role`
AND csu.`cciStaffUserId` = urc.`cciStaffUserId`;

UPDATE CCIStaffUsersCCIStaffRoles csu
SET csu.createdOn = CURRENT_TIMESTAMP,
csu.createdBy = 1000,
csu.modifiedBy = 1000;

/*UPDATE CCIStaffUsersCCIStaffRoles csu
SET csu.cciStaffRoleId = NULL
WHERE csu.cciStaffRoleId = 0; */


/* ---------------------------------------------------------------------------------------------------------------------------------------------------------
---------------------  Inserting data into LoginHistory table ----------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------*/

INSERT INTO `LoginHistory`    (`loggedOn`,`loginId`,`ipAddress`)
SELECT     LastLoggedIn,LoginID,LastIPAddress
FROM       `cci_go`.`UserLogin`;

SET FOREIGN_KEY_CHECKS = 1;
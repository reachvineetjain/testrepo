

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE GoIdSequence;
TRUNCATE UserType;
TRUNCATE Login;
TRUNCATE LoginUserType;
TRUNCATE CCIStaffUsers;
TRUNCATE CCIStaffUserNotes;
TRUNCATE LoginHistory;
/* ------------------------------------------------------------------------------------------------------------------------------------------------------------
---------------------  Created a mapping table called gomapping with GoID, loginId,CCIStaffUserId, LoginTypeId to insert data into CCIStaffUser table-------
------------------------------------------------------------------------------------------------------------------------------------------------------------*/ 

 /* --------------------------------------------------------------------------------------------------------------------------------------------------------
---------------------  Inserting data into GoIdSequence table using gomapping table ------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------*/ 
INSERT INTO `GoIdSequence` (`goId`) SELECT `goId` FROM `cci_go`.`gomapping`;

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
INSERT INTO `Login` (`loginId`,`loginName`,`password`,`passwordSalt`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `LoginID`,`LoginName`,`Password`,`PasswordSalt`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy`
FROM `cci_go`.`UserLogin` WHERE `LoginID` <> 0 AND `LoginTypeID` = 1;

UPDATE `Login` l,`cci_go`.`gomapping` gm
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
	
INSERT INTO `CCIStaffUsers` (`cciStaffUserId`) SELECT `goId` FROM `cci_go`.`gomapping`;


UPDATE `CCIStaffUsers` csu,`cci_go`.`gomapping` gm,`cci_go`.`CCIAdmin` ca
SET csu.`cciAdminGuid` = ca.CCIAdminGuid,
    csu.`firstName` = ca.FirstName,
	csu.`lastName` = ca.LastName,
	csu.`primaryPhone` = ca.Phone,
	csu.`emergencyPhone` = ca.EmergencyPhone,
	csu.`email` = ca.Email,
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
SET `genderId` = 1,
    `supervisorId` = 1;

UPDATE `CCIStaffUsers`
SET `usStatesId` = NULL
WHERE `usStatesId` = 0;

UPDATE `CCIStaffUsers`
SET `countryId` = NULL
WHERE `countryId` = 0;

ALTER TABLE CCIStaffUsers ADD UNIQUE INDEX cciAdminGuid (cciAdminGuid) ;

/* ---------------------------------------------------------------------------------------------------------------------------------------------------------
---------------------  Inserting data into CCIStaffUserNotes table -----------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------- */

INSERT INTO CCIStaffUserNotes (cciStaffUserId)
SELECT goId FROM cci_go.gomapping gm,cci_go.CCIAdminNote cn WHERE gm.cciId = cn. cciAdminID;

UPDATE CCIStaffUserNotes csn,cci_go.gomapping gm,cci_go.CCIAdminNote cn 
SET csn.note = cn.note,
csn.createdOn = cn.CreatedOn,
csn.createdBy = cn.CreatedBy,
csn.modifiedOn = cn.modifiedOn,
csn.modifiedBy = cn.modifiedBy
WHERE csn.`cciStaffUserId` = gm.goId
AND   gm.cciId = cn.`CCIAdminID`;

/* ---------------------------------------------------------------------------------------------------------------------------------------------------------
---------------------  Inserting data into LoginHistory table ----------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------*/

INSERT INTO `LoginHistory`    (`loggedOn`,`loginId`,`ipAddress`)
SELECT     LastLoggedIn,LoginID,LastIPAddress
FROM       `cci_go`.`UserLogin`;
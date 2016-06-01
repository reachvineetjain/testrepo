

SET FOREIGN_KEY_CHECKS = 0;


TRUNCATE TABLE `SeasonProgramDocument`;
TRUNCATE TABLE `SeasonDepartmentDocument`;
TRUNCATE TABLE `SeasonDepartmentNotes`;
TRUNCATE TABLE `SeasonDepartmentUpdateLog` ;
TRUNCATE TABLE `SeasonProgramNotes`;
TRUNCATE TABLE `SeasonProgramUpdateLog`;
TRUNCATE TABLE `FieldStaffType`;
TRUNCATE TABLE `FieldStaff`;
TRUNCATE TABLE `FieldStaffLeadershipSeason`;
TRUNCATE TABLE `RegionIHP`;
TRUNCATE TABLE `SuperRegion`;
TRUNCATE TABLE `Region`;




/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Inserting data in SeasonProgramDocument Table
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
INSERT INTO `SeasonProgramDocument` (documentInformationId)
SELECT documentInformationId FROM `DocumentInformation`;

UPDATE `SeasonProgramDocument` spd,`cci_go`.`SeasonDocument` sd,cci_go.`SeasonMapping` sm
SET spd.seasonId = sm.newSeasonId,
    spd.departmentProgramId=sm.departmentProgramId,
    spd.active = sd.Active,
    spd.createdOn = sd.createdOn,
    spd.createdBy = sd.createdBy,
    spd.modifiedOn = sd.modifiedOn,
    spd.modifiedBy = sd.modifiedBy
WHERE sd.SeasonDocumentID = spd.documentInformationId
AND sd.SeasonID = sm.oldSeasonId;

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Inserting data in SeasonProgramNotes Table
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
INSERT INTO `SeasonProgramNotes`(`seasonId`,`departmentProgramId`)
SELECT `newSeasonId`,`departmentProgramId` FROM `cci_go`.`SeasonMapping` sm, `cci_go`.`SeasonNote` sn
WHERE sm.oldSeasonId = sn.SeasonID;

UPDATE `SeasonProgramNotes` sp,`cci_go`.`SeasonNote` sn,`cci_go`.`SeasonMapping` sm
SET sp.`programNote` = sn.Note,
    sp.`active` = sn.`Active`,
 sp.`createdOn` = sn.`CreatedOn`,
 sp.`createdBy` = sn.`CreatedBy`,
 sp.`modifiedOn` = sn.`ModifiedOn`,
 sp.`modifiedBy` = sn.`ModifiedBy`
WHERE sn.SeasonID = sm.oldSeasonId
AND sm.newSeasonId = sp.seasonId;

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Inserting data in RegionIHP Table
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/

INSERT INTO `RegionIHP` (regionName,active,createdBy,modifiedOn,modifiedBy)
VALUES   
('Atlantic',1,1,CURRENT_TIMESTAMP,1),
('Midwest',1,1,CURRENT_TIMESTAMP,1),
('West',1,1,CURRENT_TIMESTAMP,1),
('California',1,1,CURRENT_TIMESTAMP,1),
('South',1,1,CURRENT_TIMESTAMP,1),
('Non-Contiguous',1,1,CURRENT_TIMESTAMP,1);

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Inserting data in SuperRegion Table
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/

INSERT INTO `SuperRegion` (`superRegionId`, `superRegionName`, `active`, `createdOn`, `createdBy`, `modifiedOn`, `modifiedBy`) 
VALUES
(1,'West',1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(2,'Central',1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(3,'East',1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1);

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Inserting data in Region Table
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
INSERT INTO `Region` (`regionId`, `regionName`, `active`, `createdOn`, `createdBy`, `modifiedOn`, `modifiedBy`) 
VALUES
( 1 ,'Region Leah', 1 , CURRENT_TIMESTAMP, 1 , CURRENT_TIMESTAMP , 1 ),
( 2 ,'Region Kendra', 1 , CURRENT_TIMESTAMP, 1 ,CURRENT_TIMESTAMP, 1 ),
( 3 ,'Region Lori', 1 ,CURRENT_TIMESTAMP, 1 ,CURRENT_TIMESTAMP, 1 ),
( 4 ,'Region Sally', 1 ,CURRENT_TIMESTAMP, 1 ,CURRENT_TIMESTAMP, 1 ),
( 5 ,'Region Connie', 1 ,CURRENT_TIMESTAMP, 1 ,CURRENT_TIMESTAMP, 1 ),
( 6 ,'Region Angela', 1 ,CURRENT_TIMESTAMP, 1 ,CURRENT_TIMESTAMP, 1 ),
( 7 ,'Region Emma', 1 ,CURRENT_TIMESTAMP, 1 ,CURRENT_TIMESTAMP, 1 ),
( 8 ,'Region Jennifer', 1 ,CURRENT_TIMESTAMP, 1 ,CURRENT_TIMESTAMP, 1 ),
( 9 ,'Region Rob', 1 ,CURRENT_TIMESTAMP, 1 ,CURRENT_TIMESTAMP, 1 ),
( 10 ,'Region Kathy', 1 ,CURRENT_TIMESTAMP, 1 ,CURRENT_TIMESTAMP, 1 ); 


/* ------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------- Migrate FieldStaffType table-------
---------------------------------------------------------------------------------------------------------------------------------------------------------- */
/*
INSERT INTO FieldStaffType (fieldStaffTypeId,fieldStaffType)
SELECT FieldStaffTypeID,FieldStaffTypeName FROM cci_go.FieldStaffType WHERE FieldStaffTypeID <> 0;

INSERT INTO FieldStaffType (fieldStaffTypeId,fieldStaffTypeCode,fieldStaffType)
VALUES (6,'UD','Undefined');

UPDATE FieldStaffType 
SET fieldStaffTypeCode = 'LC'
WHERE fieldStaffTypeId = 1;

UPDATE FieldStaffType 
SET fieldStaffTypeCode = 'RM'
WHERE fieldStaffTypeId = 2;

UPDATE FieldStaffType 
SET fieldStaffTypeCode = 'RD'
WHERE fieldStaffTypeId = 3;

UPDATE FieldStaffType 
SET fieldStaffTypeCode = 'AC'
WHERE fieldStaffTypeId = 4;

UPDATE FieldStaffType 
SET fieldStaffTypeCode = 'ERD'
WHERE fieldStaffTypeId = 5;
*/
/* ------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------- Migrate FieldStaff table-------
---------------------------------------------------------------------------------------------------------------------------------------------------------*/
/*
INSERT INTO FieldStaff (fieldStaffId,fieldStaffTypeId,firstName,lastName)
SELECT fs.FieldStaffId,fss.FieldStaffTypeId,fs.FirstName,fs.LastName
FROM cci_go.FieldStaffSeason  fss
INNER JOIN cci_go.FieldStaff fs ON fss.FieldStaffId= fs.FieldStaffId
AND fss.seasonId = 92;

UPDATE FieldStaff SET fieldStaffTypeId = 6 WHERE fieldStaffTypeId = 0;
*/
/* ------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------- Migrate FieldStaffLeadershipSeason table-------
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
/*
INSERT INTO `FieldStaffLeadershipSeason` (`fieldStaffLeadershipSeasonId`,`fieldStaffId`,`seasonId`,`seasonGeographyConfigurationId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)  
VALUES 
(1,2,22,1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(2,17,22,2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(3,9453,22,3,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(4,11895,22,4,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(5,9224,22,5,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(6,4195,22,6,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(7,9375,22,7,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(8,11099,22,8,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(9,7,22,9,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(10,27,22,10,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(11,59,22,11,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(12,147,22,12,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(13,2587,22,13,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(14,3,22,14,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(15,3,22,15,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(16,7,22,16,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(17,147,22,17,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(18,59,22,18,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(19,2587,22,19,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(20,3063,22,20,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(21,17,22,21,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(22,59,22,22,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(23,3063,22,23,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(24,3377,22,24,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1);*/

SET FOREIGN_KEY_CHECKS= 1;

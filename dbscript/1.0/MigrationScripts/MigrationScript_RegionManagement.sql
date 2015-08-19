

SET FOREIGN_KEY_CHECKS = 0;

/* ------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------- Migrate FieldStaffType table-------
---------------------------------------------------------------------------------------------------------------------------------------------------------- */

TRUNCATE FieldStaffType;
TRUNCATE FieldStaff;
TRUNCATE FieldStaffLeadershipSeason;

INSERT INTO FieldStaffType (fieldStaffTypeId,fieldStaffType)
SELECT FieldStaffTypeID,FieldStaffTypeName FROM cci_go.FieldStaffType WHERE FieldStaffTypeID <>0;

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


/* ------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------- Migrate FieldStaff table-------
---------------------------------------------------------------------------------------------------------------------------------------------------------*/

INSERT INTO FieldStaff (fieldStaffId,fieldStaffTypeId,firstName,lastName)
SELECT fs.FieldStaffId,fss.FieldStaffTypeId,fs.FirstName,fs.LastName
FROM cci_go.FieldStaffSeason  fss
INNER JOIN cci_go.FieldStaff fs ON fss.FieldStaffId= fs.FieldStaffId
AND fss.seasonId = 92;

UPDATE FieldStaff SET fieldStaffTypeId = 6 WHERE fieldStaffTypeId = 0;



/* ------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------- Migrate FieldStaffLeadershipSeason table-------
------------------------------------------------------------------------------------------------------------------------------------------------------------*/

INSERT INTO `FieldStaffLeadershipSeason` (`fieldStaffLeadershipSeasonId`,`fieldStaffId`,`seasonId`,`seasonGeographyConfigurationId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)  
VALUES 
(1,2,7,1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(2,17,7,2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(3,9453,7,3,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(4,11895,7,4,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(5,9224,7,5,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(6,4195,7,6,CURRENT_TIMESTAMP,1,'2015-08-17 10:07:58',1),
(7,9375,7,7,CURRENT_TIMESTAMP,1,'2015-08-17 10:07:58',1),
(8,11099,7,8,CURRENT_TIMESTAMP,1,'2015-08-17 10:07:58',1),
(9,7,7,9,CURRENT_TIMESTAMP,1,'2015-08-17 10:07:58',1),
(10,27,7,10,CURRENT_TIMESTAMP,1,'2015-08-17 10:07:58',1),
(11,59,7,11,CURRENT_TIMESTAMP,1,'2015-08-17 10:07:58',1),
(12,147,7,12,CURRENT_TIMESTAMP,1,'2015-08-17 10:07:58',1),
(13,2587,7,13,CURRENT_TIMESTAMP,1,'2015-08-17 10:07:58',1),
(14,3,7,14,CURRENT_TIMESTAMP,1,'2015-08-17 10:07:58',1),
(15,3,7,15,CURRENT_TIMESTAMP,1,'2015-08-17 10:07:58',1),
(16,7,7,16,CURRENT_TIMESTAMP,1,'2015-08-17 10:07:58',1),
(17,147,7,17,CURRENT_TIMESTAMP,1,'2015-08-17 10:07:58',1),
(18,59,7,18,CURRENT_TIMESTAMP,1,'2015-08-17 10:07:58',1),
(19,2587,7,19,CURRENT_TIMESTAMP,1,'2015-08-17 10:07:58',1),
(20,3063,7,20,CURRENT_TIMESTAMP,1,'2015-08-17 10:07:58',1),
(21,17,7,21,CURRENT_TIMESTAMP,1,'2015-08-17 10:07:58',1),
(22,59,7,22,CURRENT_TIMESTAMP,1,'2015-08-17 10:07:58',1),
(23,3063,7,23,CURRENT_TIMESTAMP,1,'2015-08-17 10:07:58',1),
(24,3377,7,24,CURRENT_TIMESTAMP,1,'2015-08-17 10:07:58',1);
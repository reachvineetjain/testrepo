-- USE `cci_gh_go_prod`;

SET FOREIGN_KEY_CHECKS= 0;

TRUNCATE TABLE `DocumentType`;
TRUNCATE TABLE `DocumentCategoryProcess`;
TRUNCATE TABLE `DocumentTypeDocumentCategoryProcess`;
TRUNCATE TABLE `DocumentInformation`;


/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Inserting data in DocumentType Table
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/

INSERT INTO `DocumentType` (`documentTypeName`)
VALUES    
('Participant Code of Conduct'),
('Employer Placement Agreement'),
('Participant Placement Agreement'),
('AYP Conditions of Participation'),
('AYP Conditions of Participation no signature');

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Inserting data in DocumentCategoryProcess Table
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/

INSERT INTO `DocumentCategoryProcess` (`documentCategoryProcessName`)
VALUES
('Placement Process'),
('Application Process');

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Inserting data in DocumentTypeDocumentCategoryProcess Table
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
									 
INSERT INTO `DocumentTypeDocumentCategoryProcess` (`documentTypeId`)
SELECT `documentTypeId` FROM `DocumentType`;

 /* UPDATE `DocumentTypeDocumentCategoryProcess` dtdcp,`cci_go`.`SeasonDocument` sd,`cci_go`.`SeasonMapping` sm
SET    dtdcp.`departmentId` = sm.`departmentId`,
       dtdcp.`departmentProgramId` = sm.`departmentProgramId`
WHERE  dtdcp.`documentTypeId` = sd.`SeasonDocumentTypeID`
AND    sd.`SeasonID` = sm.`oldSeasonId`;  
*/
UPDATE `DocumentTypeDocumentCategoryProcess`
SET `documentCategoryProcessId` = 2
WHERE `documentTypeId` IN (1,4,5);

UPDATE `DocumentTypeDocumentCategoryProcess`
SET `documentCategoryProcessId` = 1
WHERE `documentTypeId` IN (2,3);

/*UPDATE `DocumentTypeDocumentCategoryProcess`
SET `active` = 1;
*/
/*INSERT INTO `DocumentInformation` (`url`)
SELECT `DocumentURL` FROM `cci_go`.`SeasonDocument` WHERE `DocumentURL` != '';*/ 


/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Inserting data in DocumentInformation Table
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/

ALTER TABLE `DocumentInformation` AUTO_INCREMENT = 1;

INSERT INTO `DocumentInformation` (`documentInformationId`,`documentName`,`fileName`,`url`,`active`)
SELECT `SeasonDocumentID`,`DocumentName`,`FileName`,`DocumentURL`,`Active`
FROM `cci_go`.`SeasonDocument` WHERE SeasonDocumentTypeID = 2 AND FilePath = '';



/* ALTER TABLE `DocumentInformation` AUTO_INCREMENT = 1;

INSERT INTO `DocumentInformation`  (`documentInformationId`,`documentName`,`fileName`,`url`,`active`)
SELECT `SeasonDocumentID`,`DocumentName`,`FileName`,CONCAT(`FilePath`,\,`FileName`),`Active`
FROM `cci_go`.`SeasonDocument`  WHERE SeasonDocumentTypeID = 2 AND DocumentURL = '';  */

UPDATE `DocumentInformation` di,`DocumentTypeDocumentCategoryProcess` dt
SET di.`documentTypeDocumentCategoryProcessId` = dt.documentTypeDocumentCategoryProcessId,
    di.`CreatedBy` = 1,
	di.`ModifiedBy` = 1
WHERE dt.documentTypeId = 1 AND di.`documentTypeDocumentCategoryProcessId` IS NULL;

ALTER TABLE `DocumentInformation` AUTO_INCREMENT = 1;

INSERT INTO `DocumentInformation` (`documentInformationId`,`documentName`,`fileName`,`url`,`active`)
SELECT `SeasonDocumentID`,`DocumentName`,`FileName`,`DocumentURL`,`Active`
FROM `cci_go`.`SeasonDocument` WHERE SeasonDocumentTypeID = 3 AND FilePath = '';

/* INSERT INTO `DocumentInformation` (`documentInformationId`,`documentName`,`fileName`,`url`,`active`)
SELECT `SeasonDocumentID`,`DocumentName`,`FileName`,CONCAT(`FilePath`,\,`FileName`),`Active`
FROM `cci_go`.`SeasonDocument` WHERE SeasonDocumentTypeID = 3 AND DocumentURL = '';  */

UPDATE `DocumentInformation` di,`DocumentTypeDocumentCategoryProcess` dt
SET di.`documentTypeDocumentCategoryProcessId` = dt.documentTypeDocumentCategoryProcessId,
    di.`CreatedBy` = 1,
	di.`ModifiedBy` = 1
WHERE dt.documentTypeId = 2 AND di.`documentTypeDocumentCategoryProcessId` IS NULL;

ALTER TABLE `DocumentInformation` AUTO_INCREMENT = 1;

INSERT INTO `DocumentInformation` (`documentInformationId`,`documentName`,`fileName`,`url`,`active`)
SELECT `SeasonDocumentID`,`DocumentName`,`FileName`,`DocumentURL`,`Active`
FROM `cci_go`.`SeasonDocument` WHERE SeasonDocumentTypeID = 4 AND FilePath = '';

/* INSERT INTO `cci_gh_go_WIP`.`DocumentInformation` (`documentInformationId`,`documentName`,`fileName`,`url`,`active`)
SELECT `SeasonDocumentID`,`DocumentName`,`FileName`,CONCAT(`FilePath`,\,`FileName`),`Active`
FROM `cci_gh_go_WIP`.`SeasonDocument` WHERE SeasonDocumentTypeID = 4 AND DocumentURL = '';  */

UPDATE `DocumentInformation` di,`DocumentTypeDocumentCategoryProcess` dt
SET di.`documentTypeDocumentCategoryProcessId` = dt.documentTypeDocumentCategoryProcessId,
    di.`CreatedBy` = 1,
	di.`ModifiedBy` = 1
WHERE dt.documentTypeId = 3 AND di.`documentTypeDocumentCategoryProcessId` IS NULL;

ALTER TABLE `DocumentInformation` AUTO_INCREMENT = 1;

INSERT INTO `DocumentInformation` (`documentInformationId`,`documentName`,`fileName`,`url`,`active`)
SELECT `SeasonDocumentID`,`DocumentName`,`FileName`,`DocumentURL`,`Active`
FROM `cci_go`.`SeasonDocument` WHERE SeasonDocumentTypeID = 5 AND FilePath = '';

/* INSERT INTO `DocumentInformation` (`documentInformationId`,`documentName`,`fileName`,`url`,`active`)
SELECT `SeasonDocumentID`,`DocumentName`,`FileName`,CONCAT(`FilePath`,\,`FileName`),`Active`
FROM `cci_go`.`SeasonDocument` WHERE SeasonDocumentTypeID = 5 AND DocumentURL = '';  */

UPDATE `DocumentInformation` di,`DocumentTypeDocumentCategoryProcess` dt
SET di.`documentTypeDocumentCategoryProcessId` = dt.documentTypeDocumentCategoryProcessId,
    di.`CreatedBy` = 1,
	di.`ModifiedBy` = 1
WHERE dt.documentTypeId = 4 AND di.`documentTypeDocumentCategoryProcessId` IS NULL;


ALTER TABLE `DocumentInformation` AUTO_INCREMENT = 1;

INSERT INTO `DocumentInformation` (`documentInformationId`,`documentName`,`fileName`,`url`,`active`)
SELECT `SeasonDocumentID`,`DocumentName`,`FileName`,`DocumentURL`,`Active`
FROM `cci_go`.`SeasonDocument` WHERE SeasonDocumentTypeID = 6 AND FilePath = '';

/* INSERT INTO `DocumentInformation` (`documentInformationId`,`documentName`,`fileName`,`url`,`active`)
SELECT `SeasonDocumentID`,`DocumentName`,`FileName`,CONCAT(`FilePath`,\,`FileName`),`Active`
FROM `cci_go`.`SeasonDocument` WHERE SeasonDocumentTypeID = 6 AND DocumentURL = '';  */

UPDATE `DocumentInformation` di,`DocumentTypeDocumentCategoryProcess` dt
SET di.`documentTypeDocumentCategoryProcessId` = dt.documentTypeDocumentCategoryProcessId,
    di.`CreatedBy` = 1,
	di.`ModifiedBy` = 1
WHERE dt.documentTypeId = 5 AND di.`documentTypeDocumentCategoryProcessId` IS NULL;

UPDATE DocumentInformation SET createdOn = CURRENT_TIMESTAMP;

UPDATE DocumentInformation
SET fileName = SUBSTRING(url,8);

UPDATE DocumentInformation
SET documentName = SUBSTRING(url,8);
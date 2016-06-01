-- USE cci_gh_go_login;

SET FOREIGN_KEY_CHECKS= 0;


/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Truncating Lookup tables.
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
TRUNCATE TABLE `LookupCountries` ;
TRUNCATE TABLE `LookupUSStates` ;
TRUNCATE TABLE `LookupGender` ;
TRUNCATE TABLE `LookupDepartments` ;
TRUNCATE TABLE `LookupDepartmentPrograms`;



/*----------------------------------------------------------------------------------------------------------------------------------------------------
          Migration Of LookUpCountries TABLE
----------------------------------------------------------------------------------------------------------------------------------------------------*/
								
INSERT INTO `LookupCountries` (`countryId`,`countryCode`,`countryName`,`isReqFinalSOAonDS`,`active`)
                      SELECT  CountryID , SevisCode , CountryName , IsReqFinalSOAonDS , Active
                      FROM  `cci_go`.`Country` WHERE CountryID <> 0;
                      
                      
/*----------------------------------------------------------------------------------------------------------------------------------------------------
          Migration Of LookUpUSStates TABLE
----------------------------------------------------------------------------------------------------------------------------------------------------*/
								                     
INSERT INTO `LookupUSStates` (`usStatesId`,`stateName`,`stateCode`)
                      SELECT StateID , StateName , StateAbbreviation
                      FROM  `cci_go`.`USState` WHERE StateID <> 0;
					  
/*----------------------------------------------------------------------------------------------------------------------------------------------------
         Migration Of LookupGender TABLE
----------------------------------------------------------------------------------------------------------------------------------------------------*/
					  
INSERT INTO `LookupGender` (`genderId`,`genderName`)
VALUES 
(1,'M'),
(2,'F'),
(3,'U');


/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Inserting data in LookupDepartments Table
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/

INSERT INTO `LookupDepartments` (`departmentId`,`departmentName`,`acronym`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`,`active`)
VALUES 
(1,'High School Programs','HSP', CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(2,'Work Programs','WP', CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(3,'Greenheart Travel','GHT', CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(4,'Greenheart Club','GHC', CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(5,'Greenheart Transforms','GT', CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(6,'Accounting','ACC', CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(7,'System','SYS', CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1);

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Inserting data in LookupDepartmentPrograms Table
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/

INSERT INTO `LookupDepartmentPrograms`(`lookupdepartmentProgramId`,`departmentId`,`programName`,`description`,`createdBy`,`createdOn`,`modifiedBy`,`modifiedOn`) 
VALUES 
(1,1,'J-1HS','J1HS visa program',1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP),
(2,1,'F-1','F1 visa program',1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP),
(3,1,'STP-IHP','Short term program',1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP),
(4,1,'STP-GHP','Short term greenheart program',1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP),
(5,1,'STP-SSE','School to school exchange',1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP),
(6,2,'W&T','Work and travel program',1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP),
(7,2,'CAP','Career Advancement Program',1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP),
(8,3,'HS Abroad','Highschool abroad',1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP),
(9,3,'Language School','Language School',1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP),
(10,3,'Teach Abroad','Teach Abroad',1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP),
(11,3,'Volunteer Abroad','Volunteer Abroad',1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP),
(12,3,'Work Abroad','Work Abroad',1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP);

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Inserting data in LookupDepartmentPrograms Table
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
 
INSERT INTO `Salutation` (`salutationName`,`active`)
VALUES ('Mr',1),
        ('Ms',1),
		('Mrs',1),
		('Dr',1);



SET FOREIGN_KEY_CHECKS = 1;
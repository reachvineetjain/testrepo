
-- USE cci_gh_go_qa;
 
 SET FOREIGN_KEY_CHECKS= 0;

 -- ----------------------------------------------
 --  Truncating Season Module tables
 -- -----------------------------------------------
TRUNCATE TABLE `SeasonGHTConfiguration` ;
TRUNCATE TABLE `SeasonHSADetails` ;
TRUNCATE TABLE `SeasonLSDetails` ;
TRUNCATE TABLE `SeasonTADetails` ;
TRUNCATE TABLE `SeasonVADetails` ;
TRUNCATE TABLE `SeasonWADetails` ;

 -- ----------------------------------------------
 --  Season Migration
 -- -----------------------------------------------
 INSERT INTO `Season` (`seasonId`,`seasonName`,`seasonFullName`,`departmentId`,`createdBy`,`modifiedBy`)
             VALUES  (16,'GHT - 2010','GHT - 2010',3,1,1),
                     (17,'GHT - 2011','GHT - 2011',3,1,1),
                     (18,'GHT - 2012','GHT - 2012',3,1,1),
                     (19,'GHT - 2013','GHT - 2013',3,1,1),
                     (20,'GHT - 2014','GHT - 2014',3,1,1),
					 (21,'GHT - 2015','GHT - 2015',3,1,1);
                     
 UPDATE `Season` 
 SET `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`Season` WHERE `SeasonID` IN (40,41,42,43,44,47) ORDER BY `SeasonStatusID` ASC LIMIT 1)
 WHERE seasonId = 16;
 
 UPDATE `Season` 
 SET  `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`Season` WHERE `SeasonID` IN (50,52,53,54,55,56) ORDER BY `SeasonStatusID` ASC LIMIT 1)
 WHERE seasonId = 17;
 
 UPDATE `Season` 
 SET  `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`Season` WHERE `SeasonID` IN (63,64,65,70) ORDER BY `SeasonStatusID` ASC LIMIT 1)
 WHERE seasonId = 18;
 
 UPDATE `Season` 
 SET  `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`Season` WHERE `SeasonID` IN (76,78,81,82,83) ORDER BY `SeasonStatusID` ASC LIMIT 1)
 WHERE seasonId = 19;
 
 UPDATE `Season` 
 SET   `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`Season` WHERE `SeasonID` IN (84,85) ORDER BY `SeasonStatusID` ASC LIMIT 1)
 WHERE seasonId = 20;
 
 UPDATE `Season` 
 SET    `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`Season` WHERE `SeasonID` IN (99,100,105) ORDER BY `SeasonStatusID` ASC LIMIT 1)
 WHERE seasonId = 21;
 
 -- ----------------------------------------------
 --  SeasonGHTConfiguration Migration
 -- -----------------------------------------------
 
 INSERT INTO `SeasonGHTConfiguration` (`seasonId`,`createdBy`,`modifiedBy`)
                                              VALUES  (16,1,1),
                                                      (17,1,1),
                                                      (18,1,1),
                                                      (19,1,1),
                                                      (20,1,1),
													  (21,1,1);						
                                                      
 UPDATE `SeasonGHTConfiguration` 
 SET `seasonStartDate` = (SELECT MIN(`StartDate`) FROM `cci_go`.`Season` WHERE `SeasonID` IN (40,41,42,43,44,47)),
     `seasonEndDate`   = (SELECT MAX(`EndDate`) FROM `cci_go`.`Season` WHERE `SeasonID` IN (40,41,42,43,44,47))
 WHERE seasonId = 16;
 
 UPDATE `SeasonGHTConfiguration` 
 SET  `seasonStartDate` = (SELECT MIN(`StartDate`) FROM `cci_go`.`Season` WHERE `SeasonID` IN (50,52,53,54,55,56)),
      `seasonEndDate`   = (SELECT MAX(`EndDate`) FROM `cci_go`.`Season` WHERE `SeasonID` IN (50,52,53,54,55,56))
 WHERE seasonId = 17;
 
 UPDATE `SeasonGHTConfiguration` 
 SET  `seasonStartDate` = (SELECT MIN(`StartDate`) FROM `cci_go`.`Season` WHERE `SeasonID` IN (63,64,65,70)),
      `seasonEndDate`   = (SELECT MAX(`EndDate`) FROM `cci_go`.`Season` WHERE `SeasonID` IN (63,64,65,70))
 WHERE seasonId = 18;
 
 UPDATE `SeasonGHTConfiguration` 
 SET  `seasonStartDate` = (SELECT MIN(`StartDate`) FROM `cci_go`.`Season` WHERE `SeasonID` IN (76,78,81,82,83)),
      `seasonEndDate`   = (SELECT MAX(`EndDate`) FROM `cci_go`.`Season` WHERE `SeasonID` IN (76,78,81,82,83))
 WHERE seasonId = 19;
 
 UPDATE `SeasonGHTConfiguration` 
 SET  `seasonStartDate` = (SELECT MIN(`StartDate`) FROM `cci_go`.`Season` WHERE `SeasonID` IN (84,85)),
      `seasonEndDate`   = (SELECT MAX(`EndDate`) FROM `cci_go`.`Season` WHERE `SeasonID` IN (84,85))
 WHERE seasonId = 20;
 
 
 UPDATE `SeasonGHTConfiguration` 
 SET  `seasonStartDate` = (SELECT MIN(`StartDate`) FROM `cci_go`.`Season` WHERE `SeasonID` IN (99,100,105)),
      `seasonEndDate`   = (SELECT MAX(`EndDate`) FROM `cci_go`.`Season` WHERE `SeasonID` IN (99,100,105))
 WHERE seasonId = 21;
 
 -- ----------------------------------------------
 --  SeasonHSADetails Migration
 -- -----------------------------------------------
 
 INSERT INTO `SeasonHSADetails` (`seasonHSADetailsId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
 SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`SeasonStatusID`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` 
 FROM `cci_go`.`Season` WHERE `SeasonID` = 47;
                                         
 UPDATE      `SeasonHSADetails` 
 SET         `seasonId` = 16
 WHERE       `SeasonHSADetailsId` = 47;
 
 INSERT INTO `SeasonHSADetails` (`seasonHSADetailsId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
 SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`SeasonStatusID`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` 
 FROM `cci_go`.`Season` WHERE `SeasonID` = 50;
                                         
 UPDATE      `SeasonHSADetails` 
 SET         `seasonId` = 17
 WHERE       `SeasonHSADetailsId` = 50;
 
 INSERT INTO `SeasonHSADetails` (`seasonHSADetailsId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
 SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`SeasonStatusID`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` 
 FROM `cci_go`.`Season` WHERE `SeasonID` = 63;
                                         
 UPDATE      `SeasonHSADetails` 
 SET         `seasonId` = 18
 WHERE       `SeasonHSADetailsId` = 63;
 
 INSERT INTO `SeasonHSADetails` (`seasonHSADetailsId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
 SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`SeasonStatusID`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` 
 FROM `cci_go`.`Season` WHERE `SeasonID` = 76;
                                         
 UPDATE      `SeasonHSADetails` 
 SET         `seasonId` = 19
 WHERE       `SeasonHSADetailsId` = 76;
 
 INSERT INTO `SeasonHSADetails` (`seasonHSADetailsId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
 SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`SeasonStatusID`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` 
 FROM `cci_go`.`Season` WHERE `SeasonID` = 85;
                                         
 UPDATE      `SeasonHSADetails` 
 SET         `seasonId` = 20
 WHERE       `SeasonHSADetailsId` = 85;
 
 
 INSERT INTO `SeasonHSADetails` (`seasonHSADetailsId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
 SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`SeasonStatusID`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` 
 FROM `cci_go`.`Season` WHERE `SeasonID` = 105;
                                         
 UPDATE      `SeasonHSADetails` 
 SET         `seasonId` = 21
 WHERE       `SeasonHSADetailsId` = 105;
 
 -- ----------------------------------------------
 --  SeasonLSDetails Migration
 -- -----------------------------------------------
 
 INSERT INTO `SeasonLSDetails` (`seasonLSDetailsId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
 SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`SeasonStatusID`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` 
 FROM `cci_go`.`Season` WHERE `SeasonID` = 41;
                                         
 UPDATE      `SeasonLSDetails` 
 SET         `seasonId` = 16
 WHERE       `SeasonLSDetailsId` = 41;
 
 INSERT INTO `SeasonLSDetails` (`seasonLSDetailsId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
 SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`SeasonStatusID`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` 
 FROM `cci_go`.`Season` WHERE `SeasonID` = 56;
                                         
 UPDATE      `SeasonLSDetails` 
 SET         `seasonId` = 17
 WHERE       `SeasonLSDetailsId` = 56;
 
 INSERT INTO `SeasonLSDetails` (`seasonLSDetailsId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
 SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`SeasonStatusID`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` 
 FROM `cci_go`.`Season` WHERE `SeasonID` = 70;
                                         
 UPDATE      `SeasonLSDetails` 
 SET         `seasonId` = 18
 WHERE       `SeasonLSDetailsId` = 70;
                                         
 
 INSERT INTO `SeasonLSDetails` (`seasonLSDetailsId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
 SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`SeasonStatusID`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` 
 FROM `cci_go`.`Season` WHERE `SeasonID` = 82;
                                         
 UPDATE      `SeasonLSDetails` 
 SET         `seasonId` = 19
 WHERE       `SeasonLSDetailsId` = 82;
 
 INSERT INTO `SeasonLSDetails`(`seasonLSDetailsId`,`seasonId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`) VALUES (106,20,'GHT - Language School 2014','9999-09-09 00:00:00','9999-09-09 00:00:00',2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1);
 INSERT INTO `SeasonLSDetails`(`seasonLSDetailsId`,`seasonId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`) VALUES (107,21,'GHT - Language School 2015','9999-09-09 00:00:00','9999-09-09 00:00:00',2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1);
                                    
 -- ------------------------------------------------
 --  SeasonTADetails Migration
 -- -----------------------------------------------
  
 INSERT INTO `SeasonTADetails` (`seasonTADetailsId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
 SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`SeasonStatusID`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` 
 FROM `cci_go`.`Season` WHERE `SeasonID` = 43;
                                         
 UPDATE      `SeasonTADetails` 
 SET         `seasonId` = 16
 WHERE       `SeasonTADetailsId` = 43;                                  

 INSERT INTO `SeasonTADetails` (`seasonTADetailsId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
 SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`SeasonStatusID`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` 
 FROM `cci_go`.`Season` WHERE `SeasonID` = 55;
                                         
 UPDATE      `SeasonTADetails` 
 SET         `seasonId` = 17
 WHERE       `SeasonTADetailsId` = 55;
 
 INSERT INTO `SeasonTADetails` (`seasonTADetailsId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
 SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`SeasonStatusID`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` 
 FROM `cci_go`.`Season` WHERE `SeasonID` = 64;
                                         
 UPDATE      `SeasonTADetails` 
 SET         `seasonId` = 18
 WHERE       `SeasonTADetailsId` = 64;
 
 INSERT INTO `SeasonTADetails` (`seasonTADetailsId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
 SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`SeasonStatusID`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` 
 FROM `cci_go`.`Season` WHERE `SeasonID` = 83;
                                         
 UPDATE      `SeasonTADetails` 
 SET         `seasonId` = 19
 WHERE       `SeasonTADetailsId` = 83; 
 
 INSERT INTO `SeasonTADetails` (`seasonTADetailsId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
 SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`SeasonStatusID`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` 
 FROM `cci_go`.`Season` WHERE `SeasonID` = 84;
                                         
 UPDATE      `SeasonTADetails` 
 SET         `seasonId` = 20
 WHERE       `SeasonTADetailsId` = 84;  
 
 INSERT INTO `SeasonTADetails` (`seasonTADetailsId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
 SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`SeasonStatusID`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` 
 FROM `cci_go`.`Season` WHERE `SeasonID` = 100;
                                         
 UPDATE      `SeasonTADetails` 
 SET         `seasonId` = 21
 WHERE       `SeasonTADetailsId` = 100;  
 
 -- ------------------------------------------------
 --  SeasonVADetails Migration
 -- ------------------------------------------------
 
 INSERT INTO `SeasonVADetails` (`seasonVADetailsId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
 SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`SeasonStatusID`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` 
 FROM `cci_go`.`Season` WHERE `SeasonID` = 40;
                                         
 UPDATE      `SeasonVADetails` 
 SET         `seasonId` = 16
 WHERE       `SeasonVADetailsId` = 40;                                                       
 
 INSERT INTO `SeasonVADetails` (`seasonVADetailsId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
 SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`SeasonStatusID`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` 
 FROM `cci_go`.`Season` WHERE `SeasonID` = 54;
                                         
 UPDATE      `SeasonVADetails` 
 SET         `seasonId` = 17
 WHERE       `SeasonVADetailsId` = 54;                                                       
 
 INSERT INTO `SeasonVADetails` (`seasonVADetailsId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
 SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`SeasonStatusID`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` 
 FROM `cci_go`.`Season` WHERE `SeasonID` = 65;
                                         
 UPDATE      `SeasonVADetails` 
 SET         `seasonId` = 18
 WHERE       `SeasonVADetailsId` = 65;                                                       
 
 INSERT INTO `SeasonVADetails` (`seasonVADetailsId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
 SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`SeasonStatusID`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` 
 FROM `cci_go`.`Season` WHERE `SeasonID` = 81;
                                         
 UPDATE      `SeasonVADetails` 
 SET         `seasonId` = 19
 WHERE       `SeasonVADetailsId` = 81;  
 
INSERT INTO  `SeasonVADetails`(`seasonVADetailsId`,`seasonId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`) 
VALUES (108,20,'GHT - Volunteer Abroad 2014','9999-09-09 00:00:00','9999-09-09 00:00:00',2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1);

 INSERT INTO `SeasonVADetails` (`seasonVADetailsId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
 SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`SeasonStatusID`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` 
 FROM `cci_go`.`Season` WHERE `SeasonID` = 99;
                                         
 UPDATE      `SeasonVADetails` 
 SET         `seasonId` = 21
 WHERE       `SeasonVADetailsId` = 99;   
                                    
                                    
 -- ------------------------------------------------
 --  SeasonWADetails Migration
 -- -------------------------------------------------
 
 INSERT INTO `SeasonWADetails`(`seasonWADetailsId`,`seasonId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`) 
 VALUES (109,16,'GHT - Work Abroad 2010','9999-09-09 00:00:00','9999-09-09 00:00:00',2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1);
                                    
  INSERT INTO `SeasonWADetails` (`seasonWADetailsId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
  SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`SeasonStatusID`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` 
  FROM `cci_go`.`Season` WHERE `SeasonID` = 52;
                                         
  UPDATE      `SeasonWADetails` 
  SET         `seasonId` = 17
  WHERE       `SeasonWADetailsId` = 52; 
                                    
INSERT INTO `SeasonWADetails`(`seasonWADetailsId`,`seasonId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`) 
VALUES (110,18,'GHT - Work Abroad 2012','9999-09-09 00:00:00','9999-09-09 00:00:00',2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1); 
 
  INSERT INTO `SeasonWADetails` (`seasonWADetailsId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
  SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`SeasonStatusID`,`CreatedOn`,`CreatedBy`,`ModifiedOn`,`ModifiedBy` 
  FROM `cci_go`.`Season` WHERE `SeasonID` = 78;
                                         
  UPDATE      `SeasonWADetails` 
  SET         `seasonId` = 19
  WHERE       `SeasonWADetailsId` = 78;  
 
INSERT INTO `SeasonWADetails`(`seasonWADetailsId`,`seasonId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`) 
VALUES (111,20,'GHT - Work Abroad 2014','9999-09-09 00:00:00','9999-09-09 00:00:00',2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1);

INSERT INTO `SeasonWADetails`(`seasonWADetailsId`,`seasonId`,`programName`,`startDate`,`endDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`) 
VALUES (112,21,'GHT - Work Abroad 2015','9999-09-09 00:00:00','9999-09-09 00:00:00',2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1);  
  
SET FOREIGN_KEY_CHECKS= 1;
  
  
 
 
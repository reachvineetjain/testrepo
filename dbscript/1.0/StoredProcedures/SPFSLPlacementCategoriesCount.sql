DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPFSLPlacementCategoriesCount`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPFSLPlacementCategoriesCount`(IN fieldStaffId INT,IN flag TINYINT(1))
BEGIN
    
          DECLARE fsId INT;
          DECLARE sFlag TINYINT(1);
          SET @fsId = fieldStaffId;
          SET @sFlag = flag;
          
                IF @sFlag = 1 THEN -- MyPlacements                
                
                            CREATE TEMPORARY TABLE fslcount (NAME VARCHAR(25),VALUE VARCHAR(15),COUNT INT);
                
                            INSERT INTO fslcount
                                VALUES ('Unplaced - On Hold','UOH',11),
                                       ('To Review','TR',3),
                                       ('Pending','pending',11),
                                       ('Placed','placed',12);
                       
                             SELECT * FROM fslcount;
                 
                 END IF;
    
                IF @sFlag = 0 THEN -- MyTeamsPlacements                
                
                            CREATE TEMPORARY TABLE fslcount (NAME VARCHAR(25),VALUE VARCHAR(15),COUNT INT);
                
                            INSERT INTO fslcount
                                VALUES ('Unplaced - On Hold','UOH',11),
                                       ('To Review','TR',3),
                                       ('Pending','pending',11),
                                       ('Placed','placed',12);
                       
                             SELECT * FROM fslcount;
                 
                 END IF;
    DROP TABLE fslcount;
    END$$

DELIMITER ;
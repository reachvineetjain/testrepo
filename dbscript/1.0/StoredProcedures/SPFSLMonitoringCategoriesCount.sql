DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPFSLMonitoringCategoriesCount`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPFSLMonitoringCategoriesCount`(IN fieldStaffId INT,IN flag TINYINT(1))
BEGIN
    
          DECLARE fsId INT;
          DECLARE sFlag TINYINT(1);
          SET @fsId = fieldStaffId;
          SET @sFlag = flag;
          
                IF @sFlag = 1 THEN -- MyParticipants                
                
                            CREATE TEMPORARY TABLE fslcount (NAME VARCHAR(25),VALUE VARCHAR(15),COUNT INT);
                
                            INSERT INTO fslcount
                                VALUES ('Placed','placed',12),
                                       ('Move - In-Progress','MIP',11),
                                       ('Repatriated','repatriated',3);
                       
                             SELECT * FROM fslcount;
                 
                 END IF;
    
                IF @sFlag = 0 THEN -- MyTeamsParticipants                
                
                            CREATE TEMPORARY TABLE fslcount (NAME VARCHAR(25),VALUE VARCHAR(15),COUNT INT);
                
                            INSERT INTO fslcount
                                VALUES ('Placed','placed',12),
                                       ('Move - In-Progress','MIP',11),
                                       ('Repatriated','repatriated',3);
                       
                             SELECT * FROM fslcount;
                 
                 END IF;
    DROP TABLE fslcount;
    END$$

DELIMITER ;
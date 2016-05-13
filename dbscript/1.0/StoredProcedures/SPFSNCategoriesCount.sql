DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPFSNCategoriesCount`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPFSNCategoriesCount`(IN fieldStaffId INT)
BEGIN
    
          DECLARE fsId INT;
--          DECLARE sFlag TINYINT(1);
          SET @fsId = fieldStaffId;
--          SET @sFlag = flag;
          
--                IF @sFlag = 1 THEN -- MyTeamsParticipants                
                
                            CREATE TEMPORARY TABLE fslcount (Categories VARCHAR(25),VALUE VARCHAR(15),COUNT INT);
                
                            INSERT INTO fslcount
                                VALUES ('Active','active',12),
                                       ('Inactive','inactive',11),
                                       ('Rejected','rejected',3),
                                       ('Future Prospects','FP',2);
                       
                             SELECT * FROM fslcount;
                 
 --                END IF;
    
    DROP TABLE fslcount;
    END$$

DELIMITER ;
DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPFSLHostFamilyCategoriesCount`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPFSLHostFamilyCategoriesCount`(IN fieldStaffId INT)
BEGIN
    
          DECLARE fsId INT;
--          DECLARE sFlag TINYINT(1);
          SET @fsId = fieldStaffId;
--          SET @sFlag = flag;
          
--                IF @sFlag = 1 THEN -- MyTeamsParticipants                
                
                            CREATE TEMPORARY TABLE fslcount (Categories VARCHAR(25),VALUE VARCHAR(15),COUNT INT);
                
                            INSERT INTO fslcount
                                VALUES ('Not Assigned To LC','NATL',12),
                                       ('Needs Bios','NB',11),
                                       ('Has Bios','HB',3),
                                       ('To Review','TR',2),
                                       ('Submitted','submitted',11),
                                       ('Rejected','rejected',9),
                                       ('Future Prospects','FP',8),
                                       ('LC Applicants','LA',6);
                       
                             SELECT * FROM fslcount;
                 
 --                END IF;
    
    DROP TABLE fslcount;
    END$$

DELIMITER ;
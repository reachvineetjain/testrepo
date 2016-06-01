INSERT INTO `SchoolStatus` (`schoolStatusName`)
VALUES ('Inactive'),('Future Prospects'),('Approve'),('Reject'),('In Review'),('Referral'),('Open'),('Draft');

## Following fields are new in Go system
`mainOfficeNumber`,`transportationBySchool`,`typeOfTransportation`,,`otherWaysToSchool`,`schoolTypeDescription`,
`otherWaysToSchool`,
,`academicCalender`,`daysForInclementWeather`,`followUpDate`;

##`typeOfScheduling`
Adage system have '4 day school week','6 grading periods','Trimester','Quarters','four quarters','07;15','7 period day','Semester','quarter system','Varied Schedules','Year Round',
'4 Term','M,W,F Univ. model','Quarter','Regular Classes','terms','IB','8 periods a day','Block','4 by 4','4 quarters','full-year classes','quaters','cant find info.',' 4 Quarters',
'Full Year','8 period day' these type of scheduling while Go system shows only two types with radio button 'Block' and 'Regular'.

INSERT INTO `School` (`schoolId`,`schoolName`,`schoolWebsite`,`PhysicalAddress`,`PhysicalCity`,`PhysicalUSStatesId`,`PhysicalzipCode`,
                      `mailingAddress`,`mailingCity`,`mailingUSStatesId`,`mailingZipCode`,`gradesIncluded`,`schoolType`,`greenHeartStudent`,
                      `acceptSecondSemesterStudents`,`acceptSeniors`,`studentBodySize`,`schoolStartTime`,`schoolEndTime`,`createdOn`,`createdBy`,
                      `modifiedOn`,`modifiedBy`,`waysToSchool`,`totalStudents`)
               SELECT `USHighSchoolID`,`SchoolName`,`SchoolWebsite`,`SchoolAddress`,`SchoolCity`,`SchoolStateID`,`SchoolPostalCode`,
                      `MailingAddress`,`MailingCity`,`MailingStateID`,`MailingPostalCode`,`GradesIncluded`,`SchoolType`,`NumberCCIStudentsAccept`,
                      `SecondSemesterStudents`,`AllowsSeniors`,`StudentBodySize`,`StartTime`,`EndTime`,`CreatedOn`,`CreatedBy`,
                      `ModifiedOn`,`ModifiedBy`,`TravelMethod`,StudentCount
               FROM cci_go.`USHighSchool` WHERE `USHighSchoolID` <> 0;
			   

UPDATE School
SET `mailingAddressIsSameAsPhysical` = 1
WHERE `PhysicalAddress` = `mailingAddress`
AND `PhysicalCity` = `MailingCity`
AND `PhysicalUSStatesId` = `mailingUSStatesId`
AND `PhysicalzipCode` = mailingZipCode;

UPDATE School s,cci_go.`USHighSchool` uhs
SET `studentBodyGender` = 'Co-Ed.'
WHERE s.schoolId = uhs.USHighSchoolID AND uhs.StudentBodyGender = 'Both';

UPDATE School s,cci_go.`USHighSchool` uhs
SET `studentBodyGender` = 'All girls'
WHERE s.schoolId = uhs.USHighSchoolID AND uhs.StudentBodyGender = 'Female';

UPDATE School s,cci_go.`USHighSchool` uhs
SET `studentBodyGender` = 'All boys'
WHERE s.schoolId = uhs.USHighSchoolID AND uhs.StudentBodyGender = 'Male';

UPDATE School s,cci_go.`USHighSchool` uhs
SET s.`isReligiousAffiliation` = uhs.ReligiousAffiliation,
s.`religiousAffiliationDetails` = uhs.ReligiousAffiliationDetails
WHERE s.schoolId = uhs.USHighSchoolID AND uhs.ReligiousAffiliation = 1;

UPDATE School s,cci_go.`USHighSchool` uhs
SET s.`isReligiousAffiliation` = 0,
s.`religiousAffiliationDetails` = uhs.ReligiousAffiliationDetails
WHERE s.schoolId = uhs.USHighSchoolID AND uhs.ReligiousAffiliation = -1;

---------------------------------------------------------------------------------------
/*  Data For SchoolSeasons */
---------------------------------------------------------------------------------------
-- earlyEndDateForSeniors,orientationStartDate,additionalComment,
INSERT INTO SchoolSeasons (schoolId,seasonId,schoolStartDate,semesterTwoStartDate,schoolEndDate,enrollmentDeadline,
createdOn,createdBy,modifiedOn,modifiedBy)
SELECT USHighSchoolID,SeasonID,SchoolStartDate,SemesterTwoStart,SchoolEndDate,EnrollmentDeadline,CreatedOn,CreatedBy,ModifiedOn,ModifiedBy
FROM USHighSchoolSeason WHERE USHighSchoolID <> 0;

UPDATE SchoolSeasons ss
SET ss.departmentProgramId = 

---------------------------------------------------------------------------------------
/*  Data For SchoolProgramDetails */
---------------------------------------------------------------------------------------

INSERT INTO SchoolProgramDetails (schoolId)
SELECT schoolId FROM School WHERE schoolId <> 0;

UPDATE SchoolProgramDetails spd,cci_go.HostFamilyParticipant hfp,cci_go.HostFamilySeason hfs
SET spd.departmentProgramId = hfs.departmentProgramId
WHERE spd.


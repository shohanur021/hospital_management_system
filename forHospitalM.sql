CREATE DATABASE HospitalManagement

CREATE TABLE ADMIN 
(
AdminID int IDENTITY(100001,1) PRIMARY KEY,
AdminName varchar(50) NOT NULL,
Phone varchar(11) NOT NULL,
Email varchar(100) NOT NULL,
PermanentAddress varchar(200) NOT NULL,
UserName varchar(50) NOT NULL,
UserPassword varchar(50) NOT NULL,
)

INSERT INTO ADMIN  VALUES ('Samina Mahjaben','01785858562','samina22@gmail.com','khilgaon, Dhaka','samina','samina129')
INSERT INTO ADMIN  VALUES ('Shohanur Rahman','01912349876','shohan41@gmail.com','Mirpur-1, Dhaka-1216','shohanur','shohanur127')

SELECT * FROM ADMIN 
SELECT UserName,UserPassword FROM ADMIN

CREATE TABLE DOCTOR
(
DoctorID int IDENTITY(200001,1) PRIMARY KEY,
DoctorName varchar(50) NOT NULL,
MobileNumber varchar(11) NOT NULL,
Qualification varchar(200) NOT NULL,
Specialization varchar(100) NOT NULL,
Department varchar(50) NOT NULL,
Fee int NOT NULL,
Gender varchar(50) NOT NULL,
PermanentAddress varchar(200) NOT NULL,
JoiningDate Date NULL,
UserName varchar(50) NOT NULL,          
UserPassword varchar(50) NOT NULL,
)

SELECT * FROM DOCTOR                            
INSERT INTO DOCTOR  VALUES ('Rahim Uddin','01785858541','MBBS, FRCS, MMST','Hematologist','Hematology',1000,'Male','Mirpur-1, Dhaka','2021-11-11','@rahim','rahim')
DROP TABLE DOCTOR


CREATE TABLE RECEPTIONIST
(
ReceptionistID int IDENTITY(50001,1) PRIMARY KEY,
ReceptionistName varchar(50) NOT NULL,
MobileNumber varchar(11) NOT NULL,
ShiftingTime varchar(50) NOT NULL CHECK(ShiftingTime='day' OR ShiftingTime='evening' OR ShiftingTime='night'),
Salary int NOT NULL,
PermanentAddress varchar(200) NOT NULL,
JoiningDate Date NULL,
UserName varchar(50) NOT NULL,          
UserPassword varchar(50) NOT NULL,
)


SELECT * FROM  RECEPTIONIST 
INSERT INTO  RECEPTIONIST  VALUES ('Rahim Uddin','01785858541','evening',12000,'Mirpur-1, Dhaka','2021-11-11','@rahim','rahim')
INSERT INTO  RECEPTIONIST  VALUES ('Jasim','01785858963','evening',15000,'Mirpur-1, Dhaka','2021-11-11','@jasim','jasim')
DROP TABLE  RECEPTIONIST


CREATE TABLE PHARMACIST 
(
PharmacistID int IDENTITY(80001,1) PRIMARY KEY,
PharmacistName varchar(50) NOT NULL,
MobileNumber varchar(11) NOT NULL,
ShiftingTime varchar(50) NOT NULL CHECK(ShiftingTime='day' OR ShiftingTime='evening' OR ShiftingTime='night'),
Salary int NOT NULL,
PermanentAddress varchar(200) NOT NULL,
JoiningDate Date NULL,
UserName varchar(50) NOT NULL,          
UserPassword varchar(50) NOT NULL,
)


SELECT * FROM  PHARMACIST
INSERT INTO  PHARMACIST  VALUES ('Rahim Uddin','01785858541','evening',12000,'Mirpur-1, Dhaka','2021-11-11','@rahim','rahim')
INSERT INTO  PHARMACIST  VALUES ('Jasim','01785858963','evening',15000,'Mirpur-1, Dhaka','2021-11-11','@jasim','jasim')
DROP TABLE  PHARMACIST


CREATE TABLE PATIENT
(
PatientID int IDENTITY(7001,1) PRIMARY KEY,
FirstName varchar(50) NOT NULL,
LastName varchar(50) NOT NULL,
MobileNumber varchar(11) NOT NULL,
Gender varchar(50) NOT NULL,
DateOfBirth Date NOT NULL,
Age int NOT NULL,
DiseageName varchar(50) NOT NULL,
PermanentAddress varchar(200) NULL,
)
SELECT * FROM  PATIENT
DROP TABLE PATIENT


CREATE TABLE APPOINTMENT
(
  VisitingDate DateTime NOT NULL,
  PatientID int NOT NULL FOREIGN KEY REFERENCES PATIENT(PatientID),
  DoctorID int NOT NULL FOREIGN KEY REFERENCES DOCTOR(DoctorID),
  ViewStatus varchar(10) NOT NULL DEFAULT 'pending',
) 
SELECT * FROM  APPOINTMENT
DROP TABLE  APPOINTMENT

INSERT INTO  PATIENT  VALUES ('Jasim','mc', '01785858963','male','Sep 1, 2021', 25,'bal','dhaka')
INSERT INTO  APPOINTMENT  VALUES ('Sep 1, 2021 02:34:44',7001,200001,'oo')
SELECT DoctorID, DoctorName, MobileNumber, Qualification, Specialization, Gender, Fee FROM DOCTOR WHERE Specialization='Neurologist'


SELECT  DoctorName,Qualification,Specialization,a.PatientID,FirstName+' '+LastName as Name,Age,p.Gender,DiseageName,VisitingDate FROM APPOINTMENT a INNER JOIN DOCTOR d ON a.DoctorID=d.DoctorID INNER JOIN PATIENT p ON p.PatientID=a.PatientID  WHERE d.DoctorID=200014 AND a.ViewStatus='pending'
SELECT DoctorName,Qualification,Specialization FROM  DOCTOR WHERE DoctorID=200014
UPDATE APPOINTMENT SET ViewStatus='pending' WHERE PatientID=7002

CREATE TABLE MEDICINE
(
  MedicineID int IDENTITY(30001,1) PRIMARY KEY,
  Name varchar(50) NOT NULL,
  Price int NOT NULL,
) 
DROP TABLE   MEDICINE
SELECT * FROM  MEDICINE

UPDATE APPOINTMENT SET ViewStatus='pending'

INSERT INTO  MEDICINE  VALUES ('Amoxil',31)
INSERT INTO  MEDICINE  VALUES ('Antabuse',35)
INSERT INTO  MEDICINE  VALUES ('Abilify',48)
INSERT INTO  MEDICINE  VALUES ('Abilax',150)
INSERT INTO  MEDICINE  VALUES ('Abilify Maintena',50)
INSERT INTO  MEDICINE  VALUES ('Acetaminophen',60)
INSERT INTO  MEDICINE  VALUES ('Acetylcysteine',45)
INSERT INTO  MEDICINE  VALUES ('Actos',43)
INSERT INTO  MEDICINE  VALUES ('Bisacodyl',80)
INSERT INTO  MEDICINE  VALUES ('Bisoprolol',45)
INSERT INTO  MEDICINE  VALUES ('Blincyto',30)
INSERT INTO  MEDICINE  VALUES ('Boniva',49)
INSERT INTO  MEDICINE  VALUES ('Botox',51)
INSERT INTO  MEDICINE  VALUES ('Breo Ellipta',107)
INSERT INTO  MEDICINE  VALUES ('Brilinta',75)
INSERT INTO  MEDICINE  VALUES ('Cabometyx',120)
INSERT INTO  MEDICINE  VALUES ('Calcium carbonate',145)
INSERT INTO  MEDICINE  VALUES ('Calquence',66)
INSERT INTO  MEDICINE  VALUES ('D-Penamine',202)
INSERT INTO  MEDICINE  VALUES ('D3 (Bioceuticals)',135)
INSERT INTO  MEDICINE  VALUES ('erdafitinib',250)
INSERT INTO  MEDICINE  VALUES ('Ercaf',81)
INSERT INTO  MEDICINE  VALUES ('erenumab',95)
INSERT INTO  MEDICINE  VALUES ('Famciclovir',301)
INSERT INTO  MEDICINE  VALUES ('Famotidine',190)
INSERT INTO  MEDICINE  VALUES ('Gabapentin',230)
INSERT INTO  MEDICINE  VALUES ('Gabitril',240)
INSERT INTO  MEDICINE  VALUES ('Gadavyt',115)
INSERT INTO  MEDICINE  VALUES ('Hydralazine',98)
INSERT INTO  MEDICINE  VALUES ('Hydrea',74)
INSERT INTO  MEDICINE  VALUES ('Ibuprofen',142)
INSERT INTO  MEDICINE  VALUES ('Jadenu',320)
INSERT INTO  MEDICINE  VALUES ('Jakafi',230)
INSERT INTO  MEDICINE  VALUES ('Ketizenol',87)
INSERT INTO  MEDICINE  VALUES ('Ketoral',161)
INSERT INTO  MEDICINE  VALUES ('L-glutamine',170)
INSERT INTO  MEDICINE  VALUES ('Macugen',231)
INSERT INTO  MEDICINE  VALUES ('Jakafi',56)
INSERT INTO  MEDICINE  VALUES ('Ketizenol',452)
INSERT INTO  MEDICINE  VALUES ('Ketoral',139)
INSERT INTO  MEDICINE  VALUES ('L-glutamine',187)
INSERT INTO  MEDICINE  VALUES ('Nadolol',161)
INSERT INTO  MEDICINE  VALUES ('Nafarelin',560)
INSERT INTO  MEDICINE  VALUES ('Paxil',478)

SELECT JoiningDate FROM RECEPTIONIST UNION ALL
SELECT JoiningDate FROM PHARMACIST UNION ALL
SELECT JoiningDate FROM DOCTOR

SELECT SUM(Salary) as salaries FROM PHARMACIST

SELECT SUM(Salary) as salaries FROM RECEPTIONIST

SELECT DoctorName,Qualification,Department,JoiningDate,Gender,MobileNumber FROM DOCTOR WHERE doctorId IN(SELECT MIN(DoctorID) FROM DOCTOR Group By Department)

 
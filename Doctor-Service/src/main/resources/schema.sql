SET DateStyle TO European;

DROP TABLE IF EXISTS `Users`, `Patients`, `Doctors`,`DoctorDetails`, `Appointments`;

CREATE TABLE `Users` (
  `mobile_num` varchar(255) PRIMARY KEY NOT NULL,
  `password` varchar(255) NOT NULL
);

CREATE TABLE `Patients` (
  `patient_id` varchar(255) PRIMARY KEY,
  `full_name` varchar(255)
);

CREATE TABLE `Doctors` (
  `doctor_id` varchar(255) PRIMARY KEY,
  `full_name` varchar(255),
  `department` ENUM ('UNKNOWN', 'Cardiology', 'Neurology', 'Orthopedics', 'Gastroenterology', 'Dermatology', 'Urology', 'Oncology', 'Radiology'),
  `degree` varchar(255),
  `profile_URL` varchar(255)
);

CREATE TABLE `DoctorDetails` (
  `doctor_id` varchar(255) PRIMARY KEY,
  `education` varchar(1000),
  `work_experience` varchar(1000),
  `interest` varchar(1000)
);

CREATE TABLE `Appointments` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `doctor_id` varchar(255),
  `patient_id` varchar(255),
  `date` date,
  `slot` ENUM ('UNKNOWN', 'Morning1', 'Morning2', 'Morning3', 'Afternoon1', 'Afternoon2', 'Afternoon3', 'Evening1', 'Evening2', 'Evening3'),
  `status` ENUM ('UNKNOWN', 'Done', 'Active', 'Cancelled'),
  `message` varchar(1000)
);

ALTER TABLE `Patients` ADD FOREIGN KEY (`patient_id`) REFERENCES `Users` (`mobile_num`) ON DELETE CASCADE;

ALTER TABLE `Doctors` ADD FOREIGN KEY (`doctor_id`) REFERENCES `Users` (`mobile_num`) ON DELETE CASCADE;

ALTER TABLE `DoctorDetails` ADD FOREIGN KEY (`doctor_id`) REFERENCES `Doctors` (`doctor_id`) ON DELETE CASCADE;

ALTER TABLE `Appointments` ADD FOREIGN KEY (`doctor_id`) REFERENCES `Doctors` (`doctor_id`)  ON DELETE CASCADE;

ALTER TABLE `Appointments` ADD FOREIGN KEY (`patient_id`) REFERENCES `Patients` (`patient_id`)  ON DELETE CASCADE;

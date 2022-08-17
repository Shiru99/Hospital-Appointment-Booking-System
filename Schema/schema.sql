SET DateStyle TO European;

DROP TABLE IF EXISTS `Users`, `Patients`, `Doctors`,`DoctorDetails`, `Appointments`;

CREATE TABLE `Users` (
  `mobile_num` varchar(255) PRIMARY KEY NOT NULL,
  `password` varchar(255) NOT NULL
);

CREATE TABLE `Patients` (
  `mobile_num` varchar(255) PRIMARY KEY,
  `full_name` varchar(255)
);

CREATE TABLE `Doctors` (
  `mobile_num` varchar(255) PRIMARY KEY,
  `full_name` varchar(255),
  `department` varchar(255),
  `degree` varchar(255),
  `profile_URL` varchar(255)
);

CREATE TABLE `DoctorDetails` (
  `doctor_id` varchar(255) PRIMARY KEY,
  `education` varchar(255),
  `work_experience` varchar(255),
  `specialisation` varchar(255)
);

CREATE TABLE `Appointments` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `doctor_id` varchar(255),
  `patient_id` varchar(255),
  `date` date,
  `slot` ENUM ('Morning1', 'Morning2', 'Morning3', 'Afternoon1', 'Afternoon2', 'Afternoon3', 'Evening1', 'Evening2', 'Evening3'),
  `status` ENUM ('Done', 'Active', 'Cancelled'),
  `message` varchar(255)
);

ALTER TABLE `Patients` ADD FOREIGN KEY (`mobile_num`) REFERENCES `Users` (`mobile_num`);

ALTER TABLE `Doctors` ADD FOREIGN KEY (`mobile_num`) REFERENCES `Users` (`mobile_num`);

ALTER TABLE `DoctorDetails` ADD FOREIGN KEY (`doctor_id`) REFERENCES `Doctors` (`mobile_num`);

ALTER TABLE `Appointments` ADD FOREIGN KEY (`doctor_id`) REFERENCES `Doctors` (`mobile_num`);

ALTER TABLE `Appointments` ADD FOREIGN KEY (`patient_id`) REFERENCES `Patients` (`mobile_num`);

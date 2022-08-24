INSERT INTO `Users` (`mobile_num`, `password`)
VALUES
    (
        '1234567890',
        '$2a$10$oCRuhNF0fDycAy9TdbdtOeZ8QcnH4JdkdH.8Hty.iDZNg0hBNNttS'
    ),
    (
        '1234567891',
        '$2a$10$oCRuhNF0fDycAy9TdbdtOeZ8QcnH4JdkdH.8Hty.iDZNg0hBNNttS'
    ),
    (
        '1234567892',
        '$2a$10$oCRuhNF0fDycAy9TdbdtOeZ8QcnH4JdkdH.8Hty.iDZNg0hBNNttS'
    ),
    (
        '1234567893',
        '$2a$10$oCRuhNF0fDycAy9TdbdtOeZ8QcnH4JdkdH.8Hty.iDZNg0hBNNttS'
    ),
    (
        '1234567894',
        '$2a$10$oCRuhNF0fDycAy9TdbdtOeZ8QcnH4JdkdH.8Hty.iDZNg0hBNNttS'
    ),
    (
        '1234567895',
        '$2a$10$oCRuhNF0fDycAy9TdbdtOeZ8QcnH4JdkdH.8Hty.iDZNg0hBNNttS'
    ),
    (
        '1234567896',
        '$2a$10$oCRuhNF0fDycAy9TdbdtOeZ8QcnH4JdkdH.8Hty.iDZNg0hBNNttS'
    ),
    (
        '1234567897',
        '$2a$10$oCRuhNF0fDycAy9TdbdtOeZ8QcnH4JdkdH.8Hty.iDZNg0hBNNttS'
    ),
    (
        '1234567898',
        '$2a$10$oCRuhNF0fDycAy9TdbdtOeZ8QcnH4JdkdH.8Hty.iDZNg0hBNNttS'
    ),
    (
        '1234567899',
        '$2a$10$oCRuhNF0fDycAy9TdbdtOeZ8QcnH4JdkdH.8Hty.iDZNg0hBNNttS'
    )
;

-- password : 1234

INSERT INTO `Patients` (`patient_id`, `full_name`)
VALUES  ( '1234567890', 'John Doe' ),
        ( '1234567891', 'jenny Doe');


INSERT INTO `Doctors` (
    `doctor_id`, `full_name`, `department`, `degree`, `profile_URL`)
VALUES
  ( '1234567892', 'Dr. Deba Dulal Biswal', 'CARDIOLOGY', 'MBBS, MD (General Medicine), DNB (Medical ONCOLOGY)', 'https://balcomedicalcentre.com/uploads/doctor/dr-deba-dulal-biswal.jpg' ),
  ( '1234567893', 'Dr. Radha Shivratan Soni', 'NEUROLOGY', 'DNB (Radiation ONCOLOGY), MNAMS, PDCR, ECMO', 'https://balcomedicalcentre.com/uploads/doctor/dr-sweta-shivratan-soni.jpg'),
  ( '1234567894', 'Dr. Sandeep Ojha', 'ORTHOPEDICS', 'MD Pathology, PDCC (Cyto), DipRCPath (Histopath)', 'https://balcomedicalcentre.com/uploads/doctor/dr-sandeep-ojha.jpg'),
  ( '1234567895', 'Dr. Shishir Agrawal', 'RADIOLOGY', 'MBBS, DMRD, DNB', 'https://balcomedicalcentre.com/uploads/doctor/dr-shishir-agrawal.jpg'),
  ( '1234567898', 'Dr. Dibyendu De', 'ONCOLOGY', 'MD (General Medicine), DM (Clinical Haematology)', 'https://balcomedicalcentre.com/uploads/doctor/dr-dibyendu-de.jpg'),
  ( '1234567896', 'Dr. Shreshtha Tiwari', 'DERMATOLOGY', 'MD (Microbiology)', 'https://balcomedicalcentre.com/uploads/doctor/dr-shreshtha-tiwari.jpg'),
  ( '1234567897', 'Dr. Jay Kumar Rai', 'UROLOGY', 'MBBS, DNB (Nuclear Medicine), MNAMS', 'https://balcomedicalcentre.com/uploads/doctor/dr-jay-kumar-rai.jpg'),
  ( '1234567899', 'Dr. Santosh Tharwani', 'GASTROENTEROLOGY', 'MD (Anaesthesiology), PDCC (Pain & Palliative care)', 'https://balcomedicalcentre.com/uploads/doctor/dr-santosh-tharwani.jpg');

INSERT INTO `DoctorDetails` (
    `doctor_id` , `education` , `work_experience` , `interest` )
VALUES
    ( '1234567899', '• Post-graduation from Banaras Hindu University, Varanasi• Post-doctoral Fellowship PDCC course in Pain & Palliative Care from BHU, Varanasi', '• Pt JNM Medical College, Raipur', 'Anaesthesiology with special interest in Interventional Pain and Palliative care' ),
    ( '1234567897', '• MBBS from N.S.C.B. Medical College, Jabalur, M.P.• DNB from Amrita Institute of Medical Sciences & Research Centre, Kochi• Fellow of European Board of Nuclear Medicine, Hamburg• Member of multiple scientific committee including ANMPI, SNMI & EANM', '• Galaxy Vidarbha Diagnostic Centre, Nagpur• Sri-Aurobindo Medical College & Research Centre, Indore• HCG, Vadodara, Gujarat', 'Radionuclide therapies & Diagnosis (Theranostics), especially in ONCOLOGY & Endocrinology with Whole body PET-CT & SPECT-CT scan interpretation' ),
    ( '1234567896', '• MD Microbiology from Seth GS Medical College and KEM Hospital, Mumbai• Member of Hospital Infection Society of India, Indian Association of Medical Microbiology and Chhattisgarh association of Medical Microbiolo gists• Multiple courses and certificate programs in infection control and antimicrobial stewardship organized by CMC Vellore and Shankara Nethralaya Chennai, British Society of antimicrobial Chemotherapy', 'AIIMS, Bhopal', 'Infection Prevention and Control, Antimicrobial and Diagnostic Stewardship, Antimicrobial Resistance and Mycology, Mycobacteriology and Molecular Diagnostics and Quality assurance program in Lab services' ),
    ( '1234567898', '• MBBS from NRS Medical College, Kolkata • MD in General Medicine from Kolkata Medical College • DM in Clinical Haematology from Institute of Hematology and Transfusion Medicine, Kolkata • Short term training on Bone marrow transplant from CMC Vellore • Certified by European Hematology Association (EHA)• Short term extramural hands off observership on Hematology and Bone Marrow Transplant from Singapore General Hospital, Singapore • Member European Hematology Association • Member Association of Physician India • Member Indian Association of Clinical Medicine', '• Institute of Hematology and Transfusion Medicine, Kolkata• The Mission Hospital, Durgapur', 'Leukaemia, Lymphoma, Multiple myeloma, Anemia including Sickle cell anaemia & bleeding disorder' ),
    ( '1234567895', '• MBBS from GSVM MC, Kanpur• DMRD from Pt. JNM MC, Raipur• DNB from Columbia Asia Hospital, Bangalore• Life member of IRIA', '• Guru Gobindh Singh Hospital,Delhi.• Sundaram Medical Foundation Hospital,Chennai.• Apollo BSR Hospital,Bhiali ,C.G.• Chandan Healthcare branch, Gorakhpur, U.P.• Param MRI Center & Galav CT, Gwalior,MP• Ganga Diagnostic and Medical Research,Raipur.', 'General RADIOLOGY & Head & Neck imaging' ),
    ( '1234567894', '• MD Pathology from Seth GSMC and KEM Hospital, Mumbai• Fellowship in Cytopathology MUHS Nashik. • Trained in departments of Histopathology and Cytopathology at Tata Memorial Hospital, Mumbai • Trained in Quality management system for Lab as per ISO 15189-2012• Diplomate Member of Royal College of Pathologist', '• Private Medical College and Hospital • SRL Lab, Bhopal • Tata Memorial Hospital• Chirayu Medical College and Hospital, Bhopal', 'Histopathology, Cytopathology, Immunohistochemistry, Haematology' ),
    ( '1234567893', '• DNB from BMCHRC, Jaipur• MBBS from PDVVPF’s Medical College, Ahmednagar, Maharashtra• ESMO Certified Medical ONCOLOGY • Professional Diploma in Clinical Research• Essentials of Palliative Care by IAPC', '• AIIMS, Jodhpur• BMCHRC, Jaipur• KJ Somaiya Medical College, Mumbai• PDVVPF’s Medical College, Ahmednagar, Maharashtra', '3D CRT, IMRT, IGRT, VMAT, PET-CT based planning, Brachytherapy' ),
    ( '1234567892', '• MBBS from MKCG Medical College, Odisha • MD in General Medicine from VSS Medical College, Burla, Odisha • DNB from SL Raheja Hospital (Fortis associate), Mahim, Mumbai ', '• Hi-tech medical College, Bhubaneshwar • Tata Medical Centre, Kolkata • Capitol Hospital, Jalandhar', 'All types of cancers in Adults, Paediatric population including Solid and Blood' )
;

INSERT INTO `Appointments` (
  `appointment_id`, `doctor_id`, `patient_id`, `date`, `slot`, `status`, `message`, `paid`
)
VALUES  
    ( 1, '1234567896', '1234567890','2021-11-23', 'MORNING1', 'ACTIVE',  'Medical Summary Reports provide an overview of the your personal history, occupational history, health history, psychiatric history, and functioning. These reports are often created by case workers. Ideally, they are also co-signed by the applicant’s doctor, psychologist, or psychiatrist.','YES' ),
    ( 2, '1234567896', '1234567891','2022-11-24', 'MORNING2', 'CANCELLED',  'Medical Summary Reports provide an overview of the your personal history, occupational history, health history, psychiatric history, and functioning. These reports are often created by case workers. Ideally, they are also co-signed by the applicant’s doctor, psychologist, or psychiatrist.','YES' ),
    ( 3, '1234567896', '1234567890','2022-11-26', 'MORNING3', 'CANCELLED',  'Medical Summary Reports provide an overview of the your personal history, occupational history, health history, psychiatric history, and functioning. These reports are often created by case workers. Ideally, they are also co-signed by the applicant’s doctor, psychologist, or psychiatrist.','YES' ),
    ( 4, '1234567896', '1234567891','2021-11-27', 'AFTERNOON1', 'DONE',  'Medical Summary Reports provide an overview of the your personal history, occupational history, health history, psychiatric history, and functioning. These reports are often created by case workers. Ideally, they are also co-signed by the applicant’s doctor, psychologist, or psychiatrist.','YES' ),
    ( 5, '1234567895', '1234567890','2022-11-20', 'EVENING1', 'ACTIVE',  'Medical Summary Reports provide an overview of the your personal history, occupational history, health history, psychiatric history, and functioning. These reports are often created by case workers. Ideally, they are also co-signed by the applicant’s doctor, psychologist, or psychiatrist.','YES' ),
    ( 6, '1234567896', '1234567891','2022-11-20', 'EVENING3', 'ACTIVE',  'Medical Summary Reports provide an overview of the your personal history, occupational history, health history, psychiatric history, and functioning. These reports are often created by case workers. Ideally, they are also co-signed by the applicant’s doctor, psychologist, or psychiatrist.','YES' ),
    ( 7, '1234567896', '1234567890','2022-11-20', 'EVENING2', 'ACTIVE',  'Medical Summary Reports provide an overview of the your personal history, occupational history, health history, psychiatric history, and functioning. These reports are often created by case workers. Ideally, they are also co-signed by the applicant’s doctor, psychologist, or psychiatrist.','UNKNOWN' )
;


-- DELETE FROM `Users` WHERE `mobile_num`='1234567899';
-- DELETE FROM `Doctors` WHERE `doctor_id`='1234567896';
-- DELETE FROM `Users` WHERE `mobile_num`='1234567892';
-- DELETE FROM `Users` WHERE `mobile_num`='1234567890';



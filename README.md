# Appointment booking system

## Description

There are two main features in this project, the patient can book an appointment in an available slot and the doctor can view his bookings after log-in. The patient should register and sign in to book an appointment. To achieve this we are using two tables, one for a doctor having fields date and slots & another for patients whose fields will be their details, doctor name, and date of appointment along with slots.

Reference Website : https://balcomedicalcentre.com/


---

## Backend using:- SpringBoot Microservices

1. Patient Microservice
2. Doctor Microservice
3. Appointment Microservice

### Database : 

* Mysql

### DB Schema :

* [DBDiagram](https://dbdiagram.io/d/62fc949dc2d9cf52fac018af)

### Login : JWT

### APIs needed : 

1. Login:

    * POST /login

2. Patient :

    * POST /signup
    * GET /patients
    * GET /patients/{id}
    * PUT /patients/{id}
    * DELETE /patients/{id}

3. Doctor :
        
    * POST /signup
    * GET /doctors
    * GET /doctors/{id}
    * GET /doctors/search/{query}
    * PUT /doctors/{id}
    * DELETE /doctors/{id}

4. Appointment :

    * POST /appointments
    * GET /appointments
    * GET /appointments/{id}
    * PUT /appointments/{id}
    * DELETE /appointments/{id}
    * GET /appointments/patients/{patientId}
    * GET /appointments/doctors/{doctorId}
    * GET /appointments/patients/{patientId}/{status}
    * GET /appointments/doctors/{doctorId}/{status}
    * GET /appointments/free-slots/{doctorId}/{date}
    * GET /payment/pay/{appointmentId}
    * GET /appointmentDetails/{appointmentId}


### Testing : JUnit & [Thunder Client](https://marketplace.visualstudio.com/items?itemName=rangav.vscode-thunder-client)

- [x] Login
- [x] Patient
- [x] Doctor
- [x] Booking
- [x] Payment

---

## Frontend using:- Angular

1. UI Microservice

### Design :

* [mockflow](https://wireframepro.mockflow.com/view/Appointment-booking-system)

### Templates :

* https://app.uizard.io/prototypes?create


---

## TODO

- [X] Communication : Appointment - Patient & Doctor
- [ ] Error handling
- [ ] API gateway



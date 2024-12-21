package main.com.hospital;

import main.com.hospital.dao.PatientDAO;
import main.com.hospital.dao.DoctorDAO;
import main.com.hospital.dao.AppointmentDAO;
import main.com.hospital.model.Patient;
import main.com.hospital.model.Doctor;
import main.com.hospital.model.Appointment;

import java.sql.Timestamp;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PatientDAO patientDAO = new PatientDAO();
        DoctorDAO doctorDAO = new DoctorDAO();
        AppointmentDAO appointmentDAO = new AppointmentDAO();

        Patient patient = new Patient();
        patient.setName("John Doe");
        patient.setAge(30);
        patient.setGender("Male");
        patient.setContact("1234567890");
        patientDAO.addPatient(patient);

        Doctor doctor = new Doctor();
        doctor.setName("Dr. Smith");
        doctor.setSpecialization("Cardiology");
        doctor.setContact("0987654321");
        doctorDAO.addDoctor(doctor);

        List<Patient> patients = patientDAO.getAllPatients();
        System.out.println("Patients:");
        for (Patient p : patients) {
            System.out.println(p.getName());
        }

        List<Doctor> doctors = doctorDAO.getAllDoctors();
        System.out.println("Doctors:");
        for (Doctor d : doctors) {
            System.out.println(d.getName());
        }

        Appointment appointment = new Appointment();
        appointment.setPatientId(patients.get(0).getId()); 
        appointment.setDoctorId(doctors.get(0).getId()); 
        appointment.setAppointmentDate(Timestamp.valueOf("2023-10-01 10:00:00"));
        appointmentDAO.addAppointment(appointment);
    }
}
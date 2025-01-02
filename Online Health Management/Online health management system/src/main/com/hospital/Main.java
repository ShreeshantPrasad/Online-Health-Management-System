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
        Scanner scanner = new Scanner(System.in);

        // Adding a new patient
        System.out.println("Enter patient name:");
        String patientName = scanner.nextLine();

        System.out.println("Enter patient age:");
        int patientAge = getValidInteger(scanner, "Age must be a positive integer.");

        System.out.println("Enter patient gender (Male/Female/Other):");
        String patientGender = scanner.nextLine();

        System.out.println("Enter patient contact number:");
        String patientContact = scanner.nextLine();

        if (isValidContact(patientContact)) {
            Patient patient = new Patient();
            patient.setName(patientName);
            patient.setAge(patientAge);
            patient.setGender(patientGender);
            patient.setContact(patientContact);
            patientDAO.addPatient(patient);
            System.out.println("Patient added successfully.");
        } else {
            System.out.println("Invalid contact number. Patient not added.");
        }

        // Adding a new doctor
        System.out.println("Enter doctor name:");
        String doctorName = scanner.nextLine();

        System.out.println("Enter doctor specialization:");
        String doctorSpecialization = scanner.nextLine();

        System.out.println("Enter doctor contact number:");
        String doctorContact = scanner.nextLine();

        if (isValidContact(doctorContact)) {
            Doctor doctor = new Doctor();
            doctor.setName(doctorName);
            doctor.setSpecialization(doctorSpecialization);
            doctor.setContact(doctorContact);
            doctorDAO.addDoctor(doctor);
            System.out.println("Doctor added successfully.");
        } else {
            System.out.println("Invalid contact number. Doctor not added.");
        }

        // Retrieving all patients
        List<Patient> patients = patientDAO.getAllPatients();
        System.out.println("Patients:");
        for (Patient p : patients) {
            System.out.println(p.getName());
        }

        // Retrieving all doctors
        List<Doctor> doctors = doctorDAO.getAllDoctors();
        System.out.println("Doctors:");
        for (Doctor d : doctors) {
            System.out.println(d.getName());
        }

        // Creating an appointment
        System.out.println("Enter appointment date (yyyy-mm-dd hh:mm:ss):");
        String appointmentDateStr = scanner.nextLine();
        Timestamp appointmentDate = Timestamp.valueOf(appointmentDateStr);

        Appointment appointment = new Appointment();
        appointment.setPatientId(patients.get(0).getId()); 
        appointment.setDoctorId(doctors.get(0).getId()); 
        appointment.setAppointmentDate(appointmentDate);
        appointmentDAO.addAppointment(appointment);
        System.out.println("Appointment created successfully.");
    }

    private static int getValidInteger(Scanner scanner, String errorMessage) {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine());
                if (value > 0) {
                    return value;
                } else {
                    System.out.println(errorMessage);
                }
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }

    private static boolean isValidContact(String contact) {
        return contact.matches("\\d{10}"); 
    }
}

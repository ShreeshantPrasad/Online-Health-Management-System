package com.hospital.ui;

import main.com.hospital.dao.AppointmentDAO;
import main.com.hospital.dao.DoctorDAO;
import main.com.hospital.dao.PatientDAO;
import main.com.hospital.model.Appointment;
import main.com.hospital.model.Doctor;
import main.com.hospital.model.Patient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class HospitalManagementUI extends JFrame {
    private PatientDAO patientDAO;
    private DoctorDAO doctorDAO;
    private AppointmentDAO appointmentDAO;

    private JTextField patientNameField, patientAgeField, patientGenderField, patientContactField;
    private JTextField doctorNameField, doctorSpecializationField, doctorContactField;
    private JTextField appointmentPatientIdField, appointmentDoctorIdField, appointmentDateField;

    public HospitalManagementUI() {
        patientDAO = new PatientDAO();
        doctorDAO = new DoctorDAO();
        appointmentDAO = new AppointmentDAO();

        setTitle("Hospital Management System");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 1));

        // Patient Section
        createPatientSection();
        // Doctor Section
        createDoctorSection();
        // Appointment Section
        createAppointmentSection();
        // Display Section
        createDisplaySection();
    }

    private void createPatientSection() {
        JPanel patientPanel = new JPanel();
        patientPanel.setBorder(BorderFactory.createTitledBorder("Add Patient"));
        patientNameField = new JTextField(10);
        patientAgeField = new JTextField(10);
        patientGenderField = new JTextField(10);
        patientContactField = new JTextField(10);
        JButton addPatientButton = new JButton("Add Patient");
        addPatientButton.addActionListener(new AddPatientAction());
        patientPanel.add(new JLabel("Name:"));
        patientPanel.add(patientNameField);
        patientPanel.add(new JLabel("Age:"));
        patientPanel.add(patientAgeField);
        patientPanel.add(new JLabel("Gender:"));
        patientPanel.add(patientGenderField);
        patientPanel.add(new JLabel("Contact:"));
        patientPanel.add(patientContactField);
        patientPanel.add(addPatientButton);
        add(patientPanel);
    }

    private void createDoctorSection() {
        JPanel doctorPanel = new JPanel();
        doctorPanel.setBorder(BorderFactory.createTitledBorder("Add Doctor"));
        doctorNameField = new JTextField(10);
        doctorSpecializationField = new JTextField(10);
        doctorContactField = new JTextField(10);
        JButton addDoctorButton = new JButton("Add Doctor");
        addDoctorButton.addActionListener(new AddDoctorAction());
        doctorPanel.add(new JLabel("Name:"));
        doctorPanel.add(doctorNameField);
        doctorPanel.add(new JLabel("Specialization:"));
        doctorPanel.add(doctorSpecializationField);
        doctorPanel.add(new JLabel("Contact:"));
        doctorPanel.add(doctorContactField);
        doctorPanel.add(addDoctorButton);
        add(doctorPanel);
    }

    private void createAppointmentSection() {
        JPanel appointmentPanel = new JPanel();
        appointmentPanel.setBorder(BorderFactory.createTitledBorder("Schedule Appointment"));
        appointmentPatientIdField = new JTextField(10);
        appointmentDoctorIdField = new JTextField(10);
        appointmentDateField = new JTextField(10);
        JButton scheduleAppointmentButton = new JButton("Schedule Appointment");
        scheduleAppointmentButton.addActionListener(new ScheduleAppointmentAction());
        appointmentPanel.add(new JLabel("Patient ID:"));
        appointmentPanel.add(appointmentPatientIdField);
        appointmentPanel.add(new JLabel("Doctor ID:"));
        appointmentPanel.add(appointmentDoctorIdField);
        appointmentPanel.add(new JLabel("Date (yyyy-MM-dd HH:mm:ss):"));
        appointmentPanel.add(appointmentDateField);
        appointmentPanel.add(scheduleAppointmentButton);
        add(appointmentPanel);
    }

    private void createDisplaySection() {
        JButton viewPatientsButton = new JButton("View Patients");
        viewPatientsButton.addActionListener(e -> viewPatients());
        JButton viewDoctorsButton = new JButton("View Doctors");
        viewDoctorsButton.addActionListener(e -> viewDoctors());
        JButton viewAppointmentsButton = new JButton("View Appointments");
        viewAppointmentsButton.addActionListener(e -> viewAppointments());
        add(viewPatientsButton);
        add(viewDoctorsButton);
        add(viewAppointmentsButton);
    }

    private void viewPatients() {
        List<Patient> patients = patientDAO.getAllPatients();
        StringBuilder patientList = new StringBuilder("Patients:\n");
        for (Patient p : patients) {
            patientList.append(p.getId()).append(": ").append(p.getName()).append("\n");
        }
        JOptionPane.showMessageDialog(this, patientList.toString());
    }

    private void viewDoctors() {
        List<Doctor> doctors = doctorDAO.getAllDoctors();
        StringBuilder doctorList = new StringBuilder("Doctors:\n");
        for (Doctor d : doctors) {
            doctorList.append(d.getId()).append(": ").append(d.getName()).append(" - ").append(d.getSpecialization()).append("\n");
        }
        JOptionPane.showMessageDialog(this, doctorList.toString());
    }

    private void viewAppointments() {
        List<Appointment> appointments = appointmentDAO.getAllAppointments();
        StringBuilder appointmentList = new StringBuilder("Appointments:\n");
        for (Appointment a : appointments) {
            appointmentList.append("Patient ID: ").append(a.getPatientId())
                    .append(", Doctor ID: ").append(a.getDoctorId())
                    .append(", Date: ").append(a.getAppointmentDate()).append("\n");
        }
        JOptionPane.showMessageDialog(this, appointmentList.toString());
    }

    private class AddPatientAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = patientNameField.getText();
                int age = Integer.parseInt(patientAgeField.getText());
                String gender = patientGenderField.getText();
                String contact = patientContactField.getText();
                Patient patient = new Patient(name, age, gender, contact);
                patientDAO.addPatient(patient);
                JOptionPane.showMessageDialog(HospitalManagementUI.this, "Patient added successfully!");
                clearPatientFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(HospitalManagementUI.this, "Invalid age. Please enter a number.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(HospitalManagementUI.this, "Error adding patient: " + ex.getMessage());
            }
        }
    }

    private class AddDoctorAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = doctorNameField.getText();
                String specialization = doctorSpecializationField.getText();
                String contact = doctorContactField.getText();
                Doctor doctor = new Doctor(name, specialization, contact);
                doctorDAO.addDoctor(doctor);
                JOptionPane.showMessageDialog(HospitalManagementUI.this, "Doctor added successfully!");
                clearDoctorFields();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(HospitalManagementUI.this, "Error adding doctor: " + ex.getMessage());
            }
        }
    }

    private class ScheduleAppointmentAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int patientId = Integer.parseInt(appointmentPatientIdField.getText());
                int doctorId = Integer.parseInt(appointmentDoctorIdField.getText());
                String dateString = appointmentDateField.getText();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Timestamp appointmentDate = new Timestamp(dateFormat.parse(dateString).getTime());
                Appointment appointment = new Appointment(patientId, doctorId, appointmentDate);
                appointmentDAO.scheduleAppointment(appointment);
                JOptionPane.showMessageDialog(HospitalManagementUI.this, "Appointment scheduled successfully!");
                clearAppointmentFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(HospitalManagementUI.this, "Invalid Patient ID or Doctor ID. Please enter numbers.");
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(HospitalManagementUI.this, "Invalid date format. Please use yyyy-MM-dd HH:mm:ss.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(HospitalManagementUI.this, "Error scheduling appointment: " + ex.getMessage());
            }
        }
    }

    private void clearPatientFields() {
        patientNameField.setText("");
        patientAgeField.setText("");
        patientGenderField.setText("");
        patientContactField.setText("");
    }

    private void clearDoctorFields() {
        doctorNameField.setText("");
        doctorSpecializationField.setText("");
        doctorContactField.setText("");
    }

    private void clearAppointmentFields() {
        appointmentPatientIdField.setText("");
        appointmentDoctorIdField.setText("");
        appointmentDateField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HospitalManagementUI ui = new HospitalManagementUI();
            ui.setVisible(true);
        });
    }
}
package main.com.hospital.dao;

import main.com.hospital.model.Patient;
import main.com.hospital.service.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    public void addPatient(Patient patient) {
        String sql = "INSERT INTO Patients (name, age, gender, contact) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, patient.getName());
            pstmt.setInt(2, patient.getAge());
            pstmt.setString (3, patient.getGender());
            pstmt.setString(4, patient.getContact());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding patient: " + e.getMessage());
        }
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM Patients";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Patient patient = new Patient(rs.getString("name"), rs.getInt("age"), rs.getString("gender"), rs.getString("contact"));
                patient.setId(rs.getInt("id"));
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving patients: " + e.getMessage());
        }
        return patients;
    }
}
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
            pstmt.setString(3, patient.getGender());
            pstmt.setString(4, patient.getContact());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // Log the exception or handle it accordingly
            System.err.println("Error adding patient: " + e.getMessage());
        }
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM Patients";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setName(rs.getString("name"));
                patient.setAge(rs.getInt("age"));
                patient.setGender(rs.getString("gender"));
                patient.setContact(rs.getString("contact"));
                patients.add(patient);
            }
        } catch (SQLException e) {
            // Log the exception or handle it accordingly
            System.err.println("Error retrieving patients: " + e.getMessage());
        }
        return patients;
    }

    public void updatePatient(Patient patient) {
        String sql = "UPDATE Patients SET name = ?, age = ?, gender = ?, contact = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, patient.getName());
            pstmt.setInt(2, patient.getAge());
            pstmt.setString(3, patient.getGender());
            pstmt.setString(4, patient.getContact());
            pstmt.setInt(5, patient.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // Log the exception or handle it accordingly
            System.err.println("Error updating patient: " + e.getMessage());
        }
    }

    public void deletePatient(int id) {
        String sql = "DELETE FROM Patients WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // Log the exception or handle it accordingly
            System.err.println("Error deleting patient: " + e.getMessage());
        }
    }
}
package main.com.hospital.dao;

import main.com.hospital.model.Doctor;
import main.com.hospital.service.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    public void addDoctor(Doctor doctor) {
        String sql = "INSERT INTO Doctors (name, specialization, contact) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, doctor.getName());
            pstmt.setString(2, doctor.getSpecialization());
            pstmt.setString(3, doctor.getContact());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding doctor: " + e.getMessage());
        }
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM Doctors";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Doctor doctor = new Doctor(rs.getString("name"), rs.getString("specialization"), rs.getString("contact"));
                doctor.setId(rs.getInt("id"));
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving doctors: " + e.getMessage());
        }
        return doctors;
    }
}
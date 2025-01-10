package main.com.hospital.dao;

import main.com.hospital.model.Appointment;
import main.com.hospital.service.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    public void scheduleAppointment(Appointment appointment) {
        String sql = "INSERT INTO Appointments (patientId, doctorId, appointmentDate) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, appointment.getPatientId());
            pstmt.setInt(2, appointment.getDoctorId());
            pstmt.setTimestamp(3, appointment.getAppointmentDate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error scheduling appointment: " + e.getMessage());
        }
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM Appointments";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Appointment appointment = new Appointment(rs.getInt("patientId"), rs.getInt("doctorId"), rs.getTimestamp("appointmentDate"));
                appointment.setId(rs.getInt("id"));
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving appointments: " + e.getMessage());
        }
        return appointments;
    }
}
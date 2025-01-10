package main.com.hospital;

import com.hospital.ui.HospitalManagementUI;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Use SwingUtilities to ensure that the GUI is created on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            HospitalManagementUI ui = new HospitalManagementUI();
            ui.setVisible(true);
        });
    }
}

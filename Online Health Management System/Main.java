import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        String patientPath = "data/Patient_List.csv";
        String staffPath = "data/Staff_List.csv";
        String medicinePath = "data/Medicine_List.csv";

        PatientContainer patientContainer = new PatientContainer(patientPath);
        DoctorContainer doctorContainer = new DoctorContainer(staffPath);
        PharmacistContainer pharmacistContainer = new PharmacistContainer(staffPath);
        AdministratorContainer administratorContainer = new AdministratorContainer(staffPath);

        MedicineContainer medicineContainer = new MedicineContainer(medicinePath);
        AppointmentContainer appointmentContainer = new AppointmentContainer();
        AppointmentOutcomeRecordContainer appointmentOutcomeContainer = new AppointmentOutcomeRecordContainer();
        ReplenishmentRequestContainer replenishmentRequestContainer = new ReplenishmentRequestContainer();

        HashMap<String, Container> containers = new HashMap<>();
        containers.put("Patient", patientContainer);
        containers.put("Doctor", doctorContainer);
        containers.put("Pharmacist", pharmacistContainer);
        containers.put("Administrator", administratorContainer);
        containers.put("Medicine", medicineContainer);
        containers.put("Appointment", appointmentContainer);
        containers.put("AppointmentOutcomeRecord", appointmentOutcomeContainer);
        containers.put("ReplenishmentRequest", replenishmentRequestContainer);

        StartMenu startMenu = new StartMenu(containers);
        startMenu.run();
    }
}

class Container {
}

class PatientContainer extends Container {
    public PatientContainer(String path) {
    }
}

class DoctorContainer extends Container {
    public DoctorContainer(String path) {
    }
}

class PharmacistContainer extends Container {
    public PharmacistContainer(String path) {
    }
}

class AdministratorContainer extends Container {
    public AdministratorContainer(String path) {
    }
}

class MedicineContainer extends Container {
    public MedicineContainer(String path) {
    }
}

class AppointmentContainer extends Container {
    public AppointmentContainer() {
    }
}

class AppointmentOutcomeRecordContainer extends Container {
    public AppointmentOutcomeRecordContainer() {
    }
}

class ReplenishmentRequestContainer extends Container {
    public ReplenishmentRequestContainer() {
    }
}

class StartMenu {
    private HashMap<String, Container> containers;

    public StartMenu(HashMap<String, Container> containers) {
        this.containers = containers;
    }

    public void run() {
    }
}

package VirtualDoctor;

import java.util.Scanner;

/**
 * MainController is the entry point for the VirtualDoctor application, handling interactions
 * between patients and staff. It provides a menu system for users to either interact as a patient
 * or as a staff member. The class manages different roles such as Receptionist, Doctor, Nurse, Pharmacist, and Cashier.
 */
public class MainController implements Runnable {
    private final Scanner scanner = new Scanner(System.in);

    // Pass names to the constructors
    private final Receptionist receptionist = new Receptionist("Rachel");
    private final Doctor doctor = new Doctor("Dr. John Smith", 101);
    private final Nurse nurse = new Nurse("Alice");
    private final Pharmacist pharmacist = new Pharmacist("Claire");
    private final Cashier cashier = new Cashier("John Doe");
    private final RecordManager recordManager = new RecordManager();
    private String currentStaffRole = null;

    /**
     * Main method to start the application and invoke the run method.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        MainController controller = new MainController();
        controller.run();
    }

    /**
     * Starts the application by calling the startApplication method.
     */
    @Override
    public void run() {
        startApplication();
    }

    /**
     * Displays the main menu and allows the user to select an option (patient, staff, or exit).
     * Depending on the selection, it calls the corresponding method.
     */
    public void startApplication() {
        while (true) {
            System.out.println("\nWelcome to VirtualDoctor!");
            System.out.println("Are you a 1. Patient or 2. Staff?");
            System.out.println("3. Exit");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    handlePatientInteraction();
                    break;
                case 2:
                    loginStaff();
                    break;
                case 3:
                    System.out.println("Exiting VirtualDoctor. Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice. Please restart the application.");
                    break;
            }
        }
    }

    /**
     * Handles the patient interaction by providing a menu for booking appointments and visiting the doctor.
     */
    private void handlePatientInteraction() {
        System.out.println("Patient Menu:");
        System.out.println("1. Book an Appointment");
        System.out.println("2. Visit the Doctor");
        System.out.println("3. Exit to Main Menu");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            return;
        }

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                receptionist.bookPatientAppointment();
                break;
            case 2:
                visitDoctor();
                break;
            case 3:
                System.out.println("Exiting to Main Menu...");
                break;
            default:
                System.out.println("Invalid option. Returning to main menu.");
                break;
        }
    }

    /**
     * Handles the doctor visit process, including checking if the patient is a first-time visitor or an existing one.
     */
    private void visitDoctor() {
        System.out.println("Is this your first time in the hospital?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            return;
        }

        int choice = scanner.nextInt();
        Patient patient;
        if (choice == 1) {
            patient = registerNewPatient();
            if (doctor.diagnosePatient(patient)) {
                handlePharmacistInteraction(patient.getName());
                handleCashierInteraction(patient.getName());
            }
        } else if (choice == 2) {
            System.out.println("Please enter your name:");
            String patientName = scanner.next();
            patient = recordManager.getPatientRecord(patientName);
            if (patient != null && doctor.diagnosePatient(patient)) {
                handlePharmacistInteraction(patient.getName());
                handleCashierInteraction(patient.getName());
            } else {
                System.out.println("No record found. Please register as a new patient.");
            }
        }
    }

    /**
     * Registers a new patient by collecting necessary information.
     *
     * @return the newly registered Patient object
     */
    private Patient registerNewPatient() {
        System.out.println("Please enter your name:");
        String name = scanner.next();
        System.out.println("Please enter your age:");
        int age = scanner.nextInt();
        System.out.println("Please enter your nationality:");
        String nationality = scanner.next();
        System.out.println("Please enter your address:");
        String address = scanner.next();

        Patient patient = new Patient(name, age, nationality, address);
        recordManager.addPatientRecord(patient);
        System.out.println("Patient record created for " + name);
        return patient;
    }

    /**
     * Allows staff to log in by entering their staff ID and password.
     */
    private void loginStaff() {
        System.out.println("Please enter your Staff ID:");
        String staffId = scanner.next();
        System.out.println("Please enter your password:");
        String password = scanner.next();

        if (authenticateStaff(staffId, password)) {
            currentStaffRole = staffId;
            handleStaffSession();
        } else {
            System.out.println("Invalid credentials. Returning to main menu.");
        }
    }

    /**
     * Handles the session for different types of staff based on the role.
     */
    private void handleStaffSession() {
        switch (currentStaffRole) {
            case "receptionist":
                receptionistTasks();
                break;
            case "doctor":
                doctorTasks();
                break;
            case "nurse":
                nurseTasks();
                break;
            case "pharmacist":
                pharmacistTasks();
                break;
            case "cashier":
                cashierTasks();
                break;
            default:
                System.out.println("Logging out...");
                break;
        }
        currentStaffRole = null;
    }

    /**
     * Authenticates the staff based on their ID and password.
     *
     * @param staffId the ID of the staff
     * @param password the password of the staff
     * @return true if authentication is successful, otherwise false
     */
    private boolean authenticateStaff(String staffId, String password) {
        switch (staffId) {
            case "receptionist":
                return password.equals("receptionist123");
            case "doctor":
                return password.equals("doctor123");
            case "nurse":
                return password.equals("nurse123");
            case "pharmacist":
                return password.equals("pharma123");
            case "cashier":
                return password.equals("cashier123");
            default:
                return false;
        }
    }

    /**
     * Performs the tasks associated with the receptionist, such as booking appointments and viewing appointments.
     */
    private void receptionistTasks() {
        while (true) {
            System.out.println("Receptionist Tasks:");
            System.out.println("1. Book an Appointment");
            System.out.println("2. View Appointments");
            System.out.println("3. Exit to Main Menu");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    receptionist.bookPatientAppointment();
                    break;
                case 2:
                    receptionist.viewAppointments();
                    break;
                case 3:
                    System.out.println("Exiting to Main Menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Please select again.");
                    break;
            }
        }
    }

    /**
     * Performs tasks associated with the doctor, such as adding diagnoses and generating diagnosis reports.
     */
    private void doctorTasks() {
        while (true) {
            System.out.println("Doctor Tasks:");
            System.out.println("1. Add Diagnoses");
            System.out.println("2. Generate Diagnosis Report");
            System.out.println("3. Exit to Main Menu");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    doctor.addSymptomDiseasePair();
                    break;
                case 2:
                    doctor.generateDiagnosisReport();
                    break;
                case 3:
                    System.out.println("Exiting to Main Menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Please select again.");
                    break;
            }
        }
    }

    /**
     * Performs tasks associated with the nurse, such as taking vitals and previewing patient vitals.
     */
    private void nurseTasks() {
        while (true) {
            System.out.println("Nurse Tasks:");
            System.out.println("1. Take Vitals");
            System.out.println("3. Preview Patient Vitals");
            System.out.println("4. Exit to Main Menu");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter patient name:");
                    String patientName = scanner.next();
                    nurse.takeVitals(patientName);
                    break;
                case 3:
                    nurse.previewVitals();
                    break;
                case 4:
                    System.out.println("Exiting to Main Menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Please select again.");
                    break;
            }
        }
    }

    /**
     * Handles the interaction with the pharmacist, including dispensing medications for the patient.
     */
    private void handlePharmacistInteraction(String patientName) {
        System.out.println("Enter the prescribed medication:");
        scanner.nextLine(); // Clear the newline
        String prescription = scanner.nextLine();
        System.out.println("Pharmacist: Dispensing medication for " + patientName + ".");
        pharmacist.dispenseMedication(patientName, prescription);
    }

    /**
     * Handles the interaction with the cashier, including processing the payment for the patient.
     */
    private void handleCashierInteraction(String patientName) {
        System.out.println("Cashier: Please enter the amount for your payment.");
        double amount = scanner.nextDouble();
        cashier.processPayment(patientName, amount);
    }

    /**
     * Performs tasks associated with the pharmacist, including adding prescriptions and viewing them.
     */
    private void pharmacistTasks() {
        while (true) {
            System.out.println("Pharmacist Tasks:");
            System.out.println("1. Add Prescription");
            System.out.println("2. View Prescriptions");
            System.out.println("3. Exit to Main Menu");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter patient name:");
                    String patientName = scanner.next();
                    System.out.println("Enter prescription:");
                    scanner.nextLine(); // Clear newline
                    String prescription = scanner.nextLine();
                    pharmacist.addPrescription(patientName, prescription);
                    break;
                case 2:
                    pharmacist.viewPrescriptions();
                    break;
                case 3:
                    System.out.println("Exiting to Main Menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Please select again.");
                    break;
            }
        }
    }

    /**
     * Performs the cashier task of processing payments.
     */
    private void cashierTasks() {
        System.out.println("Enter amount to process payment:");
        double amount = scanner.nextDouble();
        System.out.println("Enter patient name:");
        String patientName = scanner.next();
        cashier.processPayment(patientName, amount);
        System.out.println("Returning to main menu...");
    }
}

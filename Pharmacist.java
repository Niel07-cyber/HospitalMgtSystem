package VirtualDoctor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Pharmacist extends Staff { // Extending Staff class

    private final Scanner scanner = new Scanner(System.in);
    private static final String PRESCRIPTION_FILE = "prescriptions.txt"; // File path for prescriptions

    // Constructor to initialize name
    public Pharmacist(String name) {
        super(name); // Call to the Staff class constructor to initialize the name
    }

    @Override
    public void performDuties() {
        // Implementing the performDuties method from Staff class
        System.out.println(getName() + " is dispensing medications and advising patients.");
    }

    @Override
    public void displayInfo() {
        // Displaying pharmacist's info (name)
        System.out.println("Pharmacist's Name: " + getName());
    }

    // Method to dispense medication
    public void dispenseMedication(String patientName, String prescription) {
        // Only printing the dispensing message once
        System.out.println("\n--- Medication Receipt ---");
        System.out.println("Patient Name: " + patientName);

        // Use the enum to get the disease type based on the prescription
        DiseaseType diseaseType = DiseaseType.fromString(prescription);

        if (diseaseType != null) {
            // Display prescription and instructions using the enum
            System.out.println("Medicine Prescription by Doc : " + diseaseType.getPrescription());
            System.out.println("Instructions: " + diseaseType.getDosageInstruction());
        } else {
            System.out.println("Prescription: " + prescription);
            System.out.println("Instructions: Take one pill two times daily before meals.");
        }

        // Optional additional information like pharmacist's contact details or
        // follow-up information
        System.out.println("\n------ End of Receipt ------");

        // Continue with the questions to the patient
        System.out.println(" \n Pharmacist: Do you have any questions?");
        System.out.println("1. No, thank you.");
        System.out.println("2. What if I’m not well afterward?");
        System.out.println("3. Exit to Main Menu");

        int choice = scanner.nextInt();
        if (choice == 2) {
            System.out.println(
                    "Pharmacist: If you're not well after 7 days, please return to see the doctor for a follow-up.");
        } else if (choice == 3) {
            System.out.println("Returning to Main Menu...");
        } else {
            System.out.println("Pharmacist: Thank you, take care!");
        }
    }

    // Method to add a prescription to `prescriptions.txt`
    public void addPrescription(String patientName, String prescription) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PRESCRIPTION_FILE, true))) {
            writer.write("Patient: " + patientName + ", Prescription: " + prescription);
            writer.newLine();
            System.out.println("Prescription saved for patient: " + patientName);
        } catch (IOException e) {
            System.out.println("Error saving prescription: " + e.getMessage());
        }
    }

    // Method to view all prescriptions
    public void viewPrescriptions() {
        System.out.println("All Prescriptions:");
        try (BufferedReader reader = new BufferedReader(new FileReader(PRESCRIPTION_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading prescriptions: " + e.getMessage());
        }
    }

    // Static method to demonstrate utility without object context
    public static void displayInstructions() {
        System.out.println(
                "Pharmacist Instructions: Dispense medications and advise patients on follow-ups if necessary.");
    }
}

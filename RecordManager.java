package VirtualDoctor;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class manages patient records, including adding, retrieving, displaying, and saving/loading records from a file.
 * It utilizes encapsulation to maintain privacy of patient data and supports file management for persistence.
 */
public class RecordManager {
    // Using encapsulation for patient records and file path
    private List<Patient> patientRecords = new ArrayList<>();
    private static final String filePath = "patient_records.txt"; // static final to indicate a constant

    /**
     * Constructor to initialize RecordManager and load patient records from the file.
     */
    public RecordManager() {
        loadPatientRecords();
    }

    /**
     * Adds a new patient record and saves the updated records to the file.
     *
     * @param patient the patient record to be added
     */
    public void addPatientRecord(Patient patient) {
        patientRecords.add(patient);
        patient.incrementPatientCount(); // Using static method from Patient class
        savePatientRecords();
        System.out.println("Record added for " + patient.getName());
    }

    /**
     * Retrieves a patient record by name.
     *
     * @param patientName the name of the patient whose record is to be retrieved
     * @return the Patient object if found, or null if no record is found
     */
    public Patient getPatientRecord(String patientName) {
        for (Patient patient : patientRecords) {
            if (patient.getName().equalsIgnoreCase(patientName)) {
                return patient;
            }
        }
        System.out.println("No record found for " + patientName);
        return null;
    }

    /**
     * Displays all patient records in sorted order by patient name.
     */
    public void displayAllRecords() {
        System.out.println("All Patient Records:");
        if (patientRecords.isEmpty()) {
            System.out.println("No records found.");
        } else {
            Collections.sort(patientRecords, (p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName())); // Sorting patients by name
            for (Patient patient : patientRecords) {
                patient.displayPatientCard();
                System.out.println("Medical History:");
                patient.displayMedicalHistory();
                System.out.println();
            }
        }
    }

    /**
     * Saves all patient records to the file.
     * This method writes each patient's details to a file for persistence.
     */
    private void savePatientRecords() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Patient patient : patientRecords) {
                writer.write(patient.getName() + "," + patient.getAge() + "," + patient.getNationality() + ","
                        + patient.getAddress());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving patient records: " + e.getMessage());
        }
    }

    /**
     * Loads patient records from the file into memory.
     * This method reads the records from a file and initializes the patient records list.
     */
    private void loadPatientRecords() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 4) {
                    Patient patient = new Patient(details[0], Integer.parseInt(details[1]), details[2], details[3]);
                    patientRecords.add(patient);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading patient records: " + e.getMessage());
        }
    }
}

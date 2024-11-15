package VirtualDoctor;

import java.io.*;
import java.util.Scanner;

/**
 * This class represents the Doctor role in the VirtualDoctor application.
 * It extends the Staff class and adds specific functionality for diagnosing patients,
 * adding symptom-disease pairs, and prescribing medications with follow-up appointments.
 */
public class Doctor extends Staff {  // Extending Staff class
    private final int id;
    private static final String SYMPTOMS_DISEASES_FILE = "symptoms_diseases.txt";
    private static final String DISEASE_CASES_FILE = "disease_cases.txt";

    /**
     * Constructor to initialize the doctor's name and ID.
     *
     * @param name the name of the doctor
     * @param id   the ID of the doctor
     */
    public Doctor(String name, int id) {
        super(name);  // Call to the Staff constructor to initialize name
        this.id = id;
        loadSymptomsDiseases();
        loadDiseaseCases();
    }

    /**
     * This method implements the doctor duties.
     */
    @Override
    public void performDuties() {
        // Implementing the performDuties method from Staff class
        System.out.println(getName() + " is performing doctor duties.");
    }

    /**
     * This method displays the doctor's name and ID.
     */
    @Override
    public void displayInfo() {
        // Displaying doctor's info (name and id)
        System.out.println("Doctor's Name: " + getName());
        System.out.println("Doctor's ID: " + id);
    }

    /**
     * Loads symptom-disease-prescription data from the SYMPTOMS_DISEASES_FILE.
     */
    private void loadSymptomsDiseases() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SYMPTOMS_DISEASES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 3);
                if (parts.length == 3) {
                    // Processing each symptom-disease-prescription entry if found
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading symptoms and diseases data: " + e.getMessage());
        }
    }

    /**
     * Loads disease case counts from the DISEASE_CASES_FILE.
     */
    private void loadDiseaseCases() {
        File file = new File(DISEASE_CASES_FILE);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating disease cases file: " + e.getMessage());
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    // Process each disease case count
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading disease cases file: " + e.getMessage());
        }
    }

    /**
     * Adds a new symptom-disease pair to the SYMPTOMS_DISEASES_FILE.
     */
    public void addSymptomDiseasePair() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter symptoms for a disease (comma-separated if multiple):");
        String symptoms = scanner.nextLine().toLowerCase();

        System.out.println("Enter the corresponding disease for these symptoms:");
        String disease = scanner.nextLine();

        System.out.println("Enter the prescription for " + disease + ":");
        String prescription = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SYMPTOMS_DISEASES_FILE, true))) {
            writer.write(symptoms + " : " + disease + " : " + prescription);
            writer.newLine();
            System.out.println("Symptom, disease, and prescription added successfully.");
        } catch (IOException e) {
            System.out.println("Error saving symptom-disease-prescription entry: " + e.getMessage());
        }

        initializeDiseaseCount(disease);
    }

    /**
     * Initializes the disease count in the DISEASE_CASES_FILE if the disease doesn't exist.
     *
     * @param disease the disease name to initialize
     */
    private void initializeDiseaseCount(String disease) {
        boolean diseaseExists = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(DISEASE_CASES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(disease + " :")) {
                    diseaseExists = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading disease cases file: " + e.getMessage());
        }

        if (!diseaseExists) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(DISEASE_CASES_FILE, true))) {
                writer.write(disease + " : 0");
                writer.newLine();
            } catch (IOException e) {
                System.out.println("Error initializing disease count: " + e.getMessage());
            }
        }
    }

    /**
     * Diagnoses a patient based on their symptoms using the DiseaseType enum.
     *
     * @param patient the patient to diagnose
     * @return true if diagnosis is found, false otherwise
     */
    public boolean diagnosePatient(Patient patient) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Doctor: Welcome " + patient.getName() + ". Please describe your symptoms: (cough, whooping cough, bleeding, headache, etc.)");
        String patientSymptoms = scanner.nextLine().toLowerCase();

        // Using the DiseaseType enum to diagnose the patient
        DiseaseType diseaseType = DiseaseType.fromString(patientSymptoms);
        if (diseaseType != null) {
            System.out.println("Diagnosis: You have " + diseaseType.getDescription());
            // Prescribe medication and show dosage instructions
            System.out.println("Prescribed: " + diseaseType.getPrescription());
            // System.out.println("Instructions: " + diseaseType.getDosageInstruction());  // Display the instructions
            return true;
        } else {
            System.out.println("Diagnosis not found.");
            return false;
        }
    }

    /**
     * Increments the disease count in the DISEASE_CASES_FILE.
     *
     * @param disease the disease to increment count for
     */
    private void incrementDiseaseCount(String disease) {
        File tempFile = new File("temp_disease_cases.txt");
        File originalFile = new File(DISEASE_CASES_FILE);

        try (BufferedReader reader = new BufferedReader(new FileReader(originalFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean updated = false;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(disease + " :")) {
                    int currentCount = Integer.parseInt(line.split(":")[1].trim());
                    writer.write(disease + " : " + (currentCount + 1));
                    updated = true;
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }

            if (!updated) {
                writer.write(disease + " : 1");
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating disease cases file: " + e.getMessage());
        }

        if (!originalFile.delete() || !tempFile.renameTo(originalFile)) {
            System.out.println("Error updating disease cases data.");
        }
    }

    /**
     * Prescribes medication to a patient and schedules a follow-up appointment if necessary.
     *
     * @param patient  the patient to prescribe medication for
     * @param diagnosis the diagnosis for the patient
     */
    public void prescribeWithFollowUp(Patient patient, String diagnosis) {
        prescribeMedication(diagnosis, patient);
        patient.addMedicalHistory(diagnosis);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to schedule a follow-up appointment? (yes/no)");
        String followUpChoice = scanner.next().toLowerCase();

        if (followUpChoice.equals("yes")) {
            scheduleFollowUp(patient);
        } else {
            patient.setFollowUp(false);
            System.out.println("No follow-up appointment scheduled.");
        }
    }

    /**
     * Schedules a follow-up appointment for the patient.
     *
     * @param patient the patient to schedule a follow-up for
     */
    private void scheduleFollowUp(Patient patient) {
        patient.setFollowUp(true);
        System.out.println("Please select a follow-up date and time:");
        System.out.println("1. Monday, 9:00 AM");
        System.out.println("2. Tuesday, 11:00 AM");
        System.out.println("3. Wednesday, 1:00 PM");
        System.out.println("4. Thursday, 3:00 PM");
        System.out.println("5. Friday, 5:00 PM");

        Scanner scanner = new Scanner(System.in);
        int selectedOption = scanner.hasNextInt() ? scanner.nextInt() : -1;
        String followUpDate;

        switch (selectedOption) {
            case 1:
                followUpDate = "Monday, 9:00 AM";
                break;
            case 2:
                followUpDate = "Tuesday, 11:00 AM";
                break;
            case 3:
                followUpDate = "Wednesday, 1:00 PM";
                break;
            case 4:
                followUpDate = "Thursday, 3:00 PM";
                break;
            case 5:
                followUpDate = "Friday, 5:00 PM";
                break;
            default:
                System.out.println("Invalid selection. No follow-up scheduled.");
                return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("followup_appointments.txt", true))) {
            writer.write("Patient: " + patient.getName() + " - Follow-up on: " + followUpDate + "\n");
            System.out.println("Follow-up appointment scheduled on: " + followUpDate);
        } catch (IOException e) {
            System.out.println("Error saving follow-up appointment: " + e.getMessage());
        }
    }

    /**
     * Prescribes medication for the patient's diagnosis.
     *
     * @param diagnosis the diagnosis for which to prescribe medication
     * @param patient   the patient to prescribe medication for
     */
    private void prescribeMedication(String diagnosis, Patient patient) {
        System.out.println("Prescribing medication for " + diagnosis);

        DiseaseType diseaseType = DiseaseType.fromString(diagnosis);
        if (diseaseType != null) {
            String prescription = diseaseType.getPrescription();
            System.out.println("Prescribed: " + prescription);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("prescriptions.txt", true))) {
                writer.write("Patient: " + patient.getName() + " - Diagnosis: " + diagnosis + " - Prescribed medications: " + prescription);
                writer.newLine();
                System.out.println("Prescription saved for patient: " + patient.getName());
            } catch (IOException e) {
                System.out.println("Error saving prescription: " + e.getMessage());
            }
        } else {
            System.out.println("No specific medication found for " + diagnosis);
        }
    }

    /**
     * Generates a report on the current disease cases.
     */
    public void generateDiagnosisReport() {
        System.out.println("Generating Diagnosis Report:");
        try (BufferedReader reader = new BufferedReader(new FileReader(DISEASE_CASES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading disease cases file: " + e.getMessage());
        }
    }
}

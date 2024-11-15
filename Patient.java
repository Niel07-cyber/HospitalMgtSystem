package VirtualDoctor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a Patient in the VirtualDoctor application.
 * It encapsulates patient details, medical history, and follow-up status,
 * and provides methods to manipulate and display patient information.
 */
public class Patient {
    // Fields for patient details
    private String name;
    private final int age; // final to prevent modification after creation
    private final String nationality;
    private final String address;
    private List<String> medicalHistory = new ArrayList<>(); // encapsulated with private access
    private boolean followUp;

    /**
     * Constructor to initialize a patient's details.
     * 
     * @param name the name of the patient
     * @param age the age of the patient
     * @param nationality the nationality of the patient
     * @param address the address of the patient
     */
    public Patient(String name, int age, String nationality, String address) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.address = address;
        this.followUp = false; // Default follow-up status is false
    }

    /**
     * Gets the name of the patient.
     *
     * @return the patient's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the age of the patient.
     *
     * @return the patient's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the nationality of the patient.
     *
     * @return the patient's nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Gets the address of the patient.
     *
     * @return the patient's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Adds a new diagnosis entry to the patient's medical history.
     *
     * @param diagnosis the diagnosis to be added
     */
    public void addMedicalHistory(String diagnosis) {
        medicalHistory.add(diagnosis);
    }

    /**
     * Displays the patient's details, including name, age, nationality, and address.
     */
    public void displayPatientCard() {
        System.out.println("Patient Card:");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Nationality: " + nationality);
        System.out.println("Address: " + address);
    }

    /**
     * Displays the patient's medical history. If no history is available, it shows a message indicating that.
     * The history is displayed in sorted order for better readability.
     */
    public void displayMedicalHistory() {
        if (medicalHistory.isEmpty()) {
            System.out.println("No medical history available.");
        } else {
            Collections.sort(medicalHistory); // Sorting for better readability
            for (String record : medicalHistory) {
                System.out.println(" - " + record);
            }
        }
    }

    // Static method to count total patients (showing static method usage)
    private static int patientCount = 0;

    /**
     * Increments the patient count by 1.
     * This method is used to keep track of the total number of patients.
     */
    public static void incrementPatientCount() {
        patientCount++;
    }

    /**
     * Gets the total number of patients.
     *
     * @return the total number of patients
     */
    public static int getPatientCount() {
        return patientCount;
    }

    /**
     * Checks whether the patient requires a follow-up appointment.
     *
     * @return true if the patient requires a follow-up, false otherwise
     */
    public boolean isFollowUp() {
        return followUp;
    }

    /**
     * Sets the follow-up status for the patient.
     *
     * @param followUp the follow-up status to set
     */
    public void setFollowUp(boolean followUp) {
        this.followUp = followUp;
    }
}

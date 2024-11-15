package VirtualDoctor;

import java.io.*;
import java.util.Scanner;

/**
 * This class represents the Nurse role in the VirtualDoctor application.
 * It extends the Staff class and adds specific functionality for taking and saving patient vitals,
 * performing initial assessments, and previewing patient vitals.
 */
public class Nurse extends Staff {  // Extending Staff class

    private Scanner scanner = new Scanner(System.in);
    private static final String VITALS_FILE = "patient_vitals.txt"; // File to store patient vitals

    /**
     * Constructor to initialize the nurse's name.
     *
     * @param name the name of the nurse
     */
    public Nurse(String name) {
        super(name);  // Passing the name to the Staff class constructor
    }

    /**
     * This method implements the nursing duties.
     */
    @Override
    public void performDuties() {
        // Implementing the performDuties method from Staff class
        System.out.println(getName() + " is performing nursing duties.");
    }

    /**
     * This method displays the nurse's name.
     */
    @Override
    public void displayInfo() {
        // Displaying nurse's info (name)
        System.out.println("Nurse's Name: " + getName());
    }

    /**
     * This method takes the vitals (temperature and blood pressure) for a given patient.
     * It validates the inputs and saves them to a file.
     *
     * @param patientName the name of the patient
     */
    public void takeVitals(String patientName) {
        System.out.println("Nurse: Taking vitals for " + patientName);

        double temperature = getTemperature();
        String bloodPressure = getBloodPressure();

        saveVitalsToFile(patientName, temperature, bloodPressure);
        System.out.println("Vitals taken:");
        System.out.println("Temperature: " + temperature + "°C");
        System.out.println("Blood Pressure: " + bloodPressure);
    }

    /**
     * This method handles the input and validation of temperature, ensuring it falls within a valid range.
     * 
     * @return the valid temperature entered by the user
     */
    private double getTemperature() {
        double temperature = 0;
        while (true) {
            try {
                System.out.println("Enter temperature (°C):");
                temperature = Double.parseDouble(scanner.next());
                if (temperature < 36 || temperature > 99) {
                    throw new IllegalArgumentException("Temperature must be between 36°C and 99°C.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Temperature must be a number.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return temperature;
    }

    /**
     * This method handles the input and validation of blood pressure, ensuring the correct format is entered.
     * 
     * @return the valid blood pressure entered by the user
     */
    private String getBloodPressure() {
        String bloodPressure;
        while (true) {
            System.out.println("Enter blood pressure (e.g., 120/80):");
            bloodPressure = scanner.next();
            if (isValidBloodPressure(bloodPressure)) {
                break;
            } else {
                System.out.println("Blood pressure must be in the format 90/60 to 120/80.");
            }
        }
        return bloodPressure;
    }

    /**
     * This method validates if the entered blood pressure is in the correct format (systolic/diastolic).
     * 
     * @param bloodPressure the blood pressure to validate
     * @return true if the blood pressure format is valid, false otherwise
     */
    private boolean isValidBloodPressure(String bloodPressure) {
        String[] parts = bloodPressure.split("/");
        if (parts.length != 2) return false;

        try {
            int systolic = Integer.parseInt(parts[0]);
            int diastolic = Integer.parseInt(parts[1]);
            return systolic >= 90 && systolic <= 120 && diastolic >= 60 && diastolic <= 80;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * This method saves the patient's vitals (temperature and blood pressure) to a file.
     *
     * @param patientName the name of the patient
     * @param temperature the temperature of the patient
     * @param bloodPressure the blood pressure of the patient
     */
    private void saveVitalsToFile(String patientName, double temperature, String bloodPressure) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(VITALS_FILE, true))) {
            writer.write("Patient: " + patientName + " - Temperature: " + temperature + "°C, Blood Pressure: " + bloodPressure);
            writer.newLine();
            System.out.println("Vitals saved for patient: " + patientName);
        } catch (IOException e) {
            System.out.println("Error saving vitals: " + e.getMessage());
        }
    }

    /**
     * This method previews all the recorded patient vitals from the vitals file.
     */
    public void previewVitals() {
        System.out.println("Previewing all patient vitals:");
        try (BufferedReader reader = new BufferedReader(new FileReader(VITALS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No vitals records found.");
        } catch (IOException e) {
            System.out.println("Error reading vitals file: " + e.getMessage());
        }
    }

    /**
     * This method conducts an initial assessment of the patient based on the symptoms they report.
     * 
     * @param patientName the name of the patient
     */
    public void initialAssessment(String patientName) {
        System.out.println("Nurse: Conducting initial assessment for " + patientName);
        System.out.println("Please describe any symptoms (e.g., headache, nausea):");
        String symptoms = scanner.next();
        System.out.println("Noted symptoms: " + symptoms);
        System.out.println("Assessment complete. Patient can now see the doctor.");
    }

    /**
     * This method provides instructions on the correct format for entering vitals.
     */
    public static void printVitalsFormatInstructions() {
        System.out.println("Enter temperature in Celsius between 36 and 99, and blood pressure in format systolic/diastolic (e.g., 120/80).");
    }
}

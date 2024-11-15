package VirtualDoctor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents the Receptionist role in the VirtualDoctor application.
 * It handles booking patient appointments, managing schedules, displaying appointments,
 * and sorting them alphabetically.
 */
public class Receptionist extends Staff {  // Extending Staff class

    private final Scanner scanner = new Scanner(System.in);
    private final List<String> doctors = List.of("Dr. John Smith", "Dr. Sarah Lee", "Dr. Banini", "Dr. Babu");
    private final List<String> timeSlots = List.of("9:00 AM", "11:00 AM", "1:00 PM", "3:00 PM", "5:00 PM");

    /**
     * Constructor to initialize the name of the receptionist.
     *
     * @param name the name of the receptionist
     */
    public Receptionist(String name) {
        super(name);  // Call to the Staff constructor to initialize the name
    }

    /**
     * This method implements the receptionist's duties, such as booking patient appointments
     * and managing schedules.
     */
    @Override
    public void performDuties() {
        System.out.println(getName() + " is booking patient appointments and managing schedules.");
    }

    /**
     * Displays the receptionist's name.
     */
    @Override
    public void displayInfo() {
        System.out.println("Receptionist's Name: " + getName());
    }

    /**
     * Books an appointment for a patient with an available doctor and time slot.
     * It validates the selected doctor and time slot and checks availability.
     */
    public void bookPatientAppointment() {
        System.out.println("Enter patient name:");
        String patientName = scanner.next();

        System.out.println("Choose a doctor:");
        displayOptions(doctors);
        int doctorChoice = scanner.nextInt() - 1;
        if (!isValidChoice(doctorChoice, doctors)) return;

        String selectedDoctor = doctors.get(doctorChoice);

        System.out.println("Choose an appointment time:");
        displayOptions(timeSlots);
        int timeChoice = scanner.nextInt() - 1;
        if (!isValidChoice(timeChoice, timeSlots)) return;

        String selectedTime = timeSlots.get(timeChoice);
        String appointment = "Patient: " + patientName + ", Doctor: " + selectedDoctor + ", Time: " + selectedTime;

        if (isSlotAvailable(selectedDoctor, selectedTime)) {
            saveAppointment(appointment);
            System.out.println("Appointment booked successfully.");
        } else {
            System.out.println("This slot is already booked. Please choose another time.");
        }
    }

    /**
     * Displays a list of options to the user.
     *
     * @param options list of options to be displayed
     */
    private void displayOptions(List<String> options) {
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    /**
     * Checks if the user's choice is valid (within the list bounds).
     *
     * @param choice  the choice made by the user
     * @param options the list of available options
     * @return true if the choice is valid, false otherwise
     */
    private boolean isValidChoice(int choice, List<String> options) {
        if (choice < 0 || choice >= options.size()) {
            System.out.println("Invalid choice. Returning to main menu.");
            return false;
        }
        return true;
    }

    /**
     * Checks if the selected time slot is available for the selected doctor.
     *
     * @param doctor    the doctor selected for the appointment
     * @param timeSlot  the time slot selected for the appointment
     * @return true if the slot is available, false if it is already booked
     */
    private boolean isSlotAvailable(String doctor, String timeSlot) {
        try (BufferedReader reader = new BufferedReader(new FileReader("appointments.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Doctor: " + doctor) && line.contains("Time: " + timeSlot)) {
                    return false; // Slot already taken
                }
            }
        } catch (IOException e) {
            System.out.println("Error checking appointment availability: " + e.getMessage());
        }
        return true;
    }

    /**
     * Saves the appointment details to a file.
     *
     * @param appointment the appointment details to be saved
     */
    private void saveAppointment(String appointment) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("appointments.txt", true))) {
            writer.write(appointment);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving appointment: " + e.getMessage());
        }
    }

    /**
     * Displays all the appointments stored in the appointments file.
     */
    public void viewAppointments() {
        System.out.println("All Appointments:");
        try (BufferedReader reader = new BufferedReader(new FileReader("appointments.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading appointments: " + e.getMessage());
        }
    }

    /**
     * Sorts the appointments alphabetically by the doctor's name.
     * This method reads the appointments file, sorts the entries, and displays the sorted appointments.
     */
    public void sortAppointments() {
        List<String> appointments = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("appointments.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                appointments.add(line);
            }
            appointments.sort(String::compareToIgnoreCase);
            System.out.println("Sorted Appointments:");
            for (String appointment : appointments) {
                System.out.println(appointment);
            }
        } catch (IOException e) {
            System.out.println("Error sorting appointments: " + e.getMessage());
        }
    }
}

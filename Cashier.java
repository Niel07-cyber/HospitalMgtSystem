package VirtualDoctor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Cashier extends Staff {  // Extending Staff class

    private final Scanner scanner = new Scanner(System.in);
    private static final String PAYMENTS_FILE = "payments.txt"; // File path is final

    // Constructor to initialize name (calls parent constructor)
    public Cashier(String name) {
        super(name);  // Passing the name to the Staff class constructor
    }

    // Main method to handle patient payment process
    public void processPayment(String patientName, double amount) {
        System.out.println("The total amount due is " + amount + " euros.");
        System.out.println("Choose payment method:");
        System.out.println("1. By Card");
        System.out.println("2. By Cash");

        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    processCardPayment(patientName, amount);
                    break;
                case 2:
                    processCashPayment(patientName, amount);
                    break;
                default:
                    System.out.println("Invalid payment method selected.");
                    break;
            }
        } else {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
    }

    // Handles card payment processing with validation
    private void processCardPayment(String patientName, double amount) {
        String cardNumber = inputCardNumber();
        String cvv = inputCVV();
        String expiryDate = inputExpiryDate();

        System.out.println("Payment of " + amount + " euros has been processed. Thank you!");
        generateReceipt(patientName, "Card", amount, null);
        savePaymentToFile(patientName, "Card", amount, null);
    }

    // Input card number with validation
    private String inputCardNumber() {
        while (true) {
            System.out.println("Enter card number (12 digits):");
            String cardNumber = scanner.next();
            if (cardNumber.matches("\\d{12}")) return cardNumber;
            System.out.println("Invalid card number. It must be exactly 12 digits.");
        }
    }

    // Input CVV with validation
    private String inputCVV() {
        while (true) {
            System.out.println("Enter CVV (3 digits):");
            String cvv = scanner.next();
            if (cvv.matches("\\d{3}")) return cvv;
            System.out.println("Invalid CVV. It must be exactly 3 digits.");
        }
    }

    // Input expiry date with validation
    private String inputExpiryDate() {
        while (true) {
            System.out.println("Enter card expiry date (MM/YY):");
            String expiryDate = scanner.next();
            if (expiryDate.matches("\\d{2}/\\d{2}")) {
                String[] parts = expiryDate.split("/");
                int year = Integer.parseInt(parts[1]);
                if (year >= 24) return expiryDate;
                System.out.println("Invalid expiry year. The year must be 24 or later.");
            } else {
                System.out.println("Invalid expiry date format. It must be in the format MM/YY.");
            }
        }
    }

    // Handles cash payment processing with change calculation
    private void processCashPayment(String patientName, double amount) {
        System.out.println("Enter cash amount:");
        if (scanner.hasNextDouble()) {
            double cashAmount = scanner.nextDouble();
            if (cashAmount >= amount) {
                double change = cashAmount - amount;
                System.out.println("Payment accepted. Your balance is " + change + " euros.");
                generateReceipt(patientName, "Cash", amount, change);
                savePaymentToFile(patientName, "Cash", amount, change);
            } else {
                System.out.println("Insufficient funds. Transaction cancelled.");
            }
        } else {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
    }

    // Overloaded receipt generator for different types of payments
    private void generateReceipt(String patientName, String paymentMethod, double amount, Double change) {
        System.out.println("\n--- Receipt ---");
        System.out.println("Patient Name: " + patientName);
        System.out.println("Payment Method: " + paymentMethod);
        System.out.println("Amount Paid: " + amount + " euros");
        if (change != null) {
            System.out.println("Your balance: " + change + " euros");
        }
        System.out.println("Thank you for your payment!");
    }

    // Saves payment details to file
    private void savePaymentToFile(String patientName, String paymentMethod, double amount, Double change) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PAYMENTS_FILE, true))) {
            writer.write("Patient: " + patientName + ", Payment Method: " + paymentMethod + ", Amount: " + amount + " euros");
            if (change != null) writer.write(", Change: " + change + " euros");
            writer.newLine();
            System.out.println("Payment record saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving payment to file: " + e.getMessage());
        }
    }

    // Implementing performDuties() from Staff class
    @Override
    public void performDuties() {
        System.out.println(getName() + " is processing payments and handling transactions.");
    }

    // Implementing displayInfo() from Staff class
    @Override
    public void displayInfo() {
        System.out.println("Cashier's Name: " + getName());
    }
}

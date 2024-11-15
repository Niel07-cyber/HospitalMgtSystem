package VirtualDoctor;

/**
 * This abstract class represents a staff member in the VirtualDoctor
 * application.
 * It contains common properties and methods for all staff roles such as name
 * and the ability to display information.
 * Subclasses will implement the abstract method to perform specific duties.
 */
public abstract class Staff {
    private String name;

    /**
     * Constructor to initialize the name of the staff member.
     *
     * @param name the name of the staff member
     */
    public Staff(String name) {
        this.name = name;
    }

    /**
     * Getter for the name of the staff member.
     *
     * @return the name of the staff member
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the staff member.
     *
     * @param name the new name for the staff member
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Abstract method that subclasses will implement to perform their specific
     * duties.
     * This method defines the tasks that the staff member must perform, but the
     * implementation
     * will vary based on the role (e.g., Receptionist, Doctor, Nurse).
     */
    public abstract void performDuties();

    /**
     * Displays the information of the staff member, including their role and name.
     * The method uses the class name to display the role of the staff member.
     */
    public void displayInfo() {
        System.out.println(getClass().getSimpleName() + "'s Name: " + name);
    }
}

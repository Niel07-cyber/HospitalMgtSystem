package VirtualDoctor;

public enum DiseaseType {
    COLD("Cold", "cough", "antibiotics", "Take one pill two times daily before meals."),
    WHOOPING("covid", "whooping cough", "ibuprofen", "Take one pill two times daily before meals."),
    HEADACHE("Stress", "headache", "relaxation therapy", "Take one pill two times daily before meals."),
    GONO("Gonorrhea", "diarrhea", "Coarterm", "Take one pill two times daily before meals."),
    HIV("HIV", "loss of appetite", "corndirump", "Take one pill two times daily before meals."),
    STROKE("Stroke", "memory loss", "luphart", "Take one pill two times daily before meals.");

    private final String description;
    private final String symptoms;
    private final String prescription;
    private final String dosageInstruction;

    DiseaseType(String description, String symptoms, String prescription, String dosageInstruction) {
        this.description = description;
        this.symptoms = symptoms;
        this.prescription = prescription;
        this.dosageInstruction = dosageInstruction;
    }

    public String getDescription() {
        return description;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public String getPrescription() {
        return prescription;
    }

    public String getDosageInstruction() {
        return dosageInstruction;
    }

    public static DiseaseType fromString(String symptomsInput) {
        for (DiseaseType type : DiseaseType.values()) {
            // Check if symptomsInput contains any part of the disease's symptoms (case insensitive)
            if (type.getSymptoms().toLowerCase().contains(symptomsInput.toLowerCase())) {
                return type;
            }
        }
        return null; // No match found
    }
}

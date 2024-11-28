VirtualDoctor:
VirtualDoctor is a Java-based application designed to provide virtual healthcare services, enabling patients to receive medical care remotely. The application allows patients to book appointments, receive diagnoses, and follow prescribed treatment from medical professionals, all while managing their records efficiently through file-based storage. The system is built with extensibility in mind, with future plans for a Graphical User Interface (GUI) to enhance user interaction


Patient Side:
Patient Registration and Management: Patients can register their details, including name, age, nationality, and address, and store their information securely in the system. This information is managed through the application, ensuring a streamlined experience for the patient.
Appointments: Patients can book appointments with doctors based on available time slots. The system checks for slot availability and ensures no double-booking occurs. Once an appointment is confirmed, patients receive a reminder of the scheduled time.
Diagnosis and Prescription: When visiting the doctor, patients describe their symptoms. The doctor provides a diagnosis based on predefined symptoms, and medications are prescribed accordingly. Patients receive clear instructions on how to take the medication (e.g., dosage and frequency). This helps in ensuring patients follow the right treatment plan.
Follow-up Scheduling: After a diagnosis and prescription, patients may need to schedule follow-up appointments. The doctor can schedule follow-up visits to monitor the patient’s recovery and provide additional care as necessary.
Medical History and Records: All patient information, including medical history, prescriptions, and appointment records, are stored in text files for future reference. This allows patients to keep track of their past visits and treatments, ensuring continuity of care.


Staff Management Side:
Role-Based Access: The application uses role-based access for various staff members, including Doctors, Nurses, Pharmacists, and Receptionists. Each role has specific responsibilities and functionality:
Doctors: Responsible for diagnosing patients, prescribing medications, and managing follow-up appointments. They use the system to record diagnoses and prescriptions, which are saved to the patient’s medical history.
Nurses: Nurses can conduct initial assessments by taking patient vitals such as temperature and blood pressure. They can store this data and ensure that it’s updated in the patient’s records.
Pharmacists: Pharmacists verify prescriptions and provide medication to patients, ensuring that the correct dosage and instructions are followed. They also handle questions related to medications, ensuring patients are well-informed about their treatments.
Receptionists: Receptionists manage patient appointments, ensuring that there are no scheduling conflicts and that all appointment details are recorded correctly. They also handle patient inquiries and ensure smooth communication between patients and healthcare providers.
File Management: Staff members have access to the patient records stored in files, allowing them to update, view, and manage the data. For example:
Patient Records: Doctors and receptionists can access patient details, medical history, and appointment schedules.
Prescriptions: Pharmacists can view and add prescriptions to the system, ensuring that all medication details are recorded for future reference.
Appointment Management: Receptionists manage appointment slots and ensure proper scheduling to avoid conflicts.


Key Features:
File-Based Data Management: All patient records, appointments, and prescriptions are stored in text files, ensuring data persistence between application sessions. This is managed through the system’s file I/O operations.
Error Handling and Validation: The application includes error handling to ensure that patients and staff members input the correct data (e.g., valid symptoms, correct medication prescriptions). This prevents incorrect information from being processed and ensures that only the right prescriptions are dispensed.
Scalable and Extensible: The system is built to be easily scalable, and future enhancements may include a GUI, which will improve user interaction and facilitate the management of appointments and prescriptions with a more intuitive interface.


![image](https://github.com/Niel07-cyber/HospitalMgtSystem/blob/master/Screenshot%202024-11-28%20184907.png)

![image](https://github.com/Niel07-cyber/HospitalMgtSystem/blob/master/Screenshot%202024-11-28%20185032.png)

![image](https://github.com/Niel07-cyber/HospitalMgtSystem/blob/master/Screenshot%202024-11-28%20185253.png)

![image](https://github.com/Niel07-cyber/HospitalMgtSystem/blob/master/Screenshot%202024-11-28%20185344.png)

![image](https://github.com/Niel07-cyber/HospitalMgtSystem/blob/master/Screenshot%202024-11-28%20185954.png)

![image](https://github.com/Niel07-cyber/HospitalMgtSystem/blob/master/Screenshot%202024-11-28%20190101.png)

![image](https://github.com/Niel07-cyber/HospitalMgtSystem/blob/master/Screenshot%202024-11-28%20190145.png)

![image](https://github.com/Niel07-cyber/HospitalMgtSystem/blob/master/Screenshot%202024-11-28%20190255.png)

![image](https://github.com/Niel07-cyber/HospitalMgtSystem/blob/master/Screenshot%202024-11-28%20190411.png)


![image](https://github.com/Niel07-cyber/HospitalMgtSystem/blob/master/Screenshot%202024-11-28%20191140.png)

![image](https://github.com/Niel07-cyber/HospitalMgtSystem/blob/master/Screenshot%202024-11-28%20191202.png)


![image](https://github.com/Niel07-cyber/HospitalMgtSystem/blob/master/Screenshot%202024-11-28%20191245.png)

![image](https://github.com/Niel07-cyber/HospitalMgtSystem/blob/master/Screenshot%202024-11-28%20191316.png)



![image](https://github.com/user-attachments/assets/f7d39b46-12a5-435b-ac2d-f182d6008ef2)



Future Enhancements:
Graphical User Interface (GUI): The current system is text-based, but a GUI will be implemented in the future to provide a more user-friendly interface for both patients and medical staff, enhancing the overall experience and ease of use.

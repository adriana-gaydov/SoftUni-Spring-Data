package _04_HospitalDatabase;

import Tools.ConnectionCreator;
import Tools.Reader;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    static EntityManager entityManager;

    public static void main(String[] args) throws IOException {
        entityManager = ConnectionCreator.getEntityManager();

        entityManager.getTransaction().begin();        // пълна импровизация за UI, but i'm tireeed ;-;

        System.out.println("ENTER 1 to add patient, 2 to add a patient and a new record, 3 to print all records, \"Exit\" to leave:");
        String session = Reader.readString();
        while (!session.equalsIgnoreCase("Exit")) {

            switch (session) {
                case "1":
                    try {
                        Patient patient = inputForPatient();
                        entityManager.persist(patient);
                    } catch (Exception e) {
                        System.out.println("Unsuccessful operation");
                    }
                    break;
                case "2":
                    try {
                        addPatientAndRecord();
                    } catch (Exception e) {
                        System.out.println("Unsuccessful operation");
                    }
                    break;
                case "3":
                    getAllRecords();
            }

            System.out.println("ENTER 1 to add patient, 2 to add a patient and a new record, 3 to print all records, \"Exit\" to leave:");
            session = Reader.readString();
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void getAllRecords() {
        List<Record> allRecords = entityManager.createQuery("SELECT r FROM Record r", Record.class)
                .getResultList();

        System.out.println(allRecords);
    }

    private static void addPatientAndRecord() throws IOException {
        Patient patient = inputForPatient();
        Visitation visitation = inputForVisitation();
        Diagnose diagnose = inputForDiagnose();

        System.out.println("Enter count of medicaments:");
        int count = Reader.readInt();

        Record record = new Record(patient, visitation, diagnose);

        for (int i = 0; i < count; i++) {
            Medicament medicament = inputForMedicament();
            record.addMedicament(medicament);
            entityManager.persist(medicament);
        }

        entityManager.persist(diagnose);
        entityManager.persist(visitation);
        entityManager.persist(patient);
        entityManager.persist(record);
    }

    private static Medicament inputForMedicament() throws IOException {
        System.out.println("Enter name of medicament:");
        String name = Reader.readString();

        return new Medicament(name);
    }

    private static Diagnose inputForDiagnose() throws IOException {
        System.out.println("Enter name of diagnose:");
        String name = Reader.readString();

        System.out.println("Enter comment of diagnose (optional):");
        String comment = Reader.readString();

        return new Diagnose(name, comment.isBlank() ? null : comment);
    }

    private static Visitation inputForVisitation() throws IOException {
        System.out.println("Enter date of visitation (yyyy-mm-dd):");
        String dateStr = Reader.readString();

        LocalDate date = getLocalDateFromStr(dateStr);

        System.out.println("Enter comment of visitation (optional):");

        String comment = Reader.readString();

        return new Visitation(date, comment.isBlank() ? null : comment);
    }

    private static Patient inputForPatient() throws IOException {
        System.out.println("Enter first name of patient:");
        String firstName = Reader.readString();

        System.out.println("Enter last name of patient:");
        String lastName = Reader.readString();

        System.out.println("Enter address of patient:");
        String address = Reader.readString();

        System.out.println("Enter email of patient (optional):");
        String email = Reader.readString();

        System.out.println("Enter date of birth of patient (yyyy-mm-dd):");
        String dateStr = Reader.readString();
        LocalDate birthDate = getLocalDateFromStr(dateStr);

        System.out.println("Enter picture URL of patient (optional):");
        String pictureURL = Reader.readString();

        System.out.println("Does patient have medical insurance? (Yes/No)");
        String medIns = Reader.readString();


        return new Patient(firstName, lastName, address, email.isBlank() ? null : email, birthDate, pictureURL.isBlank() ? null : pictureURL, medIns.equalsIgnoreCase("Yes"));
    }

    private static LocalDate getLocalDateFromStr(String dateStr) throws IOException {
        return LocalDate.parse(dateStr);
    }
}

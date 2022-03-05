package _04_HospitalDatabase;

import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "records")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "visitation_id", referencedColumnName = "id")
    private Visitation visitation;

    @OneToOne
    @JoinColumn(name = "diagnose_id", referencedColumnName = "id")
    private Diagnose diagnose;

    @OneToMany
    @JoinTable(name = "record_medicaments",
    joinColumns = @JoinColumn(name = "record_id"),
    inverseJoinColumns = @JoinColumn(name = "medicament_id"))
    private Set<Medicament> medicaments;

    public Record() {
    }

    public Record(Patient patient, Visitation visitation, Diagnose diagnose) {
        this.patient = patient;
        this.visitation = visitation;
        this.diagnose = diagnose;
        this.medicaments = new HashSet<>();
    }

    public void addMedicament(Medicament medicament) {
        this.medicaments.add(medicament);
    }

    public int getId() {
        return id;
    }

    public Diagnose getDiagnose() {
        return diagnose;
    }

    public Set<Medicament> getMedicaments() {
        return medicaments;
    }

    public Visitation getVisitation() {
        return visitation;
    }

    public Patient getPatient() {
        return patient;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", patient=" + patient +
                ", visitation=" + visitation +
                ", diagnose=" + diagnose +
                ", medicaments=" + medicaments.stream().map(Medicament::toString).collect(Collectors.joining(", ")) +
                '}';
    }
}

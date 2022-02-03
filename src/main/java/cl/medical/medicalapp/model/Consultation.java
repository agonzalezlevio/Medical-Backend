package cl.medical.medicalapp.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "consultation")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idConsultation;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "id_patient", nullable = false, foreignKey = @ForeignKey(name = "FK_consultation_patient"))
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "id_specialty", nullable = false, foreignKey = @ForeignKey(name = "FK_consultation_specialty"))
    private Specialty specialty;

    @ManyToOne
    @JoinColumn(name = "id_doctor", nullable = false, foreignKey = @ForeignKey(name = "FK_consultation_doctor"))
    private Doctor doctor;

    @OneToMany(mappedBy = "consultation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Detail> details;

    @ManyToMany
    @JoinTable(
            name = "consultation_examination",
            joinColumns = @JoinColumn(name = "id_consultation", referencedColumnName = "idConsultation"),
            inverseJoinColumns = @JoinColumn(name = "id_examination", referencedColumnName = "idExamination"))
    private List<Examination> examinations;

    public Integer getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(Integer idConsultation) {
        this.idConsultation = idConsultation;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<Detail> getConsultationDetails() {
        return details;
    }

    public void setConsultationDetails(List<Detail> details) {
        this.details = details;
    }

    public List<Examination> getExaminations() {
        return examinations;
    }

    public void setExaminations(List<Examination> examinations) {
        this.examinations = examinations;
    }
}

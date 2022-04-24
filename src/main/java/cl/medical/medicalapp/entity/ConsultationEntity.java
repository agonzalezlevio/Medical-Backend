package cl.medical.medicalapp.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "consultation")
public class ConsultationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idConsultation;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "id_patient", nullable = false, foreignKey = @ForeignKey(name = "FK_consultation_patient"))
    private PatientEntity patient;

    @ManyToOne
    @JoinColumn(name = "id_specialty", nullable = false, foreignKey = @ForeignKey(name = "FK_consultation_specialty"))
    private SpecialtyEntity specialty;

    @ManyToOne
    @JoinColumn(name = "id_doctor", nullable = false, foreignKey = @ForeignKey(name = "FK_consultation_doctor"))
    private DoctorEntity doctor;

    @OneToMany(mappedBy = "consultation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetailEntity> details;

    @ManyToMany
    @JoinTable(
            name = "consultation_examination",
            joinColumns = @JoinColumn(name = "id_consultation", referencedColumnName = "idConsultation"),
            inverseJoinColumns = @JoinColumn(name = "id_examination", referencedColumnName = "idExamination"))
    private List<ExaminationEntity> examinations;

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

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public SpecialtyEntity getSpecialty() {
        return specialty;
    }

    public void setSpecialty(SpecialtyEntity specialty) {
        this.specialty = specialty;
    }

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
    }

    public List<DetailEntity> getDetails() {
        return details;
    }

    public void setDetails(List<DetailEntity> details) {
        this.details = details;
    }

    public List<ExaminationEntity> getExaminations() {
        return examinations;
    }

    public void setExaminations(List<ExaminationEntity> examinations) {
        this.examinations = examinations;
    }
}

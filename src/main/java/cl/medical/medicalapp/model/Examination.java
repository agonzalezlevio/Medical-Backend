package cl.medical.medicalapp.model;

import javax.persistence.*;

@Entity
@Table(name = "examination")
public class Examination {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idExamination;

    @Column(nullable = false, length = 72)
    private String name;

    @Column(length = 256)
    private String description;

    public Integer getIdExamination() {
        return idExamination;
    }

    public void setIdExamination(Integer idExamination) {
        this.idExamination = idExamination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

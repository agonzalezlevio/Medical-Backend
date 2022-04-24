package cl.medical.medicalapp.model;

import org.springframework.hateoas.RepresentationModel;

public class ConsultationResumeModel extends RepresentationModel<ConsultationResumeModel> {

    private Integer idConsultation;

    private String fullNamePatient;

    private String fullNameDoctor;

    public Integer getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(Integer idConsultation) {
        this.idConsultation = idConsultation;
    }

    public String getFullNamePatient() {
        return fullNamePatient;
    }

    public void setFullNamePatient(String fullNamePatient) {
        this.fullNamePatient = fullNamePatient;
    }

    public String getFullNameDoctor() {
        return fullNameDoctor;
    }

    public void setFullNameDoctor(String fullNameDoctor) {
        this.fullNameDoctor = fullNameDoctor;
    }
}

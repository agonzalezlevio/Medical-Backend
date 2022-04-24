package cl.medical.medicalapp.assembler;

import cl.medical.medicalapp.controller.ConsultationController;
import cl.medical.medicalapp.controller.DoctorController;
import cl.medical.medicalapp.controller.PatientController;
import cl.medical.medicalapp.entity.ConsultationEntity;
import cl.medical.medicalapp.model.ConsultationResumeModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ConsultationResumeAssembler implements RepresentationModelAssembler<ConsultationEntity, ConsultationResumeModel> {

    public static final String FIELD_SEPARATOR = " ";

    @Override
    public ConsultationResumeModel toModel(ConsultationEntity consultation) {
        ConsultationResumeModel consultationResumeModel = this.toConsultationResumeModel(consultation);
        return consultationResumeModel;
    }

    @Override
    public CollectionModel<ConsultationResumeModel> toCollectionModel(Iterable<? extends ConsultationEntity> consultations) {
        List<ConsultationResumeModel> consultationResumeModels = new ArrayList<>();
        consultations.forEach(consultation -> {
            consultationResumeModels.add(this.toConsultationResumeModel(consultation));
        });
        return CollectionModel.of(consultationResumeModels);
    }

    private ConsultationResumeModel toConsultationResumeModel(ConsultationEntity consultation) {
        ConsultationResumeModel consultationResumeModel = new ConsultationResumeModel();
        consultationResumeModel.setIdConsultation(consultation.getIdConsultation());
        consultationResumeModel.setFullNameDoctor(consultation.getDoctor().getFirstName() + FIELD_SEPARATOR + consultation.getDoctor().getLastName());
        consultationResumeModel.setFullNamePatient(consultation.getPatient().getFirstName() + FIELD_SEPARATOR + consultation.getPatient().getLastName());
        // patient
        Link patientLink = linkTo(methodOn(PatientController.class).findById((consultation.getPatient().getIdPatient()))).withRel("patient");
        consultationResumeModel.add(patientLink);
        // doctor
        Link doctorLink = linkTo(methodOn(DoctorController.class).findById((consultation.getDoctor().getIdDoctor()))).withRel("doctor");
        consultationResumeModel.add(doctorLink);
        // self
        Link selfLink = linkTo(methodOn(ConsultationController.class).findById((consultation.getIdConsultation()))).withSelfRel();
        consultationResumeModel.add(selfLink);
        return consultationResumeModel;
    }

}
